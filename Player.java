import java.util.Random;

public class Player
{
  private int playerYposition, playerXposition;
  private int diseaseYposition, diseaseXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private boolean corner = false;
  private Board bd;
  private Random rand;

  Player(Board board) 
  {
    Random rand = new Random();  
    playerYposition = rand.nextInt(bd.DIM);
    playerXposition = rand.nextInt(bd.DIM);
    bd = board;
  }

  public void setDisease(int dX, int dY)
  {
    diseaseYposition = dY;
    diseaseXposition = dX;
  }

  public int randomPosition(int position) 
  {
    int value = 12; 

    while(value >11 || value <0)
    {
      value = (position + (-1 + (int)(Math.random() * 3)));
    }
    return value;
  }

  public void runAway()
  {
    if(playerYposition > diseaseYposition) 
    {
      playerYposition = checkBoundary(playerYposition + 1);
    } else {
      playerYposition = checkBoundary(playerYposition - 1);
    }

    if(playerXposition > diseaseXposition) 
    {
      playerXposition = checkBoundary(playerXposition + 1);
    } else {
      playerXposition = checkBoundary(playerXposition - 1);
    }
  }

  public void randomMove()
  {
    playerXposition = randomPosition(playerXposition);
    playerYposition = randomPosition(playerYposition);
  }

  public void changeRegions()
  {
    for(int i=-1;i<2;i++)
    {
      for(int j=-1;j<2;j++)
      {
	if(playerXposition + j <=11 && playerXposition  + j >=0 && playerYposition + i <=11 && playerYposition  + i >=0) 
	{
	  int pos = rand.nextInt(bd.getNumRegions());
	  bd.setBoardPositions(playerXposition + j, playerYposition + i, bd.regionLetters[pos]);
	}
      }
    }
  }

  public void updatePlayer(int level) 
  {
    prevPlayerYposition = playerYposition;
    prevPlayerXposition = playerXposition;

    if(level == 1) 
    {
      randomMove();
    } 

    if(level == 2) 
    {
      runAway();
    } 

    if(level == 3) 
    {
      /*if(rand.nextBoolean() == true)
      {
	randomMove();
      } else { */
	changeRegions();
      //}
    }

    bd.setBoard(prevPlayerYposition, prevPlayerXposition, bd.getBoardPositions(prevPlayerYposition, prevPlayerXposition));

    if(bd.getBoard(playerYposition, playerXposition) == bd.D)
    {
      MainProgram.setGameOver();
    }
    bd.setBoard(playerYposition, playerXposition, bd.P);
  }

  public int checkBoundary(int pos) 
  {
    if(pos >11)
      pos--;

    if(pos <0)
      pos++;

    return pos;
  }

  //This isn't used, but I wrote it and I am pleased with it.
  public boolean isCorner(int x, int y)
  {
    if((y == 0 || y == 11)  && (x == 0 || x == 11))
      return true;
    else
      return false;
  }

}
