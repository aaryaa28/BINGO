import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

//Bingo squares that contain a unique number between 1-75.

public class BingoSquare extends Rectangle
{
    private int value;//the value of the square
    private boolean status; //true if the value has been called by the number generator
    private boolean isClicked; //true if the user has clicked on the box
    private static final int RANGE = 15; //range of the numbers per column.
    //e.g., the B-column can only have numbers ranging from 1 to 15.
    private boolean isWinner; //this square is part of the winning sequence

    private Random generator = new Random();

    
    //Constructor for BingoSquare
    
    public BingoSquare(int x1, int y1, int width, int height) {//constructor
        super(x1, y1, width, height);//calling super class constructor
        status = false;//initializing status to false
        isClicked = false;//initializing isClicked to false
        isWinner = false;//initializing isWinner to false
    }

    
    //Mutator method for value
     
    public void setValue(int newValue) {//mutator method
        value = newValue;//setting value to newValue
    }


    // Accessor method for value

    public int getValue() {//accessor method
        return value;
    }

    
    // Changes the status of the square.
    
    public void setStatus(boolean newStatus) {//mutator method
        status = newStatus;//setting status to newStatus
    }

    
    // Returns the current status of the square
    
    public boolean getStatus() {//accessor method
        return status;//returning status
    }

    
    // Changes the isClicked boolean of the square
    
    public void setIsClicked(boolean newIsClicked) {//mutator method
        isClicked = newIsClicked;//setting isClicked to newIsClicked
    }

     // Returns the isClicked boolean of the square
    
    public boolean getIsClicked() {//accessor method
        return isClicked;//returning isClicked
    }

    
    // Changes the isWinner boolean of the square
    
    public void setIsWinner(boolean newIsWinner) {//mutator method
        isWinner = newIsWinner;//setting isWinner to newIsWinner
    }

    
    // Returns the isWinner boolean of the square
        
    public boolean getIsWinner() {//accessor method
        return isWinner;//returning isWinner
    }

    
    //Creates a unique number for the square.
    
    public int createNum(int col) {//method to create unique number
        int temp = RANGE * (col - 1) + (generator.nextInt(15) + 1);//creating random number
        value = temp;//setting value to temp
        return temp;//returning temp
    }
}
