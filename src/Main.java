//The pegboard gets called in main and the moves object handles the parallel fork-joins
public class Main {
    public static void main(String[] args) {
        int depth = 18;
        Board pegSolitaireCopyA = new Board();
        Board pegSolitaireCopyB = new Board();
        pegSolitaireCopyA.setUpBoard();

        pegSolitaireCopyA.refreshBoard();
        pegSolitaireCopyA.firstMove();
        Moves moves = new Moves(depth,0,pegSolitaireCopyA);
        moves.setMaxDepth(depth);
        pegSolitaireCopyA.refreshBoard();
        moves.compute();
    }
}
