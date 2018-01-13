import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.lang.Math;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Polygon;
import static java.lang.Math.*;

public class Parcel extends JComponent
{
  // the 3D array with the coordonnes of the points of every pentomino we're going to use.
  private double[][][] coordOfAll =
    {
      //0 A
      { {-1, 0.5, 0.5}, {-1, -0.5, 0.5}, {-1, -0.5, -0.5}, {-1, 0.5, -0.5}, {1, 0.5, 0.5}, {1, -0.5, 0.5}, {1, -0.5,- 0.5}, {1, 0.5, -0.5}, {0,0,0}},
      //1 B
      { {-1, 0.75, 0.5}, {-1, -0.75, 0.5}, {-1, -0.75, -0.5}, {-1, 0.75, -0.5}, {1, 0.75, 0.5}, {1, -0.75, 0.5}, {1, -0.75, -0.5}, {1, 0.75, -0.5}, {0,0,0}},
      //2 C
      { {-0.75, 0.75, 0.75}, {-0.75, -0.75, 0.75}, {-0.75, -0.75, -0.75}, {-0.75, 0.75, -0.75}, {0.75, 0.75, 0.75}, {0.75, -0.75, 0.75}, {0.75, -0.75, -0.75}, {0.75, 0.75, -0.75}, {0,0,0}}
    };
  // the coords of the Parcel that we use are initialised, without any values yet
  private double[][] coords = new double[9][3];
  // the colors of each Pent, im the order
  private Color[] colors = {Color.blue, Color.green, Color.red};
  // initialisation of the color of the Pent that we're going to use
  private Color color;
  //to know wich on we are using
  private int wichOne;
//  private int wichOne;
  private boolean rotatable;
  private double xLeft = 600;
  private double yTop = 500;
  private int scale = 70;

  //constructor
  public Parcel(int wichOne)
  {
    this.wichOne = wichOne;
    color = colors[wichOne];
    for (int i = 0; i < coordOfAll[0].length ; i++)
        for (int j = 0; j < 3; ++j)
            coords[i][j] = coordOfAll[wichOne][i][j]*scale;
  }

  public void setPlace(int startx, int starty)
  {
    for(int i = 0; i<coordOfAll[0].length; i++)
    {
  		coords[i][0] = coords[i][0]+ startx;
  		coords[i][1] = coords[i][1]+ starty;

  	}
  }
/*
  public void translationsX(int z)
  {
    for(int i = 0; i<coordOfAll[0].length; i++){
      coords[i][0] = coords[i][0]+ startx;
      coords[i][1] = coords[i][1]+ starty;
    }
  }
*/


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

  public void setRotatable(boolean a){
  	  rotatable = a;
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

  public void draw(Graphics2D g2)
  {
    /*
    boolean[][] links = { {false,true,true,false,true,false,false,false, false},
                          {true,false,false,true,false,true,false,false, false},
                          {true,false,false,true,false,false,true,false, false},
                          {false,true,true,false,false,false,false,true, false},
                          {true,false,false,false,false,true,true,false, false},
                          {false,true,false,false,true,false,false,true, false},
                          {false,false,true,false,true,false,false,true, false},
                          {false,false,false,true,false,true,true,false, false},
                          {false,false,false,false,false,false,false,false,false}
                        };

    for (int i = 0; i < plan[0].length; i++)
    {
      Point2D.Double point1 = new Point2D.Double((xLeft + plan[0][i]), (yTop + plan[1][i]));
      for(int j = 0; j < plan[0].length; j++)
      if (links[i][j] == true)
      {
        Point2D.Double point2 = new Point2D.Double((xLeft + plan[0][j]), (yTop + plan[1][j]));
        Line2D.Double line = new Line2D.Double(point1, point2);
        g2.draw(line);
      }
    }

    */
    double[] projPoint = {460.0, 750.0, 100.0};
    double[][] plan = proj2D(coords, projPoint);


    int[][] link = {{1,2},{1,4},{1,5},{2,3},{2,6},{3,4},{3,7},{4,8},{5,6},{6,7},{7,8},{8,5}};
    for (int i = 0; i < link.length; i++)
    {
      Point2D.Double point1 = new Point2D.Double((xLeft + plan[0][link[i][0]-1]), (yTop + plan[1][link[i][0]-1]));
      Point2D.Double point2 = new Point2D.Double((xLeft + plan[0][link[i][1]-1]), (yTop + plan[1][link[i][1]-1]));
      Line2D.Double line = new Line2D.Double(point1, point2);
      g2.draw(line);
    }

    double[][] basis = {{300,0,0},{0,300,0},{0,0,300}, {0,0,0}};
    double[][] basisRep = proj2D(basis, projPoint);
    Point2D.Double point0 = new Point2D.Double((xLeft + basisRep[0][3]), (yTop + basisRep[1][3]));
    System.out.println(""+ (xLeft + basisRep[0][3]) + "  " + (yTop + basisRep[1][3]));
    for (int i = 0; i < basis.length-1; i++)
    {
      Point2D.Double point2 = new Point2D.Double((xLeft + basisRep[0][i]), (yTop + basisRep[1][i]));
      System.out.println(""+ (xLeft + basisRep[0][i]) + "  " + (yTop + basisRep[1][i]));
      Line2D.Double line = new Line2D.Double(point0, point2);
      g2.draw(line);
    }
    // add links between point

  //  Rectangle2D.Double face1 = new Rectangle2D.Double(xLeft - coords[0][0]*scale, yTop - coords[0][2]*scale, Math.abs(coords[0][0]*scale*2), Math.abs(coords[0][2]*scale*2));
    //Rectangle2D.Double face2 = new Rectangle2D.Double(xLeft - coords[0][0]*scale - perspectiveX, yTop - coords[0][2]*scale - perspectiveY, Math.abs(coords[0][0]*scale*2), Math.abs(coords[0][2]*scale*2));

    //Point2D.Double point1 = new Point2D.Double(xLeft + plan[0][0], yTop - coords[0][2]);
    //Point2D.Double point2 = new Point2D.Double(xLeft + coords[0][0], yTop - coords[0][2]);
    //    Point2D.Double point3 = new Point2D.Double(xLeft, yTop);
    //    Line2D.Double line1 = new Line2D.Double(point1, point2);
    //    Line2D.Double line2 = new Line2D.Double(point2, point3);
    //    Line2D.Double line3 = new Line2D.Double(point3, point1);
//    g2.draw(face1);
  //  g2.draw(face2);
    //    g2.draw(line1);
    //    g2.draw(line2);
    //    g2.draw(line3);
    //    g2.draw(p);
  }
  public double[][] proj2D(double[][] m3D, double[] projCenter)
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


    double deltaX = PI/2 -acos((projCenter[0]*0 + projCenter[1]*100 + projCenter[2]*0)/(sqrt(projCenter[0]*projCenter[0] + projCenter[1]*projCenter[1] + projCenter[2]*projCenter[2])*100));
    double deltaY = PI/2 -acos((projCenter[0]*100 + projCenter[1]*0 + projCenter[2]*0)/(sqrt(projCenter[0]*projCenter[0] + projCenter[1]*projCenter[1] + projCenter[2]*projCenter[2])*100));
    double deltaZ = PI/2 -acos((projCenter[0]*0 + projCenter[1]*0 + projCenter[2]*100)/(sqrt(projCenter[0]*projCenter[0] + projCenter[1]*projCenter[1] + projCenter[2]*projCenter[2])*100));

    System.out.println(deltaX + "  " + deltaY + "  " + deltaZ);
    for(int k = 0; k < m3D.length; k++)
    {
      //the point if the center of projection was 0, if 0,0,0 is the eye
      double X = coordsForProj[0][k] - projCenter[0];
      double Y = coordsForProj[1][k] - projCenter[1];
      double Z = coordsForProj[2][k] - projCenter[2];


      double cX = Math.cos(deltaX);
      double cY = Math.cos(deltaY);
      double cZ = Math.cos(deltaZ);

      double sX = Math.sin(deltaX);
      double sY = Math.sin(deltaY);
      double sZ = Math.sin(deltaZ);

      double dX = cY*(sZ*Y + cZ*X) - sY*Z;
      double dY = sX*(cY*Z + sY*(sZ*Y + cZ*X)) + cX*(cZ*Y - sZ*X);
      double dZ = cX*(cY*Z + sY*(sZ*Y + cZ*X)) - sX*(cZ*Y - sZ*X);

      m2D[0][k] = (dX*400)/(dZ*400)*100;
      m2D[1][k] = (dY*400)/(dZ*400)*100;

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

  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;

    Parcel parcelTest = new Parcel(wichOne);

    parcelTest.draw(g2);
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
