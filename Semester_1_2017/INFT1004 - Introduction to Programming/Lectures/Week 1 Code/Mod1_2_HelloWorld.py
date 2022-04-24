###  Programmer : Keith Nesbitt
###  Date: 16 Feb 2015
###  This simple program introduces some simple functions for printing messages
###  To test try loading the program, and typing the following command(s):
###  >>> helloWorld()
###  >>> helloName("Keith") 
###  >>> helloAll()   

def helloWorld():
   #This is a function that prints a simple string message
   print("Hello World")

def helloName(name):
   #This is a function that uses a paramater (name) to 
   #print a message  
   print("Hello " + name)
   
def helloAll():
   #This function uses some previously defined functions to print a number of messages
   helloWorld()
   helloName("Keith")
   print ("Hello All!")
