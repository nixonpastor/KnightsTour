import java.security.SecureRandom;

public class Problem22D {
    /*
    Nixon Pastor
    Chapter#7 Problem 22D
    Knight's Tour
    */
    public static SecureRandom secureRandom = new SecureRandom();
    public static int[][] board=new int[8][8];
    public static int[] horizontal = {2,1,-1,-2,-2,-1,1,2};
    public static int[] vertical = {-1,-2,-2,-1,1,2,2,1};
    public static int currentRow;
    public static int currentColumn;
    public static int moveNumber = 1;
    public static int accessBoard =9;
    public static int[][] accessibility = {
            {2,3,4,4,4,4,3,2},
            {3,4,6,6,6,6,4,3},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {4,6,8,8,8,8,6,4},
            {3,4,6,6,6,6,4,3},
            {2,3,4,4,4,4,3,2}};


    public static void main(String[] args) {

        currentRow = secureRandom.nextInt(8);
        currentColumn = secureRandom.nextInt(8);

        board[currentRow][currentColumn] = moveNumber;

        int tempRow;
        int tempColumn;
        int nextMove1;
        int nextMove2;
        int minimumRow= 0;
        int minimumColumn = 0;
        int accessNum;
        boolean gameOver = false;
        boolean validMove;

        while ( !gameOver )
        {
            accessNum = accessBoard;

            for ( int moveNu = 0; moveNu < 8; moveNu++ )
            {
                tempRow = currentRow + vertical[ moveNu ];
                tempColumn = currentColumn + horizontal[ moveNu ];
                validMove = ValidMove( tempRow, tempColumn );

                if(validMove) {
                    if(accessibility[tempRow][tempColumn] < accessNum) {
                        accessNum = accessibility[tempRow][tempColumn];
                        minimumRow = tempRow;
                        minimumColumn = tempColumn;
                    }
                    else if(accessibility[tempRow][tempColumn] == accessNum){
                        nextMove1 = findMove(tempRow, tempColumn);
                        nextMove2 = findMove(minimumRow, minimumColumn);

                        if(nextMove1 < nextMove2) {
                            accessNum = accessibility[tempColumn][tempColumn];
                            minimumColumn = tempColumn;
                            minimumRow = tempRow;
                        }

                    }
                    --accessibility[tempRow][tempColumn];
                }
            }

            if(accessNum == accessBoard) {
                gameOver = true;
            }
            else {
                currentRow = minimumRow;
                currentColumn = minimumColumn;
                board[currentRow][currentColumn] = ++moveNumber;
            }
        }

        System.out.printf( "The tour had %d moves.\n", moveNumber);

        printBoard();
    }

    public static int findMove(int row, int column){
        int tempRow, tempColumn;
        int tempAccessNumber = accessBoard;

        int[][] tempAccessibility = new int[8][8];

        for ( int i = 0; i < tempAccessibility.length; i++ )
            for ( int j = 0; j < tempAccessibility.length; j++ )
                tempAccessibility[i][j] = accessibility[ i][j ];

        for ( int moveNum = 0; moveNum < board.length; moveNum++ )
        {
            tempRow = row + vertical[ moveNum ];
            tempColumn = column + horizontal[ moveNum ];

            if ( ValidMove( tempRow, tempColumn ) )
            {
                if ( accessibility[ tempRow][tempColumn ] < tempAccessNumber ) {
                    tempAccessNumber = tempAccessibility[tempRow][tempColumn];
                }
                --tempAccessibility[ tempRow][tempColumn];
            }
        }

        return tempAccessNumber;
    }

    public static boolean ValidMove( int row, int column )
    {
        //if in bound and unvisited
        if(row>=0 & row < 8 && column >= 0 && column < 8 && board[row][column] == 0){
            return true;
        }
        else
            return false;

    }

    public static void printBoard()
    {
        System.out.println();
        for( int row = 0; row < board.length; row++ )
        {
            for(int column = 0; column < board.length; column++ )
                System.out.printf(" %2d ", board[row][column] );
            System.out.println();
        }
    }
}
