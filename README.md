# App

The story behind this app--->
I remember, when I was in high school, my teachers would always come late to the class in the first period because they had to go to the administration building to sign a register to mark their attendance. They used to complain how far they had to go each day only to sign and write the time they entered. Now, what if this attendance marking process is automated? What if the teachers don’t have to go anywhere, and as soon as they enter the school premises, they would be automatically marked present. That’s exactly what Attendo does. Attendo is a mobile application which uses Radar’s brilliant geofencing technology to automate the attendance marking of employees, be it teachers, nurses, programmers, receptionists, basically any employee who has to mark their attendance.

Employers can set a geofence around their office building. Whenever an employee enters or exits this geofence, the time at which they entered/exited is updated on the server. The employer can see who all are currently checked in, meaning who all are currently in the geofence, as well as the past-history of all the employees, which would include whether they exited/entered as well as the time when they did it.

The employees can see their status, which includes whether they are checked in/checked out,  as well as their personal past history. 

Attendo makes life easier for both employers and employees, as employers can easily know the past history of their employees in order to calculate the pay of the employees, as well as know who all are currently checked in by a couple of clicks, and employees just need to switch their phone’s GPS on and their attendance will be marked.

#Inspiration
When we were in high school, our teachers would always come late to the class in the first period because they had to go to the administration building to sign a register to mark their attendance. They used to complain how far they had to go each day only to put their signature and write the time they entered.

We realized that this problem was not particular to only our school. In fact, most offices and institutions do not have an electronic gateway system which keep track of who is going in and going out. Rather, they rely on paper - based attendance marking, wherein the employee needs to put their signature and the time they entered/exited. Firstly, this does waste some time for the employee, and in cases wherein they would need to exit their office immediately because of an emergency, filling in the attendance register is extremely problematic. Secondly, employers have to be very cautious of not losing that attendance register, since that information on paper is not available elsewhere. Thirdly, buddy punching, which means an employee filling in the attendance in place of another employee, is possible and employees don't become serious regarding reaching their workplace on time, since they have their friends to fall back upon.

#What it does
Attendo is a mobile application which uses Radar’s brilliant geofencing technology to automate the attendance marking of employees, be it teachers, nurses, programmers, receptionists, basically any employee who has to mark their attendance.

Employers can set a geofence around their office building. Whenever an employee enters or exits this geofence, the time at which they entered/exited is updated on the server. The employer can see who all are currently checked in, meaning who all are currently in the geofence, as well as the past-history of all the employees, which would include whether they exited/entered as well as the time when they did it.

The employees can see their status, which includes whether they are checked in/checked out, as well as their personal past history.

Attendo makes life easier for both employers and employees: employers can easily know the past history of their employees in order to calculate the pay of the employees; they don't need to be worried about losing the attendance register since the data is saved on the server; they don't need to be worried about employees engaging in buddy punching; they can know who all are currently checked in, just by a couple of clicks, so that they can delegate tasks to the employees; similarly, employees also benefit as they just need to switch their phone’s GPS on and their attendance will be marked, rather than having to waste time to sign the attendance register.

#How I built it
We built it using Android Studio. We used Java to write the backend logic, Firebase Authentication to authenticate when signing up/logging in, Firebase Realtime Databases to store the employee and employer data, and Radar.io for the geofencing technology.
