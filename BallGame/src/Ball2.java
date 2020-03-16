import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball2 implements Runnable  {
	
	protected int ThreadBall2_flag = 1;
	protected int TargetBall2_X = 1;
	protected int TargetBall2_y = 150;
	protected int TargetBall2_dirX = 1;
	protected int TargetBall2_dirY = 0;
	protected int TargetBall2_score = 9;
	@Override
	public void run() {
		//System.out.println("Ball_2 Thread :" + Thread.currentThread().getName());
		TargetBall2_X += TargetBall2_dirX;
		TargetBall2_y += TargetBall2_dirY;
		if (TargetBall2_X < 0) // left border collosion check
		{
			TargetBall2_dirX = -TargetBall2_dirX;
		}

		if (TargetBall2_X > 670) // Right border collosion check
		{
			TargetBall2_dirX = -TargetBall2_dirX;
		}		
	}

	public void drawDynamicBall2(Graphics g) {
		if (ThreadBall2_flag == 1) {
			g.setColor(Color.GREEN);
			g.fillOval(TargetBall2_X, TargetBall2_y, 40, 40);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString("" + TargetBall2_score, TargetBall2_X + 13, TargetBall2_y + 27);
		}
	}

}
