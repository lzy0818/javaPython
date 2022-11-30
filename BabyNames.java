public class BabyNames {
    //columns in BabyName dataset
	public String state;
	public String gender;
	public int year;
	public String name;
	public int count;
		 
	//constructor
	public BabyNames (String state, String gender, int year, String name, int count) { 
		this.state = state;
		this.gender = gender;
		this.year = year;
		this.name = name;
		this.count = count;
	}
	
	public static String getState(BabyNames row) {
		return row.state;
	}
	public static String getGender(BabyNames row) {
		return row.gender;
	}
	public static int getYear(BabyNames row) {
		return row.year;
	}
	public static String getName(BabyNames row) {
		return row.name;
	}

	public static int getCount(BabyNames row) {
		return row.count;
	}

	public String getType(int index) {
		if(index % 5 == 0 |index % 5 == 1|index % 5 == 3){
			return "String";
		}else {
			return "Int";
		}
	}
	
	
	//define how to print row
	@Override
	public String toString() {
		String print;
		print = this.state + "\t" + this.gender + "\t" + this.year + "\t" + this.name + "\t" + this.count + "\n";	 
		return print;
	}
}
