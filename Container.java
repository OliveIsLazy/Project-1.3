public class Container{

	private boolean[][][] cMatrix;
	private int[] freePoint = new int[3];

	public Container()
	{
			cMatrix = new boolean[8][5][33];
			// freePoint = {0,0,0};
	}

	public Container(int h, int w, int l)
	{
			cMatrix = new boolean[h][w][l];
			// freePoint = {0,0,0};
	}

	public int[] getFreePoint()
	{ return freePoint; }

	public Object clone()
	{
		Container cloned = new Container();
		cloned.cMatrix = cMatrix;
		cloned.freePoint = freePoint;
		return cloned;
	}

	public boolean relevant(Parcel p){
	for(int i = 0; i<cMatrix.length; i++){
		for(int j = 0; j<cMatrix[0].length; j++){
			for(int k = 0; k<cMatrix[0][0].length; k++){
				if( cMatrix[i][j][k] == false){
					if(fit(i,j,k, p)){
						// fill(i,j,k,p);
						freePoint[0] = i;
						freePoint[1] = j;
						freePoint[2] = k;
						return true;
					}}}}}
		return false;
	}

	public boolean fit(int i, int j, int k, Parcel p){
		int[][] coords = p.getCoords();
		for(int m = 0; m<coords.length; m++){
			if ( i+coords[m][0]<cMatrix.length && j+coords[m][1]<cMatrix[0].length && k+coords[m][2]<cMatrix[0][0].length )
					if ( cMatrix[ i+coords[m][0]][j+coords[m][1]][k+coords[m][2]] == true)
							return false;
	}
	return true;
	}

	public void fill(int i, int j, int k, Parcel p){
			int[][] coords = p.getCoords();
			for(int m = 0; m<coords.length; m++){
					if ( i+coords[m][0]<cMatrix.length && j+coords[m][1]<cMatrix[0].length && k+coords[m][2]<cMatrix[0][0].length )
							cMatrix[ i+coords[m][0]][j+coords[m][1]][k+coords[m][2]] = true;
			}
	}

	}
