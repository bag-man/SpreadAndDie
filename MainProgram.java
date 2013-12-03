import java.util.Random;
import java.util.Scanner;

public class MainProgram
{
  private static int score = 0;
  private static int level = 1;
  private static boolean mutation = false, cure = false, gameOver = false;
  private static Random rand = new Random();

  public static void main(String[] args) 
  {
    System.out.print("Enter the number of regions on the board [2 - 4]: ");
    int regions = Keyboard.readInt();
    while(regions <2 || regions >4)
    {  
      System.out.print("Pick betwen 2 and 4 please: ");
      regions = Keyboard.readInt();
    }

    Board bd = new Board(regions);
    Player pl = new Player(bd);

    pl.updatePlayer(level);
    bd.printBoard();

    System.out.println("\nThis is the board, you are the P, enter the co-ordiantes for where the Disease starts.");
    System.out.print("X: ");
    int diseaseX = Keyboard.readInt();
    while(diseaseX <0 || diseaseX >11)
    {  
      System.out.print("Pick betwen 0 and 11 please: ");
      diseaseX = Keyboard.readInt();
    }

    System.out.print("Y: ");
    int diseaseY = Keyboard.readInt();
    while(diseaseY <0 || diseaseY >11)
    {  
      System.out.print("Pick betwen 0 and 11 please: ");
      diseaseY = Keyboard.readInt();
    }

    pl.setDisease(diseaseX, diseaseY);

    Disease ick = new Disease(diseaseX, diseaseY, bd);
    bd.printBoard();

    System.out.println("Press enter to move the player randomly");
    while(endGame() != true)
    {
      pl.updatePlayer(level);

      if(pl.checkCure() == true)
      {
	cure = true;
	if (rand.nextInt(5) == 1)
	{
	  cure = false;
	  pl.setTillCure();
	  mutation = true;
	}
      }

      if(cure == false)
      {
	ick.updateDisease();
      }

      bd.printBoard();
      System.out.println("\n\nScore: " + score);
      System.out.println("Level: " + level);
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
      new Scanner(System.in).nextLine();
      score++;

      if(score == 20)
      {
        System.out.println("Level UP!");
	//Rest game. Should probably make a new class. 
	level++;
	score = 0;
      }

    }
  }
    
  public static void setGameOver()
  {
    gameOver = true;
  }

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
