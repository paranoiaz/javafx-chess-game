package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public class Bishop extends Piece {

    public Bishop(int x, int y, String color) {
        super(x, y, color);
        if (this.COLOR.equals("black")) {
            this.sprite = new Image("resources/blackbishop.png");
        }
        if (this.COLOR.equals("white")) {
            this.sprite = new Image("resources/whitebishop.png");
        }
    }

    @Override
    public boolean checkMove(int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return false;
        }
        if (this.positionX == x && this.positionY == y) {
            return false;
        }
        int row;
        int column;
        int plusOrMinusX = this.positionX < x ? 1 : -1;
        int plusOrMinusY = this.positionY < y ? 1 : -1;

        for (row = this.positionY + plusOrMinusY, column = this.positionX + plusOrMinusX; row != y && column != x; row += plusOrMinusY, column += plusOrMinusX) {
            if (Board.boardMatrix[row][column] != null) {
                return false;
            }
        }
        
        // checking if x and y are on the same diagonal as current position
        if (row == y && column == x) {
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
            return Board.boardMatrix[y][x] == null;
        }
        return false;
    }
}
