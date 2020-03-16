import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingWorker;

public class Ball7 extends SwingWorker<Void , Void> {
	protected int doit7 = 1;
	protected int ThreadBall7_flag = 1;
	protected int TargetBall7_X = 270;
	protected int TargetBall7_y = 400;
	protected int TargetBall7_dirX = 1;
	protected int TargetBall7_dirY = 0;
	protected int TargetBall7_score = 10;

	public void drawDynamicBall7(Graphics g) {
		if (ThreadBall7_flag == 1) {
			g.setColor(Color.WHITE);
			g.fillOval(TargetBall7_X, TargetBall7_y, 33, 33);
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("" + TargetBall7_score, TargetBall7_X + 6, TargetBall7_y + 20);
		}
	}
	@Override
	protected Void doInBackground() throws Exception {
			

				TargetBall7_X += TargetBall7_dirX;
				TargetBall7_y += TargetBall7_dirY;
				if (TargetBall7_X < 0) // left border collosion check
				{
					TargetBall7_dirX = -TargetBall7_dirX;
				}

				if (TargetBall7_y < 0) { // TOP border collosion check { TargetBall1_dirY = -
					TargetBall7_dirY = -TargetBall7_dirY;
				}

				if (TargetBall7_X > 670) // Right border collosion check
				{
					TargetBall7_dirX = -TargetBall7_dirX;
				}
				if (TargetBall7_y > 520) // Bottom border collosion check
				{
					TargetBall7_dirY = -TargetBall7_dirY;
				}		
		System.out.println("Ball_7 Thread :" + Thread.currentThread().getName());
		return null;

	}

}
