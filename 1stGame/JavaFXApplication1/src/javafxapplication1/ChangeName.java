package javafxapplication1;

import java.io.* ;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ChangeName extends VBox{
    
    File file1 = new File("NameFile1.txt") ;
    File file2 = new File("NameFile2.txt") ;
    FileWriter fw = null ;
    BufferedWriter writer = null ;
    
    TextField [] plrName = new TextField[3] ;
    HBox [] hbox = new HBox[3] ;
    Button [] button = new Button[3] ;
    Label [] label = new Label[3] ;
    
    int option ;
    
    public ChangeName(){
    } 
    
    public ChangeName(int option){
        
        this.option = option ;
        managingUnit() ;
        if(option==1){
            singlePlayerChangeName() ;
        }
        
        if(option == 2){
            twoPlayerChangeName() ;
        } 
        
        getChildren().addAll(button[0],button[1]) ;
        setMinSize(1200,820);
        setAlignment(Pos.CENTER);
        
    }
    
    public void managingUnit(){
       
        button[0] = new Button("back") ;
        button[0].setTranslateX(530);
        if(option==1)button[0].setTranslateY(-360);
        else button[0].setTranslateY(-410);
        
        button[0].setTextFill(Color.BLACK);
        button[0].setStyle("-Fx-background-color : DODGERBLUE");
        button[0].setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
        
        button[0].setOnAction(e->{
            SinglePlayerMenu root = new SinglePlayerMenu(1) ;
            root.setOption(option);
            
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/ttt.jpg") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root) ;
            
            Control.getStage().setScene(new Scene(pane)) ;
        });
        
        button[1] = new Button("OK") ;
        button[1].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 28));
        button[1].setMinSize(100,50 );
        button[1].setTextFill(Color.GREENYELLOW);
        button[1].setStyle("-fx-background-color: Purple");
        
        plrName[0] = new TextField("Sujon") ;
        plrName[0].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        plrName[0].setAlignment(Pos.CENTER);
        plrName[0].setMinSize(400, 50);
        plrName[1] = new TextField("Jannat") ;
        plrName[1].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        plrName[1].setAlignment(Pos.CENTER);
        plrName[1].setMinSize(400, 50);
        plrName[2] = new TextField("Rabiul") ;
        plrName[2].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        plrName[2].setAlignment(Pos.CENTER);
        plrName[2].setMinSize(400, 50);
        
        label[0] = new Label("Player 1 : ") ;
        label[0].setMinSize(100, 50);
        label[0].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        label[0].setTextFill(Color.ANTIQUEWHITE);
        label[1] = new Label("Player 1 : ") ;
        label[1].setMinSize(100, 50);
        label[1].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        label[1].setTextFill(Color.ANTIQUEWHITE);
        label[2] = new Label("Player 2 : ") ;
        label[2].setMinSize(100, 50);
        label[2].setFont(Font.font("Times new Roman", FontPosture.REGULAR, 24));
        label[2].setTextFill(Color.ANTIQUEWHITE);
        
        setSpacing(50);
        setAlignment(Pos.CENTER);
        
    }
        
    public void singlePlayerChangeName(){   
        
        hbox[0] = new HBox() ;
        hbox[0].setAlignment(Pos.CENTER);
        hbox[0].getChildren().addAll(label[0] , plrName[0]) ;
        
        plrName[1].setOnAction(e->plrName[1].setText(plrName[1].getText()));
        
        getChildren().add(hbox[0]);
        
        button[1].setOnAction(e->{
                writeNameInFileSinglePlayer() ;
                SinglePlayerMenu root = new SinglePlayerMenu(1) ;
                root.setOption(option);
                
                Pane pane = new Pane() ;
                Image image = new Image("/newpackage/ttt.jpg") ;
                ImageView view = new ImageView(image) ;
                pane.getChildren().addAll(view,root) ;
                
                Control.getStage().setScene(new Scene(pane)) ;
                Control.getStage().setTitle("Single Player");
        }              
        );
        
    }
    
    public void writeNameInFileSinglePlayer(){
        try{
            writer = new BufferedWriter(new FileWriter(file1)) ;
            writer.write(plrName[0].getText());
            writer.newLine();
            
            writer.close();
        }catch(Exception e){
            System.out.println("Excep: " + e);
        }
    }
    
    public void twoPlayerChangeName(){
        
        hbox[1] = new HBox() ;
        hbox[1].setAlignment(Pos.CENTER);
        hbox[1].getChildren().addAll(label[1] , plrName[1]) ;
        
        plrName[1].setOnAction(e->plrName[1].setText(plrName[1].getText()));

        hbox[2] = new HBox() ;
        hbox[2].setAlignment(Pos.CENTER);
        hbox[2].getChildren().addAll(label[2] , plrName[2]) ;
        
        plrName[2].setOnAction(e->plrName[2].setText(plrName[2].getText()));
        
        button[1].setOnAction(e->{
                writeNameInFileTwoPlayer() ;
                SinglePlayerMenu root = new SinglePlayerMenu(1) ;
                root.setOption(option);
                
                Pane pane = new Pane() ;
                Image image = new Image("/newpackage/ttt.jpg") ;
                ImageView view = new ImageView(image) ;
                pane.getChildren().addAll(view,root) ;
                
                Control.getStage().setScene(new Scene(pane)) ;
                Control.getStage().setTitle("Two Player");
        }              
        );
        
        getChildren().addAll(hbox[1] , hbox[2]) ;
    }
    
    public void writeNameInFileTwoPlayer(){
        try{
            writer = new BufferedWriter(new FileWriter(file2)) ;
            writer.write(plrName[1].getText());
            writer.newLine();
            writer.write(plrName[2].getText());
            writer.newLine();
            
            writer.close();
        }catch(Exception e){
            System.out.println("Excep: " + e);
        }
    }
}
