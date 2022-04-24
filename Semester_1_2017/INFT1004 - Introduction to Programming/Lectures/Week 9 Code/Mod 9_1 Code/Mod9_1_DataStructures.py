###  Programmer : Keith Nesbitt
###  Date: 9 April 2017
###  This  program illustrates the use of data structures


def enterAllHeights():
   # a function to get some height data for 5 people
   # uses indidvidual variables to store the data
   # quite difficult to modify the number of people (eg 200)

   height1 = requestNumber("Height person 1?")
   height2 = requestNumber("Height person 2?")
   height3 = requestNumber("Height person 3?")
   height4 = requestNumber("Height person 4?")
   height5 = requestNumber("Height person 5?")
   
   print("height1=" + str(height1))
   print("height2=" + str(height2))
   print("height3=" + str(height3))
   print("height4=" + str(height4))
   print("height5=" + str(height5))


def enterAllHeightsList():
   # a function to get some height data for 5 people
   # uses a list to store the data
   # gets the data and print it - without iteration (looping)
   # Could be written to take advantage of itertaion - see enterAllHeightsListLoop()
   height = []
   
   height1 = requestNumber("Height person 1?")
   height.append(height1)
   height2 = requestNumber("Height person 2?")
   height.append(height2)
   height3 = requestNumber("Height person 3?")
   height.append(height3)
   height4 = requestNumber("Height person 4?")
   height.append(height4)
   height5 = requestNumber("Height person 5?")
   height.append(height5)
   
   print("height1=" + str(height[0]))
   print("height2=" + str(height[1]))
   print("height3=" + str(height[2]))
   print("height4=" + str(height[3]))
   print("height5=" + str(height[4]))


def enterAllHeightsListLoop():
   # a function to get some height data for 5 people
   # uses a list to store the data
   # and a loop to get the data and print it
   # it can easily be modified to any number of people

   height = []
   numberPeople = 5  #just change the number of people here - that's it

   for i in range(0, numberPeople):
      inHeight= requestNumber ("Height person " + str(i) + "?")
      height.append(inHeight)

   for i in range(0, numberPeople):
      print("height" + str(i) + "=" + str(height[i]))

   #this will also display the elements of a list
   displayList(height)



def displayList(aList):
   ## a simple function that just prints the elements of a list
   for i in range(0, len(aList)):
      print("list[" + str(i) + "]=" + str(aList[i]))


def displayListWords():
   #  this function defines a list of words adn the number of occurences and then prints
   #  the elements and the number of times they occur
   
   myWords = [ ['a',20], ['the',15], ['bee',5], ['jump',3], ['apple',1], ['busy',1], ['xylophone',1] ]
   
   for item in myWords:
      message = item[0] + " - " + str(item[1])
      print message



   