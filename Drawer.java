import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.lang.Math;
import static java.lang.Math.*;
import java.util.ArrayList;

public class Drawer  extends JComponent
{
  double[] projPoint = new double[3];
  Parcel3D[] parcels;
  int xLeft = 300;
  int yTop = 300;
  public Drawer(ArrayList<Parcel3D> p, double[] projp)
  {
    parcels = new Parcel3D[p.size()];
    for(int i = 0; i < p.size(); i++)
      parcels[i] = p.get(i);
    projPoint = projp;
  }
  public void paintComponent(Graphics g)
  {
    Color[] colors = {Color.blue, Color.green, Color.red, Color.BLACK};

    Graphics2D g2 = (Graphics2D)g;


    for (int i = 0; i < parcels.length; i++)
    {
      parcels[i].setProjPoint(projPoint);
      parcels[i].setXY2D(xLeft, yTop);
      parcels[i].draw(g2);
    }


    //orthogonal basis need to be removed
    double[][] basis = {{300,0,0},{0,300,0},{0,0,300}, {0,0,0}};
    double[][] basisRep = proj2D(basis, projPoint);
    Point2D.Double point0 = new Point2D.Double((xLeft + basisRep[0][3]), (yTop + basisRep[1][3]));
    Ellipse2D.Double origin = new Ellipse2D.Double((xLeft + basisRep[0][3]-5), (yTop + basisRep[1][3]-5), 10, 10);
    g2.draw(origin);

    System.out.println(""+ (xLeft + basisRep[0][3]) + "  " + (yTop + basisRep[1][3]));
    for (int i = 0; i < basis.length-1; i++)
    {
      Point2D.Double point2 = new Point2D.Double((xLeft + basisRep[0][i]), (yTop + basisRep[1][i]));
      System.out.println(""+ (xLeft + basisRep[0][i]) + "  " + (yTop + basisRep[1][i]));
      Line2D.Double line = new Line2D.Double(point0, point2);
      Ellipse2D.Double mark = new Ellipse2D.Double((xLeft + basisRep[0][i]-2), (yTop + basisRep[1][i]-2), 4, 4);
      //    g2.setColor(Color.black);
      g2.setColor(colors[i]);
      g2.draw(line);
      g2.draw(mark);
    }
  }

  public static double[][] proj2D(double[][] m3D, double[] projCenter)
  {
    double[][] coordsForProj = new double[m3D[0].length + 1][m3D.length];
    for(int i = 0; i < m3D.length; i++)
      for(int j = 0; j < m3D[0].length; j++)
        coordsForProj[j][i] = m3D[i][j];
    for(int i = 0; i < m3D.length; i++)
      coordsForProj[3][i] = 1;
    printArray(coordsForProj);
    //x1 x2 x3
    //y1 y2 y3
    //z1 z2 z3
    //1  1  1
    double[][] m2D = new double[2][m3D.length];


    //  double deltaX = -projCenter[0];
    //  double deltaY = -projCenter[1];
    //  double deltaZ = -projCenter[2];
    // what  works
    //point of projection : (100,0,0)  (0,100,0)  (0,0,100)
    //deltas: PI/2,0,0                    OK
    //        0,PI/2,0        OK
    //        0,0,PI/2                              OK

//PI - acos(((-projCenter[0])*100 + (-projCenter[1])*0 + (-projCenter[2])*0)/(sqrt((-projCenter[0])*(-projCenter[0]) + (-projCenter[1])*(-projCenter[1]) + (-projCenter[2])*(-projCenter[2]))*100))

    double deltaX = PI - asin(((-projCenter[1])*100)/(sqrt((-projCenter[0])*(-projCenter[0]) + (-projCenter[1])*(-projCenter[1]) + (-projCenter[2])*(-projCenter[2]))*100));
    double deltaY = PI - asin(((-projCenter[0])*100 + (-projCenter[1])*0 + (-projCenter[2])*0)/(sqrt((-projCenter[0])*(-projCenter[0]) + (-projCenter[1])*(-projCenter[1]) + (-projCenter[2])*(-projCenter[2]))*100));
    double deltaZ = 0;
    //    double deltaX = PI/4;
  //  double deltaY = 0;
    //double deltaZ = 0;


    double cX = Math.cos(deltaX);
    double cY = Math.cos(deltaY);
    double cZ = Math.cos(deltaZ);

    double sX = Math.sin(deltaX);
    double sY = Math.sin(deltaY);
    double sZ = Math.sin(deltaZ);

    System.out.println(deltaX + "  " + deltaY + "  " + deltaZ);
    System.out.println("PI/2 = " + PI/2 + "PI/4 = " + PI/4);
    for(int k = 0; k < m3D.length; k++)
    {
      //the point if the center of projection was 0, if 0,0,0 is the eye
      double X = coordsForProj[0][k] - projCenter[0];
      double Y = coordsForProj[1][k] - projCenter[1];
      double Z = coordsForProj[2][k] - projCenter[2];


      double dX = cY*(sZ*Y + cZ*X) - sY*Z;
      double dY = sX*(cY*Z + sY*(sZ*Y + cZ*X)) + cX*(cZ*Y - sZ*X);
      double dZ = cX*(cY*Z + sY*(sZ*Y + cZ*X)) - sX*(cZ*Y - sZ*X);

      m2D[0][k] = (dX*400)/(dZ*400)*200;
      m2D[1][k] = (dY*400)/(dZ*400)*200;

  //    double[][] projMat = {  {1, 0, -(eX/eZ), 0},
  //                            {0, 1, -(eY/eZ), 0},
  //                            {0, 0, 1, 0},
  //                            {0, 0, -(1/eZ), 1}
  //                         };
      //printArraY(projMat);

      //false here
    //  double [][] matProj = {{eX},{eY},{eZ},{1}};
    //  double[][] mat2 = multOfMatrices(projMat, matProj);
    //  printArray(multOfMatrices(projMat, matProj));
    //  m2D[0][k] = mat2[0][0]/mat2[3][0];
    //  m2D[1][k] = mat2[1][0]/mat2[3][0];
/*
    double cosX = Math.cos(eX);
    double cosY = Math.cos(eY);
    double cosZ = Math.cos(eZ);
    double sinX = Math.sin(eX);
    double sinY = Math.sin(eY);
    double sinZ = Math.sin(eZ);
    //---
    //The position of point A with respect to a coordinate system defined by the camera, with origin in C and rotated by Theta with respect to the initial coordinate system.
    double dX = ((cosY*sinZ*eY) + (cosY*cosZ*eX)) - (sinY * eZ);
double dY = ((sinX*cosY*eZ) + (sinX*sinY*sinZ*eY) + (sinX*sinY*cosZ*eX)) + ((cosX*cosZ*eY) - (cosX*sinZ*eX));
double dZ = ((cosX*cosY*eZ) + (cosX*sinY*sinZ*eY) + (cosX*sinY*cosZ*eX)) - ((sinX*cosZ*eY) - (sinX*sinZ*eX));
    //---
      m2D[0][k] = (int)(((eZ / dZ) * dX) - eX);
      m2D[1][k] = (int)(((eZ / dZ) * dY) - eY);
*/



    }
    return m2D;
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
