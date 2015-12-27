import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;

public class ListOfMusic extends DefaultListModel {
	private File file;

	ListOfMusic(int lang) throws IOException {
		if(lang==1)
		file = new File("musics.txt");
		else
			file = new File("musicsE.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] arr;
			while ((line = reader.readLine()) != null) {
				addElement(line);
			}

		}
	}
}
