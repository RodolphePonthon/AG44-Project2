package treatment;

import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
	
	private ArrayList<WeightPred> distanceResp;
	private Point start;
	
	public Dijkstra(Point point)
	{
		distanceResp = new ArrayList<>();
		start = point;
	}
	
	public void launchDijkstra(Station s)
	{
		for(Point point : s.getPoints())
		{
			WeightPred temp = new WeightPred();
			if (point.getNumber() == start.getNumber())
			{
				temp.setWeight(0);
				temp.setPred(start.getNumber());
			}
			else
			{
				temp.setWeight(Float.MAX_VALUE);
				temp.setPred(-1);
			}
			
			distanceResp.add(temp);
		}
		
		boolean modif = false;
		do
		{
			modif = false;
			for(Route r : s.getRoutes())
			{
				if(distanceResp.get((r.getStart().getNumber())-1).getWeight() != Float.MAX_VALUE)
				{
					float newWeight = distanceResp.get(r.getStart().getNumber()-1).getWeight() + r.getTime();
					if(newWeight < distanceResp.get(r.getArrival().getNumber()-1).getWeight())
					{
						distanceResp.get(r.getArrival().getNumber()-1).setWeight(newWeight);
						distanceResp.get(r.getArrival().getNumber()-1).setPred(r.getStart().getNumber());
						modif  = true;
					}
				}
			}
		}
		while(modif == true);
	}
	
	public ArrayList<Integer> returnPointPath(Point arrival) //Read distanceResp to build the shortest path
	{
		ArrayList<Integer> path = new ArrayList<>();
		if(distanceResp.get(arrival.getNumber()-1).getWeight() != Float.MAX_VALUE) //if the node is reachable (it means it doesn't have an infinite weight)
		{
			path.add(arrival.getNumber());
			
			if(start.getNumber() != arrival.getNumber())
			{
				int pred = distanceResp.get(arrival.getNumber()-1).getPred();
				while(pred != start.getNumber())
				{
					path.add(pred);
					pred = distanceResp.get(pred-1).getPred();
				}
				path.add(pred);
				Collections.reverse(path);
			}
			else
			{
				System.out.println("Error : the arrival is the same point as the departure !");
			}
		}
		else
		{
			path.add(start.getNumber());
			System.out.println("You can't reach this point from where you are !");
		}
		
		return path;
	}
	
	public ArrayList<Route> returnEdgePath(ArrayList<Integer> pointSPath, Station s)
	{
		ArrayList<Route> edgeSPath = new ArrayList<>();
		if(pointSPath.size() > 1)
		{
			for(int i = 0 ; i < (pointSPath.size()-1) ; i++)
			{
				for(Route r : s.getRoutes())
				{
					if(r.getStart().getNumber() == pointSPath.get(i) && r.getArrival().getNumber() == pointSPath.get(i+1))
					{
						if(edgeSPath.isEmpty())
						{
							edgeSPath.add(r);
						}
						else
						{
							int j = -1;
							
							for(Route ro : edgeSPath)
							{
								if(ro.getStart().getNumber() == pointSPath.get(i) && ro.getArrival().getNumber() == pointSPath.get(i+1))
								{
									j = edgeSPath.indexOf(ro);
								}
							}
							
							if(j != -1)
							{
								if(edgeSPath.get(j).getTime() > r.getTime())
								{
									edgeSPath.remove(j);
									edgeSPath.add(r);
								}
							}
							else
							{
								edgeSPath.add(r);
							}
						}
					}
				}
			}
		}
		return edgeSPath;
	}
	
	public ArrayList<Float> getDistanceResp()
	{
		ArrayList<Float> weight = new ArrayList<>();
		for(WeightPred wp : distanceResp)
		{
			weight.add(wp.getWeight());
		}
		return weight;
	}
	
	public class WeightPred {
		private float weight;
		private int pred;
		
		public WeightPred()
		{
			weight = 0;
			pred = 0;
		}
		
		public WeightPred(float w, int p)
		{
			weight = w;
			pred = p;
		}
		
		public float getWeight()
		{
			return weight;
		}
		
		public int getPred()
		{
			return pred;
		}
		
		public void setWeight(float w)
		{
			weight = w;
		}
		
		public void setPred(int p)
		{
			pred = p;
		}
	}
}
