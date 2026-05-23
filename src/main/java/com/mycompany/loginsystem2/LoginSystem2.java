/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginsystem2;

// IMPORTS


// Import Scanner for user input
import java.util.Scanner;

// Import Pattern for regex validation
import java.util.regex.Pattern;

// Import Random for generating random message IDs
import java.util.Random;

/**
 *
 * @author Student
 */
public class LoginSystem2 {
   
// GLOBAL VARIABLES


// Scanner object for console input
static Scanner input = new Scanner(System.in);

// Counter to track number of messages sent
static int numMessagesSent = 0;


// USERNAME VALIDATION METHOD

// Method checks:
// Username must contain "_"
// Username must not exceed 5 characters
public static boolean checkUserName(String username) {

return username.contains("_")
&& username.length() <= 5;
}


// PASSWORD VALIDATION METHOD


// Password must:
// - Have at least 8 characters
// - Contain a capital letter
// - Contain a number
// - Contain a special character
public static boolean checkPasswordComplexity(String password) {

String regex =
"^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

return Pattern.matches(regex, password);
}


// CELL PHONE VALIDATION METHOD


// Number must:
// - Start with +27
// - Followed by 9 digits
public static boolean checkCellPhoneNumber(String number) {

String regex = "^\\+27\\d{9}$";

return Pattern.matches(regex, number);
}


// REGISTER USER METHOD


// Method validates username and password
public static String registerUser(String username,
String password) {

// Validate username
if (!checkUserName(username)) {

return "Username is not correct. "
+ "Must contain '_' and "
+ "be no more than 5 characters.";
}

// Validate password
if (!checkPasswordComplexity(password)) {

return "Password is not correct. "
+ "Must contain 8 characters, "
+ "capital letter, number "
+ "and special character.";
}

// Successful registration
return "Username and password "
+ "successfully captured.";
}


// LOGIN USER METHOD

// Method checks if login details match
public static boolean loginUser(String username,
String password,
String storedUsername,
String storedPassword) {

return username.equals(storedUsername)
&& password.equals(storedPassword);
}


// RETURN LOGIN STATUS METHOD

// Method returns login message
public static String returnLoginStatus(boolean status) {

if (status) {

return "Login successful! Welcome back!";

} else {

return "Username or password incorrect, "
+ "please try again.";
}
}


// GENERATE MESSAGE ID METHOD


// Method generates a random 10-digit number
public static String generateMessageID() {

Random random = new Random();

long number = 1000000000L
+ (long)(random.nextDouble() * 9000000000L);

return String.valueOf(number);
}


// RECIPIENT NUMBER VALIDATION METHOD


// Recipient number must:
// - Have international code
// - Be between 10 and 13 digits
public static boolean checkRecipientCell(String recipient) {

return recipient.matches("^\\+?[0-9]{10,13}$");
}


// MESSAGE LENGTH VALIDATION METHOD


// Message must not exceed 250 characters
public static boolean checkMessageLength(String message) {

return message.length() <= 250;
}


// CREATE MESSAGE HASH METHOD


// Method creates message hash using:
// First 2 digits of message ID
// Message number
// First and last words of message
public static String createMessageHash(String messageID,
int messageNumber,
String message) {

// Split message into words
String[] words = message.split(" ");

// First word in uppercase
String firstWord = words[0].toUpperCase();

// Last word in uppercase
String lastWord =
words[words.length - 1].toUpperCase();

// Return message hash
return messageID.substring(0, 2)
+ ":" + messageNumber
+ ":" + firstWord + lastWord;
}

    
    
    

    public static void main(String[] args) {
        // Variables for storing user details
String storedUsername = "";
String storedPassword = "";
String cellPhone;


// USERNAME SECTION


String username;

// Loop until correct username entered
while (true) {

System.out.print(
"Enter Username "
+ "(must contain '_' and max 5 chars): ");

username = input.nextLine();

// Validate username
if (checkUserName(username)) {

break;
}

System.out.println(
"Invalid username. "
+ "Example: user_");
}


// PASSWORD SECTION


String password;

// Loop until correct password entered
while (true) {

System.out.print(
"Enter Password "
+ "(8+ chars, 1 capital, "
+ "1 number, 1 special char): ");

password = input.nextLine();

// Validate password
if (checkPasswordComplexity(password)) {

break;
}

System.out.println(
"Invalid password. "
+ "Example: Password1!");
}

// Register user
String registerMessage =
registerUser(username, password);

// Display registration message
System.out.println(registerMessage);

// Store registered details
storedUsername = username;
storedPassword = password;


// CELL PHONE SECTION


// Loop until correct cell phone entered
while (true) {

System.out.print(
"Enter Cell Phone "
+ "(+27 followed by 9 digits): ");

cellPhone = input.nextLine();

// Validate cell phone
if (checkCellPhoneNumber(cellPhone)) {

System.out.println(
"Cell phone number "
+ "successfully added.");

break;
}

System.out.println(
"Invalid number. "
+ "Example: +27123456789");
}


// LOGIN SECTION


System.out.println("\n========== LOGIN ==========");

String loginUser;
String loginPass;

boolean status;

// Loop until successful login
while (true) {

System.out.print("Enter Username: ");
loginUser = input.nextLine();

System.out.print("Enter Password: ");
loginPass = input.nextLine();

// Check login details
status = loginUser(loginUser,
loginPass,
storedUsername,
storedPassword);

// Display login result
System.out.println(
returnLoginStatus(status));

// Stop loop if login successful
if (status) {

break;
}
}


// ASK NUMBER OF MESSAGES


System.out.print(
"\nHow many messages "
+ "would you like to send? ");

int totalMessages = input.nextInt();

// Clear scanner buffer
input.nextLine();


// MESSAGE LOOP


for (int i = 1; i <= totalMessages; i++) {

System.out.println(
"\n========== MESSAGE "
+ i + " ==========");


// GENERATE MESSAGE ID


String messageID = generateMessageID();


// RECIPIENT SECTION


System.out.print(
"Enter recipient number: ");

String recipient = input.nextLine();

// Validate recipient number
while (!checkRecipientCell(recipient)) {

System.out.println(
"Cell number incorrectly formatted.");

System.out.print(
"Please re-enter recipient number: ");

recipient = input.nextLine();
}


// MESSAGE SECTION


System.out.print(
"Enter your message: ");

String message = input.nextLine();

// Validate message length
while (!checkMessageLength(message)) {

System.out.println(
"Please enter a message "
+ "of less than 250 characters.");

System.out.print(
"Enter your message again: ");

message = input.nextLine();
}

// Increment total messages sent
numMessagesSent++;


// CREATE MESSAGE HASH


String messageHash =
createMessageHash(
messageID,
numMessagesSent,
message);

// Display message hash
System.out.println(
"\nMessage Hash: "
+ messageHash);


// MESSAGE OPTIONS MENU


System.out.println("\nChoose an option:");
System.out.println("1 - Send Message");
System.out.println("0 - Disregard Message");
System.out.println("2 - Store Message to send later");

int choice = input.nextInt();

// Clear scanner buffer
input.nextLine();


// SEND MESSAGE OPTION


if (choice == 1) {

System.out.println(
"\nMessage successfully sent.");

System.out.println(
"Message ID: "
+ messageID);

System.out.println(
"Recipient: "
+ recipient);

System.out.println(
"Message: "
+ message);

System.out.println(
"Total Messages Sent: "
+ numMessagesSent);
}


// DISREGARD MESSAGE OPTION


else if (choice == 0) {

System.out.println(
"\nPress 0 to delete the message.");

System.out.println(
"Message disregarded.");
}


// STORE MESSAGE OPTION


else if (choice == 2) {

System.out.println(
"\nMessage successfully stored.");
}


// INVALID OPTION


else {

System.out.println(
"\nInvalid option selected.");
}
}


// PROGRAM END


System.out.println(
"\nApplication Closed.");

// Close scanner
input.close();
}
}
      
    

