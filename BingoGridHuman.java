import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

 // Bingo board for the human player
public class BingoGridHuman extends BingoGrid
{
    public BingoGridHuman() {//constructor
        super();        //calling super class constructor

        indentX = 50;//initializing indentX to 50
        indentY = 250;        //initializing indentY to 250
        initializeGrid();//calling initializeGrid method
    }

    public void highlightSquare(int x, int y) {//method to highlight the square
        for (int row = 0; row < WIDTH; row++) {//for loop
            for (int col = 0; col < LENGTH; col++) {//for loop
                BingoSquare square = grid[row][col];//creating object of BingoSquare class
                if (row == 2 && col == 2) {}//if row is equal to 2 and col is equal to 2
                else if (square.contains(x, y)) {//if square contains (x, y)
                    if (square.getIsClicked()) {//if square is clicked
                        square.setIsClicked(false);//setting isClicked to false
                    } else {//else
                        square.setIsClicked(true);//setting isClicked to true
                    }//end of if-else
                    break;//breaking the loop
                }
            }
        }
        repaint();//calling repaint method
    }

    public void isCalled() { //method to check if the number is called       
        for (int x : BingoNumbers.numbers) {//for each loop
            for (int row = 0; row < WIDTH; row++) {//for loop
                for (int col = 0; col < LENGTH; col++) {//for loop
                    if (x == grid[row][col].getValue()) {//if x is equal to value of grid[row][col]
                        grid[row][col].setStatus(true);//setting status to true
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {     //paints the board   
        Graphics2D g2 = (Graphics2D) g;//creating object of Graphics2D class

        //font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = 25; //font for everything else
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);//creating object of Font class

        int letterFont = 48; //font for B I N G O letters
        Font font2 = new Font("SansSerif", Font.BOLD, letterFont);//creating object of Font class

        g2.setFont(font);//setting font to font

        //draws the board
        for (int row = 0; row < grid.length; row++) {//for loop
            for (int col = 0; col < grid.length; col++) {//for loop
                BingoSquare square = grid[row][col];//creating object of BingoSquare class

                g2.draw(square);//drawing square

                //highlights the square if it is clicked
                if (square.getIsClicked() ) {//if square is clicked
                    g2.setColor(Color.YELLOW);//setting color to yellow
                    g2.fill(square);                    //fills the square
                    g2.setColor(Color.BLACK);//setting color to black
                    g2.draw(square);//drawing square
                }
                
                //highlights the square if it is part of the winning sequence
                if (square.getIsWinner()) {//if square is winner
                    g2.setColor(Color.GREEN);//setting color to green
                    g2.fill(square);//filling the square
                    g2.setColor(Color.BLACK);//setting color to black
                    g2.draw(square);//drawing square
                }

                //the middle space is a freebie
                if (row == 2 && col == 2) {}//if row is equal to 2 and col is equal to 2
                else {
                    int value = grid[row][col].getValue();//getting value of grid[row][col]
                    int xCoord = (int)square.getX() + (SQUARE_SIZE / 4);//getting x coordinate
                    int yCoord = (int)square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);//getting y coordinate
                    if (value < 10)//if value is less than 10
                        g2.drawString(" " + value + "", xCoord, yCoord);//drawing string
                    else
                        g2.drawString(value + "", xCoord, yCoord);//drawing string
                }

                //prints the letters B I N G O above the board
                if (row == 0) {//if row is equal to 0
                    g2.setFont(font2);//setting font to font2
                    g2.drawString(BINGO[col], (int)square.getX() + (SQUARE_SIZE / 6), (int)square.getY() - (SQUARE_SIZE / 4) );//drawing string
                    g2.setFont(font);//setting font to font
                }

                //writes "Human" below the board
                if (row == grid.length - 1 && col == 1) {//if row is equal to grid.length - 1 and col is equal to 1
                    g2.setFont(font2);//setting font to font2
                    g2.setColor(Color.LIGHT_GRAY);//setting color to light gray
                    g2.drawString("Human", (int)square.getX() + SQUARE_SIZE / 4 - 5, (int)square.getY() + SQUARE_SIZE + ((SQUARE_SIZE / 3) * 2));//drawing string
                    g2.setFont(font);//setting font to font
                    g2.setColor(Color.BLACK);//setting color to black
                }
            }
        }

        //prints message if grid has won
        g2.setColor(Color.RED);//setting color to red
        g2.drawString(winnerMessage, 50, 190);//drawing string
    }
}
