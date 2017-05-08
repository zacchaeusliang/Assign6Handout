import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }

    public boolean canMoveLeftIn(GameBoard b) {
        GamePiece currentPiece;
        int minYCoord, maxYCoord, minXCoord, maxXCoord;
        int nextX = this.getTopLeftX() - 1;
        int nextY = this.getTopLeftY();
        System.out.println("hor move left next X Y " + nextX + " " + nextY);
        if (nextX < 0) {
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


    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        GamePiece currentPiece;
        int minYCoord, maxYCoord, minXCoord, maxXCoord;
        int nextX = this.getTopLeftX() + this.getWidth();
        int nextY = this.getTopLeftY();
        System.out.println("hor move right next X Y " + nextX + " " + nextY);

        if (nextX == 7) {
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