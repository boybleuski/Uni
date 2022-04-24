###  Programmer : Keith Nesbitt
###  Date: 23 Feb 2016
###  This  program illustsrates the use of selection (if, else, elif) 
###  statements
###  >>> testSelectionIfIntegers() 
###  >>> testSelectionIfStrings() 
###  >>> testRequestStringSelection()
###  >>> testRequestStringIteration()

def testSelectionIfIntegers():

   # This function shows the if statement being used with a 
   # range of comparison operators (<, ==, >, <>, <=, >=). 
   # All the examples compare integers
   # but they can also work with floats and strings.
   
   #Note:This function has a lot of repeatitive code
   #     I will redesign this in a later Module by
   #     replacing the repetition with a function
   
   #set up some variables to compare
   numberA = 1   
   numberB = 2
   
   print("####### numberA = " + str(numberA) + "       numberB = " + str(numberB) + " #######")
   #try a few different comparisons
   
   if (numberA > numberB):           #greater than
       print "numberA is greater than numberB"

   if (numberA == numberB):          #equal to
       print "numberA is equal to numberB"

   if (numberA < numberB):           #less than
       print "numberA is less than numberB"

   if (numberA <> numberB):           #not equal
       print "numberA is not equal to numberB"

   if (numberA >= numberB):           #greater than
       print "numberA is greater than or equal to numberB"

   if (numberA <= numberB):           #less than
       print "numberA is less than or equal to numberB"

   #change the values
   numberA = 1   
   numberB = 1
   
   print("####### numberA = " + str(numberA) + "       numberB = " + str(numberB) + " #######")
   #try a few different comparisons
   
   if (numberA > numberB):           #greater than
       print "numberA is greater than numberB"

   if (numberA == numberB):          #equal to
       print "numberA is equal to numberB"

   if (numberA < numberB):           #less than
       print "numberA is less than numberB"

   if (numberA <> numberB):           #not equal
       print "numberA is not equal to numberB"

   if (numberA >= numberB):           #greater than
       print "numberA is greater than or equal to numberB"

   if (numberA <= numberB):           #less than
       print "numberA is less than or equal to numberB"
  
   #change the values
   numberA = 2   
   numberB = 1
   
   print("####### numberA = " + str(numberA) + "       numberB = " + str(numberB) + " #######")
   #try a few different comparisons
   
   if (numberA > numberB):           #greater than
       print "numberA is greater than numberB"

   if (numberA == numberB):          #equal to
       print "numberA is equal to numberB"

   if (numberA < numberB):           #less than
       print "numberA is less than numberB"

   if (numberA <> numberB):           #not equal
       print "numberA is not equal to numberB"

   if (numberA >= numberB):           #greater than
       print "numberA is greater than or equal to numberB"

   if (numberA <= numberB):           #less than
       print "numberA is less than or equal to numberB"

def testSelectionIfStrings():

   # This function shows the if statement being used with a 
   # range of comparison operators (<, ==, >, <>, <=, >=). 
   # using strings.
   
   stringA = "apple"   
   stringB = "orange"
   
   print("####### stringA = " + stringA + "       stringB = " + stringB + " #######")
   #try a few different comparisons
   
   if (stringA > stringB):           #greater than
       print "stringA is greater than stringB"

   if (stringA == stringB):          #equal to
       print "stringA is equal to stringB"

   if (stringA < stringB):           #less than
       print "stringA is less than stringB"

   if (stringA <> stringB):           #not equal
       print "stringA is not equal to stringB"

   if (stringA >= stringB):           #greater than
       print "stringA is greater than or equal to stringB"

   if (stringA <= stringB):           #less than
       print "stringA is less than or equal to stringB"



def testRequestStringSelection():
   # This function uses requestString to get a string 
   # from the user. It checks for "Cancel", printing a 
   # sensible message if "None" is returned
   # if the OK button is selected the code then checks if the user
   # has entered a sensible value either "1, 2, 3 or 4?"
   # Depending on the entered string the code selects different 
   # print statements. If a string that has not been expected is 
   # entered then a sensible message is printed.
   
   # NOTE: This is a good example code but you would probably use
   # requestIntegerInRange, another JES function, 
   # When gettting an integer in a range like this.
   
   answer = requestString("1, 2, 3 or 4?")
   
   if answer == None:
      print "No value entered"
   elif answer =="1":
      print "One"
   elif answer =="2":
      print "Two"
   elif answer =="3":
      print "Three"
   elif answer =="4":
      print "Four"
   else: 
      print "Invalid selection = " + answer


def testRequestStringIteration():
   # This function uses requestString to get a predetermined
   # number (currently 3) of strings (names) from the user. 
   # It places them in a list. At the end it prints the list.
   # The function also returns the list 

   listNames =[]
   
   numberOfNames = 3
   
   for number in range(1,numberOfNames+1):
     name = requestString("Please enter name " + str(number))
     if name <> None:
         listNames.append(name)
   
   #print listNames
   return listNames


