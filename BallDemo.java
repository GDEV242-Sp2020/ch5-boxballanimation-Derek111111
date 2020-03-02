import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * boxBounce method creates a box and a random number of balls that
     * will bounce inside that box
     */
    public void boxBounce()
    {
        //dimensions of the box
        int topWall = 0;
        int bottomWall = 400;
        int rightWall = 450;
        int leftWall = 50;
        int[] horizontalDimentions = {topWall, bottomWall};
        int[] verticalDimentions = {rightWall, leftWall};
        ArrayList<BoxBall> ballCollection = new ArrayList<>();
        Random rand = new Random();
        
        myCanvas.setVisible(true);
        

        
        
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
            for(BoxBall ball: ballCollection){
                ball.move();
                if(!ball.isStopped()){
                    allStopped = false;
                }
            }
            if(allStopped){
                finished = true;
            }   

        }
        
    }
}
