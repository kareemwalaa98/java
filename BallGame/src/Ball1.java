import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball1 implements Runnable {

	protected int ThreadBall1_flag = 1;
	protected int TargetBall1_X = 100;
	protected int TargetBall1_y = 60;
	protected int TargetBall1_dirX = -3;
	protected int TargetBall1_dirY = 0;
	protected int TargetBall1_score = 20;

	@Override
	public void run() {
		//System.out.println("Ball_1 Thread moving :" + Thread.currentThread().getName());
		TargetBall1_X += TargetBall1_dirX;
		TargetBall1_y += TargetBall1_dirY;
		if (TargetBall1_X < 0)// left border collosion check
		{
			TargetBall1_dirX = -TargetBall1_dirX;
		}

		if (TargetBall1_X > 670) // Right border collosion check
		{
			TargetBall1_dirX = -TargetBall1_dirX;
		}
	}

	public void drawDynamicBall1(Graphics g) {
		//System.out.println("Ball_1 Thread drawing :" + Thread.currentThread().getName());
		if (ThreadBall1_flag == 1) {
			g.setColor(Color.RED);
			g.fillOval(TargetBall1_X, TargetBall1_y, 30, 30);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("" + TargetBall1_score, TargetBall1_X + 3, TargetBall1_y + 20);
		}
	}

	

}
