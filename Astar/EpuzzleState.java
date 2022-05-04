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

    // ------------------------------------
	//	   CALCULATES MANHATTEN DISTANCE
	// ------------------------------------
    // @param state - the current search
    // @param target
    // @return manhattan distance

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

    // ------------------------------------
	//	   CALCULATES HAMMING DISTANCE
	// ------------------------------------
    // @param state - the current search
    // @param target
    // @return hamming distance
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

        if(getMethod().equals("manhatten")){
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 0, 0), 1, manhattan(moveRight(searcher, 0, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 0), 1, manhattan(moveDown(searcher, 0, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[0][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 0, 1), 1, manhattan(moveRight(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 0, 1), 1, manhattan(moveLeft(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 1), 1, manhattan(moveDown(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[0][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 0, 2), 1, manhattan(moveLeft(searcher, 0, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 2), 1, manhattan(moveDown(searcher, 0, 2), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][0] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 1, 0), 1, manhattan(moveRight(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 0), 1, manhattan(moveUp(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 0), 1, manhattan(moveDown(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 1, 1), 1, manhattan(moveRight(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 1, 1), 1, manhattan(moveLeft(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 1), 1, manhattan(moveUp(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 1), 1, manhattan(moveDown(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 1, 2), 1, manhattan(moveLeft(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 2), 1, manhattan(moveUp(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 2), 1, manhattan(moveDown(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][0] == 0){
                openList.add(new EpuzzleState(moveUp(searcher, 2, 0), 1, manhattan(moveUp(searcher, 2, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveRight(searcher, 2, 0), 1, manhattan(moveRight(searcher, 2, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 2, 1), 1, manhattan(moveRight(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 2, 1), 1, manhattan(moveLeft(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 2, 1), 1, manhattan(moveUp(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 2, 2), 1, manhattan(moveLeft(searcher, 2, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 2, 2), 1, manhattan(moveUp(searcher, 2, 2), eSearcher.getTarget()), getMethod() )));
            }
        }
        else {
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 0, 0), 1, hammingDistance(moveRight(searcher, 0, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 0), 1, hammingDistance(moveDown(searcher, 0, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[0][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 0, 1), 1, hammingDistance(moveRight(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 0, 1), 1, hammingDistance(moveLeft(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 1), 1, hammingDistance(moveDown(searcher, 0, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[0][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 0, 2), 1, hammingDistance(moveLeft(searcher, 0, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 0, 2), 1, hammingDistance(moveDown(searcher, 0, 2), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][0] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 1, 0), 1, hammingDistance(moveRight(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 0), 1, hammingDistance(moveUp(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 0), 1, hammingDistance(moveDown(searcher, 1, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 1, 1), 1, hammingDistance(moveRight(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 1, 1), 1, hammingDistance(moveLeft(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 1), 1, hammingDistance(moveUp(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 1), 1, hammingDistance(moveDown(searcher, 1, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[1][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 1, 2), 1, hammingDistance(moveLeft(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 1, 2), 1, hammingDistance(moveUp(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveDown(searcher, 1, 2), 1, hammingDistance(moveDown(searcher, 1, 2), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][0] == 0){
                openList.add(new EpuzzleState(moveUp(searcher, 2, 0), 1, hammingDistance(moveUp(searcher, 2, 0), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveRight(searcher, 2, 0), 1, hammingDistance(moveRight(searcher, 2, 0), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][1] == 0){
                openList.add(new EpuzzleState(moveRight(searcher, 2, 1), 1, hammingDistance(moveRight(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveLeft(searcher, 2, 1), 1, hammingDistance(moveLeft(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 2, 1), 1, hammingDistance(moveUp(searcher, 2, 1), eSearcher.getTarget()), getMethod() )));
            }
            if(puzzleState[2][2] == 0){
                openList.add(new EpuzzleState(moveLeft(searcher, 2, 2), 1, hammingDistance(moveLeft(searcher, 2, 2), eSearcher.getTarget()), getMethod() )));
                openList.add(new EpuzzleState(moveUp(searcher, 2, 2), 1, hammingDistance(moveUp(searcher, 2, 2), eSearcher.getTarget()), getMethod() )));
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
        
        return new EpuzzleState(returnPuzzle, 1, 1);
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
        
        return new EpuzzleState(returnPuzzle, 1, 1);
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
        
        return new EpuzzleState(returnPuzzle, 1, 1);
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
        
        return new EpuzzleState(returnPuzzle, 1, 1);
    } 
}

    
}
