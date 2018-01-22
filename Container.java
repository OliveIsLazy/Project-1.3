import java.util.ArrayList;

public class Container extends Parcel3D
{

	private boolean[][][] cMatrix;
	private int[] freePoint = new int[3];
	private int containerNumber;
	private int volume;
	private ArrayList<Parcel3D> filledParcels = new ArrayList<Parcel3D>();
	private static int counter = 0;
	private static ArrayList<Container> v = new ArrayList<Container>();

	// Constructors
	public Container()
	{
			super(3);
			cMatrix = new boolean[66][10][16];
			volume = 0;
			containerNumber = 0;
			this.setPlace(cMatrix.length, cMatrix[0].length, cMatrix[0][0].length);
	}

	public Container(int l, int w, int h)
	{
			super(3);
			cMatrix = new boolean[l][w][h];
			volume = 0;
			containerNumber = 0;
			this.setPlace(cMatrix.length, cMatrix[0].length, cMatrix[0][0].length);
	}

	// instance methods

	// creating a clone of this
	public Object clone()
	{
		Container cloned = new Container();
		cloned.cMatrix = cMatrix;
		cloned.freePoint = freePoint;
		cloned.containerNumber = containerNumber;
		cloned.volume = volume;
		cloned.filledParcels = filledParcels;
		return cloned;
	}

	// checking the first spot we can fit the box
	// NEED TO CHANGE FOR EVERY SPOT?
	public boolean relevant(Parcel3D p){
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

	// checking if a box can be fit for a given point
	public boolean fit(int i, int j, int k, Parcel3D p){
		double[][] coords = p.getCoords();
		for(int m = 0; m<coords.length; m++)
			if ( i + (int) (coords[m][2]) < cMatrix.length && j + (int) (coords[m][1])<cMatrix[0].length && k + (int) (coords[m][0])<cMatrix[0][0].length )
			{
				if ( cMatrix[i+(int)(coords[m][2])] [j+(int)(coords[m][1])] [k+(int)(coords[m][0])] == true)
					return false;
			}
			else
				return false;
	return true;
	}

	// fitting a box in a given point, and updates the number
	public void fill(int i, int j, int k, Parcel3D p){
			double[][] coords = p.getCoords();
			for(int m = 0; m<coords.length; m++){
					if ( i+(int) (coords[m][2])<cMatrix.length && j+(int) (coords[m][1])<cMatrix[0].length && k+(int) (coords[m][0])<cMatrix[0][0].length )
							cMatrix[ i+(int) (coords[m][2])][j+(int) (coords[m][1])][k+(int) (coords[m][0]) ] = true;
			}
			// updateNumber();
			updateVolume();
			Parcel3D newP = (Parcel3D) p.clone();
			newP.setPlace(i - Math.abs(this.getDim()[0]/2), j - Math.abs(this.getDim()[1]/2), k - Math.abs(this.getDim()[2]/2));
			filledParcels.add(newP);
	}

	/*
	// create the number of the container (same containers have same number)
	public boolean updateNumber()
	{
		// checking if there is a match. if it finds a match, it updates the container number.
		for (int i=0; i<v.size(); i++)
		{
				Container x = v.get(i);
				if ( sameMatrix(x) )
				{
						containerNumber = x.getNumber();
						//System.out.println("sameMatrix");
						return true;
				}
		}
		// if not - create a new number and keep this object
		counter++;
		containerNumber = counter;
		v.add(this);
		return false;
	}

	// print the nummbers of the cointainers in the vector
	public void printVector()
	{
		System.out.print("vector of cointainers: ");
		for (int i=0; i<v.size(); i++)
		{
			Container x = v.get(i);
			System.out.print(x.getNumber() + " ");
		}
	}

	// checking if the other object is a container with the same cMatrix as this.
	public boolean sameMatrix(Object otherObject)
	{
		if (otherObject == null) return false;
		if (getClass() != otherObject.getClass()) return false;
		Container other = (Container) otherObject;

		for (int i=0; i<cMatrix.length; i++)
				for (int j=0; j<cMatrix[i].length; j++)
						for (int k=0; k<cMatrix[i][j].length; k++)
						{
								if ( cMatrix.length!=other.getMatrix().length || cMatrix[i].length!=other.getMatrix()[i].length || cMatrix[i][j].length!=other.getMatrix()[i][j].length)
										return false;
								if (cMatrix[i][j][k] != other.getMatrix()[i][j][k])
										return false;
						}
		return true;
	}
*/

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

	public int[] getFreePoint()
	{ return freePoint; }

	public int getNumber()
	{ return containerNumber; }

	public int getVolume()
	{ return volume; }

	public ArrayList<Parcel3D> getFilledParcels()
	{ return filledParcels; }

}
