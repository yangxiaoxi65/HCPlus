public class ContrastTest {
    public static void main(String[] args){
        long start, end;
        long time = 0; long timeSC = 0; long timeDP = 0;
        System.out.printf("%-18s%-16s%-16s", "Graph","HamiltonLoop", "HamiltonLoop with state compression\n" );

        for(int i = 3; i <= 30; i++){
            System.out.printf("%-18s", getGraph(i));
            time = 0;   timeSC = 0;     timeDP = 0;

            for(int j = 0; j < 10; j++){
                start = System.nanoTime();
                HamiltonLoop hl = new HamiltonLoop(new Graph(getGraph(i)));
                end = System.nanoTime();
                time += (end - start);
            }

            for(int j = 0; j < 10; j++){
                start = System.nanoTime();
                HamiltonLoopSC hlsc = new HamiltonLoopSC(new Graph(getGraph(i)));
                end = System.nanoTime();
                timeSC += (end - start);
            }

//            for(int j = 0; j < 10; j++){
//                start = System.nanoTime();
//                HamiltonLoopDP hldp = new HamiltonLoopDP(new Graph(getGraph(i)));
//                end = System.nanoTime();
//                timeDP += (end - start);
//            }

            System.out.printf("%-16s",(time/10) + " ");
            System.out.printf("%-16s",(timeSC/10) + " ");
//            System.out.printf("%-16s",(timeDP/10) + " ");
            System.out.println();
            //System.out.println(hl.result());
        }
    }

    public static String getGraph(int num){
        String ad = "graph\\g0.txt";
        ad = ad.replace("0", num + "");
        return ad;
    }
}
