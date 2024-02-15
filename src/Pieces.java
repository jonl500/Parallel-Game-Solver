public class Pieces {
    private int currentState;
    private final int neutralState = 0;
   private final int skippable = 1;
    private final int outOfPlay = -1;
    private boolean isInPlay;

    public Pieces(int neutralState){
        this.currentState = neutralState;
        this.isInPlay = true;
    }


    public int getCurrentState() {
        return currentState;
    }

    public void resetPieceToNeutral() {
        currentState = 0;
    }

    public void makeSkippable(){
        currentState = skippable;
    }


    public int removeFromPlay(){
        isInPlay = false;
        currentState = outOfPlay;
        return currentState;
    }
}
