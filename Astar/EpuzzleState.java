public class EpuzzleState extends SearchState {
    public EpuzzleState(int state){
        puzzleState = state;
        localCost = lc;
        estRemCost = erc;
    }

    public getPuzzleState(){
        return puzzleState
    }

    public getLocalCost(){
        return localCost
    }

    public getEstRemCost(){
        return estRemCost
    }

}
