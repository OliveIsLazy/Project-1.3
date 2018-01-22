import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.lang.Math;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Polygon;
import static java.lang.Math.*;

public class Parcel3D extends JComponent
{
  // the 3D array with the coordonnes of the points of every shape of Parcel that we're going to use.
  private double[][][] coordOfAll =
    {
      //0 A
      { {-1, 0.5, 0.5}, {-1, -0.5, 0.5}, {-1, -0.5, -0.5}, {-1, 0.5, -0.5}, {1, 0.5, 0.5}, {1, -0.5, 0.5}, {1, -0.5,- 0.5}, {1, 0.5, -0.5}, {0,0,0}},
      //1 B
      { {-1, 0.75, 0.5}, {-1, -0.75, 0.5}, {-1, -0.75, -0.5}, {-1, 0.75, -0.5}, {1, 0.75, 0.5}, {1, -0.75, 0.5}, {1, -0.75, -0.5}, {1, 0.75, -0.5}, {0,0,0}},
      //2 C
      { {-0.75, 0.75, 0.75}, {-0.75, -0.75, 0.75}, {-0.75, -0.75, -0.75}, {-0.75, 0.75, -0.75}, {0.75, 0.75, 0.75}, {0.75, -0.75, 0.75}, {0.75, -0.75, -0.75}, {0.75, 0.75, -0.75}, {0,0,0}},
      { {-(16.5/2), (2.5/2), 2}, {-(16.5/2), -(2.5/2), 2}, {-(16.5/2), -(2.5/2), -2}, {-(16.5/2), (2.5/2), -2}, {(16.5/2), (2.5/2), 2}, {(16.5/2), -(2.5/2), 2}, {(16.5/2), -(2.5/2), -2}, {(16.5/2), (2.5/2), -2}, {0,0,0}}
    };
  // the coords of the Parcel3D that we use are initialised, without any values yet
  private double[][] coords = new double[9][3];
  // the colors of each parcel (for more visibility)
  private Color[] colors = {Color.blue, Color.green, Color.red, Color.BLACK};
  // initialisation of the color of the parcel that we're going to use
  private Color color;
  //to know wich on we are using
  private int wichOne;
  // to define the place of the origin (0,0,0)
  private double xLeft;
  private double yTop;
  //to facilte fisibility
  private int scale = 4;

  private double[] projPoint;

  private double[] values = {3, 4, 5, 0};
  private double value;
  private String[] names = {"A", "B", "C", "Cargo"};
  private String name;

  //constructor
  public Parcel3D(String wichOne)
  {
    if (wichOne.equals("A")) this.wichOne = 0;
    else if (wichOne.equals("B")) this.wichOne = 1;
    else if (wichOne.equals("C")) this.wichOne = 2;
    else if (wichOne.equals("Cargo")) this.wichOne = 3;
    else System.out.println("wrong type of Parcel");

    color = colors[this.wichOne];
    for (int i = 0; i < coordOfAll[0].length ; i++)
        for (int j = 0; j < 3; ++j)
            coords[i][j] = coordOfAll[this.wichOne][i][j]*scale;
    value = values[this.wichOne];
    name = names[this.wichOne];
  }

  public Parcel3D(int wichOne)
  {
    this.wichOne = wichOne;
    color = colors[this.wichOne];
    for (int i = 0; i < coordOfAll[0].length ; i++)
        for (int j = 0; j < 3; ++j)
            coords[i][j] = coordOfAll[this.wichOne][i][j]*scale;
    value = values[this.wichOne];
    name = names[this.wichOne];
  }

//
  public void setPlace(double newx, double newy, double newz)
  {
    for(int i = 0; i<coordOfAll[0].length; i++)
    {
  		coords[i][0] = (coordOfAll[wichOne][i][0])*scale + newx;
  		coords[i][1] = (coordOfAll[wichOne][i][1])*scale + newy;
      coords[i][2] = (coordOfAll[wichOne][i][2])*scale + newz;
  	}
  }

  public void translationsX(int addx)
  {
    for(int i = 0; i<coords.length; i++)
      coords[i][0] += addx*scale;
  }
  public void translationsY(int addy)
  {
    for(int i = 0; i<coords.length; i++)
      coords[i][1] += addy*scale;
  }
  public void translationsZ(int addz)
  {
    for(int i = 0; i<coords.length; i++)
      coords[i][1] += addz*scale;
  }

  public Color getColor()
  {
    return color;
  }

  public void setX(int n, int value)
  {
    coords[n][0] = value;
  }

  public void setY(int n, int value)
  {
    coords[n][1] = value;
  }

  public void setZ(int n, int value)
  {
    coords[n][2] = value;
  }

  public void setProjPoint(double[] projp)
  {
    projPoint = projp;
  }

  public void setXY2D(int x, int y)
  {
    xLeft = x;
    yTop = y;
  }

/*
  public void setCoords(int x, int y, int value)
  {
    return coords;
  }
*/
  public void setColor(Color c)
  {
    color = c;
  }

  public double[][] getCoords()
  {
    return coords;
  }

  public int getWichOne()
  {
    return wichOne;
  }

  public double getValue() {
        return value;
    }

  public String getName() {
          return name;
  }

  public int getScale() {
            return scale;
  }

  public double[] getDim()
  {
      double[] x = {abs(coordOfAll[wichOne][0][0]*2*scale), abs(coordOfAll[wichOne][0][1]*2*scale), abs(coordOfAll[wichOne][0][2]*2*scale)};
      return x;
  }

  public void draw(Graphics2D g2)
  {

    double[][] plan = proj2D(coords, projPoint);

    //links between the points to draw the parcells
    int[][] link = {{1,2},{1,4},{1,5},{2,3},{2,6},{3,4},{3,7},{4,8},{5,6},{6,7},{7,8},{8,5}};
    for (int i = 0; i < link.length; i++)
    {
      Point2D.Double point1 = new Point2D.Double((xLeft + plan[0][link[i][0]-1]), (yTop + plan[1][link[i][0]-1]));
      Point2D.Double point2 = new Point2D.Double((xLeft + plan[0][link[i][1]-1]), (yTop + plan[1][link[i][1]-1]));
      Line2D.Double line = new Line2D.Double(point1, point2);
      g2.draw(line);
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

    double deltaX = PI - asin((-projCenter[1])/sqrt((-projCenter[0])*(-projCenter[0]) + (-projCenter[1])*(-projCenter[1]) + (-projCenter[2])*(-projCenter[2])));
    double deltaY = PI - asin((-projCenter[0])/sqrt((-projCenter[0])*(-projCenter[0]) + (-projCenter[1])*(-projCenter[1]) + (-projCenter[2])*(-projCenter[2])));
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


      //double dX = cY*(sZ*Y + cZ*X) - sY*Z;
      //double dY = sX*(cY*Z + sY*(sZ*Y + cZ*X)) + cX*(cZ*Y - sZ*X);
      //double dZ = cX*(cY*Z + sY*(sZ*Y + cZ*X)) - sX*(cZ*Y - sZ*X);

      double[][] mat1 = { {1,0,0},
                          {0,cX,sX},
                          {0,-sX,cX}};

      double[][] mat2 = { {cY,0,-sY},
                          {0,1,0},
                          {sY,0,cY}};

      double[][] mat3 = { {cZ,sZ,0},
                          {-sZ,cZ,0},
                          {0,0,1}};

      double[][] mat4 = {{X},{Y},{Z}};

      double[][] ds = multOfMatrices(multOfMatrices(multOfMatrices(mat1,mat2),mat3),mat4);

      double dX = ds[0][0];
      double dY = ds[1][0];
      double dZ = ds[2][0];

      m2D[0][k] = (dX*400)/(dZ*400)*500;
      m2D[1][k] = (dY*400)/(dZ*400)*500;

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

/*
  public void rotateZ(String sens, char axe)
  {
    //store the old x coords
    double[] place = {coords[coords.length-1][0] - coordOfAll[wichOne][coordOfAll.length-1][0], coords[coords.length-1][1] - coordOfAll[wichOne][coordOfAll.length-1][1], coords[coords.length-1][2] - coordOfAll[wichOne][coordOfAll.length-1][2]};
    double[] oldCoordsX = new double[coods[0].length];
    for (int i = 0; i < 5; i++)
      oldCoordsX[i] = this.getCoords()[i][0] - place[0];
    //store the old y coords
    int[] oldCoordsY = new int[5];
    for (int i = 0; i < 5; i++)
      oldCoordsY[i] = this.getCoords()[i][1] - place[1];
  if (sens == "right")
    {
      for (int i = 0; i < 5 ; i++)
      {
        coords[i][0] = oldCoordsY[i] + place[0];
        coords[i][1] = - oldCoordsX[i] + place[1];
      }
    }
    if (sens == "left")
    {
      for (int i = 0; i < 5 ; i++)
      {
        checkCoords[i][0] = - oldCoordsY[i] + place[0];
        checkCoords[i][1] = oldCoordsX[i] + place[1];
      }
    }
  }*/
}