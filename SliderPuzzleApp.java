import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.math.*;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame model;
    private SliderPuzzleView view;

    private GamePiece selectedPiece;
    private boolean justGrabbed;
    private int lastX;
    private int lastY;


    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);

        // Add event handlers to the inner game board buttons
        for (int w = 1; w <= (GameBoard.WIDTH); w++) {
            for (int h = 1; h <= (GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }

                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionMove(mouseEvent);

                    }


                });
            }
        }
        view.getNextBoardButton().setDisable(true);
        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                view.getNextBoardButton().setDisable(false);
                model.startBoard();
                view.update();


            }
        });

        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });

        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, -10 + SliderPuzzleView.GRID_UNIT_SIZE * (GameBoard.WIDTH + 2), 45 + SliderPuzzleView.GRID_UNIT_SIZE * (GameBoard.HEIGHT + 2)));
        primaryStage.show();

        // Update the view upon startup
        view.update();
    }


    private void handleGridSectionSelection(MouseEvent mouseEvent) {
        // FILL IN YOUR CODE
        int Selectedx = 0;
        int Selectedy = 0;
        lastX = (int) mouseEvent.getX();
        lastY = (int) mouseEvent.getY();
        for (int w = 1; w <= (GameBoard.WIDTH); w++) {

            for (int h = 1; h <= (GameBoard.HEIGHT); h++) {
                if (mouseEvent.getSource() == view.getGridSection(w, h)) {

                    Selectedx = w - 1;
                    Selectedy = h - 1;
                    justGrabbed = true;
                    //System.out.println("x" + Selectedx + "y" + Selectedy);
                }
            }

        }
        for (int i = 0; i < this.model.getCurrentBoard().getGamePieces().length; i++) {
            GamePiece Current = this.model.getCurrentBoard().getGamePieces()[i];
            // System.out.println(Current.getTopLeftX() + "  " + Current.getTopLeftY());

            if (Current instanceof VerticalGamePiece) {
                int maxY = Current.getTopLeftY() + Current.getHeight();
                if ((Current.getTopLeftX() == Selectedx) && (Selectedy >= Current.getTopLeftY()) && (Selectedy <= maxY)) {
                    selectedPiece = Current;
                    //System.out.println(selectedPiece);


                }
            }
            if (Current instanceof HorizontalGamePiece) {
                //System.out.println("hi");
                int maxX = Current.getTopLeftX() + Current.getWidth();
                if ((Current.getTopLeftY() == Selectedy) && (Selectedx >= Current.getTopLeftX()) && (Selectedx <= maxX)) {
                    selectedPiece = Current;
                    //System.out.println(selectedPiece);


                }
            }


        }


    }

    private void handleGridSectionMove(MouseEvent mouseEvent) {
        int currentGridX = (int) mouseEvent.getX();
        int currentGridY = (int) mouseEvent.getY();
        int deltax = currentGridX - lastX;
        int deltay = currentGridY - lastY;
        GameBoard current = this.model.getCurrentBoard();
        //System.out.println("x " + deltax + "y" + deltay);
        if (justGrabbed == true) {

            if (Math.abs(deltax) > view.GRID_UNIT_SIZE || Math.abs(deltay) > view.GRID_UNIT_SIZE) {
                if (selectedPiece instanceof VerticalGamePiece) {
                    if (Math.abs(deltay) > Math.abs(deltax)) {
                        if (deltay > 0 && selectedPiece.canMoveDownIn(current)) {
                            selectedPiece.moveDown();
                            // System.out.println("got here");
                            model.makeAMove();
                            lastX = (int) mouseEvent.getX();
                            lastY = (int) mouseEvent.getY();

                            view.update();
                            justGrabbed = false;
                        } else if (deltay < 0 && selectedPiece.canMoveUpIn(current)) {
                            selectedPiece.moveUp();
                            // System.out.println("got here");
                            model.makeAMove();

                            lastX = (int) mouseEvent.getX();
                            lastY = (int) mouseEvent.getY();
                            view.update();
                            justGrabbed = false;
                        }
                    }
                }


                if (selectedPiece instanceof HorizontalGamePiece) {

                    if (Math.abs(deltax) > Math.abs(deltay)) {
                        if (deltax < 0 && selectedPiece.canMoveLeftIn(current)) {
                            model.getCurrentBoard().checkCompletion(selectedPiece);
                            if (this.model.getCurrentBoard().isCompleted()) {
                                this.model.moveToNextBoard();
                                view.update();
                            }


                            selectedPiece.moveLeft();
                            //System.out.println("got here");
                            model.makeAMove();
                            lastX = (int) mouseEvent.getX();
                            lastY = (int) mouseEvent.getY();


                            view.update();
                            justGrabbed = false;
                        } else if (deltax > 0 && selectedPiece.canMoveRightIn(current)) {



                            selectedPiece.moveRight();
                          //  System.out.println("got here");

                            lastX = (int) mouseEvent.getX();
                            lastY = (int) mouseEvent.getY();
                            model.makeAMove();
                            if(selectedPiece instanceof GoalPiece){
                                model.getCurrentBoard().checkCompletion(selectedPiece);
                                System.out.println(this.model.getCurrentBoard().isCompleted());
                                if(this.model.getCurrentBoard().isCompleted())
                                    this.model.moveToNextBoard();
                                view.update();
                            }

                        }

                            view.update();
                            justGrabbed = false;

                        }
                    }
                }
            }
        }




    public static void main(String[] args) {
        launch(args);
    }
}