import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Places extends JPanel {
	private String city;
	private File file;
	private JTextArea[] area = new JTextArea[6];
	private JButton[] button = new JButton[6];
	private int i;
	String []mas=new String[6];

	Places(String city) throws IOException {
		setLayout(new GridLayout(5, 3, 3, 3));
		this.city = city;
		openPlaces();
	}

	void openPlaces() throws IOException {
		for (i = 1; i <=5; i++) {
			area[i] = new JTextArea();
			area[i].setLineWrap(true);
			area[i].setOpaque(false);
			String name = city + "\\places\\" + i + ".txt";
			file = new File(name);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				int c=0;
				while ((line = reader.readLine()) != null) {
					area[i].append(line + "\n");
					mas[i]=line;
					c++;
				}
				JScrollPane scroll = new JScrollPane(area[i]);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				add(scroll);
				String image = city + "\\places\\" + i + ".jpg";
				MyImage m=new MyImage(image);
				add(m);
				forButton p=new forButton();
				//p.setLayout(new BorderLayout());
				button[i] = new JButton("Сохранить информацию в файл");
				button[i].setPreferredSize(new Dimension(300, 150));
				//button[i].setMaximumSize(new Dimension(100, 50));
				button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser fc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "*.*");
						fc.setFileFilter(filter);
						if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
							FileWriter fw;
							try {
								fw = new FileWriter(fc.getSelectedFile());
								fw.write(mas[i]);
						
								fw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

				});
				p.add(button[i]);
				add(p);

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
	Image im ;
	MyImage(String image) throws IOException {
		this.image = image;
		 im = null;
		 im = ImageIO.read(new File(image));
	}

	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0,   getWidth() ,getHeight(), this);

	}
}
class forButton extends JPanel
{
	public void paintComponent(Graphics g) {
		Image im = null;
		try {
			im = ImageIO.read(new File("fon.jpg"));
		} catch (IOException e) {
		}
		g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
	}
}
