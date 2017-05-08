import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {

       /// REPLACE THE CODE BELOW WITH YOUR OWN CODE
        GamePiece currentPiece;
        int minYCoord, maxYCoord, minXCoord, maxXCoord;
        // nextY is the topY coordinate of the last button for the current piece.
        int nextY = this.getTopLeftY() + this.getHeight();
        int nextX= this.getTopLeftX();
        if (nextY < 0||nextY==7) {
            return false;
        }

        for (int i = 0; i < b.getGamePieces().length; i++) {

            currentPiece = b.getGamePieces()[i];
            if (currentPiece instanceof VerticalGamePiece) {

                minYCoord = currentPiece.getTopLeftY();
                maxYCoord = currentPiece.getTopLeftY() + currentPiece.getHeight();
                if ((nextX == currentPiece.getTopLeftX()) &&
                        ((nextY >= minYCoord) && (nextY < maxYCoord))) {
                    return false;
                }
            }

            if (currentPiece instanceof HorizontalGamePiece) {
                minXCoord = currentPiece.getTopLeftX();
                maxXCoord = currentPiece.getTopLeftX() + currentPiece.getWidth();
                if ((nextY == currentPiece.getTopLeftY()) &&
                        ((nextX >= minXCoord) && (nextX < maxXCoord))) {
                    return false;
                }
            }
        }
        return true;

    }


    public boolean canMoveUpIn(GameBoard b) {

        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        GamePiece currentPiece;
        int minYCoord, maxYCoord, minXCoord, maxXCoord;
        int nextY = this.getTopLeftY() - 1;
        int nextX= this.getTopLeftX();
        if (nextY < 0||nextY==7) {
            return false;
        }

        for (int i = 0; i < b.getGamePieces().length; i++) {

            currentPiece = b.getGamePieces()[i];
            if (currentPiece instanceof VerticalGamePiece) {

                minYCoord = currentPiece.getTopLeftY();
                maxYCoord = currentPiece.getTopLeftY() + currentPiece.getHeight();
                if ((nextX == currentPiece.getTopLeftX()) &&
                        ((nextY >= minYCoord) && (nextY < maxYCoord))) {
                    return false;
                }
            }

            if (currentPiece instanceof HorizontalGamePiece) {
                minXCoord = currentPiece.getTopLeftX();
                maxXCoord = currentPiece.getTopLeftX() + currentPiece.getWidth();
                if ((nextY == currentPiece.getTopLeftY()) &&
                        ((nextX >= minXCoord) && (nextX < maxXCoord))) {
                    return false;
                }
            }
        }
        return true;

    }


}