public class Parcel{

	private double volume;
	private double density;
	private String name = "";

	private int currentValue;
	private int[][] currentCoords;

	private int valA = 3;
	private int valB = 4;
	private int valC = 5;

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


	public Parcel(String type){
	if(type.equals("A")){
		currentValue = valA;
		currentCoords = A;
	}
	else if(type.equals("B")){
		currentValue = valB;
		currentCoords = B;
	}
	else if(type.equals("C")){
		currentValue = valC;
		currentCoords = C;
	}
	name = type;
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

}
