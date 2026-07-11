package org.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {
    private boolean solvable;
    private Iterable<Board> solutionBoards;
    private int moves;

    static class SearchNode {
        Board board;
        int moves;
        SearchNode previous;

        SearchNode(Board board,
                   int moves,
                   SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    static class PriorityFunction implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode nodeA, SearchNode nodeB) {
            return Integer.compare(nodeA.board.hamming(), nodeB.board.hamming());
        }
    }

    private void stackSolution(SearchNode searchNode) {
        Stack<Board> stack = new Stack<>();
        stack.push(searchNode.board);
        SearchNode current = searchNode;
        while (current.previous != null) {
            stack.push(current.previous.board);
            current = current.previous;
            moves++;
        }
        this.solutionBoards = stack;
    }

    private boolean attemptSolution(MinPQ<SearchNode> pq) {
        SearchNode min = pq.delMin();
        if (min.board.isGoal()) {
            solvable = true;
            stackSolution(min);
            return true;
        } else {
            Iterable<Board> boards = min.board.neighbors();
            for (Board board : boards) {
                SearchNode node = new SearchNode(board, min.moves + 1, min);
                pq.insert(node);
            }
            return attemptSolution(pq);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("null initializer");
        }
        SearchNode root = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ<>(new PriorityFunction());
        pq.insert(root);
        attemptSolution(pq);
    }

    public Iterable<Board> solution() {
        return solutionBoards;
    }

    public int moves() {
        return moves;
    }

    public boolean isSolvable() {
        return solvable;
    }

    // To read from the files, add a configuration with arguments like "puzzle02-basic.txt" and
    // make sure file is added at the root of the project
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
