import java.awt.geom.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.Rectangle;
import java.util.Scanner;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;


public class Test
{
  private ArrayList<Parcel3D> parcels;
  private Drawer drawer;
  private JPanel panelDraw;

  public static void main(String[] args)
  {
    Test t = new Test();
    t.start();
  }
  public void start()
  {
    double[] projPoint = {200.0, 300.0, 400.0};

    JFrame frame = new JFrame();
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 800;
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setTitle("parcel ?");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel panelInterface = new JPanel(new GridLayout(4, 0));

    JComboBox whichAlgo = new JComboBox();
    whichAlgo.addItem("Greedy");
    whichAlgo.addItem("Back-tracking");
    whichAlgo.addItem("Dynamic");

    JPanel panelValues  = new JPanel(new GridLayout(6,0));

    JTextField aValueText = new JTextField("Value of parcel A");
    aValueText.setEditable(false);
    JTextField bValueText = new JTextField("Value of parcel B");
    bValueText.setEditable(false);
    JTextField cValueText = new JTextField("Value of parcel C");
    cValueText.setEditable(false);

    NumberFormat valueFormat;
    valueFormat = NumberFormat.getNumberInstance();
    JFormattedTextField aValue = new JFormattedTextField(valueFormat);
    aValue.setEditable(true);
    JFormattedTextField bValue = new JFormattedTextField(valueFormat);
    bValue.setEditable(true);
    JFormattedTextField cValue = new JFormattedTextField(valueFormat);
    cValue.setEditable(true);

    panelValues.add(aValueText);
    panelValues.add(aValue);
    panelValues.add(bValueText);
    panelValues.add(bValue);
    panelValues.add(cValueText);
    panelValues.add(cValue);

    panelDraw = new JPanel(new BorderLayout());

    JPanel results = new JPanel();
    results.setLayout(new GridLayout(2, 0));

    JTextField totalValueText = new JTextField("Total value :" + "\n" + 0);
    totalValueText.setEditable(false);
    JTextField fillingRateText = new JTextField("Filling rate :" + 0 + "%");
    fillingRateText.setEditable(false);

    results.add(totalValueText);
    results.add(fillingRateText);

    class ClickListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
          if ((String) whichAlgo.getSelectedItem() == "Greedy")
          {
            System.out.println("Greedy");
            totalValueText.setText("Total value :" + "\n" + 0.1);
            fillingRateText.setText("Filling rate :" + 0.1 + "%");
          }
          else if ((String) whichAlgo.getSelectedItem() == "Back-tracking")
          {
            System.out.println("Back-tracking");

          }
          else if ((String) whichAlgo.getSelectedItem() == "Dynamic")
          {
            System.out.println("Dynamic");
            totalValueText.setText("Total value :" + "\n" + 0.3);
            fillingRateText.setText("Filling rate :" + 0.3 + "%");
          }
      }
    }

    // start
    Container container = new Container();
    GenerateParcelList generator = new GenerateParcelList(2, "random");
    Parcel3D[] parcelsList = generator.getList();
    System.out.println("Parcels list: ");
    generator.print();
    BasicAlgorithm myAlgorithm = new BasicAlgorithm(container, parcelsList);
    // max value
    //double x = myAlgorithm.maximize("value");
    //System.out.println("Result value: " + x);
    //parcels = myAlgorithm.getContainer().getFilledParcels();
    parcels = myAlgorithm.getList();
    drawer = new Drawer(parcels, projPoint);
    panelDraw.add(drawer);
    // end

    ActionListener calculate = new ClickListener();

    JButton calcButton = new JButton("Calculate");
    calcButton.addActionListener(calculate);

    panelInterface.add(whichAlgo);
    panelInterface.add(panelValues);
    panelInterface.add(results);
    panelInterface.add(calcButton);

    JPanel finalPanel = new JPanel();
    finalPanel.setLayout(new BorderLayout());
    finalPanel.add(panelDraw, BorderLayout.CENTER);
    finalPanel.add(panelInterface, BorderLayout.LINE_END);

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
