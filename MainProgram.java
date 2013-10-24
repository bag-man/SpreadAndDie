public class MainProgram
{
  public static void main(String[] args) 
  {
    //Get number of reigons for board from user
    System.out.print("Enter the number of regions on the board: ");
    int regions = Keyboard.readInt(); //Need to validate this

    //Create and display board
    Board bd = new Board(regions);
    bd.printBoard();

    //Get the disease starting point from user
    System.out.println("\nThis is the board, you are the P, enter the co-ordiantes for where the Disease starts.");
    System.out.print("X: ");
    int diseaseX = Keyboard.readInt();
    System.out.print("Y: ");
    int diseaseY = Keyboard.readInt();

    //Place disease
    bd.createDisease(diseaseX, diseaseY);
  }
}
