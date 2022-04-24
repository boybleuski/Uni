###  Programmer : Keith Nesbitt
###  Date: 31 March 2017
###  This  program illustrates how to do some simple file operations such as
###  as reading and writing  text files - you may want to set your media path 
###  using setMediaPath() before testing as it creates a file called
###  "lowerCase.txt" at the location of your current media path
###  >>> testChangeCaseFile() 


def testChangeCaseFile():

   # This function creates a simple text file with a few lines of text.
   # well one for each character in the alphabet
   # These lines are written using a while loop.
   # It saves the text file in the current media path 
   # It then reads the file again and coverts all the chracters to upper case.
   # the upper case text is saved to a new file (where the filename is based on the original filename)
   
   #PART 1 - create the lower case text file using a while loop
   
   filenameLower = getMediaPath("lowerCase.txt")  # create a file in the currrent media path
   newFileLower = open(filenameLower, "wt")       # open teh file for writing (w) text (t)
   
   characters=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
   
   finished = false  #assume we are not finished yet.
   count = 0   #First character in the list is at position 0
   
   while not finished:
      #can you work out what this selection statement does???
      if (count+1) < 10:
         message = "line  " + str(count+1) + " is " + characters[count] + "\n"
      else:
         message = "line " + str(count+1) + " is " + characters[count] + "\n"
      
      #there is a slight bug as this will put an extra newline at the end of file after z) - can you fix this?
         
      newFileLower.write(message)
      count = count + 1
      finished = (count == len(characters))  #finished when the last character is written (z at index 25)
     
   newFileLower.close()

   #PART 2 - create the upper case text file using a while loop
   
   # The second part of the function reads the original file, converts in to upper case
   # and writes the new upper case version to a new file called "upperCase.txt"

   filenameLower = getMediaPath("lowerCase.txt")  # create a file in the currrent media path
   newFileLower = open(filenameLower, "rt")       # open the file for reading (r) text (t)
   
   lowerCaseLines = newFileLower.read()      #read the file contents as a string
   newFileLower.close()                      #don't forget to close the file (usually OK with reading) 


   upperCaseLines = lowerCaseLines.upper()   #covert the string to upper case
   
   
   filenameUpper = getMediaPath("upperCase.txt")  # create a file in the currrent media path
   newFileUpper = open(filenameUpper, "wt")       # open the file for writing (w) text (t)


   newFileUpper.write(upperCaseLines)  #write the uppercase string to file
   
   newFileUpper.close()        #don't forget to close the file (can be a big problem with writing!)



def readFileText():
   # This function reads a file of text from a file as a single string and prints it
  
   filename = pickAFile()       # pick a file for opening
   file = open(filename, "rt")  # open the text file for reading
   stringText = file.read()     # read the text as a single string
   file.close()                 # close the file
   
   print stringText             # print the string

   

 