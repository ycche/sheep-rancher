/**
 * Program Name : Sheep Rancher
 * Program Desc : Main class of sheep rancher. Creates all the screens of the game,
 * reads images,launches sheep rancher and contains all of the logic checking conditions per frame.
 */
package sheep_rancher;


import sheep_rancher.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author SHYgu
 */
public class SheepRancher extends Application implements Constants {

    public static void main(String[] args) {

        launch(args);
    }
    /*Specified that the pause screen does not have instructions button.
    Back button of instructions screen leads to the menu screen and more screens 
    have to be created so that back button leads goes back to the game.
    */
    
    //ArrayList
    ArrayList<Integer> scores = new ArrayList<Integer>();
    
    Pane p = new Pane();
    PrintWriter pw = null;
    FileWriter fw = null;
        
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        stage.setTitle("Sheep Rancher");
        /////////////////////////////////////////////////////////////////////
        //Reads pre-existing scores from a text file into the scores arraylist.
        FileReader fr = null;
        Scanner scanner = null;
        //Reading from text file.
        try{
            fr = new FileReader("scores.txt");
        }
        catch(IOException e){
            System.out.println("No text file found");
        }
        int score = 0;
        scanner = new Scanner(fr);
        while(scanner.hasNextInt()){
            score = scanner.nextInt();
            scores.add(score);
        }
        try{
        fw = new FileWriter("scores.txt");
        }
        catch(IOException e){}
        pw = new PrintWriter(fw);  
        
        //populates the text file with all numbers in the array when the program is closed
        stage.setOnCloseRequest(e ->{
            pw = new PrintWriter(fw);
            for(Integer a : scores){
                pw.println(a);
            }
            pw.close();
        });
        /////////////////////////////////////////////////////////////////////   
        //setting up the background, user and sheep.
        Image grassTile = readGrassImage();
        Interface background = new Interface(grassTile);
        
        FileInputStream inputstream = null;
        
        final ImageView[][] sheepSprites = readSheepSprites();
        final ImageView[][] playerSprites = readPlayerSprites();
        
        User player = new User();
        player.setSprites(playerSprites);
        
        ImageView iv = playerSprites[0][0];
        FileInputStream fenceStream = null;
        FileInputStream heartStream = null;
               
        Text t = new Text(75,624,"3");
        t.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,40));
        Text pointsText = new Text(WIDTH - 100, 30,"");
        pointsText.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,20)); // text for points
                     
        ImageView baseSheepAnimation = sheepSprites[0][0];
        Group allCharacters = new Group();// this is here so that we can fit multiple characters
        Group sheep = new Group(); // group so that we can fit the sheep.
        Group playerGroup = new Group(iv);
        
        Sheep s = new Sheep();
        s.setSprites(sheepSprites);
        Group g = new Group(baseSheepAnimation);
            
        g.setLayoutX(s.getPosX());
        g.setLayoutY(s.getPosY());
        s.animate(g);
        
        Scene gameScreen = new Scene(p, WIDTH,HEIGHT);
              
        ///////////////////////////////////////////////////////////////////////
        //Creating the pause screen
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        FileInputStream fis4 = null;
        FileInputStream fis5 = null;
        FileInputStream fis6 = null;
        Pane pausePane = new Pane();
        
        try{
            fis1 = new FileInputStream("Other_Sprites/pauseMenu.png");
            fis2 = new FileInputStream("Other_Sprites/pauseContinue.png");
        }
        catch(FileNotFoundException e){
            
        }
        Image pauseMenuImage = new Image(fis1,400,300,true,true);
        Image pauseContinueImage = new Image(fis2,400,300,true,true);
        
        ImageView pauseMenuImageView = new ImageView(pauseMenuImage);
        ImageView pauseContinueImageView = new ImageView(pauseContinueImage);
        
        Button menuButton = new Button("",pauseMenuImageView);
        menuButton.setLayoutY(50);
        menuButton.setLayoutX(400);   
        
        Button continueButton = new Button("",pauseContinueImageView);
        continueButton.setLayoutX(400);
        continueButton.setLayoutY(250);
                      
        Interface pauseBackground = new Interface(grassTile);
        pausePane.getChildren().add(pauseBackground);
        pausePane.getChildren().add(menuButton);
        pausePane.getChildren().add(continueButton);

        Scene pause = new Scene(pausePane, WIDTH, HEIGHT);
        
        ///////////////////////////////////////////////////////////////////////
        //Making main menu
        ////////////////////////////////////////////////////////////////////////
        Pane menuPane = new Pane();
        try{
             fis1 = new FileInputStream("Other_Sprites/playImage.png");
             fis2 = new FileInputStream("Other_Sprites/scores.png");
             fis3 = new FileInputStream("Other_Sprites/titleBackground.png");
             fis4 = new FileInputStream("Other_Sprites/menuSheep.png");
             fis5 = new FileInputStream("Other_Sprites/menuRancher.png");
             fis6 = new FileInputStream("Other_Sprites/howtoplay.png");
                         
        }
        catch(FileNotFoundException e){
            
        }
        Image playImage = new Image(fis1,200,300,true,true);
        Image scoreImage = new Image(fis2,230,300,true,true);
        Image backgroundImage = new Image(fis3);
        Image menuSheepImage = new Image(fis4,700,200,true,true);
        Image menuRancherImage = new Image(fis5,700,240,true,true);
        Image howtoplayImage = new Image(fis6,236,300,true,true);
        
        ImageView playDisplay = new ImageView(playImage);
        ImageView scoreDisplay = new ImageView(scoreImage);
        ImageView backgroundDisplay = new ImageView(backgroundImage);
        ImageView menuSheepImageView = new ImageView(menuSheepImage); 
        ImageView menuRancherImageView = new ImageView(menuRancherImage);
        ImageView howtoplayImageView = new ImageView(howtoplayImage);
        
        Button playButton = new Button("",playDisplay);
        
        playButton.setLayoutX(70);
        playButton.setLayoutY(440);
        
        Button scoreButton = new Button("", scoreDisplay);
        scoreButton.setLayoutX(970);
        scoreButton.setLayoutY(440);
        
        Button instructionsButton = new Button("",howtoplayImageView);
        instructionsButton.setLayoutX(525);
        instructionsButton.setLayoutY(440);
        
        menuSheepImageView.setLayoutX(350);
        menuRancherImageView.setLayoutY(160);
        menuRancherImageView.setLayoutX(265);
           
        menuPane.getChildren().add(backgroundDisplay);
        menuPane.getChildren().add(playButton);
        menuPane.getChildren().add(scoreButton);
        menuPane.getChildren().add(menuSheepImageView);
        menuPane.getChildren().add(menuRancherImageView);
        menuPane.getChildren().add(instructionsButton);
        
        Scene menuScreen = new Scene(menuPane, WIDTH,HEIGHT);
        ///////////////////////////////////////////////////////////////////////
           
        ///////////////////////////////////////////////////////////////////////
       //end screen
        try{
            fis3 = new FileInputStream("Other_Sprites/endScore.png");
        }
        catch(FileNotFoundException e){
            
        }
        Text totalPoints = new Text(520,440,"0");
        totalPoints.setFont(Font.font("Stencil",FontWeight.BOLD,FontPosture.REGULAR,160));
        totalPoints.setFill(Color.BROWN);       
        
        Pane endPane = new Pane();
        Image endScoreImage = new Image(fis3,600,450,true,true);
        ImageView endScoreImageView = new ImageView(endScoreImage);
        endScoreImageView.setLayoutX(300);
              
        fis4 = new FileInputStream("Other_Sprites/pauseMenu.png");
        Image endMenuImage = new Image(fis4,400,300,true,true);
        ImageView endMenuImageView = new ImageView(endMenuImage);
        Button endMenuButton = new Button("",endMenuImageView);
        endMenuButton.setLayoutY(450);
        endMenuButton.setLayoutX(400);
              
        endPane.getChildren().add(endScoreImageView);
        endPane.getChildren().add(totalPoints);
        endPane.getChildren().add(endMenuButton);
        Scene endMenu = new Scene(endPane,WIDTH,HEIGHT);
        
        ////////////////////////////////////////////////////////////////////////
        //score screen
        Scores scoreScreen = new Scores(scores);
        try{
            fis1 = new FileInputStream("Other_Sprites/back.png");
        }
        catch(FileNotFoundException e){}
        
        Image scoresBackImage = new Image(fis1,200,200,true,true);
        ImageView scoresBackImageView = new ImageView(scoresBackImage);
        
        Button scoresBack = new Button("",scoresBackImageView);
        scoreScreen.getChildren().add(scoresBack);
        
        Scene scoreScene = new Scene(scoreScreen,WIDTH,HEIGHT);
        
        ////////////////////////////////////////////////////////////////////////
        //How to play screens
        try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Dog_Sprites/dogForward1.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
            
        }
        
        Image next1 = new Image(fis1,200,400,true,true);
        ImageView nextImageView1 = new ImageView(next1);
        
        Image back1 = new Image(fis3,200,400,true,true);
        ImageView backImageView1 = new ImageView(back1);
        
        Pane instructions1 = new Pane();
        Text line1 = new Text(30,100,"This is you. "); 
        line1.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,90));
        
        Image dogImage = new Image(fis2,150,150,true,true);
        ImageView instructions1Dog = new ImageView(dogImage);
        instructions1Dog.setFitHeight(200);
        instructions1Dog.setFitWidth(200);
        instructions1Dog.setLayoutX(200);
        instructions1Dog.setLayoutY(200);
        
        Button back1Button = new Button("",backImageView1);
        back1Button.setLayoutX(BACK_BUTTON_X);
        back1Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton1 = new Button("",nextImageView1);
        instructionsButton1.setLayoutX(BUTTON_X);
        instructionsButton1.setLayoutY(BUTTON_Y);
        
        instructions1.getChildren().add(line1);
        instructions1.getChildren().add(instructions1Dog);
        instructions1.getChildren().add(instructionsButton1);
        instructions1.getChildren().add(back1Button);
        
        Scene instructionsScreen1 = new Scene(instructions1,WIDTH,HEIGHT);
               
        Text line2 = new Text(30,100,"This is the sheep");
        
         try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Sheep_Sprites/sheepForward1.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
         Image back2 = new Image(fis3,200,400,true,true);
        ImageView backImageView2 = new ImageView(back2);
                 
        Image next2 = new Image(fis1,200,400,true,true);
        ImageView nextImageView2 = new ImageView(next2);
        
        Pane instructions2 = new Pane();
        
        line2.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,90));
        
        Image sheepImage = new Image(fis2,150,150,true,true);
        ImageView instructions2sheep = new ImageView(sheepImage);
        
        instructions2sheep.setFitHeight(200);
        instructions2sheep.setFitWidth(200);
        instructions2sheep.setLayoutX(200);
        instructions2sheep.setLayoutY(200);
        
        Button back2Button = new Button("",backImageView2);
        back2Button.setLayoutX(BACK_BUTTON_X);
        back2Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton2 = new Button("",nextImageView2);
        instructionsButton2.setLayoutX(BUTTON_X);
        instructionsButton2.setLayoutY(BUTTON_Y);
        
        instructions2.getChildren().add(line2);
        instructions2.getChildren().add(instructions2sheep);
        instructions2.getChildren().add(instructionsButton2);
        instructions2.getChildren().add(back2Button);
        
        Scene instructionsScreen2 = new Scene(instructions2,WIDTH,HEIGHT);
        
        Text line3 = new Text(30,100,"Collide with the sheep to change their direction");
        
         try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Other_Sprites/menuDemo3.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
         
         Image back3 = new Image(fis3,200,400,true,true);
        ImageView backImageView3 = new ImageView(back3);
        
        Image next3 = new Image(fis1,200,400,true,true);
        ImageView nextImageView3 = new ImageView(next3);
        
        Pane instructions3 = new Pane();
        
        line3.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,50));
        
        Image menuDemo3 = new Image(fis2,550,350,false,false);
        ImageView instructions3Image = new ImageView(menuDemo3);
        
        instructions3Image.setLayoutX(200);
        instructions3Image.setLayoutY(200);
        
        Button back3Button = new Button("",backImageView3);
        back3Button.setLayoutX(BACK_BUTTON_X);
        back3Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton3 = new Button("",nextImageView3);
        instructionsButton3.setLayoutX(BUTTON_X);
        instructionsButton3.setLayoutY(BUTTON_Y);
               
        instructions3.getChildren().add(line3);
        instructions3.getChildren().add(instructions3Image);
        instructions3.getChildren().add(instructionsButton3);
        instructions3.getChildren().add(back3Button);
        
        Scene instructionsScreen3 = new Scene(instructions3,WIDTH,HEIGHT);
                
        Text line4 = new Text(30,100,"Move by pressing W/A/S/D \n W to go forward, S to go back \n A to go Left, D to go backward");
        
        try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
        Image back4 = new Image(fis3,200,400,true,true);
        ImageView backImageView4 = new ImageView(back4);
        
        Image next4 = new Image(fis1,200,400,true,true);
        ImageView nextImageView4 = new ImageView(next4);
        
        Pane instructions4 = new Pane();
        
        line4.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,75));
        
        Button back4Button = new Button("",backImageView4);
        back4Button.setLayoutX(BACK_BUTTON_X);
        back4Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton4 = new Button("",nextImageView4);
        instructionsButton4.setLayoutX(BUTTON_X);
        instructionsButton4.setLayoutY(BUTTON_Y);
        
        instructions4.getChildren().add(line4);
        instructions4.getChildren().add(instructionsButton4);
        instructions4.getChildren().add(back4Button);
        
        Scene instructionsScreen4 = new Scene(instructions4,WIDTH,HEIGHT);
        
        Text line5 = new Text(30,100,"You get one point for herding the sheep into the pen. \n Try to get as many points as possible!");
                
         try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Other_Sprites/menuDemo5.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
         Image back5 = new Image(fis3,200,400,true,true);
        ImageView backImageView5 = new ImageView(back5);
        
        Image next5 = new Image(fis1,200,400,true,true);
        ImageView nextImageView5 = new ImageView(next5);
        
        Pane instructions5 = new Pane();
        
        line5.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,45));
        
        Image menuDemo5 = new Image(fis2,550,350,false,false);
        ImageView instructions5Image = new ImageView(menuDemo5);
        
        instructions5Image.setLayoutX(200);
        instructions5Image.setLayoutY(200);
        
        Button back5Button = new Button("",backImageView5);
        back5Button.setLayoutX(BACK_BUTTON_X);
        back5Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton5 = new Button("",nextImageView5);
        instructionsButton5.setLayoutX(BUTTON_X);
        instructionsButton5.setLayoutY(BUTTON_Y);
        
        instructions5.getChildren().add(line5);
        instructions5.getChildren().add(instructions5Image);
        instructions5.getChildren().add(instructionsButton5);
        instructions5.getChildren().add(back5Button);
        
        Scene instructionsScreen5 = new Scene(instructions5,WIDTH,HEIGHT);
        
        Text line6 = new Text(30,100,"You lose one life if the sheep goes out of bounds.");
        try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Other_Sprites/menuDemo6.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
        Image back6 = new Image(fis3,200,400,true,true);
        ImageView backImageView6 = new ImageView(back6);
        
        Image next6 = new Image(fis1,200,400,true,true);
        ImageView nextImageView6 = new ImageView(next6);
        
        Image demoImage6 = new Image(fis2,600,450,true,true);
        
        ImageView demoImageView6 = new ImageView(demoImage6);
        demoImageView6.setLayoutY(200);
        Pane instructions6 = new Pane();
        
        line6.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,45));
        
        Button back6Button = new Button("",backImageView6);
        back6Button.setLayoutX(BACK_BUTTON_X);
        back6Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton6 = new Button("",nextImageView6);
        instructionsButton6.setLayoutX(BUTTON_X);
        instructionsButton6.setLayoutY(BUTTON_Y);
        
        instructions6.getChildren().add(line6);
        instructions6.getChildren().add(instructionsButton6);
        instructions6.getChildren().add(back6Button);
        instructions6.getChildren().add(demoImageView6);
        
        Scene instructionsScreen6 = new Scene(instructions6,WIDTH,HEIGHT);
        
        ////////////////////////////////////////////////////////////////////////
        //Menu 7
        
        Text line7 = new Text(30,100,"Press p to pause the game" +"\n"+ "Beware, the hitboxes are large.");
        try{
            fis1 = new FileInputStream("Other_Sprites/next.png");
            fis2 = new FileInputStream("Other_Sprites/menuDemo4.png");
            fis3 = new FileInputStream("Other_Sprites/back.png");
        }catch(FileNotFoundException e){
             System.out.println("misspelled file");
        }
        
        Image back7 = new Image(fis3,200,400,true,true);
        ImageView backImageView7 = new ImageView(back7);
        
        Image demoImage7 = new Image(fis2,500,375,true,true);
        ImageView demoImageView7 = new ImageView(demoImage7);
        demoImageView7.setLayoutX(400);
        demoImageView7.setLayoutY(300);
        
        Image next7 = new Image(fis1,200,400,true,true);
        ImageView nextImageView7 = new ImageView(next7);
        
        Pane instructions7 = new Pane();
        
        line7.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,60));
        
        Button back7Button = new Button("",backImageView7);
        back7Button.setLayoutX(BACK_BUTTON_X);
        back7Button.setLayoutY(BACK_BUTTON_Y);
        
        Button instructionsButton7 = new Button("",nextImageView7);
        instructionsButton7.setLayoutX(BUTTON_X);
        instructionsButton7.setLayoutY(BUTTON_Y);
        
        instructions7.getChildren().add(line7);
        instructions7.getChildren().add(instructionsButton7);
        instructions7.getChildren().add(back7Button);
        instructions7.getChildren().add(demoImageView7);
        Scene instructionsScreen7 = new Scene(instructions7,WIDTH,HEIGHT);
        
        //setting button functionality on the instruction screens.
        instructionsButton1.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen2);
        });
        instructionsButton2.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen3);
        });
        instructionsButton3.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen4);
        });
        instructionsButton4.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen5);
        });
        instructionsButton5.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen6);
        });
        instructionsButton6.setOnMouseClicked(e ->{
            stage.setScene(instructionsScreen7);
        });
        instructionsButton7.setOnMouseClicked(e ->{
            stage.setScene(menuScreen);
        });
        
        back1Button.setOnMouseClicked(e->{
            stage.setScene(menuScreen);
        });
        back2Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen1);
        });
        back3Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen2);
        });
        back4Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen3);});
       
        back5Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen4);
        });
        back6Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen5);
        });
        back7Button.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen6);
        });
        /*main loop of the program... Every frame the handle method is called.
        -Every frame checks for :
            -If player lives are 0
            -Whether a key is pressed
            -If there is a collision between sheep and player.
            -Collision between player,sheep and the boundaries or fence.
            -Whether the sheep has scored or sheep has went out of bounds.
            -Every five frames, move user.
            -Every ten frames, move sheep.
            -uses incrementing variables to determine how many frames have passed.
            -60 frames per second.
        */     
        AnimationTimer timer = new AnimationTimer() {
            // this thing is used to spawn the sheep
            long tick = 0;
            boolean collide = false;
            int wait = 300; // fontrols buffer for collision between sheep and player.
            int buffer = 120;
            int keyBuffer = 45;
            boolean recentKey = false;
            boolean recentCollision = false;
            boolean sheepInPen = false;
            boolean sheepOutOfBounds = false; // used by the sheepedgedetection method
            KeyCode lastKey = KeyCode.ALT;

            @Override
            public void handle(long now) {
                pointsText.setText(Integer.toString(player.getPoints()));
                setDisplayLives(player,t);
                
                if(player.getLives() == 0){ // Testing if player lives are gone.
                    pw = new PrintWriter(fw);
                    scores.add(player.getScore());
                    totalPoints.setText(Integer.toString(player.getPoints()));
                    for(Integer i : scores){
                        pw.println(i);
                    }
                    stage.setScene(endMenu);
                    scoreScreen.update(scores);
                    pw.close();
                    
                    this.stop();
                    
                }               
                //collisions
                collide = detectCollision(s,player);
                //Testing if a key is being pressed.
                gameScreen.setOnKeyPressed(e -> {
                    if(keyBuffer == 45){
                        if(!lastKey.equals(e.getCode())){
                            player.stopAnimate();
                        }                       
            switch (e.getCode()) {
                
                case W:
                    player.setDirection(User.Direction.BACK);
                    recentKey = true;
                    lastKey = KeyCode.W;
                    break;
                case S:
                    player.setDirection(User.Direction.FORWARD);
                    recentKey = true;
                    lastKey = KeyCode.S;
                    break;
                case A:
                    recentKey = true;
                    player.setDirection(User.Direction.LEFT);
                    lastKey = KeyCode.A;
                    break;
                case D:
                    recentKey = true;
                    player.setDirection(User.Direction.RIGHT);
                    lastKey = KeyCode.D;
                    break;                    
                case P:
                    stage.setScene(pause);
                    player.setSpeed(0);
                    s.setActualSpeed(0);
                    this.stop();
                    break;
            }}});
                //Testing whether there is a collision between sheep and user
                if(collide && wait == 300){ // 5 seconds for each collision
                    player.setSpeed(0);
                    wait = 0;
                    s.setActualSpeed(5);
                    recentCollision = true;
                    s.stopAnimate();
                    player.stopAnimate();

                    switch(player.getDirection()){
                        case FORWARD:
                            s.direction = Sheep.Direction.FORWARD;
                            player.direction = User.Direction.BACK;
                            s.animate(g);
                            break;
                        case BACK:
                            s.direction = Sheep.Direction.BACK;
                            player.direction = User.Direction.FORWARD;
                            s.animate(g);
                            break;
                        case LEFT:
                            s.direction = Sheep.Direction.LEFT;
                            player.direction = User.Direction.RIGHT;
                            s.animate(g);
                            break;
                        case RIGHT:
                            s.direction = Sheep.Direction.RIGHT;
                            player.direction = User.Direction.LEFT;
                            s.animate(g);
                            break;
                    }
                //waiting after a collision    
                }if (wait == 300 && recentCollision){
                    
                    player.setSpeed(2);
                    s.setActualSpeed(3);
                    recentCollision = false;
                }
                //If the sheep enters the pen
                if(sheepEnterPen(s)){
                    player.increasePoints();
                    sheepInPen = true;
                }
                else{
                    sheepInPen = false;
                }
                //If the sheep collides with the fence.
                if(fenceCollisionSheep(s) && buffer == 120){ // do a buffer...
                    buffer = 0;
                    //checking the direction of the sheep to test if the collision should be counted.
                    if(s.direction == Sheep.Direction.RIGHT){
                        s.direction = Sheep.Direction.LEFT;
                        s.stopAnimate();
                        s.animate(g);
                        for (int i = 0; i < 10; i++) {
                            s.subtractX();
                        }
                        
                        s.updatePosition();
                        moveSheep(g,s);
                    }
                    else if(s.direction == Sheep.Direction.LEFT){
                        s.direction = Sheep.Direction.RIGHT;
                        s.stopAnimate();
                        s.animate(g);
                        for (int i = 0; i < 10; i++) {
                            s.addX();
                        }
                        s.updatePosition();
                        moveSheep(g,s);
                    }
                    else if(s.direction == Sheep.Direction.BACK){
                        s.direction = Sheep.Direction.FORWARD;
                        s.stopAnimate();
                        s.animate(g);
                        for (int i = 0; i < 10; i++) {
                            s.addY();
                        }
                        s.updatePosition();
                        moveSheep(g,s);
                    }
                    // check if we have to do buffer.
                   
                }
                //If player collides with the edge.
                if(playerEdgeCollision(player)){
                    player.setSpeed(0);
                }
                
                else if (!recentCollision){
                    player.setSpeed(USER_SPEED);
                    
                }
                //if the player collides with the fence... and tests if there is a fence straight from the player and not to the side of it.
                if(fenceCollisionPlayer(player)){
                    
                    if(player.direction == User.Direction.RIGHT && player.getXpos() < FENCE_LEFT){
                        player.setSpeed(0);
                        for (int i = 0; i < 10; i++) {
                            player.moveLeft();
                            player.updatePosition();
                        }
                    }
                    else if(player.direction == User.Direction.LEFT && player.getXpos() > FENCE_RIGHT){
                        player.setSpeed(0);
                        for (int i = 0; i < 10; i++) {
                            player.moveRight();
                            player.updatePosition();
                        }
                        
                    }
                    else if(player.direction == User.Direction.BACK){
                        player.setSpeed(0);
                        for (int i = 0; i < 10; i++) {
                            player.moveDown();
                            player.updatePosition();
                        }
                    }

                    
                }
                else if(wait == 120 && !fenceCollisionPlayer(player)){
                    player.setSpeed(USER_SPEED);
                }
                
                sheepOutOfBounds = sheepEdgeCollision(s);
                //Checking if sheep is out of bounds or in the pen.. does the same steps for both cases.
                if(sheepOutOfBounds || sheepInPen){
                    // Instead of making a new sheep every time... we can just change the sheep's coordinates so it looks like a new sheep.
                    s.randomPosition();
                    s.randomDirection();
                    wait = 0;
                    s.updatePosition();
                    g.setLayoutX(s.getPosX());
                    g.setLayoutY(s.getPosY());
                    s.stopAnimate();
                    if(sheepOutOfBounds){
                        player.decreaseLives();
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                    s.animate(g);                    
                }
                //moves the sheep.             
                if(s.getActualSpeed() != 0){
                    if(tick % 10 == 0){ // calls 6 times per second
                        moveSheep(g,s);                   
                        s.updatePosition();
                    }
                }
                //moves the player
                if(tick % 5 == 0){ // default calls 12 times per second
                    moveUser(player,playerGroup);
                    if(recentKey){
                        keyBuffer = 0;
                        recentKey = false;
                    }
                    player.updatePosition();
                }   
                
                wait++;
                wait = Math.min(wait, 300);
                tick++;
                buffer ++;
                buffer = Math.min(buffer, 120);

                keyBuffer++;
                keyBuffer = Math.min(keyBuffer,45);               
            }
        };
        timer.start();       
//////////////////////////////////////////////////////////////////////////////// 
//Finalizing the main screen.
        try{
            fenceStream = new FileInputStream("Other_Sprites/fenceSprite.png");
            heartStream = new FileInputStream("Other_Sprites/heartSprite.png");
        }
        catch(FileNotFoundException e){
            
        }
            Image fenceImage = new Image(fenceStream,300,225,true,true);
            ImageView fenceImageView = new ImageView(fenceImage);
            fenceImageView.setLayoutX(FENCE_LEFT);
            
            Image heartImage = new Image(heartStream,64,64,true,true);
            ImageView heartImageView = new ImageView(heartImage);
            heartImageView.setLayoutX(0);
            heartImageView.setLayoutY(HEIGHT - 64);
       
        allCharacters = new Group(g,playerGroup);
        //Have to add the sheep and the user in one line or else it won't work.

        p.getChildren().add(background);
        p.getChildren().add(fenceImageView);
        p.getChildren().add(allCharacters);
        p.getChildren().add(heartImageView);
        p.getChildren().add(t);
        p.getChildren().add(pointsText);
////////////////////////////////////////////////////////////////////////////////

/*button functionality for the rest of the buttons. Down here because we defined 
the scenes up top and these commands reference those scenes.
*/
        //Checks for the menu button when game ends is clicked
        endMenuButton.setOnMouseClicked(e -> stage.setScene(menuScreen));
        
        //Checks for the menu button when the game is paused is clicked.
        menuButton.setOnMouseClicked(e -> {stage.setScene(menuScreen);
        scores.add(player.getScore());
        scoreScreen.update(scores);
        });
        //checks if continue button is pressed during pause screen.
        continueButton.setOnMouseClicked(e ->{
            stage.setScene(gameScreen);
            timer.start();
                });
        //checks if the play button is pressed
        playButton.setOnMouseClicked(e ->{ 
            stage.setScene(gameScreen);
            player.zero();
            playerGroup.setLayoutX(player.getXpos());
            playerGroup.setLayoutY(player.getYpos()); 
            timer.start();
                });
        //checks if the scores button is pressed
        scoreButton.setOnMouseClicked(e->{
            stage.setScene(scoreScene);
        });
        //checks if the back button at the scores screen is pressed.
        scoresBack.setOnMouseClicked(e->{
            stage.setScene(menuScreen);
        });
        //checks if the instructions button is pressed.
        instructionsButton.setOnMouseClicked(e->{
            stage.setScene(instructionsScreen1);
        });
        
        //p.getChildren().add(player);
         gameScreen.setOnKeyReleased(e -> {
            player.stopAnimate();
        });

               
        stage.setScene(menuScreen);

        stage.show();
    }
    
    /**
     * Desc : Compares the values of the coordinates of the sheep compared to the edge coordinates
     * and determines whether the sheep has traveled out of bounds.
     * Pre : Sheep s has to be properly defined ( not null)
     * Post : False will be returned if the sheep is in bounds, else true will be returned
     * @param s : The sheep that the method will take the coordinates of
     * @return true if out of bounds, false if in bounds
     */

    public boolean sheepEdgeCollision(Sheep s){ // Detects when a sheep hits the boundaries of the game.
        if(s.getHitbox().x < -20){ // the 20s are there because we want the sheep to go out of bounds.
            return true;           
        }
        if(s.getHitbox().x > WIDTH + 20){
            return true;
        }
        if(s.getHitbox().y < -20){
            return true;
        }
        if(s.getHitbox().y > HEIGHT + 20){
            return true;
        }
        return false;
    }
/**
 * Desc : Detects whether a player is colliding with the edge of the screen and 
 * direction means the player will run towards the edge.
 * Pre : User p has to be defined(not null)
 * Post : True is returned if the player is about to go out of bounds, else false is returned
 * @param p the player that is on the screen
 * @return true if about to to the edge, false otherwise.
 */
    public static boolean playerEdgeCollision(User p){
        if(p.getHitbox().x < 0 && p.direction == User.Direction.LEFT){
            return true;
        }
        if(p.getHitbox().x > WIDTH - 60 && p.direction == User.Direction.RIGHT){
            return true;
        }
        if(p.getHitbox().y < 0 && p.direction == User.Direction.BACK){
            
            return true;
        }
        if(p.getHitbox().y > HEIGHT - 56 && p.direction == User.Direction.FORWARD){
            return true;
        }
        return false;
    }
    
    public static boolean detectCollision(Sheep s, User p){
        //detects a collision between sheep and user.
        if(p.getHitbox().intersects(s.getHitbox())){
            return true;
        }
        return false;
    }
    
    public static boolean fenceCollisionSheep(Sheep s){  
        if(s.getHitbox().intersects(FENCE_HITBOX)){
            return true;
        }     
        return false;
    }
    
    public static boolean fenceCollisionPlayer(User p){ 
       if(p.getHitbox().intersects(FENCE_HITBOX)){
            return true;
        }
        return false;
    }
    
    public static boolean sheepEnterPen(Sheep s){ // check this before checking fenceCollisionSheep
        if(s.getHitbox().intersects(SCORING_ZONE)){
            return true;
        }
        return false;
    }
    /**
     * Translates the x-coordinate of the sheep, and the x-coordinate of the 
     * picture of the sheep by the speed in the current direction of the sheep
     * Pre : Sheep has to be properly defined (not null)
     * The group(base) of the sheep animation has to be initialized to a blank sheep image.
     * Speed has to be positive
     * Post : The sheep and the picture of the sheep is moved by "speed" coordinates based on the direction of the sheep.
     * @param gr : A group containing a sheep ImageView acting as the base of sheep animation.
     * @param s : The sheep that is currently on the board.
     */
    
    public static void moveSheep(Group gr, Sheep s){
        
        switch(s.direction){
            case FORWARD:
                gr.setTranslateY(s.getActualSpeed());
                gr.translateYProperty();
                gr.setLayoutY(gr.getLayoutY() + gr.getTranslateY());
                s.addY();
                break;
            case BACK:
                gr.setTranslateY(-s.getActualSpeed());
                gr.translateYProperty();
                gr.setLayoutY(gr.getLayoutY() + gr.getTranslateY());
                s.subtractY();
                break;
            case LEFT:
                gr.setTranslateX(-s.getActualSpeed());
                gr.translateXProperty();
                gr.setLayoutX(gr.getLayoutX() + gr.getTranslateX());
                s.subtractX();
                break;
            case RIGHT:
                gr.setTranslateX(s.getActualSpeed());
                gr.translateXProperty();
                gr.setLayoutX(gr.getLayoutX() + gr.getTranslateX());   
                s.addX();
        }
    }
        /**
     * Translates the x-coordinate of the player, and the x-coordinate of the 
     * picture of the player by the speed in the current direction of the player
     * Pre : Player has to be properly defined (not null)
     * The group(base) of the player animation has to be initialized to a blank player image.
     * Speed has to be positive
     * Post : The player and the picture of the player is moved by "speed" coordinates based on the direction of the player.
     * @param gr : A group containing a player ImageView acting as the base of player animation.
     * @param p : The player that is currently on the board.
     */
    

    public static void moveUser(User p, Group gr){
        
        switch (p.direction) {
                case BACK:
                    p.setDirection(User.Direction.BACK);                    
                    p.moveUp();
                    p.animate(gr);
                    gr.setTranslateY(-p.getSpeed());
                    gr.translateYProperty();
                    gr.setLayoutY(gr.getLayoutY() + gr.getTranslateY());
                    p.updatePosition();
                    break;
                case FORWARD:
                    p.moveDown();
                    p.setDirection(User.Direction.FORWARD);
                    p.animate(gr);
                    gr.setTranslateY(p.getSpeed());
                    gr.translateYProperty();
                    gr.setLayoutY(gr.getLayoutY() + gr.getTranslateY());
                    p.updatePosition();
                    break;
                case LEFT:
                    p.moveLeft();
                    p.setDirection(User.Direction.LEFT);
                    p.animate(gr);
                    gr.setTranslateX(-p.getSpeed());
                    gr.translateXProperty();
                    gr.setLayoutX(gr.getLayoutX() + gr.getTranslateX());
                    p.updatePosition();
                    break;
                case RIGHT:
                    p.moveRight();
                    p.setDirection(User.Direction.RIGHT);
                    p.animate(gr);
                    gr.setTranslateX(p.getSpeed());
                    gr.translateXProperty();
                    gr.setLayoutX(gr.getLayoutX() + gr.getTranslateX());
                    p.updatePosition();
            }       
    }
    
    public static void setDisplayLives(User p, Text text){
        switch(p.getLives()){
            case 3:
                text.setText("3");
                break;
            case 2: 
                text.setText("2");
                break;
            case 1:
                text.setText("1");
                break;
            case 0:
                text.setText("0");
                break;
        }
    }    
    public static Image readGrassImage() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Other_Sprites/Grass_Sprite.png");
        } catch (FileNotFoundException e) {
        }
        Image i = new Image(fis);
        return i;
    }
    
    public static Image readBaseSheepImage(){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("Sheep_Sprites/sheepForward1.png");
        } catch(FileNotFoundException e){     
        }
            Image i = new Image(fis);
            return i;    
    }
        
    /**
     * Desc : Reads the sheep sprites into images and then assigns each image to an element in a 2d imageview array.
     * Pre : The files need to be named properly, files need to exist in the Sheep_Sprites forward with the same name.
     * Post : A 2d imageview array containing the sheep sprites will be returned.
     * @return 4x4 2d array of the sheep sprites
     * @throws FileNotFoundException 
     */

    public static ImageView[][] readSheepSprites() throws FileNotFoundException {
        FileInputStream inputstream = null;
        Image img = null;
        Image[][] sprites = new Image[4][4];
        ImageView[][] spriteImages = new ImageView[4][4];
        ImageView image = null;
        //Uses 2D arrays

        inputstream = new FileInputStream("Sheep_Sprites/sheepForward1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][0] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepForward2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][1] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepForward3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][2] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepForward4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][3] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepBackward1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][0] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepBackward2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][1] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepBackward3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][2] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepBackward4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][3] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepLeft1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][0] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepLeft2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][1] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepLeft3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][2] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepLeft4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][3] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepRight1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][0] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepRight2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][1] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepRight3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][2] = img;
        inputstream = new FileInputStream("Sheep_Sprites/sheepRight4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][3] = img;

        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites.length; j++) {
                image = new ImageView(sprites[i][j]);
                spriteImages[i][j] = image;
            }
        }

        return spriteImages;

    }
    
    /**
     * Desc : Reads the player sprites into images and then assigns each image to an element in a 2d imageview array.
     * Pre : The files need to be named properly, files need to exist in the Dog_Sprites forward with the same name.
     * Post : A 2d imageview array containing the player sprites will be returned.
     * @return 4x4 2d array of the player sprites
     * @throws FileNotFoundException 
     */

    public static ImageView[][] readPlayerSprites() throws FileNotFoundException {
        FileInputStream inputstream = null;
        Image img = null;
        Image[][] sprites = new Image[4][4];
        ImageView[][] spriteImages = new ImageView[4][4];
        ImageView image = null;

        inputstream = new FileInputStream("Dog_Sprites/dogForward1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][0] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogForward2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][1] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogForward3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][2] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogForward4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[0][3] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogBackward1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][0] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogBackward2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][1] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogBackward3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][2] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogBackward4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[1][3] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogLeft1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][0] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogLeft2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][1] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogLeft3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][2] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogLeft4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[2][3] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogRight1.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][0] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogRight2.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][1] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogRight3.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][2] = img;
        inputstream = new FileInputStream("Dog_Sprites/dogRight4.png");
        img = new Image(inputstream, 64, 64, false, false);
        sprites[3][3] = img;

        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites.length; j++) {
                image = new ImageView(sprites[i][j]);
                spriteImages[i][j] = image;
            }
        }
        return spriteImages;
    }
}
