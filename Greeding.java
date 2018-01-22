import java.util.*;

public class Greeding // Maximising value density based on short term gains.
{
	static int counter = 0;
	static int countOfA = 0;
	static int countOfC = 0;
	static int countOfB = 0;
	static boolean usedA = false;	
	static boolean usedC = false;
	static boolean usedB = false;

	
	public static void main(String[] args)
	{
	// Default container with 8x5x33 dimensions, totalling to 1,320 units cubed volume.
		Container box = new Container(); 
		boolean[][][] crate = box.getMatrix();
		ArrayList<Parcel> placementOrder = new ArrayList<Parcel>();
	
	// Cycle through the possible parcels.
		Parcel doesAfit = new Parcel("A");
		Parcel doesCfit = new Parcel("C");
		Parcel doesBfit = new Parcel("B");
		
		System.out.println("Parcel A has a rate of:" + doesAfit.getRate());
		System.out.println("Parcel B has a rate of:" + doesBfit.getRate());
		System.out.println("Parcel C has a rate of:" + doesCfit.getRate());

	// Sort by value!
		String[] scan = sort();
	
	// Scan container through k, j and then i for an empty placement space.
	
		for (int k = 0; k < crate[0][0].length; k++)
			for (int j = 0; j < crate[0].length; j++)
				for (int i = 0; i < crate.length; i++)
					if (crate[i][j][k] == false)
					{
	// Ordering.
		
			
						if (scan[0].equals("a"))
						{
							a(i, j, k, doesAfit, box, placementOrder);
							//System.out.println("beep");
							if(scan[1].equals("b"))
							{
								b(i, j, k, doesBfit, box, placementOrder);
								c(i, j, k, doesCfit, box, placementOrder);
							}
							else
							{
								c(i, j, k, doesCfit, box, placementOrder);
								b(i, j, k, doesBfit, box, placementOrder);
							}
						}
						
						else if (scan[0].equals("b"))
						{
							b(i, j, k, doesBfit, box, placementOrder);
							//System.out.println("boop");
							if(scan[1].equals("a"))
							{
								a(i, j, k, doesAfit, box, placementOrder);
								c(i, j, k, doesCfit, box, placementOrder);
							}
							else
							{
								c(i, j, k, doesCfit, box, placementOrder);
								a(i, j, k, doesAfit, box, placementOrder);
							}
						}
						
						else if (scan[0].equals("c"))
						{
							c(i, j, k, doesCfit, box, placementOrder);
							//System.out.println("bop");
							if(scan[1].equals("b"))
							{
								b(i, j, k, doesBfit, box, placementOrder);
								a(i, j, k, doesAfit, box, placementOrder);
							}
							else
							{
								a(i, j, k, doesAfit, box, placementOrder);
								b(i, j, k, doesBfit, box, placementOrder);
							}
						}
						//else System.out.print("Fit: DENIED!!!");
						
	// Parcel A.
					/*	if (box.fit(i, j, k, doesAfit) == true) // When this parcel can fit in at this spot.
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
						}*/
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
	
	
	public static String[] sort()
	{
		String[] scan = new String[3];
		Parcel a = new Parcel("A");
		Parcel c = new Parcel("C");
		Parcel b = new Parcel("B");
		
		if (a.getRate() >= b.getRate() && a.getRate() >= c.getRate())
		{
			if (b.getRate() > c.getRate())
			{
				scan[0] = "a";
				scan[1] = "b";
				scan[2] = "c";
			}
			else if (c.getRate() > b.getRate())
			{
				scan[0] = "a";
				scan[1] = "c";
				scan[2] = "b";
			}
		}
		else if (b.getRate() > a.getRate() && b.getRate() >= c.getRate())
		{
			if (a.getRate() > c.getRate())
			{
				scan[0] = "b";
				scan[1] = "a";
				scan[2] = "c";
			}
			else if (c.getRate() > a.getRate())
			{
				scan[0] = "b";
				scan[1] = "c";
				scan[2] = "a";
			}
		}
		else if (c.getRate() > a.getRate() && c.getRate() > b.getRate())
		{
			if(a.getRate() > b.getRate())
			{
				scan[0] = "c";
				scan[1] = "a";
				scan[2] = "b";
			}
			else if (b.getRate() > a.getRate())
			{
				scan[0] = "c";
				scan[1] = "b";
				scan[2] = "a";
			}
		}
		else System.out.println("Kill yourself");

		System.out.println("scan's " + scan.length + " elements are: " + scan[0] + ", " + scan[1] + ", " + scan[2] + "." );
		
		return scan;
	}

	
	public static void a(int i, int j, int k, Parcel doesAfit, Container box, ArrayList<Parcel> placementOrder)
	{
		if (box.fit(i, j, k, doesAfit) == true) // When this parcel can fit in at this spot.
		{
			Parcel aNewA = new Parcel("A");
			placementOrder.add(aNewA);
			box.fill(i, j, k, placementOrder.get(counter));
			// System.out.println("Parcel #" + (counter + 1) + " placed!");
			counter++;
			countOfA++;
			if (usedA == false)
				usedA = true;
		}
	}
	
	
	public static void b(int i, int j, int k, Parcel b, Container box, ArrayList<Parcel> placementOrder)
	{
		if (box.fit(i, j, k, b) == true) // When this parcel can fit in at this spot.
		{
			Parcel aNewB = new Parcel("B");
			placementOrder.add(aNewB);
			box.fill(i, j, k, placementOrder.get(counter));
			// System.out.println("Parcel #" + (counter + 1) + " placed!");
			counter++;
			countOfB++;
			if (usedB == false)
				usedB = true;
		}
	}
	
	
	public static void c(int i, int j, int k, Parcel c, Container box, ArrayList<Parcel> placementOrder)
	{
		if (box.fit(i, j, k, c) == true) // When this parcel can fit in at this spot.
		{
			Parcel aNewC = new Parcel("C");
			placementOrder.add(aNewC);
			box.fill(i, j, k, placementOrder.get(counter));
			// System.out.println("Parcel #" + (counter + 1) + " placed!");
			counter++;
			countOfC++;
			if (usedC == false)
				usedC = true;
		}
	}
}