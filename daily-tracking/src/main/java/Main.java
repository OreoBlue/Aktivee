import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/java/view/KegiatanView.fxml").toURI().toURL();
        Scene scene = new Scene(FXMLLoader.load(url));

        stage.setTitle("Aktivee");
        stage.setScene(scene);
        stage.show();
    }
}
