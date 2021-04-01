package essentials;

import pieces.Piece;
import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    public static final int DIMENSION = 8;
    public static Object[][] boardMatrix;

    public Board() {
        Board.boardMatrix = new Object[Board.DIMENSION][Board.DIMENSION];
    }

    public void addTilesToBoard() {
        for (int row = 0; row < Board.boardMatrix.length; row++) {
            Arrays.fill(Board.boardMatrix[row], null);
        }
    }

    public void addPiecesToBoard(ArrayList<Piece> playerPieces) {
        for (Piece piece: playerPieces) {
            Board.boardMatrix[piece.positionY][piece.positionX] = piece;
        }
    }
}
