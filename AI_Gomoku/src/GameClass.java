import java.util.ArrayList;
import java.util.Scanner;

public class GameClass {
    int[][] gameBoard  ;
    String turn = "human";
    int plusInfinity = 200, minusInfinity = -200 ;
    int helloIndex ;
    Scanner scanner = new Scanner(System.in) ;

    int overallDepth = 81 ;

    GameClass(){
        gameBoard = new int[9][9] ;
        for(int i=0; i<9 ;i++){
            for(int j=0; j<9 ;j++){
                gameBoard[i][j] = 0 ;
            }
        }

        int []cell = new int[2];

        for(int i=0; i<9 ;i++){
            for(int j=0; j<9 ;j++){
                System.out.print(gameBoard[i][j]+ " "+ '\t') ;
            }
            System.out.println();
        }

        while(true){
            if(turn=="human"){
                System.out.println("Human's turn");
                cell[0] = scanner.nextInt();
                cell[1] = scanner.nextInt();

                gameBoard[cell[0]][cell[1]] = 6 ;

                turn = "AI" ;
            }
            else{
                mini_max(gameBoard, 0, "AI", minusInfinity, plusInfinity) ;

                turn = "human" ;
            }
            for(int i=0; i<9 ;i++){
                for(int j=0; j<9 ;j++){
                    System.out.print(gameBoard[i][j]+ " "+ '\t') ;
                }
                System.out.println();
            }

            if (gameOver(gameBoard)){
                break;
            }
        }
    }

    boolean gameOver(int [][] board){
        int human_Count=0, AI_Count=0;
        for(int i=0; i<9 ; i++){
            human_Count = 0;
            AI_Count = 0;
            for(int j=0; j<9 ; j++){
                if (board[i][j]==0){
                    human_Count = 0;
                    AI_Count = 0;
                    continue;
                }

                if(board[i][j]==6){
                    human_Count++ ;
                    AI_Count = 0 ;
                }
                else if(board[i][j]==9){
                    human_Count=0 ;
                    AI_Count++ ;
                }

                if(human_Count==5){
                    System.out.println("Human Win");
                    return true;
                }
                else if(AI_Count == 5){
                    System.out.println("AI Win2");
                    return true;
                }
            }
        }

        for(int i=0; i<9 ; i++){
            human_Count = 0;
            AI_Count = 0;
            for(int j=0; j<9 ; j++){
                if (board[i][j]==0){
                    human_Count = 0;
                    AI_Count = 0;
                    continue;
                }

                if(human_Count==5){
                    System.out.println("Human Win");
                    return true;
                }
                else if(AI_Count == 5){
                    System.out.println("AI Win2");
                    return true;
                }

                if(board[j][i]==6){
                    human_Count++ ;
                    AI_Count = 0 ;
                }
                else if(board[j][i]==9){
                    human_Count=0 ;
                    AI_Count++ ;
                }
            }
        }

        int i = 0, j = 0;
        boolean isUp = true;

        for (int k = 0; k < 9 * 9;) {
            human_Count = 0;
            AI_Count = 0;
            if (isUp) {
                for (; i >= 0 && j < 9 && i<9; j++, i--) {
                    //System.out.println(i + " " + j);

                    if (board[i][j]==0){
                        human_Count = 0;
                        AI_Count = 0;
                        if(i>=8&&j>=8) return false ;
                        continue;
                    }

                    if(human_Count==5){
                        System.out.println("Human Win");
                        return true;
                    }
                    else if(AI_Count == 5){
                        System.out.println("AI Win2");
                        return true;
                    }

                    if(board[j][i]==6){
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[j][i]==9){
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    k++;

                    if(i>=8&&j>=8) return false ;
                }

                if (i < 0 && j <= 9 - 1)
                    i = 0;
                if (j == 9) {
                    i = i + 2;
                    j--;
                }
            }
            else {
                for (; j >= 0 && i < 9 && j<9; i++, j--) {
                    //System.out.println(i + " hh" + j);

                    if (board[i][j]==0){
                        human_Count = 0;
                        AI_Count = 0;
                        if(i>=8&&j>=8) return false ;
                        continue;
                    }

                    if(human_Count==5){
                        System.out.println("Human Win");
                        return true;
                    }
                    else if(AI_Count == 5){
                        System.out.println("AI Win2");
                        return true;
                    }

                    if(board[j][i]==6){
                        human_Count++ ;
                        AI_Count = 0 ;
                    }
                    else if(board[j][i]==9){
                        human_Count=0 ;
                        AI_Count++ ;
                    }

                    k++;

                    if(i>=8&&j>=8) return false ;
                }

                if (j < 0 && i <= 9 - 1)
                    j = 0;
                if (i == 9) {
                    j = j + 2;
                    i--;
                }
            }
            isUp = !isUp;


        }

        return false;
    }

    int mini_max(int [][] board, int level, String player, int alpha, int beta){
        if(level>=overallDepth) return 0 ;

        for(int i=0; i<9 ; i++){
            for (int j=0; j<9 ;j++){
                System.out.print(board[i][j]  + " \t");
            }
            System.out.println();
        }


        System.out.println("\ndepth:"+level);

        //scanner.nextInt() ;
        if(gameOver(board)){
            if(player=="human") {
                overallDepth = level;
                System.out.println("AI WON, Hurrah, depth: " + level );
                scanner.nextInt() ;
                return 100-level;
            }
            else if(player=="AI") {
                System.out.println(player +"Human WON, Hurrah, depth: " + level );
                return level-100;
            }
        }

        ArrayList<ArrayList<Integer>> availableNodeList = findAvailableNodes(board) ;
        if(availableNodeList.size()==0){
            return 0;
        }


        if(player=="AI"){
            for(int i=0 ; i<availableNodeList.size() ; i++){
                int tempBoard[][] = new int[9][9] ;

                for(int k=0; k<9 ; k++){
                    for (int l=0; l<9 ;l++){
                        tempBoard[k][l] = board[k][l] ;
                    }
                }

                if(level>=overallDepth) return 0 ;

                tempBoard[availableNodeList.get(i).get(0)][availableNodeList.get(i).get(1)] = 9  ;
                alpha = max(alpha, mini_max(tempBoard ,level+1, "human", alpha, beta)) ;
                if(alpha>=beta){
                    break;
                }

                if(level==0){
                    scanner.nextInt();
                    availableNodeList.get(i).add(alpha) ;
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
                gameBoard[availableNodeList.get(index).get(0)][availableNodeList.get(index).get(1)] = 9;
            }
            return alpha ;
        }
        else{
            for(int i=0 ; i<availableNodeList.size() ; i++){
                int tempBoard[][] = new int[9][9] ;

                for(int k=0; k<9 ; k++){
                    for (int l=0; l<9 ;l++){
                        tempBoard[k][l] = board[k][l] ;
                    }
                }
                tempBoard[availableNodeList.get(i).get(0)][availableNodeList.get(i).get(1)] = 6  ;
                beta = min(beta, mini_max(tempBoard, level+1, "AI", alpha, beta)) ;
                if(alpha<=beta){
                    break;
                }
            }
            return beta ;
        }
    }

    ArrayList<ArrayList<Integer>> findAvailableNodes(int [][] board){
        ArrayList<ArrayList<Integer>> availableNodeList = new ArrayList<>() ;
        for(int i=0 ; i<9 ; i++){
            for (int j=0 ; j<9 ; j++){
                if (board[i][j]==0){
                    ArrayList<Integer> arrayList = new ArrayList<>(3) ;
                    arrayList.add(i) ;
                    arrayList.add(j) ;
                    availableNodeList.add(arrayList) ;
                }
            }
        }

        return availableNodeList ;
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

