/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author Abdifatah Feisal
 */
public class Control {
   //Generate number function
     public static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }
     //Autogenerate first user if system hasn't been run
    public void firstUser() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(new Login().getDataUser()));
                Customer customer = new Customer();
                customer.setCusID("UID" + Integer.toString(generateNum(1000, 9999)));
                customer.setCusPassword(Integer.toString(generateNum(100, 999)));
                customer.setCusType("ADMIN");
                customer.setCusName("ADMIN");
                customer.setCusAddress("-");
                customer.setCusContact("-");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                customer.setCusDate(dateFormat.format(date));
                oos.writeObject(customer);
                System.out.println("WELCOME NEW USER!\n");
                System.out.println("ID NUMBER: " + customer.getCusID());
                System.out.println("PASSWORD:  " + customer.getCusPassword());
                System.out.println("USER TYPE: " + customer.getCusType());
                System.out.println("\nAuto-generated login credentials for new system!");
                System.out.println(customer.getCusID());
                customer=null;
                oos.flush();
                oos.close();
                System.out.println("Check 1");

            } catch (Exception e) {}
        } catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    
}
    //add customer function
    public void addCustomer(String password, String name, String address, String contact) {
        //stores in an array list
        ArrayList<Customer> tempCustomer = new ArrayList<Customer>();
        //read data type from inputstream
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                //adds customer
                tempCustomer.add((Customer)obj);
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
        //stores objects in to an output stream
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new Login().getDataUser()));
            Customer customer = new Customer();
            customer.setCusID("UID" + Integer.toString(generateNum(1000, 9999)));
            customer.setCusPassword(password);
            customer.setCusType("CUSTOMER");
            customer.setCusName(name);
            customer.setCusAddress(address);
            customer.setCusContact(contact);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            customer.setCusDate(dateFormat.format(date));
            //No duplication of id's
            //compares new id and id's in file 
            boolean idExists = false;
            for(Customer eachCustomer:tempCustomer){
                if(eachCustomer.getCusID().equals(customer.getCusID())) {
                    idExists = true;
                }
            }
            
            if (idExists == false) {
                tempCustomer.add(customer);
            } else {
                addCustomer(password, name, address, contact);
            }
            for(Customer eachCustomer: tempCustomer){
                oos.writeObject(eachCustomer);
            }
            System.out.println("\nCustomer '" + customer.getCusName() + "' is successfully registered!");
            System.out.println(customer.toString());
            customer=null;
            tempCustomer.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) {}
    }
    public void searchCustomer(String id) {
        boolean idFound = false;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if(((Customer)obj).getCusID().equals(id)){
                    idFound = true;
                    System.out.println("\nSearch result for '" + id + "':");
                    System.out.println(((Customer)obj).toString());
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
        
        if (idFound == false) {
            System.out.println("\nCustomer '" + id + "' is not found!\n");
        }
    }
    
    // overloading
    public static Customer searchCustomer(String id, boolean a)
    {
        ObjectInputStream ois = null;
        Customer customer = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if(((Customer)obj).getCusID().equals(id)){
                    customer = (Customer)obj;
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
        return customer;
    }

    public void editCustomer(String id, String password, String name, String address, String contact) {
        ArrayList<Customer> tempCustomer = new ArrayList<Customer>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Customer)obj).getCusID().equals(id)){
                    tempCustomer.add((Customer)obj);
                } else {
                    System.out.println("\nCustomer '" + id + "' is successfully updated!");
                    Customer customer = new Customer();
                    customer.setCusID(id);
                    customer.setCusPassword(password);
                    customer.setCusType(((Customer)obj).getCusType());
                    customer.setCusName(name);
                    customer.setCusAddress(address);
                    customer.setCusContact(contact);
                    customer.setCusDate(((Customer)obj).getCusDate());
                    tempCustomer.add(customer);
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new Login().getDataUser()));
            for(Customer eachCustomer: tempCustomer){
                oos.writeObject(eachCustomer);
            }
            tempCustomer.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) {}
        searchCustomer(id);
    }

    public void deleteCustomer(String id) {
        
        boolean idFound = false;
        ArrayList<Customer> tempCustomer = new ArrayList<Customer>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Customer)obj).getCusID().equals(id)){
                    tempCustomer.add((Customer)obj);
                } else {
                    idFound = true;
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new Login().getDataUser()));
            for(Customer eachCustomer: tempCustomer){
                oos.writeObject(eachCustomer);
            }
            tempCustomer.clear();
            oos.flush();
            oos.close();
            
            if (idFound) {
                System.out.println("\nCustomer '" + id + "' is successfully deleted!\n");
            } else {
                System.out.println("\nCustomer '" + id + "' is not found!\n");
            }
        } catch (Exception e) {}
    }
    
    public void viewCustomer() {
        int count=0;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Login().getDataUser()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                count++;
                System.out.println(((Customer)obj).toString());
            }
        } catch (EOFException ex) {
            System.out.println("\nTotal " + count + " customers.");
        } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

}