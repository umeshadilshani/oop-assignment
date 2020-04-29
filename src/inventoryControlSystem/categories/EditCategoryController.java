package inventoryControlSystem.categories;

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

public class EditCategoryController {
    @FXML
    TextField txtCategoryId;

    @FXML
    TextField txtNewCategoryName;

    @FXML
    TextField txtDescription;

    @FXML
    Button btnEdit;

    @FXML
    ChoiceBox<String> check;

    @FXML
    public void initialize() {
        check.getItems().add("Edit Category Name");
        check.getItems().add("Edit Description");
        check.getItems().add("Edit Both");
        check.setValue("Edit Category Name");

    }

    public void edit() {
        String select = check.getValue();
        String category_id = txtCategoryId.getText();
        String new_category_name = txtNewCategoryName.getText();
        String new_description = txtDescription.getText();

        if (category_id.isEmpty()) {
            AlertBox.required();
        } else {
            try {
                int id = Integer.parseInt(category_id);
            } catch (NumberFormatException e) {
                txtCategoryId.setText("");
                txtNewCategoryName.setText("");
                txtDescription.setText("");
                AlertBox.type();
            }
        }
        if (select.contentEquals("Edit Both")) {
            if (new_category_name.isEmpty() || new_description.isEmpty()) {
                AlertBox.required();
            } else {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("category");

                Document found1 = (Document) collection.find(new Document("Category_name", new_category_name)).first();
                Document found2 = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();

                if (found1 != null) {
                    AlertBox.exist();
                } else if (found2 == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Category_id", Integer.parseInt(category_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Category_name", new_category_name).append("Description", new_description));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        } else if (select.contentEquals("Edit Category Name")) {
            if (!new_description.isEmpty()) {
                AlertBox.incorrect();
            } else {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("category");

                Document found1 = (Document) collection.find(new Document("Category_name", new_category_name)).first();
                Document found2 = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();

                if (found1 != null) {
                    AlertBox.exist();
                } else if (found2 == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Category_id", Integer.parseInt(category_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Category_name", new_category_name));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        } else if (select.contentEquals("Edit Description")) {
            if (!new_category_name.isEmpty()) {
                AlertBox.incorrect();
            } else {
                MongoDatabase database = DbConnection.connect();
                MongoCollection collection = database.getCollection("category");

                Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();

                if (found == null) {
                    AlertBox.notFound();
                } else {
                    BasicDBObject find = new BasicDBObject();
                    find.put("Category_id", Integer.parseInt(category_id));
                    BasicDBObject update = new BasicDBObject();
                    update.put("$set", new BasicDBObject("Description", new_description));

                    collection.updateOne(find, update);

                    AlertBox.updated();
                }
            }
        }
    }
}





























































































//        if (category_id.isEmpty()) {
//            txtNewCategoryName.setText("");
//            txtDescription.setText("");
//            txtCategoryId.setText("");
//            AlertBox.required();
//        } else if (!category_id.isEmpty()) {
//            try {
//                int id = Integer.parseInt(category_id);
//            } catch (NumberFormatException e) {
//                txtNewCategoryName.setText("");
//                txtDescription.setText("");
//                txtCategoryId.setText("");
//                AlertBox.type();
//            }
//        } else if (select.contentEquals("Edit Both")) {
//            if (new_description.isEmpty() || new_category_name.isEmpty()) {
//                txtDescription.setText("");
//                txtNewCategoryName.setText("");
//                txtCategoryId.setText("");
//                AlertBox.required();
//            } else {
//                MongoDatabase database = DbConnection.connect();
//                MongoCollection collection = database.getCollection("category");
//
//                Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();
//                Document found1 = (Document) collection.find(new Document("Category_name", new_category_name)).first();
//
//                if (found == null) {
//                    AlertBox.notFound();
//                } else if (found1 != null) {
//                    AlertBox.exist();
//                } else {
//                    BasicDBObject find = new BasicDBObject();
//                    find.put("Category_id", Integer.parseInt(category_id));
//                    BasicDBObject update = new BasicDBObject();
//                    update.put("$set", new BasicDBObject("Category_name", new_category_name).append("Description", txtDescription));
//
//                    collection.updateOne(find, update);
//
//                    txtCategoryId.setText("");
//                    txtDescription.setText("");
//                    txtNewCategoryName.setText("");
//
//                    AlertBox.updated();
//                }


//        else if (!category_id.isEmpty()) {
//            try {
//                int id = Integer.parseInt(category_id);
//            } catch (NumberFormatException e) {
//                txtCategoryId.setText("");
//                txtNewCategoryId.setText("");
//                txtNewCategoryName.setText("");
//                AlertBox.type();
//            }
//            if (new_category_id.isEmpty()) {
//                MongoDatabase database = DbConnection.connect();
//                MongoCollection collection = database.getCollection("category");
//
//                Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();
//                Document found1 = (Document) collection.find(new Document("Category_name", new_category_name)).first();
//
//
//                if (found == null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.notFound();
//                } else if (found1 != null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.exist();
//                } else {
//                    BasicDBObject find = new BasicDBObject();
//                    find.put("Category_id", Integer.parseInt(category_id));
//                    BasicDBObject update = new BasicDBObject();
//                    update.put("$set", new BasicDBObject("Category_name", new_category_name));
//
//                    collection.updateOne(find, update);
//
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//
//                    AlertBox.updated();
//                }
//            }
//            if (new_category_name.isEmpty()) {
//                try {
//                    int id = Integer.parseInt(new_category_id);
//                } catch (NumberFormatException e) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.type();
//                }
//                MongoDatabase database = DbConnection.connect();
//                MongoCollection collection = database.getCollection("category");
//
//                Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();
//                Document found1 = (Document) collection.find(new Document("Category_id", Integer.parseInt(new_category_id))).first();
//
//
//                if (found == null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.notFound();
//                } else if (found1 != null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.exist();
//                } else {
//                    BasicDBObject find = new BasicDBObject();
//                    find.put("Category_id", Integer.parseInt(category_id));
//                    BasicDBObject update = new BasicDBObject();
//                    update.put("$set", new BasicDBObject("Category_id", Integer.parseInt(new_category_id)));
//
//                    collection.updateOne(find, update);
//
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//
//                    AlertBox.updated();
//                }
//            }if (!category_id.isEmpty() && !new_category_id.isEmpty()){
//                try {
//                    int id = Integer.parseInt(new_category_id);
//                } catch (NumberFormatException e) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.type();
//                }
//                MongoDatabase database = DbConnection.connect();
//                MongoCollection collection = database.getCollection("category");
//
//                Document found = (Document) collection.find(new Document("Category_id", Integer.parseInt(category_id))).first();
//                Document found1 = (Document) collection.find(new Document("Category_id", Integer.parseInt(new_category_id))).first();
//                Document found2 = (Document) collection.find(new Document("Category_name", new_category_name)).first();
//
//
//                if (found == null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.notFound();
//                } else if (found1 != null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.exist();
//                }else if (found2 != null) {
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//                    AlertBox.exist();
//                } else {
//                    BasicDBObject find = new BasicDBObject();
//                    find.put("Category_id", Integer.parseInt(category_id));
//                    BasicDBObject update = new BasicDBObject();
//                    update.put("$set", new BasicDBObject("Category_id", Integer.parseInt(new_category_id)).append("Category_name",new_category_name));
//
//                    collection.updateOne(find, update);
//
//                    txtCategoryId.setText("");
//                    txtNewCategoryId.setText("");
//                    txtNewCategoryName.setText("");
//
//                    AlertBox.updated();



