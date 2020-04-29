package inventoryControlSystem.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    MenuItem menuItemViewCategory;

    @FXML
    MenuItem menuItemAddProduct;

    @FXML
    MenuItem menuItemDeleteProduct;

    @FXML
    MenuItem menuItemEditProduct;

    @FXML
    MenuItem menuItemSearchProduct;

    @FXML
    MenuItem menuItemViewProduct;

    @FXML
    MenuItem menuItemAddStocks;

    @FXML
    MenuItem menuItemRemoveStocks;

    @FXML
    MenuItem menuItemSearchStocks;

    @FXML
    MenuItem menuItemViewStocks;

    @FXML
    Button btnLogOut;

    @FXML
    public void addCategory() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/addCategory.fxml"));
        viewStage.setTitle("Add Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }

    @FXML
    public void deleteCategory() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/deleteCategory.fxml"));
        viewStage.setTitle("Delete Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }

    @FXML
    public void editCategory() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/editCategory.fxml"));
        viewStage.setTitle("Edit Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }

    @FXML
    public void viewCategory() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../categories/viewCategory.fxml"));
        viewStage.setTitle("View Category");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void addProduct() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../products/addProduct.fxml"));
        viewStage.setTitle("Add Product");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void deleteProduct() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../products/deleteProduct.fxml"));
        viewStage.setTitle("Delete Product ");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void editProduct() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../products/editProduct.fxml"));
        viewStage.setTitle("Edit Product ");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void searchProduct() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../products/searchProduct.fxml"));
        viewStage.setTitle("Search Product ");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void viewProduct() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../products/viewProduct.fxml"));
        viewStage.setTitle("View Product ");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void addStock() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../stocks/addStock.fxml"));
        viewStage.setTitle("Add Stock");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void removeStock() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../stocks/removeStock.fxml"));
        viewStage.setTitle("Remove Stock");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void searchStock() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../stocks/searchStock.fxml"));
        viewStage.setTitle("Search Stock");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void viewStock() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../stocks/viewStock.fxml"));
        viewStage.setTitle("View Stock");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
    @FXML
    public void back() throws Exception {
        Stage viewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../main/mainPage.fxml"));
        viewStage.setTitle("Main Page");
        viewStage.setScene(new Scene(root, 600, 400));
        viewStage.show();
    }
}
