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

    private static class SearchNode {
        Board board;
        int moves;
        SearchNode previous;
        int manhattanPriority;

        SearchNode(Board board,
                   int moves,
                   SearchNode previous,
                   int manhattanPriority) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.manhattanPriority = manhattanPriority;
        }
    }

    private static class PriorityFunction implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode nodeA, SearchNode nodeB) {
            return Integer.compare(nodeA.manhattanPriority, nodeB.manhattanPriority);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("null initializer");
        }
        SearchNode root = new SearchNode(initial, 0, null, initial.manhattan());
        Board twinBoard = initial.twin();
        SearchNode twinNode = new SearchNode(twinBoard, 0, null, twinBoard.manhattan());
        MinPQ<SearchNode> mainPq = new MinPQ<>(new PriorityFunction());
        MinPQ<SearchNode> twinPq = new MinPQ<>(new PriorityFunction());
        mainPq.insert(root);
        twinPq.insert(twinNode);
        attemptParallelSolutions(mainPq, twinPq);
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
            moves = -1;
            return true;
        } else {
            Iterable<Board> boards = min.board.neighbors();
            System.out.println("Manhattan priority " + min.manhattanPriority);
            for (Board board : boards) {
                if (min.previous != null && board.equals(min.previous.board)) continue;
                SearchNode node = new SearchNode(board, min.moves + 1, min, board.manhattan() + min.moves + 1);
                pq.insert(node);
            }
            return attemptSolution(pq);
        }
    }

    private boolean attemptParallelSolutions(MinPQ<SearchNode> mainPq, MinPQ<SearchNode> twinPq) {
        SearchNode mainMin = mainPq.delMin();
        SearchNode twinMin = twinPq.delMin();
        if (twinMin.board.isGoal()) {
            solvable = false;
            solutionBoards = null;
            return false;
        } else if (mainMin.board.isGoal()) {
            solvable = true;
            stackSolution(mainMin);
            return true;
        } else {
            Iterable<Board> mainNeighbors = mainMin.board.neighbors();
            for (Board board : mainNeighbors) {
                if (mainMin.previous != null && board.equals(mainMin.previous.board)) continue;
                SearchNode node = new SearchNode(board, mainMin.moves + 1, mainMin, board.manhattan() + mainMin.moves + 1);
                mainPq.insert(node);
            }
            Iterable<Board> twinNeighbors = twinMin.board.neighbors();
            for (Board board : twinNeighbors) {
                if (twinMin.previous != null && board.equals(twinMin.previous.board)) continue;
                SearchNode node = new SearchNode(board, twinMin.moves + 1, twinMin, board.manhattan() + twinMin.moves + 1);
                twinPq.insert(node);
            }
            return attemptParallelSolutions(mainPq, twinPq);
        }
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

    // To read from the files, add a configuration with arguments like "puzzle1.txt" and
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
