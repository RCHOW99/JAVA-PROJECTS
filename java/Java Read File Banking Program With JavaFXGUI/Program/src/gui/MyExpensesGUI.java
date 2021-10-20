package gui;

import expenses.MyExpenses;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyExpensesGUI extends MyExpenses{
    public static void main(String[] args) throws IOException {
        MyExpenses main = new MyExpenses();
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Raad");
        stage.setHeight(300);
        stage.setWidth(740);
        Rectangle r = new Rectangle();
        r.setHeight(200);
        r.setWidth(340);
        r.setY(30);
       r.setX(15);
        r.setStroke(Color.BLACK);
        r.setFill(Color.WHITE);
       Button b = new Button("Print Expenses & Payment");
        Button b2 = new Button("Read File");
        Button b3 = new Button("Print All Expenses & Payments");
        Button b4 = new Button("Print Total Expense");
        Button b5 = new Button("Print Total Balance");
        b.setLayoutY(90);
        b.setLayoutX(190);
        b2.setLayoutY(55);
        b2.setLayoutX(190);
        b2.setMinWidth(155);
        b3.setLayoutY(122.5);
        b3.setLayoutX(90);
        b3.setMinWidth(155);
        b4.setLayoutY(162.5);
        b4.setLayoutX(90);
        b4.setMinWidth(178);
        b5.setLayoutY(202.5);
        b5.setLayoutX(90);
        b5.setMinWidth(180);
        TextField tf = new TextField();
        tf.setLayoutY(90);
        tf.setLayoutX(20);
        TextField tf2 = new TextField();
        tf2.setLayoutY(55);
        tf2.setLayoutX(20);
        TextArea TA = new TextArea();
        TA.setEditable(false);
        TA.setMinHeight(200);
        TA.setMaxWidth(340);
        TA.setLayoutY(30);
        TA.setLayoutX(370);
        b.setOnAction(e -> {
            String ERF = tf.getText();
            TA.setText(interact(ERF));
            if (ERF.equals("exit") || ERF.equals("Exit")|| ERF.equals("EXIT")) {
                System.exit(0);
            }
        });
        b2.setOnAction(e -> {
            String FN = tf2.getText();
            try {
                TA.setText(ReadAndInit(FN));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        if(FN.equals("exit") || FN.equals("Exit")|| FN.equals("EXIT")){
           System.exit(0);
        }
        });
        b3.setOnAction(e -> TA.setText(Print()));
        b4.setOnAction(e -> TA.setText(PrintTotalExpenses()));
        b5.setOnAction(e -> TA.setText(PrintTotalBalance()));
        Pane p = new Pane();
        p.getChildren().addAll(r,b,b2,b3,b4,b5,tf,tf2,TA);
        Scene scene = new Scene(p);
        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
