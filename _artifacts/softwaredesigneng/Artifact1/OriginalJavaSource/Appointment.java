/*Author Name: Chris Gollnick
 * Date: 07 February 2021
 * Course ID: CS320-T3228
 * Description: Appointment.java runs the core functions of the program.  It allows for creating the unique ID number,
 * 				checks for duplicate and null ID, Creates the appointment date, checks for the date being in the past,
 * 				Checks the description, and stores all the info in appropriate arrays.
 * */

import java.util.*;
import java.time.*;
import java.lang.*;
import java.text.*;

public class Appointment {

    //Format Date
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    //Array Declarations
    public String[] apptID = {"1","999999999"};
    public String[] apptDesc = {"This Test Appointment", "TestDeletetest"};
    public Date[] apptDates = {new Date("4/6/2021"), new Date("12/12/2112")};

    //error counter declarations
    public int errorNum = 0;
    public int errorDate = 0;
    public int errorDesc = 0;

    //Increase Array Declarations
    public int arrSize;
    public String newElement;
    public Date newDate;
    public int arrDateSize;

    //Return Store declarations
    public String storeID;
    public Date storeDate;
    public String storeDescription;

    //Date Variable Declarations
    public int numMonth = 0;
    public int numDay = 0;
    public int numYear = 0;

    //Function to create an ID Object
    public void appointmentID (String idNum) {
        List<String> apIdList = Arrays.asList(apptID);									//create list of values from apptID array
        if (idNum == null) {															//Check if passed idNum is null
            errorNum = 1;																//change errorNum to 1 if null
            throw new IllegalArgumentException("CAN NOT BE NULL");						//Throw exception if null
        } else if(idNum.length() > 10 ) {												//Check if idNum is longer than 10 characters
            errorNum = 1;																//Change errorNum to 1 if too long
            throw new IllegalArgumentException("EXCEEDS CHARACTER LENGTH (10)");		//Throw exception if too long
        } else if (apIdList.contains(idNum)) {											//check if idNum is in list
            errorNum = 1;																//change errorNum to 1 if duplicate found
            throw new IllegalArgumentException("DUPLICATE ERROR");						//Throw exception if duplicate
        } else {																		//Runs if passes all three checks
            if (errorNum == 0) {														//checks that errorNum is 0
                arrSize = apptID.length;												//set arrsize to length of apptID array
                newElement = idNum;														//set newElement to idNum
                apptID = increaseArraySize(arrSize, apptID, newElement);				//run function to increase array size and store idnum value
                storeID =  idNum;														//set storeId to idNum
            } else {
                throw new IllegalArgumentException("ERROR VALUE CHECK FAILURE");		//If makes it to final step but errorNum is not zero throw exception
            }
        }
    }
    //Function to check and store Data of appointment
    public void appointmentDate(String year, String month, String day) {
        if(year == null) {																//Checks if year is null
            errorDate = 1;																//Change errorDate to 1
            throw new IllegalArgumentException("DATE IS REQUIRED");						//Throw exception if null
        } else {
            numYear = Integer.parseInt(year);											//Convert year to integer and store in numYear
        }

        if(month == null) {																//Check if month is null
            errorDate = 1;																//Change errorDate to 1
            throw new IllegalArgumentException("DATE IS REQUIRED");						//Throw exception if null
        } else {
            numMonth = convertMonth(month);												//pass month to function to convert to integer valuation
        }

        if(day == null) {																//Check if Day is null
            errorDate = 1;																//Change errorDate to 1
            throw new IllegalArgumentException("DATE IS REQUIRED");						//Throw exception if null
        } else {
            numDay = Integer.parseInt(day);												//Convert day to integer and store to numDay
        }

        Calendar cal = Calendar.getInstance();											//Create Calendar instance of cal from Calendar class
        cal.set(Calendar.MONTH, numMonth);												//Set cal month
        cal.set(Calendar.DATE, numDay);													//Set cal day
        cal.set(Calendar.YEAR, numYear);												//Set cal year
        Date apptDate= cal.getTime();													//Create appointment Date from cal sets
        Date currentDate = new Date();													//Call Date class to get todays date

        if(apptDate.before(currentDate)) {												//Check if appointment Date is before todays date
            errorDate = 1;																//Change errorDate to 1
            throw new IllegalArgumentException
                    ("APPOINTMENT DATE CAN NOT BE IN THE PAST");								//Throw Exception if appointment date is before todays date
        } else {
            if( errorDate == 0) {
                arrDateSize = apptDates.length;												//Set arrDateSize to length of apptDates array
                newDate = apptDate;															//set new date to apptDate
                apptDates = increaseArraySizeDates(arrDateSize, apptDates, newDate);		//Run function to increase apptDates array and store new appointment date
                storeDate = apptDate;														//Store apptDate to storeDate
            }
        }
    }

    //Function to store description of appointment
    public void appointmentDescription(String description) {
        if( description == null) {														//Checks if description is null
            errorDesc = 1;																//Change ErrorDesc to 1
            throw new IllegalArgumentException("CAN NOT BE NULL");						//Throw null exception
        } else if(description.length() > 50) {											//Check if Description too long
            errorDesc = 1;																//Change errorDesc to 1 if too long
            throw new IllegalArgumentException("EXCEEDS CHARACTER LIMIT (50");			//Throw exception if too long
        } else {
            if(errorDesc == 0) {														//if errorDesc is 0, runs store
                arrSize = apptDesc.length;												//set array size to length of apptDesc array
                newElement = description;												//set new element to description
                apptDesc = increaseArraySize(arrSize, apptDesc, newElement);			//increase array size and store description
                storeDescription = description;											//Set storeDescription to description
            }
        }
    }

    //Test Function return ID on successful storage
    public String getApptId() {
        return storeID;																	//output storeID
    }

    //Test Method to return Date on Successful Storage
    public Date getDate() {
        return storeDate;																//output storeDate
    }

    //Test Method to return Description on successful storage
    public String getApptDesc() {
        return storeDescription;														//output storeDescription
    }

    //Convert Month to Int
    public int convertMonth(String month) {
        List <String> janList = Arrays.asList("January", "Jan", "january", "jan", "JANUARY", "JAN", "1", "01");			//Create List of possible January entries
        List <String> febList = Arrays.asList("February", "Feb", "february", "feb", "FEBRUARY", "FEB", "2","02");		//create list of possible February entries
        List <String> marList = Arrays.asList("March", "Mar", "march", "mar", "MARCH", "MAR", "3", "03");				//Create list of possible March entries
        List <String> aprList = Arrays.asList("April", "Apr", "april", "apr", "APRIL", "APR", "4", "04");				//Create list of possible April entries
        List <String> mayList = Arrays.asList("May", "may", "MAY", "5", "05");											//Create list of possible May entries
        List <String> junList = Arrays.asList("June", "Jun", "june", "jun", "JUNE", "JUN", "6", "06");					//Create list of possible June entries
        List <String> julList = Arrays.asList("July", "Jul", "july", "jul", "JULY", "JUL", "7", "07");					//Create list of possible July entries
        List <String> augList = Arrays.asList("August", "Aug", "august", "aug", "AUGUST", "AUG", "8", "08");			//Create list of possible August entries
        List <String> sepList = Arrays.asList("September", "Sep", "september", "sep","SEPTEMBER", "SEP", 				//Create list of possible September entries
                "Sept", "sept", "SEPT", "9", "09");
        List <String> octList = Arrays.asList("October", "Oct", "october", "oct", "OCTOBER", "OCT", "10");				//Create list of possible October entries
        List <String> novList = Arrays.asList("November", "Nov", "november", "nov", "NOVEMBER", "NOV", "11");			//Create list of possible November entries
        List <String> decList = Arrays.asList("December", "Dec", "december", "dec", "DECEMBER", "DEC", "12");			//Create list of possible December entries

        int numMonth;													//Create variable numMonth
        if(janList.contains(month)) {									//Check if month in January list
            numMonth = 0;													//If found set numMonth to zero
        } else if (febList.contains(month)) {							//Check if month in February list
            numMonth = 1;													//If found set numMonth to one
        } else if (marList.contains(month)) {							//Check if month in March list
            numMonth = 2;													//If found set numMonth to two
        } else if (aprList.contains(month)) {							//Check if month in April List
            numMonth = 3;													//if Found set numMonth to three
        } else if (mayList.contains(month)) {							//Check if month in May List
            numMonth = 4;													//If found set numMonth to four
        } else if (junList.contains(month)) {							//Check if month in June List
            numMonth = 5;													//If found set numMonth to five
        } else if (julList.contains(month)) {							//Check if month in July List
            numMonth = 6;													//If found set numMonth to six
        } else if (augList.contains(month)) {							//check if month in August List
            numMonth = 7;													//if found set numMonth to seven
        } else if (sepList.contains(month)) {							//check if month in September list
            numMonth = 8;													//if found set numMonth to eight
        } else if (octList.contains(month)) {							//check if month in October list
            numMonth = 9;													//if found set numMonth to nine
        } else if (novList.contains(month)) {							//check if month in November list
            numMonth = 10;													//if found set numMonth to ten
        } else if (decList.contains(month)) {							//Check if month in December list
            numMonth = 11;													//if found set numMonth to 11
        } else {
            throw new IllegalArgumentException("INVALID MONTH ENTRY");	//if month not found in any list throw exception
        }
        return numMonth;												//return the numMonth integer value for month
    }

    //Function to increase the array size to store new elements
    public static String[] increaseArraySize(int arrSize, String arr[], String newElement) {
        int i;																					//Declare variable i
        String newarr[] = new String[arrSize + 1];												//Create newarr for strings, set equal to the array size plus 1
        for(i = 0; i < arrSize; i++)															//Run for loop to copy elements to new array
            newarr[i] = arr[i];
        newarr[arrSize] =  newElement;															//Stores the new element in the last index

        return newarr;																			//Return the new array
    }
    //Function to increase the array size to store new elements for Date
    public static Date[] increaseArraySizeDates(int arrDateSize, Date arr[], Date newDate) {
        int i;																					//Declare variable i
        Date newarr[] = new Date[arrDateSize + 1];												//Create new Date array equal to passed in Date Array size plus 1
        for(i = 0; i < arrDateSize; i++)														//Run loop to copy element to new array
            newarr[i] = arr[i];
        newarr[arrDateSize] =  newDate;															//Store the new date in last index

        return newarr;																			//return the new array
    }

}
