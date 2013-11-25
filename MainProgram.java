import java.util.Scanner;

public class MainProgram
{
  public static void main(String[] args) 
  {
    System.out.print("Enter the number of regions on the board: ");
    int regions = Keyboard.readInt();

    Board bd = new Board(regions);
    Player pl = new Player();

    pl.updatePlayer();
    bd.printBoard();

    System.out.println("\nThis is the board, you are the P, enter the co-ordiantes for where the Disease starts.");
    System.out.print("X: ");
    int diseaseX = Keyboard.readInt();
    System.out.print("Y: ");
    int diseaseY = Keyboard.readInt();

    Disease ick = new Disease(diseaseX, diseaseY);
    bd.printBoard();

    System.out.println("Press enter to move the player randomly");
    while(bd.endGame() != true)
    {
      pl.updatePlayer();
      ick.updateDisease();
      bd.printBoard();
      new Scanner(System.in).nextLine();
    }
  }
    
  public boolean endGame() 
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
