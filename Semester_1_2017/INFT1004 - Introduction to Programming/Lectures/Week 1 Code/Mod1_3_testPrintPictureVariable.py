###  Programmer : Keith Nesbitt
###  Date: 23 Feb 2015
###  This simple program introduces some simple test  
###  functions for printng pictures and variables
###  To run type the following command(s):
###  >>> testPickShowPicture()
###  >>> testPrintVariables()


def testPickShowPicture():
   # This function uses pickAFile function to select a picture file
   # it prints the path and name of the the file and some
   # information about the picture
   #
   # Note: There is no testing that you actually get a picture file
   # nor that it is the beach picture.
   
   pictureFile = pickAFile()
   
   print pictureFile
   
   beachPicture = makePicture(pictureFile)
   
   print beachPicture
   
   show(beachPicture)


def testPrintVariables():
   # This function initialises and prints some variables

   #initiaise some variables
   luckyNumber = 13
   bankBalance = -8.275
   myMessage = "My name"

   #print some variables
   print(luckyNumber)
   print(bankBalance)
   print(myMessage)
   
   #print some variables as part of a string
   #use the concatenation operator for joining two strings
   print("luckNumber = " + str(luckyNumber))   #convert number to string
   print("bankBalance = " + str(bankBalance))  #convert number to string
   print("myMessage = " + myMessage)
