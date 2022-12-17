import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){

        this.G = G;
        visited = new boolean[G.V()]; // this array store whether the vertex is visited
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0, G.V()); //start from the vertex 0
    }

    private boolean dfs(int v, int parent, int left){

        visited[v] = true;
        pre[v] = parent;
        left--; //left means how many vertex we have not checked
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if(!visited[w]){ // if w is not visited, recursively check w
                if(dfs(w, v, left)) return true;
            }

        visited[v] = false; //traceback
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
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("g2.txt");
        HamiltonLoop hl2 = new HamiltonLoop(g2);
        System.out.println(hl2.result());
    }
}
