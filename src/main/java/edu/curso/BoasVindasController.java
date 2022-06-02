package edu.curso;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class BoasVindasController implements Initializable {
    @FXML
    public Label labelBoasVindas;

    @Autowired
    private BoasVindasServico boasVindas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String msg = boasVindas.getBoasVindas();
        System.out.println("Boas Vindas: " + msg);
        labelBoasVindas.setText(msg);
    }

    public String getBoasVindas() {
        return boasVindas.getBoasVindas();
    }

    public void setBoasVindas(BoasVindasServico boasVindas) {
        this.boasVindas = boasVindas;
    }
}