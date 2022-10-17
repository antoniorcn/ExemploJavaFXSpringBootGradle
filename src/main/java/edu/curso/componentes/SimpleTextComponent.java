package edu.curso.componentes;

import edu.curso.models.MenuInfo;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class SimpleTextComponent extends Pane {

    public SimpleTextComponent(MenuInfo menuInfo) throws IOException {
        VBox allPane = new VBox();
        allPane.setAlignment(Pos.CENTER);
        allPane.getChildren().add(new LabelTitulo(menuInfo.getTitulo()));
        this.getChildren().add(allPane);
        this.setStyle(menuInfo.getStyle());
    }

}
