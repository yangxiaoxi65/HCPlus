public class TestForDP {
    public static void main(String[] args){
        long start, end;
        long time = 0; long timeSC = 0; long timeDP = 0;
        String  ad = "graph\\g8.txt";

        System.out.printf("%-18s%-16s%-16s%-16s\n", "Graph","HL", "HL_SC", "HL_DP" );

            System.out.printf("%-18s", "Special Graph");

            for(int j = 0; j < 10; j++){
                start = System.nanoTime();
                HamiltonLoop hl = new HamiltonLoop(new Graph(ad));
                end = System.nanoTime();
                time += (end - start);
            }

            for(int j = 0; j < 10; j++){
                start = System.nanoTime();
                HamiltonLoopSC hlsc = new HamiltonLoopSC(new Graph(ad));
                end = System.nanoTime();
                timeSC += (end - start);
            }

            for(int j = 0; j < 10; j++){
                start = System.nanoTime();
                HamiltonLoopDP hldp = new HamiltonLoopDP(new Graph(ad));
                end = System.nanoTime();
                timeDP += (end - start);
            }

            System.out.printf("%-16s",(time/10) + " ");
            System.out.printf("%-16s",(timeSC/10) + " ");
            System.out.printf("%-16s",(timeDP/10) + " ");
            System.out.println();
            //System.out.println(hl.result());
        }
    }

