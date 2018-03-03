package treatment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Station {
	private ArrayList<Point> points;
	private CopyOnWriteArrayList<Route> routes;
	
	public Station(String filename)
	{
		points = new ArrayList<>();
		routes = new CopyOnWriteArrayList<>();
		
		try{
	        Scanner reader = new Scanner(new FileInputStream("ressources/"+filename+".txt"));
	        
	        int numberOfPoints = Integer.parseInt(reader.next());


	        for (int i = 0; i<numberOfPoints; i++)
	        {
	        	reader.nextLine();
	        	Point p = new Point(Integer.parseInt(reader.next()), reader.next(), Integer.parseInt(reader.next()));
	        	points.add(p);
	        }
	        
	        reader.nextLine();
	        int numberOfRoutes = Integer.parseInt(reader.next());
	        for (int i = 0; i<numberOfRoutes; i++)
	        {
	        	reader.nextLine();
	        	Route r = new Route(Integer.parseInt(reader.next()), reader.next(), reader.next(), getPointByNumber(Integer.parseInt(reader.next())), getPointByNumber(Integer.parseInt(reader.next())));
	        	routes.add(r);
	        	r.getStart().addSuccessor(r.getArrival());
	        }

            reader.close();
	    }
	    catch(FileNotFoundException e)
	    {
	    	System.out.println("Error: no file found");
	    }
	}
	
	public Station(Station s)
	{
		points = new ArrayList<>();
		routes = new CopyOnWriteArrayList<>();
		points.addAll(s.points);
		routes.addAll(s.routes);
	}
	
	public Point getPointByNumber(int n)
	{
		Point returningPoint = new Point();
		for(Point p : points)
		{
			if(p.getNumber() == n)
			{
				returningPoint = p;
			}
		}
		return returningPoint;
	}
	
	public ArrayList<Point> getPoints()
	{
		return points;
	}
	
	public CopyOnWriteArrayList<Route> getRoutes()
	{
		return routes;
	}
}
