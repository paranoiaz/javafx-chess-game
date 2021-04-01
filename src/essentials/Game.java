package essentials;

import pieces.*;


public class Game {
    public Board chessBoard;
    public Player playerBlack;
    public Player playerWhite;
    public Player currentPlayer;

    public Game() {
        this.chessBoard = new Board();
        this.playerBlack = new Player("black");
        this.playerWhite = new Player("white");
        this.currentPlayer = this.playerWhite;
    }

    public void nextMove() {
        if (this.currentPlayer.equals(this.playerWhite)) {
            this.currentPlayer = this.playerBlack;
        }
        else if (this.currentPlayer.equals(this.playerBlack)) {
            this.currentPlayer = this.playerWhite;
        }
    }
    
    public Player checkWinner() {
        boolean blackHasKing = false;
        boolean whiteHasKing = false;
        for (Piece piece: this.playerBlack.PIECES) {
            if (piece instanceof King) {
                blackHasKing = true;
                break;
            }
        }

        for (Piece piece: this.playerWhite.PIECES) {
            if (piece instanceof King) {
                whiteHasKing = true;
                break;
            }
        }

        if (!blackHasKing) {
            return this.playerWhite;
        }
        if (!whiteHasKing) {
            return this.playerBlack;
        }
        return null;
    }

    public void setupPieces() {
        for (int row = 0; row < Board.boardMatrix.length; row++) {
            for (int column = 0; column < Board.boardMatrix[row].length; column++) {
                if (row == 1 || row == 6) {
                    boolean temp = row == 1 ? playerBlack.PIECES.add(new Pawn(column, row, playerBlack.COLOR)) :
                            playerWhite.PIECES.add(new Pawn(column, row, playerWhite.COLOR));
                }
                if (row == 0 || row == 7) {
                    if (column == 0 || column == 7) {
                        boolean temp = row == 0 ? playerBlack.PIECES.add(new Rook(column, row, playerBlack.COLOR)) :
                                playerWhite.PIECES.add(new Rook(column, row, playerWhite.COLOR));
                    }
                    if (column == 1 || column == 6) {
                        boolean temp = row == 0 ? playerBlack.PIECES.add(new Knight(column, row, playerBlack.COLOR)) :
                                playerWhite.PIECES.add(new Knight(column, row, playerWhite.COLOR));
                    }
                    if (column == 2 || column == 5) {
                        boolean temp = row == 0 ? playerBlack.PIECES.add(new Bishop(column, row, playerBlack.COLOR)) :
                                playerWhite.PIECES.add(new Bishop(column, row, playerWhite.COLOR));
                    }
                    if (column == 3) {
                        boolean temp = row == 0 ? playerBlack.PIECES.add(new Queen(column, row, playerBlack.COLOR)) :
                                playerWhite.PIECES.add(new Queen(column, row, playerWhite.COLOR));
                    }
                    if (column == 4) {
                        boolean temp = row == 0 ? playerBlack.PIECES.add(new King(column, row, playerBlack.COLOR)) :
                                playerWhite.PIECES.add(new King(column, row, playerWhite.COLOR));
                    }
                }
            }
        }
    }
}
