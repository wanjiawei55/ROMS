package roms;

import java.util.Scanner;





public class Menu {
    private Menu(){}
    
    static Scanner input = new Scanner(System.in);
    
    // Display log in page
    public static String loginPage()
    {
        String id, password;
        
        System.out.println("------------------------------");        
        System.out.println("Retail Order Management System");
        System.out.println("------------------------------");
        System.out.println("Login");
        System.out.print("User ID : ");
        id = input.next();
        System.out.print("Password : ");
        password = input.next();
        
        // what should I return
        // 1. dont return but the credential is collected in main()
        // 2. create a log in object that stores id and pw, return that object
        return id;
    }
    
    // Display main menu
    // for ADMIN
    public static int mainMenuAdmin()
    {
        int selection;

        /***************************************************/
        System.out.println("------------------------------");
        System.out.println("Main Menu");
        System.out.println("------------------------------");
        System.out.println("1 - Customer");
        System.out.println("2 - Product");
        System.out.println("3 - Order");
        System.out.println("4 - Quit");

        selection = input.nextInt();
        return selection;    
    }
    
    // Display customer management page
    // for ADMIN
    public static int customerManagement()
    {
        int selection;
        
        System.out.println("------------------------------");
        System.out.println("Manage Customer");
        System.out.println("------------------------------");
        System.out.println("1 - Add New Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Edit Customer Details");
        System.out.println("4 - View Customer");
        System.out.println("5 - Search Customer");
        System.out.println("6 - Quit");
        
        selection = input.nextInt();
        
        return selection;
    }
    
    // Display product management page
    // for ADMIN
    public static int ProductManagement()
    {
        int selection;
        
        System.out.println("------------------------------");
        System.out.println("Manage Product");
        System.out.println("------------------------------");
        System.out.println("1 - Add New Product");
        System.out.println("2 - Delete Product");
        System.out.println("3 - Edit Product Details");
        System.out.println("4 - View Product");
        System.out.println("5 - Search Product");
        System.out.println("6 - Quit");
        
        selection = input.nextInt();
        return selection;
    }
 
    // Display product management page
    // for ADMIN and CUSTOMER
    public static int OrderManagement()
    {
        int selection;
        
        System.out.println("------------------------------");
        System.out.println("Manage Order");
        System.out.println("------------------------------");
        System.out.println("1 - Add New Order");
        System.out.println("2 - Delete Order");
        System.out.println("3 - Edit Order Details");
        System.out.println("4 - View Order");
        System.out.println("5 - Search Order");
        System.out.println("6 - Quit");
        
        selection = input.nextInt();
        return selection;
    }
    
    // Customer object is needed 
    // Display form to add new customer
    public static int addCustomer()
    {
        System.out.println("------------------------------");
        System.out.println("Add Customer");
        System.out.println("------------------------------");  
        // ID will be generated automatically 
        System.out.println("Identification Number : " + "");
        System.out.println("Name : ");
        System.out.println("Address : ");
        System.out.println("Contact Number : ");
        
        return 0;
    }
    
    
    public static String deleteCustomer()
    {
        System.out.println("------------------------------");
        System.out.println("Delete Customer");
        System.out.println("------------------------------"); 
        System.out.println("Please enter the User ID that you want to delete : ");
        String id = input.next();
        
        // Do we need confirmation
        //System.out.println("Are you sure you want to delete user with ID " + id);
        // delete the user
        
        return id;
    }
    
    // Edit_Customer() Method is needed
    public static int editCustomer()
    {
        System.out.println("------------------------------");
        System.out.println("Edit Customer");
        System.out.println("------------------------------");  
        
        System.out.println("Please enter the User ID of the customer: " );
        String userID = input.next();
        
        // 
        System.out.println("Please select an information to edit.");
        System.out.println("1 - Name");
        System.out.println("2 - Address");
        System.out.println("3 - Contact Number");
        System.out.println("4 - Quit");
        
        return 0;
    }
    
    public static String viewCustomer()
    {
        System.out.println("------------------------------");
        System.out.println("View Customer");
        System.out.println("------------------------------");  
        
        System.out.println("Please enter the User ID of the customer: " );
        String userID = input.next();
        
        System.out.println("1 - Name : " + "");
        System.out.println("2 - Address : " + "");
        System.out.println("3 - Contact Number : " + "");
        System.out.println("4 - Quit");
        
        return "";
    }
    
    public static String searchCustomer()
    {
        System.out.println("------------------------------");
        System.out.println("Search Customer");
        System.out.println("------------------------------");  
        
        System.out.println("Please enter the User ID of the customer: " );
        String userID = input.next();
        
        System.out.println("1 - Name : " + "");
        System.out.println("2 - Address : " + "");
        System.out.println("3 - Contact Number : " + "");
        System.out.println("4 - Quit");
        
        return"";
    }
    
 
    
    
}
