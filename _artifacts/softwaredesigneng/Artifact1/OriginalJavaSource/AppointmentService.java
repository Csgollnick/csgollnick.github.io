/* Author Name: Chris Gollnick
 * Date: 07 February 2021
 * Course ID: CS320-T3228
 * Description: AppointmentService.java contains the function to add a new appointment and delete an existing appointment.s
 * */



import java.util.*;
import java.time.*;
import java.lang.*;
import java.text.*;

public class AppointmentService {

    //Call in Appointment class for use in this class
    Appointment appt = new Appointment();
    //Declare Variables to store return values
    String newApptId;
    String newApptDescription;
    Date newApptDate;
    //Declare variables for index searching functions
    public int index;
    //Declare Variable for deletion method
    public String message;


    //Function to Add appointments by ID
    public void AddAppointment(String idNum, String year, String day, String month, String descriptor) {
        appt.appointmentID(idNum);																	//passes idNum to the AppointId Function in appointment class
        if(appt.errorNum ==  0) {																	//Checks if function passed without error
            appt.appointmentDate(year, month, day);													//Pass Day, Month, Year to appoint date function in appointment class
            if(appt.errorDate == 0) {																//Checks if function passed without error
                appt.appointmentDescription(descriptor);											//Pass Descriptor to appointment Description function in appointment class
                if(appt.errorDesc == 0) {															//Checks if function pass without error
                    newApptId = appt.getApptId();													//Gets return value of appointment ID and sets to newApptID
                    newApptDate = appt.getDate();													//Gets return value of Appointment date and sets to newApptDate
                    newApptDescription = appt.getApptDesc();										//Gets return value of appoint description and sets to newApptDescription
                    this.NewAppointmentConfirmation(newApptId, newApptDate, newApptDescription);	//Pass the returned values to the Appointment Confirm function to out put the details
                } else {
                    throw new IllegalArgumentException("ERROR WITH DESCRIPTION");					//Throw Exception if Description function fails
                }
            } else {
                throw new IllegalArgumentException("ERROR WITH DATE");								//Throw exception if date function fails
            }
        } else {
            throw new IllegalArgumentException("ERROR WITH ID");									//Throw exception if Id function fails
        }
    }

    //Function to Delete appointment by ID
    public String DeleteAppointment(String apptIdNum) {
        List<String> idList = Arrays.asList(appt.apptID);											//Create List of values from apptID Array
        if(idList.contains(apptIdNum)) {															//Checks if apptIdNum is in the list
            String idVal = apptIdNum;																//store apptIdNum in idVal variable
            index = getIndexOfElementinArray(appt.apptID, idVal);									//Store the found index of the search to index
        } else {
            throw new IllegalArgumentException("ID DOES NOT EXIST");								//If ID not found in list throw exception
        }
        String[] tempAptId = new String[appt.apptID.length - 1];									//Create tempID array 1 less than apptId Array
        for (int i = 0, j = 0; i < appt.apptID.length; i++) {										//Create for loop to run while i less than the length of ID array
            if (i != index) {																		//Run as long as i is not the index
                tempAptId[j++] = appt.apptID[i];													//Increase index of tempID array by one and store the appointment ID value there
                appt.apptID = tempAptId.clone();													//Copy the tempID back to the original Array
            }
        }
        Date[] tempDate = new Date[appt.apptDates.length - 1];										//Create tempDate array 1 less than the size of the apptDate array
        for (int i = 0, j = 0; i < appt.apptDates.length; i++) {									//Create for loop to run while i less than the length of date array
            if (i != index) {																		//Run as long as i is not the index
                tempDate[j++] = appt.apptDates[i];													//Increase index of tempDate array by one and store the Date value there
                appt.apptDates = tempDate.clone();													//Copy the tempDate back to the original Array
            }
        }
        String[] tempAptDsc = new String[appt.apptDesc.length - 1];									//Create tempDEscrption array 1 less than the size of the apptDescription array
        for (int i = 0, j = 0; i < appt.apptDesc.length; i++) {										//Create for loop to run while i less than the length of description array
            if (i != index) {																		//Run as long as i is not the index
                tempAptDsc[j++] = appt.apptDesc[i];													//Increase index of tempDescription array by one and store the Description value there
                appt.apptDesc = tempAptDsc.clone();													//Copy the tempDescription back to the original Array
            }
        }
        List<String> newIdList = Arrays.asList(appt.apptID);										//Creates new list from apptId Array
        if(newIdList.contains(apptIdNum)) {															//Checks if apptIdNum is in the list
            throw new IllegalArgumentException("ERROR DELETION FAILURE");							//If found, throws error exception
        } else {
            message = ("DELETION SUCCESS!");														//If not found, stores success message
        }
        return message;																				//Returna success message
    }

    //Function to output return values for new appointment
    public String NewAppointmentConfirmation(String id, Date date, String desc) {
        return ("Appointment ID: " + id  															//Returns formatted statement listing the newly added appointment information
                + "\nAppointment Date: " + date
                + "\nAppointment Description: " + desc);
    }

    //Method to find the index of an element in an Date array
    public static int getIndexOfDate (Date dateArray[], Date searchDate) {

        if (dateArray == null) {
            return -1;																	//Returns -1 if arrayName is null
        }
        int arrayLen = dateArray.length;														//Gets the length of the array
        int searchIdx = 0;																		//Initialize first index to check is position 0

        while (searchIdx < arrayLen) {															//Continues while the index is less than the length of the array
            if (dateArray[searchIdx] == searchDate) {
                return searchIdx;																//Returns the index when search term is found
            } else {
                searchIdx = searchIdx + 1;														//If not found at current position, increment by 1
            }
        }
        return -1;
    }

    //Method to find the index of an element in ID or Descriptor array
    public static int getIndexOfElementinArray (String arrayName[], String searchTerm) {

        if (arrayName == null) {
            return -1;																	//Returns -1 if arrayName is null
        }
        int arrayLength = arrayName.length;														//Gets the length of the array
        int searchindex = 0;																	//Initialize first index to check is position 0

        while (searchindex < arrayLength) {														//Continues while the index is less than the length of the array
            if (arrayName[searchindex] == searchTerm) {
                return searchindex;																//Returns the index when search term is found
            } else {
                searchindex = searchindex + 1;													//If not found at current position, increment by 1
            }
        }
        return -1;
    }
}
