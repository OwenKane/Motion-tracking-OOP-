import java.util.ArrayList;
import java.util.Collections;
import processing.core.PApplet;
import processing.video.Capture;
import blobscanner.Detector;

public class Main extends PApplet {

  Blob blob;
  Screen screen;
  Score score;
  Object[] object = new Object[3];
  Object2[] object2 = new Object2[5];
  
  public static Capture video;
  public static Detector bd;
  public static Capture frame;
  
  public static float ballX;
  public static float ballY;
  public static int points;
  public static int lives;
  public static boolean splash;
  public static boolean start;
  public static boolean instruct;
  public static boolean end;
  int difficulty;

  
  public void setup() {
	  size(640, 480);
	  lives = 10;
	  difficulty = 3;
	  video = new Capture(this, width, height, 30);
	  bd = new Detector( this, 255 );
	  
	  score = new Score(this);
	  blob = new Blob(this);
	  screen = new Screen(this);
	  for (int i = 0; i < object.length; i++) {
		  object[i] = new Object(this);
	  }
	  for (int i = 0; i < object2.length; i++) {
		  object2[i] = new Object2(this);
	  }
	  
	  splash = true;
	  end = false;
  }

  public void draw() {
	  screen.display();
	  
	  if(splash == false && instruct == false){
		  blob.display();
		  score.update();
		  for (int i = 0; i < difficulty; i++) {
			  object2[i].enemy();
			  object2[i].dection();
		  }
		  for (int i = 0; i < 3; i++) {
			  object[i].friendly();
			  object[i].dection();
		  }		  
	  }
  }
  
  public void keyPressed() {
	  if (key == CODED) {
		    if (keyCode == RIGHT) {
		    	screen.option = screen.option + 1;
		    } else if (keyCode == LEFT) {
		    	screen.option = screen.option - 1;
		    } else if (keyCode == UP){
		    	if(screen.option == 0 && end == false && start == false){//start the game on normal
		    		splash = false;
		    		instruct = false;
		    		difficulty = 3;
		    		start = true;
		    	}
		    	if(screen.option == 2 && end == false && start == false){//start the game on hard
		    		splash = false;
		    		instruct = false;
		    		difficulty = 5;
		    		start = true;
		    	}
		    	if(screen.option == 1 && end == false && start == false){//The instruction screen
		    		splash = false;
		    		instruct = true;
		    	}
		    	if(end == true){//game over screen
		    		splash = true;
		    		instruct = false;
		    		end = false;
		    		lives = 10;
		    		points = 0;
		    		start = false;
		    	}
		    	
		    }else if (keyCode == DOWN && instruct == true){
		    	splash = true;
	    		instruct = false;
		    }
		}
  }
}