/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class Login {
     private String userID, userPassword, userType;
    private final String dataUser = "dataUsers.txt";
    
    public Login(){}
    
    public Login(String id, String Password, String type) {
        this.userID = id;
        this.userPassword = Password;
        this.userType = type;
    }
    public String getDataUser() {
        return dataUser;
    }
    public String getUserID() {
        return userID;
    }

    public String getUserType() {
        return userType;
    }

   
     public static void main(String[] args) {
        new Login().verifyLogin();
    }
      public void verifyLogin() {
        clearConsole();
        new Control().firstUser();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("\n---------- RETAIL ORDER MANAGEMENT SYSTEM ----------\n");
            System.out.println(">>> Please enter your ID number: ");
            //read customer data
            userID = scanner.nextLine().toUpperCase();
            boolean idFound = false;
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(getDataUser()));
                Object obj = null;
                while ((obj = ois.readObject()) != null) {
                    //compare data from input to the one in the file
                    if (((Customer) obj).getCusID().equals(userID)) {
                        idFound = true;
                        userPassword = ((Customer) obj).getCusPassword();
                        userType = ((Customer) obj).getCusType();
                    }
                }
            } catch (EOFException ex) {}
            catch (ClassNotFoundException ex) { ex.printStackTrace(); }
            catch (FileNotFoundException ex) { ex.printStackTrace(); }
            catch (IOException ex) { ex.printStackTrace(); }
            finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) { ex.printStackTrace(); }
            }
            if (idFound == true) {
                System.out.println("\n>>> Please enter your password: ");
                if (userPassword.equals(scanner.nextLine().toUpperCase())) {
                    System.out.println(" Your username is " + userID + " and pass is: " + userPassword);
                    if(userType.equals("ADMIN")){
                        clearConsole();
                        System.out.print("Succesfully Logged in as an Admin\n");
                        new Menu().mainMenuAdmin(userID, userType);
                    } 
                    if(userType.equals("CUSTOMER")){
                        clearConsole();
                        System.out.print("Succesfully Logged in as a Customer\n");
                        new Menu().orderManagement(userID, userType);
                    }
                    break;
                } else {
                    clearConsole();
                    System.out.println("The password is incorrect!");
                }
            } else {
                clearConsole();
                System.out.println("The ID number is incorrect! (" + userID + ").");
            }
        }
    }  
      
      
   public void clearConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }
    
 }
    

