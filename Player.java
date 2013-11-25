import java.util.Random;

public class Player
{
  private int playerYposition, playerXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private Board bd;

  Player(Board board) 
  {
    Random rand = new Random();  
    playerYposition = rand.nextInt(bd.DIM);
    playerXposition = rand.nextInt(bd.DIM);
    bd = board;
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

  //Move the player randomly
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
      //Move away from the disease
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
