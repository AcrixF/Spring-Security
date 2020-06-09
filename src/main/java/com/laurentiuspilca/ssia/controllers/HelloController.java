package com.laurentiuspilca.ssia.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    /*
    @GetMapping("/hello")
    public String hello() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return "Hello " + authentication.getName() + "!";
    }
    */

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "<h1> Hello " + authentication.getName() + "!</h1>";
    }

    @GetMapping("/bye")
    @Async
    public void goodBye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println("Good By " + username);
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
          SecurityContext context = SecurityContextHolder.getContext();
          return context.getAuthentication().getName();
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            Callable<String> contextTask = new DelegatingSecurityContextCallable<>(task);

            return "Ciao, " + executor.submit(contextTask).get() + "!";
        } finally {
            executor.shutdown();;
        }

    }

    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            String username = context.getAuthentication().getName();
            return username;
        };

        ExecutorService service = Executors.newCachedThreadPool();
        service = new DelegatingSecurityContextExecutorService(service);

        try {
            return "Hola, " + service.submit(task).get() + "!";
        } finally {
            service.shutdown();
        }
    }
}
