import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Towns extends DefaultListModel {
	private File file;
	int num;
	public List<String> list = new ArrayList<String>();

	Towns(int num) throws IOException {
		this.num = num;
		if (num == 1)
			file = new File("towns.txt");
		if (num == 2)
			file = new File("likes.txt");
		if (num == 3)
			file = new File("townsE.txt");
		if (num == 4)
			file = new File("likesE.txt");
		openInfo();
	}
	public void openInfo() throws IOException
	{
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
				reader.close();
			}

		}
		
	}

	boolean findTown(String town) {
		if (town == null || town.isEmpty()) {
			return false;
		} else {
			String str = town.substring(0, 1).toUpperCase() + town.substring(1).toLowerCase();
			if (contains(str))
				return true;}
			return false;
		
	}

	public void addLike(String like,int f) throws IOException {
		FileWriter sw;
		if (f == 1)
			sw = new FileWriter("likes.txt", true);
		else
			sw = new FileWriter("likesE.txt", true);
		sw.write(like + "\n");
		sw.close();

	}
}
