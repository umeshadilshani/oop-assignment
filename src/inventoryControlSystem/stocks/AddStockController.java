package inventoryControlSystem.stocks;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.bson.Document;

public class AddStockController {
    @FXML
    Button btnAdd;

    @FXML
    TextField txtProduct;

    @FXML
    TextField txtAddStock;

    @FXML
    ChoiceBox<String> check;


    @FXML
    public void initialize() {
        check.getItems().add("Add Stock by Product Id");
        check.getItems().add("Add Stock by Product Name");
        check.setValue("Add Stock by Product Id");
    }

    @FXML
    public void addStock() {
        String select = check.getValue();
        String product = txtProduct.getText();
        String add_stock = txtAddStock.getText();

        if (product.isEmpty() || add_stock.isEmpty()) {
            AlertBox.required();
        } else if (select.contentEquals("Add Stock by Product Id")) {
            try {
                int id = Integer.parseInt(product);
                int id1 = Integer.parseInt(add_stock);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }

            MongoDatabase database = DbConnection.connect();

            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(product))).first();

            if (found != null) {
                Integer quantity = found.getInteger("Quantity");
                Integer add = Integer.parseInt(add_stock);
                Integer currentquantity = quantity + add;

                BasicDBObject find = new BasicDBObject();
                find.put("Product_id", Integer.parseInt(product));
                BasicDBObject update = new BasicDBObject();
                update.put("$set", new BasicDBObject("Quantity", currentquantity));

                collection.updateOne(find, update);

                AlertBox.updated();

            } else {
                AlertBox.notFound();
            }
        } else if (select.contentEquals("Add Stock by Product Name")) {
            try {
                int id1 = Integer.parseInt(add_stock);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }

            MongoDatabase database = DbConnection.connect();

            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_name", product)).first();

            if (found != null) {
                Integer quantity = found.getInteger("Quantity");
                Integer add = Integer.parseInt(add_stock);
                Integer currentquantity = quantity + add;

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
