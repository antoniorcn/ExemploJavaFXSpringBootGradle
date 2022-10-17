package edu.curso.componentes;

import edu.curso.models.MenuInfo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageComponent extends Pane {

    private static final String SVG_CHECK_CONTENT = "m22.77 290.12c13.492-26.988 40.48-60.723 87.711-47.23 " +
            "40.48 13.492 67.469 53.977 87.711 107.95 161.93-182.17 296.87-310.36 458.8-350.84 20.242 0 " +
            "26.988 0 13.492 13.492-175.42 121.45-344.1 303.61-479.04 539.76-6.7461 6.7461-13.492 6.7461-20.242 " +
            "0-26.988-67.469-47.23-134.94-80.965-202.41-13.492-33.734-33.734-60.723-67.469-60.723z";

    private BooleanProperty selected = new SimpleBooleanProperty(false);

    SVGPath svg = new SVGPath();
    private MenuInfo menuInfo;

    public ImageComponent(MenuInfo menuInfo) throws IOException {
        this.menuInfo = menuInfo;
        File imgFile = new ClassPathResource(menuInfo.getImagemNome()).getFile();
        try {
            svg.setContent(SVG_CHECK_CONTENT);
            Region svgRegion = new Region();
            svgRegion.setShape(svg);
            svgRegion.setPrefSize(200, 200);
            svgRegion.setMaxSize(200, 200);
            svgRegion.setMinSize(200, 200);
            HBox allPane = new HBox();
            allPane.getChildren().add(svgRegion);
            ImageView img = new ImageView(new Image(new FileInputStream(imgFile.getAbsolutePath())));
            img.setFitWidth(menuInfo.getImageWidth() * 0.6);
            img.setFitHeight(menuInfo.getImageHeight() * 0.6);

            BorderPane infoPane = new BorderPane();
            infoPane.setTop(new LabelTitulo(menuInfo.getTitulo()));
            infoPane.setCenter(new Label(menuInfo.getDescricao()));
            infoPane.setBottom(new LabelPreco(menuInfo.getPreco()));
            allPane.setSpacing(20);
            allPane.setAlignment(Pos.CENTER);
            allPane.getChildren().add(img);
            allPane.getChildren().add(infoPane);
            allPane.setStyle("-fx-padding: 20px");
            this.getChildren().add(allPane);
            this.setStyle(menuInfo.getStyle());
            this.setStyle("-fx-background-color: #faebd7; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
                    "-fx-border-color: #8b4513; -fx-border-width: 5px; -fx-background-radius: 5em; " +
                    "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");

            this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                this.toggleSelection();
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        selected.addListener( (prop, oldValue, newValue) -> {
            if (newValue) {
                this.setStyle("-fx-background-color: #8b4513; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
                        "-fx-border-color: #faebd7; -fx-border-width: 5px; -fx-background-radius: 5em; " +
                        "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");
            } else {
                this.setStyle("-fx-background-color: #faebd7; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
                        "-fx-border-color: #8b4513; -fx-border-width: 5px; -fx-background-radius: 5em; " +
                        "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");
            }
        });
    }

    private void toggleSelection() {
        System.out.println("### CLICADO ### " + menuInfo.getTitulo() + " SELECTED " + selected.get());
        this.selected.set(!this.selected.get());
    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        selected.addListener( (prop, oldValue, newValue) -> {
//            if (newValue) {
//                this.setStyle("-fx-background-color: #8b4513; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
//                        "-fx-border-color: #faebd7; -fx-border-width: 5px; -fx-background-radius: 5em; " +
//                        "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");
//            } else {
//                this.setStyle("-fx-background-color: #faebd7; -fx-padding: 0.3em; -fx-spacing: 0.5em; " +
//                        "-fx-border-color: #8b4513; -fx-border-width: 5px; -fx-background-radius: 5em; " +
//                        "-fx-border-radius: 5em; -fx-margin: 20px; -fx-text-fill: #a0522d; ");
//            }
//        });
//    }
}
