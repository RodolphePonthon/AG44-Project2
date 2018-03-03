package graphical_interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import treatment.Station;
import treatment.Point;
import treatment.Route;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;

public class GFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -8123406571694511514L;
	private JPanel contentPane;
	private JComboBox comboBox;//comboBox of the starting points
	private JComboBox comboBox_1;//comboBox of the arrival points
	private JSplitPane splitPane;//splitPane of the graph
	private mxGraph myStation;
	private JLabel tTD;//label time to destination
	private JTextPane textPane;
	private JCheckBox checkBox, checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6, checkBox_7, checkBox_8, checkBox_9, checkBox_10, checkBox_11;
	private boolean hasCheckBoxChanged = false;
	private boolean[] isChckBxActive;
	private final int HEIGHT = 768;
	private final int WIDTH = 1366;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public GFrame(Station s) {
		
		isChckBxActive = new boolean[12];
		for(int i = 0 ; i < isChckBxActive.length ; i++)
		{
			isChckBxActive[i] = true;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(HEIGHT/8);
		

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setEnabled(false);
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblFindTheShortest = new JLabel("SHORTEST PATH BETWEEN :");
		lblFindTheShortest.setBounds(20, 11, 186, 20);
		panel.add(lblFindTheShortest);
		
		JLabel lblStartingPoint = new JLabel("Starting Point :");
		lblStartingPoint.setBounds(10, 42, 95, 20);
		panel.add(lblStartingPoint);
		
		JLabel lblArrivalPoint = new JLabel("Arrival Point :");
		lblArrivalPoint.setBounds(312, 42, 95, 20);
		panel.add(lblArrivalPoint);
		
		comboBox = new JComboBox();
		comboBox.setBounds(115, 42, 160, 20);
		panel.add(comboBox);
		
		for(Point p : s.getPoints())
		{
			comboBox.addItem(p);
		}
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(417, 42, 160, 20);
		panel.add(comboBox_1);
		
		JLabel lblTimeToDestination = new JLabel("Time to destination :");
		lblTimeToDestination.setBounds(10, 65, 123, 20);
		panel.add(lblTimeToDestination);
		
		tTD = new JLabel("0");
		tTD.setBounds(143, 65, 50, 20);
		panel.add(tTD);
		
		JLabel lblMinutes = new JLabel("minutes");
		lblMinutes.setBounds(203, 65, 72, 20);
		panel.add(lblMinutes);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(255, 204, 102));
		textField_1.setBounds(10, 11, 196, 20);
		panel.add(textField_1);
		
		for(Point p : s.getPoints())
		{
			comboBox_1.addItem(p);
		}
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		checkBox = new JCheckBox("V");
		checkBox.setSelected(true);
		checkBox.setBounds(6, 7, 70, 23);
		checkBox.addActionListener(this);
		panel_1.add(checkBox);
		
		checkBox_1 = new JCheckBox("B");
		checkBox_1.setSelected(true);
		checkBox_1.setBounds(6, 33, 70, 23);
		checkBox_1.addActionListener(this);
		panel_1.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("R");
		checkBox_2.setSelected(true);
		checkBox_2.setBounds(6, 59, 70, 23);
		checkBox_2.addActionListener(this);
		panel_1.add(checkBox_2);
		
		checkBox_3 = new JCheckBox("SURF");
		checkBox_3.setSelected(true);
		checkBox_3.setBounds(78, 59, 70, 23);
		checkBox_3.addActionListener(this);
		panel_1.add(checkBox_3);
		
		checkBox_4 = new JCheckBox("KL");
		checkBox_4.setSelected(true);
		checkBox_4.setBounds(78, 33, 70, 23);
		checkBox_4.addActionListener(this);
		panel_1.add(checkBox_4);
		
		checkBox_5 = new JCheckBox("N");
		checkBox_5.setSelected(true);
		checkBox_5.setBounds(78, 7, 70, 23);
		checkBox_5.addActionListener(this);
		panel_1.add(checkBox_5);
		
		checkBox_6 = new JCheckBox("TPH");
		checkBox_6.setSelected(true);
		checkBox_6.setBounds(150, 7, 70, 23);
		checkBox_6.addActionListener(this);
		panel_1.add(checkBox_6);
		
		checkBox_7 = new JCheckBox("TC");
		checkBox_7.setSelected(true);
		checkBox_7.setBounds(150, 33, 70, 23);
		checkBox_7.addActionListener(this);
		panel_1.add(checkBox_7);
		
		checkBox_8 = new JCheckBox("TSD");
		checkBox_8.setSelected(true);
		checkBox_8.setBounds(150, 59, 70, 23);
		checkBox_8.addActionListener(this);
		panel_1.add(checkBox_8);
		
		checkBox_9 = new JCheckBox("BUS");
		checkBox_9.setSelected(true);
		checkBox_9.setBounds(222, 59, 70, 23);
		checkBox_9.addActionListener(this);
		panel_1.add(checkBox_9);
		
		checkBox_10 = new JCheckBox("TK");
		checkBox_10.setSelected(true);
		checkBox_10.setBounds(222, 33, 70, 23);
		checkBox_10.addActionListener(this);
		panel_1.add(checkBox_10);
		
		checkBox_11 = new JCheckBox("TS");
		checkBox_11.setSelected(true);
		checkBox_11.setBounds(222, 7, 70, 23);
		checkBox_11.addActionListener(this);
		panel_1.add(checkBox_11);
		
		JLabel lblReachableDestinations = new JLabel("Reachable Points :");
		lblReachableDestinations.setBounds(330, 10, 115, 20);
		panel_1.add(lblReachableDestinations);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(455, 10, 184, 72);
		panel_1.add(jsp);
		
		textField = new JTextField();
		textField.setBackground(new Color(204, 102, 204));
		textField.setEditable(false);
		textField.setBounds(320, 11, 125, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		splitPane_1.setDividerLocation(WIDTH/2);
		
		myStation = printAndCreateMxGraph(splitPane, s);
	}
	
	public mxGraph printAndCreateMxGraph(JSplitPane splitPane, Station s)
	{
		double [][] coords = getPointsCoords();
		
		mxGraph myStation = new mxGraph();
		Object parent = myStation.getDefaultParent();
		
		myStation.getModel().beginUpdate();
		
		try{
			ArrayList<Object> objectList = new ArrayList<Object>();
			for(Point p : s.getPoints())
			{
				objectList.add(myStation.insertVertex(parent, null, p.getName(), coords[p.getNumber()-1][0], coords[p.getNumber()-1][1], 80, 30));
			}
			for(Route r : s.getRoutes())
			{
				myStation.insertEdge(parent, null, r.getType(), objectList.get(r.getStart().getNumber()-1), objectList.get(r.getArrival().getNumber()-1));
			}
		} finally {
		      myStation.getModel().endUpdate();
	    }
		
		mxGraphComponent graphComponent = new mxGraphComponent(myStation);
		splitPane.setRightComponent(graphComponent);
	    mxParallelEdgeLayout layout = new mxParallelEdgeLayout(myStation);
        layout.execute(myStation.getDefaultParent());
        
        return myStation;
	}
	
	public Point getStartingPoint()
	{
		return (Point)(comboBox.getSelectedItem());
	}
	
	public Point getArrivalPoint()
	{
		return (Point)(comboBox_1.getSelectedItem());
	}
	
	public JSplitPane getGraphSplitPane()
	{
		return splitPane;
	}
	
	public mxGraph getMxGraph()
	{
		return myStation;
	}
	
	public void updateGraph(ArrayList<Integer> pSPath, ArrayList<Route> eSP, ArrayList<Float> weight, Station s)
	{
		//splitPane.remove(splitPane.getRightComponent()); //used to avoid the stack of images in the splitPane RightComponent
		
		double [][] coords = getPointsCoords();
		
		mxGraph myStation = new mxGraph();
		Object parent = myStation.getDefaultParent();
		
		myStation.getModel().beginUpdate();
		StyledDocument sDoc = (StyledDocument)textPane.getDocument();
		textPane.setText("");
		Style defaut = textPane.getStyle("default");
		
		float time = 0;
		for(Route r : eSP)
		{
			time += r.getTime();
		}

		tTD.setText(Float.toString(time));
		
		try{
			ArrayList<Object> objectList = new ArrayList<Object>();
			for(Point p : s.getPoints())
			{
				
				if(pSPath.contains(p.getNumber()))
				{
					objectList.add(myStation.insertVertex(parent, null, p.getName(), coords[p.getNumber()-1][0], coords[p.getNumber()-1][1], 80, 30, "fontColor=black;strokeColor=black;fillColor=#ffcc66"));
				}
				else if(weight.get(p.getNumber()-1) != Float.MAX_VALUE)
				{
					try {
						sDoc.insertString(sDoc.getLength(), p.getName()+"\n", defaut);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					objectList.add(myStation.insertVertex(parent, null, p.getName(), coords[p.getNumber()-1][0], coords[p.getNumber()-1][1], 80, 30, "fontColor=black;strokeColor=black;fillColor=#cc66cc"));
				}
				else
				{
					objectList.add(myStation.insertVertex(parent, null, p.getName(), coords[p.getNumber()-1][0], coords[p.getNumber()-1][1], 80, 30));
				}
			}
			for(Route r : s.getRoutes())
			{
				if(eSP.contains(r))
				{
					myStation.insertEdge(parent, null, r.getType(), objectList.get(r.getStart().getNumber()-1), objectList.get(r.getArrival().getNumber()-1), "fontColor=black;strokeColor=#f2b04f;fillColor=#ffcc66");
				}
				else
				{
					myStation.insertEdge(parent, null, r.getType(), objectList.get(r.getStart().getNumber()-1), objectList.get(r.getArrival().getNumber()-1));
				}
				
			}
		} finally {
		      myStation.getModel().endUpdate();
	    }

		mxGraphComponent graphComponent = new mxGraphComponent(myStation);
		splitPane.setRightComponent(graphComponent);
		splitPane.setDividerLocation(HEIGHT/8);
	    mxParallelEdgeLayout layout = new mxParallelEdgeLayout(myStation);
        layout.execute(myStation.getDefaultParent());
	}
	
	public boolean chckBxC()
	{
		return hasCheckBoxChanged;
	}
	
	public void setchckBxC(boolean b)
	{
		hasCheckBoxChanged = b;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object  source=e.getSource();
		
		if(source == checkBox)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[0] = !isChckBxActive[0];
		}
		else if(source == checkBox_1)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[1] = !isChckBxActive[1];
		}
		else if(source == checkBox_2)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[2] = !isChckBxActive[2];
		}
		else if(source == checkBox_3)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[3] = !isChckBxActive[3];
			System.out.println(""+isChckBxActive[3]);
		}
		else if(source == checkBox_4)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[4] = !isChckBxActive[4];
		}
		else if(source == checkBox_5)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[5] = !isChckBxActive[5];
		}
		else if(source == checkBox_6)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[6] = !isChckBxActive[6];
		}
		else if(source == checkBox_7)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[7] = !isChckBxActive[7];
		}
		else if(source == checkBox_8)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[8] = !isChckBxActive[8];
		}
		else if(source == checkBox_9)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[9] = !isChckBxActive[9];
		}
		else if(source == checkBox_10)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[10] = !isChckBxActive[10];
		}
		else if(source == checkBox_11)
		{
			hasCheckBoxChanged = true;
			isChckBxActive[11] = !isChckBxActive[11];
		}
	}
	
	public boolean[] getCheckBoxActivity()
	{
		return isChckBxActive;
	}
	
	public double[][] getPointsCoords()
	{
		double[][] coords = {
                {350, 850},//1
                {450, 780},//2
                {650, 740},//3
                {760, 650},//4
                {940,650},//5
                {820,550},//6
                {530, 470},//7
                {600, 580},//8
                {1000, 380},//9
                {600, 400},//10
                {820, 350},//11
                {970,480},//12
                {1100,100},//13
                {1000,150},//14
                {500, 350},//15
                {900,220},//16
                {500,530},//17
                {450,700},//18
                {400,600},//19
                {300,670},//20
                {320,520},//21
                {600,270},//22
                {600,200},//23
                {500,150},//24
                {820,40},//25
                {820,130},//26
                {150,150},//27
                {150,480},//28
                {100,380},//29
                {250,400},//30
                {350,300},//31
                {650,30},//32
                {400,50},//33
                {240,70},//34
                {100,250},//35
                {350,150},//36
                {500,100}
        };
		return coords;
	}
}
