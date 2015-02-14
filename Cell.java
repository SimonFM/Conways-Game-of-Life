public class Cell
{
	// Member Data of the 'Cell' Object.
	// True or False (Alive or Dead).
	public boolean mState;
	// Number of alive cells around the cell.
	public int mNeighbours;

	// Constructor that creates the Cell Object.
	// Which holds a boolean value for alive or dead.
	// and an int for the total number of alive neighbours 
	// the cell has.
	Cell(int neighbours, boolean state)
	{
		this.mNeighbours = neighbours;
		this.mState = state;
	}	
	//Method for determining whether a cell is alive or not.
	// Checks 8 neighbours around the cell and sees if they
	// are alive or dead. Returning the amount that are alive.
	int normalCase(Cell theCells[][], int N, int row, int col)
	{
		int total = 0;
		for(int i = row-1; i <= (row+1);i++)
		{
			for(int j = col-1; j <= (col+1);j++)
			{
				if( (row == i)&&(j == col) )
				{
				}
				else
				{
					if(theCells[i][j].mState == true)
					{
						total++;
					}
				}
			}
		}
		return total;
	}
}