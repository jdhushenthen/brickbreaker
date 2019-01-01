import java.awt.Rectangle;

public class Brick extends ScreenItem {
	int width, height;
	boolean collision;
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		width = 150;
		height = 50;
		collision = false;
		visible = true;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Rectangle getBounds()
   {
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
   }
	
	public int getWidth()
   {
        return width;
   }
    
   public int getHeight()
   {
        return height;
   }
	
}