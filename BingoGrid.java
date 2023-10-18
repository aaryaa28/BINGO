import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

// Bingo board for the human player
public class BingoGrid extends JComponent
{    
    protected BingoSquare[][] grid;//declaring 2D array of BingoSquare class
    protected ArrayList<Integer> bingoGridNumbers;//declaring ArrayList of Integer class
    protected final int SQUARE_SIZE = 60;//declaring and initializing SQUARE_SIZE to 60
    protected final int LENGTH = 5;//declaring and initializing LENGTH to 5
    protected final int WIDTH = 5;//declaring and initializing WIDTH to 5
    public static final String[] BINGO = {"B", " I", "N", "G", "O"};//declaring and initializing BINGO to {"B", " I", "N", "G", "O"}
    public int indentX;//declaring indentX
    public int indentY;//declaring indentY
    protected String winnerMessage;//declaring winnerMessage

    public BingoGrid() {//constructor
        grid = new BingoSquare[WIDTH][LENGTH];//initializing grid
        bingoGridNumbers = new ArrayList<Integer>();//initializing bingoGridNumbers
        winnerMessage = "";//initializing winnerMessage
    }

    public boolean isFound(int value, int r , int c)//method to check if the value is found
    {
        for (int row = 0; row < r; row++) {//for loop
            for (int col = 0; col <= c; col++) {//for loop
                if (value == grid[row][col].getValue()) {//if value is equal to value of grid[row][col]
                    return true;//returning true
                }
            }
        }
        return false;//returning false
    }

    public void initializeGrid() {//method to initialize the grid
        for (int row = 0; row < grid.length; row++) {//for loop
            for (int col = 0; col < grid[0].length; col++) {//for loop
                grid[row][col] = new BingoSquare(col * SQUARE_SIZE + indentX, row * SQUARE_SIZE + indentY, SQUARE_SIZE, SQUARE_SIZE);//initializing grid[row][col]
                int value = grid[row][col].createNum(col + 1);//initializing value to value of grid[row][col]
                while (isFound(value,row,col))//while isFound method returns true
                    value = grid[row][col].createNum(col + 1);//initializing value to value of grid[row][col]
            }
        }

        //middle spot is freebie
        grid[WIDTH / 2][LENGTH / 2].setStatus(true);//setting status to true
        grid[WIDTH / 2][LENGTH / 2].setIsClicked(true);//setting isClicked to true
        winnerMessage = "";//initializing winnerMessage
    }

    public boolean checkWin() {//method to check if the player has won
        int winningNumber = 5;//initializing winningNumber to 5
        int count;//declaring count

        //checks the rows
        for (int row = 0; row < grid.length; row++) {//for loop
            count = 0;//initializing count to 0
            for (int col = 0; col < grid[0].length; col++) {//for loop
                if (grid[row][col].getStatus()) {//if status is true
                    grid[row][col].setIsWinner(true);//setting isWinner to true
                    count++;                //incrementing count
                }
                if (count == winningNumber) {//if count is equal to winningNumber
                    return true;             //returning true
                }
            }
            this.removeIsWinnerMark();//calling removeIsWinnerMark method
        }
        
        //checks the columns
        for (int col = 0; col < grid[0].length; col++) {//for loop
            count = 0;//initializing count to 0
            for (int row = 0; row < grid.length; row++) {//for loop
                if (grid[row][col].getStatus()) {//if status is true
                    grid[row][col].setIsWinner(true);//setting isWinner to true
                    count++;                //incrementing count
                }
                if (count == winningNumber) {//if count is equal to winningNumber
                    return true;             //returning true
                }
            }
            this.removeIsWinnerMark();//calling removeIsWinnerMark method
        }
        
        //check diagonal from top-left to bottom-right
        count = 0;//initializing count to 0
        for (int index = 0; index < grid.length; index++) {//for loop
            BingoSquare square = grid[index][index];//creating object of BingoSquare class
            if (square.getStatus()) {//if status is true
                square.setIsWinner(true);//setting isWinner to true
                count++;            //  incrementing count
            }
            if (count == winningNumber) {//if count is equal to winningNumber
                return true;//returning true
            }
        }        
        this.removeIsWinnerMark();//calling removeIsWinnerMark method

        //check diagonal from bottom-right to top-left
        count = 0;//initializing count to 0
        for (int index = grid.length - 1; index >= 0; index--) {//for loop
            BingoSquare square = grid[index][(grid.length - 1) - index];//creating object of BingoSquare class
            if (square.getStatus()) {//if status is true
                square.setIsWinner(true);//setting isWinner to true
                count++;//incrementing count
            }
            if (count == winningNumber) {//if count is equal to winningNumber
                return true;//returning true
                }
        }
        this.removeIsWinnerMark();//calling removeIsWinnerMark method
        return false;//returning false
    }
    
    public void removeIsWinnerMark() {//method to remove isWinner mark
        for (int row = 0; row < grid.length; row++) {//for loop
            for (int col = 0; col < grid[0].length; col++) {//for loop
                grid[row][col].setIsWinner(false);//setting isWinner to false
            }
        }
    }

    public void setWinnerMessage(String newMsg) {//method to set winnerMessage
        winnerMessage = newMsg;//setting winnerMessage to newMsg
    }
}
