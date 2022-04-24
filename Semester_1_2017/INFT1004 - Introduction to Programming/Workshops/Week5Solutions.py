#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 15 March, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the lab in Week 5
##
## Question 2  - calculateBodyMassIndex(weightKilograms, heightMeters)
## Question 3  - testBodyMassIndex(), testBodyMassIndexCancel()
## Question 4  - calculateWaterBill(litresUsed), testCalculateWaterBill()
## Question 5  - drawHorizontalCenterLine(), drawVerticalCenterLine()
## Question 6  - verticalReflection(picture), testVerticalReflect()
## Question 7  - isValidPosition(picture, x, y))
## Question 8  - testValidPosition()
## 
#######################################################################################################

####-------------- Week 4 - Q2 ------------------------------------------
# 2. Write a function that returns the Body mass Index (BMI). It should be called
#        calculateBodyMassIndex(weightKilograms, heightMeters)
#
#    BodyMassIndex (BMI) is calculated using the formula
#         BMI = weight / height * height
#
#    (where weight is in kilograms and height is in metres )

def calculateBodyMassIndex(weightKilograms, heightMeters):
    BMI = weightKilograms / (heightMeters ** 2)  #height * height
    return BMI


####-------------- Week 4 - Q3 ------------------------------------------
# 3. Write a function that can be used to test the calculateBodyMassIndex 
# function in Q2. I suggest using the JES requestNumber function to get the 
# weight and height values from the user. (Or you could hard code some test 
# values if you feel lazy). It should print a message based on the persons 
# BMI and their age. The message should show what category the person falls into.
# Note: Try using the showInformation()function in JES to show your messages.

def testBodyMassIndex():

    weight = requestNumber("What is your weight (kilograms)")
    height = requestNumber("What is your height (metres)")

    bmi = calculateBodyMassIndex(weight, height)
    
    if bmi < 15:
       message = "Very severely underweight"
    elif bmi < 16: 
       message = "Severely underweight"
    elif bmi < 16.5: 
       message = "Underweight"
    elif bmi < 25: 
       message = "Normal (healthy weight)"
    elif bmi < 30: 
       message = "Overweight"
    else: # bmi >= 30: 
       message = "Moderately obese"


    print(message)
    
    showInformation(message)  # this is a JES information window
    
### (T) If you feel enthusiastic add in a check to make sure the 
### user doesn't press the 'Cancel' button on the requestNumber window.

def testBodyMassIndexCancel():
   ### This function allows the user to select the "Cancel" button at any stage
   ### (while entering weight or height) and teh function will cancel

   weight = requestNumber("What is your weight (kilograms)")
   
   if weight <> None: # "None" will be returned if the user presses the cancel button on first request
      height = requestNumber("What is your height (metres)")
      
      if height <> None: # so user must have cancelled from request to enter height
         bmi = calculateBodyMassIndex(weight, height)
    
         if bmi < 15:
            message = "Very severely underweight"
         elif bmi < 16: 
            message = "Severely underweight"
         elif bmi < 16.5: 
            message = "Underweight"
         elif bmi < 25: 
            message = "Normal (healthy weight)"
         elif bmi < 30: 
            message = "Overweight"
         else: # bmi >= 30: 
            message = "Moderately obese"

         print(message)
    
         showInformation(message)
   

####-------------- Week 5 - Q4 ------------------------------------------
# 4. Write a function called  calculateWaterBill(litresUsed)
#    It calculates your water bill based on the number of mega litres of 
#    water you have used. The calculation is very simple. If the number of 
#    litres is less than or equal to 1000 then the bill is $130. If the water 
#    used is greater than 1000 litres but less than or equal to 3000 then 
#    the bill is $230. If the water used is more than 3000 litres but less 
#    than 6000 then the bill is $350. If the water used is greater than or 
#    equal to 6000 then the bill is $500. The function should return the 
#    price of a water bill. Make sure you test the function carefully.

def calculateWaterBill(litresUsed):
    ### This function calculates a water bill from the number of litres of water used
    price = 0
    
    if litresUsed <= 1000:
       price = 130
    elif litresUsed <= 3000:  #notice if you get to this check the litres used must be > 1000
       price = 230
    elif litresUsed < 6000:   #notice if you get to this check the litres used must be > 3000
       price = 320
    else:  #litersUsed >= 6000:    notice litres used can't b eless than 6000 
       price = 500
       
    return price
    

def testCalculateWaterBill():
    ### This function helps to test the calculateWaterBill function

    litres = 300
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

    litres = 1000
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

    litres = 1001
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

    litres = 3000
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

    litres = 3001
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))
    litres = 6000
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))
    
    litres = 6001
    cost = calculateWaterBill(litres)
    print("litres = " + str(litres) + "  cost = $" + str(cost))

####-------------- Week 5 - Q5 ------------------------------------------
# 5. (M) Write a function that draws a horizontal "gray" line in approximately 
#    the middle of a picture. The line should go all the way across the picture. 
#    (Hint use a for statement and the range function to iterate through 
#    all the x values). 
#
#    After you do this you might want to try something similar but with a vertical 
#    line in the middle.

def drawHorizontalCenterLine():
    # this function draws a horizontal grey line on the centre of a picture
    # (well only if it is an odd number of pixels high - even number heights
    # have the line drawn slightly off centre
   

    pictureFile = pickAFile()
    aPicture = makePicture(pictureFile)
    show(aPicture)
    
    width = getWidth(aPicture)
    height = getHeight(aPicture)
    
    yCentre = (height / 2)   
    
    for x in range(0, width):
       aPixel = getPixel(aPicture, x, yCentre)
       setColor(aPixel, gray)

def drawVerticalCenterLine():
    # this function draws a vertical grey line on the centre of a picture
    # Well only if it is an odd number of pixels wide - even number widths
    # have the line drawn slightly off centre

    pictureFile = pickAFile()
    aPicture = makePicture(pictureFile)
    show(aPicture)
    
    width = getWidth(aPicture)
    height = getHeight(aPicture)
    
    xCentre = (width / 2)   
    
    for y in range(0, height):
       aPixel = getPixel(aPicture, xCentre, y)
       setColor(aPixel, gray)

####-------------- Week 4 - Q6 ------------------------------------------
# Write and test a function that reflects an image about its vertical 
# centre line. This function takes a picture as a parameter then creates 
# a picture twice as wide, but the same height. It should copy all the 
# pixels of the original image to the left side of the picture, and then 
# reflect this images about the centre line to fill the right half of the 
# image. The function should return the new reflected picture and should
# not affect the original image.

def verticalReflection(picture):
   ### This function creates a new picture (no side effects) - the new picture
   ### is twice as wide as the original. It is the same height. The left side
   ### of the image is the same as teh original. The right contains the reflection
   ### of the left side.
   originalHeight = getHeight(picture)
   originalWidth = getWidth(picture)
   
   newWidth = originalWidth * 2
   newHeight = originalHeight
   
   newPicture = makeEmptyPicture(newWidth, newHeight, white)
   
   #step 1 - copy original picture to the left side of new Picture
   for x in range(0, originalWidth):
      for y in range(0, originalHeight):
         originalPixel = getPixel(picture, x, y)
         redValue = getRed(originalPixel)
         greenValue = getGreen(originalPixel)
         blueValue = getBlue(originalPixel)
         
         newPixel = getPixel(newPicture, x, y)
         setRed(newPixel, redValue)
         setGreen(newPixel, greenValue)
         setBlue(newPixel, blueValue)
         
   
   #step 2 - reflect the original picture to the right side of the new picture
   for x in range(0, originalWidth):
      for y in range(0, originalHeight):
         originalPixel = getPixel(picture, x, y)
         redValue = getRed(originalPixel)
         greenValue = getGreen(originalPixel)
         blueValue = getBlue(originalPixel)
         
         newPixel = getPixel(newPicture, newWidth-1-x, y)
         setRed(newPixel, redValue)
         setGreen(newPixel, greenValue)
         setBlue(newPixel, blueValue)
   
   return newPicture

def testVerticalReflect():
   ### This function is used to test the verticalReflection function
   aFile = pickAFile()
   testPicture = makePicture(aFile)
   
   reflectedPicture = verticalReflection(testPicture)
   explore(reflectedPicture)


####-------------- Week 5 - Q7 ------------------------------------------
# 6.	(H) Write a function, called isValidPosition(picture, x, y)
#      This function takes a picture as the first argument and an x position 
#      and y position of a pixel as the second and third arguments. 
#      Test the (x,y) position is valid by comparing the x and y value with 
#      the height and width of the picture. If the position is valid the 
#      function should return true otherwise it should return false.

def isValidPosition(picture, x, y):

    # This function checks to see if the pixel position (x,y) is valid
    # it returns true if valid and false if not valid
    # This function is then used in question 8 

    #NOTE Be careful a picture of height 25 only has pixels from 0,1,2,...24
    #NOTE Be careful a picture of width  30 only has pixels from 0,1,2,...29
    
    height = getHeight(picture)
    width  = getWidth(picture)
    
    if x < 0: #can't have a negative pixel value
       result = false
    elif y < 0: #can't have a negative pixel value
       result = false
    elif y >= height: #can't be bigger than height-1
       result = false
    elif x >= width: #can't be bigger than width-1
       result = false
    else: #all good
       result = true
       
    return result #NOTE - we only have one return statement


####-------------- Week 5 - Q8 ------------------------------------------
# 7. (H) Write a function that can be used to test the function in question 6. 
#    This function should let the user pick a file and then make a picture
#    from this file. Print the details of the picture. Allow the user to 
#    enter an x and y position (try using the requestInteger() function). 
#    It should then use the is isValidPosition function to test the validity 
#    of the pixel positions and show a warning if the pixel position is not 
#    valid or show an information message that the position is valid. 
#    If the position is valid it should get the pixel and print the details 
#    of this pixel. [Hint use the showWarning() and showInformation() functions.


def testValidPosition():

    # This function is designed to test the isValidPosition
    # function. It only tests once which is a bit painful
    # so I've implmented another version testValidPositionWhile()
    # that goes in a loop - it is just below - it uses the while loop
    # which we haven't discussed yet in class - sio it may be a bit tricky
    
    pictureFile = pickAFile()
    aPicture = makePicture(pictureFile)
    
    # I decided to provide some information about the picture 
    # to help with testing - this wasn't part of the question
    # I just thought it would help the user a bit
    height = getHeight(aPicture)
    width = getWidth(aPicture)
    messageX = "[width="  + str(width)  + "] Enter x value to test " 
    messageY = "[height=" + str(height) + "] Enter y value to test"
    
    xPosition = requestInteger(messageX)
    yPosition = requestInteger(messageY)
    
    if isValidPosition(aPicture, xPosition, yPosition):
       print( "(" + str(xPosition) + "," + str(yPosition) + ") is a valid pixel position")
    else:
       print( "(" + str(xPosition) + "," + str(yPosition) + ") is a INVALID pixel position")

