package inventoryControlSystem.stocks;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import inventoryControlSystem.products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class SearchStockController {
    @FXML
    TableView tableView;

    @FXML
    TextField txtSearch;

    @FXML
    Button btnSearch;

    @FXML
    TableColumn clmnProductId;

    @FXML
    TableColumn clmnProductName;

    @FXML
    TableColumn clmnQuantity;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Search by Product Id");
        check.getItems().add("Search by Product Name");
        check.setValue("Search by Product Id");

        clmnProductId.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        clmnProductName.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

    }

    @FXML
    public void display() {
        String select = check.getValue();
        String find = txtSearch.getText();

        if (find.isEmpty()) {
            AlertBox.required();
        }
        if (select.contentEquals("Search by Product Id")) {
            try {
                Integer id = Integer.parseInt(find);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }
            MongoDatabase database = DbConnection.connect();
            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(find))).first();

            if (found != null) {
                FindIterable<Document> findIterable = collection.find(found);

                ObservableList mainList = FXCollections.observableArrayList();

                for (Document doc : findIterable) {
                    Product document = new Product();
                    document.setProduct_name(doc.getString("Product_name"));
                    document.setProduct_id(doc.getInteger("Product_id"));
                    document.setQuantity(doc.getInteger("Quantity"));
                    mainList.add(document);
                }

                tableView.setItems(mainList);
            } else {
                AlertBox.notFound();
            }

        } else if (select.contentEquals("Search by Product Name")) {
            MongoDatabase database = DbConnection.connect();
            MongoCollection collection = database.getCollection("product");

            Document found = (Document) collection.find(new Document("Product_name", find)).first();

            if (found != null) {
                FindIterable<Document> findIterable = collection.find(found);

                ObservableList mainList = FXCollections.observableArrayList();

                for (Document doc : findIterable) {
                    Product document = new Product();
                    document.setProduct_name(doc.getString("Product_name"));
                    document.setProduct_id(doc.getInteger("Product_id"));
                    document.setQuantity(doc.getInteger("Quantity"));
                    mainList.add(document);
                }

                tableView.setItems(mainList);
            } else {
                AlertBox.notFound();
            }
        }
    }
}



