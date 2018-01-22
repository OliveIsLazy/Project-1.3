import java.lang.Math;
import java.util.*;

public class BasicAlgorithm
{
    private Container container;
    private Parcel3D[] boxList;
    private ArrayList<Parcel3D> boxList2;
    private Container best;

    public BasicAlgorithm(Container c, Parcel3D[] pList)
    {
      container = c;
      boxList = pList;
      boxList2 = transfer(boxList);
      boxList2 = translate(boxList2);
    }

    public double maximize(String type)
    {
        double max = 0;
        if ( type.toLowerCase().equals("value") )
                max = maximizeValue(boxList2.size()-1, boxList2, container);
        // if ( type.toLowerCase().equals("volume") )
                // max = maximizeVolume(boxList2.size()-1, boxList2, container);
        return max;
    }

    public double maximizeValue(int index, ArrayList<Parcel3D> list, Container c)
    {
      double resultValue = 0;
      // base case
      if (index==-1 /*add if theres no place to any parcel*/)
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
            double temp1 = maximizeValue(index - 1, list, c);
            // option 2- with this box
            double temp2 = 0;
            Container c2 = new Container();
            if (c.relevant(list.get(index))==true)
            {
            	c2 = (Container) c.clone();
            	//int[] free = c2.getFreePoint();
            	//c2.fill(free[0],free[1],free[2], list.get(index));
            	temp2 = list.get(index).getValue() + maximizeValue(index - 1, list, c2);
            }
            // take the better option
            if (resultValue < Math.max(temp1, temp2))
            {
            		resultValue = Math.max(temp1, temp2);
            		if (temp1>=temp2)
            			best = (Container) c.clone();
            		else
            			best = (Container) c2.clone();
           	}

            //System.out.println("resultValue " + resultValue);
            // swap back
            java.util.Collections.swap(list, i, k);
          }
      }
      // System.out.println(resultValue);
      return resultValue;
    }

    /*
    public int maximizeVolume(int index, ArrayList<Parcel3D> list, Container c)
    {
      int resultVolume = 0;
      // base case
      if (index==-1 )
          resultVolume = c.getVolume() / (4*4*4);
      if (c.relevant(list.get(index))==false)
          resultValue = maximizeValue(index - 1, list, c);
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
          int temp1 = maximizeVolume(index - 1, list, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList2.get(index));
          int temp2 = maximizeVolume(index - 1, list, c2);
          // take the best result
          if (resultVolume < Math.max(temp1, temp2))
          {
            resultVolume = Math.max(temp1, temp2);
          }
          // swap back
          java.util.Collections.swap(list, i, k);
        }
      }
      return resultVolume;
    }
    */

    public ArrayList<Parcel3D> transfer(Parcel3D[] l1)
    {
      ArrayList<Parcel3D> l2 = new ArrayList<Parcel3D>();
      for (int i=0; i<l1.length; i++)
          l2.add(l1[i]);
      return l2;
    }

    public ArrayList<Parcel3D> translate(ArrayList<Parcel3D> l)
    {
      for (int i=0; i<l.size(); i++)
          l.get(i).setPlace(Math.abs(l.get(i).getDim()[0]/2), Math.abs(l.get(i).getDim()[1]/2), Math.abs(l.get(i).getDim()[2]/2));
      return l;
    }

    public Container getContainer()
    { return best; }


    /*
    public static void main(String[] args)
    {
      Container container = new Container();
      GenerateParcelList generator = new GenerateParcelList(4, "random");
      Parcel3D[] ParcelsList = generator.getList();
      System.out.println("Parcels list: ");
      generator.print();
      BasicAlgorithm myAlgorithm = new BasicAlgorithm(container, ParcelsList);
      // max value
      double x = myAlgorithm.maximize("value");
      System.out.println("Result value: " + x);
      // max volume
      double y = myAlgorithm.maximize("volume");
      System.out.println("Result volume: " + y);
    }
    */


} // class
