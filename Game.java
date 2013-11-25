public class Game
{
  Game(Board bd)
  {
    System.out.println("Press enter to move the player randomly");
    while(bd.endGame() != true)
    {
      bd.updatePlayer();
      bd.updateDisease();
      bd.printBoard();
      new Scanner(System.in).nextLine();
    }
  }

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
