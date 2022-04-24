import java.util.*;

public class ArrayExample
{
    static Scanner console = new Scanner(System.in);
    static final int ARRAY_SIZE=10;

    public static void main(String[] args) 
    {
        ArrayExample example = new ArrayExample();
        example.run();
    }
    
    public void run ()
    {
        int[] listA = new int[ARRAY_SIZE];
        int[] listB = new int[ARRAY_SIZE];
   
        System.out.print("listA elements: ");
        printArray(listA, listA.length);
        System.out.println();

        System.out.print("Enter " + listA.length + " integers: ");
        fillArray(listA, listA.length);
        System.out.println();

        System.out.println("After filling "+ "listA, the elements are:" + "\n");
        printArray(listA, listA.length);
        System.out.println("\n");

        System.out.println("Sum of the "+"elements of listA is: "+sumArray(listA, listA.length)+"\n");

        System.out.println("Location of "+"the largest element in "+"listA is: "+(indexLargestElement(listA,listA.length)+1)+"\n");
        System.out.println("Largest element in "+"listA is: "+listA[indexLargestElement(listA, listA.length)]+"\n");

        copyArray(listA, listB, listA.length);
        System.out.print("Line 16: After copying the "+"elements of listA into listB\n"+"listB elements are: ");
        printArray(listB, listB.length);                    
        System.out.println();                           
    }
    
     //Method to input data and store in an array
     public void fillArray(int[] list, int noOfElements) 
    {
         int index;

         for(index = 0; index < noOfElements; index++)
         {
            list[index] = console.nextInt();
         }
    }

    //Method to print the array
    public void printArray(int[] list, int noOfElements)
    {
        int index;

        for(index = 0; index < noOfElements; index++)
            System.out.print(list[index] + " ");
    }

    //Method to find and return the sum of an array
    public int sumArray(int[] list, int noOfElements)
    {
        int index;
        int sum = 0;

        for(index = 0; index < noOfElements; index++)
            sum = sum + list[index];

        return sum;
    }

    //Method to find and return the index of the
    //largest element of an array
    public int indexLargestElement(int[] list, int noOfElements)
    {
        int index;
        int maxIndex = 0; //Assume first element is the largest

        for(index = 1; index < noOfElements; index++)
            if(list[maxIndex] < list[index])
                 maxIndex = index;

        return maxIndex;
    }

    //Method to copy one array into another array
    public void copyArray(int[] list1, int[] list2,
                                 int noOfElements)
    {
        int index;

          for(index = 0; index < noOfElements; index++)
            list2[index] = list1[index];
    }
}
