package javafxapplication1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Congrats extends VBox{
    
    File file1 = new File("Score1.txt") ;
    FileReader fr = null ;
    BufferedReader reader = null ;
    
    public Congrats(String player , int point , int n){
        
        VBox vbox = new VBox() ;
        
        Button button = new Button("back") ;
        button.setTranslateX(530);
        button.setTranslateY(-200);
        button.setTextFill(Color.BLACK);
        button.setStyle("-Fx-background-color : DODGERBLUE");
        button.setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
        
        vbox.getChildren().add(button) ;
        
        button.setOnAction(e->{
            SinglePlayerMenu root = new SinglePlayerMenu(1) ;
            root.setOption(n);
            
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/ttt.jpg") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root) ;
            
            Control.getStage().setScene(new Scene(pane)) ;
            Control.getStage().setTitle("Two Player");
        });
        
        double d=0 ;
        
        Label label0 = new Label();
        
        if(n==1){
            label0.setText("YOU  WIN");
        }
        
        else{
            label0.setText(player + "  WIN");
        }
        
        Label label1 = new Label("GAME OVER");
        Label label2 = new Label("Congrats , You set a new Highest Score") ;
        Label label3 = new Label("" + player + "  :  " + point + "") ;
        label0.setTranslateY(-100);
        label1.setTranslateY(-100);
        label2.setTranslateY(-100);
        label3.setTranslateY(-100);
        
        vbox.getChildren().add(label3) ;
        
        label0.setFont(Font.font("Segoe UI Black", FontPosture.REGULAR, 60));
        label0.setTextFill(Color.CHARTREUSE);
        label1.setFont(Font.font("Fixedsys", FontPosture.REGULAR, 36));
        label1.setTextFill(Color.CORAL);
        label2.setFont(Font.font("Times new Roman", FontPosture.REGULAR, 40));
        label2.setTextFill(Color.BURLYWOOD) ;
        label3.setFont(Font.font("Times new Roman", FontPosture.REGULAR, 36));
        label3.setTextFill(Color.AQUA) ;
        label3.setLayoutX(500);
        label3.setLayoutY(100);
        
        if(point>40){
            vbox.getChildren().add(label0) ;
        }
        
        try{
            reader = new BufferedReader(new FileReader(file1));
            d = Double.valueOf(reader.readLine()) ;
        }catch(Exception e){
            System.out.println("ExceptionIm : " + e);
        }
        
        if((int)d == point && point > 40 && n!=2){
            button.setTranslateY(-150);
            vbox.getChildren().add(label2) ;
        }
        
        System.out.println((int)d + "   "  + point);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(label1) ;
        vbox.setSpacing(50);
        setMinSize(1200, 800);
        setSpacing(100);
        setAlignment(Pos.CENTER);
        getChildren().addAll(vbox) ;
    }
}
