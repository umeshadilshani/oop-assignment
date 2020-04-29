package inventoryControlSystem.stocks;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.DbConnection;
import inventoryControlSystem.products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class ViewStockController {
    @FXML
    TableView tableView;

    @FXML
    TableColumn clmnProductId;

    @FXML
    TableColumn clmnProductName;

    @FXML
    TableColumn clmnQuantity;


    @FXML
    public void initialize(){
        clmnProductId.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        clmnProductName.setCellValueFactory(new PropertyValueFactory<>("Product_name"));


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
            document.setProduct_id(doc.getInteger("Product_id"));
            document.setProduct_name(doc.getString("Product_name"));
            document.setQuantity(doc.getInteger("Quantity"));

            mainList.add(document);
        }

        tableView.setItems(mainList);
    }
}



