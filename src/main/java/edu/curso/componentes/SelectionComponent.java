package edu.curso.componentes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

public class SelectionComponent extends StackPane {

    private static final String SVG_CHECK_CONTENT = "m22.77 290.12c13.492-26.988 40.48-60.723 87.711-47.23 " +
            "40.48 13.492 67.469 53.977 87.711 107.95 161.93-182.17 296.87-310.36 458.8-350.84 20.242 0 " +
            "26.988 0 13.492 13.492-175.42 121.45-344.1 303.61-479.04 539.76-6.7461 6.7461-13.492 6.7461-20.242 " +
            "0-26.988-67.469-47.23-134.94-80.965-202.41-13.492-33.734-33.734-60.723-67.469-60.723z";

    private BooleanProperty selected = new SimpleBooleanProperty(false);

    private SVGPath svg = new SVGPath();
    private Node frontComponent = new Pane();
    private Region frontCover = new Region();
    private Node currentNode;

    public SelectionComponent(Node node) {
        this.currentNode = node;
        Bounds bounds = node.layoutBoundsProperty().get();
        svg.setContent(SVG_CHECK_CONTENT);
        frontCover.setShape(svg);
        frontCover.setPrefSize(bounds.getWidth(), bounds.getHeight());
        frontCover.setMaxSize(bounds.getWidth(), bounds.getHeight());
        frontCover.setMinSize(bounds.getWidth(), bounds.getHeight());

        this.getChildren().add(node);
        this.getChildren().add(frontCover);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.toggleSelection();
        });

        selected.addListener( (prop, oldValue, newValue) -> {
            frontCover.setVisible(newValue);
        });
    }

    private void toggleSelection() {
        System.out.println("### CLICADO ###  SELECTED " + selected.get());
        this.selected.set(!this.selected.get());
    }

}
