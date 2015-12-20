import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Sights extends JPanel{
	String first;
	String about;
Sights(String city) throws IOException
{
	
for (int i=1;i<=10;i++)
{
	String name=city+"\\"+i+".txt";
	File file=new File(name);
	BufferedReader reader = new BufferedReader(new FileReader(file));
	String line;
	int c=0;
	while ((line = reader.readLine()) != null) {
		if(c!=0)
		{
			about+=line;
			
		}
		else
		{
			c++;
			first=line;
		}
	}
	
}
}
}
