###  Programmer : Keith Nesbitt
###  Date: 6 April 2017
###  This simple program introduces some simple functions.
###  That use iteration (while statements)
###  >>> testWhile()
###  >>> testWhileMax() 
###  >>> testRequestNumberIteration()
###  >>> testForRedPixel() #use the "redDoor.jpg" image
      
def testWhile():
   # This function uses While statement to print 
   # the numbers from 1 to 4
   count = 1
   while (count < 5):
     printNow(count)
     count = count + 1
   
def testWhileMax():
   # This function uses While statement to print 
   # the numbers from 1 to whatever number the user 
   # selects
   
   max = requestInteger("Please enter a number:")
   count = 1
   
   while (count <= max):
     print(count)
     count = count + 1

def testRequestNumberIteration():
   # This function uses requestString to get a predetermined
   # number (currently 3) of integers (>0) from the user. 
   # It places them in a list. At the end it prints the list.
   # Instead of using a for loop - it uses a while loop
   # and doesn't finish until the user selects 3 numbers
   # greater than 0

   listNumbers =[]
   
   totalNumbers = 3
   
   #define a boolean variable called "finished"
   # when finished is true the numbers have been entered correctly
   finished = false 
   
   while not finished:
      integerNumber = len(listNumbers) + 1 # just used in the message
      message = "Please enter integer " + str(integerNumber) 
      message = message + " (must be > 0) "
      
      number = requestInteger(message)
      
      if number <> None and number > 0:
         listNumbers.append(number)
      finished = (len(listNumbers) == totalNumbers)  #this will be true when 3 numbers entered
    
   print listNumbers


def redPixelInRow(image, rowNumber):
   # This function test for a pixel that is red (or almost red)
   # in a specified row of an image
   # it returns true if there is a redish pixel in the row 
   # or false otherwise

   result = false #return value

   # set up a boolean loop variable ? it becomes true
   # if a redish pixel is found
   redFound = false
   
   width = getWidth(image)
   x = 0 #start looking in first column

   while ((not redFound) and (x < width) ):
      pixel = getPixel(image, x, rowNumber)
      colourPixel = getColor(pixel)
      if (distance(colourPixel, red) < 100): #100 seems to work OK
         redFound = true #we have found a redish pixel
         result = true
         if redFound:  #just for debugging 
            print "redFound at x=" + str(x) + " y= " + str(rowNumber)
      else:
         x = x + 1 #check the next column

   return result


def testForRedPixel():
   # This function is used to test the function that finds a red pixel in a row
   # I will need to have an image with a reddish pixel in one row 
   # so I can check it (I'll also need another row without a red pixel)
   
   file = pickAFile()   #try the redDoor.jpg
   picture = makePicture(file)
   
   # test row y = 137  
   # with the "redDoor.jpg" image there is a reddish pixel in this row)
   
   if redPixelInRow(picture, 137):
      print("red pixel found")
   else:
      print ("No red pixel found")
   
   # test row y = 450 
   # with the "redDoor.jpg" image there is no red pixel in this row)
 
   if redPixelInRow(picture, 450):
       print("red pixel found")
   else:
       print ("No red pixel found")
