import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class History extends JTextArea {
private String city;
private File file;
	History(String city) throws IOException
{
	this.city=city;
	openHistory();
	setLineWrap(true);
	setWrapStyleWord(true);
	setFont(new Font("Times New Roman", Font.PLAIN, 18));

}
	void openHistory() throws IOException
	{ String image=city+"\\"+"history.jpg";
		
				if (file.exists()) {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					String[] arr;
					while ((line = reader.readLine()) != null) {
						append(line+"\n");
					}
		}
				
}
}
