package essentials;

import pieces.Piece;
import java.util.ArrayList;


public class Player {
    public String COLOR;
    public ArrayList<Piece> PIECES;

    public Player(String color) {
        this.COLOR = color;
        this.PIECES = new ArrayList<Piece>();
    }
}
