import java.util.*;

public class MurkyPent // Backtracking via recursion to fill the container entirely.
{
	static ArrayList<Parcel[]> acceptance = new ArrayList<Parcel[]>();
	static int counter = 0;
	static int rounds = 0;
	
	public static void main(String[] args)
	{
	// Default container with 8x5x33 dimensions, totalling to 1,320 units cubed volume.
		Container box = new Container(8,5,33); 
		boolean[][][] crate = box.getMatrix();
		explain(box);
		ArrayList<Parcel> placement = new ArrayList<Parcel>();
		
		Parcel a = new Parcel("PB");
		Parcel b = new Parcel("PB2");
		Parcel c = new Parcel("C");
		
		System.out.println("Starting process");
		fitting(box, placement, a, b, c);
		System.out.println("COMPLETE!!");
		explain(box);
		System.out.println(counter + " many solutions have been created!");
		System.out.println("Here is the first solution:");
		for (int i = 0; i < acceptance.get(0).length; i++)
			System.out.println("(" + i + ") " + acceptance.get(0)[i].getName());
	}
	
	
	public static void fitting(Container box, ArrayList<Parcel> placement, Parcel a, Parcel b, Parcel c)
	{
	//	System.out.println("In recursion function");
	
		rounds++;
		//System.out.println("Round: " + rounds);
		boolean full = true;
		for (int k = 0; k < (box.getMatrix())[0][0].length; k++)
				for (int i = 0; i < (box.getMatrix()).length; i++) 
					for (int j = 0; j < (box.getMatrix())[0].length; j++)
						if (box.getMatrix()[i][j][k] == false)
							full = false;
		// Base case.
		if (full == true)
		{
			//System.out.println();
			Parcel[] entry = new Parcel[placement.size()];
			for (int i = 0; i < entry.length; i++)
				entry[i] = placement.get(i);
			
			if (acceptance.size() == 0)
			{
				System.out.println("Base case found");
				acceptance.add(entry);
				System.out.println("Here is the first solution:");
				for (int i = 0; i < acceptance.get(0).length; i++)
				System.out.println("(" + (i+1) + ") " + acceptance.get(0)[i].getName());
			}
			
			return;
		}
		else
		{
			// RECURSE!
		//	System.out.println("Recursing");
			for (int k = 0; k < (box.getMatrix())[0][0].length; k++)
				for (int i = 0; i < (box.getMatrix()).length; i++) 
					for (int j = 0; j < (box.getMatrix())[0].length; j++)
					 // Cycle through the box
					{
					//	System.out.println("point" + i + ", " + j + ", " + k + " is free");
						// Rotation 1.
						if (box.fitTwo(i, j, k, a) == true)
						{
							Parcel a2 = new Parcel("PB");
							placement.add(a2);
							box.fillTwo(i, j, k, placement.get((placement.size() - 1)));
						//	System.out.println("parcel PB has been added");
							//explain(box);
							
							fitting(box, placement, a, b, c);
							box.empty(i, j, k, placement.get((placement.size() - 1)));
							placement.remove(placement.size() - 1);	
		
						//	System.out.println("parcel PB has been removed");
							//explain(box);

						}
						
						// Mirror of rotation 1.
						if (box.fitTwo(i, j, k, b) == true)
						{
							Parcel a2 = new Parcel("PB2");
							placement.add(a2);
							box.fillTwo(i, j, k, placement.get((placement.size() - 1)));
						//	System.out.println("parcel PB2 has been added ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							//explain(box);
							
							fitting(box, placement, a, b, c);
							box.empty(i, j, k, placement.get((placement.size() - 1)));
							placement.remove(placement.size() - 1);	
							
						//	System.out.println("parcel PB2 has been removed");
							//explain(box);

						}

				}
		}
	}
	
	
	public static void explain(Container box)
	{
		for (int k = 0; k < box.getMatrix()[0][0].length; k++)
		{
			for (int i = 0; i < box.getMatrix().length; i++)
			{
				for (int j = 0; j < box.getMatrix()[0].length; j++)	
				{
					if(box.getMatrix()[i][j][k] == false)
						System.out.print("F");
					else System.out.print("T");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}