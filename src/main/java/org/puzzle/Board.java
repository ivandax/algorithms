package org.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[] tileArray;
    private int n;
    private int blankIndex;

    public Board(int[][] tiles) {
        int n = tiles.length;
        int[] tileArray = new int[n * n];
        for (int i = 0; i < tiles.length; i++) {
            int[] row = tiles[i];
            for (int j = 0; j < row.length; j++) {
                int index = i * n + j;
                tileArray[index] = row[j];
                if(row[j] == 0){
                    this.blankIndex = i * n + j;
                }
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

    private int findRow(int index) {
        return index / n;
    }

    private int findColumn(int index) {
        return index % n;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int totalDiff = 0;
        for (int i = 0; i < tileArray.length; i++) {
            int value = tileArray[i];
            if (value == 0) continue;
            int targetIndex = value - 1;
            int targetRow = findRow(targetIndex);
            int targetCol = findColumn(targetIndex);
            int currentRow = findRow(i);
            int currentCol = findColumn(i);
            int rowDiff = Math.abs(currentRow - targetRow);
            int colDiff = Math.abs(currentCol - targetCol);
            totalDiff = totalDiff + rowDiff + colDiff;
        }
        return totalDiff;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;

        // Not the same dimensions
        if (this.n != that.n) {
            return false;
        }

        // Not all equal elements
        boolean areEqual = true;
        for (int i = 0; i < this.tileArray.length; i++) {
            int thisValue = this.tileArray[i];
            int thatValue = that.tileArray[i];
            if (thisValue != thatValue) {
                areEqual = false;
                break;
            }
        }
        return areEqual;
    }

    public int getNumberOfNeighbors() {
        int colRef = blankIndex % n;
        int rowRef = blankIndex / n;
        boolean isFirstCol = colRef == 0;
        boolean isLastCol = colRef + 1 == n;
        boolean isFirstRow = rowRef == 0;
        boolean isLastRow = rowRef + 1 == n;

        int maxNeighbors = 4;
        if(isFirstCol || isLastCol) maxNeighbors--;
        if(isFirstRow || isLastRow) maxNeighbors--;
        return maxNeighbors;
    }

    public static void main(String[] args) {
        int[][] sample = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board newBoard = new Board(sample);
        StdOut.println(newBoard.toString());
        System.out.println(newBoard.manhattan());
        System.out.println("Neighbors");
        System.out.println(newBoard.getNumberOfNeighbors());
    }

}
