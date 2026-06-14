import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        boolean shouldRunClient = true;

        while(shouldRunClient){
            StdOut.println("Running client");

            String input = StdIn.readString();

            if(input.equals("exit")){
                System.out.println("Exiting...");
                shouldRunClient = false;
            }

            if(input.equals("quickfind")){
                StdOut.println("Enter N");
                int N = StdIn.readInt();
                QuickFind quickFind = new QuickFind(N);
                StdOut.println(Arrays.toString(quickFind.getId()));
                while (!StdIn.isEmpty()){
                    StdOut.println("Enter p and q");
                    int p = StdIn.readInt();
                    int q = StdIn.readInt();
                    System.out.println("Result " + p + " - " + q);
                    if(!quickFind.connected(p, q)){
                        quickFind.union(p, q);
                        System.out.println("Union for " + p + " " + q);
                    }
                    StdOut.println(Arrays.toString(quickFind.getId()));
                }
            }

            if(input.equals("quickunion")){
                StdOut.println("Enter N");
                int N = StdIn.readInt();
                QuickUnion quickUnion = new QuickUnion(N);
                StdOut.println(Arrays.toString(quickUnion.getId()));
                while (!StdIn.isEmpty()){
                    StdOut.println("Enter p");
                    int p = StdIn.readInt();
                    StdOut.println("Enter q");
                    int q = StdIn.readInt();
                    System.out.println("Result " + p + " - " + q);
                    if(!quickUnion.connected(p, q)){
                        quickUnion.union(p, q);
                        System.out.println("Union for " + p + " " + q);
                    }
                    StdOut.println(Arrays.toString(quickUnion.getId()));
                }
            }

            if(input.equals("weighted")){
                StdOut.println("Enter N");
                int N = StdIn.readInt();
                Weighted weighted = new Weighted(N);
                StdOut.println(Arrays.toString(weighted.getPointers()));
                while (!StdIn.isEmpty()){
                    StdOut.println("Enter p");
                    int p = StdIn.readInt();
                    StdOut.println("Enter q");
                    int q = StdIn.readInt();
                    System.out.println("Result " + p + " - " + q);
                    if(!weighted.connected(p, q)){
                        weighted.union(p, q);
                        System.out.println("Union for " + p + " " + q);
                    }
                    StdOut.println(Arrays.toString(weighted.getPointers()));
                }
            }

            if(input.equals("compressed")){
                StdOut.println("Enter N");
                int N = StdIn.readInt();
                Compressed compressed = new Compressed(N);
                StdOut.println(Arrays.toString(compressed.getPointers()));
                while (!StdIn.isEmpty()){
                    StdOut.println("Enter p");
                    int p = StdIn.readInt();
                    StdOut.println("Enter q");
                    int q = StdIn.readInt();
                    System.out.println("Result " + p + " - " + q);
                    if(!compressed.connected(p, q)){
                        compressed.union(p, q);
                        System.out.println("Union for " + p + " " + q);
                    }
                    StdOut.println(Arrays.toString(compressed.getPointers()));
                }
            }

            if(input.equals("percolation")){
                StdOut.println("Percolation: Enter n");
                int n = StdIn.readInt();
                Percolation percolation = new Percolation(n);
                percolation.open(1, 4);
                percolation.open(2, 4);
                percolation.open(3, 4);
                percolation.open(3, 3);
                percolation.open(2, 2);
                percolation.open(2, 1);

                System.out.println(percolation.isFull(2,1) + " testing");
            }

            if(input.equals("stats")){
                PercolationStats stats = new PercolationStats(3, 5);
            }
        }

    }
}