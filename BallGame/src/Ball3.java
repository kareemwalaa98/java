import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball3 implements Runnable {

	protected int ThreadBall3_flag = 1;
	protected int TargetBall3_X = 669;
	protected int TargetBall3_y = 240;
	protected int TargetBall3_dirX = -1;
	protected int TargetBall3_dirY = 0;
	protected int TargetBall3_score = 6;

	@Override
	public void run() {
		//System.out.println("Ball_3 Thread :" + Thread.currentThread().getName());
		TargetBall3_X += TargetBall3_dirX;
		TargetBall3_y += TargetBall3_dirY;
		if (TargetBall3_X < 0) // left border collosion check
		{
			TargetBall3_dirX = -TargetBall3_dirX;
		}

		if (TargetBall3_X > 670) // Right border collosion check
		{
			TargetBall3_dirX = -TargetBall3_dirX;
		}
			
	}

	public void drawDynamicBall3(Graphics g) {
		if (ThreadBall3_flag == 1) {
			g.setColor(Color.cyan);
			g.fillOval(TargetBall3_X, TargetBall3_y, 50, 50);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 29));
			g.drawString("" + TargetBall3_score, TargetBall3_X + 17, TargetBall3_y + 31);
		}
	}
}
