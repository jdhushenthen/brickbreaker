   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.image.*;
   import java.awt.*;

    public class DrawPanel extends JPanel implements KeyListener
   {	
   
      BufferedImage buffer;
      Paddle paddle;
      Ball ball;
      Image playerImage = Toolkit.getDefaultToolkit().getImage("player.jpg");
      Image ballImage = Toolkit.getDefaultToolkit().getImage("enemy.jpg");
      Image brickImage = Toolkit.getDefaultToolkit().getImage("brick.jpg");
      Image background = Toolkit.getDefaultToolkit().getImage("background.jpg");
      Brick[][] bricks = new Brick[3][5];
		boolean tempUp, tempDown, tempRight, tempLeft;
   	   
       public DrawPanel()
      {
         setIgnoreRepaint(true);
         addKeyListener(this);
         setFocusable(true);
      }
   
       public void keyTyped(KeyEvent e)
      {
      
      }
   
       public void keyPressed(KeyEvent e)
      {
         int key = e.getKeyCode();
         if (key == KeyEvent.VK_LEFT)
            paddle.left = true;
         if (key == KeyEvent.VK_RIGHT)
            paddle.right = true;  
      }
   
       public void keyReleased(KeyEvent e)
      {
         int key = e.getKeyCode();
         if (key == KeyEvent.VK_LEFT)
            paddle.left = false;
         if (key == KeyEvent.VK_RIGHT)
            paddle.right = false;
      }
       public void initialize()
      {
         buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
         paddle = new Paddle(300,500,2);
         ball = new Ball(400, 400, 3);
      	
         for (int r = 0; r < bricks.length; r++)
            for (int c = 0; c < bricks[r].length; c++)
               bricks[r][c] = new Brick((c*150)+(5*(c+1)), (r*50)+(5*(r+1)));
      }
   
       public void update()
      {
         paddle.move(800);
         ball.move();
      }
   
       public void checkCollisions()
      {
      	
         if(ball.getY() <= 0) {
            ball.up = false;
            ball.down = true;
         }
         if(ball.getX() <= 0) {
            ball.left = false;
            ball.right = true;
         }
         if(ball.getX() >= 800-ball.getWidth()) {
            ball.right = false;
            ball.left = true;
         }
     		
			if((ball.getX() + ball.getWidth() >= paddle.getX()) && (ball.getX() <= paddle.getX() + paddle.getWidth()))	
				if(ball.getY() + ball.getHeight() >= paddle.getY() - paddle.getHeight()) {
					ball.up = true;
					ball.down = false;
					if((ball.getX() + ball.getWidth() >= paddle.getX()) && (ball.getX() <= paddle.getX() + paddle.getWidth()/2)) {
						ball.right = false;
          		   ball.left = true;
					} else if((ball.getX() + ball.getWidth() >= paddle.getX()+paddle.getWidth()/2) && (ball.getX() <= paddle.getX())) {
						ball.left = false;
            		ball.right = true;
					}
				}
			
			for (int r = 0; r < bricks.length; r++)
            for (int c = 0; c < bricks[r].length; c++) {
		      	if(ball.getY() >= bricks[r][c].getY() + bricks[r][c].getHeight()) {
		            tempUp = false;
		            tempDown = true;
		         }
		         if(ball.getY() + ball.getHeight() <= bricks[r][c].getY()) {
		            tempUp = true;
		            tempDown = false;
		         }
		         if(ball.getX() + ball.getWidth()<= bricks[r][c].getX()) {
		            tempLeft = true;
		            tempRight = false;
		         }
		         if(ball.getX() <= bricks[r][c].getX() + bricks[r][c].getHeight()) {
		            tempLeft = false;
		            tempRight = true;
		         }
				}
			
         for (int r = 0; r < bricks.length; r++)
            for (int c = 0; c < bricks[r].length; c++)
               if(bricks[r][c].visible && ball.getBounds().intersects(bricks[r][c].getBounds()))
               {
                  bricks[r][c].visible = false;
               	ball.up = tempUp;
						ball.down = tempDown;
						ball.right = tempRight;
						ball.left = tempLeft;
               }			
      	    
      }
   
       public void drawBuffer()
      {
         Graphics2D b = buffer.createGraphics();
         b.setColor(Color.black);
         b.drawImage(background, 0, 0, null);
         for (int r = 0; r < bricks.length; r++)
            for (int c = 0; c < bricks[r].length; c++)
               if(bricks[r][c].visible == true)
                  b.drawImage(brickImage, bricks[r][c].x, bricks[r][c].y, null);
         
         b.drawImage(playerImage, paddle.getX(), paddle.getY(), null);
         b.drawImage(ballImage, ball.getX(), ball.getY(), null);
         //b.dispose();
         
      }
   
       public void drawScreen()
      {
         Graphics2D g = (Graphics2D)this.getGraphics();
         g.drawImage(buffer,0,0,this);
         Toolkit.getDefaultToolkit().sync();
         g.dispose();
      }
   
       public void startGame()
      {
         initialize();
         while(true)
         {
            try
            {
               update();
               checkCollisions();
               drawBuffer();
               drawScreen();
               Thread.sleep(15);
            }
                catch(Exception e)
               {
                  e.printStackTrace();
               }    
         }
      }
    
   }