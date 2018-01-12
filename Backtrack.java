import java.util.*;

public class Backtrack
{
	public static void main(String[] args)
	{
		search(1,1,1); // A = 5, B = 27, C = 89.	
	}
	
	
	public static void search (int numberofA, int numberofB, int numberofC)
	{
		// Initalise searching assistants.
		boolean found = false;
	//	ArrayList<int> failures = new ArrayList<int>();
		boolean failing = false;
		int[] chosenOnes = new int[numberofA + numberofB + numberofC];
		System.out.print("The original array is: ");
		explainPlez(chosenOnes);
		System.out.println();
		
		// Create base permutation.
		for (int i = 0; i < numberofA; i++)
			chosenOnes[i] = 5;
		
		for (int i = 0; i < numberofB; i++)
			chosenOnes[numberofA + i] = 27;
		
		for (int i = 0; i < numberofC; i++)
			chosenOnes[numberofA + numberofB + i] = 89;
		
		System.out.print("The inital filled array is: ");
		explainPlez(chosenOnes);
		System.out.println();

		// Permufy!
		permute(chosenOnes, 0, chosenOnes.length-1);
		
		/*for (int i = 0; i < failures.length; i++)
		{
			if (chosenOnes.equals(failures.get(i)) == true)
			{
				failing = true;
				System.out.println("Disgusting!");
			}
		}*/
	}
	
	
	public static void permute(int[] chosenOnes, int l, int r)
    {
        if (l == r)
            explainPlez(chosenOnes); // fit();
        else
        {
            for (int i = l; i <= r; i++)
            {
                swap(chosenOnes,l,i);
                permute(chosenOnes, l+1, r);
                swap(chosenOnes,l,i);
            }
        }
    }
	
	
	public static void swap(int[] a, int i, int j)
    {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
        /*char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);*/
    }
	
	
	public static void explainPlez(int[] a)
	{
		System.out.print("[");
		
		for (int i = 0; i < a.length-1; i++)
		{
			System.out.print(a[i] + ", ");
		}
		System.out.println(a[a.length-1] + "]");
	}
}