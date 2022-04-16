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
