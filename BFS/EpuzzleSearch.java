public class EpuzzleSearch extends Search{
    private int[][] puzzleState;
    private int[][] target;
    
    public EpuzzleSearch (int[][] state, int[][] tar){
        puzzleState = state;
        target = tar;
    }

    public int getPuzzleState(){
        return puzzleState;
    }

    public int getTarget(){
        return target;
    }
}
