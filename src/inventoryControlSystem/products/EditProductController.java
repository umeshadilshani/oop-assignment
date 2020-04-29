package inventoryControlSystem.products;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.bson.Document;

public class EditProductController {
    @FXML
    TextField txtProductId;

    @FXML
    TextField txtNewProductName;

    @FXML
    TextField txtDescription;

    @FXML
    TextField txtPrice;

    @FXML
    Button btnEdit;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Edit Product Name");
        check.getItems().add("Edit Description");
        check.getItems().add("Edit Price");
        check.getItems().add("Edit All");
        check.setValue("Edit Product Name");

    }

    public void edit() {
        String select = check.getValue();
        String product_id = txtProductId.getText();
        String new_product_name = txtNewProductName.getText();
        String new_description = txtDescription.getText();
        String new_price = txtPrice.getText();

        if (product_id.isEmpty()) {
            AlertBox.required();
        } else {
            try {
                int id = Integer.parseInt(product_id);
            } catch (NumberFormatException e) {
                txtProductId.setText("");
                txtNewProductName.setText("");
                txtDescription.setText("");
                txtPrice.setText("");
                AlertBox.type();
            }
        }
        if (select.contentEquals("Edit All")) {
            if (new_product_name.isEmpty() || new_description.isEmpty() || new_price.isEmpty()) {
                AlertBox.required();
            } else {
                try {
                    int id = Integer.parseInt(new_price);
                } catch (NumberFormatException e) {
                    AlertBox.type();
                }
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found1 = (Document) collection.find(new Document("Product_name", new_product_name)).first();
                Document found2 = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();

                if (found1 != null) {
                    AlertBox.exist();
                } else if (found2 == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Product_id", Integer.parseInt(product_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Product_name", new_product_name).append("Description", new_description).append("Unit_price", Integer.parseInt(new_price)));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        } else if (select.contentEquals("Edit Product Name")) {
            if (!new_description.isEmpty() || !new_price.isEmpty()) {
                AlertBox.incorrect();
            } else {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found1 = (Document) collection.find(new Document("Product_name", new_product_name)).first();
                Document found2 = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();

                if (found1 != null) {
                    AlertBox.exist();
                } else if (found2 == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Product_id", Integer.parseInt(product_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Product_name", new_product_name));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        } else if (select.contentEquals("Edit Description")) {
            if (!new_product_name.isEmpty() || !new_price.isEmpty()) {
                AlertBox.incorrect();
            } else {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();

                if (found == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Product_id", Integer.parseInt(product_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Description", new_description));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        } else if (select.contentEquals("Edit Price")) {
            if (!new_product_name.isEmpty() || !new_description.isEmpty()) {
                AlertBox.incorrect();
            } else {
                try {
                    int id = Integer.parseInt(new_price);
                } catch (NumberFormatException e) {
                    AlertBox.type();
                }
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("product");

                Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();

                if (found == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Product_id", Integer.parseInt(product_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Unit_price", Integer.parseInt(new_price)));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        }
    }
}