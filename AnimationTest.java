/*
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

final public class AnimationTest
{

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 7;
    private int oneY = 7;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;

    public static void main(String... args)
    {
        new AnimationTest().go();
    }

    private void go()
    {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        moveIt();
    }

    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.RED);
            g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
            g.setColor(Color.WHITE);
            g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
            g.setColor(Color.BLACK);
            g.fillRect(oneX, oneY, 6, 6);
        }
    }

    private void moveIt()
    {
        while (true)
        {
            if (oneX >= 283)
            {
                right = false;
                left = true;
            }
            if (oneX <= 7)
            {
                right = true;
                left = false;
            }
            if (oneY >= 259)
            {
                up = true;
                down = false;
            }
            if (oneY <= 7)
            {
                up = false;
                down = true;
            }
            if (up) oneY--;
            if (down) oneY++;
            if (left) oneX--;
            if (right) oneX++;
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}
*/
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.util.Scanner;


public class AnimationTest extends JComponent
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 600;

    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setTitle("parcel ?");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Parcel parcelTest = new Parcel(2);
  //  Vector parcelTest = new Vector(200, 200, 200, 400, 400, 400);
    frame.add(parcelTest);

    frame.setVisible(true);
  }



}
