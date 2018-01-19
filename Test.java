import java.awt.geom.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.Rectangle;
import java.util.Scanner;


public class Test
{
  public static void main(String[] args)
  {
    double[] projPoint = {300.0, 300.0, 300.0};

    JFrame frame = new JFrame();
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 800;
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setTitle("parcel ?");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new BorderLayout());
    JPanel panel2 = new JPanel(new BorderLayout());

    Parcel3D cargo = new Parcel3D(3);
    Parcel3D parcelTest = new Parcel3D(2);

    Parcel3D[] parcels = {cargo, parcelTest};
    Drawer drawer = new Drawer(parcels, projPoint);

    panel.add(drawer);

    JPanel finalPanel = new JPanel();
    finalPanel.setLayout(new GridLayout(1, 3));
    finalPanel.add(panel);
    //finalPanel.add(panel2);
    frame.add(finalPanel);

    frame.setVisible(true);
  }
}
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
/*
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.lang.Math.*;
import javax.swing.*;

public class AnimationTest extends JPanel {
double[][] nodes = { {-1, 0.5, 0.5}, {-1, -0.5, 0.5}, {-1, 0.5, -0.5}, {-1, -0.5, -0.5}, {1, 0.5, 0.5}, {1, -0.5, 0.5}, {1, 0.5, -0.5}, {1, -0.5,- 0.5}};

//conectios between points
int[][] edges = {{0, 1}, {1, 3}, {3, 2}, {2, 0}, {4, 5}, {5, 7}, {7, 6},
{6, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};

public AnimationTest() {
setPreferredSize(new Dimension(640, 640));
setBackground(Color.white);

scale(70);
//rotateCube(PI / 4, atan(sqrt(2)));

new Timer(500, (ActionEvent e) -> {
rotateCube(PI / 2, 0);
repaint();
}).start();
}

final void scale(double s) {
for (double[] node : nodes) {
node[0] *= s;
node[1] *= s;
node[2] *= s;
}
}

final void rotateCube(double angleX, double angleY) {
double sinX = sin(angleX);
double cosX = cos(angleX);

double sinY = sin(angleY);
double cosY = cos(angleY);

for (double[] node : nodes) {
double x = node[0];
double y = node[1];
double z = node[2];

node[0] = x * cosX - z * sinX;
node[2] = z * cosX + x * sinX;

z = node[2];

node[1] = y * cosY - z * sinY;
node[2] = z * cosY + y * sinY;
}
}

void drawCube(Graphics2D g) {
g.translate(getWidth() / 2, getHeight() / 2);

for (int[] edge : edges) {
double[] xy1 = nodes[edge[0]];
double[] xy2 = nodes[edge[1]];
g.drawLine((int) round(xy1[0]), (int) round(xy1[1]),
(int) round(xy2[0]), (int) round(xy2[1]));
}

for (double[] node : nodes)
g.fillOval((int) round(node[0]) - 4, (int) round(node[1]) - 4, 8, 8);
}

@Override
public void paintComponent(Graphics gg) {
super.paintComponent(gg);
Graphics2D g = (Graphics2D) gg;
g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);

drawCube(g);
}

public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
JFrame f = new JFrame();
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setTitle("Rotating Cube");
f.setResizable(false);
f.add(new AnimationTest(), BorderLayout.CENTER);
f.pack();
f.setLocationRelativeTo(null);
f.setVisible(true);
});
}
}
*/
