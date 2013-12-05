import java.util.Random;
import java.io.*;

public class Board 
{
  //Define constants
  //These are Bash colour codes so need to be changed for the Windows version
  public static final String A = "A";  
  public static final String B = "B";
  public static final String X = "X";
  public static final String Y = "Y";
  public static final String D = "D";
  public static final String I = "I";
  public static final String P = "P";
  public static final String[] regionLetters = new String[] {A,B,X,Y};
  public static final int DIM = 12; //Board size. I could make it so the player can decide the board size in the future. 

  //Define variables
  private String[][] board, boardPositions;
  private int numReigons;
  Random rand = new Random();

  public Board(int x) 
  {
    //Assign varables
    board = new String[DIM][DIM];
    boardPositions = new String[DIM][DIM];
    numReigons = x;

    //Create two boards of random reigons
    int position;

    for(int i=0; i<DIM; i++)
    {
      for(int y=0;y<DIM;y++)
      {
	position = rand.nextInt(numReigons);
        board[i][y] = regionLetters[position];
	boardPositions[i][y] = regionLetters[position];
      }
    }
  }

  //Getters and Setters for the board positions
  public int getNumRegions()
  {
    return numReigons;
  }

  public void setBoard(int x, int y, String z)
  {
    board[x][y] = z;
  }

  public String getBoard(int x, int y)
  {
    return board[x][y];
  }

  public String getBoardPositions(int x, int y)
  {
    return boardPositions[x][y];
  }

  public void setBoardPositions(int x, int y, String z)
  {
    boardPositions[x][y] = z;
  }
  //------------------------------------------

  public void printBoard() 
  {
    //System.out.print("\033c"); //Clear screen in bash
    
    System.out.println("X>    0  1  2  3  4  5  6  7  8  9  10 11"); //This isn't scalable
    System.out.println("      -----------------------------------");
    for(int i=0;i<DIM;i++)
    {
      //Keep formatting neat when board row is double digits
      if(i < 10) 
      {
	System.out.print(i + "    |");
      } else {
	System.out.print(i + "   |"); 
      }

      for(int y=0;y<DIM;y++)
      { 
	System.out.print(board[i][y]);
        System.out.print("  "); //Space the values nicely
      }
      System.out.println();
    }
  }
}
