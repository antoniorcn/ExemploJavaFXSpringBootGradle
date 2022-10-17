package edu.curso;

import edu.curso.boundaries.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class App extends Application implements CommanderExecutor {

    @Autowired
    private ApplicationContext appContext;

    private final BorderPane pane = new BorderPane();
    private Map<String, BoundaryRender> telas = new HashMap<>();

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    @Override
    public void init() {
        SpringApplication.run(getClass()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    public void initAll() {
//        for (BoundaryRender tela : telas.values()) {
//            tela.init();
//            tela.setCommandExecutor(this);
//        }
        telas.get("teste").init();
    }

    @Override
    public void start(Stage stage) {
        // Inicialização das telas
        telas.put("servicos", new ServicosBoundary());
        telas.put("profissionais", new ProfissionalBoundary());
        telas.put("calendar", new CalendarBoundary());
        telas.put("teste", new TesteBoundary());

        initAll();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        executeCommand("OPEN", "teste");

        Scene scene = new Scene(pane, screenBounds.getWidth() * 2 / 3, screenBounds.getHeight() * 0.90 );
        stage.setScene(scene);
        stage.show();
    }

    private Parent loadFxml(String viewFileName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFileName));
        loader.setControllerFactory(appContext::getBean);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println("IOException while loading resource " + viewFileName);
        }
        return loader.getRoot();
    }

    @Override
    public void executeCommand(String command, Object... parameters) {
        if ("OPEN".equals(command) && parameters.length > 0) {
            String strBoundary = parameters[0].toString();
            BoundaryRender tela = telas.get(strBoundary);
            pane.setCenter(tela.render());
            tela.animate();
        } else if ("SAIR".equals(command)) {
            Platform.exit();
            ((ConfigurableApplicationContext) appContext).close();
            // SpringApplication.exit(appContext, () -> 0);
            System.exit(0);
        }
    }

    public void setAppContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }
}
