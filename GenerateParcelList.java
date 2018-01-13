import java.lang.Math;

public class GenerateParcelList
{
      private Parcel[] parcelsList;

      public GenerateParcelList(int n, String s)
      {
        parcelsList = new Parcel[n];
        if (s.toLowerCase().equals("random"))
            random();
      }

      public Parcel[] getList()
      { return parcelsList; }

      public void print()
      {
        for (int i=0; i<parcelsList.length; i++)
            System.out.println(parcelsList[i].getName());
      }

      public void random()
      {
        for (int i=0; i<parcelsList.length; i++)
        {
           String t;
           double x = (10*Math.random());
    	     if (x < 3.3) {t = "A";}
    	     else if (x < 6.6) {t = "B";}
    	     else {t = "C";}
           parcelsList[i] = new Parcel(t);
           //System.out.println(t);
        }
      }

} // class
