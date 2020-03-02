import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class BoxBounce - showing animation of many balls bouncing within a box
 * as well as bouncing off the walls of the box.
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BoxBounce   
{
    private Canvas myCanvas;

    /**
     * Create a BoxBounce object. Creates a fresh canvas and makes it visible.
     */
    public BoxBounce()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }
    
    /**
     * boxBounce method creates a box and a 25 balls that
     * will bounce inside that box, each ball has a random color and random start
     * position
     */
    public void boxBounce()
    {
        //dimensions of the box
        int topWall = 0;
        int bottomWall = 400;
        int rightWall = 450;
        int leftWall = 50;
        //lists
        int[] horizontalDimentions = {topWall, bottomWall};
        int[] verticalDimentions = {rightWall, leftWall};
        ArrayList<BoxBall> ballCollection = new ArrayList<>();
        Random rand = new Random();
        
        myCanvas.setVisible(true);
        

        
        //make the 25 balls
        for(int i = 0; i < 25; i++){
            int randDiameter = rand.nextInt(20) + 5;
            int randomX = rand.nextInt
                                (rightWall - 50 - randDiameter) + (50);
            int randomY = rand.nextInt
                                (bottomWall - randDiameter);
            int randR = rand.nextInt(200);
            int randG = rand.nextInt(200);
            int randB = rand.nextInt(200);
            Color randomColor = new Color(randR,randG,randB);
            ballCollection.add(new BoxBall(randomX, randomY,randDiameter,
                                            randomColor, bottomWall,
                                            topWall,rightWall,leftWall,
                                            myCanvas));
        }
        
        

        //draw all the balls
        for(BoxBall ball: ballCollection){
                ball.draw();
        }
                //draw the box
        for(int dimention: horizontalDimentions)
        {
            myCanvas.drawLine(leftWall, dimention,rightWall, dimention);
        }
        
        for(int dimention: verticalDimentions)
        {
            myCanvas.drawLine(dimention, bottomWall,dimention, topWall);
        }
         // make them bounce
        boolean finished =  false;
        boolean allStopped;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            allStopped = true;
            //render each ball in the collection every tick
            for(BoxBall ball: ballCollection){
                ball.move();
                //checking ball forces
                if(!ball.isStopped()){
                    allStopped = false;
                }
            }
            //end loop if all balls have no force
            if(allStopped){
                finished = true;
            }   

        }
        
    }
}
