/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;
import java.util.*;

/**
 *
 * @author JW Wan
 */
public class ROMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        // TODO code application logic here
        String username;
        String password;

        Scanner input = new Scanner(System.in);

        
        System.out.println("username: ");
        username = input.nextLine();

        System.out.println("password: ");
        password = input.nextLine();
 
        
        admin check = new admin(username, password);

        if(check.auth()) 
            System.out.println("You are logged in");
        else
            System.out.println("Log in failed");
    }
    
    }
    

