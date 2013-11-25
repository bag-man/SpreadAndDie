public class Disease
{
  private int prevDiseaseYposition, prevDiseaseXposition;
  private String[][] diseasePositions;

  Disease(int x, int y)
  {
    board[y][x] = D;
  }

  public void updateDisease()
  {
    for(int x=0;x<12;x++)
    {
      for(int y=0;y<12;y++)
      {
	if(board[x][y] == D)
	{
	  diseasePositions[x][y] = D; 
	  for(int i=-1;i<2;i++)
	  {
	    for(int j=-1;j<2;j++)
	    {
	      if(x + j <=11 && x  + j >=0 && y + i <=11 && y  + i >=0) 
	      {
		String foo = board[x + j][y + i]; 
		if(foo == boardPositions[x][y] || foo == I || foo == D)
		{
		  diseasePositions[x + j][y + i] = D; 
		} else {
		  diseasePositions[x + j][y + i] = I; 
		}
	      }
	    }
	  }
	}
      }
    }

    for(int x=0;x<12;x++)
    {
      for(int y=0;y<12;y++)
      {
	if(diseasePositions[x][y] == D || diseasePositions[x][y] == I)
	{
	  if(board[x][y] != P)
	  {
	    board[x][y] = diseasePositions[x][y];
	  }
	}
      }
    }
  }
}
