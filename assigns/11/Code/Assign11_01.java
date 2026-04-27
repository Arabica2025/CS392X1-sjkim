//
// HX-2026-04-21: 50 points
//
// Please see lectures/lecture-04-21 for an
// example using DFirstEnumerate/BFirstEnumerate
//
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution should
// be able to solve "hard" Sudoku puzzles effectively.
//
import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnGtree.*;
import MyLibrary.FnA1sz.*;

class Sudoku {
    // Please find a way to represent a Sudoku puzzle
    // BFS uses queue
    // DFS uses stack
    // sudoku interface
    // 1. board representation (2d int array)
    private static final int row_num = 9;
    private static final int col_num = 9;
    private final FnA1sz<FnA1sz<Integer>> board;
    private final int empty = -1;

    // empty board constructor
    Sudoku(){
        this.board = 
    FnA1szSUtil.map_array(FnA1szSUtil.int1$make(row_num), i ->
        FnA1szSUtil.map_array(FnA1szSUtil.int1$make(col_num), j -> empty)
        );
    }


    // 2D Functional array for board config to try placing
    Sudoku(FnA1sz<FnA1sz<Integer>> board){
        this.board = board;
    }
    // random initializer

    // 2D int array input and convert to FnA1sz
    Sudoku(int[][] board){
        FnA1sz<Integer>[] rows = (FnA1sz<Integer>[])new FnA1sz[row_num];
        for (int i = 0; i < row_num; i++) {
            Integer[] row = new Integer[col_num];
            for (int j = 0; j < col_num; j++) {
                row[j] = board[i][j];
            }
            rows[i] = new FnA1sz<>(row);
        }
        this.board = new FnA1sz<>(rows);
    }


    // private getter for board at specific (row, col)
    private int getval(int row, int col){
        return board.getAt(row).getAt(col);
    }


    // place method to solve the problem
    public Sudoku tryplace(int row, int col, int setnum){
        // fork a row that we have an elemnt to change
        Integer[] newRowarr = new Integer[col_num];
        for (int i = 0; i < col_num; i++){
            newRowarr[i] = board.getAt(row).getAt(i);
        }

        // change the element we want to change
        newRowarr[col] = setnum;

        // convert the placed array to FnA1sz
        FnA1sz<Integer> newRow = new FnA1sz<>(newRowarr);

        // copy over the converted array to the original
        // create deep copied 2d array that has all elements same
        // but with the row that has changed
        // FnA1sz = 1d array
        // have bracket to store 2d array
        FnA1sz<Integer>[] newRowsArr = (FnA1sz<Integer>[]) new FnA1sz[row_num];
        for (int i = 0; i < row_num; i++) {
            newRowsArr[i] = (i == row) ? newRow : board.getAt(i);
        }
        // return the changed Sudoku board
        return new Sudoku(new FnA1sz<>(newRowsArr));
    }
    // checking if the puzzle is solved
    // elements stored in 2d array liearly
    // check if all elements are filled
    public boolean isSolved(){
        return board.forall(row ->
            row.forall(v ->
                v != empty
            )
        );
    }

    //private helper to verify rows
    private boolean isrowValid(int row, int num){
        // at given row index, iterate through all elements for horizontal validity
        return board.getAt(row).forall(v -> v != num);
    }

    // private helper to verify columns
    private boolean iscolValid(int col, int num){
        // iterate through all array at column index for vertical validity
        return board.forall(rowarr -> rowarr.getAt(col) != num);
    }

    // private helper to verify the grid
    private boolean isgridValid(int row, int col, int num){
        // Check the 3x3 grid for validity
        int gridRow = (row / 3) * 3;
        int gridCol = (col / 3) * 3;
        for (int i = gridRow; i < gridRow + 3; i++) {
            for (int j = gridCol; j < gridCol + 3; j++) {
                if (getval(i, j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // board validity checker
    public boolean isValid(int row, int col, int num){
        return 
        isrowValid(row, num)
        && iscolValid(col, num)
        && isgridValid(row, col, num);  
    }

    // have to find the empty cell to check
    // previous empty cell cache 
    public int[] findEmptyFrom(int startidx) {
        for (int i = startidx; i < row_num * col_num; i++){
            int row = i / col_num; // row updated every 9th iteration
            int col = i % col_num; // column updated every iteration

            if (getval(row,col) == empty){
                return new int[] {row, col};
            }
        }
        return null;
    }

    public void printBoard() {
        board.iforitm((row, rowArr) -> {
            rowArr.iforitm((col, num) -> {
                System.out.print(num == empty ? "? " : num + " ");
                if ((col + 1) % 3 == 0 && col != 8) System.out.print("| ");
            });
            System.out.println();
            if ((row + 1) % 3 == 0 && row != 8) System.out.println("------+-------+------");
        });
    }

}

public class Assign11_01 {

    public FnGtree<Sudoku> gTreecached(Sudoku puzzle, int startidx){
        return new FnGtree<Sudoku>(){
            @Override
            public Sudoku value(){
                return puzzle;
            }

            @Override
            public FnList<FnGtree<Sudoku>> children(){
                int[] empty = puzzle.findEmptyFrom(startidx);
                if (empty == null){
                    return FnListSUtil.nil();
                }
                int row = empty[0];
                int col = empty[1];

                // empty cell cache
                // row for every 9th iteration, column for every iteration but next would be +1 idx
                int nextstartidx = row * 9 + col + 1;
                FnList<FnGtree<Sudoku>> result = FnListSUtil.nil();
                // from 1 to 9 to place in the empty cell for checking
                for (int num = 1; num <= 9; num++){
                    if (puzzle.isValid(row, col, num)){
                        Sudoku child = puzzle.tryplace(row, col, num);
                        result = FnListSUtil.cons(gTreecached(child, nextstartidx), result);
                    }
                }
                return result;
            }
        };
    }
    public LnStrm<Sudoku> Sudoku_dfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.DFirstEnumerate(
        gTreecached(puzzle, 0)).filter0(s -> s.isSolved());
    }
    public LnStrm<Sudoku> Sudoku_bfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.BFirstEnumerate(
        gTreecached(puzzle, 0)).filter0(s -> s.isSolved());
    }
//
    public static void main (String[] args) {
	// Please add minimal testing code for Sudoku_dfs_solve
	// Please add minimal testing code for Sudoku_bfs_solve
	// return /*void*/;
        Assign11_01 sdkSolver = new Assign11_01();
        int[][] initial = {
            {9, -1, -1, -1, -1, -1, -1, 3, 1},
            {-1, 6, 1, 9, 3, 8, -1, 5, 4},
            {3, 5, 2, 4, -1, 6, -1, 8, 7},
            {-1, -1, 5, -1, 7, 3, 4, -1, -1},
            {2, -1, -1, -1, -1, -1, -1, 6, -1},
            {-1, -1, -1, 6, -1, 1, 5, 9, -1},
            {-1, 7, 6, -1, -1, -1, -1, 4, -1},
            {4, 2, -1, -1, 6, 9, -1, -1, -1},
            {-1, -1, -1, 7, -1, -1, 3, -1, -1}
        };

        Sudoku testpuzzle = new Sudoku(initial);

        System.out.println("Initial Sudoku Board:");
        testpuzzle.printBoard();

        System.out.println();
        System.out.println("DFS Sudoku Solution:");
        sdkSolver.Sudoku_dfs_solve(testpuzzle).foritm0(sol -> {
            sol.printBoard();
        });

        System.out.println();
        System.out.println("BFS Sudoku Solution:");
        sdkSolver.Sudoku_bfs_solve(testpuzzle).foritm0(sol -> {
            sol.printBoard();
        });

        // Sudoku testBoard = new Sudoku() // empty board
        // .tryplace(0,0,9)
        // .tryplace(0,7,1)
        // .tryplace(0,8,1)

        // .tryplace(1,1, 6)
        // .tryplace(1,2,1)
        // .tryplace(1,3,9)
        // .tryplace(1,4,3)
        // .tryplace(1,5,8)
        // .tryplace(1,7,5)
        // .tryplace(1,8,4)

        // .tryplace(2,0,3)
        // .tryplace(2,1,5)
        // .tryplace(2,2,2)
        // .tryplace(2,3,4)
        // .tryplace(2,5,6)
        // .tryplace(2,7,8)
        // .tryplace(2,8,7)

        // .tryplace(3,2,5)
        // .tryplace(3,4,7)
        // .tryplace(3,5,3)
        // .tryplace(3,6,4)

        // .tryplace(4,0,2)
        // .tryplace(4,7,6)
        
        // .tryplace(5,3,6)
        // .tryplace(5,5,1)
        // .tryplace(5,6,5)
        // .tryplace(5,7,9)

        // .tryplace(6,1,7)
        // .tryplace(6,2,6)
        // .tryplace(6,7,4)

        // .tryplace(7,0,4)
        // .tryplace(7,1,2)
        // .tryplace(7,4,6)
        // .tryplace(7,5,9)

        // .tryplace(8,3,7)
        // .tryplace(8,6,3);

        // System.out.println("Initial Sudoku Board:");
        // testBoard.printBoard();

        // System.out.println("Solving Sudoku with DFS:");
        // sdkSolver.Sudoku_dfs_solve(testBoard).foritm0(solve -> {
        //     solve.printBoard();
        //     System.exit(0);
        // });

        // System.out.println();
        // System.out.println("Solving Sudoku with BFS:");
        // sdkSolver.Sudoku_bfs_solve(testBoard).foritm0(solve -> {
        //     solve.printBoard();
        // });

    }
//
}
