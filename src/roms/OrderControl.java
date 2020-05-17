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
public class OrderControl {
    private ArrayList<Order> ordersFromFile()
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
    
    private void order2File(ArrayList<Order> orderList)
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
    
    // still need to make change for ADMIN
    public void makeOrder(String cusID, ArrayList<String> productIDs, ArrayList<Integer> productQuantity)
    {
        ArrayList<Order> orderList = ordersFromFile();
        String orderID, date;
                
        orderID =  "O" + Integer.toString(Control.generateNum(10000, 99999));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = dateFormat.format(new Date());
        
        Order order = new Order(orderID, cusID, date, productIDs, productQuantity);
        orderList.add(order);
        order2File(orderList);
        
        System.out.println("\nOrder Successful!");
        System.out.println(order); 
    }
    
    public void viewOrder(String customerID, String userType)
    {
        ArrayList<Order> orderList = ordersFromFile();
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
        ArrayList<Order> orderList = ordersFromFile();
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
        order2File(orderList);
        
        if(deleted) System.out.println("Order " + orderID + " is successfully deleted!");
        else System.out.println("Order " + orderID + " not found.");
    }
    
    public void editOrder(String customerID, String userType, String orderID)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Order> orderList = ordersFromFile();
        boolean edited = false;
        
        for(Order order : orderList)
        {
            if(order.getOrderID().equals(orderID))
            {
                if(userType.equals("ADMIN") || (userType.equals("CUSTOMER") && order.getCustomerID().equals(customerID)))
                {
                    ArrayList<Product> productList = order.getProductList();
                    ArrayList<Integer> productQuantity = order.getProductQuantity();
                    for(Product product : productList)
                    {
                        System.out.println(">>> Please enter new quantity of the product '" 
                                + product.getProductID() + "' " + product.getProductName() + ": ");
                        int tempQuantity = scanner.nextInt();
                        scanner.nextLine();
                        
                        int index = productList.indexOf(product);
                        productQuantity.set(index, tempQuantity);
                    }
                    edited = true;
                    break;
                }
                    
            }
        }
        order2File(orderList);
        
        if(edited)  System.out.println("Order " + orderID + " is successfully edited!");
        else        System.out.println("Order " + orderID + " not found or edit unsuccess.");
    }
    
    public void searchOrder(String customerID,String userType,String orderID)
    {
        ArrayList<Order> orderList = ordersFromFile();
        boolean found = false;
        
        for(Order order : orderList)
        {
            if(order.getOrderID().equals(orderID))
            {
                if(userType.equals("ADMIN") || (userType.equals("CUSTOMER") && order.getCustomerID().equals(customerID)))
                {
                    System.out.println("\nOrder found.");
                    System.out.println(order);
                    found = true;
                    break;
                }
            }
        }
        order2File(orderList);
        
        if(!found) System.out.println("Order " + orderID + " not found.");        
    }
}
