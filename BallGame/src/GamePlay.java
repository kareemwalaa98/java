import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener, ActionListener {

	//to stop shooter ball from moving if hit paddle or bottom ground and reset it on paddle else keep moving
	private boolean play = false;

	// Game Timer 1 min 
	StopWatch game_timer ;
	
	// Total Score
	private int score = 0;

	// balls creation(classes)
	Ball1 ball1; 
    Ball2 ball2;
    Ball3 ball3;
    Ball4 ball4;
    Ball5 ball5;
    Ball6 ball6 ;
    
	//timers for the balls when intersection to disappear and appear again
	private Timer timer_ball1;
	private Timer timer_ball2;
	private Timer timer_ball3;
	private Timer timer_ball4;
	private Timer timer_ball5;
	private Timer timer_ball6;
	
	//timer for the main action listener to repeat the whole code 
	private Timer timer;
	private int delay = 2; 
    
	//paddle postion
	private int ShooterX = 300;
	
	// rectangle for shooter ball and rect for every thread ball to able to make intersection and inc score
	Rectangle rect; 
	Rectangle ShooterBallrect;
	
	// Thread pool for ball tasks
	ExecutorService ex ;
	
	//shooter ball postion on screen and moving coordinates
	private int ballposX = 342;
	private int ballposY = 530;
	private int ballXdir = 0;
	private int ballYdir = 0;
 
	// to avoid shooter ball to move with paddle when launched 
	private int Move_Ball_With_Paddle_Flag = 0;
	
	//Balls flags to check collsion or no to update color text
	int ball1_color_flag = 0 ;
	int ball2_color_flag = 0 ;
	int ball3_color_flag = 0 ;
	int ball4_color_flag = 0 ;
	int ball5_color_flag = 0 ;
	int ball6_color_flag = 0 ;
	
	// colors flag and properties
	int color_flag = 0 ;
	String [] color  = {"RED","GREEN","CYAN","PINK","PURPLE","ORANGE"};
	
	// equation flag flag and properties
	int eq_flag = 0 ;
	String [] equation = {"4 * 5","6 + 3","12 / 2","2^4 - 1","5 + 5","67 % 4"};
	
	// Random variable to change for colors and equations
	 Random random ;
	int selection ;
	int selection_eq ;
	
   // constructor that intialize balls and timer of the actionlistener
	public GamePlay() {
		ball1 = new Ball1();
		ball2 = new Ball2();
		ball3 = new Ball3();
		ball4 = new Ball4();
		ball5 = new Ball5();
		ball6 = new Ball6();
	    ex = Executors.newCachedThreadPool();
	    game_timer = new StopWatch();
	    random = new Random();
		selection = random.nextInt(color.length);
		selection_eq = random.nextInt(equation.length);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics g) // Graphics class method
	{
		// background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);

		// Thread Balls draw
		ball1.drawDynamicBall1(g);
		ball2.drawDynamicBall2(g);
		ball3.drawDynamicBall3(g);
		ball4.drawDynamicBall4(g);
		ball5.drawDynamicBall5(g);
		ball6.drawDynamicBall6(g);
       		
		// borders
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 3, 592);// left border
		g.fillRect(0, 0, 692, 3);// upper border
		g.fillRect(684, 0, 3, 592);// right border
		g.setColor(Color.cyan);
		g.fillRect(0, 560, 692, 3);// bottom border

		// paddle
		g.setColor(Color.green);
		g.fillRect(ShooterX, 550, 100, 8);

		// scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("SCORE:" + score, 535, 30);

		// Shooter Ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		//StopWatch
		game_timer.Display_StopWatch(g);
		
		// Colors printing randomly uing arrays and choosing random index
		if(selection == 0) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+10 seconds)", 125, 30);
		}else if (selection == 1) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+2 seconds)", 125, 30);
		}else if (selection == 2) {
			g.setColor(Color.CYAN);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+1 second)", 125, 30);
		}else if (selection == 3) {
			g.setColor(Color.PINK);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+7 seconds)", 125, 30);
		}else if (selection == 4) {
			g.setColor(Color.magenta);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+1 second)", 125, 30);
		}else {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString( color[selection]+"(+5 seconds)", 125, 30);
		}
		
		// Equations printing randomly uing arrays and choosing random index
				if(selection_eq == 0) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+50 score)",362, 30);
				}else if (selection_eq == 1) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+10 score)", 362, 30);
				}else if (selection_eq == 2) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+5 score)", 362, 30);
				}else if (selection_eq == 3) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+7 score)", 362, 30);
				}else if (selection_eq == 4) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+15 score)", 362, 30);
				}else {
					g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 22));
					g.drawString( equation[selection_eq]+" (+3 score)", 362, 30);
				}
		
		
		
		//game over condtion
		if(game_timer.second == 0)
		{
			timer.stop();
			System.out.println("Score : " + score);
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over , SCORE : " + score, 180, 250);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 250, 300);
		}
		
		g.dispose();
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
			if (play) // ball leaves paddle by one of three keys makes play = true
		      {
			// touch paddle shoot again from the paddle
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(ShooterX, 550, 100, 8))) 
			{
				play = false; // stop shooter ball from moving
				Move_Ball_With_Paddle_Flag = 0; // to allow moving with paddle
				ballposY -= 5; // to avoid the intersection condition from first press after touching the
								// paddle
			}
			ballposX += ballXdir;
			ballposY += ballYdir;
			if (ballposX < 0) // left border collosion check
			{
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) // TOP border collosion check
			{
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) // Right border collosion check
			{
				ballXdir = -ballXdir;
			}
			if (ballposY > 560) // Bottom border collosion check
			{
				Move_Ball_With_Paddle_Flag = 0;
				play = false;
				ballposX = ShooterX + 45;
				ballposY = 530;
			}
		}
		
		// Rectange pointer on shooter ball to check intersection
		ShooterBallrect = new Rectangle(ballposX, ballposY, 20, 20);

		// Task for ball 1

		rect = new Rectangle(ball1.TargetBall1_X, ball1.TargetBall1_y, 30, 30);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x + 2  || ballposX == rect.x + rect.width + 2) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 0 && game_timer.StopWatch_state) { //check if it's color turn or not by index (sorted array from 0 --> 5 by colors of balls
			selection = random.nextInt(color.length);
			game_timer.second += 10 ;
			}
			if(selection_eq == 0 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 50 ;
				}
			score += ball1.TargetBall1_score;
			ball1.TargetBall1_X = 1500;
			ball1.TargetBall1_y = 1500;
			ball1.ThreadBall1_flag = 0;
			timer_ball1 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball1.TargetBall1_X = 100;
					ball1.TargetBall1_y = 60;
					ball1.ThreadBall1_flag = 1;
				}
			});
			timer_ball1.start();
			timer_ball1.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball1); //was running on the swing thread AWT-EVENTQUEUE
		ex.submit(ball1);  // running in thread pool

		// Task for ball 2
		
		rect = new Rectangle(ball2.TargetBall2_X,ball2.TargetBall2_y, 40, 40);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x + 2 || ballposX == rect.x + rect.width + 2) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 1&& game_timer.StopWatch_state) {
				selection = random.nextInt(color.length);
				game_timer.second += 2 ;
				}
			if(selection_eq == 1 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 10 ;
				}
			ball2.TargetBall2_X = 1500;
			ball2.TargetBall2_y = 1500;
			ball2.ThreadBall2_flag = 0;
			score += ball2.TargetBall2_score;
			timer_ball2 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball2.TargetBall2_X = 1;
					ball2.TargetBall2_y = 150;
					ball2.ThreadBall2_flag = 1;
				}
			});
			timer_ball2.start();
			timer_ball2.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball2);
		ex.submit(ball2);
		
		// Task for ball 3
		
		rect = new Rectangle(ball3.TargetBall3_X, ball3.TargetBall3_y, 50, 50);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x +2 || ballposX  == rect.x + rect.width + 2) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 2 && game_timer.StopWatch_state) {
				selection = random.nextInt(color.length);
				game_timer.second += 1 ;
				}
			if(selection_eq == 2 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 5 ;
				}
			ball3.TargetBall3_X = 1500;
			ball3.TargetBall3_y = 1500;
			ball3.ThreadBall3_flag = 0;
			score += ball3.TargetBall3_score;
			timer_ball3 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball3.TargetBall3_X = 669;
					ball3.TargetBall3_y = 240;
					ball3.ThreadBall3_flag = 1;
				}
			});
			timer_ball3.start();
			timer_ball3.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball3);
		ex.submit(ball3);
		
		// Task for ball 4
		
		rect = new Rectangle(ball4.TargetBall4_X, ball4.TargetBall4_y, 45, 45);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x + 2 || ballposX == rect.x + rect.width + 2) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 3 && game_timer.StopWatch_state) {
				selection = random.nextInt(color.length);
				game_timer.second += 7 ;
				}
			if(game_timer.StopWatch_state) { // no socre increase if ball 4 5 6 hit the shooter without game start
				score += ball4.TargetBall4_score;
				}
			if(selection_eq == 3 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 7 ;
				}
			ball4.TargetBall4_X = 1500;
			ball4.TargetBall4_y = 1500;
			ball4.ThreadBall4_flag = 0;
			timer_ball4 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball4.TargetBall4_X = 100;
					ball4.TargetBall4_y = 200;
					ball4.ThreadBall4_flag = 1;
				}
			});
			timer_ball4.start();
			timer_ball4.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball4);
		ex.submit(ball4);
		
		// Task for ball 5
		
		rect = new Rectangle(ball5.TargetBall5_X,ball5.TargetBall5_y, 33, 33);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x + 2 || ballposX  == rect.x + rect.width + 2) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 4 && game_timer.StopWatch_state) {
				selection = random.nextInt(color.length);
				game_timer.second += 1 ;
				}
			if(game_timer.StopWatch_state) {
				score += ball5.TargetBall5_score;
				}
			if(selection_eq == 4 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 15 ;
				}
			ball5.TargetBall5_X = 1500;
			ball5.TargetBall5_y = 1500;
			ball5.ThreadBall5_flag = 0;
			timer_ball5 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball5.TargetBall5_X = 150;
					ball5.TargetBall5_y = 500;
					ball5.ThreadBall5_flag = 1;
				}
			});
			timer_ball5.start();
			timer_ball5.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball5);
		ex.submit(ball5);
		
		// Task for ball 6
		
		rect = new Rectangle(ball6.TargetBall6_X, ball6.TargetBall6_y, 35, 35);
		if (ShooterBallrect.intersects(rect) ) {
			if (ballposX == rect.x + 2 || ballposX  == rect.x + rect.width + 2 ) {
				ballXdir = -ballXdir;
			} else {
				ballYdir = -ballYdir;
			}
			if(selection == 5 && game_timer.StopWatch_state) {
				selection = random.nextInt(color.length);
				game_timer.second += 5 ;
				}
			if(game_timer.StopWatch_state) {
				score += ball6.TargetBall6_score;
				}
			if(selection_eq == 5 && game_timer.StopWatch_state) { //check if it's equation turn or not by index (sorted array from 0 --> 5 by colors of balls
				selection_eq = random.nextInt(equation.length);
				score += 3 ;
				}
			ball6.TargetBall6_X = 1500;
			ball6.TargetBall6_y = 1500;
			ball6.ThreadBall6_flag = 0;
			timer_ball6 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ball6.TargetBall6_X = 342;
					ball6.TargetBall6_y = 450;
					ball6.ThreadBall6_flag = 1;
				}
			});
			timer_ball6.start();
			timer_ball6.setRepeats(false);
		}
		//SwingUtilities.invokeLater(ball6);
		ex.submit(ball6);
		
		
		repaint(); // Graphic class method to update screen .. happens on swing thread(AWT)
	} 
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// check for keys and movment of paddle 
		
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 && play != true) {
			Move_Ball_With_Paddle_Flag = 1;
			play = true;
			ballXdir = -2;
			ballYdir = -2;
			if(!game_timer.StopWatch_state)// to start timer at the first shoot only
				game_timer.start();		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 && play != true ) {
			Move_Ball_With_Paddle_Flag = 1;
			play = true;
			ballXdir = 2;
			ballYdir = -2;
			if(!game_timer.StopWatch_state)// to start timer at the first shoot only
				game_timer.start();		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// paddle limit + ball move with it or no
			if (ShooterX >= 600) {
				ShooterX = 583;
				if (!play) // no ball repostion while playing
					ballposX = 626;
			} else {
				moveRight();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// paddle limit + ball move with it or no
			if (ShooterX < 10) {
				ShooterX = 10;
				if (!play) // no ball repostion while playing
					ballposX = 52;
			} else {
				moveLeft();
			}
		}
		// restart
		if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (game_timer.second == 0)) {  //to be sure not to press enter and restart if we still playing
			// Restart the game
			ballposX = 342;
			ballposY = 530;
			ballXdir = 0;
			ballYdir = 0;
			ShooterX = 300;
			play = false;
			Move_Ball_With_Paddle_Flag = 0;
			score = 0;
			selection = random.nextInt(color.length);
			game_timer.reset();
			timer.start();
		}
		
		if ( ( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_NUMPAD8 )&& play != true ) {
			play = true;
			ballXdir = 0;
			ballYdir = -2;
			Move_Ball_With_Paddle_Flag = 1;
			if(!game_timer.StopWatch_state) // to start timer at the first shoot only
				game_timer.start();		}
	}

	public void moveRight() {
		ShooterX += 30;
		if (Move_Ball_With_Paddle_Flag == 0) // ball doesn't move with paddle after launched
			ballposX += 30;
	}

	public void moveLeft() {
		ShooterX -= 30;
		if (Move_Ball_With_Paddle_Flag == 0) // ball doesn't move with paddle after launched
			ballposX -= 30;
	}

	// I don't need them but must be there or ERROR OCCURS
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}



}