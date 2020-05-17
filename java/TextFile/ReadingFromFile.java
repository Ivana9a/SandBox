package TextFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadingFromFile {

			public static Map<String, String> readXPaths() {
				Map<String, String> pathsFile = new HashMap<>();
				File myObj = new File("src\\main\\java\\TextFile\\paths.txt");
				Scanner myReader;
				
				try {
					
					myReader = new Scanner(myObj);
					while (myReader.hasNextLine()) {
						String data = myReader.nextLine();
						//System.out.println("Data value: "+ data);
						String values[] = data.split("~");
						//System.out.println(values[0] + " " + values[1]);
						pathsFile.put(values[0], values[1]);
					}
					myReader.close();
					
				} catch (FileNotFoundException e) {
					System.out.println("An error has occured.");
					e.printStackTrace();
				}

				return pathsFile;

			}

}
