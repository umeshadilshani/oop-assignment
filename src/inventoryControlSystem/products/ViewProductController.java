package inventoryControlSystem.products;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class ViewProductController {
    @FXML
    TableView tableView;

    @FXML
    TableColumn clmnProductId;

    @FXML
    TableColumn clmnProduct;

    @FXML
    TableColumn clmnCategoryId;

    @FXML
    TableColumn clmnCategory;

    @FXML
    TableColumn clmnPrice;

    @FXML
    TableColumn clmnQuantity;

    @FXML
    TableColumn clmnDescription;

    @FXML
    public void initialize(){
        clmnCategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_id"));
        clmnCategory.setCellValueFactory(new PropertyValueFactory<>("Category_name"));
        clmnProductId.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_price"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        clmnProduct.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        clmnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

        display();
    }


    @FXML
    public void display(){
        MongoDatabase database = DbConnection.connect();

        MongoCollection collection = database.getCollection("product");

        FindIterable<Document> findIterable = collection.find();

        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc: findIterable) {
            Product document = new Product();
            document.setCategory_name(doc.getString("Category_name"));
            document.setDescription(doc.getString("Description"));
            document.setCategory_id(doc.getInteger("Category_id"));
            document.setProduct_id(doc.getInteger("Product_id"));
            document.setProduct_name(doc.getString("Product_name"));
            document.setQuantity(doc.getInteger("Quantity"));
            document.setUnit_price(doc.getInteger("Unit_price"));
            mainList.add(document);
        }

        tableView.setItems(mainList);
    }
}

