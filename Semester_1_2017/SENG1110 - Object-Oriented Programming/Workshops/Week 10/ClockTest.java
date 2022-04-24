public class ClockTest
{
	/** Test driver to exercise the AlarmClock class */
	public static void main(String args[])
	{
		Clock myClock = new Clock(12, 30, 0);
		System.out.println(myClock.toString());

		//Change the alarm time
		myClock.setHour(7);
		System.out.println(myClock.toString());

		//Create another AlarmClock object and test equals
		Clock mySecondClock = new Clock();

		if(myClock.equals(mySecondClock))
			System.out.println("The Clocks are equal!");
		else
			System.out.println("The Clocks are not equal!");

	}
}