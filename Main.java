import javax.swing.JFrame;//importing JFrame class

public class Main//Main class
{
    public static void main(String[] args) {//main method
        BingoGame game = new BingoGame();//creating object of BingoGame class
        
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting default close operation
        game.setVisible(true);//setting visibility
    }
}
