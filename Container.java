public class Container{
	
	private boolean[][][] cMatrix;
	
	public Container(int h, int w, int l){
	cMatrix = new boolean[h][w][l];
	
	}

	public boolean relevant(Parcel p){
	for(int i = 0; i<cMatrix.length; i++){
		for(int j = 0; j<cMatrix[0].length; j++){
			for(int k = 0; k<cMatrix[0][0].length; k++){
				if( cMatrix[i][j][k] == false){
					if(fit(i,j,k, p )){
						fill(i,j,k,p);
						return true;
					}}}}}
		return false;
	}
	
	public boolean fit(int i, int j, int k, Parcel p){
		int[][] coords = p.getCoords();
		for(int m = 0; m<coords.length; m++){
			if ( cMatrix[ i+coords[m][0]][j+coords[m][1]][k+coords[m][2]] == true)
				return false;
		
	}
	return true;
	}

	public void fill(int i, int j, int k, Parcel p){
			int[][] coords = p.getCoords();
			for(int m = 0; m<coords.length; m++){
				cMatrix[ i+coords[m][0]][j+coords[m][1]][k+coords[m][2]] = true;
			}
	}
	
	}