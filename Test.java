import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test {


    public static void main(String[] args) throws IOException {
		
		//all tests will be printed in this file
		PrintWriter writer = new PrintWriter("methodTests.txt", "UTF-8");
		
		//test getFiles
		String directoryPath = "/Users/liucheryl/Desktop/javaPython/proj/namesbystate";
		ArrayList<String> myFiles = getFiles(directoryPath);
		//System.out.println(Arrays.toString(myFiles.toArray()));
		
		//test loadBabyNames (this tests readCSV and concat)
		MyDataFrame baby = loadBabyNames(myFiles, directoryPath);
		System.out.println("Dataframe size after loading all states:");
		writer.println("Dataframe size after loading all states:");
		System.out.println(baby.babynames.size());
		writer.println(baby.babynames.size());
		
		//test head
		//MyDataFrame babyhead = baby.head(5);
		//System.out.println("\nTesting Head(5):");
		//writer.println("\nTesting Head(5)");
		//System.out.println(babyhead.babynames);
		//writer.println(babyhead.babynames);

		//test tail
		//MyDataFrame babytail = baby.tail(5);
		//System.out.println("\nTesting Tail(5):");
		//writer.println("\nTesting Tail(5):");
		//System.out.println(babytail.babynames);
		//writer.println(babytail.babynames);

		//test dType on index
		//String typeIndex = baby.dType(2);
		//System.out.println("\nThe type of index 2 (year) is:");
		//writer.println("\nThe type of index 2 (year) is:");
		//System.out.println(typeIndex);
		//writer.println(typeIndex);

		//test dType on name
		//String typeName = baby.dType("name");
		//System.out.println("\nThe type of name column is:");
		//writer.println("\nThe type of name column is:");
		//System.out.println(typeName);
		//writer.println(typeName);

		//test loc on index (1000000)
		//MyDataFrame locdf = baby.loc(1000000);
		//System.out.println("\nSize of dataframe after loc(1,000,000):");
		//writer.println("\nSize of dataframe after loc(1,000,000):");
		//System.out.println(locdf.babynames.size());
		//writer.println(locdf.babynames.size());
		
		//test loc from index 5 to index 10
		//MyDataFrame locrange = baby.loc(5, 10);
		//System.out.println("\nDataframe after loc(5, 10):");
		//writer.println("\nDataframe after loc(5, 10):");
		//System.out.println(locrange.babynames);
		//writer.println(locrange.babynames);

		//test loc on "name"
		//MyDataFrame locdfName = baby.loc("name");
		//System.out.println("\nSize of dataframe after loc(name):");
		//writer.println("\nSize of dataframe after loc(name):");
		//System.out.println(locdfName.babynames.size());
		//writer.println(locdfName.babynames.size());

		//test loc from "name" to "count"
		//MyDataFrame locrangeName = baby.loc("name","count");
		//System.out.println("\nDataframe after loc(name, count):");
		//writer.println("\nDataframe after loc(name, count):");
		//System.out.println(locrangeName.babynames.size());
		//writer.println(locrangeName.babynames.size());

		//test getMin on index 2 (year)
		//Object minYear = baby.getMin(2);
		//System.out.println("\nThe min year is:");
		//writer.println("\nThe min year is:");
		//System.out.println(minYear);
		//writer.println(minYear);

		//test getMax on index 3 (Name)
		//Object maxName = baby.getMax(3);
		//System.out.println("\nThe max Name is:");
		//writer.println("\nThe max Name is:");
		//System.out.println(maxName);
		//writer.println(maxName);

		//test getMin on "count"
		//Object minCount = baby.getMin("count");
		//System.out.println("\nThe min count is:");
		//writer.println("\nThe min count is:");
		//System.out.println(minCount);
		//writer.println(minCount);

		//test getMax on "count"
		//Object maxCount = baby.getMax("count");
		//System.out.println("\nThe max count is:");
		//writer.println("\nThe max count is:");
		//System.out.println(maxCount);
		//writer.println(maxCount);

		// test slice with index
		//ArrayList slicing_index = baby.slice(2);
		//System.out.println("\nDataframe after slicing on index:");
		//writer.println("\nDataframe after slicing on index");
		//System.out.println(slicing_index);
		//writer.println(slicing_index);
	   
		// test slice with name
		//ArrayList slicing_index_String = baby.slice("state");
		//System.out.println("\nDataframe after slicing on index:");
		//writer.println("\nDataframe after slicing on index");
		//System.out.println(slicing_index_String);
		//writer.println(slicing_index_String);
	   
		// test slice with index
		//int[] test = {1,2,3};
		//ArrayList slicing_index_list = baby.slice(test);
		//System.out.println("\nDataframe after slicing on index:");
		//writer.println("\nDataframe after slicing on index");
		//System.out.println(slicing_index_list);
		//writer.println(slicing_index_list);
	   
	   
		// test slice with names
		//String[] test_string = {"state", "gender"};
		//ArrayList slicing_index_list_string = baby.slice(test_string);
		//System.out.println("\nDataframe after slicing on index:");
		//writer.println("\nDataframe after slicing on index");
		//System.out.println(slicing_index_list_string);
		//writer.println(slicing_index_list_string);


		//test write csv (write sortedName to csv)
		//writer.println("\nWrite above dataframe to csv. (See 'BabyNames.csv')");
		//try {
		//	MyPandas.writeCSV(baby, "BabyNames.csv");
		//}  
		//catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		writer.close();
	}

    //get all txt files from a directory
	public static ArrayList<String> getFiles(String directoryPath) {
		ArrayList<String> textFiles = new ArrayList<String>();
		File dir = new File(directoryPath);
		for(File file: dir.listFiles()) {
			if(file.getName().toLowerCase().endsWith(".txt")) {
				textFiles.add(file.getName());
			}
		}
		return textFiles;
	}

    //create dataframe from all babynames files
	public static MyDataFrame loadBabyNames(ArrayList<String> files, String path) throws IOException {

		//create dataframe from first and second file in list and concat them
		MyDataFrame first = MyPandas.readCSV(path +"/"+ files.get(0));
		MyDataFrame sec = MyPandas.readCSV(path +"/"+ files.get(1));
		MyDataFrame df = MyPandas.concat(first, sec);
		//loop through the rest of the files
		for(int i = 2; i < files.size(); i++) {
			//concat all files into one
			df = MyPandas.concat(df, MyPandas.readCSV(path +"/"+ files.get(i)));
		}
		return df;
	}
}
