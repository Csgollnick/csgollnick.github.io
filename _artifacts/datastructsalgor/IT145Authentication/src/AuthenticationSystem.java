/**
 @author Chris Gollnick
 IT 145-R2920
 Updated for CS499
 Final Project
 */
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



