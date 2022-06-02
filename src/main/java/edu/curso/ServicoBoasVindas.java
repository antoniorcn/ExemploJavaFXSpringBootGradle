package edu.curso;

import org.springframework.stereotype.Service;

@Service
public class ServicoBoasVindas {
    public String getBoasVindas() {
        return "Ola Mundo JavaFX SpringBoot e Gradle";
    }
}