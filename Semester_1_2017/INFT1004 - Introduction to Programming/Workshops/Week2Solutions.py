##################################################################
## Programmer: Keith Nesbitt
## Date: 5 March, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 2
##
## Question 2  - pickReadPrintShowPicture()
## Question 3  - pickReadPrintExplorePicture()
## Question 4  - helloSuperHeroes(), helloAll(), ..
## Question 5  - sumThree(a, b, c 
## Question 6  - sumThree(a, b, c)
## Question 7  - divideBy(top, bottom)
## Question 8  - countVowels(aString)
## Question 9  - mirrorString(aString)
## Question 10 - madStory(title, furniture, snack)
## Question 11 - calculateStarWarsName(firstName, lastName, mothersMaidenName, city)
## 
##################################################################

# 2. (E) Use pickAFile() and makePicture() to read in a file from the 
# mediasources-4ed folder (the pictures that come with JES). Print out 
# the details of the picture  - how big is it? Try the show() function 
# so you can look at the picture.


def pickReadPrintShowPicture():
   ## This function allows the user to pick a picture file,
   ## make a picture from it and then print the details of the picture.
   ## Finally it shows the picture (NOTE you could do all this in the command window)
   
   file = pickAFile()            #1. Pick the file - make sure it is a picture
   picture = makePicture(file)   #2. It's only a file - so make a picture object
   print picture                 #3. This will tell you how big it is 
   show(picture)                 #4. Now you can see it


# 3.(E) Use the picture from question 2 and use the explore() function 
# to investigate these pixel positions. x=0,  y=0; x=10, y=0 ; x=0, y=20
# (middle pixel - or almost middle). What colour are they? 
# What are their red, green and blue values.

def pickReadPrintExplorePicture():
   ## This function allows the user to pick a picture file,
   ## make a picture from it and then print the details of the picture.
   ## Finally it explores the picture (NOTE you could do all this in the command window)
   ## You can work out the colours by exploring - next week we will also learn 
   ## how to do this in code using the getPixel() function
   
   file = pickAFile()            #1. Pick the file - make sure it is a picture
   picture = makePicture(file)   #2. It's only a file - so make a picture object
   print picture                 #3. This will tell you how big it is 
   explore(picture)              #4. Now you can explore it and check out the colours 


# 4.(M) Download the example code from the week 1 lecture (Mod02_01_HelloWorld.py).
# Modify this program by adding in another function called helloSuperHeroes(). 
# This function should print a hello message to 3 of your favourite super heroes.
# 
# For example,...
#   Hello Spiderman
#   Hello Superman
#   Hello Wonder Woman
#
# Test your function by calling it from the existing helloAll function. 
# (That is add the call to your helloSuperHeroes() to the end of the helloAll() 
# function that already exists.)

def helloSuperHeroes():
   ### This functions prints out a hello message to some of my 
   ### favourite super hereos
    print "Hello Batman"
    print "Hello Power Rangers"
    print "Wonder Woman"

## The following functions I've borrowed from Mod02_01_HelloWorld.py
## Then I have added in the new function to the end of 
def helloWorld():
   #This is a function that prints a simple string message
   print("Hello World")

def helloName(name):
   #This is a function that uses a paramater (name) to 
   #print a message  
   print("Hello " + name)
   
def helloAll():
   #This function uses some previously defined functions to print a number of messages
   helloWorld()
   helloName("Keith")
   print ("Hello All!")
   helloSuperHeroes()         # Call the new super hero function


# 5.(E) Write a function called sumThree(a, b, c) This function should print 
# the result of adding these three numbers (a, b, c) together. (They could be 
# integers or floats). To start with just print the answer e.g. 
#
#     >>>sumThree(4,3,2)
#     9

# 6.(M) Modify question 5 so that the function now prints an answer like this..
#
#     >>>sumThree(4,3,2)
#     result = 9
#
#  then change it again so it prints an answer like this...
#
#     >>>sumThree(4,3,2)
#    4 + 3 + 2 = 9
# HINT: you will need the str() function if you want to turn numbers to strings

def sumThree(a,b,c):
   ### This function adds the three numbers a, b, c, and then prints the result
   result = a + b + c
   print (result)  ### I could do it all in 1 step - print(a+b+c)
   print ("result = " + str(result))  #joining 2 string together - first turn result into a string
   print (str(a) + " + " + str(b) + " + " + str(c) + " = " + str(result))  #joining lots of strings together - first turn result into a string


# 7.(M) Write a function called divideBy(top, bottom). This function should work with 
# integers. The function should report how many times the bottom number goes into the 
# top number and the remainder.. eg.
#
#      >>> divideBy(10,3)
#     10 divided by 3 is 3 with a remainder of 1
#
#     >>> divideBy(28,11)
#     28 divided by 11 is 2 with a remainder of 6
def divideBy(top, bottom):
   divideResult = top / bottom   # division - the result is an integer if top and bottom are integers
   remainder = top % bottom      # this is the mod function - see module on arithmetic
   
   # now create a message for printing
   
   message = str(top) + " divided by " + str(bottom) + " is " + str(divideResult) + " with a remainder of " + str(remainder)
   print message ## I could have done this on one step withouy creating the message variable
  
# 8. (M) Write a function that counts how many vowels there are in string.
#    HINT: I based this on the code from Mod2_2_QuickStart.py

def countVowels(aString):
    ## Takes a string and counts all the vowels in the string - prints/returns the result
    
    count = 0 # start at 0
    
    for letter in aString:
       if letter.lower() in "aeiou": #lower() function makes sure you count captial letters
          count = count + 1
     
    print "count = " + str(count)
    
    return count # normally I would return the result so I culd assign it to a variable 
    

# 9. (H) Write a function that mirrors a string. (eg "abc" mirrored is "abccba")

def mirrorString(aString):
     ### This function mirrors the string using the index 
     mirrorString = "" # just set the mirror strinhg to the empty string
     
     lengthOfString = len(aString) #number of characters in string

     # There's quite a few different ways to do this - I just picked this way
     # iterating through the string twice - once forward, once backward
     for i in range(0,lengthOfString): 
        mirrorString = mirrorString + aString[i] #just copy the string
        
     for i in range(lengthOfString-1, -1, -1):
        mirrorString = mirrorString + aString[i] #now copy the string backwards
    
     print mirrorString   #print the result
     return mirrorString  #return the result


# 10.(H) Write your own madStory function. Decide what parameters you will change in the story.
def madStory(title, furniture, snack):
    # This function just prints a bit of a story.
    # You need to provide 3 strings as parameters
    print "Little " + title + " Muffet sat on a " + furniture + " eating those " + snack + "."
  
# 11.(T) Write a function that calculates your Star Wars name given the required parameters
# http://au.askmen.com/recess/fun_lists/star-wars-name-generator.html
# You take the first 3 letters from your first name and the first 2 from your last name 
# to make your first name.
# Then to make your last name you take the first 2 letters from your mother maiden name 
# and the then the first 3 of the city you were born in.

def calculateStarWarsName(firstName, lastName, mothersMaidenName, city):
   # This function calculates yoru star wars name from the provided arguments
   # Note: This doesn't do an error checking - so assumes arguments are OK
   
   # Do the star wars first name
   #first 3 letters of first name and first 2 letters from last name
   starFirst = firstName[0] + firstName[1] + firstName[2] + lastName[0] + lastName[1]
 
   # Do the star wars last name
   # first 2 letters of mothers maiden name and first 3 letters of city
   starLast = mothersMaidenName[0] + mothersMaidenName[1] + city[0] + city[1] + city[2]
   
   # Ok join the two names together - with a space between them
   starName = starFirst + " " + starLast
   
   print starName
   return starName

def calculateStarWarsNameFix(firstName, lastName, mothersMaidenName, city):
   # This function calculates yoru star wars name from the provided arguments
   # Note: This doesn't do an error checking - so assumes arguments are OK
   # removes the problme with capitals
   
   # Do the star wars first name
   #first 3 letters of first name and first 2 letters from last name
   starFirst = firstName[0] + firstName[1] + firstName[2] + lastName[0].lower() + lastName[1]
 
   # Do the star wars last name
   # first 2 letters of mothers maiden name and first 3 letters of city
   starLast = mothersMaidenName[0] + mothersMaidenName[1] + city[0].lower() + city[1] + city[2]
   
   # Ok join the two names together - with a space between them
   starName = starFirst + " " + starLast
   
   print starName
   return starName

