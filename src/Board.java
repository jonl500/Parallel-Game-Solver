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
// sets up a board for a game of peg solitaire.
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
}


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
