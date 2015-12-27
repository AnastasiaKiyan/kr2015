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
	private JTextArea[] area = new JTextArea[5];
	private JButton[][] button = new JButton[5][5];

	private int i;

	Places(String city) throws IOException {
		setLayout(new GridLayout(5, 3, 3, 3));
		this.city = city;
		openPlaces();
	}

	void openPlaces() throws IOException {
		int j = 0;
		for (i = 1; i <= 5; i++) {
			area[i - 1] = new JTextArea();
			area[i - 1].setLineWrap(true);
			area[i - 1].setOpaque(false);
			String name = city + "\\places\\" + i + ".txt";
			file = new File(name);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					area[i - 1].append(line + "\n");
				}
				reader.close();
				JScrollPane scroll = new JScrollPane(area[i - 1]);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				add(scroll);
				String image = city + "\\places\\" + i + ".jpg";
				MyImage m = new MyImage(image);
				add(m);
				MyImage p = new MyImage("fon.jpg");
				button[i - 1][j] = new JButton("Сохранить информацию в файл");
				button[i - 1][j].setPreferredSize(new Dimension(300, 150));
				button[i - 1][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						JFileChooser fc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "*.*");
						fc.setFileFilter(filter);
						if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
							FileWriter fw;
							try {
								for (int k = 0; k < 5; k++) {
									for (int h = 0; h < 5; h++) {
										if (e1.getSource() == button[k][h]) {
											int num = k + 1;
											String name = city + "\\places\\" + num + ".txt";
											fw = new FileWriter(fc.getSelectedFile());
											file = new File(name);
											
											if (file.exists()) {
												BufferedReader reader = new BufferedReader(new FileReader(file));
												String line;
												while ((line = reader.readLine()) != null) {
													fw.write(line + "\n");
												}
											}
											fw.close();
										}
									}
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
						}

					}
				});
				p.add(button[i - 1][j]);
				add(p);
				j++;
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
	Image im;

	MyImage(String image) throws IOException {
		this.image = image;
		im = null;
		im = ImageIO.read(new File(image));
	}

	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);

	}
}


