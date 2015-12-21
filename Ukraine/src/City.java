import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class City extends JPanel {
	private String city;
	private History history;
	private Places places;
	private Gallery gallery;
private ArrayList<String> like;
City()
{
	
}
	City(String city,ArrayList<String> like) throws IOException {
		this.city = city;
		System.out.println(city);
		this.like=like;
		history = new History(city);
		places = new Places(city);
		gallery=new Gallery("Kiev");
		paint();

	}

	void paint() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(city);
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		add(label);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JButton b1 = new JButton("Добавить в избранное");
		JButton b2 = new JButton("Удалить из избранного");
		panel.add(b1);
		panel.add(b2);
		add(panel);
		JTabbedPane pane = new JTabbedPane();
		JScrollPane scroll = new JScrollPane(history);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scroll,"История");
		pane.add(places,"Достопримечательности");
		
		JScrollPane scroll1 = new JScrollPane(gallery);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scroll1,"Галерея");
		add(pane);
		
	}
	public void paintComponent(Graphics g){
        Image im = null;
        try {
            im = ImageIO.read(new File("fon.jpg"));
        } catch (IOException e) {}
        g.drawImage(im, 0, 0,getWidth(),getHeight(), null); 
    }
}

