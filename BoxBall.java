import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BoxBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 1;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int roofPosition;
    private final int leftWall;
    private final int rightWall;
    private Canvas canvas;
    private int ySpeed = 1;
    private int xSpeed = 4;// initial downward speed

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int roofPos,int rightWallPos, 
                        int leftWallPos, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        roofPosition = roofPos;
        rightWall = rightWallPos;
        leftWall = leftWallPos;
        canvas = drawingCanvas;
        Random rand = new Random();
        do{
            xSpeed = rand.nextInt(14) - 7;
        }while(xSpeed == 0);
        
        do{
            ySpeed = rand.nextInt(14) - 7;
        }while(ySpeed == 0);
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }  
    
    /**
     *  Return whether the ball is stopped or not, if it has no
     *  speed on either axis it will be considered stopped.
     */
    public boolean isStopped()
    {
        return ySpeed == 0 && xSpeed == 0;
    }

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        //ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;
        // check if it has hits ground or roof
        if((yPosition >= (groundPosition - diameter))  && ySpeed != 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed; 
            
        }else if ((yPosition <= (roofPosition + 2))  && ySpeed != 0) {
            yPosition = (int)(roofPosition + 2);
            ySpeed = -ySpeed; 
        }
        //check if it hits right or left wall
        if ((xPosition >= (rightWall - diameter)) && xSpeed != 0){
            xPosition = (int)(rightWall - diameter);
            xSpeed = -xSpeed;
        }else if ((xPosition <= (leftWall + 2)) && xSpeed != 0){
            xPosition = (int)(leftWall + 2);
            xSpeed = -xSpeed;
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
