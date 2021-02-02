package javafxapplication1;


import java.io.* ;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public final class newGameSinglePlayer extends AnchorPane{
     
    Circle [] p = new Circle[100] ;
    Line [] line = new Line[200] ;
    Line line1 = new Line() ;
    Label label1 = new Label() ;
    
    Button btn = new Button("Your Turn") ;
    Button [] button = new Button[10] ;
    
    int [] arr = new int[1000] ;
    int flag1,advancedflag ,flag5=0 ,flag6 = 0,flag7=0 ,flag8=0,count=0;
    Line line2 = new Line() ;

    int player = 1 ; 
    int l = 0 ,point1 = 0 , point2 = 0 ,checkDouble = 0,option ,pt1,pt2;
    String plrName1  ;
    
    File file1 = new File("SujonRI.txt") ;
    File file2 = new File("RabiulRI.txt") ;
    File file3 = new File("IslamRI.txt") ;
    File file4 = new File("NameFile1.txt") ;
    File file5 = new File("Score1.txt") ;
    FileWriter fw = null ;
    BufferedWriter writer = null ;
    FileReader fr = null ;
    BufferedReader reader = null ;
    
    public newGameSinglePlayer(){
    }
    
    public newGameSinglePlayer(int option){
        
        for(int i=0 ; i<100 ; i++){
            arr[i]=0 ;
        }
        
        this.option = option ;
        
        setName() ;
        platformCircle();
        managingUnit() ;
        platformCircle();
        getClickPoint();
    }
    
    public newGameSinglePlayer(int option , int n){
        
        for(int i=0 ; i<100 ; i++){
            arr[i]=0 ;
        }
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
            
            reader.close();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }       
    }
    
    public void managingUnit(){
        setStyle("-fx-background: AliceBlue");
        btn.setLayoutX(710);
        btn.setLayoutY(150);
        btn.setMinSize(400, 80);
        btn.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 40));
        btn.setStyle("-fx-background-color: Brown");
        btn.setTextFill(Color.AQUAMARINE);
        button[0] = new Button(plrName1 + " : " + point1) ;
        button[1] = new Button("RI Sujon : " + point2) ;
        button[0].setLayoutX(760);
        button[0].setLayoutY(300);
        button[1].setLayoutX(760);
        button[1].setLayoutY(400);
        button[0].setShape(new Circle(10));
        button[1].setShape(new Circle(10));
        button[0].setMinSize(300,50) ;
        button[1].setMinSize(300,50);
        button[0].setStyle("-fx-background-color: Aqua");
        button[1].setStyle("-fx-background-color: DarkOrange");
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
        
        getChildren().addAll(btn,button[0],button[1],button[2],label1) ;
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
                    
                    break ;
                }
            }
            
            setOnMouseReleased(event->{ 
            
            for(int i=0 ; i<100 ; i++)
            {
                if(p[i].contains(event.getX(),event.getY())){
                    int pt2 = i ;
                    setPT2(pt2) ;
                    
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
        
        while(true){
            if(player==2){
                computerTurn() ;
            }
        
            line1 = new Line(p[pt1].getCenterX(),p[pt1].getCenterY(),p[pt2].getCenterX(),p[pt2].getCenterY()) ;
            line1.setStrokeWidth(5);
     
            int flag = 1 ;
            for(int k = 0 ; k < l ; k++){
                if(line1.getStartX()==line[k].getStartX() && line1.getStartY()==line[k].getStartY() && line1.getEndX()==line[k].getEndX() && line1.getEndY()==line[k].getEndY()){
                      flag = 0 ;
                      break ;
                } 

                if(line1.getStartX()==line[k].getEndX() && line1.getStartY()==line[k].getEndY() && line1.getEndX()==line[k].getStartX() && line1.getEndY()==line[k].getStartY()){
                      flag = 0 ;
                      break ;
                }
           }
            
            if(flag==1){
                if(pt1==pt2+1 && (pt2+1)%10!=0 || pt2==pt1+1 && (pt1+1)%10!=0 || (pt1==pt2+10) && (pt1-9) >= 0 || (pt1==pt2-10) && (pt1+10)<100){
                    getChildren().addAll(line1) ;
                  
                    line[l] = line1 ;
                    l++ ;
                    
                    if(player==1) line1.setStroke(Color.BLUE);
                    if(player==2) line1.setStroke(Color.RED);
                    
                    try{
                        writer = new BufferedWriter(new FileWriter(file1,true));
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
                }    
            }
        
            if(point1+point2==81) player = 1 ;
            if(player==1) break ;
            
        }
        
    }
    
    public void computerTurn(){
    
        Random r = new Random() ;
        flag1 = 0 ;
        advancedflag=0 ;
        
        advancedStep();
        if(advancedflag==0){
            for(int i = r.nextInt(99) ;  i <100 ; ){

                if(l<50){
                    i = r.nextInt(99) ;
                }
                
                else{
                    if(flag8==0)i=0 ;
                    
                    flag8 = 1 ;
                    i++ ;
                }
                
                if(arr[i]==2) {
                    continue ;
                }

                if(i%2==0){
                    if(i<90 ) computerTurnCheck(i,i+10) ;
                    if((i+1)%10!=0 && i<99 && flag1!=1) computerTurnCheck(i, i+1) ;
                }
                
                else{
                    if((i+1)%10!=0 && i<99) computerTurnCheck(i, i+1) ;
                    if(i<90 && flag1!=1) computerTurnCheck(i,i+10) ;
                }

                if(flag1==1) 
                {
                    flag7=0 ;
                    moreAdvancedStep(); 
                    if(flag7 == 1 && count < 1000){
                        count++ ;
                        System.out.println(count);
                        continue ;
                        
                    }
                    
                    arr[i]++ ;
                    break ; 
                }
            }
        }
    }
    
    public void computerTurnCheck(int i , int j){
        
            pt1 = i ;
            pt2 = j ;
            line2 = new Line(p[pt1].getCenterX(),p[pt1].getCenterY(),p[pt2].getCenterX(),p[pt2].getCenterY()) ;
            int flag2 = 1 ;
            for(int k = 0 ; k < l ; k++){
                if(line2.getStartX()==line[k].getStartX() && line2.getStartY()==line[k].getStartY() && line2.getEndX()==line[k].getEndX() && line2.getEndY()==line[k].getEndY()){
                    flag2 = 0 ;
                    break ;
                } 

                if(line2.getStartX()==line[k].getEndX() && line2.getStartY()==line[k].getEndY() && line2.getEndX()==line[k].getStartX() && line2.getEndY()==line[k].getStartY()){
                    flag2 = 0 ;
                    break ; 
                }
            }
            
            if(flag2 == 1) {

                flag1 = 1 ;
                //System.out.println("welcome");
                return ;
            }
                
    }
    
    public void advancedStep(){
        
        flag6=0 ;
        for(int i=0 ; i<l ; i++){
            for(int j=0 ; j<l ; j++){
                if(line[i].getEndX()==line[j].getStartX() && line[i].getEndY()==line[j].getStartY() && line[i].getStartX()!=line[j].getEndX() && line[i].getStartY()!=line[j].getEndY()){
                    for(int k=0 ; k<l ; k++){
                        if(line[j].getEndX()==line[k].getStartX() && line[j].getEndY()==line[k].getStartY() && (line[i].getStartX()==line[k].getEndX() || line[i].getStartY()==line[k].getEndY()) && line[j].getStartX()!=line[k].getEndX() && line[j].getStartY()!=line[k].getEndY()){
                            
                            setCircleNumber(line[i].getStartX(),line[i].getStartY(),line[k].getEndX(),line[k].getEndY());
                            
                            if(flag6 == 1) continue ;
                            advancedflag = 1 ;
                            
                            return ;
                        }
                        
                        else if(line[j].getEndX()==line[k].getEndX() && line[j].getEndY()==line[k].getEndY() && (line[i].getStartX()==line[k].getStartX() || line[i].getStartY()==line[k].getStartY()) && line[j].getStartX()!=line[k].getStartX() && line[j].getStartY()!=line[k].getStartY()){
                            
                            setCircleNumber(line[i].getStartX(),line[i].getStartY(),line[k].getStartX(),line[k].getStartY());
                            
                            if(flag6 == 1) continue ;
       
                            advancedflag = 1 ;
                            
                            return ;
                        }
                    }
                }
                
                else if(line[i].getEndX()==line[j].getEndX() && line[i].getEndY()==line[j].getEndY() && line[i].getStartX()!=line[j].getStartX() && line[i].getStartY()!=line[j].getStartY()){
                    for(int k=0 ; k<l ; k++){
                        if(line[j].getStartX()==line[k].getStartX() && line[j].getStartY()==line[k].getStartY() && (line[i].getStartX()==line[k].getEndX() || line[i].getStartY()==line[k].getEndY()) && line[j].getEndX()!=line[k].getEndX() && line[j].getEndY()!=line[k].getEndY() ){
                            
                            setCircleNumber(line[i].getStartX(),line[i].getStartY(),line[k].getEndX(),line[k].getEndY());
                            
                            if(flag6 == 1) continue ;
                         
                            advancedflag = 1 ;
                            
                            return ;
                        }
                        
                        else if(line[j].getStartX()==line[k].getEndX() && line[j].getStartY()==line[k].getEndY() && (line[i].getStartX()==line[k].getStartX() || line[i].getStartY()==line[k].getStartY())&& line[j].getEndX()!=line[k].getStartX() && line[j].getEndY()!=line[k].getStartY()){
                            
                            flag6 = 0 ;
                            setCircleNumber(line[i].getStartX(),line[i].getStartY(),line[k].getStartX(),line[k].getStartY());
                            
                            if(flag6 == 1) continue ;
                           
                            advancedflag = 1 ;
                           
                            return ;
                        }
                    }
                }
                
                else if(line[i].getStartX()==line[j].getStartX() && line[i].getStartY()==line[j].getStartY() && line[i].getEndX()!=line[j].getEndX() && line[i].getEndY()!=line[j].getEndY()){
                    for(int k=0 ; k<l ; k++){
                        if(line[j].getEndX()==line[k].getStartX() && line[j].getEndY()==line[k].getStartY() && (line[i].getEndX()==line[k].getEndX() || line[i].getEndY()==line[k].getEndY()) && line[j].getStartX()!=line[k].getEndX() && line[j].getStartY()!=line[k].getEndY()){
                            
                            setCircleNumber(line[i].getEndX(),line[i].getEndY(),line[k].getEndX(),line[k].getEndY());
              
                            if(flag6 == 1) continue ;
                        
                            advancedflag=1 ;
                            
                            return ;
                        }
                        
                        else if(line[j].getEndX()==line[k].getEndX() && line[j].getEndY()==line[k].getEndY() && (line[i].getEndX()==line[k].getStartX() || line[i].getEndY()==line[k].getStartY()) && line[j].getStartX()!=line[k].getStartX() && line[j].getStartY()!=line[k].getStartY()){
                            
                            setCircleNumber(line[i].getEndX(),line[i].getEndY(),line[k].getStartX(),line[k].getStartY());
                            
                            if(flag6 == 1) continue ;
                           
                            advancedflag = 1 ;
                            
                            return ;
                        }
                    }
                }
                
                else if(line[i].getStartX()==line[j].getEndX() && line[i].getStartY()==line[j].getEndY() && line[i].getEndX()!=line[j].getStartX() && line[i].getEndY()!=line[j].getStartY()){
                    for(int k=0 ; k<l ; k++){
                        if(line[j].getStartX()==line[k].getStartX() && line[j].getStartY()==line[k].getStartY() && (line[i].getEndX()==line[k].getEndX() || line[i].getEndY()==line[k].getEndY()) && line[j].getEndX()!=line[k].getEndX() && line[j].getEndY()!=line[k].getEndY()){
                            
                            setCircleNumber(line[i].getEndX(),line[i].getEndY(),line[k].getEndX(),line[k].getEndY());
                            
                            if(flag6 == 1) continue ;
                            
                            advancedflag = 1 ;
                            
                            return ;
                        }
                        
                        else if(line[j].getStartX()==line[k].getEndX() && line[j].getStartY()==line[k].getEndY() && (line[i].getEndX()==line[k].getStartX() || line[i].getEndY()==line[k].getStartY()) && line[j].getEndX()!=line[k].getStartX() && line[j].getEndY()!=line[k].getStartY()){
                            
                            setCircleNumber(line[i].getEndX(),line[i].getEndY(),line[k].getStartX(),line[k].getStartY());
                            
                            if(flag6 == 1) continue ;
                            
                            advancedflag = 1 ;
                            
                            return ;
                        }
                    }
                }
            }
        }
    }
    
    public void setCircleNumber(double x1,double y1,double x2,double y2){
        
        int ptt1 = 0 ,ptt2 = 0 ;
        flag6 = 0 ;
        
        for(int i=0 ; i<100 ; i++){
            if(p[i].getCenterX()==x1 && p[i].getCenterY()==y1){
                ptt1 = i ;
                break ;
            }
        }
        
        for(int i=0 ; i<100 ; i++){
            if(p[i].getCenterX()==x2 && p[i].getCenterY()==y2){
                ptt2 = i ;
                break ;
            }
        }
        
        Line line2 = new Line(p[ptt1].getCenterX(),p[ptt1].getCenterY(),p[ptt2].getCenterX(),p[ptt2].getCenterY()) ;
                for(int k = 0 ; k < l ; k++){
                    if(line2.getStartX()==line[k].getStartX() && line2.getStartY()==line[k].getStartY() && line2.getEndX()==line[k].getEndX() && line2.getEndY()==line[k].getEndY()){  
                        
                        flag6 = 1 ;
                        break ;
                    } 

                    if(line2.getStartX()==line[k].getEndX() && line2.getStartY()==line[k].getEndY() && line2.getEndX()==line[k].getStartX() && line2.getEndY()==line[k].getStartY()){
                        
                        flag6 = 1 ;
                        break ; 
                    }
                }
                
                if(flag6 == 0 ){
                    pt1 = ptt1 ;
                    pt2 = ptt2 ;
                }
        
    }
    
    public void moreAdvancedStep(){
        for(int i=0 ; i<l ; i++){
            for(int j=0 ; j<l ; j++){
                if(line[i].getEndX()==line[j].getStartX() && line[i].getEndY()==line[j].getStartY() && line[i].getStartX()!=line[j].getEndX() && line[i].getStartY()!=line[j].getEndY()){
                    
                        if(line[j].getEndX()==line2.getStartX() && line[j].getEndY()==line2.getStartY() && (line[i].getStartX()==line2.getEndX() || line[i].getStartY()==line2.getEndY()) && line[j].getStartX()!=line2.getEndX() && line[j].getStartY()!=line2.getEndY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                        
                        else if(line[j].getEndX()==line2.getEndX() && line[j].getEndY()==line2.getEndY() && (line[i].getStartX()==line2.getStartX() || line[i].getStartY()==line2.getStartY()) && line[j].getStartX()!=line2.getStartX() && line[j].getStartY()!=line2.getStartY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                   
                }
                
                else if(line[i].getEndX()==line[j].getEndX() && line[i].getEndY()==line[j].getEndY() && line[i].getStartX()!=line[j].getStartX() && line[i].getStartY()!=line[j].getStartY()){
                    
                        if(line[j].getStartX()==line2.getStartX() && line[j].getStartY()==line2.getStartY() && (line[i].getStartX()==line2.getEndX() || line[i].getStartY()==line2.getEndY()) && line[j].getEndX()!=line2.getEndX() && line[j].getEndY()!=line2.getEndY() ){
                            
                            flag7 = 1 ;
                            return ;
                        }
                        
                        else if(line[j].getStartX()==line2.getEndX() && line[j].getStartY()==line2.getEndY() && (line[i].getStartX()==line2.getStartX() || line[i].getStartY()==line2.getStartY())&& line[j].getEndX()!=line2.getStartX() && line[j].getEndY()!=line2.getStartY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                   
                }
                
                else if(line[i].getStartX()==line[j].getStartX() && line[i].getStartY()==line[j].getStartY() && line[i].getEndX()!=line[j].getEndX() && line[i].getEndY()!=line[j].getEndY()){
                    
                        if(line[j].getEndX()==line2.getStartX() && line[j].getEndY()==line2.getStartY() && (line[i].getEndX()==line2.getEndX() || line[i].getEndY()==line2.getEndY()) && line[j].getStartX()!=line2.getEndX() && line[j].getStartY()!=line2.getEndY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                        
                        else if(line[j].getEndX()==line2.getEndX() && line[j].getEndY()==line2.getEndY() && (line[i].getEndX()==line2.getStartX() || line[i].getEndY()==line2.getStartY()) && line[j].getStartX()!=line2.getStartX() && line[j].getStartY()!=line2.getStartY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                    
                }
                
                else if(line[i].getStartX()==line[j].getEndX() && line[i].getStartY()==line[j].getEndY() && line[i].getEndX()!=line[j].getStartX() && line[i].getEndY()!=line[j].getStartY()){
                    
                        if(line[j].getStartX()==line2.getStartX() && line[j].getStartY()==line2.getStartY() && (line[i].getEndX()==line2.getEndX() || line[i].getEndY()==line2.getEndY()) && line[j].getEndX()!=line2.getEndX() && line[j].getEndY()!=line2.getEndY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                        
                        else if(line[j].getStartX()==line2.getEndX() && line[j].getStartY()==line2.getEndY() && (line[i].getEndX()==line2.getStartX() || line[i].getEndY()==line2.getStartY()) && line[j].getEndX()!=line2.getStartX() && line[j].getEndY()!=line2.getStartY()){
                            
                            flag7 = 1 ;
                            return ;
                        }
                    
                }
                
                else if(line2.getStartX()==line[i].getStartX() && line2.getEndX()==line[j].getStartX() && line2.getStartY()==line[i].getStartY() && line2.getEndY()==line[j].getStartY() && (line[i].getEndX()==line[j].getEndX() || line[i].getEndY()==line[j].getEndY())){
                
                            flag7 = 1 ;
                            return ;
                }
                
                else if(line2.getStartX()==line[i].getEndX() && line2.getEndX()==line[j].getEndX() && line2.getStartY()==line[i].getEndY() && line2.getEndY()==line[j].getEndY() && (line[i].getStartX()==line[j].getStartX() || line[i].getStartY()==line[j].getStartY())){
                            
                            flag7 = 1 ;
                            return ;
                }
                
                else if(line2.getStartX()==line[i].getStartX() && line2.getEndX()==line[j].getEndX() && line2.getStartY()==line[i].getStartY() && line2.getEndY()==line[j].getEndY() && (line[i].getEndX()==line[j].getStartX() || line[i].getEndY()==line[j].getStartY())){
                            
                            flag7 = 1 ;
                            return ;
                }
                
                else if(line2.getStartX()==line[i].getEndX() && line2.getEndX()==line[j].getStartX() && line2.getStartY()==line[i].getEndY() && line2.getEndY()==line[j].getStartY() && (line[i].getStartX()==line[j].getEndX() || line[i].getStartY()==line[j].getEndY())){
                            
                            flag7 = 1 ;
                            return ;
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
            btn.setText("RI Turn");
            player = 2 ;
        }
        else if(player == 2){
            btn.setText("Your Turn");
            player = 1 ;
        }
        
        try{      
            writer = new BufferedWriter(new FileWriter(file3));
            writer.write(player +"");
            writer.close();

        }catch(Exception e){
            System.out.println("Exception : " + e);
        }
        //System.out.println("kaj Hochena keno re vai2" );
    }
    
    public void setPointName(double x,double y){
        
        if(player==1 && checkDouble != 1) player = 2 ;
        else if(player==2 && checkDouble !=1) player = 1 ;
        
        Label label = new Label() ;
      
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
            label.setText("R");
            label.setStyle("-fx-background-color: DarkOrange") ;
            point2++ ;
            button[1].setText("RI Sujon : " + point2);
        }
        
        else {
            String firstLetter = "" ;
            firstLetter = String.valueOf(plrName1.charAt(0)) ;
            label.setText(firstLetter);
            label.setStyle("-fx-background-color: Aqua") ;
            point1++ ;
            button[0].setText(plrName1 +" : " + point1);
        }
        
        if(point1+point2==81){
            if(point1 > point2){
                label1.setText("YOU WIN") ;
                setScoreCard(plrName1, point1);
            }
            
            else if(point1 < point2){
                label1.setText("YOU LOSE") ;
            }
            
            else label1.setText("Draw Match") ;
            
            Congrats root = new Congrats(plrName1,point1,1) ;
            
            Pane pane = new Pane() ;
            Image image = new Image("/newpackage/kkk.png") ;
            ImageView view = new ImageView(image) ;
            pane.getChildren().addAll(view,root) ;
                
            Control.getStage().setScene(new Scene(pane)) ;
            
        }
        
        label.setAlignment(Pos.CENTER);
        
        getChildren().add(label) ;
    }
    
    public void readInputFromFileAndDraw(){
        
        try{
            reader = new BufferedReader(new FileReader(file1)) ;
            String str ;
            while((str=reader.readLine())!=null){
                double plr = Double.valueOf(str) ;
                double line1X = Double.valueOf(reader.readLine()) ;
                double line1Y = Double.valueOf(reader.readLine()) ;
                double line2X = Double.valueOf(reader.readLine()) ;
                double line2Y = Double.valueOf(reader.readLine()) ;
                
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
            btn.setText("Your turn");
            player = 1 ;
        }
        
        else if(plr==2.0){
            btn.setText("RI turn");
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
        
        if(plr==1.0) newLine.setStroke(Color.BLUE);
        else if(plr==2.0) newLine.setStroke(Color.RED);
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
            label.setStyle("-fx-background-color: Aqua") ;
            point1++ ;
            button[0].setText(plrName1 + " : " + point1);
        }
        
        else if(plr == 1){
            label.setText("R");
            label.setStyle("-fx-background-color: DarkOrange") ;
            point2++ ;
            button[1].setText("RI Sujon : " + point2);
            
        } 
        
        getChildren().add(label) ;
    }
    
    public void setScoreCard(String plrName,int point){
        
        SortedMap<Double,String> mymap = new TreeMap<Double,String>() ;
        double dm = point ;
        mymap.put( dm ,plrName ) ;
        int im=1 ;
        try{
            reader = new BufferedReader(new FileReader(file5)) ;
            String str ;
            while((str=reader.readLine())!=null){
                double d = Double.valueOf(str) ;
                String s = reader.readLine() ;
                if((int)d==(int)dm){
                    d = d + 0.01*im ;
                    System.out.println( d + "  " + s);
                }
                im++ ;
                mymap.put( d, s) ;
                System.out.println( d + s);
            }
            writer = new BufferedWriter(new FileWriter(file5)) ;
            writer.close();
            reader.close();
        }catch(Exception e){
            System.out.println("Exception and : " + e);
        }
        
        
        
        for(int i = 0 ; i<10 ; i++){
            try{
            writer = new BufferedWriter(new FileWriter(file5,true)) ;
            
            {
                double point1 = mymap.lastKey() ;
                System.out.println(point1);
                writer.write(point1 + "") ;
                writer.newLine() ;
                writer.write(mymap.get(point1)) ;
                writer.newLine();
                
                mymap.remove(point1) ;
                
                System.out.println(point1);
                
                writer.close();
            }
            
        }catch(Exception e){}
        }
    }
}