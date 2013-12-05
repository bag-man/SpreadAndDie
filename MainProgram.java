import java.util.Random;
import java.util.Scanner;

public class MainProgram
{
  private static int score = 0, level = 4, regions, diseaseY, diseaseX;
  private static boolean mutation = false, cure = false, gameOver = false;
  private static Random rand = new Random();
  private static Player pl;
  private static Board bd;
  private static Disease ick;

  public static void main(String[] args) 
  {
    System.out.print("Enter the number of regions on the board [2 - 4]: ");
    regions = Keyboard.readInt();

    //Validation
    while(regions <2 || regions >4)
    {  
      System.out.print("Pick betwen 2 and 4 please: ");
      regions = Keyboard.readInt();
    }

    createGame();
    playGame();
  }

  public static void playGame() 
  {
    System.out.println("Press enter to move the player randomly");

    while(endGame() != true) //Run till endgame conditions
    {
      pl.updatePlayer(level);

      if(pl.checkCure() == true) //Check cure for level 4
      {
	cure = true;
	if (rand.nextInt(5) == 1) //Randomise disease mutation
	{
	  cure = false;
	  pl.setTillCure();
	  mutation = true;
	}
      }

      if(cure == false)
      {
	ick.updateDisease(); //Update the disease for all levels
      }

      //Display the board and info
      bd.printBoard();
      System.out.println("\n\nScore: " + score);
      System.out.println("Level: " + level);

      //Display cure info on level 4
      if(level == 4 && mutation == false)
      {
	if(pl.getTillCure() <= 0)
	{
	  System.out.println("The disease has been stopped, lets hope it doesn't mutate");

	} else {
	  System.out.println("Turns till cure: " + pl.getTillCure());
	}
      } else if(level == 4 && mutation == true) {
	System.out.println("Disease has mutated to resist the cure!");
	mutation = false;
      }
      
      //Wait for the user to press Enter to continue
      new Scanner(System.in).nextLine();
      score++;

      if(score == 20) //This value can be changed for difficulty. Spec says 20 but that is hard to test with.
      {
        System.out.println("Level UP!");
	new Scanner(System.in).nextLine(); //I should use exisiting scanner object
	level++;

        //Player wins end game,
	if(level == 5)
	{ 
	  System.out.println("Woo hoo you won!");
	  return;
	}

	//Create new level/game once leveled up
	score = 0;
	createGame();
      }
    }
  }

  public static void placeDisease()
  { 
    System.out.println("\nThis is the board, you are the P, enter the co-ordiantes for where the Disease starts.");
    System.out.print("X: ");
    diseaseX = Keyboard.readInt();

    //Validation
    while(diseaseX <0 || diseaseX >11)
    {  
      System.out.print("Pick betwen 0 and 11 please: ");
      diseaseX = Keyboard.readInt();
    }

    System.out.print("Y: ");
    diseaseY = Keyboard.readInt();

    //Validation
    while(diseaseY <0 || diseaseY >11)
    {  
      System.out.print("Pick betwen 0 and 11 please: ");
      diseaseY = Keyboard.readInt();
    }

    //Stop the player putting the disease on the player
    if(diseaseY == pl.getY() && diseaseX == pl.getX())
    {
      System.out.println("You cannot place the disease on the player!");
      placeDisease();
    }

    //Let the player know where the disease is so they can run away in level 2
    pl.setDisease(diseaseX, diseaseY); 
  }

  public static void createGame()
  {
    //Create the player and board
    bd = new Board(regions);
    pl = new Player(bd);

    //Place the player
    pl.updatePlayer(level);
    bd.printBoard();

    //Place disease
    placeDisease();
    ick = new Disease(diseaseX, diseaseY, bd);
    bd.printBoard();
  }
    

  //Method to end game so player and disease class can stop when disease gets the player
  public static void setGameOver()
  {
    gameOver = true;
  }

  //Test if the game is over
  public static boolean endGame() 
  { 
    if(gameOver == true)
    {
      System.out.println("Game over!");
      return true;
    } else {
      return false;
    }
  }
}
