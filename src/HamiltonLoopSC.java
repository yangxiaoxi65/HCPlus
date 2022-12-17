import java.util.ArrayList;
import java.util.Collections;

// the hamilton algo with state of compression
public class HamiltonLoopSC {

    private Graph G;
    private int[] pre;
    private int end;

    public HamiltonLoopSC(Graph G){

        this.G = G;
        pre = new int[G.V()];
        end = -1;

        int visited = 0;
        dfs(visited,0, 0, G.V()); //start from the vertex 0
    }

    private boolean dfs(int visited, int v, int parent, int left){

        visited += (1 << v); //set bit v of visited as 1
        pre[v] = parent;
        left--; //left means how many vertex we have not checked
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if((visited & (1 << w)) == 0){ // if w is not visited, recursively check w
                if(dfs(visited, w, v, left)) return true; //the importance of optimization
            }

        visited -= (1 << v); //traceback
        return false;
    }

    //get the path through the end
    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

//    //if all vertexes are visited, we have found a Hamilton loop
//    private boolean allVisited(){
//        for(int v = 0; v < G.V(); v ++)
//            if(!visited[v]) return false;
//        return true;
//    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        HamiltonLoopSC hl = new HamiltonLoopSC(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("g2.txt");
        HamiltonLoopSC hl2 = new HamiltonLoopSC(g2);
        System.out.println(hl2.result());
    }
}