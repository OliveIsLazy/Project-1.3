import java.lang.Math;

public class DynamicAlgorithm
{
    private Container container;
    private Parcel[] boxList;
    private int[][] storage;
    private int counter;
    private Parcel[][] allOders;

    public DynamicAlgorithm(Container c, Parcel[] pList)
    {
      container = c;
      boxList = pList;
      allOders = new Permutation(boxList).getPermutationArray();
    }

    public int maximizeValue()
    {
        storage = new int[3][(2*8*5*33) + 1];
        for (int i=0; i<storage.length; i++)
            for (int j=0; j<storage[i].length; j++)
                storage[i][j] = -1;

        int max = 0;
        for (int i=0; i<allOders.length; i++)
        {
            max = Math.max( max, maximizeValueInternal(allOders[i].length-1, container) );
        }
        return max;
    }

    public int maximizeValueInternal(int index, Container c)
    {
      System.out.println("index: " + index);
      System.out.println("container: " + c.getNumber());
      // check input
      if (index == -1)
      {
          System.out.println("finished");
          return 0;
      }
      // check storage
      if (storage [(boxList[index]).getNumber()] [c.getNumber()] != -1)
      {
          System.out.println("use storage");
          return storage[(boxList[index]).getNumber()][c.getNumber()];
      }

      // otherwise - calculate
      int resultValue;
      // base case
      if (c.relevant(boxList[index])==false)
      {
          System.out.println("no space");
          resultValue = 0;
      }
      // recursive step
      else
      {
          // option 1- without this box
          int temp1 = maximizeValueInternal(index - 1, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList[index]);
          System.out.println("container 2: " + c2.getNumber());
          int temp2 = boxList[index].getValue() + maximizeValueInternal(index - 1, c2);
          resultValue = Math.max(temp1, temp2);
      }
      storage[(boxList[index]).getNumber()][c.getNumber()] = resultValue;
      // System.out.println("result value: " + resultValue);
      return resultValue;
    }

    public void printStorage()
    {
    	System.out.println("Storage: ");
      for (int i=0; i<storage.length; i++)
      {
          for (int j=0; j<storage[i].length; j++)
    			     System.out.print(storage[i][j] + " ");
          System.out.println();
      }
    }

    public static void main(String[] args)
    {
      Container container = new Container();
      GenerateParcelList generator = new GenerateParcelList(20, "random");
      Parcel[] parcelsList = generator.getList();
      System.out.println("Parcels list: ");
      generator.print();
      DynamicAlgorithm myAlgorithm = new DynamicAlgorithm(container, parcelsList);
      int x = myAlgorithm.maximizeValue();
      System.out.println("Result value: " + x);
      container.printVector();
      //myAlgorithm.printStorage();
    }

} // class
