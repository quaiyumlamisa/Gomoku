package javafxapplication1;

import java.io.File;
import java.io.FileWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SinglePlayerMenu extends VBox{
    
    Pane pane = new Pane() ;
    
    int option ;
    
    public SinglePlayerMenu(int n){
        
        
    
        Button [] btn = new Button[5] ;
            
            btn[0] = new Button("New Game") ;
            btn[1] = new Button("Continue") ;
            btn[2] = new Button("Highest Score") ;
            btn[3] = new Button("Change Name") ;
            btn[4] = new Button("Back to Menu") ;
        
            for(int i=0 ; i<4 ; i++)
            {
                btn[i].setTranslateY(-25);
                btn[i].setMinHeight(50);
                btn[i].setMinWidth(200) ;
                
                btn[i].setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 20)); 
                btn[i].setAlignment(Pos.CENTER);
                btn[i].setTextFill(Color.DARKVIOLET);
                btn[i].setStyle("-Fx-background-color : CORNFLOWERBLUE");
            }
            
            btn[4].setTranslateX(510);
            btn[4].setTranslateY(-575);
            btn[4].setTextFill(Color.BLACK);
            btn[4].setStyle("-Fx-background-color : DODGERBLUE");
            btn[4].setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
            
            
            btn[0].setOnAction(e->newGame());
            btn[1].setOnAction(e->continueGame());
            btn[2].setOnAction(e->highestScore());
            btn[3].setOnAction(e->changeName());
            btn[4].setOnAction(e->backToMenu());
            
            setMinSize(1200, 820);
            getChildren().addAll(btn[0],btn[1],btn[2],btn[3],btn[4]) ;
            setAlignment(Pos.CENTER);
            setSpacing(50);
    }
    
    public void setOption(int option){
        this.option = option ;
    }
    
    public void newGame(){
        
        Image image = new Image("/newpackage/hhh.jpg") ;
        ImageView view = new ImageView(image) ;
        view.setFitHeight(820);
        view.setFitWidth(1200);
        
        if(option==1){
            File nfile = new File("SujonRI.txt") ;
            File mfile = new File("RabiulRI.txt") ;
            FileWriter fw = null;
            try{
                fw = new FileWriter(nfile) ;
                fw = new FileWriter(mfile) ;
            }
            
            catch(Exception e){}
            
            newGameSinglePlayer root = new newGameSinglePlayer(1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Single Player Mode");
        }
           
        if(option== 2){
            File nfile = new File("Sujon.txt") ;
            File mfile = new File("Rabiul.txt") ;
            FileWriter fw = null;
            try{
                fw = new FileWriter(nfile) ;
                fw = new FileWriter(mfile) ;
            }
            
            catch(Exception e){}
            
            NewGameTwoPlayer root = new NewGameTwoPlayer(2) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Two Player Mode");
        }
    }
    
    public void continueGame(){
        Image image = new Image("/newpackage/hhh.jpg") ;
        ImageView view = new ImageView(image) ;
        view.setFitHeight(820);
        view.setFitWidth(1200);
        
        if(option==1){
            newGameSinglePlayer root = new newGameSinglePlayer(1,-1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene); 
            Control.getStage().setTitle("Single Player Mode");
        }
        
        if(option==2){
            NewGameTwoPlayer root = new NewGameTwoPlayer(2,-1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Two Player Mode");
        }
    }
    
    public void highestScore(){
        
        Image image = new Image("/newpackage/iii.jpg") ;
        ImageView view = new ImageView(image) ;
        
        if(option==1) {
            Statistics root = new Statistics(1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Highest Score");
        }
        
        if(option==2) {
            Statistics root = new Statistics(2,1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Statistics");
        }
    }
    
    public void changeName(){
        
        Image image = new Image("/newpackage/iii.jpg") ;
        ImageView view = new ImageView(image) ;
        
        
        if(option==1){
            ChangeName root = new ChangeName(1) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Change Name");
        }
        
        if(option==2){
            ChangeName root = new ChangeName(2) ;
            
            pane.getChildren().addAll(view,root) ;
            
            Scene scene = new Scene(pane) ;
            Control.getStage().setScene(scene);
            Control.getStage().setTitle("Change Name");
        }
    }
    
    public void backToMenu(){
        
        OpeningMenu root = new OpeningMenu(1) ;
        
        Image image = new Image("/newpackage/ttt.jpg") ;
        ImageView view = new ImageView(image) ;
        pane.getChildren().addAll(view,root) ;
        
        Scene scene = new Scene(pane) ;
        
        Control.getStage().setScene(scene);
        Control.getStage().setTitle("Menu");
    }
}
