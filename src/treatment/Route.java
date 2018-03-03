package treatment;

public class Route {
	private int number;
	private String name;
	private String type;
	private Point start;
	private Point arrival;
	private float time;
	
	public Route(int numb, String n, String t, Point st, Point ar)
	{
		number = numb;
		name = n;
		type = t;
		start = st;
		arrival = ar;
		setTime();
	}
	
	public void setTime()
	{
		int distance = Math.abs(start.getAltitude() - arrival.getAltitude());
		switch(type)
		{
		case "V" :
			time = distance * 5 / 100; break;
		case "B" :
			time = distance * 4 / 100; break;
		case "R" :
			time = distance * 3 / 100; break;
		case "N" :
			time = distance * 2 / 100; break;
		case "KL" :
			time = distance * (1.0f/6.0f) / 100; break;
		case "SURF" :
			time = distance * 10 / 100; break;
		case "TPH" :
			time = 4 + distance * 2 / 100; break;
		case "TC" :
			time = 2 + distance * 3 / 100; break;
		case "TSD" :
			time = 1 + distance * 3 / 100; break;
		case "TS" :
			time =  1 + distance * 4 / 100; break;
		case "TK" :
			time = 1 + distance * 4 / 100; break;
		case "BUS" :
			 if(name.equals("navette1600-1800") || name.equals("navette1800-1600"))
             {
                 this.time = 40;
             }
             else if(name.equals("navette1600-2000") || name.equals("navette2000-1600"))
             {
                 this.time = 30;
             }
               break;
		}
	}
	
	public String getName()
	{
		return name;
	}

	public Point getStart()
	{
		return start;
	}
	
	public Point getArrival()
	{
		return arrival;
	}
	
	public String getType()
	{
		return type;
	}
	
	public float getTime()
	{
		return time;
	}
	
	public Integer getNumber()
	{
		return number;
	}
}
