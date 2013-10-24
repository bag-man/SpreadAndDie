import java.util.Random;

public class Board 
{
  //board[Y][X]
  private String[][] board = new String[12][12];
  private String[] regionLetters = {"\033[42m \033[m", "\033[45m \033[m", "\033[47m \033[m", "\033[46m \033[m"};
  private int playerYposition = new Random().nextInt(12);  
  private int playerXposition = new Random().nextInt(12);
  private int diseaseYposition;
  private int diseaseXposition;
  private boolean displayDisease = false;

  public Board(int numberOfReigons) 
  {
    for(int i=0; i<12; i++)
    {
      for(int y=0;y<12;y++)
      {
	int regionPosition = new Random().nextInt(numberOfReigons);
        board[i][y] = regionLetters[regionPosition];
      }
    }
  }

  public void createDisease(int x, int y)
  {
    diseaseYposition = y;
    diseaseXposition = x;
    displayDisease = true;
    printBoard();
  }

  public void printBoard() 
  {
    System.out.print("\033c"); //Clear screen in bash

    board[playerYposition][playerXposition] = "\033[44mP\033[m";

    if(displayDisease == true)
    {
      board[diseaseYposition][diseaseXposition] = "\033[41mD\033[m";
    }

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
