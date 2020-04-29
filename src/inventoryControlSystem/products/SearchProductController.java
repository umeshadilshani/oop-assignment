package inventoryControlSystem.products;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class SearchProductController {
    @FXML
    TableView tableView;

    @FXML
    TextField txtSearch;

    @FXML
    Button btnSearch;

    @FXML
    TableColumn clmnCategoryId;

    @FXML
    TableColumn clmnCategoryName;

    @FXML
    TableColumn clmnPrice;

    @FXML
    TableColumn clmnQuantity;

    @FXML
    TableColumn clmnDescription;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Search by Product Id");
        check.getItems().add("Search by Product Name");
        check.setValue("Search by Product Id");

        clmnCategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_id"));
        clmnCategoryName.setCellValueFactory(new PropertyValueFactory<>("Category_name"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_price"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        clmnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

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
                    document.setCategory_name(doc.getString("Category_name"));
                    document.setDescription(doc.getString("Description"));
                    document.setCategory_id(doc.getInteger("Category_id"));
                    document.setQuantity(doc.getInteger("Quantity"));
                    document.setUnit_price(doc.getInteger("Unit_price"));
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
                    document.setCategory_name(doc.getString("Category_name"));
                    document.setDescription(doc.getString("Description"));
                    document.setCategory_id(doc.getInteger("Category_id"));
                    document.setQuantity(doc.getInteger("Quantity"));
                    document.setUnit_price(doc.getInteger("Unit_price"));
                    mainList.add(document);
                }

                tableView.setItems(mainList);
            } else {
                AlertBox.notFound();
            }
        }
    }
}

