import java.util.Random;

public class Board 
{
  //board[Y][X]
  private String[][] board = new String[12][12];
  private String[] regionLetters = {"\033[42m \033[m", "\033[45m \033[m", "\033[47m \033[m", "\033[46m \033[m"};
  private int playerYposition = new Random().nextInt(12);  
  private int playerXposition = new Random().nextInt(12);
  private int oldPlayerYposition, oldPlayerXposition;
  private int diseaseYposition, diseaseXposition;
  private int oldDiseaseYposition, oldDiseaseXposition;

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

  public int randomPosition(int position) 
  {
    int value = 12; //Bit of a hack

    while(value >11 || value <0)
    {
      value = (position + (-1 + (int)(Math.random() * ((1 - -1) +1))));
    }
    return value;
  }

  public void createDisease(int x, int y)
  {
    diseaseYposition = y;
    diseaseXposition = x;
    oldDiseaseYposition = diseaseYposition;
    oldDiseaseXposition = diseaseXposition;
    board[diseaseYposition][diseaseXposition] = "\033[41mD\033[m";
  }

  public void updateDisease()
  {
    for(int i=-1;i==2;i++)
    {
      for(int j=-1;j==2;j++)
      {
	if(board[oldDiseaseYposition + j][oldDiseaseXposition + i] == board[oldDiseaseYposition][oldDiseaseXposition])
	{
	  board[oldDiseaseYposition + j][oldDiseaseXposition + i] = "\033[41mD\033[m";
	} else {
	  board[oldDiseaseYposition + j][oldDiseaseXposition + i] = "\033[41mI\033[m";
	}
      }
    }
  }

  public void updatePlayer() 
  {
    oldPlayerYposition = playerYposition;
    oldPlayerXposition = playerXposition;
    playerYposition = randomPosition(playerYposition);
    playerXposition = randomPosition(playerXposition);
    board[oldPlayerYposition][oldPlayerXposition] = board[playerYposition][playerXposition];
    board[playerYposition][playerXposition] = "\033[44mP\033[m";
  }

  public boolean endGame() 
  { 
    if((playerYposition == diseaseYposition) && (playerXposition == diseaseXposition))
    { 
      return true;
    }
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
