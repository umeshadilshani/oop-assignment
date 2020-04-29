package inventoryControlSystem.products;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import inventoryControlSystem.AlertBox;
import inventoryControlSystem.DbConnection;
import inventoryControlSystem.categories.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class AddProductController {
    @FXML
    TextField txtProductId;

    @FXML
    TextField txtProductName;

    @FXML
    TextField txtDescription;

    @FXML
    TextField txtPrice;

    @FXML
    TextField txtCategoryId;

    @FXML
    TextField txtQuantity;

    @FXML
    Button btnAdd;

    @FXML
    Button btnFill;

    @FXML
    ComboBox<String> find;

    @FXML
    public void initialize() {
        find.setEditable(true);

        MongoDatabase database = DbConnection.connect();
        MongoCollection collection = database.getCollection("category");

        FindIterable<Document> findIterable = collection.find();

        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc : findIterable) {
            Category document = new Category();
            document.setCategory_name(doc.getString("Category_name"));
            mainList.add(doc.getString("Category_name"));
        }
        find.setItems(mainList);
    }

    @FXML
    public void fill() {
        String category = find.getValue();

        MongoDatabase database = DbConnection.connect();
        MongoCollection collection = database.getCollection("category");

        Document found1 = (Document) collection.find(new Document("Category_name", category)).first();

        if (found1 != null) {
            FindIterable<Document> findIterable = collection.find(found1);

            for (Document doc : findIterable) {
                Category document = new Category();
                document.setCategory_id(doc.getInteger("Category_id"));

                txtCategoryId.setText(Integer.toString(doc.getInteger("Category_id")));
            }
        } else {
            txtCategoryId.setText("Not found please fill.");
        }
    }

    @FXML
    public void add() {
        String category = find.getValue();
        String category_id = txtCategoryId.getText();
        String product_name = txtProductName.getText();
        String product_id = txtProductId.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();

        if (category == null || category_id.isEmpty() || product_name.isEmpty() || product_id.isEmpty() || description.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            AlertBox.required();
        } else {
            try {
                int id = Integer.parseInt(category_id);
                int id1 = Integer.parseInt(product_id);
                int id2 = Integer.parseInt(price);
                int id3 = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                AlertBox.type();
            }
            MongoDatabase database = DbConnection.connect();
            MongoCollection collection = database.getCollection("category");

            Document found1 = (Document) collection.find(new Document("Category_name", category)).first();

            if (found1 != null) {
                FindIterable<Document> findIterable = collection.find(found1);

                for (Document doc : findIterable) {
                    Category document = new Category();
                    document.setCategory_id(doc.getInteger("Category_id"));

                    Integer n = doc.getInteger("Category_id");
                    if (n == Integer.parseInt(category_id)) {
                        MongoDatabase database1 = DbConnection.connect();
                        MongoCollection collection1 = database1.getCollection("product");

                        Document found2 = (Document) collection1.find(new Document("Product_id", Integer.parseInt(product_id))).first();
                        Document found3 = (Document) collection1.find(new Document("Product_name", product_name)).first();

                        if (found2 == null && found3 == null) {
                            Document document1 = new Document();
                            document1.put("Category_id", Integer.parseInt(category_id));
                            document1.put("Category_name", category);
                            document1.put("Product_id", Integer.parseInt(product_id));
                            document1.put("Quantity", Integer.parseInt(quantity));
                            document1.put("Product_name", txtProductName.getText());
                            document1.put("Unit_price", Integer.parseInt(price));
                            document1.put("Description", txtDescription.getText());

                            collection1.insertOne(document1);

                            AlertBox.updated();
                        } else {
                            AlertBox.exist();
                        }
                    } else {
                        AlertBox.notFound();
                    }
                }
            }if (found1 == null){
                Document found4 = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();

                if (found4 !=null){
                    AlertBox.exist();
                }else{
                    MongoDatabase database1 = DbConnection.connect();
                    MongoCollection collection1 = database1.getCollection("product");
                    Document document = new Document();
                    document.put("Category_id", Integer.parseInt(category_id));
                    document.put("Category_name", category);
                    document.put("Product_id", Integer.parseInt(product_id));
                    document.put("Quantity", Integer.parseInt(quantity));
                    document.put("Product_name", txtProductName.getText());
                    document.put("Unit_price", Integer.parseInt(price));
                    document.put("Description", txtDescription.getText());

                    Document catdocument = new Document();
                    catdocument.put("Category_id", Integer.parseInt(category_id));
                    catdocument.put("Category_name", category);

                    collection.insertOne(catdocument);
                    collection1.insertOne(document);

                    AlertBox.updated();
                }
            }
        }
    }
}

//            MongoDatabase database = DbConnection.connect();
//            MongoCollection collection = database.getCollection("product");
//
//            Document found = (Document) collection.find(new Document("Product_id", Integer.parseInt(product_id))).first();
//            Document found1 = (Document) collection.find(new Document("Product_name", product_name)).first();
//
//            if (found == null && found1 == null) {
//                Document document = new Document();
//                document.put("Category_id", Integer.parseInt(category_id));
//                document.put("Category_name", category);
//                document.put("Product_id", Integer.parseInt(product_id));
//                document.put("Product_name", txtProductName.getText());
//                document.put("Unit_price", Integer.parseInt(price));
//                document.put("Description", txtDescription.getText());
//
//
//                collection.insertOne(document);
//
//                AlertBox.updated();
//            } else {
//                AlertBox.exist();

