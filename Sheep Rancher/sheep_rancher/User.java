/**
 * Project Name : Sheep Rancher
 * Description : Defines the user class. The user has a position, lives, speed, direction
 * and a hitbox. The user is controlled by the player and has animations.
 */
package sheep_rancher;

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
public class User extends ImageView {
    private int x = 0;
    private int y = 0;
    private final int width = 64;
    private final int height = 64;
    private int points  = 0;
    private int speed = 3;
    private int lives = 3;
    public static enum Direction{
        BACK,FORWARD,LEFT,RIGHT
    }
    public User(){
        x = 0;
        y = 0;
        points = 0;
        lives = 3;
    }   
    Timeline t1 = new Timeline();
    Timeline t2 = new Timeline();
    Timeline t3 = new Timeline();
    Timeline t4 = new Timeline();
    private ImageView[][] sprites = new ImageView[4][4];
    Direction direction = Direction.FORWARD;
       
    private Rectangle hitbox = new Rectangle(x,y,width,height); //giving buffer area for collisions
        
    public void setSpeed(int s){
        speed = s;
    }
    public int getSpeed(){
        return speed;
    }
    
    public void moveLeft(){
        x -= speed;
                }
    public void moveRight (){
        x += speed;
    }
    public void moveUp(){
        y -= speed; 
    }
    public void moveDown(){
        y += speed;
    }
    
    public int getXpos(){
        return x;
    }
    public int getYpos(){
        return y;
    }
    public void updatePosition(){
        hitbox.x = x;
        hitbox.y = y;
    }
/**
 * Desc: Resets all of the necessary variables(position, hitbox, direction,lives) and stops player animation.
 * Should be called every time a new game is started. 
 * Pre: Successful definition of user object.
 * -User can not be null
 * -New game should be started
 * Post: - Position, hitbox, points, lives, and direction will be set to their to their base value.
 */   
    
    public void zero(){
        x = 0;
        y = 0;
        hitbox = new Rectangle(x,y,width,height);
        points = 0;
        lives = 3;
        direction = Direction.FORWARD;
        this.updatePosition();
        this.stopAnimate();
        
    }
    
    public int getScore(){
        return points;
    }
    
/**
 * Desc : Defines four timelines for each direction in which animations will process.
 * Plays the timeline corresponding to the current direction of the user.
 * Pre: base has to be initialized to a group of any imageView of the player
 * -Player has to be properly defined (not null)
 * -Sprite array must be set.
 * Post:
 * -The animation timeline corresponding to the player's direction will be played.
 * @param base : a group containing any imageview of the player.
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
        }       
    }
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
    public void setSprites(ImageView[][] spriteArray){
        sprites = spriteArray;
    }
    public void moveTranslate(Group g){
        
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
    public void increasePoints(){
        points += 1;
    }
    public int getPoints(){
        return points;
    }
    public void decreaseLives(){
        lives --;
    }
    public int getLives(){
        return lives;
    } 
}
