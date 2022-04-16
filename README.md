---
youtubeId: b0CQV-9wtbs
---

# Professional Self-Assessment

I am an experienced Information Technology and Computer Science professional having more than 15 years experience in the field.  My carreer has spanned roles including 
but not limtied to: Solutions Engineer, Technology Integration Trainer, Senior Technician, Client Technology Administrator, Cybersecurity Officer, Network Engineer, 
Database Manager, Software Analyst, Software Developer, Director of Information Technology, and Chief Technology Officer. I have worked in both the orporate and 
education industries and have been acknowledged for my level of work, efficiency, and personal interaction skills on multiple occassions.

Throughout my coursework at Southern New Hampshire University's Computer Science - Software Engineering program has allowed me to build upon my skills in software and 
systems engineering, security in coding and policies, database engineering, and client relations skills. 
I have been able to study in a formalized environment in tema collaboration through GitHub based tools, how to effectively extrapolate client needs and formalize 
proposals and analyses, work with and integrate advanced data structures and algorithms in a variety of applications in Python, Java, C++, and C, develop and create 
complex and intricate applications in multiple languages, deploy them in client/server environments, craft multi-layered databases and integrate them into various 
applications and use cases, and explore new and old projects to improve security standards within them as well as develop tactful and detailed security policies and 
documentations for applications and teams. 

The following is a sampling of artifacts of some of my work to demonstrate these skills. 

For software design and engineering, I have chosen an appointment system application.  This program is designed to create a new user and/or appointment, update those 
fields, read them, and delete them.  It employs a simplified database system that uses C++ source code and not SQL to store and check that information.  Itr also 
includes a series of checks if the entered information violates determined rules such as date of an appointment in the past as well as warn the user of such problems. 
The program was origionally written in Java.  For the integation into my ePortfolio I have completely re-coded it into C++ as well as cleaned up security issues and 
logical issues that existed in the origional Java version.

For data structures and algorithms, I have chosen a authentication system program written very early in my education.  This version made modifications to implement 
better and higher level functions that I origionally did not have knowledge of.  This includes the implementations of hash tables to store credentials as opposed to an 
unencrypted text file to review and compare authentication credentials.  I have added the ability to add a new user in this version as well.  It also utilizes an MD5 
hashing algorithm for secure password storage encryption and decryption protocols. 

For the datatbase, I have taken the frameworks obtained in the mobile appplication development course and developed a new Android application in Java.  The origional 
application from the class was an event planner application which utilized sql databases and queries.  I took this idea and created a Mobile Student Information System 
to demonstrate my ability to build mobile applications, utilize and query databases, and also works as an inclusion to my continued career in the education industry.  
This application has multiple classes and includes protocols to check for users and their access levels against the database which will then determine what information 
they can see and interact with.  It allows for users of the appropriate levels to then be able to create, read, update, and delete entries to other databses that 
include such actions as schedules, classes, and user managment.  

# Informal Code Review 

## What is code review?

Code review is the process of analyzing source code for the purposes of understanding the program and what it does, identifying potential security flaws, verification of the functionality of the code does what it is setout to do, and verified the clarity and readability of the code.

## What are some code review best practices that you would advocate?

I think some best practices I would want to incorporate would be reviewing no more than 400 lines at a time and no longer than 60-minute intervals.  I think by using these two practices it can help allow me to focus in on sections of code and not become over encumbered with the large picture.  It also allows me the time limit to refresh and not become stuck on things that would allow the rest of the review to go to waste.

## Code Review of My Three Artifacts
{% include youtubePlayer.html id=page.youtubeId %}


# Artifacts

## Software Design and Engineering
{::options parse_block_html="true" /}
<details><summary markdown="span"> Click to View Code</summary>
	'''#include cstdio
	'''
</details>
<br/>
{::options parse_block_html="false" /}


### Narrative
  This artifact was an appointment service application built as part of the final project of CS320.  The goal was to originally build a program that allowed a user to enter appointment details to create appointments as in a medical office setting.  
	This artifact enters my ePortfolio as part of the Software Design and Engineering aspect.  I chose this item as it was originally a more complex Java program relying on multiple class files and performing a variety methods and method checks to accomplish its’ tasks.  This would have served as a view of my abilities to work within the confines of the Java programming language, develop testing structures, and build a functional application that adheres to limitations of user requirements and books an appointment.  It also served as a proof of my ability to work off a prompt that served as a customer statement of work and be able to build the software from what they said.  The improvements in this case have been to clear up and automate some of the methods and checks utilized as well as convert the application to C++ from Java.
	As far as meeting the expected goal for this item, I would say I accomplished what I setout to do.  I took an existing java application that I wrote and converted to C++ and rebuilt it with more automations and optimizations to make it run smoother.  I think If I have more time at the end I may try to incorporate some of the other services that were part of the original project that I did not intend from the start.
	I think the biggest takeaway in this project was when I converted to C++.  My initial thought was to go line by line and converting to the languages syntax.  I later found out as I was doing it that this process would not really work.  As such I reviewed the Java version and determine what methods were used, what they did, and what the goal of them was.  Then took 



## Algorithm and Data Structure

[Authentication System program in Java] (_artifacts/datastructsalgor/IT145Authentication/src/AuthenticationSystem.java)

### Narrative
  This artifact is an authentication system in Java.  It was originally developed as part of the IT-145 class.  The original purpose was to develop a Java application that could get a username and password from a user, compare them against a file, and then return success or failure based on what it found for matches.  
	This artifact is included as part of the Data Structures and Algorithms aspect of the ePortfolio.  The original included a basic algorithm to deal with creating a hash for the password.  As it was, the artifact could suffice in my understanding of algorithms and how to interact with them.  With my modifications it will serve better as my understanding of data structures, how to create them, manipulate them, create search algorithms, and interact with the hashing algorithm and its’ incorporation into my code.  Those improvements came in the form of removing the text file of usernames and passwords and replacing with hash tables.  I have incorporated the ability to add a new user in which I built and employ a search algorithm to be able to assign the value in the hash tables.  
	I feel this met the objective I set out to do.  The goal was to modify an application that would better serve as a demonstration of my ability to use, create, and interact with data structures and algorithms.  I feel this application with its’ updates showcases those abilities.  
	As I was improving the artifact, I think one of the challenges I found was that I have not used a data structure in quite some time.  I spent a decent amount re-reading and learning hash tables and how to implement them for this project.  I also found that when removing the call to a text file, there were several areas I needed to completely re-do.  As such, I needed to build in how the interaction with the hash table occurred. Throughout this process I needed to relearn how to utilize and properly implement the hash table as well as perform such actions in Java since I had originally learned it in C++.


## Database

### Narrative
  The artifact is to be used for the database requirement.  The artifact I am using in is an Android application that is a mobile SIS system.  This exact artifact is being specifically created for these purposes.  I am using source code from the event manager Android application created in CS360 Mobile App Development and adapting or modifying the code to fit my needs for this application.  
	I selected this artifact as the original was the most interactivity in dealing with databases through my classwork and one I felt very confident with.  The database requirement needs to show my ability to create, query, and modify databases.  I am intending to showcase those abilities by creating a functional application that accomplishes all those elements by how the user interacts with the application.  The improvements in this case are taking the form of reviewing the original code from CS360 and determining what worked and how in it.  I then apply those concepts and some of that code into the new application to allow it do similarly. 
	I would say the application right now meets the requirements.  It is a functional application with multiple databases.  I have made the user interaction call to the databases to sperform functions and queries.  It allows for data to be added, viewed, updated, and deleted from the database.  It also calls on queries to the database to get information the application can use to perform functions such as checking for if a user exists, whether they have the proper credential to login, and determine what access level the user has and what information they get to see or interact with.  
I think what I learned is how in-depth this app has to be to be fully functional.  The original from CS360 had two views of login and event management.  This app is not fully functional as there are more elements to it that I’ve discovered through the development and design process. These include other databases I need to create for things like schedules and grades as well as the views for students, faculty, and academic admins and what they see and interact with.  Right now, the platform manager or super admin role is complete and meets all requirements.  In theory, I can modify what I have and remove the extra pieces and the app would look fully functional from a classroom perspective.  However, I wish to complete the extra pieces before the final submission at the end of the class.  Working in education, I feel this is an endeavor that is something that will be useful for me in the future and as such I want to have a fully functional product in the end. 
	


