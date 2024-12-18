import java.awt.*;
import javax.swing.*;
public class GUI {
	GameData gameData;
	JFrame f;
	JLabel[][] b;
	JButton upButton, leftButton, downButton, rightButton;

	GUI(GameData gameData) {
		this.gameData = gameData;
		f = new JFrame("Magic Tower");
		b = new JLabel[gameData.H][gameData.W];
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				b[i][j]=new JLabel();
				b[i][j].setBounds(j*100, i*100, 100, 100);
				f.add(b[i][j]);
			}
		}

		upButton = new JButton("↑ Up");
        leftButton = new JButton("← Left");
        downButton = new JButton("↓ Down");
        rightButton = new JButton("→ Right");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(upButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(downButton);
        buttonPanel.add(rightButton);

		f.setLayout(new BorderLayout());

		f.add(buttonPanel, BorderLayout.SOUTH);

		JPanel mapPanel = new JPanel(null);
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				b[i][j] = new JLabel();
				b[i][j].setBounds(j * 100, i * 100, 100, 100);
				mapPanel.add(b[i][j]);
			}
		}

		f.add(mapPanel, BorderLayout.CENTER);

		f.setSize(gameData.W * 100 + 10, gameData.H * 100 + 40);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		refreshGUI();

	}
	public void refreshGUI()
	{
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				Image scaledImage = chooseImage(gameData.map[gameData.currentLevel][i][j]);
				b[i][j].setIcon(new ImageIcon(scaledImage));
			}
		}
	}
	private static Image chooseImage(int index){
		ImageIcon[] icons = new ImageIcon[10];
		Image scaledImage;
		icons[0]= new ImageIcon("Wall.jpg");
		icons[1]= new ImageIcon("Floor.jpg");
		icons[2]= new ImageIcon("Key.jpg");
		icons[3]= new ImageIcon("Door.jpg");
		icons[4]= new ImageIcon("Stair.jpg");
		icons[5]= new ImageIcon("Exit.jpg");
		icons[6]= new ImageIcon("Hero.jpg");
		icons[7]= new ImageIcon("Potion.jpg");
		icons[8]= new ImageIcon("Monster.jpg");
		if(index>10)
			scaledImage = icons[7].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else if(index<0)
			scaledImage = icons[8].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else
			scaledImage = icons[index].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		return scaledImage;
	}
}
