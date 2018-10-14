package codechef;

public class WordMatrix {
  static  int ROW=3,COL=5;
    // check whether given cell (row, col) is a valid cell or not.
   static boolean isvalid(int row, int col, int prevRow, int prevCol)
    {
        // return true if row number and column number
        // is in range
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL) &&
                !(row== prevRow && col == prevCol);
    }

// These arrays are used to get row and column numbers of 8 neighbours of a given cell
  static  int rowNum[] = {-1, -1, -1, 0, 0, 1, 1, 1};
  static  int colNum[] = {-1, 0, 1, -1, 1, -1, 0, 1};

// A utility function to do DFS for a 2D boolean matrix. It only considers the 8 neighbours as adjacent vertices
  static  void DFS(char mat[][], int row, int col,
             int prevRow, int prevCol, char word[],
             String path, int index, int n)
        {
// return if current character doesn't match with the next character in the word
        if (index > n || mat[row][col] != word[index])
            return;

        //append current character position to path
        path +=  word[index] + "(" + row + ", " + col + ")";

// current character matches with the last character in the word
        if (index == n)
        {
            System.out.println(path);
            return;
        }

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
            if (isvalid(row + rowNum[k], col + colNum[k],
                    prevRow, prevCol))

                DFS(mat, row + rowNum[k], col + colNum[k],
                        row, col, word, path, index+1, n);
    }
// The main function to find all occurrences of the word in a matrix
   static void findWords(char mat[][], char word[], int n)
    {
// traverse through the all cells of given matrix
        for (int i = 0; i < ROW; ++i)
            for (int j = 0; j < COL; ++j)
                // occurrence of first character in matrix
                if (mat[i][j] == word[0])
                    // check and print if path exists
                    DFS(mat, i, j, -1, -1, word, "", 0, n);
    }
    // Driver program to test above function
   public static void main(String args[])
    {
        char mat[][]= { {'B', 'N', 'E', 'Y', 'S'},
        {'H', 'E', 'D', 'E', 'S'},
        {'S', 'G', 'N', 'D', 'E'}
    };

        char word[] ={'D','E','S'};

        findWords(mat, word, word.length - 1);


    }
}
