package edu.curso.boundaries;

public interface CommanderExecutor {

    void executeCommand(String command, Object ... parameters);
}
