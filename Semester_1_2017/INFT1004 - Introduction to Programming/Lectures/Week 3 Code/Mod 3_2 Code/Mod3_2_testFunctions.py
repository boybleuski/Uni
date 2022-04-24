###  Programmer : Keith Nesbitt
###  Date: 23 Feb 2015
###  This simple program introduces some simple JES Arithmeti
###   and the range function
###  To run type the following command(s):
###  >>> testRange()
###  >>> showRange() 
###  >>> sumThree(a, b, c) 
###  >>> testPickAFile() 


def testRange():
   #test the range function
   showRange(-2, 5)
   showRange(4,10)
   
   start = 3
   end = start + 5
   showRange(start, end)


def showRange(min, max):
   #This function plays with the range functions (a Python function)
   #it uses the print statement to print the results

   myRange = range(min, max+1)
   print myRange
   

def sumThree(a, b, c):
   # This function takes three numbers (a, b, c) and adds them together
   # it returns the sum (a + b + c)

   sum = a + b + c

   return sum


def testPickAFile():
   # This function uses pickAFile function to select a file
   # it prints the path and name of the the file if one is selected
   # If no file is selected then it prints an appropriate message
   filename = pickAFile()
   if filename == None:
      print "No filename was selected"
   else:
      print "Selected File = " + filename


