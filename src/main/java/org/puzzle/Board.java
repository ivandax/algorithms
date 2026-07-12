package org.puzzle;

import edu.princeton.cs.algs4.Stack;
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
                if (row[j] == 0) {
                    this.blankIndex = index;
                }
            }
        }
        this.tileArray = tileArray;
        this.n = n;
    }

    public Board getBoardFromTileArray(int[] array) {
        int[][] outerArray = new int[n][n];
        for (int c = 0; c < n; c++) {
            int[] innerArray = new int[n];
            int pointer = 0;
            for (int i = c * n; i < c * n + n; i++) {
                innerArray[pointer] = array[i];
                pointer++;
            }
            outerArray[c] = innerArray;
        }
        return new Board(outerArray);
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

    private enum SwapDirection {
        UP,
        RIGHT,
        LEFT,
        DOWN,
    }

    private int getSwapIndex(SwapDirection direction) {
        return switch (direction) {
            case SwapDirection.UP -> blankIndex - n;
            case SwapDirection.RIGHT -> blankIndex + 1;
            case SwapDirection.LEFT -> blankIndex - 1;
            case SwapDirection.DOWN -> blankIndex + n;
        };
    }

    private Board getNeighbor(SwapDirection direction) {
        int[] copy = new int[n * n];
        System.arraycopy(this.tileArray, 0, copy, 0, this.tileArray.length);
        int swapIndex = getSwapIndex(direction);
        int newValue = this.tileArray[swapIndex];
        copy[swapIndex] = 0;
        copy[this.blankIndex] = newValue;
        return getBoardFromTileArray(copy);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();
        int colRef = blankIndex % n;
        int rowRef = blankIndex / n;
        boolean isFirstCol = colRef == 0;
        boolean isLastCol = colRef + 1 == n;
        boolean isFirstRow = rowRef == 0;
        boolean isLastRow = rowRef + 1 == n;

        if (!isFirstCol) {
            stack.push(getNeighbor(SwapDirection.LEFT));
        }
        if (!isLastCol) {
            stack.push(getNeighbor(SwapDirection.RIGHT));
        }
        if (!isFirstRow) {
            stack.push(getNeighbor(SwapDirection.UP));
        }
        if (!isLastRow) {
            stack.push(getNeighbor(SwapDirection.DOWN));
        }
        return stack;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int tryToGetRandomTile(int excludeIndex) {
        int tileIndex = -1;
        while (tileIndex < 0) {
            int i = getRandomNumber(0, tileArray.length - 1);
            if (tileArray[i] == 0 || i == excludeIndex) continue;
            tileIndex = i;
        }
        return tileIndex;
    }

    public Board twin() {
        int firstTileToSwap = tryToGetRandomTile(-2);
        int secondTileToSwap = tryToGetRandomTile(firstTileToSwap);
        int[] copyTileArray = new int[n * n];
        for (int i = 0; i < tileArray.length; i++) {
            if (i == firstTileToSwap) {
                copyTileArray[i] = tileArray[secondTileToSwap];
                continue;
            }
            if (i == secondTileToSwap) {
                copyTileArray[i] = tileArray[firstTileToSwap];
                continue;
            }
            copyTileArray[i] = tileArray[i];
        }
        return getBoardFromTileArray(copyTileArray);
    }

    public boolean isGoal() {
        boolean isGoal = true;
        for (int i = 0; i < n * n - 1; i++) {
            if (tileArray[i] != i + 1) {
                isGoal = false;
                break;
            }
        }
        return isGoal;
    }

    public static void main(String[] args) {
        int[][] sample = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board newBoard = new Board(sample);

        System.out.println("To string");
        String printable = newBoard.toString();
        StdOut.println(printable);

        System.out.println("Dimensions");
        System.out.println(newBoard.dimension());
        System.out.println("\n");

        System.out.println("Manhattan");
        System.out.println(newBoard.manhattan());
        System.out.println("\n");

        System.out.println("Hamming");
        System.out.println(newBoard.hamming());
        System.out.println("\n");

        System.out.println("Is goal");
        System.out.println(Boolean.toString(newBoard.isGoal()));
        System.out.println("\n");

        System.out.println("Equals");
        System.out.println(Boolean.toString(newBoard.equals(newBoard)));
        System.out.println("\n");

        System.out.println("Twin");
        System.out.println(newBoard.twin().toString());
        System.out.println("\n");

        Iterable<Board> boards = newBoard.neighbors();
        for (Board board : boards) {
            System.out.println(board.toString());
            System.out.println("\n");
        }
    }

}
