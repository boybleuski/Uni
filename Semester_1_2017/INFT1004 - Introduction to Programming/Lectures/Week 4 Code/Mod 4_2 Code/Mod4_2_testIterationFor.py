###  Programmer : Keith Nesbitt
###  Date: 08 March 2015
###  This simple program introduces some simple functions.
###  Tthat use iteration (for statements)
###  >>> testForRange() 
###  >>> testForList()
###  >>> testNumberedForList() 
###  >>> testPrintReverseList() 
###  >>> testMaximumFunction() 
###  >>> findMaximum() 


def testForRange():
   # This function uses for statement to print 
   # allthe numbers from 1 to 4
   for count in range(1,5):
     print(count)

def testForList():
   # This function uses for statement to print 
   # all the items in the list
   fruitList = ["Apple", "Orange", "Grape"]
   
   for fruit in fruitList:
     print(fruit)


def testNumberedForList():
   # This function uses for statement to print 
   # all the items in the list. It puts a number 
   # (position in list) before the item
   fruitList = ["Apple", "Orange", "Grape"] # list of items
   position = 1                             # keep count of position
   
   for fruit in fruitList:
     print(str(position) + ".  " + fruit)
     position = position + 1


def testPrintReverseList():
   # This function uses for statement to print 
   # all the items in the list in reverse order. 
   # It puts a number (position in list) before the item

   fruitList = ["Apple", "Orange", "Grape"]
   numberItems = len(fruitList)
   position = numberItems - 1 #index starts at 0 

   for count in range(0, numberItems):
      print(str(position+1) + ".  " + str(fruitList[position]))
      position = position - 1

def testMaximumFunction():
    # A function to test the maximum function 
    
    #a list of numbers to test
    listNumbers = [89.3, 42.6, 91.7, 28.0]

    #I still need to write the findMaximum function
    myMax = findMaximum(listNumbers)

    #print the result to check it works
    # it should be 91.7 in this list
    print("Maximum = " + str(myMax))


def findMaximum(numberList):
    #A function that returns the maximum
    #number in the list
    #assumes it is a list of numbers
    
    maximum = numberList[0]
    
    for number in numberList:
       if number > maximum:
          maximum = number

    return maximum

