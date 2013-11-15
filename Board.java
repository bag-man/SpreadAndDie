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
  private int playerYposition, playerXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private int prevDiseaseYposition, prevDiseaseXposition;

  public Board(int numReigons) 
  {
    //Assign varables
    Random rand = new Random();  
    board = new String[DIM][DIM];
    boardPositions = new String[DIM][DIM];
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

  public int randomPosition(int position) 
  {
    int value = 12; //Bit of a hack

    while(value >11 || value <0)
    {
      value = (position + (-1 + (int)(Math.random() * 3)));
    }
    return value;
  }

  public void createDisease(int x, int y)
  {
    board[y][x] = D;
  }

  public void updateDisease()
  {
    for(int x=0;x<12;x++)
    {
      for(int y=0;y<12;y++)
      {
	if(board[x][y] == D)
	{
	  boardPositions[y][x] = D;
	  for(int i=-1;i<2;i++)
	  {
	    for(int j=-1;j<2;j++)
	    {
	      if(y + j >11 || y  + j <0 || x + i >11 || x  + i <0) 
	      { 
		continue;
	      } else {
		String foo = board[y + j][x + i]; //Make the next line prettier
		if(foo == boardPositions[y][x] || foo == I)
		{
		  boardPositions[y + j][x + i] = D; 
		} else {
		  board[y + j][x + i] = I;
		}
	      }
	    }
	  }
	}
      }
    }

    for(int x=0;x<12;x++)
    {
      for(int y=0;y<12;y++)
      {
	if(boardPositions[y][x] == D)
	  board[y][x] = D;
      }
    }
  }

  public void updatePlayer() 
  {
    prevPlayerYposition = playerYposition;
    prevPlayerXposition = playerXposition;
    playerYposition = randomPosition(playerYposition);
    playerXposition = randomPosition(playerXposition);
    board[prevPlayerYposition][prevPlayerXposition] = board[playerYposition][playerXposition];
    board[playerYposition][playerXposition] = P;
  }

  public boolean endGame() 
  { 
      return false;
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
