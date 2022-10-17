package edu.curso.boundaries;
import javafx.scene.layout.Pane;

public interface BoundaryRender {
    Pane render();
    void init();
    void animate();
    void setCommandExecutor(CommanderExecutor value);
    CommanderExecutor getCommanderExecutor();
}