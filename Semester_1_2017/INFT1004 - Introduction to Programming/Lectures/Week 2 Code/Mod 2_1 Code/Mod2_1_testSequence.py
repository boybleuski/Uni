###  Programmer : Keith Nesbitt
###  Date: 2 March 2015
###  This simple program introduces some simple test functions 
###  that demonstrate the importance of sequence - or the 
###  order you do things in programming.Compare the difference
###  between testOrderOne and testOrderTwo.
###
###  To run type the following command(s):
###  >>> testOrderOne()
###  >>> testOrderTwo() 
###  >>> testOrderOneDebug() #this adds some extra print statements for debugging
###  >>> testOrderTwoDebug() #this adds some extra print statements for debugging

def testOrderOne():
   # This function creates two integer variables and assigns
   # values to these variables. It then performs some multiplication.
   # It is the same code as testOrderTwo except that 
   # the order of the last two statements is swapped
   x = 5
   y = 2
   
   #NOTE - these two lines have their order swapped in testOrderTwo()
   x = x * y    # calculate x first
   y = x * y    #calculate y second
   
   print("======== final result ======== testOrderOne")
   print("    x = " + str(x) + "   y = " + str(y) )  #turn integers to strings with str()

def testOrderTwo():
   # This function creates two integer variables and assigns
   # values to these variables. It then performs some multiplication.
   # It is the same code as testOrderOne except that 
   # the order of the last two statements is swapped
   x = 5
   y = 2
   
   #NOTE - these two lines have their order swapped in testOrderTwo()
   y = x * y    # calculate y first
   x = x * y    # calculate x second
   
   print("======== final result ======== testOrderTwo")
   print("    x = " + str(x) + "   y = " + str(y) )  #turn integers to strings with str()



def testOrderTwoDebug():
   # This function creates two integer variables and assigns
   # values to these variables. It then performs some multiplication.
   # It is the same code as testOrderTwo except that 
   # it prints the results after each line - this is a bit
   # like Desk Checking and can be a useful way to check/debug code
   #
   # NOTE that when I print out the value of the integers (x,y)
   # I've concatenated them with a message eg. "x = " + str(x)
   # This makes it easier for me to read the message
   # But I have to turn the integers into strings first (using str function)
   # as python doesn't know how to concatenate a string and an integer
   # it only knoes how to concatenate strings together
   x = 5
   print("x = " + str(x))  #x is an integer I need to turn it into a string
   y = 2
   print("y = " + str(y))
   
   #NOTE - these two lines have their order swapped in testOrderTwo()
   y = x * y    # calculate y first
   print("y = x * y = " + str(y))
   
   x = x * y    #calculate x second
   print("x = x * y = " + str(x))

   print("======== final result ======== testOrderTwoDebug")
   print("    x = " + str(x) + "   y = " + str(y) )



def testOrderOneDebug():
   # This function creates two integer variables and assigns
   # values to these variables. It then performs some multiplication.
   # It is the same code as testOrderOne except that 
   # it prints the results after each line - this is a bit
   # like Desk Checking and can be a useful way to check/debug code
   #
   # NOTE that when I print out the value of the integers (x,y)
   # I've concatenated them with a message eg. "x = " + str(x)
   # This makes it easier for me to read the message
   # But I have to turn the integers into strings first (using str function)
   # as python doesn't know how to concatenate a string and an integer
   # it only knoes how to concatenate strings together
   x = 5
   print("x = " + str(x))  #x is an integer I need to turn it into a string
   y = 2
   print("y = " + str(y))
   
   #NOTE - these two lines have their order swapped in testOrderTwo()
   x = x * y    # calculate x first
   print("x = x * y = " + str(x))
   
   y = x * y    #calculate y second
   print("y = x * y = " + str(y))
   
   print("======== final result ======== testOrderOneDebug")
   print("    x = " + str(x) + "   y = " + str(y) )


def testOrderTwoDebug():
   # This function creates two integer variables and assigns
   # values to these variables. It then performs some multiplication.
   # It is the same code as testOrderTwo except that 
   # it prints the results after each line - this is a bit
   # like Desk Checking and can be a useful way to check/debug code
   #
   # NOTE that when I print out the value of the integers (x,y)
   # I've concatenated them with a message eg. "x = " + str(x)
   # This makes it easier for me to read the message
   # But I have to turn the integers into strings first (using str function)
   # as python doesn't know how to concatenate a string and an integer
   # it only knoes how to concatenate strings together
   x = 5
   print("x = " + str(x))  #x is an integer I need to turn it into a string
   y = 2
   print("y = " + str(y))
   
   #NOTE - these two lines have their order swapped in testOrderTwo()
   y = x * y    # calculate y first
   print("y = x * y = " + str(y))
   
   x = x * y    #calculate x second
   print("x = x * y = " + str(x))

   print("======== final result ======== testOrderTwoDebug")
   print("    x = " + str(x) + "   y = " + str(y) )

