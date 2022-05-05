package BFS;

import java.util.*;

public class EpuzzleState extends SearchState {
    private int[][] puzzleState;
    
    public EpuzzleState(int[][] state){
        puzzleState = state;
    }

    public int[][] getPuzzleState(){
        return puzzleState;
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

        // create puzzle to edit with the 'move' functions
        int[][] movePuzzle = new int[3][3];
        for(int i = 0; i < 3; i++){
            movePuzzle[i] =  puzzleState[i].clone();
        }

        this.puzzleState = puzzle.getPuzzleState();
    
        ArrayList<EpuzzleState> openList = new ArrayList<EpuzzleState>(); // open list
        ArrayList<SearchState> closedList = new ArrayList<SearchState>(); // closed list

        // sets rules for each square
        if(puzzleState[0][0] == 0){
            openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 0)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 0)));
        }
        if(puzzleState[0][1] == 0){
            openList.add(new EpuzzleState(moveRight(movePuzzle, 0, 1)));
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 1)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 1)));
        }
        if(puzzleState[0][2] == 0){
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 0, 2)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 0, 2)));
        }
        if(puzzleState[1][0] == 0){
            openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 0)));
            openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 0)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 0)));
        }
        if(puzzleState[1][1] == 0){
            openList.add(new EpuzzleState(moveRight(movePuzzle, 1, 1)));
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 1)));
            openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 1)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 1)));
        }
        if(puzzleState[1][2] == 0){
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 1, 2)));
            openList.add(new EpuzzleState(moveUp(movePuzzle, 1, 2)));
            openList.add(new EpuzzleState(moveDown(movePuzzle, 1, 2)));
        }
        if(puzzleState[2][0] == 0){
            openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 0)));
            openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 0)));
        }
        if(puzzleState[2][1] == 0){
            openList.add(new EpuzzleState(moveRight(movePuzzle, 2, 1)));
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 1)));
            openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 1)));
        }
        if(puzzleState[2][2] == 0){
            openList.add(new EpuzzleState(moveLeft(movePuzzle, 2, 2)));
            openList.add(new EpuzzleState(moveUp(movePuzzle, 2, 2)));
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

    public boolean sameState (SearchState s2) {
        EpuzzleState objectArray = (EpuzzleState) s2;
        return this.sameStateExtra(objectArray);
    }

    private static void printPuzzle(int[][] puzzle) {
        for (int[] x : puzzle){
            for (int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public String node_toString(){
        
        return "bleh";
    }

}

