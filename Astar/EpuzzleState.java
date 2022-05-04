package Astar;
import java.util.*;

public class EpuzzleState extends SearchState {
    private int[][] puzzleState;
    private String method;
    
    public EpuzzleState(int[][] state, int lc, int erc, String meth){
        puzzleState = state;
        localCost = lc;
        estRemCost = erc;
        method = meth;
    }

    public int[][] getPuzzleState(){
        return puzzleState;
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
            for(int j = 0; j < 3; j++){
                if (state[i][j] != target[i][j]){
                    count ++;
                }
            }
        }

        return count;
    }

    // ------------------------------------
	//		    MOVE STATE UP
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public int[][] moveUp(int[][] state, int row, int column) {
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
        
        return returnPuzzle;
    } 

    // ------------------------------------
	//		    MOVE STATE DOWN
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public int[][] moveDown(int[][] state, int row, int column) {
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
        
        return returnPuzzle;
    } 

    // ------------------------------------
	//		    MOVE STATE RIGHT
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public int[][] moveRight(int[][] state, int row, int column) {
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
        
        return returnPuzzle;
    } 

    // ------------------------------------
	//		    MOVE STATE LEFT
	// ------------------------------------
    // @param state - the current search
    // @param column
    // @param row
    // @return puzzle with state position changed

    public int[][] moveLeft(int[][] state, int row, int column) {
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
        
        return returnPuzzle;
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
        EpuzzleState puzzle = (EpuzzleState) eSearcher.currentNode.get_State();

        // create puzzle to edit with the 'move' functions
        int[][] movePuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            movePuzzle[i] =  puzzleState[i].clone();
        }

        this.puzzleState = puzzle.getPuzzleState();
    
        ArrayList<EpuzzleState> openList = new ArrayList<EpuzzleState>(); // open list
        ArrayList<SearchState> closedList = new ArrayList<SearchState>(); // closed list

        // sets rules for each square

        if(getMethod().equals("manhatten")){
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 0), 1, manhattan(moveRight(movePuzzle, 0, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 0), 1, manhattan(moveDown(movePuzzle, 0, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[0][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 1), 1, manhattan(moveRight(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 1), 1, manhattan(moveLeft(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 1), 1, manhattan(moveDown(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[0][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 2), 1, manhattan(moveLeft(movePuzzle, 0, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 2), 1, manhattan(moveDown(movePuzzle, 0, 2), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][0] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 0), 1, manhattan(moveRight(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 0), 1, manhattan(moveUp(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 0), 1, manhattan(moveDown(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 1), 1, manhattan(moveRight(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 1), 1, manhattan(moveLeft(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 1), 1, manhattan(moveUp(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 1), 1, manhattan(moveDown(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 2), 1, manhattan(moveLeft(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 2), 1, manhattan(moveUp(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 2), 1, manhattan(moveDown(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][0] == 0){
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 0), 1, manhattan(moveUp(movePuzzle, 2, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 0), 1, manhattan(moveRight(movePuzzle, 2, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 1), 1, manhattan(moveRight(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 1), 1, manhattan(moveLeft(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 1), 1, manhattan(moveUp(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 2), 1, manhattan(moveLeft(movePuzzle, 2, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 2), 1, manhattan(moveUp(movePuzzle, 2, 2), eSearcher.getTarget()), getMethod() ));
            }
        }
        else {
            if(puzzleState[0][0] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 0), 1, hammingDistance(moveRight(movePuzzle, 0, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 0), 1, hammingDistance(moveDown(movePuzzle, 0, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[0][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 1), 1, hammingDistance(moveRight(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 1), 1, hammingDistance(moveLeft(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 1), 1, hammingDistance(moveDown(movePuzzle, 0, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[0][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 2), 1, hammingDistance(moveLeft(movePuzzle, 0, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 2), 1, hammingDistance(moveDown(movePuzzle, 0, 2), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][0] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 0), 1, hammingDistance(moveRight(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 0), 1, hammingDistance(moveUp(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 0), 1, hammingDistance(moveDown(movePuzzle, 1, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 1), 1, hammingDistance(moveRight(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 1), 1, hammingDistance(moveLeft(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 1), 1, hammingDistance(moveUp(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 1), 1, hammingDistance(moveDown(movePuzzle, 1, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[1][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 2), 1, hammingDistance(moveLeft(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 2), 1, hammingDistance(moveUp(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 2), 1, hammingDistance(moveDown(movePuzzle, 1, 2), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][0] == 0){
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 0), 1, hammingDistance(moveUp(movePuzzle, 2, 0), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 0), 1, hammingDistance(moveRight(movePuzzle, 2, 0), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][1] == 0){
                openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 1), 1, hammingDistance(moveRight(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 1), 1, hammingDistance(moveLeft(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 1), 1, hammingDistance(moveUp(movePuzzle, 2, 1), eSearcher.getTarget()), getMethod() ));
            }
            if(puzzleState[2][2] == 0){
                openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 2), 1, hammingDistance(moveLeft(movePuzzle, 2, 2), eSearcher.getTarget()), getMethod() ));
                openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 2), 1, hammingDistance(moveUp(movePuzzle, 2, 2), eSearcher.getTarget()), getMethod() ));
            }
        }
        
        for (EpuzzleState openListValues : openList){
            closedList.add((SearchState) openListValues);
        }
        return closedList;
    }

    // ------------------------------------
	//		    SAME STATE?
	// ------------------------------------
    // comparing two arrays and seeing if they are equal
    // returns true if equal

    public boolean sameStateExtra (Object objectArray){
        boolean checker = true;
        int puzzleLength = this.puzzleState.length;
        
        if (this == objectArray){
            return true;
        }
        
        EpuzzleState openList = (EpuzzleState) objectArray;
        for (int i = 0; i < puzzleLength; i++) {
            for (int j = 0; j < this.puzzleState[i].length; j++) {
                if (this.puzzleState[i][j] != openList.getPuzzleState()[i][j]) {
                    checker = false;
                    break;
                }
            }
        }
        return checker;
    }

/**
 * sameState - do 2 EPuzzleSearch nodes have the same state?
 * @param s2 second state
 */

public boolean sameState (SearchState s2) {
        EpuzzleState objectArray = (EpuzzleState) s2;
        return this.sameStateExtra(objectArray);
}

}
