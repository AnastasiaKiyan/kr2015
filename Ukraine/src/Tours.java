import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Tours extends JPanel {
	private String city;

	Tours() {
		this.city = city;
		setLayout(new GridLayout(5,3,2,2));
	}

	void openTours() throws IOException {
		for (int i = 1; i <= 5; i++) {
			JTextArea area = new JTextArea();
			area.setLineWrap(true);
			area.setOpaque(false);
			String name = city + "\\tours" + i + ".txt";
			File file = new File(name);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				String[] arr;
				while ((line = reader.readLine()) != null) {
					area.append(line + "\n");
				}
				JScrollPane scroll = new JScrollPane(area);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				add(scroll);
				String image = city + "\\tours\\" + i + ".jpg";
				JPanel p = new JPanel();
				p.setLayout(new BorderLayout());
				p.add(new MyImage(image), BorderLayout.CENTER);
				add(p);
				JButton b=new JButton();
				p.add(b, BorderLayout.EAST);
			}
		}

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

class MyImage extends JPanel {
	private String image;
	ImageIcon ii;

	MyImage(String image) {
		this.image = image;

		ii = new ImageIcon(image);
	}

	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), this);

	}

}
