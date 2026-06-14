import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private boolean[] openState;
    private int gridSize;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n cannot be lower than 1");
        }
        setGridSize(n);
        int totalItems = n * (n + 2);
        grid = new WeightedQuickUnionUF(totalItems);
        boolean[] initialStates = new boolean[totalItems];
        for (int i = 0; i < totalItems - 1; i++) {
            boolean isOnFirstRow = i < n - 1;
            boolean isOnLastRow = i >= (n * n) + n && i < totalItems - 1;
            if (isOnFirstRow || isOnLastRow) {
                initialStates[i] = true;
                initialStates[i + 1] = true;
                grid.union(i, i + 1);
            } else {
                boolean currentState = initialStates[i];
                if (!currentState) {
                    initialStates[i] = false;
                }
            }
        }
        setOpenState(initialStates);
    }

    private int xyTo1D(int row, int col) {
        return row * gridSize + col - 1;
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("Row and col cannot be below 1");
        } else if (row > gridSize || col > gridSize) {
            throw new IllegalArgumentException("Row and col cannot be greater than n");
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        int index = xyTo1D(row, col);
        return openState[index];
    }

    private boolean internalIsOpen(int row, int col) {
        int index = xyTo1D(row, col);
        return openState[index];
    }

    public void open(int row, int col) {
        validateIndices(row, col);
        int index = xyTo1D(row, col);
        boolean notOpenYet = !openState[index];
        if (notOpenYet) {
            tryUpperUnion(row, col, index);
            tryRightUnion(row, col, index);
            tryLowerUnion(row, col, index);
            tryLeftUnion(row, col, index);
            openState[index] = true;
        }
    }

    public boolean percolates() {
        int upperIndex = grid.find(0);
        int firstIndexBottom = grid.find(gridSize * gridSize + gridSize);
        return upperIndex == firstIndexBottom;
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        int upperIndex = grid.find(0);
        int index = xyTo1D(row, col);
        return grid.find(index) == upperIndex;
    }

    public int numberOfOpenSites() {
        int trueCount = 0;

        for (boolean value : openState) {
            if (value) {
                trueCount++;
            }
        }

        return trueCount - (gridSize * 2);
    }

    private void tryUpperUnion(int row, int col, int index) {
        if (row > 0) {
            int upperRow = row - 1;
            boolean isAboveOpen = internalIsOpen(upperRow, col);
            if (isAboveOpen) {
                int upperIndex = xyTo1D(upperRow, col);
                grid.union(index, upperIndex);
            }
        }
    }

    private void tryRightUnion(int row, int col, int index) {
        if (col < gridSize) {
            int rightCol = col + 1;
            boolean isRightOpen = internalIsOpen(row, rightCol);
            if (isRightOpen) {
                int rightIndex = xyTo1D(row, rightCol);
                grid.union(index, rightIndex);
            }
        }
    }

    private void tryLowerUnion(int row, int col, int index) {
        if (row <= gridSize) {
            int belowRow = row + 1;
            boolean isBelowOpen = internalIsOpen(belowRow, col);
            if (isBelowOpen) {
                int belowIndex = xyTo1D(belowRow, col);
                grid.union(index, belowIndex);
            }
        }
    }

    private void tryLeftUnion(int row, int col, int index) {
        if (col > 1) {
            int leftCol = col - 1;
            boolean isLeftOpen = internalIsOpen(row, leftCol);
            if (isLeftOpen) {
                int leftIndex = xyTo1D(row, leftCol);
                grid.union(index, leftIndex);
            }
        }
    }

    private void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    private void setOpenState(boolean[] openState) {
        this.openState = openState;
    }
}
