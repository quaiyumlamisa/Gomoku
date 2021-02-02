package javafxapplication1;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.io.* ;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public final class NewGameTwoPlayer extends  AnchorPane{
    
    Circle [] p = new Circle[100] ;
    Line [] line = new Line[200] ;
    Line line1 = new Line() ;
    Label label1 = new Label() ; 
    
    Button btn = new Button() ;
    Button [] button = new Button[10] ;
    
    
    int player = 1 ; 
    int l = 0 ,point1 = 0 , point2 = 0 ,checkDouble = 0, pt1,pt2,option ;
    
    String plrName1 , plrName2 ;
    
    File file = new File("Sujon.txt") ;
    File file2 = new File("Rabiul.txt") ;
    File file3 = new File("Islam.txt") ;
    File file4 = new File("NameFile2.txt") ;
    File file5 = new File("Score2.txt") ;
    File file6 = new File("Score2help.txt") ;
    FileWriter fw = null ;
    BufferedWriter writer = null ;
    FileReader fr = null ;
    BufferedReader reader = null ;
    
    public NewGameTwoPlayer(){
    }
       
    public NewGameTwoPlayer(int option){
        this.option = option ;
        setName() ;
        managingUnit();
        platformCircle() ;
        getClickPoint() ;
    }
    
    public NewGameTwoPlayer(int option,int n){
        this.option = option ;
        setName() ;
        managingUnit();
        platformCircle() ;
        readInputFromFileAndDraw() ;
        getClickPoint() ;
    }
    
    public void setName(){
        
        try{
            reader = new BufferedReader(new FileReader(file4)) ;
            plrName1 = reader.readLine() ;
            plrName2 = reader.readLine() ;
            
            reader.close();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }       
    }
    
    public void managingUnit(){
        setStyle("-fx-background: AliceBlue");
        btn.setText(plrName1 + "'s Turn");
        btn.setLayoutX(710);
        btn.setLayoutY(150);
        btn.setMinSize(400, 80);
        btn.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 40));
        btn.setStyle("-fx-background-color: Brown");
        btn.setTextFill(Color.AQUAMARINE);
        button[0] = new Button(plrName1 + " : " + point1) ;
        button[1] = new Button(plrName2 + " : " + point2) ;
        button[0].setLayoutX(760);
        button[0].setLayoutY(300);
        button[1].setLayoutX(760);
        button[1].setLayoutY(400);
        button[0].setShape(new Circle(10));
        button[1].setShape(new Circle(10));
        button[0].setMinSize(300,50) ;
        button[1].setMinSize(300,50);
        button[0].setStyle("-fx-background-color: DarkOrange");
        button[1].setStyle("-fx-background-color: Aqua");
        //button[0].setTextFill(Color.);
        
        button[0].setFont(Font.font("Segoe Script", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 32));
        button[1].setFont(Font.font("Segoe Script", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 32));
        
        button[2] = new Button() ;
        button[2].setText("back");
        button[2].setLayoutX(1100);
        button[2].setLayoutY(20);
        button[2].setTextFill(Color.BLACK);
        button[2].setStyle("-Fx-background-color : DODGERBLUE");
        button[2].setFont(Font.font("Times New Roman",FontWeight.BOLD, 14));
        
        button[2].setOnAction(e->{
            SinglePlayerMenu root = new SinglePlayerMenu(1) ;
            root.setOption(option);
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/ttt.jpg") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root);
            Control.getStage().setScene(new Scene(pane)) ;
        });
        
        label1.setLayoutX(200);
        label1.setLayoutY(590);
        label1.setAlignment(Pos.CENTER);
        label1.setMinSize(800 , 200);
        label1.setFont(Font.font("MS Sans Serif", FontWeight.LIGHT, FontPosture.ITALIC, 60)) ;
        label1.setTextFill(Color.CORNFLOWERBLUE);
        label1.setText("WelCome To My First Game") ;
        
        getChildren().addAll(btn,button[0],button[1],label1,button[2]) ;
    }
    
    public void platformCircle(){

        for(int i=0 ; i<100 ; i++)
        {
            p[i] = new Circle() ;
        }

        int y=0 ;
        for(int i=0 ; i<10 ;i++){
            y = y+60 ;
            int x = 50 ;
            for(int j=0 ; j<10 ; j++){
                x = x+60 ;
                p[i*10+j].setCenterX(x);
                p[i*10+j].setCenterY(y);
                p[i*10+j].setFill(Color.LIGHTGREEN);
                p[i*10+j].setRadius(15);
                p[i*10+j].setStroke(Color.GREEN);   
            }
        }
          
        for(int i = 0 ; i < 100 ; i++ ){
            getChildren().add(p[i]);
        }
        
    }
    
    public void getClickPoint(){
        
        
        setOnMousePressed(e->{ 
               
            for(int i=0 ; i<100 ; i++)
            {
                if(p[i].contains(e.getX(),e.getY())){
                    int pt1 = i ;
                    setPT1(pt1) ;
                    //System.out.println(e.getX()+"hhh"+e.getY());
                    break ;
                }
            }
            
            setOnMouseReleased(event->{ 
        
            for(int i=0 ; i<100 ; i++)
            {
                if(p[i].contains(event.getX(),event.getY())){
                    int pt2 = i ;
                    setPT2(pt2) ;
                    //System.out.println(event.getX()+"mmm"+event.getY());
                    break ;
                }
            } 
            
                drawLine(); 
        });
        });               
    }
    
    public void setPT1(int pt1){
        this.pt1 = pt1 ;
    } 
    
    public void setPT2(int pt2){
        this.pt2 = pt2 ;
    } 
    
    public void drawLine(){   
   
        line1 = new Line(p[pt1].getCenterX(),p[pt1].getCenterY(),p[pt2].getCenterX(),p[pt2].getCenterY()) ;
        line1.setStrokeWidth(5);
     
            int flag = 1 ;
            for(int k = 0 ; k < l ; k++){
                if(line1.getStartX()==line[k].getStartX() && line1.getStartY()==line[k].getStartY() && line1.getEndX()==line[k].getEndX() && line1.getEndY()==line[k].getEndY()){
                      flag = -1 ;
                      break ;
                } 

                if(line1.getStartX()==line[k].getEndX() && line1.getStartY()==line[k].getEndY() && line1.getEndX()==line[k].getStartX() && line1.getEndY()==line[k].getStartY()){
                      flag = -1 ;
                      break ;
                }
           }

            if(flag==1){
                if(pt1==pt2+1 && (pt2+1)%10!=0 || pt2==pt1+1 && (pt1+1)%10!=0 || (pt1==pt2+10) && (pt1-9) >= 0 || (pt1==pt2-10) && (pt1+10)<100){
                    getChildren().addAll(line1) ;

                    line[l] = line1 ;
                    l++ ;
                    
                    if(player==1) line1.setStroke(Color.RED);
                    if(player==2) line1.setStroke(Color.BLUE);
                    
                    try{
                        writer = new BufferedWriter(new FileWriter(file,true));
                        writer.write(player+"");
                        writer.newLine() ;
                        writer.write(line1.getStartX() + "");
                        writer.newLine();
                        writer.write(line1.getStartY() + "");
                        writer.newLine();
                        writer.write(line1.getEndX() + "");
                        writer.newLine() ;
                        writer.write(line1.getEndY() + "");
                        writer.newLine();
                        
                        writer.close();
                        
                    }catch(Exception e){
                        System.out.println("Exception : " + e);
                    }
                    
                    checkPoint() ;
                    
                    try{      
                        writer = new BufferedWriter(new FileWriter(file3));
                        writer.write(player +"");
                        writer.close();

                    }catch(Exception e){
                        System.out.println("Exception : " + e);
                    }
                    
                }
        }
    }
    
    public void checkPoint()
    {
        checkDouble = 0 ;
        for(int i = 0 ;i < l-1 ; i++){
            if(line[l-1].getEndX()==line[i].getStartX() && line[l-1].getEndY()==line[i].getStartY()){
                for(int j=0 ; j<l-1 ;j++){
                    if(j==i) continue ;
                    
                    if(line[i].getEndX()==line[j].getStartX() && line[i].getEndY()==line[j].getStartY()){
                        
                        for(int k=0 ; k<l-1 ; k++){
                            if(j==i || j==k) continue ;
                            
                            if(line[j].getEndX()==line[k].getStartX() && line[j].getEndY()==line[k].getStartY()){
                                
                                if(line[k].getEndX()==line[l-1].getStartX() && line[k].getEndY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    
                                    checkDouble = 1 ;
                                }
                            }
                            
                            else if(line[j].getEndX()==line[k].getEndX() && line[j].getEndY()==line[k].getEndY()){
                                
                                if(line[k].getStartX()==line[l-1].getStartX() && line[k].getStartY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    
                                    checkDouble = 1 ;
                                }
                            }
                        }
                    }
                    
                    else if(line[i].getEndX()==line[j].getEndX() && line[i].getEndY()==line[j].getEndY()){
                        
                        for(int k=0 ; k<l-1 ; k++){
                            if(j==i || j==k) continue ;
                            
                            if(line[j].getStartX()==line[k].getStartX() && line[j].getStartY()==line[k].getStartY()){
                                
                                if(line[k].getEndX()==line[l-1].getStartX() && line[k].getEndY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{  
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    checkDouble = 1 ;
                                }
                            }
                            
                            else if(line[j].getStartX()==line[k].getEndX() && line[j].getStartY()==line[k].getEndY()){
                                
                                if(line[k].getStartX()==line[l-1].getStartX() && line[k].getStartY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    
                                    checkDouble = 1 ;
                                }
                            }
                        }
                    }
                }
            }
            
            else if(line[l-1].getEndX()==line[i].getEndX() && line[l-1].getEndY()==line[i].getEndY()){
                for(int j=0 ; j<l-1 ;j++){
                    if(j==i) continue ;
                    
                    if(line[i].getStartX()==line[j].getStartX() && line[i].getStartY()==line[j].getStartY()){
                        
                        for(int k=0 ; k<l-1 ; k++){
                            if(j==i || j==k) continue ;
                            
                            if(line[j].getEndX()==line[k].getStartX() && line[j].getEndY()==line[k].getStartY()){
                                
                                if(line[k].getEndX()==line[l-1].getStartX() && line[k].getEndY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    checkDouble = 1 ;
                                }
                            }
                            
                            else if(line[j].getEndX()==line[k].getEndX() && line[j].getEndY()==line[k].getEndY()){
                                
                                if(line[k].getStartX()==line[l-1].getStartX() && line[k].getStartY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    checkDouble = 1 ;
                                }
                            }
                        }
                    }
                    
                    else if(line[i].getStartX()==line[j].getEndX() && line[i].getStartY()==line[j].getEndY()){
                        
                        for(int k=0 ; k<l-1 ; k++){
                            if(j==i || j==k) continue ;
                           
                            
                            if(line[j].getStartX()==line[k].getStartX() && line[j].getStartY()==line[k].getStartY()){
                                
                                if(line[k].getEndX()==line[l-1].getStartX() && line[k].getEndY()==line[l-1].getStartY()){
                                    
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    checkDouble = 1 ;
                                }
                            }
                            
                            else if(line[j].getStartX()==line[k].getEndX() && line[j].getStartY()==line[k].getEndY()){
                                
                                if(line[k].getStartX()==line[l-1].getStartX() && line[k].getStartY()==line[l-1].getStartY()){
                                   
                                    if(line[l-1].getStartX()!= line[l-1].getEndX()){
                                        setPointName((line[l-1].getStartX()+line[l-1].getEndX())/2,(line[k].getStartY()+line[k].getEndY())/2) ;
                                    }
                                    
                                    else{
                                        setPointName((line[k].getStartX()+line[k].getEndX())/2,(line[l-1].getStartY()+line[l-1].getEndY())/2) ;
                                    }
                                    checkDouble = 1 ;
                                }
                            }
                        }
                    }
                }
            }
            
        }
        
        if(player == 1){
            btn.setText(plrName2 + "'s Turn");
            player = 2 ;
        }
        else if(player == 2){
            btn.setText(plrName1 + "'s Turn");
            player = 1 ;
        }
        
    }
    
    public void setPointName(double x,double y){
        
        Label label = new Label() ;
        
        if(player==1 && checkDouble != 1) player = 2 ;
        else if(player==2 && checkDouble !=1) player = 1 ;
      
        label.setLayoutX(x-25);
        label.setLayoutY(y-25);
        label.setMinSize(50, 50);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)) ;
        
        try{
                writer = new BufferedWriter(new FileWriter(file2,true)) ;
                writer.write(label.getLayoutX()+"");
                writer.newLine();
                writer.write(label.getLayoutY()+"");
                writer.newLine();
                writer.write(player+"");
                writer.newLine();
                
                writer.close();
            }catch(Exception e){
                System.out.println("Ex : " + e);
            }
        
        if(player == 1){
            String firstLetter = "" ;
            firstLetter = String.valueOf(plrName2.charAt(0)) ;
            label.setText(firstLetter);
            label.setStyle("-fx-background-color: Aqua") ;
            point2++ ;
            button[1].setText(plrName2 +" : " + point2);
            
        }
        
        else {
            String firstLetter = "" ;
            firstLetter = String.valueOf(plrName1.charAt(0)) ;
            label.setText(firstLetter);
            label.setStyle("-fx-background-color: DarkOrange") ;
            point1++ ;
            button[0].setText(plrName1 + " : " + point1);
            
            
        }
        
        if(point1+point2==81){
            if(point1 > point2){
                setScoreCard(plrName1,point1,plrName2,point2) ;
                
                Congrats root = new Congrats(plrName1,point1,2) ;
            
                Pane pane = new Pane() ;
                Image image = new Image("/newpackage/kkk.png") ;
                ImageView view = new ImageView(image) ;
                pane.getChildren().addAll(view,root) ;

                Control.getStage().setScene(new Scene(pane)) ;
                
            }
            
            else if(point1 < point2){
                setScoreCard(plrName2,point2,plrName1,point1) ;
                
                Congrats root = new Congrats(plrName2,point2,2) ;
            
                Pane pane = new Pane() ;
                Image image = new Image("/newpackage/kkk.png") ;
                ImageView view = new ImageView(image) ;
                pane.getChildren().addAll(view,root) ;

                Control.getStage().setScene(new Scene(pane)) ;
                
            }
            
            else label1.setText("Draw Match") ;
                
        }
        
        label.setAlignment(Pos.CENTER);
        
        getChildren().add(label) ;
        
    }
    
    public void readInputFromFileAndDraw(){
        
        try{
            reader = new BufferedReader(new FileReader(file)) ;
            String str ;
            while((str=reader.readLine())!=null){
                double plr = Double.valueOf(str) ;
                double line1X = Double.valueOf(reader.readLine()) ;
                double line1Y = Double.valueOf(reader.readLine()) ;
                double line2X = Double.valueOf(reader.readLine()) ;
                double line2Y = Double.valueOf(reader.readLine()) ;
               
                System.out.println(" player :  " + plr +"  Line 1 : " + line1X + " Line 1: " + line1Y + " Line 2 : " + line2X + " Line 2 : " + line2Y);
                
                continueDrawLine(plr ,line1X,line1Y,line2X,line2Y) ;
            }
            
        }catch(Exception e){
            System.out.println("Ex1 : " + e );
        }
        double plr = 0 ;
        try{
            reader = new BufferedReader(new FileReader(file3)) ;
            plr = Double.valueOf(reader.readLine()) ;      
        }catch(Exception e){
            System.out.println("Ex2 : " + e );
        }
        
        if(plr==1.0){
            btn.setText(plrName1 + "'s turn");
            player = 1 ;
        }
        
        else if(plr==2.0){
            btn.setText(plrName2 + "'s turn");
            player = 2 ;
        }
        
        try{
            reader = new BufferedReader(new FileReader(file2)) ;
            
            String str ;
            while((str=reader.readLine())!=null)
            {
                double positionX = Double.valueOf(str) ;
                double positionY = Double.valueOf(reader.readLine()) ;
                double plr2 = Double.valueOf(reader.readLine()) ;
                
                continueDrawPoint(positionX,positionY,(int)plr2) ;
            }
        }catch(Exception e){
            System.out.println("Ex3 : " + e );
        }
    }
    
    public void continueDrawLine(double plr ,double x1,double y1,double x2 , double y2){
        Line newLine = new Line(x1 , y1 , x2 , y2);
        newLine.setStrokeWidth(5);
        line[l]= newLine ;
        l++ ;
        
        if(plr==1.0) newLine.setStroke(Color.RED);
        else if(plr==2.0) newLine.setStroke(Color.BLUE);
        getChildren().add(newLine);
    }
    
    public void continueDrawPoint(double x , double y , int plr){
        
        Label label = new Label() ;
        
        label.setAlignment(Pos.CENTER);
      
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setMinSize(50, 50);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)) ;
        if(plr==2){
            String firstLetter = "" ;
            firstLetter = String.valueOf(plrName1.charAt(0)) ;
            label.setText(firstLetter);
            label.setStyle("-fx-background-color: DarkOrange") ;
            point1++ ;
            button[0].setText(plrName1 + " : " + point1);
        }
        
        else if(plr==1){
            String firstLetter = "" ;
            firstLetter = String.valueOf(plrName2.charAt(0)) ;
            label.setText(firstLetter);
            label.setStyle("-fx-background-color: Aqua") ;
            point2++ ;
            button[1].setText( plrName2 + " : " + point2);
            
        } 
        
        getChildren().add(label) ;
    }
    
    public void setHighestScore(){
    }
    
    public void setScoreCard(String plrName1,int point1 ,String plrName2 , int point2){
        
        try{
            reader = new BufferedReader(new FileReader(file5)) ;
            writer = new BufferedWriter(new FileWriter(file6)) ;
            String str ;
            int i = 0 ;
            while((str=reader.readLine())!=null && i<36){
                 writer.write(str);
                 writer.newLine() ;
                 i++ ;
            }
            writer.close();
            writer = new BufferedWriter(new FileWriter(file5)) ;
            writer.close();
            reader.close();
        }catch(Exception e){
            System.out.println("Exception and : " + e);
        }    
        
        
        try{
            writer = new BufferedWriter(new FileWriter(file5)) ;
  
                writer.write(point1 + "") ;
                writer.newLine() ;
                writer.write(plrName1) ;
                writer.newLine();
                writer.write(point2 + "") ;
                writer.newLine() ;
                writer.write(plrName2) ;
                writer.newLine();
              
            reader = new BufferedReader(new FileReader(file6)) ;
            String str ;
            while((str=reader.readLine())!=null){
                 writer.write(str);
                 writer.newLine();
            }
            
            writer.close();
            writer = new BufferedWriter(new FileWriter(file6)) ;
            writer.close();
            reader.close();
            
            }catch(Exception e){}
        
    }
    
}