import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BingoNumbers extends JComponent
{
    public static ArrayList <Integer> numbers = new ArrayList<Integer>();//ArrayList to store the numbers

    //Checks if <code>value</code> already exists in the ArrayList
    //@return  true if a duplicate is found
     
    public boolean isFound(int value)//method to check if value is already present in the ArrayList
    {
        for (int x : numbers) {//for each loop
            if (value == (int) x)//if value is equal to x
                return true;//returning true
        }
        return false;//returning false
    }

     // Creates a unique number from 1-75 and puts it into the ArrayList
    public void generateNumber() {//method to generate a unique number from 1-75 and put it into the ArrayList
        boolean exit = false;//initializing exit to false
        Random generator = new Random();//creating object of Random class

        while (!exit && numbers.size() != 75) {//while exit is false and size of ArrayList is not equal to 75
            int newNumber = generator.nextInt(75) + 1; //generating a random number from 1-75
            if (!isFound(newNumber)) {//if newNumber is not present in the ArrayList
                numbers.add(newNumber);//adding newNumber to the ArrayList
                exit = true;//setting exit to true
            }
        }
    }

    // Displays the numbers
    public void paintComponent(Graphics g) {//method to display the numbers
        Graphics2D g2 = (Graphics2D) g;//creating object of Graphics2D class

        //font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int fontSize = 45;//initializing fontSize to 45
        int bigFont = 80;//initializing bigFont to 80
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);//creating object of Font class
        g2.setFont(font);//setting font

        String call = "Calling: ";//initializing call to "Calling: "
        g2.drawString(call, BingoGame.WIDTH / 2 - 85, 70);//drawing call at (BingoGame.WIDTH / 2 - 85, 70)
        if (!numbers.isEmpty()) {//if ArrayList is not empty
            int number = numbers.get(numbers.size() - 1);//getting the last element of the ArrayList
            int xCoord = BingoGame.WIDTH / 2 - 100;//initializing xCoord to BingoGame.WIDTH / 2 - 100
            int yCoord = 145;//initializing yCoord to 145
            g2.setFont(new Font("SansSerif", Font.BOLD, bigFont));//setting font
            if (number <= 15) {//if number is less than or equal to 15
                g2.drawString("B-" + number, xCoord, yCoord);//drawing "B-" + number at (xCoord, yCoord)
            } else if (number <= 30) {//if number is less than or equal to 30
                g2.drawString(" I-" + number, xCoord, yCoord);//drawing "I-" + number at (xCoord, yCoord)
            } else if (number <= 45) {//if number is less than or equal to 45
                g2.drawString("N-" + number, xCoord, yCoord);//drawing "N-" + number at (xCoord, yCoord)
            } else if (number <= 60) {//if number is less than or equal to 60
                g2.drawString("G-" + number, xCoord, yCoord);//drawing "G-" + number at (xCoord, yCoord)
            } else if (number <= 75) {//if number is less than or equal to 75
                g2.drawString("O-" + number, xCoord, yCoord);//drawing "O-" + number at (xCoord, yCoord)
            }
        }
    }
}
