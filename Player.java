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
    playerYposition = rand.nextInt(bd.DIM);
    playerXposition = rand.nextInt(bd.DIM);
    bd = board;
  }

  public void setDisease(int dX, int dY)
  {
    diseaseYposition = dY;
    diseaseXposition = dX;
  }

  //Generate a random position around the player
  public int randomPosition(int position) 
  {
    int value = 12; //Bit of a hack

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

  public void updatePlayer(int level) 
  {
    prevPlayerYposition = playerYposition;
    prevPlayerXposition = playerXposition;

    if(level == 1) 
    {
      playerYposition = randomPosition(playerYposition);
      playerXposition = randomPosition(playerXposition);
    } else if (level == 2)
    {
      runAway();
    } else {
      //Randomly move or change neighboring reigons
    }

    bd.setBoard(prevPlayerYposition, prevPlayerXposition, bd.getBoard(playerYposition, playerXposition));

    if(bd.getBoard(playerYposition, playerXposition) == bd.D)
    {
      MainProgram.setGameOver();
    }
    bd.setBoard(playerYposition, playerXposition, bd.P);
  }
}
