package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public class King extends Piece {

    public King(int x, int y, String color) {
        super(x, y, color);
        if (this.COLOR.equals("black")) {
            this.sprite = new Image("resources/blackking.png");
        }
        if (this.COLOR.equals("white")) {
            this.sprite = new Image("resources/whiteking.png");
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

        // up, down and the side steps
        if ((this.positionY + 1 == y || this.positionY - 1 == y) && (this.positionX == x || this.positionX + 1 == x || this.positionX - 1 == x)) {
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
            return Board.boardMatrix[y][x] == null;
        }

        // left and right
        if (this.positionY == y && (this.positionX + 1 == x || this.positionX - 1 == x)) {
            if (Board.boardMatrix[y][x] != null) {
                Piece piece = (Piece) Board.boardMatrix[y][x];
                return !this.COLOR.equalsIgnoreCase(piece.COLOR);
            }
            return Board.boardMatrix[y][x] == null;
        }
        return false;
    }
}
