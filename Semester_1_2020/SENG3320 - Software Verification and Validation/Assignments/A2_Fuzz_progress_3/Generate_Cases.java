import java.util.Random; 
import java.io.FileWriter;  
import java.io.IOException;
import java.lang.Runtime;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList; 

public class Generate_Cases{	

	//Calls the function which run the blackbox testing, performance testing and fuzz testing
	public static void main(String[] args) {
		blackbox_testing();
		performance_testing();
		fuzz_testing();
	}
	
	private static void blackbox_testing(){
		ArrayList<String> errors = new ArrayList<String>();
		String characters = "~!@#$%^&*()_+{}|:<>?1234567890-=qwertyuiop[]asdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM;,./'`\"\\\n\r\t ";
		String output = "";
		
		
		//#####Test on empty file. No errors
		output = "";
		create_new_file("test.txt", output);	
		try{
			System.out.print("Running KWIC program on empty file");
			run_KWIC_program(output, -1, errors);
			System.out.println("  Completed successfully");
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//#####Test on line of 10000 characters.
		output = "";
		for(int i = 0; i < 100000; i++){
			output += "a";
		}
		create_new_file("test.txt", output);	
		try{
			System.out.print("Running KWIC program on line of 100000 characters");
			run_KWIC_program(output, -1, errors);
			System.out.println("  Completed successfully");
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//#####Test on file of 10000 lines. Finds ArrayIndexOutOfBoundsException
		output = "";
		for(int i = 0; i < 10000; i++){
			output += "a \n";
		}
		create_new_file("test.txt", output);	
		try{
			System.out.print("Running KWIC program on file of 10000 lines");
			run_KWIC_program(output, -1, errors);
			System.out.println("  Completed successfully");
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//#####Test all values individually. No errors
		System.out.println("==========Test all possible characters==========");
		for(int i = 0; i < characters.length(); i++){
			output = "";
			output += characters.charAt(i);
			create_new_file("test.txt", output);			//file used to run tests on	
			System.out.print("Running KWIC program on " + output + "... ");
			run_KWIC_program(output, -1, errors);
			System.out.println("  Completed successfully");
		}
		
		//#####Test all values repeated on same line. No errors
		System.out.println("==========Test on multiples identical strings==========");
		for(int i = 0; i < characters.length(); i++){
			output = "";
			for(int j = 0; j < 10; j++){
				output += characters.charAt(i) + " ";
			}
			create_new_file("test.txt", output);			//file used to run tests on	
			System.out.print("Running KWIC program on " + output + "... ");
			run_KWIC_program(output, -1, errors);
			System.out.println("  Completed successfully");
		}
	}
	
	private static void performance_testing(){				//Prints the time it takes to run the program 10 times
		try {												//Running the KWIC program on the generated file
			float duration = 0;
			for(int i = 0; i < 10; i++){
				Process process = Runtime.getRuntime().exec("java KWIC books.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null)
					System.out.println("tasklist: " + line);
							
				long startTime = System.nanoTime();			//Record execution time from after the file has been read
				process.waitFor();
				System.out.println(process.exitValue());
				duration += System.nanoTime() - startTime;
			}
			System.out.print("Time taken to run the program 10 times: ");
			System.out.printf("%.4f", (duration/1000000000.0));
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private static void fuzz_testing(){	
		ArrayList<String> errors = new ArrayList<String>();
		for(int i = 0; i < 1000; i++){			//Run program multiple times
			System.out.print(i+"-");
			//Contains all all possible characters, space and line break. backslash used to include escaped characters \ and " 
			String characters = "~!@#$%^&*()_+{}|:<>?1234567890-=qwertyuiop[]asdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM;,./'`\"\\\n\r\t ";
			Random rand = new Random(); 
			int rand_int; 
			int gen_size = rand.nextInt(5000);
	  
			String output = "";
			for(int j = 0; j < gen_size; j++){
				rand_int = rand.nextInt(characters.length()); 
				output += characters.charAt(rand_int);
			}
			create_new_file("test.txt", output);			//file used to run tests on				
			run_KWIC_program(output, i, errors);
		}
	}
	
	
	//Helper methods
	private static void run_KWIC_program(String output, int i, ArrayList<String> errors){
		try {											//Running the KWIC program on the generated file
			Process process = Runtime.getRuntime().exec("java KWIC test.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null){
				//System.out.println("tasklist: " + line);
			}
						
			process.waitFor();							//Print out the exception if process fails
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			stdError.readLine();
			String er = stdError.readLine();
			if(er != null && !errors.contains(er)){		//Save new errors
				create_new_file("Error_causing_input/test" + i + ".txt", output);
				errors.add(er);	
				System.out.println();
				System.out.println("New error found " + er);
			}
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private static void create_new_file(String name, String output){	
		try {
			FileWriter myWriter = new FileWriter(name);
			myWriter.write(output);
			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}