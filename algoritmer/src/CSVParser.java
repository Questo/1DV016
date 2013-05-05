import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Test CSV parser, might be useful
 * 
 * @author mathias
 *
 */

public class CSVParser {

	public static void main(String[] args) throws Exception {
		
		BufferedReader CSVFile =
				new BufferedReader(new FileReader("/home/mathias/1.csv"));
		
		String dataRow = CSVFile.readLine(); // read fist line.
		
		while(dataRow != null) {
			String[] dataArray = dataRow.split(",");
			for(String item:dataArray)
				System.out.print(item + "\t");
			System.out.println();
			dataRow = CSVFile.readLine();
		}
		CSVFile.close();
		System.out.println();
	}

}
