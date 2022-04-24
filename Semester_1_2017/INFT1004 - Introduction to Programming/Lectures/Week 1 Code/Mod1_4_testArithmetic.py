###  Programmer : Keith Nesbitt
###  Date: 3 March 2015
###  This simple program introduces some simple JES Arithmetic
###  It also provides some examples of changing numbers to strings
###  and strings to numbers
###  To run type the following command(s):
###  >>> testRange()
###  >>> showRange() 
###  >>> testSimpleArithmetic() 
###  >>> testTrickyArithmetic() 


def testSimpleArithmetic():
   #This function plays with some simple arithmetic
   # using the arithmetic operators (+, -, *, /)
   #it uses the print statement to print the results

   #test some simple arithmetic
   print(513 * 25)
   size = 513 * 25
   print(size)
   print(3 + 25)
   minutes = 60 - 8
   print(minutes)

   #test some order of operation 
   print(2 + 5 * 8)
   print(2 * 5 + 8)
   print(2 * (5 + 8))
   
   #test some division 
   quotient = 13 / 2   #result is an integer
   print(quotient)
   quotient = 13.0 / 2 #result is a float
   print(quotient)

def testTrickyArithmetic():
   #This function plays with some complex arithmetic\
   #using the arithmetic operators (%, **)
   #it uses the print statement to print the results

   #test some simple arithmetic - modulus
   print(9 % 4)
   print(9 % 3)
   print(9 % 3)
   
   totalMinutes = 125
   hours = totalMinutes / 60
   minutes = totalMinutes % 60
   print(str(totalMinutes) + " min = " + str(hours) + " hrs " + str(minutes) + " min")

   #test some simple arithmetic - exponent
   print(3 ** 2) # 3 squared
   print(4 ** 2) # 4 squared
   print(5 ** 2) # 5 squared
   
   print(2 ** 3) # (2 to power 3) 2 cubed
   print(2 ** 4) # (2 to power 4)
   print(2 ** 5) # (2 to power 5)
   
   print(4 ** 0.5) # square root of 4
   print(9 ** 0.5) # square root of 9
   print(16 ** 0.5) # square root of 16
   print(25 ** 0.5) # square root of 25
   print(9.9 ** 0.5) # square root of 9.9

def testTurnStringsToNumbers():
   # This function demonstrates how strings can be turned into numbers 
   # (useful when reading strings from a file) or anytime you have a string that
   # looks like a number and you want to make it a number!

   myStringInteger = "-9"    # this is a string of characters (it only looks like a number)
   myStringFloat =   "47.3"  # this is a string of characters (it only looks like a number)
   
   myInteger = int(myStringInteger)  #turn a string into an integer
   print(myInteger)
   
   myFloat = float(myStringFloat)  #turn a string into a float   
   print(myFloat)
   
   print(myInteger + myFloat) #these are really numbers - so try some arithmetic


def testTurnNumbersToStrings():
   # This function demonstrates how numbers can be turned into strings 
   # (useful for printing numbers when you need to join strings together)

   myInteger = 42            # integers 
   myFloat = 8.6             # floats (or floating point) is how decimals are stored
   
   #print is clever enough to work with integers or floats or strings 
   print(myInteger)
   print(myFloat)
   print(myInteger + myFloat)
   
   # but if you want to concatenate (join) strings and numbers together 
   # for printing say, then you will need to turn your number 
   # (integer or float) into a string first.
   # You can turn an integer or float into a string using the str() command.
   # Note: The str function also works with other more complex types
   print("myInteger=" + str(myInteger))
   print("myFloat=" + str(myFloat))
   print("myFloat=" + str(myInteger + myFloat))

 
   