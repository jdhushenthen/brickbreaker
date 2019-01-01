import javax.swing.*;
import java.awt.*;

public class Gui
{
    JFrame window;
	 DrawPanel panel;

    public Gui()
    {
        window = new JFrame("Collision detection, movement, double buffering, and a game loop!");
    	  panel = new DrawPanel();    
		  window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  window.getContentPane().add(panel);
        window.setVisible(true);
    }

    public void go()
    {
    	panel.startGame();
	 }  
	 
	 public static void main(String[]args)
    {
        Gui game = new Gui();
		  game.go();
    }
}