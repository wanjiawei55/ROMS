/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

/**
 *
 * @author JW Wan
 */
public class Product {
    private String productID;
    private String productName;
    private double productPrice;
    private String productType;
    
    public void setProductID(String productID) { this.productID = productID; }
    public String getProductID() { return productID; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductName() { return productName; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
    public double getProductPrice() { return productPrice; }
    public void setProductType(String productType) { this.productType = productType; }   
    public String getProductType() { return productType; }
    
    public Product(String id, String name, double price, String type)
    {
        productID = id;
        productName = name;
        productPrice = price;
        productType = type;
    }
    
    @Override
    public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("\n - PRODUCT ID:    ");
    buffer.append(productID);
    buffer.append("\n - PRODUCT NAME:  ");
    buffer.append(productName);
    buffer.append("\n - PRODUCT PRICE: $");
    buffer.append(String.format("%.2f", productPrice));
    buffer.append("\n - PRODUCT TYPE:  ");
    buffer.append(productType);
    buffer.append("\n\n----------------------------------------------------");
    return buffer.toString();
}
    
}
