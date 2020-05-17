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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JW Wan
 */
public class ProductControl {
    
    public static ArrayList<Product> productsFromFile()
    {
        File productFile = new File("ProductData.txt");
        ArrayList<Product> productList = new ArrayList<>();  

        try
        {
            Scanner sc = new Scanner(productFile);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] productArr = line.split(",");
                productList.add(new Product(productArr[0], productArr[1], Double.parseDouble(productArr[2]), productArr[3]));
            } 
        }
        catch(IOException Ex) { }
        
        return productList;
    }    
    
    private void product2File(ArrayList<Product> productList)
    {
        File FInput = new File("ProductData.txt");
        try
        {
            FileWriter fw = new FileWriter(FInput, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            for(Product eachProduct : productList)
            {
                String Line = eachProduct.getProductID() + "," + eachProduct.getProductName() + "," 
                        + eachProduct.getProductPrice() + "," + eachProduct.getProductType();
                pw.write(Line);
                bw.newLine(); 
            }
            pw.close();
        }
        catch(IOException Ex)
        {
            
        }
    }
    
    
    public void addProduct(String name, double price, String type)
    {
        ArrayList<Product> productList = productsFromFile();
        
        String id = "P" + (Control.generateNum(1000, 9999));
        
        for(Product eachProduct : productList)
        {
            if(eachProduct.getProductID().equals(id))
            {
                addProduct(name, price, type);
                break;
            }
        }
        Product p = new Product(id, name, price, type);
        productList.add(p);
        product2File(productList);
        
        System.out.println("\nProduct " + name + " is successfully added!");
    }
    
    
    public void viewProduct()
    {
        ArrayList<Product> productList = productsFromFile();
        
        int no = 0;
        for (Product eachProduct : productList)
        {
            no++;
            System.out.println("Product " + no);
            System.out.println(eachProduct.toString());;

        }
        
        if(no==0)   System.out.println("\nNo product found in the system.");
        else    System.out.println("\nTotal " + no + " product(s).");
    }
    
    public void editProduct(String id, String name, double price, String type)
    {
        ArrayList<Product> productList = productsFromFile();
        boolean edited = false;     
        
        for(Product eachProduct : productList)
        {
            if(eachProduct.getProductID().equals(id))
            {
                eachProduct.setProductName(name);
                eachProduct.setProductPrice(price);
                eachProduct.setProductType(type);
                edited = true;
            }
        }
        if(edited == false)
        {
            System.out.println("\nProduct " + id + " not found!");
        }

        product2File(productList);

        System.out.println("\nProduct " + id + " is successfully updated!");
    }

    public void deleteProduct(String id)
    {
        ArrayList<Product> productList = productsFromFile();
        boolean deleted = false;
        
        for(Product eachProduct : productList)
        {
            if(eachProduct.getProductID().equals(id))
            {
                productList.remove(eachProduct);
                deleted = true;
                break;
            }
        }
        product2File(productList);
        
        if(deleted) System.out.println("\nProduct " + id + " is successfully deleted!");
        else    System.out.println("\nProduct " + id + " not found.");
    }
    
    
    public void searchProduct(String id)
    {
        ArrayList<Product> productList = productsFromFile();
        boolean found = false;
        
        for(Product eachProduct : productList)
        {
            if(eachProduct.getProductID().equals(id))
            {
                System.out.println("\nSearch result for '" + id + "':");
                System.out.println(eachProduct.toString());
                found = true;
            }
        }
        if(!found)  System.out.println("\nProduct " + id + " not found.");
    }
    
    
}
