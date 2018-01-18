import java.lang.Math;
import java.util.*;

public class BasicAlgorithm
{
    private Container container;
    private Parcel[] boxList;
    private ArrayList<Parcel> boxList2;

    public BasicAlgorithm(Container c, Parcel[] pList)
    {
      container = c;
      boxList = pList;
      boxList2 = transfer(boxList);
    }

    public int maximize(String type)
    {
        int max = 0;
        if ( type.toLowerCase().equals("value") )
                max = maximizeValue(boxList2.size()-1, boxList2, container);
        // if ( type.toLowerCase().equals("volume") )
                // max = maximizeVolume(boxList2.size()-1, boxList2, container);
        return max;
    }

    public int maximizeValue(int index, ArrayList<Parcel> list, Container c)
    {
      int resultValue = 0;
      // base case
      if (index==-1 || c.relevant(list.get(index))==false)
      {
          return resultValue;
      }
      // recursive step
      else
      {
          int k = index;
          for (int i=k; i>=0; i--)
          {
            // swap
            java.util.Collections.swap(list, k, i);
            // check
            // option 1- without this box
            int temp1 = maximizeValue(index - 1, list, c);
            // option 2- with this box
            Container c2 = (Container) c.clone();
            int[] free = c2.getFreePoint();
            c2.fill(free[0],free[1],free[2], list.get(index));
            int temp2 = list.get(index).getValue() + maximizeValue(index - 1, list, c2);
            if (resultValue < Math.max(temp1, temp2))
                resultValue = Math.max(temp1, temp2);
            // System.out.println("resultValue " + resultValue);

            // swap back
            java.util.Collections.swap(list, i, k);
            // check ?
            /*
            // option 1- without this box
            temp1 = maximizeValue(index - 1, list, c);
            // option 2- with this box
            c2 = (Container) c.clone();
            free = c2.getFreePoint();
            c2.fill(free[0],free[1],free[2], list.get(index));
            temp2 = list.get(index).getValue() + maximizeValue(index - 1, list, c2);
            int result2 = Math.max(temp1, temp2);
            // System.out.println(result2);

            // take max
            resultValue = Math.max(result1, result2);
            System.out.println(resultValue);
            */
          }
          System.out.println(resultValue);
          return resultValue;
      }
    }

    /*
    public int maximizeVolume(int index, Container c)
    {
      int resultVolume;
      // base case
      if (index==-1 || c.relevant(boxList2.get(index))==false)
          resultVolume = c.getVolume();
      // recursive step
      else
      {
          // option 1- without this box
          int temp1 = maximizeVolume(index - 1, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList2.get(index));
          int temp2 = maximizeVolume(index - 1, c2);
          resultVolume = Math.max(temp1, temp2);
      }
      return resultVolume;
    }
    */

    public ArrayList<Parcel> transfer (Parcel[] l1)
    {
      ArrayList<Parcel> l2 = new ArrayList<Parcel>();
      for (int i=0; i<l1.length; i++)
          l2.add(l1[i]);
      return l2;
    }

    public static void main(String[] args)
    {
      Container container = new Container();
      GenerateParcelList generator = new GenerateParcelList(6, "random");
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
