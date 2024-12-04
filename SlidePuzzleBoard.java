import javax.swing.*;

public class SlidePuzzleBoard extends JFrame {
    private PuzzlePiece[][] board;
    private int empty_row;
    private int empty_col;
    private boolean game_on =false;
    private PuzzlePiece[][] initialState;

    public SlidePuzzleBoard() {
        board = new PuzzlePiece[4][4];

        int counter = 1;

        //create initial board
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    board[row][col] = null; //empty spot
                    empty_row = 3;
                    empty_col = 3;
                } else {
                    board[row][col] = new PuzzlePiece(counter++);
                }
            }
        }

        initialState = deepCopyBoard(board);
    }

    public PuzzlePiece[][] contents() {
        return board;
    }

    public boolean move(int w) {

        int pieceRow = -1, pieceCol = -1;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null && board[row][col].faceValue() == w) {
                    pieceRow = row;
                    pieceCol = col;
                    break;
                }
            }
        }

        if (pieceRow != -1 && pieceCol != -1 && Math.abs(empty_row - pieceRow) + Math.abs(empty_col - pieceCol) == 1) {
            //System.out.println("Moving piece " + w + " to empty slot.");

            board[empty_row][empty_col] = board[pieceRow][pieceCol];
            board[pieceRow][pieceCol] = null;

            empty_row = pieceRow;
            empty_col = pieceCol;

            return true;
        }
        //JOptionPane.showMessageDialog(null,"Move invalid for piece "+w);
        //System.out.println("Move invalid for piece " + w);
        return false;
    }

    public PuzzlePiece getPuzzlePiece(int row, int col) {
        return board[row][col];
    }

    //create shuffled slide puzzle
    public void createPuzzleBoard(){
        int[] tiles = new int[15];
        for (int i = 0;i<15;i++){
            tiles[i] = i+1;
        }
        //do {
        for (int i = tiles.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = tiles[i];
            tiles[i] = tiles[j];
            tiles[j] = temp;
        }
        //}while(!isSolvable(tiles));

        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    board[row][col] = null; // Empty spot
                    empty_row = 3;
                    empty_col = 3;
                } else {
                    board[row][col] = new PuzzlePiece(tiles[index++]);
                }
            }
        }

    }

    public boolean gameOn(){
        return game_on;
    }

    //check if the initial puzzle and finished shuffled puzzle is the same
    public boolean gameOver(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                PuzzlePiece piece1 = board[row][col];
                PuzzlePiece piece2 = initialState[row][col];
                if ((piece1 == null && piece2 != null) || (piece1 != null && piece2 == null)) {
                    return false;
                }
                if (piece1 != null && piece1.faceValue() != piece2.faceValue()) {
                    return false;
                }
            }
        }
        return true;
    }


    private PuzzlePiece[][] deepCopyBoard(PuzzlePiece[][] source) {
        PuzzlePiece[][] copy = new PuzzlePiece[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                copy[row][col] = (source[row][col] != null) ? new PuzzlePiece(source[row][col].faceValue()) : null;
            }
        }
        return copy;
    }

    private boolean isSolvable(int[] tiles) {
        int inversions = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = i + 1; j < tiles.length; j++) {
                if (tiles[i] > tiles[j]) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }

}

