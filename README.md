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
<details><summary markdown="span"> Click to View Appointment System in C++ Code</summary>
```cpp
// AppointmentService.cpp 
//

#include <iomanip>
#include <iostream>
#include <string>

#pragma warning(disable:4996);

//Arrays
long idNumbers[] = {220000001};
int appointmentDate[] = {01012023};
std::string appointmentDescription[] = {"This Is a Test"};

//Variable Declarations
int existingID = 0;
int dateOfNewAppointment = 0;
std::string descriptionOfNewAppointment;
int menuChoice = 0;
long idNumberOfNewAppointment = 0;

long CreateId()
{
    /*
    * Checks the ID Array
    * Creates a new ID number based on last entry
    * Adds it to the end of the array
    */
    int newIndex = sizeof(idNumbers) / sizeof(idNumbers[0]);  
    int lastIndex = newIndex - 1;                             
    long newId = idNumbers[lastIndex] + 1;                     
    idNumbers[newIndex] = newId;                                
    
    return idNumbers[newIndex];
}

int CreateDate()
{
   /*
   * Takes in a month, day and year
   * formats the day and month to a 2digit format
   * Verifies the day is in range of the associated month
   * formats the date to a mmddyyyy format
   */

    //Variables for This function 
    int month = 0;
    int day = 0;
    int year = 0;
    int formatDay = 0;
    int formatMonth = 0;
    int formatYear = 0;
    int switchMonth = 0;
    bool leapYear = false;
    std::string dayString;
    std::string monthString;
    std::string yearString;
    std::string fullDate;
    int date = 0;

    //Calulates the current date time
    std::time_t time = std::time(0);
    std::tm* nowTime = std::localtime(&time);

    /*
    * The following functions get the appointment date in chunks
    * This includes a year check to approve appointment not in the past
    *     */

    while (year < nowTime->tm_year + 1900)
    {
        std::cout << "Enter The year of the new appointment date in YYYY format: ";
        std::cin >> year;
        if (year < nowTime->tm_year + 1900)
        {
            std::cout << "ERROR: Year is in the past" << std::endl;
        }
    }

    
    while (month < 1 || month > 12) 
    {

        std::cout << "Enter the month of the appointment in number format(1-12): ";
        std::cin >> month;

        if (month < 1 || month > 12)
        {
            std::cout << "ERROR: Invalid Month number" << std::endl;
        }
    }
    
    while (day < 1 || day > 31)
    {
        std::cout << "Enter the day of the appointment in number format (1-31): ";
        std::cin >> day;
        if (day < 1 || day > 31)
        {
            std::cout << "ERROR: Invalid Day Number" << std::endl;
        }
    }

    //leap year check
    if (year % 4 == 0)
    {
        leapYear = true;
    }
    else
    {
        leapYear = false;
    }

    //format month to a 2-digit format
    if (month < 10)
    {
        formatMonth = ("%02d", month);
    }
    else
    {
        formatMonth = month;
    }

    //Loop to assign a value for a switch case statement
    if (month == (1 || 3 || 5 || 7 || 8 || 10 || 12))
    {
        switchMonth = 1;
    }
    else if (month == (4 || 6 || 9 || 11))
    {
        switchMonth = 2;
    }
    else if ((month == 2) && (leapYear = true))
    {
        switchMonth = 3;
    }
    else
    {
        switchMonth = 4;
    }

    //Check Day is in range of the given month
    switch (month) {
    case 1:
        if (day <= 31)
        {
            if (day < 10)
            {
                formatDay = ("%02d", day);
                break;
            }
            else
            {
                formatDay = day;
                break;
            }
        }
        else
        {
            std::cout << "Invalid Date Range" << std::endl;
            break;
        };
    case 2:
        if (day <= 30)
        {
            if (day < 10)
            {
                formatDay = ("%02d", day);
                break;
            }
            else
            {
                formatDay = day;
                break;
            }
        }
        else
        {
            std::cout << "Invalid Date Range" << std::endl;
            break;
        };
    case 3:
        if (day <= 29)
        {
            if (day < 10)
            {
                formatDay = ("%02d", day);
                break;
            }
            else
            {
                formatDay = day;
                break;
            }
        }
        else
        {
            std::cout << "Invalid Date Range" << std::endl;
            break;
        };

    case 4:
        if (day <= 28)
        {
            if (day < 10)
            {
                formatDay = ("%02d", day);
                break;
            }
            else
            {
                formatDay = day;
                break;
            }
        }
        else
        {
            std::cout << "Invalid Date Range" << std::endl;
            break;
        };
    }

    //Perform concactanation of the date values to a single date
    dayString = std::to_string(formatDay);
    monthString = std::to_string(formatMonth);
    yearString = std::to_string(formatYear);
    fullDate = monthString + dayString + yearString;
    date = stoi(fullDate);

    return date;
}

std::string CreateDescription() 
{
    /*
    * Function to store the description of the new appointment
    */
    std::string description;
    int count = 51;
    while (count > 50)
    {
        std::cout << "Enter the description of the appointment (50 Character Maximum): " << std::endl;
        std::cin >> description;
        if (description.length() > 50)
        {
            std::cout << "ERROR: Too Many Characters" << std::endl;
            count = description.length();
        }
        else
        {
            count = description.length();
        }

    }
    return description;

}

void AddAppointmentByID(long idNumber, int date, std::string description) 
{
    /*
    * Gets the index of the ID Number
    * Uses the same index across date and description arrays
    * Stores Values to arrays
    */

    long appointmentID = idNumber;
    int i = 0;

    int n = sizeof(idNumbers) / sizeof(idNumbers[0]);
    while (i < n)
    {
        if (idNumbers[i] == appointmentID)
        {
            break;
        }
        i++;
    }
    appointmentDate[i] = date;
    appointmentDescription[i] = description;

    std::cout << "Appointment Added" << std::endl;
}

void ViewAppointmentByID(long idNumber)
{
    /*
    * Gets the index of the ID Number
    * Uses the same index across date and description arrays
    * Prints the values stored at those points
    */

    long appointmentID = idNumber;
    int i = 0;

    int n = sizeof(idNumbers) / sizeof(idNumbers[0]);
    while (i < n)
    {
        if (idNumbers[i] == appointmentID)
        {
            break;
        }
        i++;
    }

    std::cout << idNumbers[i] << std::endl;
    std::cout << appointmentDate[i] << std::endl;
    std::cout << appointmentDescription[i] << std::endl;
}

void UpdateAppointmentByID(long idNumber, int newDate, std::string newDescription)
{
    /*
    * Gets the index of the ID Number
    * Uses the same index across date and description arrays
    * Stores the new values to the appropriate arrays
    */

    long appointmentID = idNumber;
    int i = 0;

    int n = sizeof(idNumbers) / sizeof(idNumbers[0]);
    while (i < n)
    {
        if (idNumbers[i] == appointmentID)
        {
            break;
        }
        i++;
    }
    appointmentDate[i] = newDate;
    appointmentDescription[i] = newDescription;

    std::cout << "Appointment Updated" << std::endl;
}

void DeleteAppointmentByID(long idNumber)
{
    /*
    * Gets the index of the ID Number
    * Uses the same index across date and description arrays
    * Deletes the index at those locations
    */

    long appointmentID = idNumber;
    int i = 0;

    int n = sizeof(idNumbers) / sizeof(idNumbers[0]);
    while (i < n)
    {
        if (idNumbers[i] == appointmentID)
        {
            break;
        }
        i++;
    }

    for (int j = i - 1; j < n; j++)
    {
        idNumbers[j] = idNumbers[j + 1];
        appointmentDate[j] = appointmentDate[j + 1];
        appointmentDescription[j] = appointmentDescription[j + 1];
    }
    n--;
}


int main()
{
    /*
    * Main Class
    * Produces a menu to let user choose an action
    * Action choice goes to a switch case to determine flow of processes
    * Program exits when choice is 5
    */   
    std::cout << "=====Appointment Service=====" << std::endl << std::endl;

    while (menuChoice != 5)
    {
        std::cout << "Enter the Menu Number of the Operation you would like to perform: " << std::endl;
        std::cout << "1. Create New Appointment" << std::endl;
        std::cout << "2. View an Appointment" << std::endl;
        std::cout << "3. Update an Existing Appointment" << std::endl;
        std::cout << "4. Delete an Existing Appointment" << std::endl;
        std::cout << "5. Exit and Quit" << std::endl << std::endl;

        std::cin >> menuChoice;
        std::cout << std::endl;

        switch (menuChoice)
        {
        case 1:
            idNumberOfNewAppointment = CreateId();
            dateOfNewAppointment = CreateDate();
            descriptionOfNewAppointment = CreateDescription();
            AddAppointmentByID(idNumberOfNewAppointment, dateOfNewAppointment, descriptionOfNewAppointment);
            break;

        case 2:
            std::cout << "Enter the appointment ID Number: ";
            std::cin >> existingID;
            ViewAppointmentByID(existingID);
            break;

        case 3:
            std::cout << "Enter the appointment ID Number: ";
            std::cin >> existingID;
            dateOfNewAppointment = CreateDate();
            descriptionOfNewAppointment = CreateDescription();
            UpdateAppointmentByID(existingID, dateOfNewAppointment,descriptionOfNewAppointment);
            break;

        case 4:
            std::cout << "Enter the appointment ID Number: ";
            std::cin >> existingID;
            DeleteAppointmentByID(existingID);
            break;
            
        case 5:
            std::cout << "Goodbye" << std::endl;
            break;
        }

    }

    std::cout << CreateId();
}
```
</details>
<br/>
{::options parse_block_html="false" /}


### Narrative
  This artifact was an appointment service application built as part of the final project of CS320.  The goal was to originally build a program that allowed a user to enter appointment details to create appointments as in a medical office setting.  
	This artifact enters my ePortfolio as part of the Software Design and Engineering aspect.  I chose this item as it was originally a more complex Java program relying on multiple class files and performing a variety methods and method checks to accomplish its??? tasks.  This would have served as a view of my abilities to work within the confines of the Java programming language, develop testing structures, and build a functional application that adheres to limitations of user requirements and books an appointment.  It also served as a proof of my ability to work off a prompt that served as a customer statement of work and be able to build the software from what they said.  The improvements in this case have been to clear up and automate some of the methods and checks utilized as well as convert the application to C++ from Java.
	As far as meeting the expected goal for this item, I would say I accomplished what I setout to do.  I took an existing java application that I wrote and converted to C++ and rebuilt it with more automations and optimizations to make it run smoother.  I think If I have more time at the end I may try to incorporate some of the other services that were part of the original project that I did not intend from the start.
	I think the biggest takeaway in this project was when I converted to C++.  My initial thought was to go line by line and converting to the languages syntax.  I later found out as I was doing it that this process would not really work.  As such I reviewed the Java version and determine what methods were used, what they did, and what the goal of them was.  Then took 



## Algorithm and Data Structure

{::options parse_block_html="true" /}

<details><summary markdown="span">Click to View Authentication System in Java Code</summary>
```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class AuthenticationSystem {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) throws NoSuchAlgorithmException {

        //Declare variables, setup Scanner, and instance of hash table
        int userChoice = 0;
        String userName;
        String passWord;
        int loginAttempts = 0;
        int nextValue = 0;
        int foundValue = 0;
        boolean authenticationSuccess = false;
        Scanner readInput = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        //Define HashTables, enumeration instance, and default values
        Map<String, Integer> userTable = new Hashtable<String, Integer>();
        Map<String, Integer> passwordTable = new Hashtable<String, Integer>();
        Enumeration <String> enumerateKeys = ((Hashtable<String, Integer>) userTable).keys();
        userTable.put("TestUser",0);
        passwordTable.put("TestPassword",0);




        while (userChoice != 3) {
            //Login prompt
            System.out.println("Please Select a number from the list below:");
            System.out.println("1 : Login");
            System.out.println("2 : Create Account");
            System.out.println("3 : Exit");
            System.out.println(" ");
            userChoice = scanner.nextInt();

            if (userChoice == 1) {

                while (loginAttempts < 3) {
                    //Logging In
                    System.out.println("Please enter username: ");
                    userName = readInput.nextLine();
                    System.out.println("Please enter password");
                    passWord = readInput.nextLine();

                    //Convert Password to MD5 hash
                    String original = passWord;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(original.getBytes());
                    byte[] digest = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                    }

                    /*
                    Checks for user in userTable
                    If found checks for password at same value in password table
                    if found then authenticates successfully
                     */
                    if (userTable.containsKey(userName) == true) {
                        foundValue = userTable.get(userName);

                        if(passwordTable.get(sb.toString())==foundValue)
                        {
                            authenticationSuccess = true;
                        }
                        else
                        {
                            authenticationSuccess = false;
                            System.out.println("Username Password Do Not Match");
                            loginAttempts =  loginAttempts + 1;
                        }

                    } else {
                        System.out.println("No User Found");
                    }

                    //Main Activity of Application
                    if (authenticationSuccess) {
                        System.out.println("Would you like to logout? Y/N");
                        String logChoice = readInput.nextLine();
                        if (logChoice.toUpperCase().charAt(0) == 'Y') {
                            System.out.println("Logged Off Successfully");
                            break;
                        } else {
                            authenticationSuccess = false;
                        }

                    }

                }
            } else if (userChoice == 2) {
                System.out.println("Please enter a new username: ");
                userName = readInput.nextLine();
                System.out.println("Please enter a new password");
                passWord = readInput.nextLine();

                //Convert Password to MD5 hash
                String original = passWord;
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(original.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }

                /*
                Loops over keys until the end
                Stores the value each time
                */
                while(enumerateKeys.hasMoreElements()) {

                    String tempKey = ((String) (enumerateKeys.nextElement()));
                    nextValue = userTable.get(tempKey);
                }
                //Place new credentials into Hash Table
                userTable.put(userName, nextValue);
                passwordTable.put(sb.toString(), nextValue);
            }
            else if (userChoice == 3) {
                System.out.println("GoodBye");
            }

        }
    }

}
```

</details>
<br/>

{::options parse_block_html="false" /}

### Narrative
  This artifact is an authentication system in Java.  It was originally developed as part of the IT-145 class.  The original purpose was to develop a Java application that could get a username and password from a user, compare them against a file, and then return success or failure based on what it found for matches.  
	This artifact is included as part of the Data Structures and Algorithms aspect of the ePortfolio.  The original included a basic algorithm to deal with creating a hash for the password.  As it was, the artifact could suffice in my understanding of algorithms and how to interact with them.  With my modifications it will serve better as my understanding of data structures, how to create them, manipulate them, create search algorithms, and interact with the hashing algorithm and its??? incorporation into my code.  Those improvements came in the form of removing the text file of usernames and passwords and replacing with hash tables.  I have incorporated the ability to add a new user in which I built and employ a search algorithm to be able to assign the value in the hash tables.  
	I feel this met the objective I set out to do.  The goal was to modify an application that would better serve as a demonstration of my ability to use, create, and interact with data structures and algorithms.  I feel this application with its??? updates showcases those abilities.  
	As I was improving the artifact, I think one of the challenges I found was that I have not used a data structure in quite some time.  I spent a decent amount re-reading and learning hash tables and how to implement them for this project.  I also found that when removing the call to a text file, there were several areas I needed to completely re-do.  As such, I needed to build in how the interaction with the hash table occurred. Throughout this process I needed to relearn how to utilize and properly implement the hash table as well as perform such actions in Java since I had originally learned it in C++.


## Database

Click the collapsable views to see three examples from the application	
	
<a href="https://github.com/Csgollnick/csgollnick.github.io/tree/main/MobileSIS">or To View The Entire Project Click Here</a>	

### Main Activity where the login window and user authentication occur

{::options parse_block_html="true" /}
<details><summary markdown="span">Click to view the Main Activity Code</summary>
```java
	package com.chrisgollnick.mobilesis;

	import android.content.Intent;
	import android.support.v7.app.AppCompatActivity;
	import android.os.Bundle;
	import android.text.Editable;
	import android.text.TextWatcher;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.Toast;

	public class MainActivity extends AppCompatActivity {

		private EditText username;
		private EditText password;
		private Button loginButton;
		private Button register;
		public UserDB userdb;
		public String roleAccess;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			username = (EditText) findViewById(R.id.usernameLogin);
			password = (EditText) findViewById(R.id.passwordLogin);
			loginButton = (Button) findViewById((R.id.loginbutton));
			register = (Button) findViewById(R.id.adminbutton);
			loginButton.setEnabled(false);
			userdb= new UserDB(this);

			username.addTextChangedListener((new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence u, int start, int count, int after) {

				}
				@Override
				public void onTextChanged(CharSequence u, int start, int before, int count) {

				}
				@Override
				public void afterTextChanged(Editable u) {
					if(u.toString().equals("")) {
						loginButton.setEnabled(false);
					} else {
						loginButton.setEnabled(true);
					}
				}
			}));

			loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String user = username.getText().toString();
					String pass = password.getText().toString();

					if (user.equals("") || pass.equals("")) {
						Toast.makeText(MainActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
					}

					roleAccess = userdb.checkUserPasswordRole(user, pass);
					if (roleAccess == "INVALID") {
						Toast.makeText(MainActivity.this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
					} else if (roleAccess == "Student") {
						startActivity(new Intent(MainActivity.this, StudentView.class));
					} else if (roleAccess == "PlatformManager") {
						startActivity(new Intent(MainActivity.this, PMView.class));
					} else if (roleAccess == "Faculty") {
						startActivity(new Intent(MainActivity.this, FacultyView.class));
					} else if (roleAccess == "AcademicAdmin") {
						startActivity(new Intent(MainActivity.this, AcademicAdminView.class));
					} else {
						startActivity(new Intent(MainActivity.this, UhOhError.class));
					}
				}
			});

			register.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					startActivity(new Intent(MainActivity.this, RegisterAdmin.class));
				}
			});

		}
	}
```

</details>
<br/>

{::options parse_block_html="false" /}

### User Management where you create, view, update, and delete users

{::options parse_block_html="true" /}
<details><summary markdown="span">Click to view User Management Code</summary>
```java
	package com.chrisgollnick.mobilesis;

	import android.content.Intent;
	import android.support.v7.app.AppCompatActivity;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.ImageButton;
	import android.widget.RadioButton;
	import android.widget.Toast;

	public class UserManagement extends AppCompatActivity {

		private RadioButton create;
		private RadioButton update;
		private RadioButton view;
		private RadioButton delete;
		private EditText first;
		private EditText last;
		private EditText password;
		private EditText userid;
		private EditText username;
		private EditText role;
		private Button submit;
		private ImageButton homebutton;
		private int radio;
		public MainActivity main;
		public UserDB userdb;


		public void onRadioButtonClicked(View view) {
			boolean checked = ((RadioButton) view).isChecked();

			switch(view.getId()) {
				case R.id.adduser:
					if (checked)
						radio = 1;
					break;
				case R.id.viewuser:
					if(checked)
						radio = 2;
					break;
				case R.id.updateuser:
					if(checked)
						radio = 3;
					break;
				case R.id.deleteuser:
					if(checked)
						radio = 4;
					break;
			}
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_user_management);

			create = (RadioButton) findViewById(R.id.adduser);
			update = (RadioButton) findViewById(R.id.updateuser);
			view = (RadioButton)  findViewById(R.id.viewuser);
			delete = (RadioButton) findViewById(R.id.deleteuser);
			first = (EditText) findViewById(R.id.firstname);
			last = (EditText)  findViewById(R.id.lastname);
			password = (EditText) findViewById(R.id.password);
			userid = (EditText) findViewById(R.id.userid);
			username = (EditText) findViewById(R.id.username);
			role = (EditText) findViewById(R.id.role);
			submit = (Button) findViewById(R.id.submitbutton);
			homebutton = (ImageButton) findViewById(R.id.home);
			userdb = new UserDB(this);
			main = new MainActivity();


			submit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					String firstname = first.getText().toString();
					String lastname = last.getText().toString();
					String upassword = password.getText().toString();
					String uname = username.getText().toString();
					String sID = userid.getText().toString();
					int id = Integer.parseInt(sID);
					String uRole = role.getText().toString();

					//Add User
					if(radio == 1) {
						boolean exist = userdb.checkUser(id);
						if(exist = false){
							boolean state = userdb.insertData(id,firstname, lastname, upassword, uRole);
							if(state = true) {
								Toast.makeText(UserManagement.this, "User Added", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(UserManagement.this, "User Not Added", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(UserManagement.this, "User Already Exists", Toast.LENGTH_SHORT).show();
						}
						//View User
					} else if (radio == 2) {
						first.setText(userdb.GetfirstName(id));
						last.setText(userdb.GetlastName(id));
						role.setText(userdb.GetRole(id));
						username.setText(userdb.GetuserName(id));
					//Update User
					} else if(radio == 3) {

						if(uname != null || uname != "" ) {
							userdb.UpdateUserName(id, uname);
						}
						if(firstname != null || firstname != "") {
							userdb.UpdateFirstName(id, firstname);
						}
						if(lastname != null || lastname !="") {
							userdb.UpdateLastName(id, lastname);
						}
						if(upassword != null || upassword != "") {
							userdb.UpdatePassword(id, upassword);
						}
						if(uRole != null || uRole != "") {
							userdb.UpdateRole(id, uRole);
						}
						Toast.makeText(UserManagement.this, "Updates Complete", Toast.LENGTH_SHORT).show();
					}
					//delete user
					else if(radio == 4) {
						userdb.deleteUser(id);
						Toast.makeText(UserManagement.this, "User Deleted", Toast.LENGTH_SHORT).show();
					}



				}
			});

			homebutton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					String roleaccess = main.roleAccess;
					if(roleaccess == "Platform Manager") {
						startActivity(new Intent(UserManagement.this, PMView.class));
					} else if (roleaccess == "Academic Admin") {
						startActivity(new Intent(UserManagement.this, AcademicAdminView.class));
					} else if (roleaccess == "Faculty") {
						startActivity(new Intent(UserManagement.this, FacultyView.class));
					} else if (roleaccess == "Student") {
						startActivity(new Intent(UserManagement.this, StudentView.class));
					}

				}
			});


		}


	}
```
</details>
<br/>
{::options parse_block_html="false" /}
		
### User Database where the database and tables are created and all functions that query the database reside for user management
{::options parse_block_html="true" /}
<details><summary markdown="span">Click to view User Database code</summary>
```java
	package com.chrisgollnick.mobilesis;

	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	import android.nfc.Tag;
	import android.support.v4.content.res.TypedArrayUtils;
	import android.util.Log;

	public class UserDB extends SQLiteOpenHelper {
		public static final String DATABASE_NAME = "users.db";
		public static final int VERSION = 1;


		public UserDB(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}

		public static final class UsersTable {
			public static final String TABLE_NAME = "users_table";
			public static final String COL_1 = "_id";
			public static final String COL_2 = "USERNAME";
			public static final String COL_3 = "PASSWORD";
			public static final String COL_4 = "ROLE";
			public static final String COL_5 = "FIRSTNAME";
			public static final String COL_6 = "LASTNAME";

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + UsersTable.TABLE_NAME + " (" +
					UsersTable.COL_1 + " INTEGER primary key, " +
					UsersTable.COL_2 + " TEXT, " +
					UsersTable.COL_3 + " TEXT, " +
					UsersTable.COL_4 + " TEXT, " +
					UsersTable.COL_5 + " TEXT, " +
					UsersTable.COL_6 + " TEXT) ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
							  int newVersion) {
			db.execSQL("drop table if exists " + UsersTable.TABLE_NAME);
			onCreate(db);
		}




		/*
		* ***********************
		* Authentication Methods
		* ***********************
		* */
		//Method to check for username and password combinations for authentication

		public String checkUserPasswordRole(String username, String password) {

			String role ="";
			SQLiteDatabase db = getReadableDatabase();
			String sql = "Select * From " + UsersTable.TABLE_NAME + " where username = ? and password = ?";
			Cursor cursorUP = db.rawQuery(sql, new String[]{username, password});
			if (cursorUP.getCount() > 0) {
				boolean auth = true;
				if(cursorUP.moveToFirst()) {
					do {
						role = cursorUP.getString(4);
					} while (cursorUP.moveToNext());

				}

			} else {
				boolean auth = false;
				role = "INVALID";
			}
			return role;
		}

		/*
		* **********************
		* Creation Data Methods
		* **********************
		* */

		//Method to add to database
		public boolean insertData(Integer id, String firstName, String lastName, String password, String role) {
			SQLiteDatabase db = getWritableDatabase();

			char firstletter = firstName.charAt(0);
			String letter = String.valueOf(firstletter);
			String username = letter + lastName;

			ContentValues contentValues = new ContentValues();
			contentValues.put(UsersTable.COL_1, id);
			contentValues.put(UsersTable.COL_2, username);
			contentValues.put(UsersTable.COL_3, password);
			contentValues.put(UsersTable.COL_4, role);
			contentValues.put(UsersTable.COL_5, firstName);
			contentValues.put(UsersTable.COL_6, lastName);


			long result = db.insert(UsersTable.TABLE_NAME, null, contentValues);
			if (result == -1) {
				return false;
			} else {
				return true;
			}
		}

		//Method to check for user ID existence
		public boolean checkUser(int id) {
			SQLiteDatabase db = getReadableDatabase();
			Cursor cursorUser = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE id = ?", new String[]{Integer.toString(id)});
			if (cursorUser.getCount() > 0) {
				return true;
			} else {
				return false;
			}
		}

		public boolean CheckAdminRegisterCode (String code) {
			boolean found = false;
			String[] codes = {"YA9Z6MBPNJYP","W8X6ZBKUGXP7","9V76SY6F7RYG",
					"N974QGDYFW78","RXZQ7SYDMY8E","94H9UU664U4R",
					"3WKM2Y8EP8YE","C9BG2F9QPY44","SY5PJQFDEYMP",
					"QX3HX3NBDA3N","Z4QGDBZFYC3W","KA6RM84HAN2B","29Y96UT2B2RH",
					"H74LR8Y6P5ZM","BF2LUJXQ6VJH","VBDQPJ3KEU7A",
					"SJWMMUNP39LD","HHHA68M53988","5ABVSFLJYATD",
					"9FK62S25WLC9"};
			for(int i = 0; i < 19; i++) {
				if(codes[i] == code) {
					found = true;
					break;
				} else {
					found = false;
				}
			}
			return found;
		}


		/*
		*****************************
		* Get/View The Data Methods
		* ***************************
		* */
		//get firstname
		public String GetfirstName(int ID) {
			String fname = "";
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
			if (cursor.moveToFirst()) {
				do {
					fname = cursor.getString(5);
				} while (cursor.moveToNext());
			}
			cursor.close();
			return fname;
		}
		//get lastname
		public String GetlastName(int ID) {
			String lname = "";
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
			if (cursor.moveToFirst()) {
				do {
					lname = cursor.getString(6);
				} while (cursor.moveToNext());
			}
			cursor.close();
			return lname;
		}

		//get username
		public String GetuserName(int ID) {
			String uname = "";
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
			if (cursor.moveToFirst()) {
				do {
					uname = cursor.getString(2);
				} while (cursor.moveToNext());
			}
			cursor.close();
			return uname;
		}

		//get role
		public String GetRole(int ID) {
			String role = "";
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
			if (cursor.moveToFirst()) {
				do {
					role = cursor.getString(5);
				} while (cursor.moveToNext());
			}
			cursor.close();
			return role;
		}

		/*
		************************
		* Update Data Methods
		* **********************
		* */
		public boolean UpdateFirstName (int ID, String firstName) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(UsersTable.COL_5, firstName);
			int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
			return rowsUpdated > 0;
		}
		public boolean UpdateLastName (int ID, String lastName) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(UsersTable.COL_6, lastName);
			int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
			return rowsUpdated > 0;
		}
		public boolean UpdateUserName (int ID, String userName) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(UsersTable.COL_2, userName);
			int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
			return rowsUpdated > 0;
		}
		public boolean UpdatePassword (int ID, String password) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(UsersTable.COL_3, password);
			int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
			return rowsUpdated > 0;
		}
		public boolean UpdateRole (int ID, String role) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(UsersTable.COL_4, role);
			int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
			return rowsUpdated > 0;
		}

		/*
		 * *****************
		 * Delete by ID
		 * *****************
		 * */
		public boolean deleteUser(int id) {
			SQLiteDatabase db = getWritableDatabase();
			int rowsDeleted = db.delete(UsersTable.TABLE_NAME, UsersTable.COL_1 + " = ?",
					new String[] { Integer.toString(id) });
			return rowsDeleted > 0;
		}

	}
```
</details>
<br/>
{::options parse_block_html="false" /}

### Narrative
  The artifact is to be used for the database requirement.  The artifact I am using in is an Android application that is a mobile SIS system.  This exact artifact is being specifically created for these purposes.  I am using source code from the event manager Android application created in CS360 Mobile App Development and adapting or modifying the code to fit my needs for this application.  
	I selected this artifact as the original was the most interactivity in dealing with databases through my classwork and one I felt very confident with.  The database requirement needs to show my ability to create, query, and modify databases.  I am intending to showcase those abilities by creating a functional application that accomplishes all those elements by how the user interacts with the application.  The improvements in this case are taking the form of reviewing the original code from CS360 and determining what worked and how in it.  I then apply those concepts and some of that code into the new application to allow it do similarly. 
	I would say the application right now meets the requirements.  It is a functional application with multiple databases.  I have made the user interaction call to the databases to sperform functions and queries.  It allows for data to be added, viewed, updated, and deleted from the database.  It also calls on queries to the database to get information the application can use to perform functions such as checking for if a user exists, whether they have the proper credential to login, and determine what access level the user has and what information they get to see or interact with.  
       I think what I learned is how in-depth this app has to be to be fully functional.  The original from CS360 had two views of login and event management.  This app has more elements to it that were discovered through the development and design process. These include multiple databases and tables for schedules, grades, users, students, authentication, and platform admin registration. Multiple classes that may lead to other classes due to logical flow of the application screens is also prevalent.  Working in education, I feel this is an endeavor that is something that will be useful for me in the future.  This is a culmination of understanding mobile application development, ineraction with databases, and attempting to incorporate features, actions, and workflows that I've witnessed as failures from othere SIS systems I have had to work with.  There is still room for a lot more expansion including time based fuynction such as rotating schedules, integration with Google worksspace API's, and other such features that a teacher may find useful if discovered through an interview with one.
	


