package inventoryControlSystem.categories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;

import inventoryControlSystem.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;

public class AddCategoryController {
    @FXML
    Button btnAdd;

    @FXML
    TextField txtCategoryName;

    @FXML
    TextField txtCategoryId;

    @FXML
    TextField txtDescription;

    @FXML
    public void addCategory() {
        String category_id = txtCategoryId.getText();
        String category_name = txtCategoryName.getText();
        String description = txtDescription.getText();
        if (category_id.isEmpty() || category_name.isEmpty() || description.isEmpty()) {
            AlertBox.required();
        } else {
            try {
                int id = Integer.parseInt(category_id);
            } catch (NumberFormatException e) {
                txtCategoryId.setText("");
                txtCategoryName.setText("");
                txtDescription.setText("");
                AlertBox.type();
            }
            MongoDatabase database = DbConnection.connect();
            MongoCollection collection = database.getCollection("category");

            Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();
            Document found1 = (Document) collection.find(new Document("Category_name", category_name)).first();

            if (found == null && found1 == null){
                Document document = new Document();
                document.put("Category_id", Integer.parseInt(category_id));
                document.put("Category_name", txtCategoryName.getText());
                document.put("Description", txtDescription.getText());

                collection.insertOne(document);

                txtCategoryId.setText("");
                txtCategoryName.setText("");
                txtDescription.setText("");

                AlertBox.updated();
            }else{
                txtCategoryId.setText("");
                txtCategoryName.setText("");
                txtDescription.setText("");
                AlertBox.exist();
            }

        }
    }
}
