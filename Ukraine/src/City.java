import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	int num;
	private String str;

	City() {

	}

	City(String city,int num) throws IOException, UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
		this.city = city;
		this.num=num;
		System.out.println(city);
		history = new History(city);
		places = new Places(city);
		gallery = new Gallery(city);
		paint();
		gui = new GUI(1);

	}

	void paint() throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(city);
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setFont(new Font("Segoe Script", Font.ITALIC, 40));
		add(label);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JButton b1,b2,b3,b4,b5 ; 
		if (num==1 )
		{
		 b1 = new JButton("Добавить в избранное");
		 b2 = new JButton("Удалить из избранного");
		 b3 = new JButton("Посмотреть на карте");
		 b4=new JButton("Возможные туры");
		 b5=new JButton("Видео");
		}
		else
		{b1 = new JButton("Add to list of favorites");
		 b2 = new JButton("Remove from list of favorites");
		 b3 = new JButton("View on the map");
		 b4=new JButton("View tours to this country");
		 b5=new JButton("Video");
			
		}
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name=city+"\\tour.txt";
				File file=new File(name);
				if (file.exists()) {
					
					String line;
					int c=0;
					try {BufferedReader reader = new BufferedReader(new FileReader(file));
						while ((line = reader.readLine()) != null) {
							str=line;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 if (java.awt.Desktop.isDesktopSupported()) {
			            try {
			            	
			                java.awt.Desktop.getDesktop().browse(new URI(str));
			            } catch (URISyntaxException ex) {
			 
			            } catch (IOException ex) {
			                
			            }
			        }

			}
			}
		});
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name=city+"\\tour.txt";
				File file=new File(name);
				if (file.exists()) {
					
					String line;
					int c=0;
					try {BufferedReader reader = new BufferedReader(new FileReader(file));
						while ((line = reader.readLine()) != null) {
							str=line;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 VideoAndMap v=new VideoAndMap(name);

			}
			}
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name=city+"\\map.txt";
				File file=new File(name);
				if (file.exists()) {
					
					String line;
					int c=0;
					try {BufferedReader reader = new BufferedReader(new FileReader(file));
						while ((line = reader.readLine()) != null) {
							str=line;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 if (java.awt.Desktop.isDesktopSupported()) {
			            try {
			                java.awt.Desktop.getDesktop().browse(new URI(str));
			            } catch (URISyntaxException ex) {
			 
			            } catch (IOException ex) {
			                
			            }
			        }

			}
			}
		});
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		Towns help;
		if (num==1)
		help=new Towns(2);
		else
			help=new Towns(4);
		System.out.println(help.findTown(city));
		if (help.findTown(city))
		{
			b1.setEnabled(false);}
		else
			b2.setEnabled(false);
		add(panel);
		JTabbedPane pane = new JTabbedPane();
		
		JScrollPane scroll = new JScrollPane(history);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scroll2 = new JScrollPane(places);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scroll1 = new JScrollPane(gallery);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		if (num==1){
		pane.add(scroll, "О городе");
		pane.add(scroll2, "Достопримечательности");	
		pane.add(scroll1, "Галерея");
		add(pane);
		}
		else
		{
			pane.add(scroll, "About city");
			pane.add(scroll2, "Sights");	
			pane.add(scroll1, "Gallery");
			add(pane);
		}
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gui.getCity1().addLike(city,num);
					if(num==1)
					gui.setCity1(new Towns(2));
					else
						gui.setCity1(new Towns(4));
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
					FileWriter sw;
					if (num==1)
					sw = new FileWriter("likes.txt", false);
					else
						sw = new FileWriter("likesE.txt", false);
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
