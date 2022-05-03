public class EpuzzleSearch extends Search{
    public EpuzzleSearch (int state, int tar){
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
