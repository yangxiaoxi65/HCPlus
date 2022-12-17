import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// the hamilton algo with mnemonic search(Dynamic Programming)
public class HamiltonLoopDP {

    private Graph G;
    private boolean res;
    private boolean[][] memo;

    public HamiltonLoopDP(Graph G){

        this.G = G;
        memo = new boolean[1<<G.V()][G.V()]; // the space to store the result of state
        for(int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], false);

        int visited = 0;
        res = dfs(visited,0, G.V()); //start from the vertex 0
    }

    private boolean dfs(int visited, int v, int left){

        if(memo[visited][v]) return true;

        visited += (1 << v); //set bit v of visited as 1
        left--; //left means how many vertex we have not checked
        if(left == 0 && G.hasEdge(v, 0)){
            memo[visited][v] = true;
            return true;
        }

        for(int w: G.adj(v))
            if((visited & (1 << w)) == 0){ // if w is not visited, recursively check w
                if(dfs(visited, w, left)){
                    memo[visited][v] = true;
                    return true; //the importance of optimization
                }
            }

        visited -= (1 << v); //traceback
        return false;
    }

//    //if all vertexes are visited, we have found a Hamilton loop
//    private boolean allVisited(){
//        for(int v = 0; v < G.V(); v ++)
//            if(!visited[v]) return false;
//        return true;
//    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        HamiltonLoopDP hl = new HamiltonLoopDP(g);
        System.out.println(hl.res);

        Graph g2 = new Graph("g2.txt");
        HamiltonLoopDP hl2 = new HamiltonLoopDP(g2);
        System.out.println(hl2.res);

    }
}