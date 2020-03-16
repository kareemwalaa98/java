import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball4 implements Runnable {

	protected int ThreadBall4_flag = 1;
	protected int TargetBall4_X = 100;
	protected int TargetBall4_y = 200;
	protected int TargetBall4_dirX = 1;
	protected int TargetBall4_dirY = -1;
	protected int TargetBall4_score = 15;
	
	@Override
	public void run() {
		//System.out.println("Ball_4 Thread :" + Thread.currentThread().getName());
		TargetBall4_X += TargetBall4_dirX;
		TargetBall4_y += TargetBall4_dirY;
		if (TargetBall4_X < 0) // left border collosion check
		{
			TargetBall4_dirX = -TargetBall4_dirX;
		}

		if (TargetBall4_y < 0) { // TOP border collosion check { TargetBall1_dirY = -
			TargetBall4_dirY = -TargetBall4_dirY;
		}

		if (TargetBall4_X > 670) // Right border collosion check
		{
			TargetBall4_dirX = -TargetBall4_dirX;
		}
		if (TargetBall4_y > 520) // Bottom border collosion check
		{
			TargetBall4_dirY = -TargetBall4_dirY;
		}		
	}

	public void drawDynamicBall4(Graphics g) {
		if (ThreadBall4_flag == 1) {
			g.setColor(Color.PINK);
			g.fillOval(TargetBall4_X, TargetBall4_y, 45, 45);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString("" + TargetBall4_score, TargetBall4_X + 9, TargetBall4_y + 27);
		}
	}
}
