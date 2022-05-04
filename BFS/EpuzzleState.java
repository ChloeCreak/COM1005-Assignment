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
    
    // [row][column]

    // ------------------------------------
	//		    GET SUCCESSORS
	// ------------------------------------
    // sets the rules for each square the current state can 
    // move to and adds them to the closed list
    // @param searcher - the current search
    // @return closed list

    ArrayList<SearchState> getSuccessors(Search searcher){
        // sets rules for each square

        EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
        EpuzzleState puzzle = (EpuzzleState) eSearcher.currentNode.getState();

        this.puzzleState = puzzle.getPuzzleState();
    
        ArrayList<EpuzzleState> openList = new ArrayList<EpuzzleState>(); // open list
        ArrayList<SearchState> closedList = new ArrayList<SearchState>(); // closed list

        // sets rules for each square
        if(puzzleState[0][0] == 0){
            openList.add(new EpuzzleState(moveRight(searcher, 0, 0)));
            openList.add(new EpuzzleState(moveDown(searcher, 0, 0)));
        }
        if(puzzleState[0][1] == 0){
            openList.add(new EpuzzleState(moveRight(searcher, 0, 1)));
            openList.add(new EpuzzleState(moveLeft(searcher, 0, 1)));
            openList.add(new EpuzzleState(moveDown(searcher, 0, 1)));
        }
        if(puzzleState[0][2] == 0){
            openList.add(new EpuzzleState(moveLeft(searcher, 0, 2)));
            openList.add(new EpuzzleState(moveDown(searcher, 0, 2)));
        }
        if(puzzleState[1][0] == 0){
            openList.add(new EpuzzleState(moveRight(searcher, 1, 0)));
            openList.add(new EpuzzleState(moveUp(searcher, 1, 0)));
            openList.add(new EpuzzleState(moveDown(searcher, 1, 0)));
        }
        if(puzzleState[1][1] == 0){
            openList.add(new EpuzzleState(moveRight(searcher, 1, 1)));
            openList.add(new EpuzzleState(moveLeft(searcher, 1, 1)));
            openList.add(new EpuzzleState(moveUp(searcher, 1, 1)));
            openList.add(new EpuzzleState(moveDown(searcher, 1, 1)));
        }
        if(puzzleState[1][2] == 0){
            openList.add(new EpuzzleState(moveLeft(searcher, 1, 2)));
            openList.add(new EpuzzleState(moveUp(searcher, 1, 2)));
            openList.add(new EpuzzleState(moveDown(searcher, 1, 2)));
        }
        if(puzzleState[2][0] == 0){
            openList.add(new EpuzzleState(moveUp(searcher, 2, 0)));
            openList.add(new EpuzzleState(moveRight(searcher, 2, 0)));
        }
        if(puzzleState[2][1] == 0){
            openList.add(new EpuzzleState(moveRight(searcher, 2, 1)));
            openList.add(new EpuzzleState(moveLeft(searcher, 2, 1)));
            openList.add(new EpuzzleState(moveUp(searcher, 2, 1)));
        }
        if(puzzleState[2][2] == 0){
            openList.add(new EpuzzleState(moveLeft(searcher, 2, 2)));
            openList.add(new EpuzzleState(moveUp(searcher, 2, 2)));
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


    // ------------------------------------
	//		    MOVE STATE UP
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public EpuzzleState moveUp(int[][] state, int row, int column) {
        int newRow = row - 1;
        
        // creating the new array to return
        int[][] returnPuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            returnPuzzle[i] =  state[i].clone();
        }
        // moving the state up one square
        int temp = returnPuzzle[newRow][column];
        returnPuzzle[newRow][column] = returnPuzzle[row][column];
        returnPuzzle[row][column] = temp;
        
        return new EpuzzleState(returnPuzzle);
    } 

    // ------------------------------------
	//		    MOVE STATE DOWN
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public EpuzzleState moveDown(int[][] state, int row, int column) {
        int newRow = row + 1;
        
        // creating the new array to return
        int[][] returnPuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            returnPuzzle[i] =  state[i].clone();
        }
        // moving the state up one square
        int temp = returnPuzzle[newRow][column];
        returnPuzzle[newRow][column] = returnPuzzle[row][column];
        returnPuzzle[row][column] = temp;
        
        return new EpuzzleState(returnPuzzle);
    } 

    // ------------------------------------
	//		    MOVE STATE RIGHT
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public EpuzzleState moveRight(int[][] state, int row, int column) {
        int newColumn = row + 1;
        
        // creating the new array to return
        int[][] returnPuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            returnPuzzle[i] =  state[i].clone();
        }
        // moving the state up one square
        int temp = puzzleState[row][newColumn];
        puzzleState[row][newColumn] = puzzleState[row][column];
        puzzleState[row][column] = temp;
        
        return new EpuzzleState(returnPuzzle);
    } 

    // ------------------------------------
	//		    MOVE STATE LEFT
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public EpuzzleState moveLeft(int[][] state, int row, int column) {
        int newColumn = row - 1;
        
        // creating the new array to return
        int[][] returnPuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            returnPuzzle[i] =  state[i].clone();
        }
        // moving the state up one square
        int temp = puzzleState[row][newColumn];
        puzzleState[row][newColumn] = puzzleState[row][column];
        puzzleState[row][column] = temp;
        
        return new EpuzzleState(returnPuzzle);
    } 
}
