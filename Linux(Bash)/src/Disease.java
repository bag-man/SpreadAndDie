public class Disease
{
  private int prevDiseaseYposition, prevDiseaseXposition;
  private String[][] diseasePositions;
  private Board bd;

  Disease(int x, int y, Board board)
  {
    //AssignVars
    diseasePositions = new String[12][12];
    bd = board;
    
    //Place disease
    bd.setBoard(y, x, bd.D);
  }

  public void updateDisease()
  {
    for(int x=0;x<12;x++) //For all the X..
    {
      for(int y=0;y<12;y++) //Loop through all the Y..
      {
	if(bd.getBoard(x, y) == bd.D) //If the disease is found..
	{
	  diseasePositions[x][y] = bd.D; 

	  for(int i=-1;i<2;i++) //For all the surrounding X.. 
	  {
	    for(int j=-1;j<2;j++) //For all the surrounding Y..
	    {
	      if(x + j <=11 && x  + j >=0 && y + i <=11 && y  + i >=0)  //Check disease won't go out of bounds
	      {
		String foo = bd.getBoard(x + j, y + i); //This is bad but it just makes the next if statement a lot shorter and easier to read.
		if(foo == bd.getBoardPositions(x, y) || foo == bd.I || foo == bd.D) //If the cell is the same reigon or diseased or infected make it diseased
		{
		  diseasePositions[x + j][y + i] = bd.D; 
		} else {
		  diseasePositions[x + j][y + i] = bd.I; //Or make it infected
		}
	      }
	    }
	  }
	}
      }
    }

    for(int x=0;x<12;x++) //Loop through X..
    {
      for(int y=0;y<12;y++) //And Y..
      {
	if(diseasePositions[x][y] == bd.D || diseasePositions[x][y] == bd.I)
	{
	  if(bd.getBoard(x, y) != bd.P) //If disease hasn't got the player
	  {
	    bd.setBoard(x, y, diseasePositions[x][y]); //Update the main (shown) board
	  } else {
	    MainProgram.setGameOver(); //End game if it did get the player
	  }
	}
      }
    }
  }
}
