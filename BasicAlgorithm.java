import java.lang.Math;

public class BasicAlgorithm
{
    private Container container;
    private Parcel[] boxList;
    private Parcel[][] allOders;

    public BasicAlgorithm(Container c, Parcel[] pList)
    {
      container = c;
      boxList = pList;
      allOders = new Permutation(boxList).getPermutationArray();
    }

    /*
    public int maximizeValue(int index, int capacity)
    {
      // base case
      if (index==0 || capacity==0)
          resultValue = 0;
      else if (w[i] > capacity)
          resultValue = maximizeValue(index - 1, capacity);
      else
      {
          temp1 = maximizeValue(index - 1, capacity);
          temp2 = v[index] + maximizeValue(index - 1, capacity - w[index]);
          resultValue = Math.max(temp1, temp2);
      }
      return resultValue;
    }
    */

    public int maximize(String type)
    {
        int max = 0;
        for (int i=0; i<allOders.length; i++)
        {
            if ( type.toLowerCase().equals("value") )
                max = Math.max( max, maximizeValue(allOders[i].length-1, container) );
            if ( type.toLowerCase().equals("volume") )
                max = Math.max( max, maximizeVolume(allOders[i].length-1, container) );
        }
        return max;
    }

    public int maximizeValue(int index, Container c)
    {
      int resultValue;
      // base case
      if (index==-1 || c.relevant(boxList[index])==false)
          resultValue = 0;
      // recursive step
      else
      {
          // option 1- without this box
          int temp1 = maximizeValue(index - 1, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList[index]);
          int temp2 = boxList[index].getValue() + maximizeValue(index - 1, c2);
          resultValue = Math.max(temp1, temp2);
      }
      return resultValue;
    }

    public int maximizeVolume(int index, Container c)
    {
      int resultVolume;
      // base case
      if (index==-1 || c.relevant(boxList[index])==false)
          resultVolume = c.getVolume();
      // recursive step
      else
      {
          // option 1- without this box
          int temp1 = maximizeVolume(index - 1, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList[index]);
          int temp2 = maximizeVolume(index - 1, c2);
          resultVolume = Math.max(temp1, temp2);
      }
      return resultVolume;
    }

    public static void main(String[] args)
    {
      Container container = new Container();
      GenerateParcelList generator = new GenerateParcelList(20, "random");
      Parcel[] parcelsList = generator.getList();
      System.out.println("Parcels list: ");
      generator.print();
      BasicAlgorithm myAlgorithm = new BasicAlgorithm(container, parcelsList);
      // max value
      int x = myAlgorithm.maximize("value");
      System.out.println("Result value: " + x);
      // max volume
      int y = myAlgorithm.maximize("volume");
      System.out.println("Result volume: " + y);
    }

} // class
