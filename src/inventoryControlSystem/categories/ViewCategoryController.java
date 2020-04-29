package inventoryControlSystem.categories;

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

public class ViewCategoryController {
    @FXML
    TableView tableView;

    @FXML
    TableColumn clmnCategoryId;

    @FXML
    TableColumn clmnCategoryName;

    @FXML
    TableColumn clmnDescription;


    @FXML
    public void initialize(){
        clmnCategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_id"));
        clmnCategoryName.setCellValueFactory(new PropertyValueFactory<>("Category_name"));
        clmnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

        display();
    }


    @FXML
    public void display(){
        MongoDatabase database = DbConnection.connect();

        MongoCollection collection = database.getCollection("category");

        FindIterable<Document> findIterable = collection.find();

        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc: findIterable) {
            Category document = new Category();
            document.setCategory_name(doc.getString("Category_name"));
            document.setDescription(doc.getString("Description"));
            document.setCategory_id(doc.getInteger("Category_id"));
            mainList.add(document);
        }

        tableView.setItems(mainList);
    }
}


