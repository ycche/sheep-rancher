/**
 * Project Name : Sheep Rancher
 * Description : Defines a sheep class. The sheep will be able to animate, 
 * switch directions, move, and have a hitbox defined by a rectangle.
 */
package sheep_rancher;

import sheep_rancher.Constants;
import java.awt.Rectangle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author SHYgu
 */
public class Sheep extends ImageView implements Constants{
    
    private int x;
    private int y;
    private int right = 0;
    private int low = 0;
    private int actualSpeed = 3;
    
    private Rectangle hitbox = null;
    
    public ImageView[][] sprites = new ImageView[4][4];
    public static enum Direction {
        FORWARD,BACK,LEFT,RIGHT
    }
    Direction direction = Direction.LEFT;
    
    public Sheep(){
        do{
            x = (int)(SHEEP_LENGTH_X * Math.random()) + SHEEP_SPAWN_LEFT;
            y = (int)(450* Math.random());// band-aid solution to make the sheep spawn between 300 and 450
        }while(y < 300);
//basically randomPosition and randomDirection methods in the constructor.
        if(x > WIDTH/2){
            direction = Direction.LEFT;
        }
        else{
            direction = Direction.RIGHT;
        }
        
        hitbox = new Rectangle(x,y + 5,SHEEP_WIDTH,SHEEP_HEIGHT);       
    }   
    private Timeline t1 = new Timeline(); // a timeline is basically a series of pictures(keyFrames)being played at determined times. This controls animation
    private Timeline t2 = new Timeline();
    private Timeline t3 = new Timeline();
    private Timeline t4 = new Timeline(); 
    
   /** 
     * Desc : Defines four timelines for each direction in which animations will process.
     * Same concept as in player.java
 * Plays the timeline corresponding to the current direction of the sheep.
 * Pre: base has to be initialized to a group of any imageView of the sheep
 * -sheep has to be properly defined (not null)
 * -Sprite array must be set.
 * Post:
 * -The animation timeline corresponding to the sheep's direction will be played.
 * @param base : a group containing any imageview of the sheep.
 */
    public void animate(Group base){

        t1.setCycleCount(Timeline.INDEFINITE);
        
        t1.getKeyFrames().add(new KeyFrame(
            Duration.millis(1),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[0][0]);
            }));
        
        t1.getKeyFrames().add(new KeyFrame(
            Duration.millis(250),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[0][1]);
            }));
        t1.getKeyFrames().add(new KeyFrame(
            Duration.millis(500),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[0][2]);
            }));
        t1.getKeyFrames().add(new KeyFrame(
            Duration.millis(750),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[0][3]);
            }));

        t2.setCycleCount(Timeline.INDEFINITE);
        
        t2.getKeyFrames().add(new KeyFrame(
            Duration.millis(1),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[1][0]);
            }));
        
        t2.getKeyFrames().add(new KeyFrame(
            Duration.millis(250),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[1][1]);
            }));
        t2.getKeyFrames().add(new KeyFrame(
            Duration.millis(500),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[1][2]);
            }));
        t2.getKeyFrames().add(new KeyFrame(
            Duration.millis(750),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[1][3]);
            }));

        t3.setCycleCount(Timeline.INDEFINITE);
        
        t3.getKeyFrames().add(new KeyFrame(
            Duration.millis(1),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[2][0]);
            }));
        
        t3.getKeyFrames().add(new KeyFrame(
            Duration.millis(250),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[2][1]);
            }));
        t3.getKeyFrames().add(new KeyFrame(
            Duration.millis(500),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[2][2]);
            }));
        t3.getKeyFrames().add(new KeyFrame(
            Duration.millis(750),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[2][3]);
            }));

        t4.setCycleCount(Timeline.INDEFINITE);
        
        t4.getKeyFrames().add(new KeyFrame(
            Duration.millis(1),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[3][0]);
            }));
        
        t4.getKeyFrames().add(new KeyFrame(
            Duration.millis(250),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[3][1]);
            }));
        t4.getKeyFrames().add(new KeyFrame(
            Duration.millis(500),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[3][2]);
            }));
        t4.getKeyFrames().add(new KeyFrame(
            Duration.millis(750),
            (ActionEvent event) ->{
                base.getChildren().setAll(sprites[3][3]);
            }));
        //playing the timeline corresponding to the sheep's direction.
       
        switch(direction){
            case FORWARD:                
                t1.play();
                break;
            case BACK:

                t2.play();
                break;
            case LEFT:

                t3.play();
                break;
            case RIGHT:             
                t4.play();
                break;
        }      
    }
    //call stopAnimate when a direction is changed 
    public void stopAnimate(){
        t1.stop();
        t2.stop();
        t3.stop();
        t4.stop();
    }    
    public void setDirection(Direction d){
       direction = d;
    }
    public Direction getDirection(){
        return direction;
    }
    public void randomPosition(){
        //spawning the sheep between y = 300 and y = 450
        //spawns a pair of coordinates and rejects them if the y value is not contained within 300 and 450
        do{
            x = (int)(SHEEP_LENGTH_X * Math.random()) + SHEEP_SPAWN_LEFT;
            y = (int)(450 * Math.random());
        }while (y < 300);   
    }
    public void randomDirection(){
        //if x is to the right, direction is left
        if(x > WIDTH/2){
            direction = Direction.LEFT;
        }
        //if x is closer to the left, direction is right.
        else{
            direction = Direction.RIGHT;
        }
    }
    public int getPosX() {
        return x;
    }    
    public int getPosY(){
        return y;
    }
    public void setSprites(ImageView[][] spriteArray){
        sprites = spriteArray;
    }
    public void addX(){
        x += actualSpeed;  
    }
    public void subtractX(){
        x -= actualSpeed;    
    }
    public void addY(){
        y += actualSpeed;
    }
    public void subtractY(){
        y -= actualSpeed;
    }
    //every time the coordinates change, call this method
    public void updatePosition(){
        hitbox.x = x;
        hitbox.y = y;
        right = x + SHEEP_WIDTH;
        low = y + SHEEP_HEIGHT;
    }
    public Rectangle getHitbox(){
        return hitbox;  
    }
    public int getActualSpeed(){
        return actualSpeed;
    }
    public void setActualSpeed(int s){
        actualSpeed = s;
    }
    public int getRight(){
        return right;
    }
    public int getLow(){
        return low;
    }
}

    



