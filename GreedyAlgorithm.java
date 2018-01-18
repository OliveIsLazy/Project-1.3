import java.lang.Math;

public class GreedyAlgorithm
{
    private Container container;
    private Parcel[] boxList;

    public GreedyAlgorithm(Container c, Parcel[] pList)
    {
      container = c;
      boxList = pList;
      retioOrder();
    }

    public int[] maximize()
    {  return maximizeInternal(boxList.length-1, container);  }

    public int[] maximizeInternal(int index, Container c)
    {
      int[] result = {0,0};
      // base case
      if (index==-1)
          //result[0] += 0;
          //result[1] += 0;
          return result;
      // recursive step
      if (c.relevant(boxList[index])==false)
      {
          result = maximizeInternal(index - 1, c);
      }
      else
      {
          int[] free = c.getFreePoint();
          c.fill(free[0],free[1],free[2], boxList[index]);
          result = maximizeInternal(index - 1, c);
          result[0] += boxList[index].getValue();
          result[1] += boxList[index].getVolume();
          System.out.println(boxList[index].getName() + " was used");
      }
      return result;
    }

    public void retioOrder()
    {
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        for (int i=0; i<boxList.length; i++)
        {
          if (boxList[i].getName()=="A")
              counterA++;
          else if (boxList[i].getName()=="B")
              counterB++;
          else counterC++;
        }
        for (int i=0; i<counterA; i++)
            boxList[i] = new Parcel("A");
        for (int i=counterA; i< counterA+counterC; i++)
            boxList[i] = new Parcel("C");
        for (int i=counterA+counterC; i< counterA+counterC+counterB; i++)
            boxList[i] = new Parcel("B");
    }

    public static void main(String[] args)
    {
      Container container = new Container();
      GenerateParcelList generator = new GenerateParcelList(50, "random");
      Parcel[] parcelsList = generator.getList();
      System.out.println("Parcels list: ");
      generator.print();
      GreedyAlgorithm myAlgorithm = new GreedyAlgorithm(container, parcelsList);
      // max value
      int[] a = myAlgorithm.maximize();
      System.out.println("Result value: " + a[0]);
      // max volume
      System.out.println("Result volume: " + a[1]);
    }

} // class
