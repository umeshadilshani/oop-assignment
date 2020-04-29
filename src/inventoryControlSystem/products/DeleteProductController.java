package inventoryControlSystem.products;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.bson.Document;

public class DeleteProductController {
    @FXML
    TextField txtProductId;

    @FXML
    TextField txtProductName;

    @FXML
    Button btnDelete;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Product_id");
        check.getItems().add("Product_name");
        check.setValue("Product_id");

    }

    public void delete() {
        String select = check.getValue();
        String product_id = txtProductId.getText();
        String product_name = txtProductName.getText();

        if (product_id.isEmpty() && product_name.isEmpty()) {
            AlertBox.required();
        } else if (!product_id.isEmpty() && !product_name.isEmpty()) {
            AlertBox.incorrect();
            txtProductId.setText("");
            txtProductName.setText("");
        } else if (select.contentEquals("Product_name")) {
            if (!product_name.isEmpty()) {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found1 = (Document) collection.find(new Document("Product_name", product_name)).first();

                if (found1 != null) {
                    Document document = new Document();
                    document.put("Product_name", product_name);

                    collection.deleteMany(document);

                    txtProductName.setText("");
                    txtProductId.setText("");

                    AlertBox.updated();
                } else {
                    txtProductId.setText("");
                    txtProductName.setText("");
                    AlertBox.notFound();
                }
            } else {
                AlertBox.required();
            }
        } else if (select.contentEquals("Product_id")) {
            if (!product_id.isEmpty()) {
                try {
                    int id = Integer.parseInt(product_id);
                } catch (NumberFormatException e) {
                    txtProductId.setText("");
                    txtProductName.setText("");
                    AlertBox.type();
                }
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found2 = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();

                if (found2 != null) {
                    Document document = new Document();
                    document.put("Product_id", Integer.parseInt(product_id));

                    collection.deleteMany(document);

                    txtProductName.setText("");
                    txtProductId.setText("");

                    AlertBox.updated();
                } else {
                    txtProductName.setText("");
                    txtProductId.setText("");
                    AlertBox.notFound();
                }
            } else {
                AlertBox.required();
            }
        }
    }
}
