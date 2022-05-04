public class EpuzzleState extends SearchState {
    private int[][] puzzleState;
    
    public EpuzzleState(int[][] state, int lc, int erc, String meth){
        puzzleState = state;
        localCost = lc;
        estRemCost = erc;
        method = meth;
    }

    public int[][] getPuzzleState(){
        return puzzleState
    }

    public String getMethod(){
        return method;
    }

    // calculates manhatten distance
    public int manhattan(int[][] s, int[][] t) {
        int d = 0;
        int si = 0;
        int sj = 0;
  
        for(int n = 0; n <= 8; ++n) {
           int i;
           int j;
           for(i = 0; i <= 2; ++i) {
              for(j = 0; j <= 2; ++j) {
                 if (s[i][j] == n) {
                    si = i;
                    sj = j;
                 }
              }
           }
  
           for(i = 0; i <= 2; ++i) {
              for(j = 0; j <= 2; ++j) {
                 if (t[i][j] == n) {
                    d = d + Math.abs(i - si) + Math.abs(j - sj);
                 }
              }
           }
        }
  
        return d;
    }

    
    // calculates all the number of tiles out of place
    public int hammingDistance(int[][] state, int[][] target){
        int count = 0;

        for (int i = 0; i < 3; i++){
            for(int j = 0, j < 3, j++){
                if (state[i][j] != target[i][j]){
                    count ++;
                }
            }
        }
    }

    // getSuccessors
    ArrayList<SearchState> getSuccessors(Search searcher){
        // sets rules for each square

        EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
        EpuzzleState puzzle = (EpuzzleState) eSearcher.currentNode.getState();

        this.puzzleState = puzzle.getPuzzleState();
    
        ArrayList<EpuzzleState> openList = new ArrayList<EpuzzleState>(); // open list
        ArrayList<SearchState> closedList = new ArrayList<SearchState>(); // closed list

        // sets rules for each square

        if(getMethod().equals("manhatten")){
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(direction(), 1, manhattan(direction(), eSearcher.getTarget()), getMethod() )) // work this out
            }
        }
        else {
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(direction(), 1, hamming(direction(), eSearcher.getTarget()), getMethod() )) // work this out
            }
        }
        
        for (EpuzzleState openListValues : openList){
            closedList.add((SearchState) openListValues)
        }

        return closedList;
    }

    // ------------------------------------
	//		    SAME STATE?
	// ------------------------------------
    // comparing two arrays and seeing if they are equal
    // returns true if equal

    boolean sameState(SearchState n2){
        EpuzzleState stateObject = (EpuzzleState) n2;
        int[][] objectArray = stateObject.getPuzzleState(); // to compare to

        boolean checker = true;
        if(objectArray == stateObject){
            return checker;
        }

        for(int i = 0; i < stateObject.length; i++){
            for(int j = 0; j < objectArray.length; j++){
                if(stateObject[i][j] != objectArray[i][j]){
                    checker = false;
                    break;
                }
            }
        }
        return checker;
    }
    
}
