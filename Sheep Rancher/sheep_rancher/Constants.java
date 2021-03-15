/*

Project Name : Sheep Rancher
Desc : Interface describing the constants used throughout the project.
 */
package sheep_rancher;

import java.awt.Rectangle;

/**
 *
 * @author SHYgu
 */
public interface Constants {
    int MAX_SHEEP = 7;
    int WIDTH = 1260;
    int HEIGHT = 640;
    int SHEEP_SPAWN_LEFT = 32;
    int SHEEP_SPAWN_RIGHT = 1228;
    int SHEEP_LENGTH_X = SHEEP_SPAWN_RIGHT - SHEEP_SPAWN_LEFT;
    int SHEEP_SPAWN_LENGTH = 48;// a 48x48 square where there should be no sheep in order for a sheep to spawn.
    int SHEEP_SPAWN_HIGH = 32;
    int SHEEP_SPAWN_LOW = HEIGHT - SHEEP_SPAWN_HIGH;
    int SHEEP_LENGTH_Y = SHEEP_SPAWN_LOW - SHEEP_SPAWN_HIGH; // 576
    int FENCE_HEIGHT = 225;
    int FENCE_WIDTH = 300;
    int SCORING_WIDTH = 87;
    int SCORING_HEIGHT = 10;
    int FENCE_LEFT = (WIDTH - FENCE_WIDTH)/2;
    int FENCE_RIGHT = FENCE_LEFT + 280;
    int USER_SPEED = 4;
    
    Rectangle FENCE_HITBOX = new Rectangle(FENCE_LEFT - 20,0,FENCE_WIDTH + 20,FENCE_HEIGHT - 50);
    Rectangle SCORING_ZONE = new Rectangle(FENCE_LEFT + 105, FENCE_HEIGHT - 60, SCORING_WIDTH, SCORING_HEIGHT);
    
    int BUTTON_X = 1010;
    int BUTTON_Y = 510;
    
    int BACK_BUTTON_X = 30;
    int BACK_BUTTON_Y = 510;
    
    int SHEEP_WIDTH = 52;
    int SHEEP_HEIGHT = 48;
}
