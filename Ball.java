public class Ball extends Paddle {
	public boolean up, down;
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
	public Ball (int x, int y, int s) {
		super(x, y, s);
		up = true;
		down = false;
		left = true;
		width = 100;
		height = 100;
	}
}