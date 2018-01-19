import java.util.*;

public class Greeding // Maximising value density based on short term gains.
{
	public static void main(String[] args)
	{
	// Default container with 8x5x33 dimensions, totalling to 1,320 units cubed volume.
		Container box = new Container(); 
		boolean[][][] crate = box.getMatrix();
		ArrayList<Parcel> placementOrder = new ArrayList<Parcel>();
		int counter = 0;
		int countOfA = 0;
		int countOfC = 0;
		int countOfB = 0;
		boolean usedA = false;	
		boolean usedC = false;
		boolean usedB = false;
	
	
	// Cycle through the possible parcels.
		Parcel doesAfit = new Parcel("A");
		Parcel doesCfit = new Parcel("C");
		Parcel doesBfit = new Parcel("B");
		
	// Scan container through k, j and then i for an empty placement space.
	
	for (int k = 0; k < crate[0][0].length; k++)
		for (int j = 0; j < crate[0].length; j++)
			for (int i = 0; i < crate.length; i++)
				if (crate[i][j][k] == false)
				{
	// Parcel A.
					if (box.fit(i, j, k, doesAfit) == true) // When this parcel can fit in at this spot.
					{
						Parcel aNewA = new Parcel("A");
						placementOrder.add(aNewA);
						box.fill(i, j, k, placementOrder.get(counter));
						System.out.println("Parcel #" + (counter + 1) + " placed!");
						counter++;
						countOfA++;
						if (usedA == false)
							usedA = true;
					}
					
	// Parcel C.
					else if (box.fit(i, j, k, doesCfit) == true)
					{
						Parcel aNewC = new Parcel("C");
						placementOrder.add(aNewC);
						box.fill(i, j, k, placementOrder.get(counter));
						System.out.println("Parcel #" + (counter + 1) + " placed!");
						counter++;
						countOfC++;
						if (usedC == false)
							usedC = true;
					}
					
	// Parcel B.
					else if (box.fit(i, j, k, doesBfit) == true)
					{
						Parcel aNewB = new Parcel("B");
						placementOrder.add(aNewB);
						box.fill(i, j, k, placementOrder.get(counter));
						System.out.println("Parcel #" + (counter + 1) + " placed!");
						counter++;
						countOfB++;
						if (usedB == false)
							usedB = true;
					}
				}
		
	// Report!	
		int value = (countOfA * 3) + (countOfC * 5) + (countOfB * 4);
		double volume = box.getVolume();
		double totalVolume = (crate.length) * (crate[0].length) * (crate[0][0].length);
		//volume = volume * 100;
		System.out.println("Used volume: " + volume);
		volume = volume / totalVolume * 100;
		int typeCount = 0;
		if (usedA) typeCount++;
		if (usedC) typeCount++;
		if (usedB) typeCount++;
		
		
		System.out.println("Crate dimensions are: (" + crate.length + "," + crate[0].length + "," + crate[0][0].length + ").");
		
		System.out.println("The crate has " + volume + "% of it's volume used with a total value of " + (value) + " currency.");
		
		System.out.print("There were " + (placementOrder.size()) + " parcels used with " + (typeCount));
		if (typeCount > 1) System.out.print(" different");
		System.out.print(" type");
		if (typeCount > 1) System.out.print("s");
		
		System.out.println(", " + (countOfC) + " C parcels, " + (countOfB) + " B parcels and " + (countOfA) + " A parcels.");
		
	/*	
		System.out.println("Here is the parcel order: ");
		for (int i = 0; i < placementOrder.size() - 1; i++)
			System.out.print( "(" + (placementOrder.indexOf(placementOrder.get(i)) + 1) + ")" + placementOrder.get(i).getName() + ", ");
		System.out.println( "("+ placementOrder.size() + ")" + placementOrder.get(placementOrder.size() - 1).getName() +".");
		
		
		for (int i = 0; i < crate.length; i++)
		{
			for (int j = 0; j < crate[0].length; j++)
			{
				for (int k = 0; k < crate[0][0].length; k++)
				{
					if (crate[i][j][k] == false)
						System.out.println("Point (" + i + "," + j + "," + k + ") is false.");
				}	
			}			
		}*/
	}
}