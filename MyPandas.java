import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyPandas {
    //Read a file and store it into a MyDataFrame object. 
    //Possible data types: Integer and String. You can assume that the first row of the .csv file is a header. 
    public static MyDataFrame readCSV(String path) throws IOException {
		
		String[] headers = {"State", "Gender", "Year", "Name", "Count"};
		
		//create class of myDataFrame
		MyDataFrame df= new MyDataFrame(headers);
		
		//create file to write located in path
		File file = new File(path);
		
		//read file
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				// Parse line into separate columns
				String[] data = line.split(",");
				String state = data[0];
				String gender = data[1];
				int year = Integer.parseInt(data[2]);
				String name = data[3];
				int count = Integer.parseInt(data[4]);
				
				// From there, create object MyData
				List<Comparable> row = new ArrayList<>();;
				row.add(state);
				row.add(gender);
				row.add(year);
				row.add(name);
				row.add(count);
				
				// Insert MyData into babynames
				df.babynames.add(row);
			}
		}
		
		return df;
	}
	
    //Write a MyDataFrame object to file specified by path
	public static void writeCSV(MyDataFrame data, String path) throws IOException {
		
		// create file to write located in path
		FileWriter writer = new FileWriter(path);
		
		// write headers
		if(data.headers != null) {
			for (int i = 0; i < data.headers.length; i++) {
				writer.write(data.headers[i] + ",");
			}
		}

		writer.write("\n");

		// write each row of data;
		for (int i = 0; i < data.babynames.size(); i++) {
            String s = (String) data.babynames.get(i).get(0);
            String g = (String) data.babynames.get(i).get(1);
            int y = (int) data.babynames.get(i).get(2);
            String n = (String) data.babynames.get(i).get(3);
            int c = (int) data.babynames.get(i).get(4);

			writer.write(s +","+ g +","+ y +","+ n +","+ c +"\n");
		}
		writer.close();
	}
	
    //Concatenate two MyDataFrame object along rows; Returns the concatenated MyDataFrame
	public static MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) {
		//contruct concatenated MyDataFrame
		String[] headers = {"State", "Gender", "Year", "Name", "Count"};
		MyDataFrame conData = new MyDataFrame (headers);
		
		//Append each row in df1 to conData
        int dim1 = df1.babynames.size();
		for (int i = 0; i < dim1; i++) {
			conData.babynames.add(df1.babynames.get(i));
		}
		
		//Append each row in df2 to conData
        int dim2 = df2.babynames.size();
		for (int i = 0; i < dim2; i++) {
			conData.babynames.add(df2.babynames.get(i));
		}
		return conData;
	}
    
}