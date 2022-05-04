import java.util.*;

public class RunEpuzzleBFS {
    public static void main(String[] arg) {

        int[][] tar = {{1,2,3}, {4,5,6}, {7,8,0}};
        
        // use this to test
        int[][] testerTar = {{1,2,3}, {4,5,6}, {7,8,0}};

        EpuzzleSearch searcher = new EpuzzleSearch(testerTar, tar);
        SearchState initState = (SearchState) new EpuzzleState(testerTar);
    
        String resb = searcher.runSearch(initState, "breadthFirst");

        System.out.println(resb);
    
    }
}
