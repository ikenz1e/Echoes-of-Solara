package Main; 
import javax.swing.JFrame;

public class Window extends JFrame{

    public GamePanel gamePanel;

    public Window(){
        setupWindow();
    }

    public void setupWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Echoes of Solara ");

        gamePanel = new GamePanel();
        
        this.add(gamePanel);
        this.pack();
    }

    public void displayWindow(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startGame(){
        gamePanel.startGameThread();
    }

}
