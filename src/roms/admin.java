/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

import java.util.Scanner;

/**
 *
 * @author Abdifatah Feisal
 */
public class admin {
    

        private String username;
        private String password;
        private  String File = ("Database/Admin.csv");
        Scanner scan = new Scanner (ROMS.class.getResourceAsStream(File)).useDelimiter(",");
        String user = scan.nextLine();
        String pass = scan.nextLine();

        public admin(String user, String pass){
            username = user;
            password = pass;
        }

        public boolean auth(){
            if((username.equals (user)) && (password.equals (pass)))
                return true;
            else
                return false;
        }

    
    
    
}
