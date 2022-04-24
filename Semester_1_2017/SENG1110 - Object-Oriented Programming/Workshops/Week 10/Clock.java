public class Clock
{
	private int hour;
	private int minute;
	private int second;

	//Constructors
	public Clock()
	{
		//initialize to default values
		hour = 0;
		minute = 0;
		second = 0;
	}

	public Clock(int h, int m, int s)
	{
		setHour(h);
		setMinute(m);
		setSecond(s);
	}

	/**
	Valid integers for hour is in the range of 0 - 24
	*/
	public void setHour(int h)
	{
		if((h >= 0) && (h <= 24))
			hour = h;
		else
			System.out.println("Fatal error: invalid hour");
	}

	/**
	Valid integers for minute is in the range 0 - 59
	*/
	public void setMinute(int m)
	{
		if((m >= 0) && (m <= 59))
			minute = m;
		else
			System.out.println("Fatal error: invalid minute");
	}

	/**
	Valid integers for second is in the range 0 - 59
	*/
	public void setSecond(int s)
	{
		if((s >= 0) && (s <= 59))
			second = s;
		else
			System.out.println("Fatal error: invalid second");
	}

	//Mutator methods
	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public int getSecond()
	{
		return second;
	}

	public String getCurrentTime()
	{
		return toString();
	}

	//Facilitator methods
	public String toString()
	{
		return "The current time is: "
		  + hour + ":" + minute + ":" + second;
	}

	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		else if(getClass() != o.getClass())
			return false;
		else
		{
			Clock otherClock = (Clock) o;
			return((hour == otherClock.hour) &&
			       (minute == otherClock.minute)
					&& (second == otherClock.second));
		}

	}
}