import java.util.Random;

public class Player
{
  private int playerYposition, playerXposition;
  private int diseaseYposition, diseaseXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private Board bd;

  Player(Board board)
  {
    Random rand = new Random();
    playerYposition = rand.nextInt(12);
    playerXposition = rand.nextInt(12);
    bd = board;
  }

  public void setDisease(int dX, int dY)
  {
    diseaseYposition = dY;
    diseaseXposition = dX;
  }

  public int randomPosition(int position)
  {
<<<<<<< HEAD
    int value = 12;
=======
>>>>>>> 39df16c55f3a7858ada2ca264dca2882a313d63d

    while(value >11 || value <0)
    {
      value = (position + (-1 + (int)(Math.random() * 3)));
    }
    return value;
  }

  public int checkBoundary(int pos)
  {
    if(pos >11)
      pos--;

    if(pos <0)
      pos++;

    return pos;
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

  public boolean isCorner(int x, int y)
  {
<<<<<<< HEAD
    if((y == 0 || y == 11) && (x == 0 || x == 11))
=======
    if((y == 0 || y == 11)  && (x == 0 || x == 11))
>>>>>>> 39df16c55f3a7858ada2ca264dca2882a313d63d
      return true;
    else
      return false;
  }


  public void updatePlayer(int level)
  {
    if(level == 1)
    {
      prevPlayerYposition = playerYposition;
      prevPlayerXposition = playerXposition;

      playerYposition = randomPosition(playerYposition);
      playerXposition = randomPosition(playerXposition);
    }

    if(level == 2)
    {
      if(isCorner(playerXposition, playerYposition) == false)
      {
<<<<<<< HEAD
        prevPlayerYposition = playerYposition;
        prevPlayerXposition = playerXposition;
      } else {
        corner = true;
      }        
=======
	prevPlayerYposition = playerYposition;
	prevPlayerXposition = playerXposition;
      } 
>>>>>>> parent of 921f53c... Nearly, and now I have a lecture and an exam!

      runAway();
    }

    if(level == 3)
    {
      //Randomly move or change neighboring reigons
    }

    bd.setBoard(prevPlayerYposition, prevPlayerXposition, bd.getBoardPositions(prevPlayerYposition, prevPlayerXposition));

    if(bd.getBoard(playerYposition, playerXposition) == bd.D)
    {
      MainProgram.setGameOver();
    }
    bd.setBoard(playerYposition, playerXposition, bd.P);
  }
}
