package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<GraphLayout> layouts = new ArrayList<>();
    private BorderPane borderPane = new BorderPane();
    Label info;
    private int currentIndex;

    @Override
    public void start(Stage primaryStage) throws Exception {


        info = new Label
                ("default text");
        info.setFont(Font.font("segio ui", FontWeight.BOLD, FontPosture.REGULAR, 14));
        info.setPadding(new Insets(10, 10, 40, 10));


        initializeList(layouts);


        System.out.println("Pane is not null");
        info.setText(layouts.get(0).getTitle());

        HBox paneForButtons = new HBox(50);
        ImageView leftImg = new ImageView("image/previous.png");
        leftImg.setFitHeight(25);
        leftImg.setFitWidth(25);
        ImageView rightImg = new ImageView("image/next.png");
        rightImg.setFitHeight(25);
        rightImg.setFitWidth(25);
        Button btLeft = new Button("",leftImg);
        Button btRight = new Button("",rightImg);

        paneForButtons.getChildren().addAll(btLeft, btRight);
        paneForButtons.setAlignment(Pos.CENTER);

        btLeft.setOnAction(e -> handlerLeft(e));
        btRight.setOnAction(e -> handlerRight(e));
        borderPane.setTop(info);
        borderPane.setCenter(layouts.get(0));
        borderPane.setBottom(paneForButtons);


        borderPane.setPadding(new Insets(20, 100, 10, 100));
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Computer Graphics");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    private void handlerLeft(ActionEvent e) {
        currentIndex = (currentIndex <= 0) ? layouts.size() - 1 : currentIndex - 1;
        info.setText(layouts.get(currentIndex).getTitle());
        borderPane.setCenter(layouts.get(currentIndex));
    }

    private void handlerRight(ActionEvent e) {
        currentIndex = (currentIndex >= layouts.size() - 1) ? 0 : currentIndex + 1;
        info.setText(layouts.get(currentIndex).getTitle());
        borderPane.setCenter(layouts.get(currentIndex));
    }

    private void initializeList(ArrayList<GraphLayout> layouts) {

        layouts.add(LineAlgorithms.drawHorizontalLine(0, 8, 4));
        layouts.add(LineAlgorithms.drawVerticalLine(4,0,8));
        layouts.add(LineAlgorithms.drawDiagonalLineWithUnitPositiveTangent(2,2,7,7));
        layouts.add(LineAlgorithms.drawDiagonalLineWithNagativeUnitTangent(2,7,7,2));
        layouts.add(LineAlgorithms.directLine(1,1,3,6));
        layouts.add(LineAlgorithms.simpleDDA(2, 2, 6, 7));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
