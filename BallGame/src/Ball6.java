import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball6 implements Runnable {

	protected int ThreadBall6_flag = 1;
	protected int TargetBall6_X = 342;
	protected int TargetBall6_y = 450;
	protected int TargetBall6_dirX = -1;
	protected int TargetBall6_dirY = 1;
	protected int TargetBall6_score = 3;
	
	@Override
	public void run() {
		//System.out.println("Ball_6 Thread :" + Thread.currentThread().getName());
		TargetBall6_X += TargetBall6_dirX;
		TargetBall6_y += TargetBall6_dirY;
		if (TargetBall6_X < 0) // left border collosion check
		{
			TargetBall6_dirX = -TargetBall6_dirX;
		}

		if (TargetBall6_y < 0) { // TOP border collosion check { TargetBall1_dirY = -
			TargetBall6_dirY = -TargetBall6_dirY;
		}

		if (TargetBall6_X > 670) // Right border collosion check
		{
			TargetBall6_dirX = -TargetBall6_dirX;
		}
		if (TargetBall6_y > 520) // Bottom border collosion check
		{
			TargetBall6_dirY = -TargetBall6_dirY;
		}		
	}
	
	
	public void drawDynamicBall6(Graphics g) {
		if (ThreadBall6_flag == 1) {
			g.setColor(Color.orange);
			g.fillOval(TargetBall6_X, TargetBall6_y, 35, 35);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString("" + TargetBall6_score, TargetBall6_X + 13, TargetBall6_y + 27);
		}
	}

	

}
