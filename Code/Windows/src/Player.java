import java.util.Random;

public class Player
{
  private int playerYposition, playerXposition;
  private int diseaseYposition, diseaseXposition;
  private int prevPlayerYposition, prevPlayerXposition;
  private int tillCure;
  private boolean corner; 
  private Board bd;
  Random rand = new Random();  

  Player(Board board) 
  {
    //Place the player randomly
    playerYposition = rand.nextInt(bd.DIM); 
    playerXposition = rand.nextInt(bd.DIM);

    //Assign vars
    bd = board;
    tillCure = 5;
    corner = false;
  }

  //Find out where disease is so we can run away
  public void setDisease(int dX, int dY)
  {
    diseaseYposition = dY;
    diseaseXposition = dX;
  }

  //Pick a random position
  public int randomPosition(int position) 
  {
    int value = 12; //Bit of a hack but stops Disease going out of bounds. Should make checkBoundary more flexible

    while(value >11 || value <0)
    {
      value = (position + (-1 + (int)(Math.random() * 3)));
    }
    return value;
  }

  //Move the player away from the disease start point
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
    //Pick the new reigons colour
    int pos = rand.nextInt(bd.getNumRegions());

    //Loop through all positions around player (incl. player)
    for(int i=-1;i<2;i++) 
    {
      for(int j=-1;j<2;j++)
      {
	if((playerXposition + j) <=11 && (playerXposition  + j) >=0 && (playerYposition + i) <=11 && (playerYposition  + i) >=0) //Make sure its in bounds. Again should use checkBoundary
	{
	  bd.setBoardPositions(playerYposition + j, playerXposition + i, bd.regionLetters[pos]); //Update underlying board
	  bd.setBoard(playerYposition + j, playerXposition + i, bd.regionLetters[pos]); 	 //Update shown board
	}
      }
    }
  }

  public void updatePlayer(int level) 
  {
    //Write previous position so it can be updated to be the region the player was on. 
    prevPlayerYposition = playerYposition;
    prevPlayerXposition = playerXposition;

    //I guess I could use a case statement for this but this is simpler and easier to read to me.
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
      if(rand.nextBoolean() == true) //Randomly pick move or change
      {
	randomMove();
      } else { 
	changeRegions();
      }
    }

    if(level == 4)
    {
      //Run away till in the corner then work on a cure
      if(isCorner(playerXposition, playerYposition) == false) { 
	runAway();
      } else {
	--tillCure;
      }
    }

    //Set the previous player location to the region underneath. 
    bd.setBoard(prevPlayerYposition, prevPlayerXposition, bd.getBoardPositions(prevPlayerYposition, prevPlayerXposition));

    //Check if the player is moving to a diseased square, if so end game
    if(bd.getBoard(playerYposition, playerXposition) == bd.D)
    {
      MainProgram.setGameOver();
    }

    //Finally update the players position to the board
    bd.setBoard(playerYposition, playerXposition, bd.P);
  }

  //I should make this function work for multiple functions
  public int checkBoundary(int pos) 
  {
    if(pos >11)
      pos--;

    if(pos <0)
      pos++;

    return pos;
  }

  //Check if the player has reached the corner
  public boolean isCorner(int x, int y)
  {
    if((y == 0 || y == 11)  && (x == 0 || x == 11))
      return true;
    else
      return false;
  }

  //Check if the cure is done
  public boolean checkCure()
  {
    if(tillCure <= 0)
      return true;
    else
      return false;
  }

  //Getters and setters
  public int getTillCure()
  {
    return tillCure;
  }

  public void setTillCure()
  {
    tillCure = 5;
  }
  
  public int getX()
  { 
    return playerXposition;
  }

  public int getY()
  { 
    return playerYposition;
  }

}
