package javafxbinarywsclient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.websocket.ClientEndpoint;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
// got up to here 16/05/2015
@ClientEndpoint
public class JavaFXBinaryWsClient extends Application {
      
    private Session session;
    private ImageView ImageView;
    private static final Logger LOGGER = Logger.getLogger(JavaFXBinaryWsClient.class.getName());
    
    private void connectToWebSocket()
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try
        {
            URI uri = URI.create("ws://localhost:8080/BinaryWebSocketServer/images");
            container.connectToServer(this, uri);
        }
        catch (DeploymentException | IOException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
  
    @Override
    public void start(final Stage primaryStage)
    {
        connectToWebSocket();

        Button btn = new Button();
        btn.setText("Send Image!");
        btn.setPrefSize(400, 27);

        imageView = new ImageView();
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        AnchorPane root = new AnchorPane();

        AnchorPane.setTopAnchor(btn, 0.0);
        AnchorPane.setLeftAnchor(btn, 0.0);
        AnchorPane.setRightAnchor(btn, 0.0);
        AnchorPane.setTopAnchor(imageView, 27.0);
        AnchorPane.setBottomAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setRightAnchor(imageView, 0.0);

        root.getChildren().add(btn);
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 427);

        primaryStage.setTitle("Image push!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
