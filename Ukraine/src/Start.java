import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JPanel {
	private String name;
	JLabel label;
	Start(String str) {
		this.name=str;
		label = new JLabel(name);
		label.setFont(new Font("Segoe Script", Font.BOLD ,60));
		add(label);
		repaint();
	}
public void setName(String name)
{
	label.setText(name);
	repaint();
}
	public void paintComponent(Graphics g) {
		Image im = null;
		try {
			im = ImageIO.read(new File("fon.jpg"));
		} catch (IOException e) {
		}
		g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
	}
}
