package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public abstract class Piece {
    public int positionX;
    public int positionY;
    public String COLOR;
    public Image sprite;

    public Piece() {

    }

    public Piece(int x, int y, String color) {
        this.positionX = x;
        this.positionY = y;
        this.COLOR = color;
    }

    public void movePiece(int newX, int newY) {
        Board.boardMatrix[this.positionY][this.positionX] = null;
        Board.boardMatrix[newY][newX] = this;
        this.positionX = newX;
        this.positionY = newY;
    }

    public abstract boolean checkMove(int x, int y);
}
