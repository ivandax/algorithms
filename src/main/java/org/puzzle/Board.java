package org.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    public String toString() {
        int length = tiles.length;
        StringBuilder finalString = new StringBuilder();
        finalString.append(length).append("\n");
        for (int[] row : tiles) {
            StringBuilder rowString = new StringBuilder();
            for (int num : row) {
                rowString.append(" ").append(num);
            }
            finalString.append(rowString).append("\n");
        }
        return finalString.toString();
    }

    public static void main(String[] args) {
        int[][] sample = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board newBoard = new Board(sample);
        System.out.println(newBoard.toString());
    }

}
