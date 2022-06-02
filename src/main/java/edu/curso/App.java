package edu.curso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class App extends Application {

    @Autowired
    WelcomeController welcomeController;

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
    @Override
    public void init() {
        SpringApplication.run(getClass()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) {
        System.out.println(welcomeController.getWelcomeMessage());
        Parent welcomePane = loadFxml("/welcome.fxml");
        Scene scn = new Scene(welcomePane, 600, 400);
        stage.setScene(scn);
        stage.show();
    }

    private Parent loadFxml(String view) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        loader.setControllerFactory(param -> welcomeController);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println("IOException while loading resource " + view);        }
        return loader.getRoot();
    }
}
