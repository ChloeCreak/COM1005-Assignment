package Astar;

public class RunEpuzzleASTAR {
    public static void main(String[] arg) {

        int[][] tar = {{1,2,3}, {4,5,6}, {7,8,0}};
        
        // use this to test
        int[][] testerTar = {{1,2,3}, {4,5,6}, {7,8,0}};

        EpuzzleSearch searcher = new EpuzzleSearch(testerTar, tar);
        SearchState initState = (SearchState) new EpuzzleState(testerTar, 0, 0, "hamming");
    
        String resb = searcher.runSearch(initState, "AStar");

        System.out.println(resb);

        // generator given seed
        int seed = 23456;
        EpuzzGen gen = new EpuzzGen(seed);
        // generate puzzle providing difficulty
        int d = 6;
        int[][] puzz = gen.puzzGen(d);
    
    }  
}

