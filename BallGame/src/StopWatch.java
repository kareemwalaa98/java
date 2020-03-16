import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class StopWatch  {
	boolean StopWatch_state = false; // false is stopping ,true running
	int second = 60;
	Timer  Game_timer ;

	public StopWatch() { // to avoid faster and faster overtime due to creating new timer every restart game
		Game_timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Stop_watch_Thread :"+Thread.currentThread().getName());
				StopWatch_state = true;
				second = second- 1;  
				if(second == 0) {
					StopWatch_state = false;
					Game_timer.stop();
				}
			}
		});		
	}
	public void start() {
		Game_timer.start();
	}
	
	public void reset() {
		second = 60 ;
	}

	public void Display_StopWatch(Graphics g) {

		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Timer : " + second , 4, 30);
		
	}
	
}
