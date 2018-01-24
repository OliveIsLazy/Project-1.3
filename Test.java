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
  private double totalValue;
  private double totalVolume;


  public static void main(String[] args)
  {
    Test t = new Test();
    t.start();
  }
  public void start()
  {
    //Z coordinate of the camera
    double projPoint = 400.0;

    //panel with all the drawings
    panelDraw = new JPanel(new BorderLayout());
    //list with the set of parcel that are draw
    parcels = new ArrayList<Parcel3D>();
    //the drawer itself
    drawer = new Drawer(parcels, projPoint);
    panelDraw.add(drawer);

    //initialisation of the frame
    JFrame frame = new JFrame();
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 800;
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setTitle("3D visualisation of the filling of the container");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel finalPanel = new JPanel();
    finalPanel.setLayout(new BorderLayout());

    //panel with the GUI
    JPanel panelInterface = new JPanel(new GridLayout(4, 0));

    JComboBox whichAlgo = new JComboBox();
    whichAlgo.addItem("Greedy");
    whichAlgo.addItem("Back-tracking");
    whichAlgo.addItem("Dynamic");
    whichAlgo.addItem("Exemple");


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
            Greedy();
            totalValueText.setText("Total value :" + "\n" + totalValue);
            fillingRateText.setText("Filled volume :" + totalVolume/(4*4*4) + "");
          }
          else if ((String) whichAlgo.getSelectedItem() == "Back-tracking")
          {
            System.out.println("Back-tracking");
            BackTr();
          }
          else if ((String) whichAlgo.getSelectedItem() == "Dynamic")
          {
            System.out.println("Dynamic");
            DynAl();
            totalValueText.setText("Total value :" + "\n" + 0.3);
            fillingRateText.setText("Filling rate :" + 0.3 + "%");
          }
          else if ((String) whichAlgo.getSelectedItem() == "Exemple")
          {
            System.out.println("Exemple");
            exemple();
          }

          Parcel3D[] parcelsArray = new Parcel3D[parcels.size()];
          for(int i = 0; i < parcels.size(); i++)
            parcelsArray[i] = parcels.get(i);
          drawer.update(parcelsArray);
      }
    }

    // start

    // end

    ActionListener calculate = new ClickListener();

    JButton calcButton = new JButton("Calculate");
    calcButton.addActionListener(calculate);

    panelInterface.add(whichAlgo);
    panelInterface.add(panelValues);
    panelInterface.add(results);
    panelInterface.add(calcButton);

    finalPanel.add(panelDraw, BorderLayout.CENTER);
    finalPanel.add(panelInterface, BorderLayout.LINE_END);

    frame.add(finalPanel);

    frame.setVisible(true);
  }
  public ArrayList<Parcel3D> transfer(Parcel3D[] l1)
  {
    ArrayList<Parcel3D> l2 = new ArrayList<Parcel3D>();
    for (int i=0; i<l1.length; i++)
        l2.add(l1[i]);
    return l2;
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

  public void Greedy()
  {
    GreedyFinal greedy = new GreedyFinal("parcel" , "value");
    greedy.start();
    ArrayList<Parcel3D> greedyList = new ArrayList<Parcel3D>();
    Parcel3D cargo = new Container();
    cargo.setPlace(0,0,0);
    greedyList.add(cargo);
    for (int i = 0; greedy.getParcelList(i) != null; i++)
    {
      Parcel temp = greedy.getParcelList(i);
      Parcel3D temp3D = new Parcel3D(temp.getName());
      temp3D.setPlace(greedy.getFirstCoordinate(i, 2)*2-cargo.getDim()[1]/2-25,greedy.getFirstCoordinate(i, 1)*2-cargo.getDim()[1]/2+2, greedy.getFirstCoordinate(i, 0)*2-cargo.getDim()[2]/2+2);
      greedyList.add(temp3D);
    }
    totalValue = greedy.getFilledValue();
    totalVolume = greedy.getFilledVolume();

    parcels = greedyList;
  }

  public void BackTr()
  {
    Container container = new Container();
    GenerateParcelList generator = new GenerateParcelList(1, "random");
    Parcel3D[] parcelsList = generator.getList();
    System.out.println("Parcels list: ");
    generator.print();
    BasicAlgorithm myAlgorithm = new BasicAlgorithm(container, parcelsList);

    // max value
    //double x = myAlgorithm.maximize("value");
    //System.out.println("Result value: " + x);
    //parcels = myAlgorithm.getContainer().getFilledParcels();
    //parcels = myAlgorithm.getList();
    //parcels = transfer(parcelsList);
    printArray(parcelsList[0].getCoords());

  }

  public void DynAl()
  {

  }
  public void exemple()
  {
    ArrayList<Parcel3D> explList = new ArrayList<Parcel3D>();
    explList.add(new Parcel3D("A"));
    explList.add(new Parcel3D("C"));
    //explList.add(new Parcel3D("Cargo"));
    Parcel3D cargo = new Container();
    cargo.setPlace(0,0,0);
    explList.add(cargo);
    parcels = explList;

  }
}
