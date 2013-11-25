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
  public void updatePlayer() 
  {
    prevPlayerYposition = playerYposition;
    prevPlayerXposition = playerXposition;
    playerYposition = randomPosition(playerYposition);
    playerXposition = randomPosition(playerXposition);
    bd.setBoard(prevPlayerYposition, prevPlayerXposition, bd.getBoard(playerYposition, playerXposition));
    if(bd.getBoard(playerYposition, playerXposition) == bd.D)
    {
      MainProgram.setGameOver();
    }
    bd.setBoard(playerYposition, playerXposition, bd.P);
  }
}
