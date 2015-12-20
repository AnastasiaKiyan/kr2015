import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Towns extends DefaultListModel {
	private File file;
	private List<String> list = new ArrayList<String>();

	Towns() throws IOException {
		file = new File("towns.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] arr;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			list.sort(null);
			for (String str : list) {
				addElement(str);

			}
		}
	}
	boolean findTown(String town)
	{
		if (contains(town))
			return true;
		return false;
	}
}
