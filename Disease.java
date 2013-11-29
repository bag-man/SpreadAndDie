public class Disease
{
  private int prevDiseaseYposition, prevDiseaseXposition;
  private String[][] diseasePositions;
  private Board bd;

  Disease(int x, int y, Board board)
  {
    diseasePositions = new String[12][12];
    bd = board;
    bd.setBoard(y, x, bd.D);
  }

  public void updateDisease()
  {
    for(int x=0;x<12;x++)
    {
      for(int y=0;y<12;y++)
      {
	if(bd.getBoard(x, y) == bd.D)
	{
	  diseasePositions[x][y] = bd.D; 
	  for(int i=-1;i<2;i++)
	  {
	    for(int j=-1;j<2;j++)
	    {
	      if(x + j <=11 && x  + j >=0 && y + i <=11 && y  + i >=0) 
	      {
		String foo = bd.getBoard(x + j, y + i); 
		if(foo == bd.getBoardPositions(x, y) || foo == bd.I || foo == bd.D)
		{
		  diseasePositions[x + j][y + i] = bd.D; 
		} else {
		  diseasePositions[x + j][y + i] = bd.I; 
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
	if(diseasePositions[x][y] == bd.D || diseasePositions[x][y] == bd.I)
	{
	  if(bd.getBoard(x, y) != bd.P)
	  {
	    bd.setBoard(x, y, diseasePositions[x][y]);
	  }
	}
      }
    }
  }
}
