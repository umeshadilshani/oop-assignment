package inventoryControlSystem.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomePageController {
    @FXML
    MenuItem menuItemAddCategory;

    @FXML
    MenuItem menuItemDeleteCategory;

    @FXML
    MenuItem menuItemEditCategory;

    @FXML
    public void addCategory()throws Exception{
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/addCategory.fxml"));
        viewStage.setTitle("Add Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void deleteCategory()throws Exception{
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/deleteCategory.fxml"));
        viewStage.setTitle("Delete Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void editCategory()throws Exception{
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/editCategory.fxml"));
        viewStage.setTitle("Edit Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
}
