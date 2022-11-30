import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyDataFrame {
    // instance variables
    String[] headers;
    public ArrayList<List<Comparable>> babynames;
	HashMap<String, Integer> map;

    //Empty Constructor
    public MyDataFrame(){
        this.babynames = new ArrayList<List<Comparable>>();
		this.map = new HashMap<String, Integer>();
    }

    // Construct MyDataFrame specifying header (1st row)
    public MyDataFrame(String[] header){
        babynames = new ArrayList<List<Comparable>>();
        this.headers = header;
		this.map = new HashMap<String, Integer>();
		this.map.put("state", 0);
		this.map.put("gender", 1);
		this.map.put("year", 2);
		this.map.put("name", 3);
		this.map.put("count", 4);
    }

    //Returns the first n rows, as a MyDataFrame object
    public MyDataFrame head(int n){
        MyDataFrame h = new MyDataFrame(headers);
        if(babynames.size() >= n){
            for(int i=0; i<n;i++){
                h.babynames.add(babynames.get(i));
            }
        }
        return h;
    }

    //Returns the last n rows, as a MyDataFrame object
    public MyDataFrame tail(int n){
        MyDataFrame t = new MyDataFrame(headers);
        if(babynames.size() >= n){
            for(int i=1; i<n+1;i++){
                int total = babynames.size();
                t.babynames.add(babynames.get(total - i));
            }
        }
        return t;
    }

    //Returns the rows starting from index
	public MyDataFrame loc(int index) {
		MyDataFrame loc = new MyDataFrame();
		for(int i = index; i < babynames.size(); i++) {
			loc.babynames.add(babynames.get(i));
		}
		return loc;
	}

	
	//Returns the rows between from and to (including from and to)
	public MyDataFrame loc(int from, int to) {
		MyDataFrame loc = new MyDataFrame();
		for(int i = from; i <= to; i++) {
			loc.babynames.add(babynames.get(i));
		}
		return loc;
	}


	//Returns the type of the column specified by index. not uniform, return ‘String’.
    public String dType(int index) {
		//String type = null;
		// if the dataframe is empty, return None
        if (babynames.size() == 0) {
            return "None | Dataframe is empty";
        }
        //List<Comparable> r = babynames.get(index%5);
		// identify the data type of the first item in that column
        //String type = r[index];
        // if any item in this column has a data type different from the first item's data type
        // return "String"
		
		List<Comparable> firstRow = babynames.get(0);
		String type = firstRow.get(index).getClass().getSimpleName();
		for (int j = 0; j<babynames.size(); j++){
			List<Comparable> rs = babynames.get(j);
			if(!rs.get(index).getClass().getSimpleName().equals(type)){
				return "String";
			}

		}
		return type;
	}

    //Returns the type of the column specified by name. not uniform, return ‘String’.
    public String dType(String name) {
		int index = this.map.get(name);
		return this.dType(index);
	}

	//Returns the column specified by index
	public ArrayList<List<Comparable>> slice(int index) {
		ArrayList<List<Comparable>> newData = new ArrayList<>();
		for(int i = 0; i < this.babynames.size(); i++ ){
			List<Comparable> list=new ArrayList<Comparable>();
			List curRow = this.babynames.get(i);
			list.add((Comparable) curRow.get(index));
			newData.add(list);
			list = null;
			curRow = null;
		}
		return newData;
	}
	  
	//Returns the column specified by index
	public ArrayList<List<Comparable>> slice(String name) {
		return this.slice(this.map.get(name));
	}
	  
	//Returns the column specified by index
	public ArrayList slice(int[] indexArr) {
		ArrayList<List<Comparable>> newData = new ArrayList<>();
		for(int i = 0; i < this.babynames.size(); i++ ){
			List<Comparable> list=new ArrayList<Comparable>();
			for (int index : indexArr) {
				List curRow = this.babynames.get(i);
				list.add((Comparable) curRow.get(index));
			}
			newData.add(list);
		}
		return newData;
	}
	  
	//Returns the column specified by index
	public ArrayList slice(String[] nameArr) {
		int[] indexArr = new int[nameArr.length];
		for (int i = 0; i < nameArr.length; i++) {
			indexArr[i] = this.map.get(nameArr[i]);
		}
		return this.slice(indexArr);
	}

	//Returns the minimum element of the column specified by index
	public Object getMin(int index) {
		String type = dType(index);
		//System.out.println(type);

    	//Get first element of the column
 		String firstVal = this.babynames.get(0).get(index).toString();

        // when the column is with type String
    	if (type.equals("String")) {
    		String min = firstVal;
    		for (int i = 1; i < this.babynames.size(); i++) {
    			if (this.babynames.get(i).get(index).toString().compareTo(min) < 0) {
    				min = this.babynames.get(i).get(index).toString();
    			}
    		}
    		//System.out.println("Min of " + index + " is "+min);
    		return min;

    	// when the column is with type Integer
    	}else {
    		Integer min = Integer.parseInt(this.babynames.get(0).get(index).toString());
    		for (int i = 1; i < this.babynames.size(); i++) {
    			if (Integer.parseInt(this.babynames.get(i).get(index).toString()) < min) {
    				min = Integer.parseInt(this.babynames.get(i).get(index).toString());
    			}
    		}
    		//System.out.println("Min of " + index + " is "+ min);
    		return min;
    	}
	}

	//Returns the minimum element of the column specified by label
	public Object getMin(String label) {
		Integer index = this.map.get(label);
    	return getMin(index);
	}

	//Returns the maximum element of the column specified by index
	public Object getMax(int index) {
		String type = dType(index);

    	//Get first element of the column
 		String firstVal = this.babynames.get(0).get(index).toString();

        // when the column is with type String
    	if (type.equals("String")) {
    		String max = firstVal;
    		for (int i = 1; i < this.babynames.size(); i++) {
    			if (this.babynames.get(i).get(index).toString().compareTo(max) > 0) {
    				max = this.babynames.get(i).get(index).toString();
    			}
    		}
    		//System.out.println("Min of " + index + " is "+min);
    		return max;

    	// when the column is with type Integer
    	}else {
    		Integer max = Integer.parseInt(this.babynames.get(0).get(index).toString());
    		for (int i = 1; i < this.babynames.size(); i++) {
    			if (Integer.parseInt(this.babynames.get(i).get(index).toString()) > max) {
    				max = Integer.parseInt(this.babynames.get(i).get(index).toString());
    			}
    		}
    		//System.out.println("Min of " + index + " is "+ min);
    		return max;
    	}
	}
	
	//Returns the maximum element of the column specified by label
	public Object getMax(String label) {
		Integer index = this.map.get(label);
    	return getMax(index);
	}


    ////////////////Need to implement/////////////////////////////////////////////////////

	//Returns the rows starting from label
	public MyDataFrame loc(String label) {
		MyDataFrame loc = new MyDataFrame();
		int colIndex = this.map.get(label);
		for(int i=0; i<babynames.size(); i++){
			List<Comparable> row = new ArrayList<> ();
			for(int j = colIndex; j < 5; j ++){
				row.add(babynames.get(i).get(j));
			}
			loc.babynames.add(row);
		}
		return loc;
	}


    //Returns the rows between from and to (including from and to)
	public MyDataFrame loc(String from, String to) {
		MyDataFrame loc = new MyDataFrame();
		int colIndexFrom = this.map.get(from);
		int colIndexTo = this.map.get(to);
		for(int i=0; i<babynames.size(); i++){
			List<Comparable> row = new ArrayList<> ();
			for(int j = colIndexFrom; j < colIndexTo; j ++){
				row.add(babynames.get(i).get(j));
			}
			loc.babynames.add(row);
		}
		return loc;
	}


    //Returns data filtered by applying “col op o” on MyDataFrame object, e.g. “count > 10”, “state = ‘IL’”. 
	public ArrayList filter(String col, String op, Object o) {
		ArrayList<List<Comparable>> newData = new ArrayList<>();
		switch (op) {
			case ">":
				//for each column in dataframe
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if ((int) curRow.get(this.map.get(col)) > (int) Integer.parseInt((String) o)) {
						newData.add(curRow);
					}
				}
				break;
			case ">=":
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if ((int) curRow.get(this.map.get(col)) >= (int) Integer.parseInt((String) o)) {
						newData.add(curRow);
					}
				}
				break;
			case "<":
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if ((int) curRow.get(this.map.get(col)) < (int) Integer.parseInt((String) o)) {
						newData.add(curRow);
					}
				}
				break;
			case "<=":
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if ((int) curRow.get(this.map.get(col)) <= (int) Integer.parseInt((String) o)) {
						newData.add(curRow);
					}
				}
				break;
			case "=":
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if (String.valueOf(curRow.get(this.map.get(col))).equals(o)) {
						newData.add(curRow);
					}
				}
				break;
			case "!=":
				for (int i = 0; i < this.babynames.size(); i++) {
					List<Comparable> curRow = this.babynames.get(i);
					if (!String.valueOf(curRow.get(this.map.get(col))).equals(o)) {
						newData.add(curRow);
					}
				}
				break;
		}

		return newData;

	}


	
	//Returns the data sorted by the column specified by index
	public MyDataFrame sort(int index) {
		int[] indexes = new int[babynames.size()];
		MyDataFrame sorted = new MyDataFrame();
		return sorted;
	}
	
	//Returns the data sorted by the column specified by name
	public MyDataFrame sort(String name) {
		
		int[] indexes = new int[babynames.size()];
		MyDataFrame sorted = new MyDataFrame();
		return sorted;
	}
}
