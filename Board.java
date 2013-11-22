import java.util.Random;

public class Board 
{
  //Define constants
  private static final int DIM = 12;
  private static final String A = "\033[42m \033[m";
  private static final String B = "\033[45m \033[m";
  private static final String X = "\033[47m \033[m";
  private static final String Y = "\033[46m \033[m";
  private static final String D = "\033[41mD\033[m";
  private static final String I = "\033[41mI\033[m";
  private static final String P = "\033[44mP\033[m";

  //Define variables
  private String[][] board, boardPositions;
  private String[] regionLetters;

  public Board(int numReigons) 
  {
    //Assign varables
    Random rand = new Random();  
    board = new String[DIM][DIM];
    boardPositions = new String[DIM][DIM];
    diseasePositions = new String[DIM][DIM];
    regionLetters = new String[] {A,B,X,Y};
    playerYposition = rand.nextInt(DIM);
    playerXposition = rand.nextInt(DIM);

    int position;

    for(int i=0; i<12; i++)
    {
      for(int y=0;y<12;y++)
      {
	position = rand.nextInt(numReigons);
        board[i][y] = regionLetters[position];
	boardPositions[i][y] = regionLetters[position];
      }
    }
  }

  public void printBoard() 
  {
    System.out.print("\033c"); //Clear screen in bash

    System.out.println("X>    0  1  2  3  4  5  6  7  8  9  10 11");
    System.out.println("      -----------------------------------");
    for(int i=0;i<12;i++)
    {
      if(i < 10) 
      {
	System.out.print(i + "    |");
      } else {
	System.out.print(i + "   |");
      }

      for(int y=0;y<12;y++)
      { 
	System.out.print(board[i][y]);
        System.out.print("  ");
      }
      System.out.println();
    }
  }
}
