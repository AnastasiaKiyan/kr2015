import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;

public class City extends JPanel {
	private String city;
	private History history;
	private Places places;
	private Gallery gallery;
	private GUI gui;

	City() {

	}

	City(String city) throws IOException, UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
		this.city = city;
		System.out.println(city);
		history = new History(city);
		places = new Places(city);
		gallery = new Gallery(city);
		paint();
		gui = new GUI();

	}

	void paint() throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(city);
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setFont(new Font("Segoe Script", Font.ITALIC, 30));
		add(label);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JButton b1 = new JButton("Добавить в избранное");
		JButton b2 = new JButton("Удалить из избранного");
		JButton b3 = new JButton("Посмотреть на карте");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 if (java.awt.Desktop.isDesktopSupported()) {
			            try {
			                java.awt.Desktop.getDesktop().browse(new URI("https://www.google.com.ua/maps/place/%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD,+%D0%92%D0%B5%D0%BB%D0%B8%D0%BA%D0%BE%D0%B1%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F/@51.528308,-0.3817765,10z/data=!3m1!4b1!4m2!3m1!1s0x47d8a00baf21de75:0x52963a5addd52a99"));
			            } catch (URISyntaxException ex) {
			 
			            } catch (IOException ex) {
			                System.out.println("Go to http://www.site.com/");
			            }
			        }

			}

		});
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		Towns help=new Towns(2);
		if (help.findTown(city))
			b1.setEnabled(false);
		else
			b2.setEnabled(false);
		add(panel);
		JTabbedPane pane = new JTabbedPane();
		JScrollPane scroll = new JScrollPane(history);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scroll, "О городе");
		JScrollPane scroll2 = new JScrollPane(places);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scroll2, "Достопримечательности");

		JScrollPane scroll1 = new JScrollPane(gallery);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scroll1, "Галерея");
		add(pane);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gui.getCity1().addLike(city);
					gui.setCity1(new Towns(2));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("problems");
				}
				b2.setEnabled(true);
				b1.setEnabled(false);

			}

		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gui.getCity1().list.remove(city);
					FileWriter sw = new FileWriter("likes.txt", false);
					for (String str : gui.city1.list) {
						sw.write(str + "\n");
					}
					sw.close();
					gui.setCity1(new Towns(2)); 
					b1.setEnabled(true);
					b2.setEnabled(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
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
