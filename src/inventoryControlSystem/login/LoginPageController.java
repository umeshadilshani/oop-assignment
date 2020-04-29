package inventoryControlSystem.login;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

public class LoginPageController {
    @FXML
    TextField txtUsername;

    @FXML
    PasswordField txtPassword;

    @FXML
    Button btnLogIn;

    @FXML
    public void logIn(ActionEvent actionEvent) throws Exception {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.equals("") || password.equals("")) {
            AlertBox.required();
        } else {
            MongoDatabase database = DbConnection.connect();
            MongoCollection collection = database.getCollection("login");

            Document found1 = (Document) collection.find(new Document("Username", username)).first();
            Document found2 = (Document) collection.find(new Document("Password", password)).first();

            if(found1 != null && found2 !=null){
                    Stage viewStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../home/homePage.fxml"));
                    viewStage.setTitle("Home Page");
                    viewStage.setScene(new Scene(root, 600, 400));
                    viewStage.show();

                    Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    previousStage.close();
                }else{
                    AlertBox.incorrect();
                }
            }
        }
    }

