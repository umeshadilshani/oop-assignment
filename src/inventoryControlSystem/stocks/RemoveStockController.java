package inventoryControlSystem.stocks;

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

public class RemoveStockController {
    @FXML
    Button btnRemove;

    @FXML
    TextField txtProduct;

    @FXML
    TextField txtRemoveStock;

    @FXML
    ChoiceBox<String> check;


    @FXML
    public void initialize() {
        check.getItems().add("Remove Stock by Product Id");
        check.getItems().add("Remove Stock by Product Name");
        check.setValue("Remove Stock by Product Id");
    }

    @FXML
    public void removeStock() {
        String select = check.getValue();
        String product = txtProduct.getText();
        String remove_stock = txtRemoveStock.getText();

        if (product.isEmpty() || remove_stock.isEmpty()) {
            AlertBox.required();
        } else if (select.contentEquals("Remove Stock by Product Id")) {
            try {
                int id = Integer.parseInt(product);
                int id1 = Integer.parseInt(remove_stock);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }

            MongoDatabase database = DbConnection.connect();

            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(product))).first();

            if (found != null) {
                Integer quantity = found.getInteger("Quantity");
                Integer remove = Integer.parseInt(remove_stock);
                Integer currentquantity = quantity - remove;

                BasicDBObject find = new BasicDBObject();
                find.put("Product_id", Integer.parseInt(product));
                BasicDBObject update = new BasicDBObject();
                update.put("$set", new BasicDBObject("Quantity", currentquantity));

                collection.updateOne(find, update);

                AlertBox.updated();

            } else {
                AlertBox.notFound();
            }
        } else if (select.contentEquals("Remove Stock by Product Name")) {
            try {
                int id1 = Integer.parseInt(remove_stock);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }

            MongoDatabase database = DbConnection.connect();

            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_name", product)).first();

            if (found != null) {
                Integer quantity = found.getInteger("Quantity");
                Integer remove = Integer.parseInt(remove_stock);
                Integer currentquantity = quantity - remove;

                BasicDBObject find = new BasicDBObject();
                find.put("Product_name", product);
                BasicDBObject update = new BasicDBObject();
                update.put("$set", new BasicDBObject("Quantity", currentquantity));

                collection.updateOne(find, update);

                AlertBox.updated();

            } else {
                AlertBox.notFound();
            }
        }
    }
}


