/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Michal
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws KeyStoreException {
        Button btn = new Button();
        
        final KeyStore  ks = KeyStore.getInstance("JKS");  
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                
                    //                    String key = "f429ccceafc6b81a6ffad442cec758c3"; //this is the key used in the Last.fm API examples
//        String user = "myungpetrucci";
//        String pwd = "avs2004";
//        String secret = "8097fcb4a54a9805599060e47ab69561";
//        Session session = Authenticator.getMobileSession(user, pwd, key, secret);
//        int now = (int) (System.currentTimeMillis() / 1000);
//        Track.scrobble("Dream Theater", "Octavarium", now  , session);
//    Track.updateNowPlaying("Dream Theater", "Octavarium", session);
                try{
               
                String password = "password";
                char[] ksPass = password.toCharArray();
                ks.load(null, ksPass);
//                ks.setKeyEntry("keyForSeckeyDecrypt", privateKey, null, null);
//                ks.setKeyEntry("keyForDigitalSignature", priv, null, null);
                FileOutputStream writeStream = new FileOutputStream("key.store");
                ks.store(writeStream, ksPass);
                writeStream.close();
                }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e){}
            }
        });
        Button btn2 = new Button("Get Password");
        
        btn2.setOnAction((ActionEvent event) -> {
                try{
//                String password = "password";
                char[] ksPass = null;
//                ks.load(null, ksPass);
//                ks.setKeyEntry("keyForSeckeyDecrypt", privateKey, null, null);
//                ks.setKeyEntry("keyForDigitalSignature", priv, null, null);
                    FileInputStream writeStream = new FileInputStream("key.store");
                ks.load(writeStream, ksPass);
                    System.out.println(ksPass);
                writeStream.close();
                }catch(IOException | NoSuchAlgorithmException | CertificateException e){}
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}