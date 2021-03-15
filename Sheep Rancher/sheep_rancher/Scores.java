/**

 * Project Name: Sheep Rancher 
 * Class Description : Defines a scoreboard as the root of a scene. It will have
 * five texts representing the scores. update() is called to sort the scores and display
 * them on the score screen.
 */
package sheep_rancher;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author SHYgu
 */
public class Scores extends Pane{
    private Text score1 = new Text(600,200,"0"); // Texts representing the five scores.
    private Text score2 = new Text(600,275,"0");
    private Text score3 = new Text(600,350,"0");
    private Text score4 = new Text(600,425,"0");
    private Text score5 = new Text(600,500,"0");
      
    ArrayList<Integer> allScores = new ArrayList<Integer>();
    private int[] topScores = new int[5];
    private int[] listToArray;
    
    public Scores(ArrayList<Integer> scores){
        score1.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,40));
        score2.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,40));
        score3.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,40));
        score4.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,40));
        score5.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,40));
        this.update(scores);
        this.addScores();
    }
    /**
     * Desc : Performs quick sort on an array
     * Pre : list can not be null
     * -Low > 0 , high < list.length
     * Post : The list will be sorted.
     * @param list : the array that will be sorted
     * @param low : lowest index of the array
     * @param high : highest index of the array.
     */
    public static void quickSort(int[] list, int low, int high) {

        final int MOVING_LEFT = 0;
        final int MOVING_RIGHT = 1;
        if (low < high) {
            int left = low;
            int right = high;
            int currentDirection = MOVING_LEFT;
            int pivot = list[low];

            while (left < right) {
                if (currentDirection == MOVING_LEFT) {
                    while ((list[right] >= pivot) && (left < right)) {
                        right--;
                    }
                    list[left] = list[right];
                    currentDirection = MOVING_RIGHT;
                }
                if (currentDirection == MOVING_RIGHT) {
                    while ((list[left] <= pivot) && (left < right)) {
                        left++;
                    }
                    list[right] = list[left];
                    currentDirection = MOVING_LEFT;

                }
            }
            list[left] = pivot;

            quickSort(list, low, left - 1);

            quickSort(list, right + 1, high);

        }
    }
/**
 * Desc : Updates the scoreboard based on the scores being passed in.Takes in all of the scores,
 * sorts them and then takes the top five scores and sets the text to those five scores.
 * Pre: - scores must have five or more elements.
 * -Scores can not be null
 * Post : The scoreboard will be updated with the new 5 top scores.
 * @param scores : an arraylist representing a running count of all scores achieved.
 */   
    
    public void update(ArrayList<Integer> scores){
        int count = 0;
        int size = scores.size();
        System.out.println(size);
        int []listToArray = new int[size];
        for(Integer a : scores){
            listToArray[count] = a;
            count++;
        }
        quickSort(listToArray,0,listToArray.length - 1);
        
        score1.setText(Integer.toString(listToArray[size - 1]));
        score2.setText(Integer.toString(listToArray[size - 2]));
        score3.setText(Integer.toString(listToArray[size - 3]));
        score4.setText(Integer.toString(listToArray[size - 4]));
        score5.setText(Integer.toString(listToArray[size - 5]));      
    }
    public void addScores(){
        super.getChildren().add(score1);
        super.getChildren().add(score2);
        super.getChildren().add(score3);
        super.getChildren().add(score4);
        super.getChildren().add(score5);
    }   
}
