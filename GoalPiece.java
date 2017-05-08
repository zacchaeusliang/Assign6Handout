import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }

    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        GamePiece currentPiece;
        int minYCoord, maxYCoord, minXCoord, maxXCoord;
        int nextX = this.getTopLeftX() + this.getWidth();
        int nextY = this.getTopLeftY();
        System.out.println(" nextX Y canMoveRightIn " + nextX);
        if (nextX > 6) {
            return false;
        }

        for (int i = 0; i < b.getGamePieces().length; i++) {

            currentPiece = b.getGamePieces()[i];
            if (currentPiece instanceof VerticalGamePiece) {
                System.out.println("current piece x" + currentPiece.getTopLeftX());
                minYCoord = currentPiece.getTopLeftY();
                maxYCoord = currentPiece.getTopLeftY() + currentPiece.getHeight();
                if ((nextX == currentPiece.getTopLeftX()) &&
                        ((nextY >= minYCoord) && (nextY < maxYCoord))) {
                    return false;
                }
            }

            if (currentPiece instanceof HorizontalGamePiece) {
                minXCoord = currentPiece.getTopLeftX();
                System.out.println("current piece x" + currentPiece.getTopLeftX());
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
