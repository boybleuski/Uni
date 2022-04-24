###  Programmer : Keith Nesbitt
###  Date: 31 March 2017
###  This simple program  introduces some simple examples of lists and using files 
###  To test try loading the program, and typing the following command(s):
###  >>> listExampleSimple()
###  >>> listExampleMore() 
###  >>> specialMessage(listOfNames) #this requires a list of names 
###  >>> readPrintFile()
###  >>> readSortedList(pathName), testSortedList()

def listExampleSimple():
   listNames = ["John","Peter","Keith","Bob","Trevor","Mick"]
   print listNames
   print listNames[3]


def listExamplesMore():
   listNames = ["John","Peter","Keith","Bob","Trevor","Mick"]
   print listNames
   print listNames[3] 
   
   # get the number of elements in the list - print this out
   numberElements = len(listNames)
   print ("number of items = " + str(numberElements))
   
   #try this special function with the list
   specialMessage(listNames)


def specialMessage(aNameList):
    #This function looks for a particular string in a list of a names
    #If it finds that name it prints a special message
    
    for item in aNameList :  #process each item in the list - iteration
       if item == "Keith" :  #only print the special case - selection
          print "Happy Birthday " + item + " - you are a legend!"

def readPrintFile():
   # This is a function that allows the user to pick a text file that
   # contains a lot of names (one name on each line). 
   # It places the names into a list. It then prints each element of the list.
   # It checks to see if each line has a newline ("\n") character on the end and
   # removes the "\n" if it exists, before placing the line into a list.
   # These extra newlines can be a problem when changing between Windows and Mac
   # operating systems - as the MAc OS use an extra newline

   #1. Pick a text file that contains names - one name on each line
   filename = pickAFile() 
   
   #2. Open the file for reading 
   file = open(filename, "r")
   
   #3. Copy all the names into the list 
   nameList =[] #start with an empty list
   
   for line in file:
      characters = len(line)                  #work out how many characters in the string
      
      if line[characters-1] == "\n":
         removedNewline =  line[0:characters-1]  #don't use the last character ("/n")
      else:
         removedNewline =  line[0:characters]    #keep the last character ("/n")

      nameList.append(removedNewline)
   
   print("nameList = " + str(nameList)) #print the list just to check

   #4. Print all the names in the list 
   for name in nameList:
      print name
    
   #5. Close your file at the end 
   file.close()

def readPrintFile():
   # This is a function that allows the user to pick a text file that
   # contains a lot of names (one name on each line). 
   # It places the names into a list. It then prints each element of the list.
   # It checks to see if each line has a newline ("\n") character on the end and
   # removes the "\n" if it exists, before placing the line into a list.
   # These extra newlines can be a problem when changing between Windows and Mac
   # operating systems - as the MAc OS use an extra newline

   #1. Pick a text file that contains names - one name on each line
   filename = pickAFile() 
   
   #2. Open the file for reading 
   file = open(filename, "r")
   
   #3. Copy all the names into the list 
   nameList =[] #start with an empty list
   
   for line in file:
      characters = len(line)                  #work out how many characters in the string
      
      if line[characters-1] == "\n":
         removedNewline =  line[0:characters-1]  #don't use the last character ("/n")
      else:
         removedNewline =  line[0:characters]    #keep the last character ("/n")

      nameList.append(removedNewline)  # add the item to the end of the list
   
   print("nameList = " + str(nameList)) #print the list just to check

   #4. Print all the names in the list 
   for name in nameList:
      print name
    
   #5. Close your file at the end 
   file.close()

def testSortedList():
   # this function is used to test the readSortedList function
   # Note: You should use setMediaPath() first to make sure the media path
   # is set to the directory where the file called GirlNames.txt is 
   
   # set up the full path name (make sure you have set the media path!)
   pathFile = getMediaPath("GirlNames.txt")
   
   # read the sorted list 
   sortedNameList = readSortedList(pathFile)
   
   # print the list to check it is correct - notice the str function works with lists
   print "sortedNameList = " + str(sortedNameList)
    

def readSortedList(pathName):

   # This is a function that takes a file path as an argument
   # the file should contain a lot of names (one name on each line). 
   # It places the names into a list. It then sorts the list of names and returns it.
   #
   # It checks to see if each line has a newline ("\n") character on the end and
   # removes the "\n" if it exists, before placing the line into a list.
   # These extra newlines can be a problem when changing between Windows and Mac
   # operating systems - as the MAc OS use an extra newline

   
   #1. Open the file for reading 
   file = open(pathName, "r")
   
   #2. Copy all the names into the list 
   nameList =[] #start with an empty list
   
   # process each line in the file - one at a time
   for line in file:
      characters = len(line)      # work out how many characters in the line  - the length
      
      # check the last character in the string in case it is a newline "\n" - don't want to keep that
      if line[characters-1] == "\n":
         removedNewline =  line[0:characters-1]  #don't use the last character ("/n")
      else:
         removedNewline =  line[0:characters]    #keep the last character ("/n")

      nameList.append(removedNewline)  # add the item to the end of the list
   
   #print the list just to check what it looks like before sorting
   print("nameList = " + str(nameList))

   #3. Create the sorted list - we first need to copy the original list
   #   I want to avoid changing this original list - and the python sort() function'
   #   has this side effect so first make a copy in a new list
   sortedList = [] # start with an empty list
   
   # now copy every name into this new list - if this is confusing try printing the list on each loop
   for name in nameList:
      sortedList.append(name)
      # print sortedList
     
   # now sort it - this is easy as python has a function that works with lists
   # and does some sorting - for strings it sorts based on the first character of string
   sortedList.sort()
    
   #4. Close your file at the end 
   file.close()
   
   #5. return the sortedList
   return sortedList
  
