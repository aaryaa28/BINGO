import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

// Bingo board for the computer player
public class BingoGridComputer extends BingoGrid
{
    public BingoGridComputer() {//constructor
        super();     //calling super class constructor

        indentX = 520;//initializing indentX to 520
        indentY = 250;        //initializing indentY to 250
        initializeGrid();//calling initializeGrid method
    }

    public void paintComponent(Graphics g) {//paints the board        
        Graphics2D g2 = (Graphics2D) g;//creating object of Graphics2D class

        //font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = 25;//font for everything else
        int letterFont = 48;//font for B I N G O letters
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);//creating object of Font class
        Font font2 = new Font("SansSerif", Font.BOLD, letterFont);//creating object of Font class
        g2.setFont(font);;//setting font to font

        //draws the board
        for (int row = 0; row < grid.length; row++) {//for loop
            for (int col = 0; col < grid.length; col++) {//for loop
                BingoSquare square = grid[row][col];//creating object of BingoSquare class

                g2.draw(square);//drawing square
                if (square.getIsClicked() ) {//if square is clicked
                    g2.setColor(Color.PINK);//setting color to pink
                    if (square.getIsWinner()) {//if square is winner
                        g2.setColor(Color.RED);//setting color to red
                    }
                    g2.fill(square);                    //fills the square
                    g2.setColor(Color.BLACK);//setting color to black
                    g2.draw(square);//drawing square
                }
                
                //the middle space is a freebie
                if (row == 2 && col == 2) {}//if row is equal to 2 and col is equal to 2
                else {//else
                    int value = grid[row][col].getValue();//initializing value to value of grid[row][col]
                    int xCoord = (int)square.getX() + (SQUARE_SIZE / 4);//initializing xCoord to x coordinate
                    int yCoord = (int)square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);//initializing yCoord to y coordinate
                    if (value < 10)//if value is less than 10
                        g2.drawString(" " + value + "", xCoord, yCoord);//drawing string
                    else
                        g2.drawString(value + "", xCoord, yCoord);//drawing string
                }

                //draws the B I N G O letters
                if (row == 0) {//if row is equal to 0
                    g2.setFont(font2);;//setting font to font2
                    g2.drawString(BINGO[col], (int)square.getX() + (SQUARE_SIZE / 6), (int)square.getY() - (SQUARE_SIZE / 4) );//drawing string
                    g2.setFont(font);//setting font to font
                }

                //writes "Computer" below the board
                if (row == grid.length - 1 && col == 0) {//if row is equal to grid.length - 1 and col is equal to 0
                    g2.setFont(font2);//setting font to font2
                    g2.setColor(Color.LIGHT_GRAY);//setting color to light gray
                    g2.drawString("Computer", (int)square.getX() + (SQUARE_SIZE / 3) * 2, (int)square.getY() + SQUARE_SIZE + ((SQUARE_SIZE / 3) * 2));//drawing string
                    g2.setFont(font);//setting font to font
                    g2.setColor(Color.BLACK);//setting color to black
                }
            }
        }

        //prints message if grid has won
        g2.setColor(Color.RED);//setting color to red
        g2.drawString(winnerMessage, 545, 190);//drawing string
    }

    public void highlightSquare() {//highlights a square
        for (int x : BingoNumbers.numbers) {//for loop
            for (int row = 0; row < WIDTH; row++) {//for loop
                for (int col = 0; col < LENGTH; col++) {//for loop
                    if (x == grid[row][col].getValue()) {//if x is equal to grid[row][col]
                        grid[row][col].setStatus(true);//setting status to true
                        grid[row][col].setIsClicked(true);//setting isClicked to true
                    }
                }
            }
        }
    }
}
