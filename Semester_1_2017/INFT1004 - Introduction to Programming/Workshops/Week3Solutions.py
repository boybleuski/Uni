#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 8 March, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 3
##
## Question 2  - drawRedCross()
## Question 3  - drawRedCrossCentre()
## Question 4  - testPickAColor()
## Question 5  - drawCross(aPicture, aColour), testDrawCross()
## Question 6  - drawCrossPosition(aPicture, aColour, xPosition, yPosition), testDrawCrossPosition()
## Question 7  - addFour(number1, number2, number3, number4)
## Question 8  - testAddFour(), testLoopAddFour(), testLoopAddFourRequest()
## 
#######################################################################################################


#######################################################################################################
#### 2. In the lectures we set some pixels to be yellow to make a line. 
####    Write a similar function that draws a red cross on the picture. 
####    (If you want to be clever draw the cross in the middle of the picture. 
####    HINT. You will need to work out the width and height of the picture).
#######################################################################################################

def drawRedCross():
   # This functions allows the user to pick a picture file and then it draws a 
   # cross on the picture at position (x=10, y=10) - I just picked this position
   # I also just decided it should be 5 pixels high and 5 pixels wide
   aFile = pickAFile()
   aPicture = makePicture(aFile)
   
   ## draw a cross - 5 pixels wide and 5 pixels high
   ## I decided to position it at x=10, y=10
   
   # draw the horizontal line first - Notice that y will stay the same
   pixel = getPixel(aPicture, 8,10)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 9,10)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 10,10)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 11,10)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 12,10)
   setColor(pixel, red)

   # draw the vertical line next - Notice that x will stay the same
   pixel = getPixel(aPicture, 10,8)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 10,9)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 10,10)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 10,11)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, 10,12)
   setColor(pixel, red)
   
   explore(aPicture) #now explore the picture so I can check the red cross is where I expect


#######################################################################################################
#### 3.(M) Create a similar function to question 2 but now you want to be a bit clever 
#### and draw the cross in the middle of the picture. (HINT. You will need to work out 
#### the centre using the width and height of the picture)
####
#### NOTE: you might realise that a loop/iteration could work for this - 
####       It would save you having to repeat similar lines of code
####       You can try this if you want - although we haven't had a lot of practice with loops yet
####
#######################################################################################################

def drawRedCrossCentre():
   # This functions allows the user to pick a picture file and then it draws a 
   # cross on the picture at position (x=10, y=10) - I just picked this position
   # I also just decided it should be 5 pixels high and 5 pixels wide
  
   aFile = pickAFile()
   aPicture = makePicture(aFile)
   
   #work out the middle of the picture
   midX = getWidth(aPicture) / 2
   midY = getHeight(aPicture) / 2
   
   ## draw a cross - 5 pixels wide and 5 pixels high
   ## I now poistion it at x=midX, y=midY (about the middle)
   
   # draw the horizontal line first - Notice y will stay the same
   pixel = getPixel(aPicture, midX-2 ,midY)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX-1,midY)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX, midY)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX + 1, midY)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX + 2, midY)
   setColor(pixel, red)

   # draw the vertical line next - Notice x will stay the same
   pixel = getPixel(aPicture, midX,midY-2)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX, midY-1)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX,midY)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX,midY+1)
   setColor(pixel, red)
   
   pixel = getPixel(aPicture, midX,midY+2)
   setColor(pixel, red)
   
   explore(aPicture)



#######################################################################################################
#### 4. Look at the JES menu item called ???JES Functions/Color???. 
#### Try the pickAColor function in the command window. 
#### (It works a bit like pickAFile but lets you pick a color). 
####
#### You didn't need to write a function that uses pickAColor() 
#### but I did to show how you might use it
#### I also demonstrate the use of makeColor(red,green,blue)
#### which I didn;t even mention in the lab exercises - but it's another way to get a colour
#######################################################################################################

def testPickAColor():
   # This function demonstrates how to use the JES pickAColor() function.
   # It also shows how to use makeColor(red,green,blue) in case you want to do this
   
   pickedColour = pickAColor()    # pops up a color picking window
   print pickedColour             #print out the colour you picked

   anotherColour = makeColor(0,127,255) # red is 0, green is 127, blue is 255 (All need to be between 0,255)
   print anotherColour
   
#######################################################################################################
####
#### 5.(M) Write a similar function to question 3 that draws a cross 
####       on the centre of a picture. However, this function should have 2 parameters, 
####       a picture and a colour. So when the use calls the function they give it a picture 
####       and a colour.  It should be defined something like this???
####             def drawCross(aPicture, aColour):
####
####      The function should first duplicate the picture (You will need to use the JES function 
####      called ???duplicatePicture??? ??? you can find this on the JES menu under ???JES Functions/Pictures???. 
####      Once you have duplicated the picture the function should draw the cross at the centre 
####      location and in the correct colour. Finally it should return the duplicated picture 
####      with the coloured cross on it.
####
####      To test this function write a second function that has no parameters and ???.
####          Lets the user pick a file (assigning it to a variable ??? called file)
####          Creates a picture from the file (assigning it to a variable ??? called picture)
####          Lets the user pick a colour (assigning it to a variable ??? called favouriteColour)
####          Calls the draw cross function (assigned the returned picture to variable ??? called newPicture
####          Lets the user explore the newPicture
####
#######################################################################################################

def drawCross(aPicture, aColour):
   # This functions duplicates a picture and draws a 5 x 5 cross in the provide colour at the centre.
   # It returns the copied picture with the coloured cross. 

   copyPicture = duplicatePicture(aPicture) # duplicate the picture using the JES function duplicatePicture
   
   #work out the middle of the picture
   midX = getWidth(copyPicture) / 2
   midY = getHeight(copyPicture) / 2
   
   ## draw a cross - 5 pixels wide and 5 pixels high (I decided these dimensions)
   
   # draw the horizontal line first in the colour provided - Notice y will stay the same
   pixel = getPixel(copyPicture, midX-2, midY)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX-1, midY)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX, midY)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX + 1, midY)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX + 2, midY)
   setColor(pixel, aColour)

   # draw the vertical line next in the colour provided - Notice x will stay the same
   pixel = getPixel(copyPicture, midX, midY-2)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX, midY-1)
   setColor(pixel, aColour)
   
   #already set as part of the horizontal line above - no need to do it again
   #pixel = getPixel(copyPicture, midX, midY)
   #setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX, midY+1)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, midX, midY+2)
   setColor(pixel, aColour)
   
   return copyPicture   #make sure you return the copy with the cross
   
   
def testDrawCross():
   # This function is used to test the drawCross function.

   # Lets the user pick a file (assigning it to a variable ??? called file)
   file = pickAFile()

   # Creates a picture from the file (assigning it to a variable ??? called picture)
   picture = makePicture(file)
   
   # Lets the user pick a colour (assigning it to a variable ??? called favouriteColour)
   favouriteColour = pickAColor()
   
   # Calls the draw cross function (assigned the returned picture to variable ??? called newPicture
   newPicture = drawColorCross(picture, favouriteColour)
   
   # Lets the user explore the newPicture
   explore(newPicture)


#######################################################################################################
####
#### 6.(M) Write a function that is a lot like the function in question 5. It draws a cross on 
#### a picture at a specified location. It should have 4 parameters, a picture, a colour and an 
#### xPosition and yPosition. These two positions tell the function where the centre of the cross 
#### should be.
#### It should return a copy of the picture with the appropriate colour cross on it. 
#### (So you call the function, with a picture and a colour and the result will be a new (duplicate) 
#### picture with the specified colour cross on it) . Test the function as you did in question 5.
####
#######################################################################################################

def drawCrossPosition(aPicture, aColour, xPosition, yPosition): 

   # This functions duplicates a picture and draws a 5 x 5 cross in the provide colour.
   # The cross is positioned with its centre at xPosition, yPosition
   # It returns the copied picture with the coloured cross. 

   copyPicture = duplicatePicture(aPicture) # duplicate the picture using the JES function duplicatePicture
   
   
   ## draw a cross - 5 pixels wide and 5 pixels high (I decided these dimensions)
   ## perhaps the size could be another parameter??
   
   # draw the horizontal line first in the colour provided - Notice y will stay the same
   pixel = getPixel(copyPicture, xPosition-2, yPosition)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition-1, yPosition)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition, yPosition)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition + 1, yPosition)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition + 2, yPosition)
   setColor(pixel, aColour)

   # draw the vertical line next in the colour provided - Notice x will stay the same
   pixel = getPixel(copyPicture, xPosition, yPosition-2)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition, yPosition-1)
   setColor(pixel, aColour)
   
   #already set as part of the horizontal line above - no need to do it again
   #pixel = getPixel(copyPicture, midX, midY)
   #setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition, yPosition+1)
   setColor(pixel, aColour)
   
   pixel = getPixel(copyPicture, xPosition, yPosition+2)
   setColor(pixel, aColour)
   
   return copyPicture   #make sure you return the copy with the cross
   
   
def testDrawCrossPosition():
   # This function is used to test the drawCrossPosition function.
   # Note the test draws the cross at position (20,80) - (I picked this at random)
   # But because I draw a 5 pixel high and wide cross I need to make sure picture is at 
   # least 23 pixel wide, 83 high. (Otherwise the function will crash - ideally we would
   # check for such a problem and stop it happening altogether.)

   # Lets the user pick a file (assigning it to a variable - called file)
   file = pickAFile()

   # Creates a picture from the file (assigning it to a variable - called picture)
   picture = makePicture(file)
   
   # Creates a colour (assigning it to a variable - called favouriteColour)
   favouriteColour = makeColor(220, 255, 50) 
   # I made a greenie yellow color for this test - could use pickAColor if you prefer
   # favouriteColour = pickAColor()
   
   # set up the position I want to test at - be careful the picture needs to big enough so that
   # these x and y positions actually exist on the picture. I choose (20,80) in this case
   # But I could actually test this function by placing the cross at the centre by using... 
   # the getHeight and getWidth functions..
   #       testX = getHeight(picture) / 2
   #       testY = getWidth(picture) / 2 
   #  
   testX = 20
   testY = 80
   
   # Calls the draw cross function (assigned the returned picture to variable - called newPicture
   newPicture = drawCrossPosition(picture, favouriteColour, testX, testY)
   
   # Lets the user explore the newPicture
   explore(newPicture)
   
#######################################################################################################
### 7.	(M) Write a function  called
###         addFour(number1, number2, numbe31, number4)
###   that takes 4 numbers as arguments and then adds those four numbers together
###   and returns the result as a number.
#######################################################################################################

def addFour(number1, number2, number3, number4):
   # This functions returns the sum of the four parameters
   sum = number1 + number2 + number3 + number4
   return sum


#######################################################################################################
###
### 8. (H) Write a function that can be used to test the function in Q7.  
###    It should call the addFour function a number of times (3 times say) and prints out a 
###    nice message to show the results. A nice message might look like....
###
###       addFour(12.3, 1.2, 10.1, -1.0) = 22.6  
###       addFour(14.3, 2.2, 8.0, 1.3) = 25.8 
###       addFour(-2, 4, 11, 2) = 15
###
###    (HINT: you will need to concatenate (add/join) a number of strings together to achieve 
###    this and you will need to use the str function to convert any numbers into strings 
###    before concatenating them. 
###
###    (T) If you want to try something harder - try using a for loop for testing this -
###    although we haven't really covered this yet. You can use the JES function "requestNumber" 
###    if you like. You will have to look it up or use the built in help system.
###
#######################################################################################################
 
def testAddFour():
   # this functions is used to test the addFour function
   
   # Try          addFour(12.3, 1.2, 10.1, -1.0) = 22.6 
   num1 = 12.3
   num2 = 1.2
   num3 = 10.1
   num4 = -1.0
   
   total = addFour(num1, num2, num3, num4)
   
   print "addFour(" + str(num1) + ", " + str(num2) + ", " + str(num3) + ", " + str(num4) + ") = " + str(total)
   
   #Now try       addFour(14.3, 2.2, 8.0, 1.3) = 25.8
   num1 = 14.3
   num2 = 2.2
   num3 = 8.0
   num4 = 1.3
   
   total = addFour(num1, num2, num3, num4)
   
   print "addFour(" + str(num1) + ", " + str(num2) + ", " + str(num3) + ", " + str(num4) + ") = " + str(total)
   
   # Finally try  addFour(-2, 4, 11, 2) = 15
   num1 = -2
   num2 = 4
   num3 = 11
   num4 = 2
   
   total = addFour(num1, num2, num3, num4)
   
   print "addFour(" + str(num1) + ", " + str(num2) + ", " + str(num3) + ", " + str(num4) + ") = " + str(total)
   

def testLoopAddFour():
   #this function uses a for loop to adjust the test numbers - using the loop variable "i"
   #I adjust teh four numbers using the loop variable i (so they all change on each loop)
   for i in range(0,3): #i will be [0,1,2]
      num1 = -2 * i  
      num2 = 4 * (i + 1)
      num3 = 11 - i
      num4 = 2 + i
      total = addFour(num1, num2, num3, num4)
      print "i = " + str(i) # helps to understand what is going on in the loop
      print "addFour(" + str(num1) + ", " + str(num2) + ", " + str(num3) + ", " + str(num4) + ") = " + str(total)
      print "" # print a blank line

def testLoopAddFourRequest():
   # this function uses a loop to request 4 numbers from the user and then shows the result
   # it goes through the loop 3 times but you can use the stop button if you want.
   for i in range(0,3): #[0,1,2]
      num1 = requestNumber("Please enter the first number")
      num2 = requestNumber("Please enter the second number")
      num3 = requestNumber("Please enter the third number")
      num4 = requestNumber("Please enter the fourth number") 
      total = addFour(num1, num2, num3, num4)
      message = "addFour(" + str(num1) + ", " + str(num2) + ", " + str(num3) + ", " + str(num4) + ") = " + str(total)
      printNow(message) 
      showInformation(message)

