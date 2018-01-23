import java.lang.Math;
import java.util.*;

public class GenerateParcelList
{
      private Parcel3D[] parcelsList;
      private ArrayList<Parcel3D> parcelsList2 = new ArrayList<Parcel3D>();

      public GenerateParcelList(int n, String s)
      {
        parcelsList = new Parcel3D[n];
        if (s.toLowerCase().equals("random"))
            random();
        if (s.toLowerCase().equals("a"))
            uniform(0);
        if (s.toLowerCase().equals("b"))
            uniform(1);
        if (s.toLowerCase().equals("c"))
            uniform(2);
      }

      public ArrayList<Parcel3D> getList()
      {
        parcelsList2 = transfer(parcelsList);
        return parcelsList2;
      }

      public void print()
      {
        for (int i=0; i<parcelsList.length; i++)
            System.out.println(parcelsList[i].getName());
      }

      public void random()
      {
        for (int i=0; i<parcelsList.length; i++)
        {
           int t;
           double x = (10*Math.random());
    	     if (x < 3.3) {t = 0;}
    	     else if (x < 6.6) {t = 1;}
    	     else {t = 2;}
           parcelsList[i] = new Parcel3D(t);
           //System.out.println(t);
        }
      }

      public void uniform(int t)
      {
        for (int i=0; i<parcelsList.length; i++)
           parcelsList[i] = new Parcel3D(t);
           //System.out.println(t);
      }

      public ArrayList<Parcel3D> transfer(Parcel3D[] l1)
      {
        ArrayList<Parcel3D> l2 = new ArrayList<Parcel3D>();
        for (int i=0; i<l1.length; i++)
            l2.add(l1[i]);
        return l2;
      }

} // class
