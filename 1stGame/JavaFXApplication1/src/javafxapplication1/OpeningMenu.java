package javafxapplication1;

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


public class OpeningMenu extends VBox{
    
    Pane pane = new Pane() ;
    Image image = new Image("/newpackage/ttt.jpg") ;
    ImageView view = new ImageView(image) ;
    
    public OpeningMenu(){
    }
    
    public OpeningMenu(int n){
    
        Button [] btn = new Button[5] ;
            
            btn[0] = new Button("Single Player") ;
            btn[1] = new Button("Two Player") ;
            btn[2] = new Button("About") ;
            btn[3] = new Button("Gomoku") ;
            btn[4] = new Button("Exit") ;
        
            for(int i=0 ; i<5 ; i++)
            {
                btn[i].setTranslateY(-50);
                btn[i].setMinHeight(50);
                btn[i].setMinWidth(200) ;
                
                btn[i].setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 20)); 
                btn[i].setAlignment(Pos.CENTER);
                btn[i].setTextFill(Color.DARKVIOLET);
                btn[i].setWrapText(true);
                
                btn[i].setStyle("-Fx-background-color : CORNFLOWERBLUE");
                btn[i].setRotate(1);
            }
            
            btn[0].setOnAction(e->singlePlayer());
            btn[1].setOnAction(e->twoPlayer());
            btn[2].setOnAction(e->highestScore());
            btn[3].setOnAction(e->help());
            btn[4].setOnAction(e->exit());
            
            setMinSize(1200, 820);
            //getChildren().addAll(btn[0],btn[1],btn[2],btn[3],btn[4]) ;
            getChildren().addAll(btn[3]) ;
            setAlignment(Pos.CENTER);
            setSpacing(50);
            //setStyle("-fx-background-color : Beige");
            
    }
        
    public void singlePlayer(){
        SinglePlayerMenu root = new SinglePlayerMenu(1) ;
        
        pane.getChildren().addAll(view,root) ;
            
        Scene scene = new Scene(pane) ;
        
        root.setOption(1) ;
        
        Control.getStage().setScene(scene) ;
        Control.getStage().setTitle("Single Player") ;
    }
    
    public void twoPlayer(){
        SinglePlayerMenu root = new SinglePlayerMenu(1) ;
        
        pane.getChildren().addAll(view,root) ;
            
        Scene scene = new Scene(pane) ;
        root.setOption(2) ;
        Control.getStage().setScene(scene) ;
        Control.getStage().setTitle("Two Player") ;
    }
    
    public void highestScore(){
                Congrats root = new Congrats("kamal",52,1) ;
            
                Pane pane = new Pane() ;
                Image image = new Image("/newpackage/kkk.png") ;
                ImageView view = new ImageView(image) ;
                pane.getChildren().addAll(view,root) ;

                Control.getStage().setScene(new Scene(pane)) ;
    }
    
    public void help(){
        GomokuUI root = new GomokuUI() ;

//        Image image = new Image("/newpackage/hhh.jpg") ;
//        ImageView view = new ImageView(image) ;
//        view.setFitHeight(820);
//        view.setFitWidth(1200);

        pane.getChildren().addAll(view,root) ;

        Scene scene = new Scene(pane) ;

        Control.getStage().setScene(scene) ;
        Control.getStage().setTitle("Gomoku") ;
    }
   
    public void exit(){
        Control.getStage().close();
    }
    
}
    
    

