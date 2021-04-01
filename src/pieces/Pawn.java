package pieces;

import essentials.Board;
import javafx.scene.image.Image;


public class Pawn extends Piece {
    private boolean firstMove = true;

    public Pawn(int x, int y, String color) {
        super(x, y, color);
        if (this.COLOR.equals("black")) {
            this.sprite = new Image("resources/blackpawn.png");
        }
        if (this.COLOR.equals("white")) {
            this.sprite = new Image("resources/whitepawn.png");
        }
    }

    @Override
    public void movePiece(int newX, int newY) {
        if (this.firstMove) {
            this.firstMove = false;
        }
        super.movePiece(newX, newY);
    }

    @Override
    public boolean checkMove(int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return false;
        }
        if (this.positionX == x && this.positionY == y) {
            return false;
        }
        if (this.COLOR.equalsIgnoreCase("black")) {
            // up move with 1 step
            if (this.positionY + 1 == y && this.positionX == x) {
                if (Board.boardMatrix[y][x] == null) {
                    return true;
                }
            }
            // capture pawn by 1 step up and 1 step to the left or right
            if (this.positionY + 1 == y && (this.positionX - 1 == x || this.positionX + 1 == x)) {
                if (Board.boardMatrix[y][x] != null) {
                    Piece piece = (Piece) Board.boardMatrix[y][x];
                    return piece.COLOR.equalsIgnoreCase("white");
                }
            }
            // up move with 2 steps
            if (firstMove) {
                if (this.positionY + 2 == y && this.positionX == x) {
                    if (Board.boardMatrix[y][x] == null && Board.boardMatrix[y - 1][x] == null) {
                        return true;
                    }
                }
            }
        }
        if (this.COLOR.equalsIgnoreCase("white")) {
            if (this.positionY - 1 == y && this.positionX == x) {
                if (Board.boardMatrix[y][x] == null) {
                    return true;
                }
            }
            if (this.positionY - 1 == y && (this.positionX - 1 == x || this.positionX + 1 == x)) {
                if (Board.boardMatrix[y][x] != null) {
                    Piece piece = (Piece) Board.boardMatrix[y][x];
                    return piece.COLOR.equalsIgnoreCase("black");
                }
            }
            if (firstMove) {
                if (this.positionY - 2 == y && this.positionX == x) {
                    return Board.boardMatrix[y][x] == null && Board.boardMatrix[y + 1][x] == null;
                }
            }
        }
        return false;
    }
}
