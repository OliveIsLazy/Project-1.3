public class Parcel{

	private int volume;
	private double density;
	private String name;
	private int number;

	private int currentValue;
	private int[][] currentCoords;

	private int valA = 3;
	private int valB = 4;
	private int valC = 5;

	private int volA = 2*2*4;
	private int volB = 2*3*4;
	private int volC = 3*3*3;
	private int volPent = 5;

	private final int[][] A = {{0,0,0},{1,0,0},{0,1,0},{1,1,0},
				{0,0,1},{1,0,1},{0,1,1},{1,1,1},
				{0,0,2},{1,0,2},{0,1,2},{1,1,2},
				{0,0,3},{1,0,3},{0,1,3},{1,1,3}};

	private final int[][] B = {{0,0,0},{1,0,0},{0,1,0},{1,1,0},{0,2,0},{1,2,0},
		 			{0,0,1},{1,0,1},{0,1,1},{1,1,1},{0,2,1},{1,2,1},
		 			{0,0,2},{1,0,2},{0,1,2},{1,1,2},{0,2,2},{1,2,2},
					{0,0,3},{1,0,3},{0,1,3},{1,1,3},{0,2,3},{1,2,3}};

	private final int[][] C = {{0,0,0},{1,0,0},{2,0,0},{0,1,0},{1,1,0},{2,1,0},{0,2,0},{1,2,0},{2,2,0},
				  {0,0,1},{1,0,1},{2,0,1},{0,1,1},{1,1,1},{2,1,1},{0,2,1},{1,2,1},{2,2,1},
				  {0,0,2},{1,0,2},{2,0,2},{0,1,2},{1,1,2},{2,1,2},{0,2,2},{1,2,2},{2,2,2}};
				  
	private final int[][] P =  {{0,0,0}, {1,0,0}, {2,0,0}, {0,1,0}, {1,1,0}};
	
	private final int[][] L = {{0,0,0}, {1,0,0}, {2,0,0}, {3,0,0}, {0,1,0}};
	
	private final int[][] T = {{0,0,0}, {1,0,0}, {2,0,0}, {1,1,0}, {1,2,0}};


	public Parcel(String type){
	name = type;
	if(type.equals("A")){
		currentValue = valA;
		currentCoords = A;
		//number = 0;
		volume = volA;
	}
	else if(type.equals("B")){
		currentValue = valB;
		currentCoords = B;
		//number = 1;
		volume = volB;
	}
	else if(type.equals("C")){
		currentValue = valC;
		currentCoords = C;
		//number = 2;
		volume = volC;
	}
	else if(type.equals("L")){
		currentValue = valA;
		currentCoords = L;
		//number = 1;
		volume = volPent;
	}
	else if(type.equals("P")){
		currentValue = valB;
		currentCoords = P;
		//number = 2;
		volume = volPent;
	}
	else if(type.equals("T")){
		currentValue = valC;
		currentCoords = T;
		//number = 2;
		volume = volPent;
	}

	}

	public int[][] getCoords(){
	return currentCoords;
	}

	public int getValue() {
        return currentValue;
    }

  public String getName() {
        return name;
  }

	public int getNumber() {
        return number;
  }

	public int getVolume() {
        return volume;
  }

	public double getRate() {
		double rate = currentValue;
		double here = volume;
		rate = rate / here;
        return rate;
  }

}