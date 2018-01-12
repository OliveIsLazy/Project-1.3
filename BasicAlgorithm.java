import java.lang.Math;

public class BasicAlgorithm
{
    private Container container;
    private Parcel[] boxList;

    public BasicAlgorithm(Container c, Parcel[] pList)
    {
      container = c;
      boxList = pList;
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

    public int maximizeValue()
    { return maximizeValueInternal(boxList.length-1, container);  }

    public int maximizeValueInternal(int index, Container c)
    {
      int resultValue;
      // base case
      if (index==-1 || c.relevant(boxList[index])==false)
          resultValue = 0;
      // recursive step
      else
      {
          // option 1- without this box
          int temp1 = maximizeValueInternal(index - 1, c);
          // option 2- with this box
          Container c2 = (Container) c.clone();
          int[] free = c2.getFreePoint();
          c2.fill(free[0],free[1],free[2], boxList[index]);
          int temp2 = boxList[index].getValue() + maximizeValueInternal(index - 1, c2);
          resultValue = Math.max(temp1, temp2);
      }
      return resultValue;
    }

    public static void main(String[] args)
    {
      Container container = new Container();
      Parcel[] parcelsList = new Parcel[6];
      for (int i=0; i<parcelsList.length; i++)
      {
         String t;
         double x = (10*Math.random());
  	     if (x < 3.3) {t = "A";}
  	     else if (x < 6.6) {t = "B";}
  	     else {t = "C";}
         parcelsList[i] = new Parcel(t);
         System.out.println(t);
      }
      BasicAlgorithm myAlgorithm = new BasicAlgorithm(container, parcelsList);
      int x = myAlgorithm.maximizeValue();
      System.out.println(x);
    }

} // class
