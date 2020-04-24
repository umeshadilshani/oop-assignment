package inventoryControlSystem.categories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.bson.Document;

public class DeleteCategoryController {
    @FXML
    TextField txtCategoryId;

    @FXML
    TextField txtCategoryName;

    @FXML
    Button btnDelete;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Category_id");
        check.getItems().add("Category_name");
        check.setValue("Category_id");

    }

    public void delete() {
        String select = check.getValue();
        String category_id = txtCategoryId.getText();
        String category_name = txtCategoryName.getText();

        if (category_id.isEmpty() && category_name.isEmpty()) {
            AlertBox.required();
        } else if (!category_id.isEmpty() && !category_name.isEmpty()) {
            AlertBox.incorrect();
            txtCategoryId.setText("");
            txtCategoryName.setText("");
        } else if (select.contentEquals("Category_name")) {
            if (!category_name.isEmpty()) {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("category");

                Document found1 = (Document) collection.find(new Document("Category_name", category_name)).first();

                if (found1 != null) {
                    Document document = new Document();
                    document.put("Category_name", category_name);

                    collection.deleteMany(document);

                    txtCategoryId.setText("");
                    txtCategoryName.setText("");

                    AlertBox.updated();
                } else {
                    txtCategoryId.setText("");
                    txtCategoryName.setText("");
                    AlertBox.notFound();
                }
            } else {
                AlertBox.required();
            }
        } else if (select.contentEquals("Category_id")) {
            if (!category_id.isEmpty()) {
                try {
                    int id = Integer.parseInt(category_id);
                } catch (NumberFormatException e) {
                    txtCategoryId.setText("");
                    txtCategoryName.setText("");
                    AlertBox.type();
                }
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("category");

                Document found2 = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();

                if (found2 != null) {
                    Document document = new Document();
                    document.put("Category_id", Integer.parseInt(category_id));

                    collection.deleteMany(document);

                    txtCategoryId.setText("");
                    txtCategoryName.setText("");

                    AlertBox.updated();
                } else {
                    txtCategoryId.setText("");
                    txtCategoryName.setText("");
                    AlertBox.notFound();
                }
            } else {
                AlertBox.required();
            }
        }
    }
}



