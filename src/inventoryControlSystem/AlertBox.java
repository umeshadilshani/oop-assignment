package inventoryControlSystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void required() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Some Fields are required.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void incorrect() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Credentials are incorrect!.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void exist() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Already Exist.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void updated() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Updated successfully.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void type() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Integer required.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void notFound() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText("Couldn't Found.");
        label.setTextFill(Paint.valueOf("#f2000c"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
