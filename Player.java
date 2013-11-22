public class Player
{
  private int playerYposition, playerXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private boolean gameOver;

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
    board[prevPlayerYposition][prevPlayerXposition] = board[playerYposition][playerXposition];
    if(board[playerYposition][playerXposition] == D)
    {
      gameOver = true;
    }
    board[playerYposition][playerXposition] = P;
  }
}
