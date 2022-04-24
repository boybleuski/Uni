public class SwitchErrors
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.out);

		System.out.println("Key: 1=blue, 2=red, 3=green");
		System.out.print("Enter an number and I'll return ");
		System.out.print(" the corresponding color. ");
		number = keyboard.nextInt();

		switch(number)
		{
			case 1:
				System.out.println("You choose red!");
				break;
			case 2:
				System.out.println("You choose blue!");
				break;
			case 3:
				System.out.println("You choose green!");
			default:
				System.out.println("Colour not available!");
				break;
		}

	}
}