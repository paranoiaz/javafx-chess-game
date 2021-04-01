package essentials;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile {
    public Color COLOR;
    public Rectangle TILE;

    public Tile(String color) {
        if (color.equalsIgnoreCase("black")) {
            this.COLOR = Color.BLUEVIOLET;
        }
        if (color.equalsIgnoreCase("white")) {
            this.COLOR = Color.LIGHTGREY;
        }
    }

    public void createTile(int height, int width) {
        this.TILE = new Rectangle(height, width);
        this.TILE.setFill(this.COLOR);
    }
}
