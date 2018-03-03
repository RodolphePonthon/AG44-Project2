package main;

import treatment.Dijkstra;
import treatment.Point;
import treatment.Route;
import treatment.Station;
import graphical_interface.GFrame;

import java.util.ArrayList;

public class Main {
		public static void main(String[] args) throws InterruptedException {
		Station s = new Station("dataski");
		
	    GFrame frame = new GFrame(s);
	    frame.setVisible(true);
	    
	    while(true)
	    {
		    frame.repaint(); //refresh the frame
	    	Station s2 = new Station(s);
	    	
	    	
	    	for(int i = 0 ; i < frame.getCheckBoxActivity().length ; i++)
	    	{
	    		if(!frame.getCheckBoxActivity()[i])//if the type of route is disabled
	    		{
	    			for(Route r : s2.getRoutes())
	    			{
	    				switch(i)
	    				{
		    				case 0 :
		    					if(r.getType().equals("V"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 1 :
		    					if(r.getType().equals("B"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 2 :
		    					if(r.getType().equals("R"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 3 :
		    					if(r.getType().equals("SURF"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 4 :
		    					if(r.getType().equals("KL"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 5 :
		    					if(r.getType().equals("N"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 6 :
		    					if(r.getType().equals("TPH"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 7 :
		    					if(r.getType().equals("TC"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 8 :
		    					if(r.getType().equals("TSD"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 9 :
		    					if(r.getType().equals("BUS"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 10 :
		    					if(r.getType().equals("TK"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
		    				case 11 :
		    					if(r.getType().equals("TS"))
		    					{
		    						s2.getRoutes().remove(r);
		    					}
		    					break;
	    				}
	    			}
	    		}
	    	}
		    
		    frame.setchckBxC(false);
		    
		    Dijkstra dij = new Dijkstra(frame.getStartingPoint());
		    Dijkstra dij2 = new Dijkstra(frame.getStartingPoint());
		    dij2.launchDijkstra(s2);
		    ArrayList<Float> weight = dij2.getDistanceResp();
		    dij.launchDijkstra(s);
		    ArrayList<Integer> pointSPath = dij.returnPointPath(frame.getArrivalPoint());
		    ArrayList<Route> edgeSPath = dij.returnEdgePath(pointSPath, s);
		    
		    frame.updateGraph(pointSPath, edgeSPath, weight, s);
		    
		    System.out.println("Shortest path : ");
		    for(int i = 0; i < pointSPath.size(); i++) // printing the shortest path
		    {
		    	System.out.println(""+pointSPath.get(i));
		    }
		    
	    	Point pstmp = frame.getStartingPoint();
	    	Point patmp = frame.getArrivalPoint();
	    	
	    	while((frame.getStartingPoint().equals(pstmp) && frame.getArrivalPoint().equals(patmp)) && !frame.chckBxC()) //Tourne si il n'y a pas de changement de point d'arrivée ou de départ
	    	{
	    		pstmp = frame.getStartingPoint();
		    	patmp = frame.getArrivalPoint();
		    	Thread.sleep(1000);
	    	}
	    }
	    

	}
}
