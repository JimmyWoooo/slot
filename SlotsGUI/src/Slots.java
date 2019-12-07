import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Slots {

	public static void main(String args []) {
		JFrame frame = new JFrame("Slot Machine");

		frame.add(new SlotsPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 900);
		frame.setResizable(false);	
		frame.setIconImage(new ImageIcon(frame.getClass().getResource("/img/icon.png")).getImage());	
		frame.setVisible(true);
	}
}
