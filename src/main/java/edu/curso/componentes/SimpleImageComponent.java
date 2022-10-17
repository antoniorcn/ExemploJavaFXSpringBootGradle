package edu.curso.componentes;

import edu.curso.models.MenuInfo;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SimpleImageComponent extends Pane {

    public SimpleImageComponent(MenuInfo menuInfo) throws IOException {
        File imgFile = new ClassPathResource(menuInfo.getImagemNome()).getFile();
        try {
            HBox allPane = new HBox();
            Image img = new Image(new FileInputStream(imgFile.getAbsolutePath()));
            ImageView imgView = new ImageView(img);

            Circle circle = new Circle(32);
            ImagePattern pattern = new ImagePattern(img);
            circle.setFill(pattern);

            VBox infoPane = new VBox();
            infoPane.setAlignment(Pos.CENTER);
            infoPane.getChildren().add(new LabelTitulo(menuInfo.getTitulo()));
            allPane.setSpacing(20);
            allPane.getChildren().add(circle);
            allPane.getChildren().add(infoPane);
            allPane.setStyle("-fx-padding: 20px");
            this.getChildren().add(allPane);
            this.setStyle(menuInfo.getStyle());
            this.setStyle("-fx-background-color: #faebd7; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
                    "-fx-border-color: #8b4513; -fx-border-width: 5px; -fx-background-radius: 5em; " +
                    "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
