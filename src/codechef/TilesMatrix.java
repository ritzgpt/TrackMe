package codechef;

import java.util.Scanner;

public class TilesMatrix {
	Tiles tiles[][] = new Tiles[3][3];
	Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		TilesMatrix obj = new TilesMatrix();
		obj.initializeTiles();
		obj.printTiles();
		obj.findWords(obj.tiles);
	}

	/*
	 * This portion is for performing DFS and find valid move
	 */
	 int ROW = 3, COL = 3;

	// check whether given cell (row, col) is a valid cell or not.
	 boolean isvalid(int row, int col, int prevRow, int prevCol,Tiles tiles[][]) {
		// return true if row number and column number
		// is in range
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) &&
				!(row == prevRow && col == prevCol)
				&& !(tiles[row][col].fixed);
	}

	// These arrays are used to get row and column numbers of 8 neighbours of a
	// given cell
	 int rowNum[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	 int colNum[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	// A utility function to do DFS for a 2D boolean matrix. It only considers
	// the 8 neighbours as adjacent vertices
	 void DFS(Tiles tiles[][], int row, int col, int prevRow, int prevCol,  String path,int index) {
		// return if current character doesn't match with the next character in
		// the word
		if (!tiles[row][col].prime 
				&& !(tiles[prevRow][prevCol].fixed) 
				&& (checkPrime(tiles[row][col].value+tiles[prevRow][prevCol].value)))
			return;

		// append current value & position to path
		path += tiles[row][col].value + "(" + row + ", " + col + ")";

		// current character matches with the last character in the word
		//TODO ::
		System.out.println(path);
		if (fixedTiles()) {
			System.out.println(path);
			return;
		}

		// Recur for all connected neighbours
		for (int k = 0; k < 8; ++k)
			if (isvalid(row + rowNum[k], col + colNum[k], prevRow, prevCol,tiles))

				DFS(tiles, row + rowNum[k], col + colNum[k], row, col, path, index + 1);
	}

	// The main function to find all occurrences of the word in a matrix
	 void findWords(Tiles tiles[][]) {
		// traverse through the all cells of given matrix
		for (int i = 0; i < ROW; ++i)
			for (int j = 0; j < COL; ++j)
				// occurrence of first character in matrix
				if (tiles[i][j].fixed)
					// check and print if path exists
					DFS(tiles, i, j, -1, -1, "", 0);
	}

	/** Portion Ends **/
	// initializing tiles with properties
	void initializeTiles() {
		int i = 0, j = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				tiles[i][j] = new Tiles();
				tiles[i][j].value = sc.nextInt();
				setActPosition(tiles[i][j]);
				tiles[i][j].currPosX = i;
				tiles[i][j].currPosY = j;
				tiles[i][j].fixed = isFixed(tiles[i][j]);
				checkEvenOdd(tiles[i][j]);
				tiles[i][j].prime = checkPrime(tiles[i][j].value);
			}
		}
	}

	// printing the tiles value
	void printTiles() {
		int i = 0, j = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				System.out.print(tiles[i][j].value + "  ");
			}
			System.out.println();
		}

	}
	// is matrix fixed now
		boolean fixedTiles() {
			boolean allFixed=true;
			int i = 0, j = 0;
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					if(!tiles[i][j].fixed){
					allFixed=false;
					break;
					}
				}
			}
			return allFixed;
		}
	// to check whether a tile is at its correct place or not
	boolean isFixed(Tiles tiles) {
		if (tiles.actPosY == tiles.currPosY && tiles.actPosX == tiles.currPosX)
			return true;
		else
			return false;
	}

	// to check tiles' value even or odd
	void checkEvenOdd(Tiles tiles) {
		if (tiles.value % 2 == 0) {
			tiles.even = true;
			tiles.odd = false;
		} else {
			tiles.odd = true;
			tiles.even = false;
		}
	}

	// to check tiles' value prime or not
	boolean checkPrime(int value) {
		int c = 0;
		for (int i = 1; i <= value / 2; i++) {
			if (value % i == 0) {
				c++;
			}
		}
		if (c >= 2) {
			return true;
		} else {
			return false;
		}
	}

	// Deciding actual (x,y) index of tiles' value
	void setActPosition(Tiles tiles) {
		switch (tiles.value) {
		case 1:
			tiles.actPosX = 0;
			tiles.actPosY = 0;
			break;
		case 2:
			tiles.actPosX = 0;
			tiles.actPosY = 1;
			break;
		case 3:
			tiles.actPosX = 0;
			tiles.actPosY = 2;
			break;

		case 4:
			tiles.actPosX = 1;
			tiles.actPosY = 0;
			break;
		case 5:
			tiles.actPosX = 1;
			tiles.actPosY = 1;
			break;
		case 6:
			tiles.actPosX = 1;
			tiles.actPosY = 2;
			break;

		case 7:
			tiles.actPosX = 2;
			tiles.actPosY = 0;
			break;
		case 8:
			tiles.actPosX = 2;
			tiles.actPosY = 1;
			break;
		case 9:
			tiles.actPosX = 2;
			tiles.actPosY = 2;
			break;

		}
	}

}

// tiles having certain properties
class Tiles {
	int actPosX, actPosY, currPosX, currPosY, value,prevRow=-1,prevCol=-1;
	boolean fixed = false, prime = false, odd = false, even = false;
}
