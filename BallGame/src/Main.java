import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		System.out.println("Scores : ");
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();
		obj.setBounds(450, 100, 701, 600);
		obj.setTitle("Ball Game");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);

	}

}
