package javafxapplication1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Control extends Application {
    
    private static Stage stage ;

    public static Stage getStage() {
        return stage;
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        this.stage = primaryStage ;
        
        //createNewFile() ;
       
        OpeningMenu root = new OpeningMenu(1) ;
        
        Pane pane = new Pane() ;
        Image image = new Image("/newpackage/ttt.jpg") ;
        Image image2 = new Image("/newpackage/myGame.PNG") ;
        ImageView view = new ImageView(image) ;
        pane.getChildren().addAll(view,root) ;
        
        Scene scene = new Scene(pane) ; 
        
        primaryStage.setTitle("Menu");
        primaryStage.getIcons().add(image2) ;
        primaryStage.setScene(scene);
        primaryStage.show();   
       
        primaryStage.setMaxWidth(1200);
        primaryStage.setMaxHeight(820);
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(820);
        primaryStage.setX(100);
        primaryStage.setY(15);
    }

    public void createNewFile(){
        
        File file1 = new File("/src/newpackage/NameFile1.txt") ;
        File file2 = new File("NameFile2.txt") ;
        File file3 = new File("Score1.txt") ;
        File file4 = new File("Score2.txt") ;
        FileWriter fw = null ;
        BufferedWriter writer = null ;
        FileReader fr = null ;
        BufferedReader reader = null ;
        
        try{
            writer = new BufferedWriter(new FileWriter(file3,true)) ;
            writer.close();
            reader = new BufferedReader(new FileReader(file3)) ;
            String str ;
           // if((str=reader.readLine())==null) { 
                writer = new BufferedWriter(new FileWriter(file1)) ;
                writer.write("Sujon");
                writer.newLine();
                writer.close();
                
                writer = new BufferedWriter(new FileWriter(file2)) ;
                writer.write("Jannat");
                writer.newLine();
                writer.write("Sujon");
                writer.newLine();
                writer.close();
                
                writer = new BufferedWriter(new FileWriter(file3)) ;
                for(int i = 0 ; i<10 ;i++){
                    writer.write( 0.1*i +"");
                    writer.newLine();
                    writer.write("Player");
                    writer.newLine();
                }
                writer.close();
                
                writer = new BufferedWriter(new FileWriter(file4)) ;
                for(int i = 0 ; i<20 ;i++){
                    writer.write( 0 +"");
                    writer.newLine();
                    writer.write("Player1");
                    writer.newLine();
                }
                writer.close();
           // }
            
           reader.close();
        }catch(Exception e){}
        
    }
    
}
