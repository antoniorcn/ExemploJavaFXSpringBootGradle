module edu.curso {
    requires javafx.fxml;
    requires javafx.controls;
    requires spring.boot.starter;
    opens edu.curso to javafx.graphics;
    exports edu.curso;
}