public class Paddle extends Brick {
	public boolean left, right;
	public int width, height;
	protected int speed;
	protected boolean visible = true;
	public void move(int screenWidth)
    { 
        if (left && x>speed)
            x -= speed;
        if (right && x+speed < screenWidth-width)
            x += speed;
    }
	public Paddle(int x, int y, int s) {
		super(x, y);
		speed = s;
		left = false;
		right = false;
		width = 200;
		height = 50;
	}
}