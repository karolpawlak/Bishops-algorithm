/**
 * @author Karol Pawlak R00103090
 * @date May 2019
 * @description Bishops algorithm
 */

public class Bishops 
{

	private int x; //size
	int nbl; //bishops left
	private boolean board[][];
	private int attack[][];
	
	public void runBishop(int n)
	{
		x = n;
		nbl = n;
		board = new boolean[n][n];
		attack = new int[n][n];
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < x; j++)
			{
				board[i][j] = false;
				attack[i][j] = 0;
			}
		}
		
		int lpt = -1; //last bishop position	
		bishopSolve(x, nbl, board, attack, lpt);
	}
	
	public void bishopSolve(int size, int nbl, boolean board[][], int attack[][],
			int lpt) 
	{

		if(nbl == 0) 
		{
			String solutions = bishopPrint(board, size);
			System.out.println(solutions);
		}
		else 
		{
			for(int i = (lpt + 1); i < (size*size); i++) 
			{
				int x = i % size;
				int y = i / size;

				if(attack[x][y] == 0) 
				{

					//bishop attack
					board[x][y] = true;	
					bishopAttack(attack, x, y, size, false);
					
					//recursion
					bishopSolve(size, (nbl - 1), board, attack, i);

					//backtracking
					board[x][y] = false;	
					bishopAttack(attack, x, y, size, true);
				}

			}

		}

	}
	

	
	public void bishopAttack(int attack[][], int x, int y, int size, boolean backtrack)
	{
		int value = 1;
		if (backtrack == true)
		{
			value = -1;
		}
		
		
		//current position
		attack[x][y] = attack[x][y] + value;
		
		//move top right
		int a = x + 1;
		int b = y - 1;
		while (a < size && b >= 0)
		{
			attack[a][b] = attack[a][b] + value;
			a++;
			b--;
		}
		
		//move top left
		a = x - 1;
		b = y - 1;
		while (a >= 0 && b >= 0)
		{
			attack[a][b] = attack[a][b] + value;
			a--;
			b--;
		}
		
		//move bottom right
		a = x + 1;
		b = y + 1;
		while (a < size && b < size)
		{
			attack[a][b] = attack[a][b] + value;
			a++;
			b++;
		}
		
		//move bottom left
		a = x - 1;
		b = y + 1;
		while (a >= 0 && b < size)
		{
			attack[a][b] = attack[a][b] + value;
			a--;
			b++;
		}
	}
	
	public String bishopMarking(boolean marking)
	{
		String message = "";
		if (marking == true)
		{
			message = " B ";
		}
		else
		{
			message = " * ";
		}
		
		return message;
	}
	
	public String bishopPrint(boolean board[][], int v)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------\n");
		for (int i = 0; i < v; i++)
		{
			for (int j = 0; j < v; j++)
			{
				String drawBoard = bishopMarking(board[i][j]);
				sb.append(drawBoard);
			}
		sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) 
	{
		Bishops b = new Bishops();
		b.runBishop(3);
	}
}
