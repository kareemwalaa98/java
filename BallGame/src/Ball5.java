import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball5 implements Runnable {

	protected int ThreadBall5_flag = 1;
	protected int TargetBall5_X = 150;
	protected int TargetBall5_y = 500;
	protected int TargetBall5_dirX = 1;
	protected int TargetBall5_dirY = -1;
	protected int TargetBall5_score = 10;
	@Override
	public void run() {
		//System.out.println("Ball_5 Thread :" + Thread.currentThread().getName());
		TargetBall5_X += TargetBall5_dirX;
		TargetBall5_y += TargetBall5_dirY;
		if (TargetBall5_X < 0) // left border collosion check
		{
			TargetBall5_dirX = -TargetBall5_dirX;
		}

		if (TargetBall5_y < 0) { // TOP border collosion check { TargetBall1_dirY = -
			TargetBall5_dirY = -TargetBall5_dirY;
		}

		if (TargetBall5_X > 670) // Right border collosion check
		{
			TargetBall5_dirX = -TargetBall5_dirX;
		}
		if (TargetBall5_y > 520) // Bottom border collosion check
		{
			TargetBall5_dirY = -TargetBall5_dirY;
		}
		
	}

	public void drawDynamicBall5(Graphics g) {
		if (ThreadBall5_flag == 1) {
			g.setColor(Color.MAGENTA);
			g.fillOval(TargetBall5_X, TargetBall5_y, 33, 33);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("" + TargetBall5_score, TargetBall5_X + 6, TargetBall5_y + 20);
		}
	}
}
