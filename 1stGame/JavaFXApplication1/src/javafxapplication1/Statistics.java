package javafxapplication1;

import java.io.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Statistics extends VBox {
    
    File file1 = new File("Score1.txt") ;
    File file2 = new File("Score2.txt") ;
    FileReader fr = null ;
    BufferedReader reader = null ;
    
    Label [] label = new Label[10] ;
    
    int count  = 0;
    
    public Statistics(){
    }
    
    public Statistics(int n){
        
        Button button = new Button("back") ;
        Label label1 = new Label("TOP 10"); 
        getChildren().add(label1) ;
       
        button.setOnAction(e->{
            SinglePlayerMenu root = new SinglePlayerMenu(1) ;
            root.setOption(1);
            
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/ttt.jpg") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root) ;
            
            Control.getStage().setScene(new Scene(pane)) ;
            Control.getStage().setTitle("Single Player");
            setAlignment(Pos.CENTER);
            setMinSize(1200, 820);
        });
        
        readScoreFile() ;
        button.setTranslateX(530);
        button.setTranslateY(-400 - count*25);
        button.setTextFill(Color.BLACK);
        button.setStyle("-Fx-background-color : DODGERBLUE");
        button.setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
        
        label1.setTextFill(Color.WHITE);
        label1.setStyle("-Fx-background-color : PURPLE");
        label1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 36));
        label1.setMinSize(400, 70);
        label1.setTranslateY(-300 + count*25);
        label1.setAlignment(Pos.CENTER);
        
        getChildren().add(button) ;
        setAlignment(Pos.CENTER);
        setMinSize(1200, 820);
    }
    
    public Statistics(int n ,int m){
        
        Button button = new Button("back") ;
        Label label2 = new Label("Last 10 Game") ;
        getChildren().add(label2) ;
       
        button.setOnAction(e->{
            SinglePlayerMenu root = new SinglePlayerMenu(1) ;
            root.setOption(2);
            
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/ttt.jpg") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root) ;
            
            Control.getStage().setScene(new Scene(pane)) ;
            Control.getStage().setTitle("Two Player");
        });
        readStatistics() ;
        button.setTranslateX(530);
        button.setTranslateY(-380 - count*25);
        button.setTextFill(Color.BLACK);
        button.setStyle("-Fx-background-color : DODGERBLUE");
        button.setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
        
        label2.setTextFill(Color.WHITE);
        label2.setStyle("-Fx-background-color : PURPLE");
        label2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 36));
        label2.setMinSize(400, 70);
        label2.setTranslateY(-300 + count*25);
        label2.setAlignment(Pos.CENTER);
        
        getChildren().add(button) ;
        setAlignment(Pos.CENTER);
        setMinSize(1200, 820);
    }
    
    public void readScoreFile(){
        
        try{
            reader = new BufferedReader(new FileReader(file1)) ;
            String str ;
            while((str=reader.readLine())!=null){
                double d = Double.valueOf(str) ;
                String s = reader.readLine() ;
                label[count] = new Label("   " + (count+1) +  ".   " +  s + "    :    " + (int)d+ "      " );
                label[count].setTextFill(Color.AQUA);
                
                count++ ;
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Exception and : " + e);
        }
        
        for(int i=0 ; i<count ; i++){
            label[i].setMinSize(300, 50);
            
            label[i].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 20));
            
            getChildren().add(label[i]) ;
        }
        
    }
    
    public void readStatistics(){
        try{
            reader = new BufferedReader(new FileReader(file2)) ;
            String str ;
            while((str=reader.readLine())!=null ){
                double d = Double.valueOf(str) ;
                String s = reader.readLine() ;
                double d1 = Double.valueOf(reader.readLine()) ;
                String s1 = reader.readLine() ;
                
                label[count] = new Label("   " + (count+1) +  ". " +  s + "  :  " + (int)d + "     vs     " + s1 + "  :  " + (int)d1+ "      ") ;
                label[count].setTextFill(Color.AQUA);
                count++ ;
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Exception and : " + e);
        }
        
        for(int i=0 ; i<count ; i++){
            label[i].setMinSize(300, 50);
            label[i].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 20));
            //setSpacing(50);
            
            getChildren().add(label[i]) ;
        }
    }
}
