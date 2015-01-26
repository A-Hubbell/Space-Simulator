# -*- coding: utf-8 -*-
"""
Created on Sun Dec 28 13:48:25 2014

@author: Austin
"""

#Time step
dt = 0.01

#Masses
m1 = 1e10
m2 = 1e10

#Gravitational constant
G = 6.67384e-11

#Initial positions
x1_initial = 10
x2_initial = -10

#Initial velocities
v1_initial = 0
v2_initial = 0

#Distance between masses
r = abs(x1_initial - x2_initial)

#Gravitational force
Fg = (G*m1*m2)/(r*r)

#Acceleration
a1 = -Fg/m1
a2 = Fg/m2

#Final velocities
v1_final = v1_initial + a1*dt
v2_final = v2_initial + a2*dt

print "v1 final:  " + str(v1_final)
print "v2 final:  " + str(v2_final)

#Final positions
x1_final = x1_initial + v1_final*dt + 0.5*a1*(dt*dt)
x2_final = x2_initial + v2_final*dt + 0.5*a2*(dt*dt)

print "x1 final:  " + str(x1_final)
print "x2 final:  " + str(x2_final)

