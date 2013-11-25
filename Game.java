public class Game
{
  public boolean endGame() 
  { 
    if(gameOver == true)
    {
      System.out.println("Game over!");
      return true;
    } else {
      return false;
    }
  }
}
