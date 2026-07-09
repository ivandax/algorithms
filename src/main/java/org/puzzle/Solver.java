package org.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

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

    public boolean isSolvable() {
        return solvable;
    }

    public static void main(String[] args) {
        int[][] sample = {{1, 2, 3}, {4, 5, 6}, {0, 7, 8}};
        Board newBoard = new Board(sample);
        Solver mySolver = new Solver(newBoard);
        System.out.println(mySolver.solvable);
        for (Board board : mySolver.solutionBoards) {
            System.out.println("Printing solutions");
            System.out.println(board.toString());
        }
    }
}
