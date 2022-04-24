###  Programmer : Keith Nesbitt
###  Date: 31 March 2017
###  This simple program introduces some simple JES input and output functions.
###  To run type the following command(s):
###
###  >>> testRequestString()
###  >>> testInputFunctions() 
###  >>> testOutputFunctions() 


def testRequestString():
   # This function uses requestString to get a string 
   # from the user. It contains no check that the user
   # enters a sensible value -so the user can put anything at 
   # all in really.
   
   answer = requestString("1, 2, 3 or 4?")
   
   print "Selected value = " + answer


def testInputFunctions():
   #This functions tests some useful JES input functions
   decimalValue = requestNumber("Enter a decimal number Will")
   printNow(decimalValue)
   
   integerValue = requestInteger("Enter an integer Will")
   printNow(integerValue)
   
   integerValue = requestIntegerInRange("Enter an integer between 1-5, Will", 1, 5)
   printNow(integerValue)


def testOutputFunctions():
   #This functions tests some useful JES output functions
   showWarning("Warning Warning Will Robinson")
   showInformation("Will is lost in space")
   showError("I'm afraid I can't do that Will")
