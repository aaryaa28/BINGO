//import important classes
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JToolTip;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class BingoGame extends JFrame//class BingoGame
{
    public static final int WIDTH = 900;//declaring and initializing WIDTH to 900
    public static final int LENGTH = 700;//declaring and initializing LENGTH to 700

    private JPanel panel;//declaring panel
    private JButton reset;//declaring reset
    private JButton bingo;//declaring bingo
    private JButton nextNum;//declaring nextNum
    private JButton start;//declaring start
    private JButton stop;//declaring stop
    private Container layout;//declaring layout
    private Container boxLayout;//declaring boxLayout
    private BingoNumbers bingoNumbers;//declaring bingoNumbers
    private BingoGrid dummyGrid;//declaring dummyGrid
    private BingoGridHuman humanGrid;//declaring humanGrid
    private BingoGridComputer computerGrid;//declaring computerGrid
    private MouseListener mouseListener;//declaring mouseListener
    private ActionListener timer;//declaring timer
    private ActionListener buttonListener;//declaring buttonListener
    private final int DELAY;//declaring DELAY
    private boolean startGame = false;//declaring and initializing startGame to false
    private boolean winner = false;//declaring and initializing winner to false

    /**
     * Constructs the game window
     */
    public BingoGame() {//constructor
        super("Bingo Bash");//calling super class constructor
        setSize(WIDTH, LENGTH);//setting size

        panel = new JPanel();//creating object of JPanel class
        setLocationRelativeTo(null);//setting location to null

        //JButtons
        reset = new JButton("Reset");//creating object of JButton class
        bingo = new JButton("Bingo!");//creating object of JButton class
        nextNum = new JButton("Call Next Number");//creating object of JButton class
        start = new JButton("Start");//creating object of JButton class
        stop = new JButton("Stop");//creating object of JButton class

        //add JButtons to the panel
        panel.add(start);//adding start to panel
        panel.add(stop);//adding stop to panel
        panel.add(reset);//adding reset to panel
        panel.add(bingo);//adding bingo to panel
        panel.add(nextNum);//adding nextNum to panel

        //creates tooltips for buttons
        reset.setToolTipText("Resets the game");//setting tooltip text
        bingo.setToolTipText("I have bingo!");//setting tooltip text
        nextNum.setToolTipText("Call next number");//setting tooltip text
        start.setToolTipText("Starts/resumes the game");//setting tooltip text
        stop.setToolTipText("Stops/pauses the game");//setting tooltip text

        //adds ActionListeners to buttons
        buttonListener = new ButtonListener();//creating object of ButtonListener class
        reset.addActionListener(buttonListener);//adding buttonListener to reset
        bingo.addActionListener(buttonListener);//adding buttonListener to bingo
        nextNum.addActionListener(buttonListener);//adding buttonListener to nextNum
        start.addActionListener(buttonListener);//adding buttonListener to start
        stop.addActionListener(buttonListener);//adding buttonListener to stop

        layout = this.getContentPane();//initializing layout to content pane
        layout.add(panel, "South");//adding panel to layout
        setVisible(true);//setting visible to true

        mouseListener = new MouseClickListener();//creating object of MouseClickListener class
        timer = new MyTimer();//creating object of MyTimer class
        DELAY = 2500;//initializing DELAY to 2500
        Timer t = new Timer(DELAY, timer);//creating object of Timer class
        t.start();//starting timer

        humanGrid = new BingoGridHuman();//creating object of BingoGridHuman class
        computerGrid = new BingoGridComputer();//creating object of BingoGridComputer class
        bingoNumbers = new BingoNumbers();//creating object of BingoNumbers class

        add(humanGrid); //  add the grids to the frame
        setVisible(true);//setting visible to true

        add(computerGrid);//adding computerGrid to frame
        setVisible(true);//setting visible to true

        add(bingoNumbers);//adding bingoNumbers to frame
        setVisible(true);//setting visible to true

        humanGrid.addMouseListener(mouseListener);//adding mouseListener to humanGrid
        setVisible(true);//setting visible to true
    }

    //the timer
    class MyTimer implements ActionListener {//class MyTimer
        public void actionPerformed(ActionEvent event) {//method actionPerformed
            if (startGame && !winner) {//if startGame is true and winner is false
                bingoNumbers.generateNumber();//calling generateNumber method
                humanGrid.isCalled();//calling isCalled method
                computerGrid.highlightSquare();//calling highlightSquare method
                if (computerGrid.checkWin()) {//if checkWin method returns true
                    computerGrid.setWinnerMessage("WINNER: COMPUTER");//setting winnerMessage
                    winner = true;//setting winner to true
                }
                humanGrid.setWinnerMessage("");//setting winnerMessage
                bingoNumbers.repaint();//calling repaint method
                computerGrid.repaint();//calling repaint method
            }
        }
    }

    //The Mouse listener
    class MouseClickListener implements MouseListener {//class MouseClickListener
        public void mousePressed(MouseEvent event) {//method mousePressed
            int x = event.getX();//declaring and initializing x
            int y = event.getY();//declaring and initializing y
            humanGrid.highlightSquare(x, y);            //calling highlightSquare method
        }

        public void mouseReleased(MouseEvent event) {}//method mouseReleased

        public void mouseClicked (MouseEvent event) {}//method mouseClicked

        public void mouseEntered(MouseEvent event) {}//method mouseEntered

        public void mouseExited(MouseEvent event) {}//method mouseExited
    }

    //the button listener
    class ButtonListener implements ActionListener {//class ButtonListener
        public void actionPerformed(ActionEvent event) {//method actionPerformed
            Object source = event.getSource();//declaring and initializing source
            if (source == reset) { //resets the boards and the bingo number callings
                humanGrid.initializeGrid();//calling initializeGrid method
                computerGrid.initializeGrid();//calling initializeGrid method
                bingoNumbers.numbers.clear();//clearing numbers
                winner = false;//setting winner to false
            } else if (source == bingo) { //checks if the human grid has won
                if (!winner) {//if winner is false
                    if (humanGrid.checkWin()) {//if checkWin method returns true
                        humanGrid.setWinnerMessage("WINNER: HUMAN");//setting winnerMessage
                        winner = true;//setting winner to true
                    } else {
                        humanGrid.setWinnerMessage("Sorry, you haven't gotten bingo.");//setting winnerMessage
                    }
                }
            } else if (source == nextNum) { //calls the next bingo number and checks for winner
                if (!winner) {//if winner is false
                    bingoNumbers.generateNumber();//calling generateNumber method
                    humanGrid.setWinnerMessage("");//setting winnerMessage
                    computerGrid.highlightSquare();   //calling highlightSquare method
                    humanGrid.isCalled();         //calling isCalled method
                    if (computerGrid.checkWin()) {//if checkWin method returns true
                        computerGrid.setWinnerMessage("WINNER: COMPUTER");//setting winnerMessage
                        winner = true;//setting winner to true
                    }
                }
            } else if (source == start) { //starts the game
                startGame = true;//setting startGame to true
            } else if (source == stop) { //stops/pauses the game
                startGame = false;//setting startGame to false
            }
            computerGrid.highlightSquare();//calling highlightSquare method
            humanGrid.repaint();//calling repaint method
            computerGrid.repaint();//calling repaint method
            bingoNumbers.repaint();//calling repaint method
            layout.repaint();//calling repaint method
        }
    }
}