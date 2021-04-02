package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public class Rook extends Piece {

    public Rook(int x, int y, String color) {
        super(x, y, color);
        if (this.COLOR.equals("black")) {
            this.sprite = new Image("resources/blackrook.png");
        }
        if (this.COLOR.equals("white")) {
            this.sprite = new Image("resources/whiterook.png");
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

        // checking horizontally
        if (this.positionY == y) {
            int plusOrMinus = this.positionX < x ? 1 : -1;
            for (int newX = this.positionX + plusOrMinus; newX != x; newX += plusOrMinus) {
                if (Board.boardMatrix[y][newX] != null) {
                    return false;
                }
            }
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
            return Board.boardMatrix[y][x] == null;
        }

        // checking vertically
        if (this.positionX == x) {
            int plusOrMinus = this.positionY < y ? 1 : -1;
            for (int newY = this.positionY + plusOrMinus; newY != y; newY += plusOrMinus) {
                if (Board.boardMatrix[newY][x] != null) {
                    return false;
                }
            }
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
            return Board.boardMatrix[y][x] == null;
        }
        return false;
    }
}
