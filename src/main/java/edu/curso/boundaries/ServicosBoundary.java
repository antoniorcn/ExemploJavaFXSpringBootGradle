package edu.curso.boundaries;

import animatefx.animation.BounceInLeft;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.curso.componentes.ImageComponent;
import edu.curso.componentes.LabelSuperTitulo;
import edu.curso.componentes.SuperButton;
import edu.curso.models.MenuInfo;
import edu.curso.models.Servico;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Box;
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

public class ServicosBoundary extends BoundaryRenderGeneric {

    private SuperButton btn = new SuperButton("Confirmar", SuperButton.BUTTON_TYPE.OK);;
    private List<ImageComponent> imagesComponents = new ArrayList<>();
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
        pane.setTop(new LabelSuperTitulo("Escolha seu serviço"));
        for (ImageComponent imgCmp : imagesComponents) {
            box.getChildren().add(imgCmp);
        }
        ScrollPane scroll = new ScrollPane(box);
        scroll.setPannable(true);
        pane.setCenter(scroll);
        pane.setBottom(btn);
        return pane;
    }

    public void playSound(String soundName) throws Exception {
        File sndFile = new ClassPathResource("sounds/" +soundName).getFile();
        Media sound = new Media(sndFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    @Override
    public void init() {

        try {
            System.out.println("Carregando Serviços");
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/servicos"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Servico[] menuTemp = objectMapper.readValue(response.body(), Servico[].class);
            System.out.println("Resposta recebida: " + response.body());
            System.out.println("GERANDO MENUS");
            imagesComponents.clear();
            for (Servico svc : menuTemp) {

                ImageComponent imgTmp = new ImageComponent(generateMenuInfoFromServico(svc));
                imagesComponents.add(imgTmp);
            }

//                playSound("pay");
        } catch (Exception err) {
            err.printStackTrace();
        }
        btn.setOnAction(e -> getCommanderExecutor().executeCommand("OPEN", "profissionais"));

    }

    private MenuInfo generateMenuInfoFromServico(Servico svc) {
        System.out.println("Criando menu a partir do servico: " + svc);
        MenuInfo menu = new MenuInfo();
        menu.setTitulo(svc.getTitulo());
        menu.setPreco(svc.getValor());
        menu.setId(String.valueOf(svc.getId()));
        menu.setPayLoad(svc);
        String img = "images/tesoura.png";
        if (svc.getTitulo().toLowerCase(Locale.ROOT).contains("barba")) {
            img = "images/barba.png";
        } else if (svc.getTitulo().toLowerCase(Locale.ROOT).contains("bigode")) {
            img = "images/bigode.png";
        } if (svc.getTitulo().toLowerCase(Locale.ROOT).contains("cabelo")) {
            img = "images/cabelo.png";
        }
        menu.setImagemNome(img);
        menu.setImageWidth(128);
        menu.setImageHeight(128);
        return menu;
    }

    @Override
    public void animate() {
        for (ImageComponent imgCmp : imagesComponents) {
            new BounceInLeft(imgCmp).play();
        }
    }
}
