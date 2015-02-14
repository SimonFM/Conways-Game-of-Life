import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TheGameOfLife 
{
	// Main Function
	public static void main(String args[])
	{	
		// Initialising variables.
		int size = 25;
		boolean nextGen = true;
		Scanner userInput = new Scanner(System.in);
		int choice = 0;
		
		// Initialising the generation of cells (1st Generation).
		// and the generation of ints (2nd Generation).
		int matrixCopy[][] = new int[size][size];
		Cell generation[][] = new Cell[size][size];
		
		// Initialising the Jframe with the header as parameters.
		JFrame frame = new JFrame("Game Of Life");
		frame.setSize(500,500);
		frame.setLayout(new GridLayout(size,size));
		// Creating a 2D JPanel in order to represent each cell in 
		// the Frame.
		JPanel board[][] = new JPanel[size][size];
		
		// Do while Loop to determine which board to create.
		do
		{
			System.out.println("Please select a board type: ");
			System.out.println(" (1) - Random Board.");
			System.out.println(" (2) - Glider.");
			System.out.println(" (3) - Block.");
			System.out.println(" (4) - Blinker.");
			System.out.println(" (5) - Beehive.");
			
			choice = userInput.nextInt();
			// Creates a board depending on which option the user 
			// submitted.
			createBoard(board,frame);
			// Creates the 2D array of Cells.
			createCells(generation,size,choice);
			// Updates the Board.
			updateBoard(board,frame,generation);
			// Shows Frame to Screen.
			frame.setVisible(true);
			
		}while((choice > 5)||(choice < 0 ) );
		
		
		// Do while loop to deal with each generation born.
		do
		{
			// Prints Cells out to the Console.
			printCells(generation,matrixCopy);
			// Checks the neighbours for each Cell.
			checkNeighbours(generation,generation.length);
			// Sets each mState for each Cell.
			setState(generation,matrixCopy, size);
			// Updates each Cell's state.
			updateCells(generation,matrixCopy);
			// Updates the Board for the frame.
			updateBoard(board,frame,generation);
			
			// Menu for entering another Generation.
			do
			{
				System.out.println("Do you want another generation? \n(1) - Yes \n(2) - No");
				choice = userInput.nextInt();
				// Switch statement for dealing with user decision of next generation.
				switch(choice)
				{
					case 1:
						nextGen = true;
						break;
					case 2:
						nextGen = false;
						break;
					default:
						System.out.println("Not a valid choice");
						break;
				}
			}while((choice > 2)||(choice < 0) );

		}while(nextGen);
		// End Message.
		System.out.println("\nAnd Thats the Game of Life!");
	}
	
	// Creates a board for inside the frame.
	static void createBoard(JPanel board[][],JFrame frame)
	{
		for(int i = 0; i < board.length;i++)
		{
			for(int j = 0; j < board.length;j++)
			{
				board[i][j] = new JPanel();
				board[i][j].setBackground(Color.BLACK);
				frame.add(board[i][j]);
			}
		}
	}
	// A function to change the colour of each board in the frame.
	static void updateBoard(JPanel board[][],JFrame frame,Cell theCells[][])
	{
		for(int i = 0; i < board.length;i++)
		{
			for(int j = 0; j < board.length;j++)
			{
				if(theCells[i][j].mState == true)
				{
					board[i][j].setBackground(Color.WHITE);
				}
				else if(theCells[i][j].mState == false)
				{
					board[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}
	
	// Updates the old generation to the new generation.
	static void updateCells(Cell generation[][], int copy[][])
	{
		for(int i = 0; i < copy.length; i++)
		{
			for(int j = 0; j < copy.length; j++)
			{
				// Alive
				// If the 2nd generation is 1 then the current generation is 
				// set to the boolean value of true.
				if(copy[i][j] == 1)
				{
					generation[i][j].mState = true;
				}
				// Dead
				// If the 2nd generation is 0 then the current generation is 
				// set to the boolean value of false.
				if(copy[i][j] == 0)
				{
					generation[i][j].mState = false;
				}
				// Rebirth
				// If the 2nd generation is 2 then the current generation is 
				// set to the boolean value of true.
				if(copy[i][j] == 2)
				{
					generation[i][j].mState = true;
				}
			}
		}
	}

	// A method to check whether or not a cell in the generation 
	// should be alive or not.
	static void setState(Cell theCells[][],int cells[][], int N)
	{
		for(int i = 1;i < N-1;i++)
		{
			for(int j = 1;j < N-1;j++)
			{
				// Under Population and OverCrowding.
				if( (theCells[i][j].mNeighbours < 2)||(theCells[i][j].mNeighbours > 3) )
				{
					cells[i][j] = 0;
				}
				// Balanced Population
				if( ( (theCells[i][j].mNeighbours == 2) || (theCells[i][j].mNeighbours == 3) ) 
						&&(theCells[i][j].mState == true))
				{
					cells[i][j] = 1;
				}
				//Rebirth
				if( (theCells[i][j].mNeighbours == 3) && (theCells[i][j].mState == false) )
				{
					cells[i][j] = 2;
				}
			}
		}
	}

	// A method that prints out the grid of cells.
	// Displaying the state of alive with an 'A' or
	// a '.' if the cell is dead.
	static void printCells(Cell theCells[][],int cells[][])
	{
		String board = "";
		for(int i = 1; i < theCells.length-1; i++)
		{
			for(int j = 1; j < theCells.length-1; j++)
			{
				if(theCells[i][j].mState == true)
				{
					board = board + "A";
				}
				else
				{
					board = board + ".";
				}
			}
			board = board + "\n";
		}
		board = board + "\n";
		System.out.print(board);
	}

	// A function that generates a Cell inside theCells[][].
	// Places an alive cell in the middle of the array.
	static void createCells(Cell theCells[][],int N,int option)
	{
		// Adds a cell to the board
		for(int i = 0; i < theCells.length;i++)
		{
			for(int j = 0; j < theCells.length;j++)
			{
				theCells[i][j] = new Cell(0,false); 
			}
		}
		// Preset Values to show.
		switch(option)
		{
			case 1:
				//Random Population.
				Random populate = new Random();
				int value;
				for(int i = 0; i < theCells.length;i++)
				{
					for(int j = 0; j < theCells.length;j++)
					{
						value = populate.nextInt(2);
						if(value == 1)
						{
							theCells[i][j] = new Cell(0,true); 
						}
						else
						{
							theCells[i][j] = new Cell(0,false); 
						}
					}
				}
				break;
			case 2:
				//Glider
				theCells[4][4] = new Cell(0,true);
				theCells[5][4]= new Cell(0,true);
				theCells[6][4]= new Cell(0,true);
				theCells[6][3]= new Cell(0,true);
				theCells[5][2]= new Cell(0,true);
				break;
			case 3:
				//Block
				theCells[5][4] = new Cell(0,true);
				theCells[5][5]= new Cell(0,true);
				theCells[6][5]= new Cell(0,true);
				theCells[6][4] = new Cell(0,true);
				break;
			case 4:
				//Blinker
				theCells[5][4] = new Cell(0,true);
				theCells[5][5]= new Cell(0,true);
				theCells[5][6]= new Cell(0,true);
				break;
			case 5:
				// Bee Hive Still Life.
				theCells[2][4] = new Cell(0,true);
				theCells[2][5]= new Cell(0,true);
				theCells[3][3]= new Cell(0,true);
				theCells[4][4]= new Cell(0,true);
				theCells[4][5]= new Cell(0,true);
				theCells[3][6]= new Cell(0,true);
				break;
			}
		// Updates the neighbours for each cell.
		checkNeighbours(theCells,theCells.length);
	}

	// A function that returns the number of neighbours the cell at the 
	// coordinates row and col, by checking through theCells[][].
	static void checkNeighbours(Cell theCells[][], int N)
	{
		int total = 0;
		for(int i = 1 ; i < theCells.length-1;i++)
		{
			for(int j = 1 ;j < theCells.length-1;j++)
			{
				//Normal Case
				if( (i > 0)&&(i > 0)&&(j < (N-1))&&(j < (N-1)) )
				{
					theCells[i][j].mNeighbours = theCells[i][j].normalCase(theCells,N,i,j);
				}
			}
		}
	}
}
