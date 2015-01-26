# -*- coding: utf-8 -*-
"""
Created on Wed Nov 26 22:28:59 2014


a33TODO: Concatenatte planet into 2D structure, easier to deal with SCIPY 

@author: Austin

TODO: -Proper Python code styling
"""
import numpy as np
import re
import pylab
import math

#Constants
NUM_PLANETS = 2
NUM_STEPS = 500
NUM_FIELDS = 10 #mass, radius, position, velocity, acceleration, net force

#Open file
dataFile = open('testData.txt', 'r')

temp = ""

#TODO: Implement 2D array that checks for "Planet1", "Planet2", etc.
p0Data = np.empty([NUM_STEPS, NUM_FIELDS], dtype=np.dtype('float64'))   #Why strings?
p1Data = np.empty([NUM_STEPS, NUM_FIELDS], dtype=np.dtype('float64'))


#TODO: Find out how to include literal "[" in regex expressions
name_regex = re.compile(r"name:")
mass_regex = re.compile(r"mass:")
radius_regex = re.compile(r"radius:")
x_position_regex = re.compile(r"xPos:")
y_position_regex = re.compile(r"yPos:")
x_velocity_regex = re.compile(r"xVel:")
y_velocity_regex = re.compile(r"yVel:")
x_acceleration_regex = re.compile(r"xAccel:")
y_acceleration_regex = re.compile(r"yAccel:")
x_netforce_regex = re.compile(r"xNetForce:")
y_netforce_regex = re.compile(r"yNetForce:")
endofline_regex = re.compile("\n")

for x in range(0, NUM_STEPS*NUM_PLANETS):
    #Read a new line from file    
    line = dataFile.readline()
    
    #name_p = name_regex.search(line)
    #
    #if name_p == None:
    #    print "Error searching for name in line #" + str(x)
    #
    
    mass_p = mass_regex.search(line)
    if mass_p == None:
        print "Error searching for mass in line #" + str(x)
        break
    
    radius_p = radius_regex.search(line)
    if radius_p == None:
        print "Error searching for radius in line #" + str(x)
        break

    x_position_p = x_position_regex.search(line)
    if x_position_p == None:
        print "Error searching for x-position in line #" + str(x)
        break
    
    y_position_p = y_position_regex.search(line)
    if y_position_p == None:
        print "Error searching for y-position in line #" + str(x)
        break    

    x_velocity_p = x_velocity_regex.search(line)
    if x_velocity_p == None:
        print "Error searching for x-velocity in line #" + str(x)
        break
    
    y_velocity_p = y_velocity_regex.search(line)
    if y_velocity_p == None:
        print "Error searching for y-velocity in line #" + str(x)
        break
    
    x_acceleration_p = x_acceleration_regex.search(line)
    if x_acceleration_p == None:
        print "Error searching for x-acceleration in line #" + str(x)
        break
    
    y_acceleration_p = y_acceleration_regex.search(line)
    if y_acceleration_p == None:
        print "Error searching for velocity in line #" + str(x)
        break
    
    x_netforce_p = x_netforce_regex.search(line)
    if x_netforce_p == None:
        print "Error searching for net force in line #" + str(x)
        break
    y_netforce_p = y_netforce_regex.search(line)
    if y_netforce_p == None:
        print "Error searching for net force in line #" + str(x)
        break

    endofline_p = endofline_regex.search(line)
    if endofline_p == None:
        print "Error searching for net force in line #" + str(x)
        break

    if (x%NUM_PLANETS) == 0:    
        # "x" goes from 0 to NUMPLANETS*NUM_STEPS, however p0Data only has room for NUM_STEPS, so math.floor(x/NUM_PLANETS) is used       
        p0Data[math.floor(x/NUM_PLANETS), 0] = line[mass_p.end():radius_p.start()]    #mass   
        p0Data[math.floor(x/NUM_PLANETS), 1] = line[radius_p.end():x_position_p.start()]    #radius
        p0Data[math.floor(x/NUM_PLANETS), 2] = line[x_position_p.end():y_position_p.start()]    #x Position
        p0Data[math.floor(x/NUM_PLANETS), 3] = line[y_position_p.end():x_velocity_p.start()]    #y Position
        p0Data[math.floor(x/NUM_PLANETS), 4] = line[x_velocity_p.end():y_velocity_p.start()]    #x Velocity
        p0Data[math.floor(x/NUM_PLANETS), 5] = line[y_velocity_p.end():x_acceleration_p.start()]    #y Velocity
        p0Data[math.floor(x/NUM_PLANETS), 6] = line[x_acceleration_p.end():y_acceleration_p.start()]    #x Acceleration
        p0Data[math.floor(x/NUM_PLANETS), 7] = line[y_acceleration_p.end():x_netforce_p.start()]  #y Acceleration
        p0Data[math.floor(x/NUM_PLANETS), 8] = line[x_netforce_p.end():y_netforce_p.start()]    #x net force    
        p0Data[math.floor(x/NUM_PLANETS), 9] = line[y_netforce_p.end():endofline_p.start()]    #x net force  
        
    if (x%NUM_PLANETS) == 1:
        # "x" goes from 0 to NUMPLANETS*NUM_STEPS, however p1Data only has room for NUM_STEPS, so math.floor(x/NUM_PLANETS) is used       
        p1Data[math.floor(x/NUM_PLANETS), 0] = line[mass_p.end():radius_p.start()]    #mass   
        p1Data[math.floor(x/NUM_PLANETS), 1] = line[radius_p.end():x_position_p.start()]    #radius
        p1Data[math.floor(x/NUM_PLANETS), 2] = line[x_position_p.end():y_position_p.start()]    #x Position
        p1Data[math.floor(x/NUM_PLANETS), 3] = line[y_position_p.end():x_velocity_p.start()]    #y Position
        p1Data[math.floor(x/NUM_PLANETS), 4] = line[x_velocity_p.end():y_velocity_p.start()]    #x Velocity
        p1Data[math.floor(x/NUM_PLANETS), 5] = line[y_velocity_p.end():x_acceleration_p.start()]    #y Velocity
        p1Data[math.floor(x/NUM_PLANETS), 6] = line[x_acceleration_p.end():y_acceleration_p.start()]    #x Acceleration
        p1Data[math.floor(x/NUM_PLANETS), 7] = line[y_acceleration_p.end():x_netforce_p.start()]  #y Acceleration
        p1Data[math.floor(x/NUM_PLANETS), 8] = line[x_netforce_p.end():y_netforce_p.start()]    #x net force    
        p1Data[math.floor(x/NUM_PLANETS), 9] = line[y_netforce_p.end():endofline_p.start()]    #x net force  
            
#Plot netforce as a function of step number
pylab.subplot(1,2,1)
pylab.plot(range(0, NUM_STEPS), p0Data[:, 2])
pylab.subplot(1,2,2)
pylab.plot(range(0, NUM_STEPS), p1Data[:, 2])

#pylab.plot(np.compress([False,True, False], planetData, axis=1), float(np.compress([False,True, False], planetData, axis=2)))

print "Program Terminated Successfully"
