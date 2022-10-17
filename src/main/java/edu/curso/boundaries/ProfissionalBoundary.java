package edu.curso.boundaries;

import animatefx.animation.BounceInLeft;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.curso.componentes.ImageComponent;
import edu.curso.componentes.LabelSuperTitulo;
import edu.curso.componentes.SimpleImageComponent;
import edu.curso.componentes.SuperButton;
import edu.curso.models.MenuInfo;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfissionalBoundary extends BoundaryRenderGeneric  {

    private SuperButton btn = new SuperButton("Confirmar", SuperButton.BUTTON_TYPE.OK);;
    private List<SimpleImageComponent> imagesComponents = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> SOUNDS = Map.of(
            "profession", "profession.mp3",
            "pay", "pay.mp3",
            "prepare", "prepare.mp3",
            "sparta", "sparta.mp3");

    @Override
    public Pane render() {
        BorderPane panel = new BorderPane();
        VBox box = new VBox();
        panel.setStyle("-fx-padding: 10px");
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        panel.setTop(new LabelSuperTitulo("Escolha o profissional"));
        for (SimpleImageComponent imgCmp : imagesComponents) {
            box.getChildren().add(imgCmp);
        }

        ScrollPane scroll = new ScrollPane(box);
        scroll.setPannable(true);
        panel.setCenter(scroll);
        panel.setBottom(btn);
        return panel;
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
            File file = new ClassPathResource("config/profissionais.json").getFile();
            FileInputStream fileIs = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileIs, "UTF-8"));
            String json = bufferedReader.lines().collect(Collectors.joining("\n"));
            // below line is for conversion
            MenuInfo[] menuTemp = objectMapper.readValue(json, MenuInfo[].class);
            imagesComponents.clear();
            for (MenuInfo mnu : menuTemp) {

                SimpleImageComponent imgTmp = new SimpleImageComponent(mnu);
                imagesComponents.add(imgTmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn.setOnAction( e -> getCommanderExecutor().executeCommand("OPEN", "calendar") );
//        imgCabelo.setOnMouseClicked( e-> {
//            System.out.println("Clicado no Cabelo");
//            HttpClient httpClient = HttpClient.newBuilder()
//                    .version(HttpClient.Version.HTTP_2)
//                    .build();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .GET()
//                    .uri(URI.create("http://localhost:8080/retrieve_agenda"))
//                    .build();
//            try {
//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                System.out.println("Resposta recebida: " + response.body());
//                playSound("pay");
//            } catch (Exception err) {
//                err.printStackTrace();
//            }
//
//        });
//
//        imgBigode.setOnMouseClicked( e -> {
//            playSound("sparta");
//        });
//
//        imgBarba.setOnMouseClicked( e -> {
//            playSound("prepare");
//        });
    }

    @Override
    public void animate() {
        for (SimpleImageComponent imgCmp : imagesComponents) {
            new BounceInLeft(imgCmp).play();
        }
    }

}
