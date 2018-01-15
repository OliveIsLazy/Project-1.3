import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

public class Permutation
{
	private ArrayList<ArrayList<Parcel>> allOptionsList;
	private Parcel[][] allOptionsListArray;

	public Permutation(Parcel[] originalL)
	{
		allOptionsList = new ArrayList<ArrayList<Parcel>>();
		ArrayList<Parcel> l = new ArrayList<Parcel>();
		for (int i=0; i<originalL.length; i++)
				l.add(originalL[i]);
		allOptions(l, 0);
		convert();
	}

	public ArrayList<ArrayList<Parcel>> getPermutationList()
	{ return allOptionsList; }

	public Parcel[][] getPermutationArray()
	{ return allOptionsListArray; }

	public void allOptions(ArrayList<Parcel> list, int k)
	{
			// basic case
			if (k == list.size() - 1)
			{
					ArrayList<Parcel> l = new ArrayList<Parcel>();
					for (int i=0; i<list.size(); i++)
							l.add(list.get(i));
					allOptionsList.add(l);
					// System.out.println(java.util.Arrays.toString(list.toArray()));
			}
			else
			{//static void permute(java.util.List<Integer> arr, int k){
      	for(int i = k; i < list.size(); i++)
				{
          java.util.Collections.swap(list, i, k);
          allOptions(list, k+1);
          java.util.Collections.swap(list, k, i);
      	}
		  }
  }

	public void convert()
	{
			allOptionsListArray = new Parcel[allOptionsList.size()][allOptionsList.get(0).size()];
			for (int i=0; i<allOptionsList.size(); i++)
					for (int j=0; j<allOptionsList.get(i).size(); j++)
							allOptionsListArray[i][j] = allOptionsList.get(i).get(j);
	}

	/*
	public static void main(String[] args)
	{
		// Parcel[] p_list = {1,2,3};
		ArrayList<ArrayList<Parcel>> all = new Permutation(p_list).getPermutationList();
		for (int i=0; i<all.size(); i++)
		{
				for (int j=0; j<all.get(i).size(); j++)
				{
						ArrayList<Parcel> x = all.get(i);
						System.out.print(x.get(j) + " ");
				}
				System.out.println();
		}
	}
	*/

}
