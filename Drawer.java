import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.lang.Math;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Drawer  extends JComponent implements MouseListener, MouseMotionListener
{
  private double projPoint;
  private Parcel3D[] parcels;
  private int xLeft = 300;
  private int yTop = 300;
  private int mouseX, mouseY;
  private double delX, delY;

  public Drawer(ArrayList<Parcel3D> p, double projp)
  {
    parcels = new Parcel3D[p.size()];
    for(int i = 0; i < p.size(); i++)
      parcels[i] = p.get(i);
    projPoint = projp;

    addMouseListener( this );
    addMouseMotionListener( this );

  }
  public void paintComponent(Graphics g)
  {
    Color[] colors = {Color.blue, Color.green, Color.red, Color.BLACK};

    Graphics2D g2 = (Graphics2D)g;


    for (int i = 0; i < parcels.length; i++)
    {
      parcels[i].setProjPoint(projPoint);
      parcels[i].setXY2D(xLeft, yTop);
      parcels[i].setDeltaXY(delX, delY);
      parcels[i].draw(g2);
    }
  }

  public void mouseEntered( MouseEvent e ) { }
  public void mouseExited( MouseEvent e ) { }
  public void mouseClicked( MouseEvent e ) { }
  public void mousePressed( MouseEvent e ) {
     mouseX = e.getX();
     mouseY = e.getY();
     e.consume();
  }
  public void mouseReleased( MouseEvent e ) { }
  public void mouseMoved( MouseEvent e ) { }
  public void mouseDragged( MouseEvent e ) {
     // get the latest mouse position
     int newMouseX = e.getX();
     int newMouseY = e.getY();

     // adjust angles according to the distance travelled by the mouse
     // since the last event
     delY -= (newMouseX - mouseX)*0.2;
     delX += (newMouseY - mouseY)*0.2;

     //System.out.println(" delta x " + delX + " delta y " + delY);
    //System.out.println("cosx : " + Math.cos(delX/180*PI) + "cosy :" + Math.cos(delY/180*PI));
    //System.out.println("sinx : " + Math.sin(delX/180*PI) + "siny :" + Math.sin(delY/180*PI));

     for(int i = 0; i < parcels.length; i++)
       parcels[i].setDeltaXY(delX, delY);

     // update our data
     mouseX = newMouseX;
     mouseY = newMouseY;

     repaint();
     e.consume();
  }

  public void update(Parcel3D[] par)
  {
    parcels = par;
    this.repaint();
  }





  public static double[][] multOfMatrices(double[][] a, double[][] b)
  {
    double[][] mult = new double[a.length][b[0].length];
    for (int k=0; k < a.length; k++)
      for (int l=0; l< b[0].length; l++)
        for (int m=0; m < a[0].length; m++)
          mult[k][l] += a[k][m]*b[m][l];
    return mult;
  }

  public static void printArray(double[][] a)
  {
    for(int i = 0; i < a.length; i++)
    {
      for(int j = 0; j < a[i].length; j++)
      System.out.print(a[i][j] + " ");
      System.out.println("");
    }
    System.out.println("");
  }
}
