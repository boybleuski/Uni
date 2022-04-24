###  Programmer : Keith Nesbitt
###  Date: 12 April 2017
###  This program illustrates some simple sound processing
###

def testPlayExplore():
   #a simple function that lets you pick a wav file and then it uses play, explore and print
   soundFile = pickAFile()  #try this with a ".wav" file from the mediasources from the textbook
   
   mySound = makeSound(soundFile)   #turn the file into a sound object
   
   play(mySound)
   explore(mySound)
   print(mySound)
   

def upVolume(aSound):
   # This function doubles the amplitude of a sound
   # Note: if multiplied sample values exceed 32767 they will be clipped
   for sample in getSamples(aSound):
      value = getSampleValue(sample) * 2
      setSampleValue(sample, value)
      
   play(aSound)


def adjustVolume(aSound, multiplier):
   # This function increases the amplitude (volume) of all samples in aSound 
   # Note: if multiplied sample values exceed 32767 they will be clipped 

   # Adjust the amplitude of a sound by a specified multiplier
   for sample in getSamples(aSound):
      setSampleValue(sample, getSampleValue(sample) * multiplier)

def normalise(aSound):
   # This function increases the amplitude of aSound as much as possible 
   # without clipping
   
   # 1. First find the largest Amplitude sample in the sound
   
   # First we have to find the biggest value (be careful with negative values)
   # its the magnitude of the sample that's important. Negative numbers won't
   # be included in calculations unless we make sure they are positive 
   biggest = 0 # The biggest is surely going to be bigger than this!
  
   # (you could do this with a selection statement
   for sample in getSamples(aSound):
      value = getSampleValue(sample)
    
      # check for -ve
      if value < 0:
         value = value * -1  #make the negative value positive for comparison
       
      if value > biggest: 
         biggest = value
  
      # This following code would also work it uses the "abs" function
      # abs - absolute value (ensures all sample magnitudes are +ve)
    
      # if abs(value) > biggest: 
      #    biggest = abs(value)

   # 2. Work out the biggest amount you can scale this value up. 
   #    This scaling factor is the largest value you can multiply
   #    the biggest amplitude sample by without clipping it.
   
   # After that loop, biggest is the biggest (positive or negative) amplitude
   # Multiplier will multiply it up to the maximum amplitude of 32767
   # This will ensure that the sound wont be clipped when we multiple sample values
   # It's a fraction; we use 32767.0 - why? - remember if we divide integers by 
   # integers we get an integer - so if we just used 32767
   # that is make it a float, it would end up being be zero!
   # then when we multiplied by zero it would end up as silence
   scalingFactor = 32767.0 / biggest
   
   # 3. Scale up all samples by the scaling factor

   # In the next loop, we multiply every sample value by the same multiplier
   for sample in getSamples(aSound):
      setSampleValue(sample, scalingFactor * getSampleValue(sample))


def adjustAmplitude(aSound, multiplier, startIndex, endIndex):
   # This function adjusts the amplitude of a range of samples in a sound
   # Samples between startIndex and endIndex are adjusted by
   # multiplying the amplitude of these samples from startIndex to endIndex
   # Note: the sample at endIndex is not included but the sample at startIndex is
   # Note: if multiplier is bigger that 1 it will increase amplitude
   #       if multiplier is less than 1 amplitude will be reduced
   
   # First we need the array of samples
   samples = getSamples(aSound)
   
   # Now we can select the samples that need adjusting and adjust them
   for index in range(startIndex, endIndex):
      setSampleValue(samples[index], multiplier * getSampleValue(samples[index]))


def boostThreeBits(aSound, boostFactor, startA, endA, startB, endB, startC, endC):
   # A highly specific function to boost the amplitude by multiplier 
   # just in three specific ranges A, B, C
   
   adjustAmplitude(aSound, boostFactor, startA, endA)
   adjustAmplitude(aSound, boostFactor, startB, endB)
   adjustAmplitude(aSound, boostFactor, startC, endC)