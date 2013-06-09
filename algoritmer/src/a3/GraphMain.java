package a3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;

public class GraphMain implements A3Main {
	
	MyGraph g;

	@Override
	public MyGraph readCSVFile(String filename) {
		BufferedReader CSVFile = null;
		try {
		CSVFile = new BufferedReader(new FileReader(filename));
		} catch(FileNotFoundException e1) {
			System.err.println(e1.getMessage());
		}
		
		g = new MyGraph();
		
		try {
			String dataRow = CSVFile.readLine();
			while(dataRow != null && dataRow.length() != 0) {
				String[] dataArray = dataRow.split(",");
				
				int src = Integer.valueOf(dataArray[0]);
				g.addNode(src);
				for(int i = 1; i < dataArray.length; i++)
					g.addEdge(src, Integer.valueOf(dataArray[i]));
				
				dataRow = CSVFile.readLine();
			}
			CSVFile.close();
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		
		return g;
	}
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Usage: GraphMain [FILENAME]");
			System.exit(1);
		}
		
		GraphMain graphMain = new GraphMain();
		MyGraph graph = graphMain.readCSVFile(
				args[0]);
	}

}
