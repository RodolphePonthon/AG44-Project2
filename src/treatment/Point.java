package treatment;

import java.util.ArrayList;

public class Point {
	private int number;
	private String name;
	private int altitude;
	private ArrayList<Point> successors;
	
	public Point()
	{
		number = 0;
		name = null;
		altitude = 0;
		successors = new ArrayList<Point>();
	}
	
	public Point(int numb, String n, int alt)
	{
		number = numb;
		name = n;
		altitude = alt;
		successors = new ArrayList<Point>();
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public int getAltitude()
	{
		return altitude;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<Point> getSuccessors()
	{
		return successors;
	}
	
	public void addSuccessor(Point p)
	{
		successors.add(p);
	}
	
	public String toString()
	{
		return name;
	}
	
	public void setName(String s)
	{
		name = s;
	}
}
