package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public class Knight extends Piece {

    public Knight(int x, int y, String color) {
        super(x, y, color);
        if (this.COLOR.equals("black")) {
            this.sprite = new Image("resources/blackknight.png");
        }
        if (this.COLOR.equals("white")) {
            this.sprite = new Image("resources/whiteknight.png");
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

        // up and down hops
        if ((this.positionY + 2 == y || this.positionY - 2 == y) && (this.positionX - 1 == x || this.positionX + 1 == x)) {
            if (Board.boardMatrix[y][x] == null) {
                return true;
            }
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
        }
        // left and right hops
        if ((this.positionX + 2 == x || this.positionX - 2 == x) && (this.positionY - 1 == y || this.positionY + 1 == y)) {
            if (Board.boardMatrix[y][x] == null) {
                return true;
            }
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
        }
        return false;
    }
}
