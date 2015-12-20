import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class Places extends JTextArea {
	private String city;
	private File file;
		Places(String city) throws IOException
	{
		this.city=city;
		openPlaces();
	}
		void openPlaces() throws IOException
		{
			switch(city)
			{
				case "Киев":
					file = new File("Kiev\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
				case "Харьков":
					file = new File("Kharkov\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
				case "Одесса":
					file = new File("Odessa\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
				case "Львов":
					file = new File("Lvov\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
				case "Днепропетровск":
					file = new File("Dnepr\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
				case "Чернигов":
					file = new File("Chernigov\\places.txt");
					if (file.exists()) {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						String[] arr;
						while ((line = reader.readLine()) != null) {
							append(line);
						}
			}
					break;
					
		}
	}
	}
