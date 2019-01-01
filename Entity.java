import java.awt.Rectangle;
import java.awt.*;

public class Entity
{
     int x,y,speed,width,height;
     boolean up, down, left, right,collision, stop;
   
    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
        speed = 3;
        width = 100;
        height = 100;
        up = false;
        down = false;
        left = false;
        right = false;
		  collision = false;
   }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void move()
    {
        if (up)
            y -= speed;
        if (down)
            y += speed;
        if (left)
            x -= speed;
        if (right)
            x += speed;
    }
}