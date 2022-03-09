import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    protected Pieces[][] board;
    //Board board2 = new Board();
    public Board(){
        board = new Pieces[7][7];
    }
    Moves move = new Moves(18, 0,this);
    public Pieces[][] getBoard() {
        return board;
    }

    public void setBoard(Pieces[][] board) {
        this.board = board;
    }

    public void setUpBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if((i == 0 && j ==0)  || (i == 0 && j== 1) || (i==1 && j ==0) || (i == 1 && j == 6) ||
                        (i == 1 && j == 1) || (i == 0 && j == 5) || (i == 1 && j == 5) ||
                        (i == 0 && j == 6) || (i == 5 && j == 5) || (i == 5 && j ==0) ||
                        (i == 6 && j == 0) || (i ==5 && j ==1) || (i == 6 && j == 1) ||
                        (i == 5 && j == 6) || (i == 6 && j ==5) || (i == 6 && j == 6)){
                    board[i][j] = null;
                } else if(i == 3 & j == 3){
                    board[i][j] = new Pieces(-1);
                }else {
                    board [i][j] = new Pieces(0);
                }

            }
        }
    }
    public Pieces[][] savedState(){
        Pieces[][] savedLastMove = board;
        return savedLastMove;
    }

//    public boolean checkNextMoveDown(int i, int j) {
//        //boolean nextMove = false;
////        for (int i = 0; i < board.length; i++) {
////            for (int j = 0; j < board[i].length; j++) {
//        if(i + 2 < 7 && i + 1 < 7){
//                if (board[i][j]!=null && board[i+1][j] != null && board[i + 2][j] != null) {
//                    if (board[i][j].getCurrentState() == 0 && board[i + 1][j].getCurrentState() == 0
//                            && (board[i + 2][j].getCurrentState() == -1)) {
//                        board[i + 1][j].makeSkippable();
//                        //nextMove = true;
//                        return true;//nextMove;
//                    }}
//                }else{
//                        //nextMove = false;
//                        System.out.println("no possible move down");
//                    }
//
//
////        }
////
////    }
//        return false;
//
//
//    }
//
//    public boolean checkNextMoveUp(int i, int j) {
//        boolean nextMove = false;
////        for (int i = 0; i < board.length; i++) {
////            for (int j = 0; j < board[i].length; j++) {
//        if(i-2 >= 0 && i - 1 >= 0){
//         if (board[i][j]!=null&&board[i - 2][j] != null && board[i - 1][j] != null) {
//                if (board[i][j].getCurrentState() == 0 && board[i - 1][j].getCurrentState() == 0
//                        && (board[i - 2][j].getCurrentState() == -1)) {
//                    board[i + 1][j].makeSkippable();
//                    nextMove = true;
//                    return true;
//                }
//         }
//            }else{
//                //nextMove = false;
//                System.out.println("no possible move up");
//            }
//
//
//
////        }
////
////    }
//        return false;
//    }
//
//    public boolean checkNextMoveRight(int i, int j) {
//        boolean nextMove = false;
////        for (int i = 0; i < board.length; i++) {
////            for (int j = 0; j < board[i].length; j++) {
//        if(j+2 < 7 && j+1 < 7){
//        if (board[i][j] != null && board[i][j + 2] != null && board[i][j+1] != null) {
//            if (board[i][j].getCurrentState() == 0 && board[i][j+1].getCurrentState() == 0
//                    && (board[i][j+2].getCurrentState() == -1)) {
//                board[i + 1][j].makeSkippable();
//
//                return true;
//            }}
//        }else{
//            //nextMove = false;
//            System.out.println("no possible move right");
//        }
//
//
////        }
////
////    }
//        return false;
//
//
//    }
//
//    public boolean checkNextMoveLeft(int i, int j) {
//        boolean nextMove = false;
////        for (int i = 0; i < board.length; i++) {
////            for (int j = 0; j < board[i].length; j++) {
//       if(j-2 >= 0 && j - 1 >= 0){
//        if (board[i][j]!=null&&board[i][j - 2] != null && board[i][j - 1] != null) {
//
//            if (board[i][j].getCurrentState() == 0 && board[i][j-1].getCurrentState() == 0
//                    && (board[i][j-2].getCurrentState() == -1)) {
//                board[i + 1][j].makeSkippable();
//                nextMove = true;
//                return true;
//            }}
//        }else{
//            //nextMove = false;
//            System.out.println("no possible move left");
//
//        }
//        return false;
//
////        }
////
////    }
//
//
//    }
//
//    public boolean movePiece(int i, int j){
////        boolean rightChk;
////        boolean leftChk;
////        boolean upChk;
////        boolean dwnChk;
////        for (int i = 0; i < board.length; i++) {
////            for (int j = 0; j < board[i].length ; j++) {
////                rightChk =;
////                leftChk = checkNextMoveLeft(i,j);
////                upChk = checkNextMoveUp(i,j);
////                dwnChk = checkNextMoveDown(i,j);
//                if ( checkNextMoveRight(i,j)){
//                    board[i][j].removeFromPlay();
//                    board[i][j+1].removeFromPlay();
//                    board[i][j+2].resetPieceToNeutral();
//                    return true;
//                }else if(checkNextMoveLeft(i,j)){
//                    board[i][j].removeFromPlay();
//                    board[i][j-1].removeFromPlay();
//                    board[i][j-2].resetPieceToNeutral();
//                    return true;
//                }else if(checkNextMoveUp(i,j)){
//                board[i][j].removeFromPlay();
//                board[i-1][j].removeFromPlay();
//                board[i-2][j].resetPieceToNeutral();
//                    return true;
//                }else if(checkNextMoveDown(i,j)){
//                    board[i][j].removeFromPlay();
//                    board[i+1][j].removeFromPlay();
//                    board[i+2][j].resetPieceToNeutral();
//                    return true;
//                }else {
//                    System.out.println("no legal moves remain");
//                    //moveRemaining = false;
//                    return false;
//                }
////            }
////        }
//        //return moveRemaining;
//    }

public void firstMove(){
        int i,j;
        int state = ThreadLocalRandom.current().nextInt(0,4);
        if (state == 0){
            i = 1;
            j = 3;
            move.movePiece(board,i,j);
        }else if(state == 1){
            i = 3;
            j = 1;
            move.movePiece(board,i,j);
        }else if (state == 2){
            i = 3;
            j = 5;
            move.movePiece(board,i,j);
        }else if(state == 3){
            i = 5;
            j = 3;
            move.movePiece(board,i,j);
        }



//        checkNextMoveDown(i,j);
//        checkNextMoveRight(i,j);
//        checkNextMoveUp(i,j);
//        checkNextMoveLeft(i,j);
//        movePiece(i,j);
}

//public void randomMove(){
////    int i = ThreadLocalRandom.current().nextInt(0,7);
////    int j = ThreadLocalRandom.current().nextInt(0,7);
////        movePiece(i,j);
//
//}
//fork then have an object to hold the best move, then compare, look at check, if the moves
public void refreshBoard(){
    System.out.println("+---------------------+");
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
           if (board[i][j] == null){
               System.out.print("   ");
           }else if(board[i][j].getCurrentState() == 0 || board[i][j].getCurrentState() == 1){
               System.out.print(" . ");
           }else if (board[i][j].getCurrentState() == -1){
               System.out.print(" O ");
           }

        }
        System.out.println("");
    }
    System.out.println("+---------------------+");
}

    //could use counted completer
    //Fork join min max algorithm



}
