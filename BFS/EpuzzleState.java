import java.util.ArrayList;

public class EpuzzleState extends SearchState {
    private int[][] puzzleState;
    
    public EpuzzleState(int[][] state){
        puzzleState = state;
    }

    public int[][] getPuzzleState(){
        return puzzleState
    }

    // ------------------------------------
	//		    GOAL PREDICATE
	// ------------------------------------
    // checks if the current puzzle is equal to the target puzzle
    // @param searcher - the current search
    // @return true/false - true if arrays are equal

    public boolean goalPredicate(Search searcher) {
        EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
        int[][] tar = eSearcher.getTarget();
        if(Arrays.deepEquals(puzzleState, tar) == true){ // deepEquals() checks if two arrays are equal
            return true;
        } else{
            return false;
        }
    }

    // GRID VISUALISATION:
    // -----------------------

    //    0  1  2
    // 0 [0][0][0]
    // 1 [0][0][0]
    // 2 [0][0][0]        


    ArrayList<SearchState> getSuccessors(Search searcher){
        // sets rules for each square

        EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
        EpuzzleState puzzle = (EpuzzleState) eSearcher.currentNode.getState();

        this.puzzleState = puzzle.getPuzzleState();
    
        ArrayList<EpuzzleState> openList = new ArrayList<EpuzzleState>(); // open list
        ArrayList<SearchState> closedList = new ArrayList<SearchState>(); // closed list

        // sets rules for each square
        if(puzzleState[0][0] == 0){
            openList.add(new EpuzzleState()) // work this out
        }
        if(puzzleState[0][1] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[0][2] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[1][0] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[1][1] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[1][2] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[2][0] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[2][1] == 0){
            openList.add(new EpuzzleState())
        }
        if(puzzleState[2][2] == 0){
            openList.add(new EpuzzleState())
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
    // @param n2 - the current search
    // @return true/false - true if equal

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


    public EpuzzleState moveUp(int[][] state, int column, int row) {
        int newRow = row - 1;
        int[][] returnPuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            returnPuzzle[i] =  state[i].clone();
        }

        int temp = puzzleState[newRow][column];
        puzzleState[newRow][column] = puzzleState[row][column];
        puzzleState[row][column] = temp;
        
        return new EPuzzleState(puzzleState, 1, 1);
    } 
}
