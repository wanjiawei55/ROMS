/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author JW Wan
 */
public class OrderControl extends ObjectControl<Order>{
    
    // import Order data from file
    @Override
    ArrayList<Order> objectsFromFile()
    {
        File orderFile = new File("OrderData.txt");
        ArrayList<Order> orderList = new ArrayList<>();  

        try
        {
            Scanner sc = new Scanner(orderFile);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] orderArr = line.split(",");
                String orderID = orderArr[0];
                String customerID = orderArr[1];
                String date = orderArr[2];
                int productCount = Integer.valueOf(orderArr[3]);
                ArrayList<String> productList = new ArrayList<>();
                ArrayList<Integer> productQuantity = new ArrayList<>();
                
                int i = 0;
                while(i<productCount)
                {
                    productList.add(orderArr[4+i]);
                    i++;
                }
                
                i = 0;
                while(i<productCount)
                {
                    productQuantity.add(Integer.valueOf(orderArr[4+productCount+i]));
                    i++;
                }

                orderList.add(new Order(orderID, customerID, date, productList, productQuantity));
            } 
        }
        catch(IOException Ex) { }
        
        return orderList;
    }
    
    // export Order data to file
    @Override
    void objects2File(ArrayList<Order> orderList)
    {
        File FInput = new File("OrderData.txt");
        try
        {
            FileWriter fw = new FileWriter(FInput, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            for(Order eachOrder : orderList)
            {
                ArrayList<String> productList = eachOrder.getProductListID();
                ArrayList<Integer> productQuantity = eachOrder.getProductQuantity();
                
                String Line = eachOrder.getOrderID() + "," + eachOrder.getCustomerID()+ "," 
                        + eachOrder.getOrderDate() + "," + productList.size() + ",";
                
                for(String productID : productList)
                {
                    Line = Line + productID + ",";
                }
                for(int quantity : productQuantity)
                {
                    Line = Line + quantity + ",";
                }
                
                pw.write(Line);
                bw.newLine(); 
            }
            pw.close();
        }
        catch(IOException Ex)
        {
            
        }
    }
    
    
    public void makeOrder(String cusID, ArrayList<String> productIDs, ArrayList<Integer> productQuantity)
    {
        ArrayList<Order> orderList = objectsFromFile();
        String orderID, date;
        boolean orderIncomplete = false;
        ArrayList<String> confirmedProductID  = new ArrayList<>();
        ArrayList<Integer> confirmedProductQuantity = new ArrayList<>();
                
        orderID =  "O" + Integer.toString(Control.generateNum(10000, 99999));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = dateFormat.format(new Date());
        
        ArrayList<Product> productList = new ProductControl().objectsFromFile();
        
        boolean productExist, duplication, isZero; // to check if the product exist, no duplication and quantity > 0
        
        for(int i = 0; i<productIDs.size(); i++)
        {
            productExist = false;
            duplication = false;
            isZero = false;
            String pID = productIDs.get(i);         // productID to check if exist and duplicated
            int pQuantity = productQuantity.get(i); // quantity of product that is being checked
            if(pQuantity <= 0)   isZero = true;

            for(Product product : productList)
            {
                if(product.getProductID().equals(pID))  productExist = true;
            }
            
            for(String confirmedID : confirmedProductID)
            {
                if(confirmedID.equals(pID)) duplication = true;
            }
            
            if(isZero)
            {
                System.out.println("Information incorrect. Quantity of product '" + pID + "' less than 1.");
                orderIncomplete = true;
            }
            else if(productExist && !duplication)
            {
                confirmedProductID.add(pID);
                confirmedProductQuantity.add(pQuantity);
            }
            else if(productExist && duplication)
            {
                int index = confirmedProductID.indexOf(pID);
                int tempQ = confirmedProductQuantity.get(index);
                confirmedProductQuantity.set(index, tempQ + pQuantity);
            }
            else
            {
                System.out.println("Product '" + pID + "' not found.");
                orderIncomplete = true;
            }
        }

        if(orderIncomplete == false)
        {
            Order order = new Order(orderID, cusID, date, confirmedProductID, confirmedProductQuantity);
            orderList.add(order);
            objects2File(orderList);

            System.out.println("\nOrder Successful!");
            System.out.println(order);             
        }
        else
        {
            System.out.println("Order Unsuccessful.");
        }

    }
    
    public void viewOrder(String customerID, String userType)
    {
        ArrayList<Order> orderList = objectsFromFile();
        int count = 0;
        
        if(userType.equals("CUSTOMER"))
        {
            for (Order eachOrder : orderList)
            {
                if(eachOrder.getCustomerID().equals(customerID))
                {
                    System.out.println(eachOrder);
                    count++;                    
                }
            }
        }
        else
        {
            for (Order eachOrder : orderList)
            {
                System.out.println(eachOrder);
                count++;
            }
        }
        System.out.println("Total " + count + " order(s)");
    }
    
    public void deleteOrder(String customerID, String userType, String orderID)
    {
        ArrayList<Order> orderList = objectsFromFile();
        boolean deleted = false;
        
        for(Order order : orderList)
        {
            if(order.getOrderID().equals(orderID))
            {
                if(userType.equals("ADMIN") || (userType.equals("CUSTOMER") && order.getCustomerID().equals(customerID)))
                {
                    orderList.remove(order);
                    deleted = true;
                    break;
                }      
            }
        }
        objects2File(orderList);
        
        if(deleted) System.out.println("Order " + orderID + " is successfully deleted!");
        else System.out.println("Order " + orderID + " not found.");
    }
    
    public void editOrder(String customerID, String userType, String orderID)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Order> orderList = objectsFromFile();
        boolean edited = false;
        
        for(Order order : orderList)
        {
            if(order.getOrderID().equals(orderID))
            {
                // to check if Admin or the Customer who ordered is editing
                if(userType.equals("ADMIN") || (userType.equals("CUSTOMER") && order.getCustomerID().equals(customerID)))
                {
                    ArrayList<Product> productList = order.getProductList();
                    ArrayList<Integer> productQuantity = order.getProductQuantity();
                    for(Product product : productList)
                    {
                        System.out.println(">>> Please enter new quantity of the product '" 
                                + product.getProductID() + "' " + product.getProductName() + ": ");
                        try
                        {
                            int tempQuantity = scanner.nextInt();
                            scanner.nextLine();          
                            int index = productList.indexOf(product);
                            productQuantity.set(index, tempQuantity);
                            edited = true; 
                        }
                        catch(Exception Ex)
                        {
                            System.out.println("\nInformation incorrect.");
                            return;
                        }
                    }
                }           
            }
        }
        objects2File(orderList);
        
        if(edited)  
        {
            System.out.println("\nOrder " + orderID + " is successfully edited!");
            System.out.println();
        }
        else    System.out.println("\nOrder " + orderID + " not found.");
    }
    
    public void searchOrder(String customerID,String userType,String orderID)
    {
        ArrayList<Order> orderList = objectsFromFile();
        boolean found = false;
        
        for(Order order : orderList)
        {
            if(order.getOrderID().equals(orderID))
            {
                // to check if Admin or the Customer who ordered is searching
                if(userType.equals("ADMIN") || (userType.equals("CUSTOMER") && order.getCustomerID().equals(customerID)))
                {
                    System.out.println("\nOrder found.");
                    System.out.println(order);
                    found = true;
                    break;
                }
            }
        }
        objects2File(orderList);
        
        if(!found) System.out.println("Order " + orderID + " not found.");        
    }
}
