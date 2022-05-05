package BFS;

import java.util.*;
import Astar.EpuzzGen;

public class RunEpuzzleBFS {
    public static void main(String[] arg) {

        int[][] tar = {{1,2,3}, {4,5,6}, {7,8,0}};
        
        // use this to test
        int[][] testerTar = {{1,0,3}, {4,2,6}, {7,5,8}};

        //     P1 = {{1,0,3}, {4,2,6}, {7,5,8}};
        //     P2 = {{4,1,3}, {7,2,5}, {0,8,6}};
        //     P3 = {{2,3,6}, {1,5,8}, {4,7,0}};
        // Target = {{1,2,3}, {4,5,6}, {7,8,0}};

        EpuzzleSearch searcher = new EpuzzleSearch(testerTar, tar);
        SearchState initState = (SearchState) new EpuzzleState(testerTar);
    
        String resb = searcher.runSearch(initState, "breadthFirst");

        System.out.println(resb);

        // generator given seed
        int seed = 23456;
        EpuzzGen gen = new EpuzzGen(seed);
        // generate puzzle providing difficulty
        int d = 6;
        int[][] puzz = gen.puzzGen(d);
    
    }
}

