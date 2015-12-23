import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Gallery extends JPanel
{String city;
Gallery(String city)
{this.city=city;
	setLayout(new GridLayout(3,3,2,2));
	openImage();
}
void openImage()
{for (int i=1;i<10;i++)

{ String image=city+"\\"+i+".jpg";
	ImageIcon ii = new ImageIcon(image);
JLabel imageLabel=new JLabel();
int w=getWidth()/3;
int h=getHeight()/3;
	imageLabel.setIcon(new ImageIcon(ii.getImage().getScaledInstance(
		300, 150, ii.getImage().SCALE_DEFAULT)));
	add(imageLabel);
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

