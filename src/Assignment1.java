package JavaAssignment1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import javax.swing.*;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.*;

/**
 * Use this template to create drawings in FX. Change the name of the class and
 * put your own name as author below. Change the size of the canvas and the
 * window title where marked and add your drawing code where marked.
 *
 *Jay Patel, 000881881
 */
public class JavaAssignment1 extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(600, 400); // Set canvas Size in Pixels
        stage.setTitle("FXGraphicsTemplate"); // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // YOUR CODE STARTS HERE
        gc.setFill(BLACK); //for background color
        gc.fillRect(0,0,600,400); //for background color area

        //for bigger stars
        int i;
        i=0;
        while (i<=250) {
            int a = (int)(Math.random()*600);
            int b = (int)(Math.random()*400);
            gc.setFill(WHITE);
            gc.fillOval(a,b,2,2);
            i++;
        }

        //for small stars
        i=0;
        while (i<=250) {
            int a = (int)(Math.random()*600);
            int b = (int)(Math.random()*400);
            gc.setFill(WHITE);
            gc.fillOval(a,b,1,1);
            i++;
        }

        String title;
        title = JOptionPane.showInputDialog("Please Enter Title: "); //for dialog box where we can enter title
        gc.setFill(ORANGE); //for title color in canvas
        gc.fillText(title, 550, 50); //for title printing and location

        int starCount;
        starCount = Integer.parseInt(JOptionPane.showInputDialog("How many stars do you want: ")); //for stars. (How many stars user want?)

        int[][] starCoordinates = new int[starCount][2]; //this 2d array for star number and its location (coordinates)
        int error; //error is defined below
        error = 0;
        String coordinates;

        for (int c=0; c < starCount; c++) {

            if (error == 0) {
                coordinates = JOptionPane.showInputDialog("Enter Coordinates (x,y): \n Remaining stars coordinates " + (starCount - c)); //this dialog box is for coordinates input and also for displaying remaining star coordinates
            } else {
                coordinates = JOptionPane.showInputDialog("x should be 0<x<600 & y should be 0<y<400 \n Remaining stars coordinates " + (starCount - c)); // if user input inappropriate coordinates then it will display criteria
            }


            String[] xy = coordinates.toString().split(","); // this array is for coordinates which is separated by ","
            int[] arrays = new int[2];

            //error definition:
            for (int a = 0; a < 2; a++) {
                arrays[a] = Integer.parseInt(xy[a]); //will convert string to integer
                if ((a == 0 && arrays[0] > 0 && arrays[0] < 600) || (a == 1 && arrays[1] < 400 && arrays[1] > 0) && error == 0) {
                    error = 0;
                } else {
                    error = 1;
                    c--;
                    break;
                }

            }

            if (error == 0) {
                starCoordinates[c] = arrays;

            }
        }

        //for printing stars on canvas
        for (int[] xy: starCoordinates){
            gc.setFill(ORANGE);
            gc.fillOval(xy[0], xy[1], 5, 5);
        }

        //to connect those stars by lines
        for (int count=0; count < starCoordinates.length; count++){
            gc.setStroke(GREEN);
            gc.setLineWidth(3);
            if(count == starCoordinates.length-1) {
                gc.strokeLine(starCoordinates[count][0]+2,starCoordinates[count][1]+2, starCoordinates[0][0]+2, starCoordinates[0][1]+2);
            }
            else{
                gc.strokeLine(starCoordinates[count][0]+2,starCoordinates[count][1]+2, starCoordinates[count+1][0]+2, starCoordinates[count+1][1]+2);
            }
        }

        // YOUR CODE STOPS HERE
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
