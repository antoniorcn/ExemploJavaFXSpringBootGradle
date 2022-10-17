package edu.curso.boundaries;

import animatefx.animation.BounceInLeft;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.SQLExceptionOverride;
import edu.curso.componentes.ImageComponent;
import edu.curso.componentes.LabelSuperTitulo;
import edu.curso.componentes.SelectionComponent;
import edu.curso.componentes.SuperButton;
import edu.curso.models.MenuInfo;
import edu.curso.models.Servico;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TesteBoundary extends BoundaryRenderGeneric {

    private SuperButton btn = new SuperButton("Confirmar", SuperButton.BUTTON_TYPE.OK);;
    private Label imageComponentTest;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> SOUNDS = Map.of(
            "profession", "profession.mp3",
            "pay", "pay.mp3",
            "prepare", "prepare.mp3",
            "sparta", "sparta.mp3");

    @Override
    public Pane render() {
        BorderPane pane = new BorderPane();
        VBox box = new VBox();
        box.setStyle("-fx-padding: 10px");
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        pane.setTop(new LabelSuperTitulo("Teste de Componentes"));
            imageComponentTest = new Label("Teste de Componente");
            SelectionComponent selItem = new SelectionComponent(imageComponentTest);
            box.getChildren().add(selItem);
        ScrollPane scroll = new ScrollPane(box);
        scroll.setPannable(true);
        pane.setCenter(scroll);
        pane.setBottom(btn);
        return pane;
    }

    @Override
    public void init() {

       //  btn.setOnAction(e -> getCommanderExecutor().executeCommand("OPEN", "profissionais"));

    }
    @Override
    public void animate() {
        new BounceInLeft(imageComponentTest).play();
    }
}
