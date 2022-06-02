package edu.curso;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WelcomeController implements Initializable {
    @FXML
    public Label welcomeLabel;

    @Autowired
    private GreetingService greetingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String msg = greetingService.getWelcomeGreeting();
        // String msg = "Hello World JavaFX + SpringBoot";
        System.out.println("Greeting: " + msg);
        welcomeLabel.setText(msg);
    }

    public String getWelcomeMessage() {
        return greetingService.getWelcomeGreeting();
    }

}