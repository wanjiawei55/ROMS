/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

import java.util.ArrayList;

/**
 *
 * @author JW Wan
 */
public class Order {
    private String orderID;
    private Customer customer;
    private String orderDate;
    private ArrayList<String> orderedProductIDList = new ArrayList<>();
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Integer> productQuantity = new ArrayList<>();
    
    private int orderItemAmount;        // total item amount purchased
    private double productTotalPrice;   // subtotal
    private double shippingFee;         // shipping & packaging cost
    private double orderTotalPrice;     // order price in total
    
    public void setOrderID(String orderID) { this.orderID = orderID; }
    public String getOrderID() { return orderID; }
    public String getCustomerID() { return customer.getCusID(); }
    public String getOrderDate() { return orderDate; }
    public ArrayList<String> getProductListID() { return orderedProductIDList; }
    public ArrayList<Product> getProductList() { return productList; }    
    public ArrayList<Integer> getProductQuantity() { return productQuantity; }
    
    
    public Order(String orderID, String customerID, String orderDate, ArrayList<String> productIDs, ArrayList<Integer> pQuantity)
    {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = Control.searchCustomer(customerID, true);
        this.orderedProductIDList = productIDs;
        
        // get products from file
        ArrayList<Product> tempProductList = new ProductControl().objectsFromFile();
        
        for(String id : productIDs) 
        {
            for(Product tempProduct : tempProductList)
            {
                if(tempProduct.getProductID().equals(id))
                {
                    this.productList.add(tempProduct);
                }
            }
        }
        this.productQuantity = pQuantity;

        calculateOrder();
    }
    
    // to calculate everything that needed calculation eg. itemAmount, totalPrice, shippingFee
    private void calculateOrder()
    {
        orderItemAmount = 0;
        productTotalPrice = 0;
        shippingFee = 0;
        orderTotalPrice = 0;
        
        int quantity;
        for(Product eachProduct : productList)
        {
            quantity = productQuantity.get(productList.indexOf(eachProduct));
            productTotalPrice += eachProduct.getProductPrice() * quantity;
            
            // packaging(shippingFee) cost $5 for each product, additional $3 for fragile item.
            shippingFee += 5 * quantity;
            if(eachProduct.getProductType().equals("Fragile"))  shippingFee += 3 * quantity;
            
            orderItemAmount += quantity;
        }
        
        orderTotalPrice = productTotalPrice + shippingFee;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n - ORDER ID:           ");
        buffer.append(orderID);
        buffer.append("\n - CUSTOMER ID:        ");
        buffer.append(customer.getCusID());
        buffer.append("\n - CUSTOMER NAME:      ");
        buffer.append(customer.getCusName());
        buffer.append("\n - CUSTOMER CONTACT:   ");
        buffer.append(customer.getCusContact());
        buffer.append("\n - CUSTOMER ADDRESS:   ");
        buffer.append(customer.getCusAddress());
        buffer.append("\n - ORDER TIME:         ");
        buffer.append(orderDate);
        buffer.append("\n - PRODUCT NAME\t\t\tPRICE($)\t\tQUANTITY");
        
        int i = 0;
        for(Product eachProduct : productList)
        {
            i++;
            buffer.append("\n   ").append(i).append(". ").append(eachProduct.getProductName()).append("\t\t\t").append(String.format("%4.2f", eachProduct.getProductPrice())).append("\t\t\t").append(productQuantity.get(productList.indexOf(eachProduct)));            
        }
        
        buffer.append("\n - TOTAL ITEMS:        ");
        buffer.append(orderItemAmount);
        buffer.append("\n - SUBTOTAL($):        ");
        buffer.append(String.format("%4.2f", productTotalPrice));
        buffer.append("\n - SHIPPING FEE($):    ");
        buffer.append(String.format("%4.2f", shippingFee));
        buffer.append("\n - TOTAL PRICE($):     ");
        buffer.append(String.format("%4.2f", orderTotalPrice));
        
        buffer.append("\n\n----------------------------------------------------");
        return buffer.toString();
    }
}
