import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class Moves extends RecursiveTask {
//take in a pieces array, make array.copyof or system.arrays.copy
    //also use a maxDepth variable
    //end of recursion if there are no moves
    //if !checkMove stop recursion by not calling fork (two returns) ->
    //or if out of maxDepth with a preset
    //tip for splitting -> send the
    int maxDepth;

    Pieces[][] board;
    protected Board original;
    protected Board copyB;
    final int currentDepth;


    public Moves(int maxDepth, int currentDepth, Board original){
    this.original = original;
    this.maxDepth = maxDepth;
    this.currentDepth = currentDepth;
    }
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Pieces[][] updateCopy(Pieces[][] copy){
        //copy = new Pieces[7][7];
        for(int i = 0; i < 7; i++)
        {
            Pieces[] inner = copy[i];
            int innerLength = inner.length;
            copy[i] = new Pieces[innerLength];
            System.arraycopy(inner, 0, copy[i], 0, innerLength);
        }
        return copy;
    }

    public void setBoard(Board bored) {
        this.original = bored;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }


    public boolean checkNextMoveDown(Pieces[][] board, int i, int j) {
        //boolean nextMove = false;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
        if(i + 2 < 7 && i + 1 < 7){
            if (board[i][j]!=null && board[i+1][j] != null && board[i + 2][j] != null) {
                if (board[i][j].getCurrentState() == 0 && board[i + 1][j].getCurrentState() == 0
                        && (board[i + 2][j].getCurrentState() == -1)) {
                    board[i + 1][j].makeSkippable();
                    //nextMove = true;
                    return true;//nextMove;
                }}
        }else{
            //nextMove = false;
            //System.out.println("no possible move down");
            return false;
        }


//        }
//
//    }
        return false;


    }

    public boolean checkNextMoveUp(Pieces[][] board, int i, int j) {
        //boolean nextMove = false;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
        if(i-2 >= 0 && i - 1 >= 0){
            if (board[i][j]!=null&&board[i - 2][j] != null && board[i - 1][j] != null) {
                if (board[i][j].getCurrentState() == 0 && board[i - 1][j].getCurrentState() == 0
                        && (board[i - 2][j].getCurrentState() == -1)) {
                    board[i -1][j].makeSkippable();
                   // nextMove = true;
                    return true;
                }
            }
        }else{
            //nextMove = false;
            //System.out.println("no possible move up");
            return false;
        }



//        }
//
//    }
        return false;
    }

    public boolean checkNextMoveRight(Pieces[][] board, int i, int j) {
        boolean nextMove = false;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
        if(j+2 < 7 && j+1 < 7){
            if (board[i][j] != null && board[i][j + 2] != null && board[i][j+1] != null) {
                if (board[i][j].getCurrentState() == 0 && board[i][j+1].getCurrentState() == 0
                        && (board[i][j+2].getCurrentState() == -1)) {
                    board[i][j+2].makeSkippable();

                    return true;
                }}
        }else{
            //nextMove = false;
           // System.out.println("no possible move right");
            return false;
        }


//        }
//
//    }
        return false;


    }

    public boolean checkNextMoveLeft(Pieces[][] board, int i, int j) {
        boolean nextMove = false;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
        if(j-2 >= 0 && j - 1 >= 0){
            if (board[i][j]!=null&&board[i][j - 2] != null && board[i][j - 1] != null) {

                if (board[i][j].getCurrentState() == 0 && board[i][j-1].getCurrentState() == 0
                        && (board[i][j-2].getCurrentState() == -1)) {
                    board[i][j-1].makeSkippable();
                    nextMove = true;
                    return true;
                }}
        }else{
            //nextMove = false;
          // System.out.println("no possible move left");
        return false;
        }
        return false;

//        }
//
//    }


    }

    public boolean movePiece(Pieces[][] board, int i, int j){

        if (checkNextMoveRight(board,i,j)){
            board[i][j].removeFromPlay();
            board[i][j+1].removeFromPlay();
            board[i][j+2].resetPieceToNeutral();
            return true;
        }else if(checkNextMoveLeft(board,i,j)){
            board[i][j].removeFromPlay();
            board[i][j-1].removeFromPlay();
            board[i][j-2].resetPieceToNeutral();
            return true;
        }else if(checkNextMoveUp(board,i,j)){
            board[i][j].removeFromPlay();
            board[i-1][j].removeFromPlay();
            board[i-2][j].resetPieceToNeutral();
            return true;
        }else if(checkNextMoveDown(board,i,j)){
            board[i][j].removeFromPlay();
            board[i+1][j].removeFromPlay();
            board[i+2][j].resetPieceToNeutral();
            return true;
        }else {
           // System.out.println("no legal moves remain");
            //moveRemaining = false;
            return false;
        }
//            }
//        }
        //return moveRemaining;
    }

    public boolean randomMove(Pieces[][] board){

        for (int i = 0 ; i < board.length-1 ; i++) {
            for (int j= 0; j< board[i].length - 1; j++){
                if(i < 2 && j < 2){
                    j = 2;
                }else if ( i > 5 && j < 2){
                  j = 2;
                } else if (i < 2 && j > 5) {
                    i = 3;
                } else if (movePiece(board, i, j)){
                    return true;

                }else if (i > 5 && j < 5){
                    return false;
                }
            }

        }
       // while(!(checkNextMoveRight(board,i,j)||checkNextMoveLeft(board,i,j)||checkNextMoveUp(board,i,j)||checkNextMoveDown(board,i,j))){

//            i = ThreadLocalRandom.current().nextInt(0,7);
//            j = ThreadLocalRandom.current().nextInt(0,7);
//

        //}



        return false;

    }
    //take in a pieces array, make array.copyof or system.arrays.copy
    //also use a maxDepth variable
    //end of recursion if there are no moves
    //if !checkMove stop recursion by not calling fork (two returns) ->
    //or if out of maxDepth with a preset
    //tip for splitting -> send the
    @Override
    protected Moves compute() {

        Moves movement = new Moves(maxDepth, +1, original);


            if (movement.randomMove(original.board)) {
                Board forkBoard = new Board();
                forkBoard.board = movement.updateCopy(original.getBoard());
                forkBoard.refreshBoard();
                movement.setBoard(forkBoard);
                System.out.println("fork");
               // System.out.println(getCurrentDepth());
                movement.fork();
                join();
            } else {
                System.out.println("no moves available");
                movement.join();

            }

        return movement.compute();
    }
}
