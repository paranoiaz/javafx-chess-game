import essentials.*;
import pieces.Piece;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUI extends Application {
    private static int HEIGHT, WIDTH;
    private Stage window;
    private GridPane rootPane;
    private Scene mainScene;
    private Node lastNode;
    private Game chessGame;

    public static void main(String[] args) {
        GUI.HEIGHT = GUI.WIDTH = 600;
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("Chess Game developed by github.com/paranoiaz");
        this.window.setResizable(false);
        this.rootPane = new GridPane();
        this.mainScene = new Scene(this.rootPane, GUI.HEIGHT, GUI.WIDTH);

        // start screen
        VBox homeScreen = new VBox();
        Label gameTitle = new Label("Chess Game");
        Button startButton = new Button("Play");

        gameTitle.setFont(new Font("Tahoma", GUI.HEIGHT / 10));
        gameTitle.setTextFill(Color.LAVENDER);
        gameTitle.setStyle("-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");

        startButton.setMinWidth(GUI.WIDTH / 2);
        startButton.setMinHeight(GUI.HEIGHT / 20);
        startButton.setStyle("-fx-background-insets: 0, 0, 0, 0; -fx-background-color: #e6e6fa;");
        startButton.setFont(new Font("Tahoma", GUI.HEIGHT / 30));
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(mainScene);
                playGame();
            }
        });

        homeScreen.setAlignment(Pos.CENTER);
        homeScreen.setSpacing(GUI.HEIGHT / 20);
        homeScreen.setStyle("-fx-background-color: #9400d3");
        homeScreen.getChildren().add(gameTitle);
        homeScreen.getChildren().add(startButton);

        Scene startScene = new Scene(homeScreen, GUI.HEIGHT, GUI.WIDTH);
        this.window.setScene(startScene);
        this.window.show();
    }

    private void playGame() {
        // setting up the game
        this.chessGame = new Game();
        this.chessGame.setupPieces();
        this.chessGame.chessBoard.addTilesToBoard();
        this.chessGame.chessBoard.addPiecesToBoard(this.chessGame.playerWhite.PIECES);
        this.chessGame.chessBoard.addPiecesToBoard(this.chessGame.playerBlack.PIECES);

        // rendering the game
        this.renderTiles();
        this.renderPieces(this.chessGame.playerWhite.PIECES);
        this.renderPieces(this.chessGame.playerBlack.PIECES);

        // making gui interactive
        this.rootPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // get node from mouse position
                Node selectedNode = mouseEvent.getPickResult().getIntersectedNode();
                Integer column = GridPane.getColumnIndex(selectedNode);
                Integer row = GridPane.getRowIndex(selectedNode);

                if (row != null && column != null) {
                    Node shapeNode = getNodeFromPane(row, column);
                    if (shapeNode instanceof Rectangle) {
                        if (lastNode != null) {
                            // access rectangle object using node index
                            Rectangle lastRectangle = (Rectangle) lastNode;
                            Integer lastNodeRow = GridPane.getRowIndex(lastNode);
                            Integer lastNodeColumn = GridPane.getColumnIndex(lastNode);

                            // change tile color to original when not selected
                            if ((lastNodeRow + lastNodeColumn) % 2 == 0) {
                                lastRectangle.setFill(Color.LIGHTGREY);
                            } else {
                                lastRectangle.setFill(Color.BLUEVIOLET);
                            }
                        }


                        Piece selectedPiece = getPieceFromPlayer(row, column);
                        Piece lastPiece = null;
                        // get piece object from previous selected node if not null
                        if (lastNode != null) {
                            lastPiece = getPieceFromPlayer(GridPane.getRowIndex(lastNode), GridPane.getColumnIndex(lastNode));
                        }

                        if (lastPiece != null && chessGame.currentPlayer.COLOR.equalsIgnoreCase(lastPiece.COLOR)) {
                            if (lastPiece.checkMove(column, row)) {
                                if (selectedPiece != null) {
                                    // capture pawn if piece on selected node location
                                    if (!lastPiece.COLOR.equalsIgnoreCase(selectedPiece.COLOR)) {
                                        boolean temp = selectedPiece.COLOR.equalsIgnoreCase("black") ?
                                                chessGame.playerBlack.PIECES.remove(selectedPiece) :
                                                chessGame.playerWhite.PIECES.remove(selectedPiece);
                                    }
                                }
                                // move piece to new location and render board
                                lastPiece.movePiece(column, row);
                                rootPane.getChildren().clear();
                                renderTiles();
                                renderPieces(chessGame.playerWhite.PIECES);
                                renderPieces(chessGame.playerBlack.PIECES);

                                Player winner = chessGame.checkWinner();
                                if (winner != null) {
                                    // winner end screen
                                    VBox endScreen = new VBox();
                                    Label endTitle = new Label(String.format("Player %s has won!", winner.COLOR));

                                    endTitle.setFont(new Font("Tahoma", GUI.HEIGHT / 20));
                                    endTitle.setTextFill(Color.LAVENDER);
                                    endTitle.setStyle("-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");


                                    endScreen.setStyle("-fx-background-color: #9400d3");
                                    endScreen.getChildren().add(endTitle);
                                    endScreen.setAlignment(Pos.CENTER);
                                    window.setScene(new Scene(endScreen, GUI.HEIGHT, GUI.WIDTH));
                                }
                                chessGame.nextMove();
                            }
                        }

                        // piece highlighting for current player
                        if (selectedPiece != null) {
                            if (chessGame.currentPlayer.COLOR.equalsIgnoreCase(selectedPiece.COLOR)) {
                                ((Rectangle) shapeNode).setFill(Color.ORANGE);
                            }
                        }
                        lastNode = shapeNode;
                    }
                }
            }
        });
    }

    private void renderTiles() {
        for (int row = 0; row < Board.boardMatrix.length; row++) {
            for (int column = 0; column < Board.boardMatrix[row].length; column++) {
                String color = (row + column) % 2 == 0 ? "white" : "black";
                Tile tile = new Tile(color);
                tile.createTile(GUI.HEIGHT / Board.DIMENSION, GUI.WIDTH / Board.DIMENSION);
                this.rootPane.add(tile.TILE, row, column);
            }
        }
    }

    private void renderPieces(ArrayList<Piece> pieces) {
        for (Piece piece: pieces) {
            ImageView pieceImage = new ImageView(piece.sprite);
            pieceImage.setFitHeight(GUI.HEIGHT / Board.DIMENSION);
            pieceImage.setFitWidth(GUI.WIDTH / Board.DIMENSION);
            this.rootPane.add(pieceImage, piece.positionX, piece.positionY);
        }
    }

    private Node getNodeFromPane(int row, int column) {
        for (Node node: this.rootPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private Piece getPieceFromPlayer(int row, int column) {
        if (Board.boardMatrix[row][column] != null) {
            return (Piece) Board.boardMatrix[row][column];
        }
        return null;
    }
}
