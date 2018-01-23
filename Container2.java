public class Container2{

	private boolean[][][] cMatrix;
	private int volume;
	private static int counter = 0;
	private Parcel[] list = new Parcel[200];
	private int[][] coordinates = new int[200][3];
	private int parcelcounter = 0;

	public Container2()
	{
			cMatrix = new boolean[8][5][33];
			volume = 0;
	}
	
	public Container2(int h, int w, int l)
	{
			cMatrix = new boolean[h][w][l];
			volume = 0;
	}
	
	public boolean relevant(Parcel p){
	for(int i = 0; i<cMatrix.length; i++){
		for(int j = 0; j<cMatrix[0].length; j++){
			for(int k = 0; k<cMatrix[0][0].length; k++){
				if( cMatrix[i][j][k] == false){
					if(fit(i,j,k, p)){
						fill(i,j,k,p);
						coordinates[parcelcounter][0] = i;
						coordinates[parcelcounter][1] = j;
						coordinates[parcelcounter][2] = k;
						list[parcelcounter] = p;
						parcelcounter++;
						return true;
					}}}}}
		return false;
	}
	
	public boolean fit(int i, int j, int k, Parcel p)
	{
		int[][] coords = p.getCoords();
		for(int m = 0; m < coords.length; m++)
			if (i + coords[m][0] < cMatrix.length && j + coords[m][1] < cMatrix[0].length && k + coords[m][2] < cMatrix[0][0].length) // Is it in bounds?
			{
				if (cMatrix[i + coords[m][0]][j + coords[m][1]][k + coords[m][2]] == true)	return false;
			}
		 	else return false;
		return true;
	}
	
	public void fill(int i, int j, int k, Parcel p){
			int[][] coords = p.getCoords();
			for(int m = 0; m<coords.length; m++){
					if ( i+coords[m][0]<cMatrix.length && j+coords[m][1]<cMatrix[0].length && k+coords[m][2]<cMatrix[0][0].length )
							cMatrix[ i+coords[m][0]][j+coords[m][1]][k+coords[m][2] ] = true;
			}
			updateVolume();
	}
	
	public void updateVolume()
	{
		int sum = 0;
		for(int i = 0; i<cMatrix.length; i++)
			for(int j = 0; j<cMatrix[0].length; j++)
				for(int k = 0; k<cMatrix[0][0].length; k++)
					if (cMatrix[i][j][k])
							sum++;
		volume = sum;
	}
	
	// setters and getters
	public boolean[][][] getMatrix()
	{ return cMatrix;}

	public int getVolume()
	{ return volume; }
	
	public int getCoordinates(int i, int j){
		return coordinates[i][j];
	}
	
	public Parcel getList(int i){
		return list[i];
	}
	
	public void printList(){
	for(int i = 0; i<200; i++){
		if(list[i] != null){
	System.out.println(coordinates[i][0] +" " + coordinates[i][1] + " " + coordinates[i][2] + " " + list[i].getName());
	}}}
	
	






}