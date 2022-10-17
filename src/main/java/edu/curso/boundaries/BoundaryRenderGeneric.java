package edu.curso.boundaries;

import javafx.scene.layout.Pane;

public class BoundaryRenderGeneric implements BoundaryRender {
    private CommanderExecutor commanderExecutor = null;

    @Override
    public Pane render() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void animate() {

    }

    @Override
    public void setCommandExecutor(CommanderExecutor value) {
        this.commanderExecutor = value;
    }

    @Override
    public CommanderExecutor getCommanderExecutor() {
        return commanderExecutor;
    }
}
