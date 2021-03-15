/**

 * Project Name : Sheep Rancher
 * Class Description : Creates the base for the background of the game.
 * An interface represents all of the objects of the background and the tile images.
 */
package sheep_rancher;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author SHYgu
 */
public class Interface extends GridPane {
    
public Interface(Image img){
    int width = 36;
    int height = 20;
       for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ImageView backgroundImg = new ImageView(img);
                this.add(backgroundImg, i, j);
            }
        } // This was created instead of just one image because in the future, different tiles can be used and randomly generated than just all grass images.

}
}
/*Class is here so that future implementations of backgrounds could contain objects such as trees and rocks..
Other maps and different tile skins can also be implemented through this class. Reserved for future implementations.
*/