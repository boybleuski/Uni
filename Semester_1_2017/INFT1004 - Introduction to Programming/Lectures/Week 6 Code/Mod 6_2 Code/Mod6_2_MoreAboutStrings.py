###  Programmer : Keith Nesbitt
###  Date: 31 March 2017
###  This  program illustrates the use of various string functions
###  startswith(), endswith()
###  find(), replace()
###  upper(), lower(), swapcase(), title()
###  split()
### 
###  To test the functions try calling..
###  >>> testStringStartEnd()
###  >>> testStringFind()
###  >>> testStringCase()
###  >>> testStringSplit()
###
###  Some other examples of reading in strings 
###  >>> testRequestStringSelection() 
###  >>> testRequestStringIteration() 

def testStringStartEnd():

   # This function shows the use of the startswith and endswith functions

   # 1. set up some test strings    
   response = "A string"
   anotherString = "Another"

   #2. Using the startswith function
   if response.startswith("A s"):
      print(" |" + response + "| starts with |A s|")
   else:
      print(" |" + response + "| doesn't start with |A s|")

   if anotherString.startswith("A s"):
      print(" |" + anotherString + "| starts with |A s|")
   else:
      print(" |" + anotherString + "| doesn't start with |A s|")
     
   #3. Using the endswith function - check for "ng"
   if response.endswith("ng"):
      print(" |" + response + "| ends with |ng|")
   else: 
      print(" |" + response + "| doesn't end with |ng|")

   if anotherString.endswith("ng"):
      print(" |" + anotherString + "| ends with |ng|")
   else:
      print(" |" + anotherString + "| doesn't end with |ng|")
  

def testStringFind():

   # This function shows the use of the string find and replace functions
   # there are 3 ways to use the find function (with 1,2 or 3 arguments)
   # They all work a little but differently.

   # set up some test strings to use
   response     = "A string"
   foxString    = "The last fox is the first fox"
   
   # try the find function with one argument - response.find(str)
   # return the index at which str starts in response, 
   # or ?1 if str isn?t found in response
   print("----- find(str) ------------------")
   index = 1
   index = response.find("s") # "A string" has an "s" at position 2
   print(index)
   index = response.find("st") # "A string" has an "st" at position 2
   print(index)
   index = response.find("ing") # "A string" has an "ing" at position 5
   print(index)
   index = response.find("xx") # "A string" has no "xx" - return -1
   print(index)

   # try the find function with two arguments - response.find(str,start)
   # return the index at which str starts in response, start looing at index=start
   # or ?1 if str isn?t found in response (after position start)
   print("----- find(str, start) ------------------")
   index = 1
   index = foxString.find("fox",5) # Start looking for "fox" from index 5 - found at 9
   print(index)
   index = foxString.find("fox",10) # Start looking for "fox" from index 10 - found at 26
   print(index)
   index = foxString.find("fox", 27) # no "fox" after index 27  : returns - 1
   print(index)

   # try the find function with three arguments - response.find(str,start,finish)
   # return the index at which str starts in response, start looing at index=start
   # but only look up to index=finish
   # or ?1 if str isn?t found in response (after position start)
   print("----- find(str, start, finish) ------------------")
   index = 1
   index = foxString.find("fox",5,15) # Start looking for "fox" between index 5 and 15 - found at 9
   print(index)
   index = foxString.find("fox",10,20) # Start looking for "fox" from index 10 : returns - 1
   print(index)
   index = foxString.find("fox",5, 10) # no "fox" in this index range : returns - 1
   print(index)

   print("----- replace(oldStr, newStr) ------------------")
   print(foxString)
   dogString=foxString.replace("fox", "dog")
   print(dogString)
   firstString=foxString.replace("last", "first")
   lastString=firstString.replace("first", "last")
   print(lastString)

def testStringSplit():
   # This function shows the use of the string split function
   # NOTE: something quyte similar is useful for splitting 
   #       comma seperated strings into a list of strings
   #       (To use the numbers you might need to make back into numbers using int(), float()
   
   # Set up a test data string, use split to make a list
   data = "1.2|2.3|4.3|8.1"
   dataList1 = data.split("|")
   print dataList1
   
   # Use split to make a list from comma seperated numbers in a string
   csvData = "10,3,4,5"
   dataList2 = csvData.split(",")
   print dataList2
   
def testStringCase():
   # This function shows the use of the string case functions
   # upper, lower, title, swapcase

   # 1. set up a test string 
   response     = "A string test"
   
   # try the find function with one argument - response.find(str)
   # return the index at which str starts in response, 
   # or ?1 if str isn?t found in response
   print(response)
   print(response.upper())
   print(response.lower())
   print(response.title())
   print(response.swapcase())


def testRequestStringSelection():
   # This function uses requestString to get a string 
   # from the user. It checks for "Cancel", printing a 
   #sensiblemessage if "None" is returned
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


