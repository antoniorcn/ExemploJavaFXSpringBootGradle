package edu.curso;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getWelcomeGreeting() {
        return "Welcome and have a nice day!";
    }
}