#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 13 March, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 4
##
## Question 2  - calculateWaterBill(litresUsed), testCalculateWaterBill()
## Question 3  - createAnimalList(),testCreateAnimalList(), createAnimalListFor()
## Question 4  - makeNegativePicture(picture), testNegative()
## Question 5  - makeGreyscalePicture(picture), testGreyscale()
## Question 6  - drawRectangle(picture, rectangleColour), testDrawRectangle()
## 
#######################################################################################################

####-------------- Week 4 - Q2 ------------------------------------------
# 5. Write a function called  calculateWaterBill(litresUsed)
#    It calculates your water bill based on the number of litres of water you have used. 
#    The calculation is very simple. It is the number of litres times $0.07 
#    (so 7 cents a litre) plus an additional service charge of $78.
#
#    The function should return the price of a water bill. 
#    Make sure you test the function carefully.

def calculateWaterBill(litresUsed):
    ### This function calculates a water bill from the number of litres of water used
    price = (litresUsed * 0.7) + 78.0 
    
    return price 
    

def testCalculateWaterBill():
    ### This function helps to test the calculateWaterBill function
    ### It could use some extra work formatting the answer - but good enough for now

    litres = 50
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

    litres = 100
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))





####-------------- Week 4 - Q3 ------------------------------------------
# 3. (E) Write a function that uses the requestString function to get 
#    three animals from the user (eg. donkey, giraffe, lion). 
#    You can do this with a for loop or just ask the user 3 times to input 
#    an animal (I would try both ways).
#    The function should return the three animals as a list. 
#    You will need to create an empty list and then use the append function 
#    to add each animal (string) to the list. When you print the list to 
#    test it should look something like... [donkey, giraffe, lion]
#
#    (M) (if you like you can try sorting this list - look at the functions for lists).


def createAnimalList():
    # This function returns a list of three animals, entered by the user
    
    animalList = []   # creates an empty list
    
    # get the animal names from the user - just ask 3 times
    animal1 = requestString("Please enter an animal name (1) ")
    animal2 = requestString("Please enter an animal name (2) ")
    animal3 = requestString("Please enter an animal name (3) ")
    
    # now append each of the animal names to the list
    animalList.append(animal1)
    animalList.append(animal2)
    animalList.append(animal3)
    
    # this is the sort part - this function works with lists
    # This was optional - it is not very tricky - except you have
    # to look up somewhere what functions actually work with lists
    animalList.sort()
    
    return animalList

def testCreateAnimalList():

    newList = createAnimalList()  # this should return a new list of animals
    print newList                 # just check the list is OK.


def createAnimalListFor():
    # This function returns a list of three animals, entered by the user
    # It uses a for loop - which is probably a better way - especially if you need 
    # 30 animals!
    
    animalList = []   # creates an empty list
    
    # get the animal names from the user - just ask 3 times
    for i in range(0,3): #0,1,2
       animal = requestString("Please enter an animal name ")
       animalList.append(animal)
    
    # this is the sort part - this function works with lists
    # This was optional - it is not very tricky - except you have
    # to look up somewhere what functions actually work with lists
    animalList.sort()
    
    return animalList

   


####-------------- Week 4 - Q4 ------------------------------------------
# 4. (M) Write a function that takes a picture as an argument. 
#    It should duplicate the picture, before processing all the pixels 
#    to make a negative of the picture. It should return the negative. 
#
#    To make a negative image subtract the current red, blue and 
#    green values from 255 (Do this for every pixel).


def makeNegativePicture(picture):
   # This function makes a negative of the original picture  
   # There are no side effects - the original picture is unharmed
   negativePicture = duplicatePicture(picture)  #duplicate picture
   
   pixels = getPixels(negativePicture) # this gets all the pixels
   
   # Now process each pixel - one at a time 
   # The negative is obtained by taking away the current channel 
   # values (red, blue, green) from 255
   # (this was discussed in lecture 4)
   
   for pixel in pixels:
       currentRed = getRed(pixel)
       currentBlue = getBlue(pixel)
       currentGreen = getGreen(pixel)
       newRed = 255 - currentRed
       newBlue = 255 - currentBlue
       newGreen = 255 - currentGreen
       setRed(pixel,newRed)
       setBlue(pixel, newBlue)
       setGreen(pixel, newGreen)

   return negativePicture # now return the negative
   

def testNegative():
   # This is used to test the makeNegativePicture function
   aFile = pickAFile()
   aPicture = makePicture(aFile)
   
   negativePicture = makeNegativePicture(aPicture)
   
   explore(negativePicture)

####-------------- Week 4 - Q5 ------------------------------------------
# 4. (M) Write a function that takes a picture as an argument. 
#    It should duplicate the picture, before processing all the pixels 
#    to make a greyscale of the picture. It should return the greyscale picture. 
#
#   Note: To make a grey scale set the red, blue and green channels to the
#         average of the 3 channels. This makes every channel the same for 
#         a pixel. (Process all pixels in the image)


def makeGreyscalePicture(picture):
   # This function makes a greyscale version of the picture
   # There are no side effects - the original picture is unharmed
      
   greyscalePicture = duplicatePicture(picture)  #duplicate picture
   
   pixels = getPixels(greyscalePicture) # this gets all the pixels from teh grey scale image
   
   # Now process each pixel - one at a time 
   # The negative is obtained by taking away the current channel 
   # values (red, blue, green) from 255
   # (this was discussed in lecture 4)
   
   for pixel in pixels:
       currentRed = getRed(pixel)
       currentGreen = getGreen(pixel)
       currentBlue = getBlue(pixel)
       averageValue = (currentRed + currentGreen + currentBlue) / 3  # average value for this pixel
       # now set all channels to this average value - this makes in grey
       newRed = averageValue
       newGreen = averageValue
       newBlue = averageValue
       setRed(pixel,newRed)
       setGreen(pixel, newGreen)
       setBlue(pixel, newBlue)

   return greyscalePicture # now return the greyscale image
   

def testGreyscale():
   # This is used to test the makeNegativePicture function
   aFile = pickAFile()
   aPicture = makePicture(aFile)
   
   greyPicture = makeGreyscalePicture(aPicture)  # call the function and save the returned grey picture
   
   explore(greyPicture)  # explore to test it worked

   
####-------------- Week 4 - Q6 ------------------------------------------
# Write a function that draws a rectangle on a picture. The rectangle should be 21 
# pixels high and 21 pixels wide and located in the approximate centre of 
# the picture. The function should take 2 parameters, a picture and a colour. 
# It should return a copy of the picture with the appropriate coloud rectangle on it. 
#
# Write a second function that lest you test the first function. This first 
# function should pick a file, and make a picture of it. It should also either 
# use makeColor or pickAColor to get an appropriate colour for drawing a rectangle. 
# This test function should then call the first function, saving the returned 
# image and showing it on screen.

def drawRectangle(picture, rectangleColour):
   ### This function draws a (21x21) rectangle of specified colour on the picture
   ### It has no side effect on the original picture (the original picture is not changed)
   rectanglePicture = duplicatePicture(picture)
   
   midX = getWidth(rectanglePicture) / 2  # calculate the middle of the picture - X (half the width)
   midY = getHeight(rectanglePicture) / 2 # calculate the middle of the picture - Y (half the height)
   
   topLeftX = midX - 10 # to get the top left hand corner of the rectangle (21 x 21)
   topLeftY = midY - 10 # to get the top left hand corner of the rectangle (21 x 21)
  
   # now draw the rectangle (21 x 21)
   addRectFilled(rectanglePicture,topLeftX,topLeftY, 21, 21, rectangleColour)
        
   return rectanglePicture

def testDrawRectangle():
   ### This function is used to test the drawRectangle function
   aFile = pickAFile()
   testPicture = makePicture(aFile)
   
   myColour = pickAColor()
   #myColour = makeColor(0,255,0)
   
   newPicture = drawRectangle(testPicture, myColour)
   explore(newPicture)
