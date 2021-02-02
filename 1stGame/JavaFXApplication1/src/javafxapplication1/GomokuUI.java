package javafxapplication1;

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

import java.util.ArrayList;
import java.util.Scanner;

public class GomokuUI extends AnchorPane {
    Circle[] mainCircle = new Circle[100] ;
    Button btn = new Button() ;
    Button [] button = new Button[10] ;
    Label label1 = new Label() ;

    String plrName1= "HUMAN" , plrName2 = "The Trio AI" ;

    int[][] gameBoard  ;
    String turn = "human";
    int plusInfinity = 200, minusInfinity = -200 ;

    int row = 10 ;
    int column = 10 ;

    int gamePoint = 5 ;
    int terminalValue = 0 ;

    int overallDepth =  8;

    int range = 2 ;

    ArrayList<ArrayList<Integer>> mainBoardAvailableNodeList;

    GomokuUI(){
        gameBoard = new int[row][column] ;
        mainBoardAvailableNodeList = new ArrayList<>() ;

        for(int i=0; i<row ;i++){
            for(int j=0; j<column ;j++){
                gameBoard[i][j] = 0 ;
            }
        }

        managingUnit();
        platformCircle();
        getClickPoint();
    }

    public void managingUnit(){
        setStyle("-fx-background: AliceBlue");
        btn.setText(plrName1 + "'s Turn");
        btn.setLayoutX(710);
        btn.setLayoutY(150);
        btn.setMinSize(400, 80);
        btn.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 40));
        btn.setStyle("-fx-background-color: LightGreen");
        btn.setTextFill(Color.CHOCOLATE);
        button[0] = new Button(plrName1 + "  " ) ;
        button[1] = new Button(plrName2 + "  " ) ;
        button[0].setLayoutX(760);
        button[0].setLayoutY(300);
        button[1].setLayoutX(760);
        button[1].setLayoutY(400);
        button[0].setShape(new Circle(10));
        button[1].setShape(new Circle(10));
        button[0].setMinSize(300,50) ;
        button[1].setMinSize(300,50);
        //button[0].setStyle("-fx-background-color: DarkOrange");
        button[0].setStyle("-fx-background-color: Red");
        button[1].setStyle("-fx-background-color: Black");
        button[0].setTextFill(Color.WHITE);
        button[1].setTextFill(Color.WHITE);
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
            OpeningMenu root = new OpeningMenu(1) ;
            //root.setOption(option);
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
        label1.setText("WelCome To GOMOKU PROJECT") ;

        getChildren().addAll(btn,button[0],button[1],label1,button[2]) ;
    }

    public void platformCircle(){

        for(int i=0 ; i<100 ; i++)
        {
            mainCircle[i] = new Circle() ;
        }

        int y=0 ;
        for(int i=0 ; i<10 ;i++){
            y = y+60 ;
            int x = 50 ;
            for(int j=0 ; j<10 ; j++){
                x = x+60 ;
                mainCircle[i*10+j].setCenterX(x);
                mainCircle[i*10+j].setCenterY(y);
                mainCircle[i*10+j].setFill(Color.BEIGE);
                mainCircle[i*10+j].setRadius(20);
                mainCircle[i*10+j].setStroke(Color.GREY);
            }
        }

        for(int i = 0 ; i < 100 ; i++ ){
            getChildren().add(mainCircle[i]);
        }
    }

    public void getClickPoint(){

        setOnMouseClicked(e->{
            int iValue = 0 , jValue = 0, flag = -1 ;
            for(int i=0 ; i<100 ; i++)
            {
                if(mainCircle[i].contains(e.getX(), e.getY())){
                    int pt1 = i ;

                    iValue = pt1/10 ;
                    jValue = pt1%10 ;

                    if(gameBoard[iValue][jValue]!=0){
                        continue ;
                    }

                    setPT1(pt1) ;
                    flag = 1;
                    //System.out.println(pt1 + "\t\t" + e.getX()+"hhh"+e.getY());
                    break ;
                }
            }
            if(flag==1){
                btn.setText("AI is thinking") ;
                drawCircle("human") ;
                gameClass(pt1/10, pt1%10) ;
            }
        });
    }

    int pt1 ;

    public void setPT1(int pt1){
        this.pt1 = pt1 ;
    }

    void drawCircle(String player){
        Circle c = new Circle(15) ;
        c.setCenterX(mainCircle[pt1].getCenterX());
        c.setCenterY(mainCircle[pt1].getCenterY());

        if(player=="AI") c.setFill(Color.RED);
        if(player!="AI") c.setFill(Color.BLACK);

        getChildren().add(c) ;
    }

    void gameClass(int iValue, int jValue){
        turn = "human" ;
        for(int loop = 0 ; loop<2 ; loop++){
            if(turn=="human"){

                gameBoard[iValue][jValue] = 6 ;

                mainBoardAvailableNodeList.clear();
                mainBoardAvailableNodeList = findAvailableNodes(gameBoard, "main") ;
                //System.out.println(mainBoardAvailableNodeList.size());
                if(mainBoardAvailableNodeList.size()==0){
                    mainBoardAvailableNodeList = findAvailableNodes(gameBoard, "main2") ;

                    if(mainBoardAvailableNodeList.size()==0){
                        //System.out.println("DRAW................Game Over..................");
                        label1.setText("GAME OVER");
                        btn.setText("MATCH DRAWN");
                        return;
                    }
                    if(mainBoardAvailableNodeList.size()>=15 && mainBoardAvailableNodeList.size()<20){
                        overallDepth = 7 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=20 && mainBoardAvailableNodeList.size()<30){
                        overallDepth = 6 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=30 && mainBoardAvailableNodeList.size()<35){
                        overallDepth = 6 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=35 && mainBoardAvailableNodeList.size()<40){
                        overallDepth = 5 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=40  && mainBoardAvailableNodeList.size()<45){
                        overallDepth = 5 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=45 && mainBoardAvailableNodeList.size()<50){
                        overallDepth = 5 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=50 && mainBoardAvailableNodeList.size()<55){
                        overallDepth = 4 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=50 && mainBoardAvailableNodeList.size()<55){
                        overallDepth = 4 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=55 && mainBoardAvailableNodeList.size()<60){
                        overallDepth = 4 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=60 && mainBoardAvailableNodeList.size()<65){
                        overallDepth = 3 ;
                    }
                    else if(mainBoardAvailableNodeList.size()>=65){
                        overallDepth = 3 ;
                    }
                }
                System.out.println(mainBoardAvailableNodeList.size());
            }
            else{
                mini_max(gameBoard, 0, "AI", minusInfinity, plusInfinity) ;
            }

            if (gameOver(gameBoard, "main")){

                for(int i=0; i<row ;i++){
                    for(int j=0; j<column ;j++){
                        gameBoard[i][j] = -1 ;
                    }
                }

                label1.setText("GAME OVER");
                if(turn=="human")
                    btn.setText(plrName1 + " WON");

                else btn.setText(plrName1 + " LOST");


                //break;
                System.out.println("GameOver");
                return;
            }

            if(turn == "human"){
                turn = "AI" ;
            }
            else{
                turn = "human" ;
            }
        }

        btn.setText(plrName1 + "'s Turn");
    }

    int setTerminalValue(int human_Count, int AI_Count, String player, int flag){
        if(flag==1){
            if(player=="human"){
                if(AI_Count>terminalValue){
                    terminalValue = AI_Count ;
                }
            }
            else if(player=="AI"){
                if(human_Count>terminalValue){
                    terminalValue = human_Count ;
                }
            }
        }
        else if(flag==2){
            if(player=="human"){
                if(AI_Count>terminalValue){
                    terminalValue = AI_Count ;
                }
            }
        }
        else if (flag==3){
            if(player=="AI"){
                if(human_Count>terminalValue){
                    terminalValue = human_Count ;
                }
            }
        }

        return terminalValue ;
    }

    void drawLine(int startCircle, int endCircle, String player){
        Line line = new Line() ;
        line.setStartX(mainCircle[startCircle].getCenterX());
        line.setStartY(mainCircle[startCircle].getCenterY());
        line.setEndX(mainCircle[endCircle].getCenterX());
        line.setEndY(mainCircle[endCircle].getCenterY());

        line.setStroke(Color.BLUE);
        line.setStrokeWidth(10);

        getChildren().add(line) ;
    }

    boolean gameOver(int [][] board, String player){
        int human_Count=0, AI_Count=0, terminalValue=0;
        for(int i=0; i<row ; i++){
            setTerminalValue(human_Count, AI_Count, player, 1) ;
            human_Count = 0;
            AI_Count = 0;
            for(int j=0; j<column ; j++){
                if (board[i][j]==0){
                    setTerminalValue(human_Count, AI_Count, player, 1) ;
                    human_Count = 0;
                    AI_Count = 0;
                    continue;
                }

                if(board[i][j]==6){
                    setTerminalValue(human_Count, AI_Count, player, 2) ;
                    human_Count++ ;
                    AI_Count = 0 ;
                }
                else if(board[i][j]==7){
                    setTerminalValue(human_Count, AI_Count, player, 3) ;
                    human_Count=0 ;
                    AI_Count++ ;
                }

                if(human_Count==gamePoint){
                    if(player=="main"){
                        drawLine(i*10+j-4, i*10+j, "human");
                        //System.out.println("Human Win");
                    }
                    return true;
                }
                else if(AI_Count == gamePoint){
                    if(player=="main"){
                        drawLine(i*10+j-4, i*10+j, "AI");
                        //System.out.println("AI Win2");
                    }
                    return true;
                }
            }
        }

        human_Count = 0;
        AI_Count = 0;
        for(int i=0; i<row ; i++){
            setTerminalValue(human_Count, AI_Count, player, 1) ;
            human_Count = 0;
            AI_Count = 0;
            for(int j=0; j<column ; j++){
                if (board[j][i]==0){
                    setTerminalValue(human_Count, AI_Count, player, 1) ;
                    human_Count = 0;
                    AI_Count = 0;
                    continue;
                }

                if(board[j][i]==6){
                    setTerminalValue(human_Count, AI_Count, player, 2) ;
                    human_Count++ ;
                    AI_Count = 0 ;
                }
                else if(board[j][i]==7){
                    setTerminalValue(human_Count, AI_Count, player, 3) ;
                    human_Count=0 ;
                    AI_Count++ ;
                }

                if(human_Count == gamePoint){
                    if(player=="main"){
                        //System.out.println("Human Win");
                        drawLine(j*10+i-4, j*10+i, "human");
                    }
                    return true;
                }
                else if(AI_Count == gamePoint){
                    if(player=="main"){
                        drawLine(j*10+i-4, j*10+i, "AI");
                        //System.out.println("AI Win2");
                    }
                    return true;
                }
            }
        }

        int i = 0, j = 0;
        boolean isUp = true;
        int flag = 1 ;

        for (int k = 0; k < row * column && flag==1;) {
            setTerminalValue(human_Count, AI_Count, player, 1) ;
            human_Count = 0;
            AI_Count = 0;
            if (isUp) {
                if(player=="main"){
                    //System.out.println("iiiiiiiiiiiiiiii222222:" + i + "\tjjjjjjjjjjjjjjjj22222222:"+ j);
                }
                for (; i >= 0 && j < column && i<row; j++, i--) {
                    if (board[i][j]==0){
                        setTerminalValue(human_Count, AI_Count, player, 1) ;
                        human_Count = 0;
                        AI_Count = 0;
                        if(i>=row-1&&j>=column-1) {
                            flag = -1;
                            break;
                        }
                        continue;
                    }

                    if(board[i][j]==6){
                        setTerminalValue(human_Count, AI_Count, player, 2) ;
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[i][j]==7){
                        setTerminalValue(human_Count, AI_Count, player, 3) ;
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    if(human_Count==gamePoint){
                        if(player=="main"){
                            //System.out.println("Human Win");
                            drawLine((i+4)*10+j-4, i*10+j, "human");
                        }
                        return true;
                    }
                    else if(AI_Count == gamePoint){
                        if(player=="main"){
                            drawLine((i+4)*10+j-4, i*10+j, "AI");
                            //System.out.println("AI Win2");
                        }
                        return true;
                    }

                    k++;

                    if(i>=row-1&&j>=column-1) {
                        flag = -1;
                        break;
                    }
                }

                if (i < 0 && j <= column - 1)
                    i = 0;
                if (j == column) {
                    i = i + 2;
                    j--;
                }
            }
            else {
                if(player=="main"){
                    //System.out.println("iiiiiiiiiiiiiiiii3333:" + i + "\tjjjjjjjjjjjjjjjjj3333:"+ j);
                }
                for (; j >= 0 && i < row && j<column; i++, j--) {

                    if (board[i][j]==0){
                        setTerminalValue(human_Count, AI_Count, player, 1) ;
                        human_Count = 0;
                        AI_Count = 0;
                        if(i>=row-1&&j>=column-1) {
                            flag = -1;
                            break;
                        }
                        continue;
                    }

                    if(board[i][j]==6){
                        setTerminalValue(human_Count, AI_Count, player, 2) ;
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[i][j]==7){
                        setTerminalValue(human_Count, AI_Count, player, 3) ;
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    if(human_Count==gamePoint){
                        if(player=="main"){
                            drawLine((i-4)*10+j+4, i*10+j, "AI");
                            //System.out.println("Human Win");
                        }
                        return true;
                    }
                    else if(AI_Count == gamePoint){
                        if(player=="main") {
                            drawLine((i-4)*10+j+4, i*10+j, "Human");
                            //System.out.println("AI Win2");
                        }
                        return true;
                    }

                    k++;

                    if(i>=row-1&&j>=column-1) {
                        flag = -1;
                        break;
                    }
                }

                if (j < 0 && i <= row - 1)
                    j = 0;
                if (i == row) {
                    j = j + 2;
                    i--;
                }
            }
            isUp = !isUp;
        }

        i = 0;
        j = column-1;
        isUp = true;
        flag = 1 ;

        for (int k = 0; k < row * column && flag==1;) {
            setTerminalValue(human_Count, AI_Count, player, 1) ;
            human_Count = 0;
            AI_Count = 0;

            if(j<=0 && i>=column-1){
                return false ;
            }
            if (isUp) {

                for (; i >= 0 && j >= 0 && i<row && j<column; j--, i--) {
                    if (board[i][j]==0){
                        setTerminalValue(human_Count, AI_Count, player, 1) ;
                        human_Count = 0;
                        AI_Count = 0;
//                        if(i>=row-1&&j<=0){
//                            flag = -1;
//                            break;
//                        }
                        continue;
                    }

                    if(board[i][j]==6){
                        setTerminalValue(human_Count, AI_Count, player, 2) ;
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[i][j]==7){
                        setTerminalValue(human_Count, AI_Count, player, 3) ;
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    if(human_Count==gamePoint){
                        if(player=="main"){
                            drawLine((i+4)*10+j+4, i*10+j, "AI");
                            //System.out.println("Human Win");
                        }
                        return true;
                    }
                    else if(AI_Count == gamePoint){
                        if(player=="main") {
                            drawLine((i+4)*10+j+4, i*10+j, "Human");
                            //System.out.println("AI Win2");
                        }
                        return true;
                    }

                    k++;

//                    if(i>=row-1&&j<=0) {
//                        flag = -1;
//                        break;
//                    }
                }

                if (i < 0 && j >=0)
                    i = 0;
                if (j == -1) {
                    i = i + 2;
                    j++;
                }
            }
            else {

                for (; j >= 0 && i < row && j<column; i++, j++) {
                    if (board[i][j]==0){
                        setTerminalValue(human_Count, AI_Count, player, 1) ;
                        human_Count = 0;
                        AI_Count = 0;
//                        if(i>=row-1&&j>=column-1) {
//                            flag = -1;
//                            break;
//                        }
                        continue;
                    }

                    if(board[i][j]==6){
                        setTerminalValue(human_Count, AI_Count, player, 2) ;
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[i][j]==7){
                        setTerminalValue(human_Count, AI_Count, player, 3) ;
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    if(human_Count==gamePoint){
                        if(player=="main"){
                            drawLine((i-4)*10+j-4, i*10+j, "AI");
                            //System.out.println("Human Win");
                        }
                        return true;
                    }
                    else if(AI_Count == gamePoint){
                        if(player=="main"){
                            drawLine((i-4)*10+j-4, i*10+j, "Human");
                            //System.out.println("AI Win2");
                        }
                        return true;
                    }

                    k++;

                }

                if (j >= column && i <= row - 1)
                    j = column-1;
                if (i == row) {
                    j = j - 2;
                    i--;
                }
            }
            isUp = !isUp;
        }

        return false;
    }

    int mini_max(int [][] board, int level, String player, int alpha, int beta){
        /*if(level==3){
            for(int i=0; i<row ; i++){
                for (int j=0; j<column ;j++){
                    System.out.print(board[i][j]  + " \t");
                }
                System.out.println();
            }
            System.out.println("depth:"+level+"\n");

        }*/

        if(gameOver(board, player)){
            if(player=="human") {
                //overallDepth = level;
                //System.out.println("AI WON, Hurrah, depth: " + level );
                //scanner.nextInt() ;
                return 10;
            }
            else if(player=="AI") {
                //System.out.println("Human WON, Hurrah, depth: " + level );
                return -10;
            }
        }

        if(level>overallDepth) {
            //System.out.println("hellloo....end");
            if(player=="AI"){
                return terminalValue  ;
            }
            else{
                return terminalValue*(-1);
            }
            //return 0 ;
        }

        ArrayList<ArrayList<Integer>> availableNodeList = findAvailableNodes(board, "recursion") ;
        if(availableNodeList.size()==0){
            //System.out.println("jjjjjjjjjjjhhhhhhhhhhhhhhhhhhhaaaaaaaaaaaaaaaaaammmmmmmmmmmmmmeeeeeeeeeeeeeeeelllllllllllaaaaaaaaaa");
            if(player=="AI"){
                return terminalValue  ;
            }
            else{
                return terminalValue*(-1);
            }
            //return 0 ;
        }


        if(player=="AI"){
            for(int i=0 ; i<availableNodeList.size() ; i++){
                int tempBoard[][] = new int[row][column] ;

                for(int k=0; k<row ; k++){
                    for (int l=0; l<column ;l++){
                        tempBoard[k][l] = board[k][l] ;
                    }
                }

                //if(level>=overallDepth) return 0 ;

                tempBoard[availableNodeList.get(i).get(0)][availableNodeList.get(i).get(1)] = 7  ;
                alpha = max(alpha, mini_max(tempBoard ,level+1, "human", alpha, beta)) ;
                if(alpha>=beta){
                    break;
                }
                if(level==0){
                    //System.out.println(i + "===========================================>>:"+ alpha);
                    availableNodeList.get(i).add(alpha) ;
                    //alpha = minusInfinity ;
                }
            }
            if(level==0){
                int index = 0 ;
                int max = availableNodeList.get(0).get(2) ;
                for(int i=1 ; i<availableNodeList.size() ; i++){
                    if(availableNodeList.get(i).get(2)>max){
                        max = availableNodeList.get(i).get(2) ;
                        index = i ;
                    }
                }
                if(level>=overallDepth) return 0 ;
                /*for(int m=0 ; m<availableNodeList.size(); m++){
                    System.out.println(availableNodeList.get(m).get(0) + "\t" + availableNodeList.get(m).get(1) + "\t" + availableNodeList.get(m).get(2));
                }*/
                gameBoard[availableNodeList.get(index).get(0)][availableNodeList.get(index).get(1)] = 7;
                setPT1(availableNodeList.get(index).get(0)*10+availableNodeList.get(index).get(1));
                drawCircle("AI") ;
            }

            //System.out.println("Level:" + level + "\tAlpha" + alpha + "\tBeta:" + beta);
            //scanner.nextInt();
            return alpha ;
        }
        else{
            for(int i=0 ; i<availableNodeList.size() ; i++){
                int tempBoard[][] = new int[row][column] ;

                for(int k=0; k<row ; k++){
                    for (int l=0; l<column ;l++){
                        tempBoard[k][l] = board[k][l] ;
                    }
                }
                tempBoard[availableNodeList.get(i).get(0)][availableNodeList.get(i).get(1)] = 6  ;
                int mini_max_return = mini_max(tempBoard, level+1, "AI", alpha, beta) ;
                beta = min(beta, mini_max_return) ;
                if(alpha>=beta){
                    //System.out.println("Alpha:"+alpha+"\nBeta:"+beta);
                    break;
                }
            }
            //System.out.println("/////////////////////////"+ beta);
            //System.out.println("LevelHumanOdd:" + level + "\tAlpha" + alpha + "\tBeta:" + beta);
            //scanner.nextInt();
            return beta ;
        }
    }

    ArrayList<ArrayList<Integer>> findAvailableNodes(int [][] board, String flagForArrayList){
        ArrayList<ArrayList<Integer>> availableNodeList = new ArrayList<>() ;
        for(int i=0 ; i<row ; i++){
            for (int j=0 ; j<column ; j++){
                if (board[i][j]==0){
                    ArrayList<Integer> arrayList = new ArrayList<>(3) ;
                    arrayList.add(i) ;
                    arrayList.add(j) ;

                    if(flagForArrayList!="main" && flagForArrayList!="main2"){
                        if(!mainBoardAvailableNodeList.contains(arrayList)) continue;
                    }

                    int importantPointFlag = -1 ;

                    if(flagForArrayList=="main"){
                        importantPointFlag = checkImportantAvailableNode(board, i, j) ;
                    }

                    if(flagForArrayList!="main"){
                        importantPointFlag = -1 ;
                        for(int k=i-range; k<=i+range ; k++){
                            for(int l=j-range; l<=j+range ; l++){
                                if(k==i&&l==j || k<0 || l<0 || k>=row || l>=column){
                                    continue;
                                }
                                if(board[k][l]!=0){
                                    importantPointFlag = 1 ;
                                    break;
                                }
                            }
                            if(importantPointFlag==1) break;
                        }
                    }

                    if(importantPointFlag==1){
                        availableNodeList.add(arrayList) ;
                    }
                }
            }
        }

        return availableNodeList ;
    }

    int checkImportantAvailableNode(int board[][], int i, int j){
        int leftAI = 0, leftHuman = 0, rightAI = 0, rightHuman = 0, topAI = 0 , topHuman = 0, bottomAI = 0, bottomHuman = 0 ;
        int topLeftAI = 0, topRightAI = 0, bottomLeftAI = 0, bottomRightAI = 0 , topLeftHuman = 0, topRightHuman = 0, bottomLeftHuman = 0, bottomRightHuman = 0 ;

        for(int k=j-1 ; k>=0 ; k--){
            if(k==j-1){
                if(board[i][k]==6){
                    leftHuman++ ;
                    continue;
                }
                else if(board[i][k]==7){
                    leftAI++ ;
                    continue;
                }
                else break;
            }
            else{
                if(leftHuman!=0 && board[i][k]==6)
                    leftHuman++ ;

                else if(leftAI!=0 && board[i][k]==7)
                    leftAI++ ;

                else break;
            }
        }

        for(int k=j+1 ; k<column ; k++){
            if(k==j+1){
                if(board[i][k]==6){
                    rightHuman++ ;
                    continue;
                }
                else if(board[i][k]==7){
                    rightAI++ ;
                    continue;
                }
                else break;
            }
            else{
                if(rightHuman!=0 && board[i][k]==6)
                    rightHuman++ ;

                else if(rightAI!=0 && board[i][k]==7)
                    rightAI++ ;

                else break;
            }
        }

        int importantPointFlag = -1 ;

        if(leftAI+rightAI>=4 && leftAI!=0 && rightAI!=0 || leftHuman+rightHuman>=4 && leftHuman!=0 && rightHuman!=0){
            importantPointFlag = 1 ;
            return importantPointFlag ;
        }
        else if(leftAI+rightAI>=3 && leftAI!=0 && rightAI!=0 && j-leftAI-1 >= 0 && j+rightAI+1<row){
            if(board[i][j-leftAI-1]==0 && board[i][j+rightAI+1]==0){
                importantPointFlag = 1 ;
                return importantPointFlag ;
            }
        }
        else if(leftHuman+rightHuman>=3 && leftHuman!=0 && rightHuman!=0 && j-leftHuman-1 >= 0 && j+rightHuman+1<row){
            if(board[i][j-leftHuman-1]==0 && board[i][j+rightHuman+1]==0){
                importantPointFlag = 1 ;
                return importantPointFlag ;
            }
        }
//        else if(leftAI+rightAI>=3 && leftAI!=0 && rightAI!=0 && board[i][j-leftAI-1]==0 && board[i][j+rightAI+1]==0
//                                                            && j-leftAI-1 >= 0 && j+rightAI+1<row
//                || leftHuman+rightHuman>=3 && leftHuman!=0 && rightHuman!=0 && board[i][j-leftHuman-1]==0 && board[i][j+rightHuman+1]==0
//                                                            && j-leftHuman-1 >= 0 && j+rightHuman+1<row){
//            importantPointFlag = 1 ;
//        }
        if(leftAI==4 || rightAI==4 || leftHuman==4 || rightHuman==4){
            importantPointFlag = 1 ;
        }
        else if(leftAI>=3 && rightAI==0 && rightHuman==0){
            if(leftAI==3 && j<column-1){
                importantPointFlag = 1 ;
            }
            else if(leftAI==4 && j<column){
                importantPointFlag = 1 ;
            }
        }
        else if(leftHuman>=3 && rightAI==0 && rightHuman==0){
            if(leftHuman==3 && j<column-1){
                importantPointFlag = 1 ;
            }
            else if(leftHuman==4 && j<column){
                importantPointFlag = 1 ;
            }
        }
        else if(rightAI>=3 && leftAI==0 && leftHuman==0){
            if(rightAI==3 && j>=1){
                importantPointFlag = 1 ;
            }
            else if(rightAI==4 && j>=0){
                importantPointFlag = 1 ;
            }
        }
        else if(rightHuman>=3 && leftAI==0 && leftHuman==0){
            if(rightHuman==3 && j>=1){
                importantPointFlag = 1 ;
            }
            else if(rightHuman==4 && j>=0){
                importantPointFlag = 1 ;
            }
        }

        if(importantPointFlag==-1){
            for(int k=i-1 ; k>=0 ; k--){
                if(k==i-1){
                    if(board[k][j]==6){
                        topHuman++ ;
                        continue;
                    }
                    else if(board[k][j]==7){
                        topAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(topHuman!=0 && board[k][j]==6)
                        topHuman++ ;

                    else if(topAI!=0 && board[k][j]==7)
                        topAI++ ;

                    else break;
                }
            }

            for(int k=i+1 ; k<row ; k++){
                if(k==i+1){
                    if(board[k][j]==6){
                        bottomHuman++ ;
                        continue;
                    }
                    else if(board[k][j]==7){
                        bottomAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(bottomHuman!=0 && board[k][j]==6)
                        bottomHuman++ ;

                    else if(bottomAI!=0 && board[k][j]==7)
                        bottomAI++ ;

                    else break;
                }
            }

            if(topAI+bottomAI>=4 && topAI!=0 && bottomAI!=0 || topHuman+bottomHuman>=4 && topHuman!=0 && bottomHuman!=0){
                importantPointFlag = 1 ;
                return importantPointFlag ;
            }
            else if(topAI+bottomAI>=3 && topAI!=0 && bottomAI!=0 && i-topAI-1 >= 0 && i+bottomAI+1<column){
                if(board[i-topAI-1][j]==0 && board[i+bottomAI+1][j]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
            else if(topHuman+bottomHuman>=3 && topHuman!=0 && bottomHuman!=0 && i-topHuman-1 >= 0 && i+bottomHuman+1<column){
                if(board[i-topHuman-1][j]==0 && board[i+bottomHuman+1][j]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
//            else if(topAI+bottomAI>=3 && topAI!=0 && bottomAI!=0 && board[i-topAI-1][j]==0 && board[i+bottomAI+1][j]==0
//                                                                && i-topAI-1 >= 0 && i+bottomAI+1<column
//                    || topHuman+bottomHuman>=3 && topHuman!=0 && bottomHuman!=0 && board[i-topHuman-1][j]==0 && board[i+bottomHuman+1][j]==0
//                                                                && i-topHuman-1 >= 0 && i+bottomHuman+1<column){
//                importantPointFlag = 1 ;
//            }
            if(topAI==4 || topHuman==4 || bottomAI==4 || bottomHuman==4){
                importantPointFlag = 1 ;
            }
            else if(topAI>=3 && bottomAI==0 && bottomHuman==0){
                if(topAI==3 && i<row-1){
                    importantPointFlag = 1 ;
                }
                else if(topAI==4 && i<row){
                    importantPointFlag = 1 ;
                }
            }
            else if(topHuman>=3 && bottomAI==0 && bottomHuman==0){
                if(topHuman==3 && i<row-1){
                    importantPointFlag = 1 ;
                }
                else if(topHuman==4 && i<row){
                    importantPointFlag = 1 ;
                }
            }
            else if(bottomAI>=3 && topAI==0 && topHuman==0){
                if(bottomAI==3 && i>=1){
                    importantPointFlag = 1 ;
                }
                else if(bottomAI==4 && i>=0){
                    importantPointFlag = 1 ;
                }

            }
            else if(bottomHuman>=3 && topAI==0 && topHuman==0){
                if(bottomHuman==3 && i>=1){
                    importantPointFlag = 1 ;
                }
                else if(bottomHuman==4 && i>=0){
                    importantPointFlag = 1 ;
                }
            }
        }

        if(importantPointFlag==-1){
            for(int k=i-1, l=j-1 ; k>=0 && l>=0 ; k--, l--){
                if(k==i-1 && l==j-1){
                    if(board[k][l]==6){
                        topLeftHuman++ ;
                        continue;
                    }
                    else if(board[k][l]==7){
                        topLeftAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(topLeftHuman!=0 && board[k][l]==6)
                        topLeftHuman++ ;

                    else if(topLeftAI!=0 && board[k][l]==7)
                        topLeftAI++ ;

                    else break;
                }
            }

            for(int k=i+1, l=j+1 ; k<row && l<column ; k++, l++){
                if(k==i+1 && l==j+1){
                    if(board[k][l]==6){
                        bottomRightHuman++ ;
                        continue;
                    }
                    else if(board[k][l]==7){
                        bottomRightAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(bottomRightHuman!=0 && board[k][l]==6)
                        bottomRightHuman++ ;

                    else if(bottomRightAI!=0 && board[k][l]==7)
                        bottomRightAI++ ;

                    else break;
                }
            }

            //System.out.println("value i:" + i + "\t\tvalue j:" + j + "\t\thumanTopLEft:" + topLeftHuman + "\t\tbotttomRight" + bottomRightHuman );

            if(topLeftAI+bottomRightAI>=4 && topLeftAI!=0 && bottomRightAI!=0 || topLeftHuman+bottomRightHuman>=4 && topLeftHuman!=0 && bottomRightHuman!=0){
                importantPointFlag = 1 ;
                return importantPointFlag ;
            }
            else if(topLeftAI+bottomRightAI>=3 && topLeftAI!=0 && bottomRightAI!=0 && i+bottomRightAI+1<column && j-topLeftAI-1>=0 && i-topLeftAI-1>=0 && j+bottomRightAI+1>row){
                if(board[i-topLeftAI-1][j-topLeftAI-1]==0 && board[i+bottomRightAI+1][j+bottomRightAI+1]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
            else if(topLeftHuman+bottomRightHuman>=3 && topLeftHuman!=0 && bottomRightHuman!=0 && i+bottomRightHuman+1<column && j-topLeftHuman-1>=0 && i-topLeftHuman-1>=0 && j+bottomRightHuman+1>row){
                if(board[i-topLeftHuman-1][j-topLeftHuman-1]==0 && board[i+bottomRightHuman+1][j+bottomRightHuman+1]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
//            else if(topLeftAI+bottomRightAI>=3 && topLeftAI!=0 && bottomRightAI!=0 && board[i-topLeftAI-1][j-topLeftAI-1]==0 && board[i+bottomRightAI+1][j+bottomRightAI+1]==0
//                                                                                && i+bottomRightAI+1<column && j-topLeftAI-1>=0 && i-topLeftAI-1>=0 && j+bottomRightAI+1>row
//                    || topLeftHuman+bottomRightHuman>=3 && topLeftHuman!=0 && bottomRightHuman!=0 && board[i-topLeftHuman-1][j-topLeftHuman-1]==0 && board[i+bottomRightHuman+1][j+bottomRightHuman+1]==0
//                                                                                && i+bottomRightHuman+1<column && j-topLeftHuman-1>=0 && i-topLeftHuman-1>=0 && j+bottomRightHuman+1>row){
//                importantPointFlag = 1 ;
//            }
            if(topLeftAI==4 || bottomRightAI==4 || topLeftHuman==4 || bottomRightHuman==4){
                importantPointFlag = 1 ;
            }
            else if(topLeftAI>=3 && bottomRightAI==0 && bottomRightHuman==0){
                if(topLeftAI==3 && j<column-1){
                    importantPointFlag = 1 ;
                }
                else if(topLeftAI==4 && j<column){
                    importantPointFlag = 1 ;
                }
            }
            else if(topLeftHuman>=3 && bottomRightAI==0 && bottomRightHuman==0){
                if(topLeftHuman==3 && j<column-1){
                    importantPointFlag = 1 ;
                }
                else if(topLeftHuman==4 && j<column){
                    importantPointFlag = 1 ;
                }
            }
            else if(bottomRightAI>=3 && topLeftAI==0 && topLeftHuman==0){
                if(bottomRightAI==3 && j>=1){
                    importantPointFlag = 1 ;
                }
                else if(bottomRightAI==4 && j>=0){
                    importantPointFlag = 1 ;
                }
            }
            else if(bottomRightHuman>=3 && topLeftAI==0 && topLeftHuman==0){
                if(bottomRightHuman==3 && j>=1){
                    importantPointFlag = 1 ;
                }
                else if(bottomRightHuman==4 && j>=0){
                    importantPointFlag = 1 ;
                }
            }
        }

        if(importantPointFlag==-1){
            for(int k=i+1, l=j-1 ; k<row && l>=0 ; k++, l--){
                if(k==i+1 && l==j-1){
                    if(board[k][l]==6){
                        bottomLeftHuman++ ;
                        continue;
                    }
                    else if(board[k][l]==7){
                        bottomLeftAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(bottomLeftHuman!=0 && board[k][l]==6)
                        bottomLeftHuman++ ;

                    else if(bottomLeftAI!=0 && board[k][l]==7)
                        bottomLeftAI++ ;

                    else break;
                }
            }

            for(int k=i-1, l=j+1 ; k>=0 && l<column ; k--, l++){
                if(k==i-1 && l==j+1){
                    if(board[k][l]==6){
                        topRightHuman++ ;
                        continue;
                    }
                    else if(board[k][l]==7){
                        topRightAI++ ;
                        continue;
                    }
                    else break;
                }
                else{
                    if(topRightHuman!=0 && board[k][l]==6) {
                        topRightHuman++;
                    }

                    else if(topRightAI!=0 && board[k][l]==7){
                        topRightAI++ ;
                    }

                    else break;
                }
            }

            if(bottomLeftAI+topRightAI>=4 && bottomLeftAI!=0 && topRightAI!=0){
                importantPointFlag = 1 ;
                return importantPointFlag ;
            }
            else if(bottomLeftAI+topRightAI>=3 && bottomLeftAI!=0 && topRightAI!=0 && i+bottomLeftAI+1<column && j-bottomLeftAI-1>=0 && i-topRightAI-1>=0 && j+topRightAI+1>row){
                if(board[i+bottomLeftAI+1][j-bottomLeftAI-1]==0 && board[i-topRightAI-1][j+topRightAI+1]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
            else if(bottomLeftHuman+topRightHuman>=4 && bottomLeftHuman!=0 && topRightHuman!=0 && i+bottomLeftHuman+1<column && j-bottomLeftHuman-1>=0 && i-topRightHuman-1>=0 && j+topRightHuman+1>row){
                if(board[i+bottomLeftHuman+1][j-bottomLeftHuman-1]==0 && board[i-topRightHuman-1][j+topRightHuman+1]==0){
                    importantPointFlag = 1 ;
                    return importantPointFlag ;
                }
            }
//            else if(bottomLeftAI+topRightAI>=3 && bottomLeftAI!=0 && topRightAI!=0 && board[i+bottomLeftAI+1][j-bottomLeftAI-1]==0 && board[i-topRightAI-1][j+topRightAI+1]==0
//                                                                                    && i+bottomLeftAI+1<column && j-bottomLeftAI-1>=0 && i-topRightAI-1>=0 && j+topRightAI+1>row
//                    || bottomLeftHuman+topRightHuman>=4 && bottomLeftHuman!=0 && topRightHuman!=0 && board[i+bottomLeftHuman+1][j-bottomLeftHuman-1]==0 && board[i-topRightHuman-1][j+topRightHuman+1]==0
//                                                                                    && i+bottomLeftHuman+1<column && j-bottomLeftHuman-1>=0 && i-topRightHuman-1>=0 && j+topRightHuman+1>row
//            ){
//                importantPointFlag = 1 ;
//            }
            if(bottomLeftAI==4 || topRightAI==4 || bottomLeftHuman==4 || topRightHuman==4){
                importantPointFlag = 1 ;
            }
            else if(bottomLeftAI>=3 && topRightAI==0 && topRightHuman==0){
                if(bottomLeftAI==3 && j<column-1){
                    importantPointFlag = 1 ;
                }
                else if(bottomLeftAI==4 && j<column){
                    importantPointFlag = 1 ;
                }
            }
            else if(bottomLeftHuman>=3 && topRightAI==0 && topRightHuman==0){
                if(bottomLeftHuman==3 && j<column-1){
                    importantPointFlag = 1 ;
                }
                else if(bottomLeftHuman==4 && j<column){
                    importantPointFlag = 1 ;
                }
            }
            else if(topRightAI>=3 && bottomLeftAI==0 && bottomLeftHuman==0){
                if(topRightAI==3 && j>=1){
                    importantPointFlag = 1 ;
                }
                else if(topRightAI==4 && j>=0){
                    importantPointFlag = 1 ;
                }
            }
            else if(topRightHuman>=3 && bottomLeftAI==0 && bottomLeftHuman==0){
                if(topRightHuman==3 && j>=1){
                    importantPointFlag = 1 ;
                }
                else if(topRightHuman==4 && j>=0){
                    importantPointFlag = 1 ;
                }
            }
        }

        return importantPointFlag ;
    }

    int min(int a, int b){
        if(a<b) return a ;
        else return b ;
    }

    int max(int a, int b){
        if(a>b) return a ;
        else return b ;
    }
}



