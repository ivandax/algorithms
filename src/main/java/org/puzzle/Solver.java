package org.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean solvable;
    private Iterable<Board> solutionBoards;

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

    private void stackSolution(SearchNode searchNode) {
        Stack<Board> stack = new Stack<>();
        stack.push(searchNode.board);
        while (searchNode.previous != null) {
            stackSolution(searchNode.previous);
        }
        this.solutionBoards = stack;
    }

    private void attemptSolution(SearchNode searchNode) {
        if (searchNode.board.isGoal()) {
            solvable = true;
            stackSolution(searchNode);
        } else {

        }
    }

    private int priorityFunction(SearchNode nodeA, SearchNode nodeB) {
        return Integer.compare(nodeA.board.hamming(), nodeB.board.hamming());
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("null initializer");
        }
        SearchNode root = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ<>();
        attemptSolution(root);
    }

    public Iterable<Board> solution() {
        return solutionBoards;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public static void main(String[] args) {
        int[][] sample = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board newBoard = new Board(sample);
        Solver mySolver = new Solver(newBoard);
        System.out.println(mySolver.solvable);
        for (Board board : mySolver.solutionBoards) {
            System.out.println(board.toString());
        }
    }
}
