package org.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[] tileArray;
    private int n;

    public Board(int[][] tiles) {
        int n = tiles.length;
        int[] tileArray = new int[n * n];
        for (int i = 0; i < tiles.length; i++) {
            int[] row = tiles[i];
            for (int j = 0; j < row.length; j++) {
                tileArray[i * n + j] = row[j];
            }
        }
        this.tileArray = tileArray;
        this.n = n;
    }

    public String toString() {
        StringBuilder finalString = new StringBuilder();
        finalString.append(n).append("\n");
        for (int i = 0; i < tileArray.length; i++) {
            int item = tileArray[i];
            finalString.append(item).append(" ");
            if ((i + 1) % n == 0) {
                finalString.append("\n");
            }
        }
        return finalString.toString();
    }

    public int dimension() {
        return n;
    }

    // Number of tiles in wrong position
    public int hamming() {
        int count = 0;
        for (int i = 0; i < tileArray.length - 1; i++) {
            // i + 1 expected value
            if (tileArray[i] != i + 1) {
                count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int totalDiff = 0;
        for (int i = 0; i < tileArray.length; i++) {
            int value = tileArray[i];
            int targetRow = value / n;
            int targetCol = value % n;
            int currentRow = (i + 1) / n;
            int currentCol = (i + 1) % n;
            int rowDiff = Math.abs(targetRow - currentRow) ;
            int colDiff = Math.abs(targetCol - currentCol);
            totalDiff = rowDiff + colDiff;
            System.out.println(totalDiff);
        }
        return totalDiff;
    }

    public static void main(String[] args) {
        int[][] sample = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board newBoard = new Board(sample);
        StdOut.println(newBoard.toString());
        newBoard.manhattan();

        System.out.println("\n");
        System.out.println("Test");
        System.out.println("\n");
        System.out.println(2 / 3);
        System.out.println(2 % 3);
    }

}
