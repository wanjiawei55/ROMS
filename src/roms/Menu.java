package roms;

import java.util.Scanner;
import java.util.ArrayList;





public class Menu {
    public Menu(){}
    
    static Scanner input = new Scanner(System.in);
    Login L = new Login();
    
    
    public void  mainMenuAdmin()
    {
        /***************************************************/
        System.out.println("------------------------------");
        System.out.println("Main Menu");
        System.out.println("------------------------------");
        System.out.println("1 - Manage Customer");
        System.out.println("2 - Manage Product");
        System.out.println("3 - Manage Order");
        System.out.println("4 - Log Out");

        String selection = input.nextLine().toUpperCase();;
        switch (selection) {
            case "1":
                L.clearConsole();
                customerManagement();
                break;
            case "2":
                L.clearConsole();
                productManagement();
                break;
            case "3":
                L.clearConsole();
                OrderManagement("", "ADMIN");
                break;
            
            case "4":
                L.verifyLogin();
                break;
            default:
                L.clearConsole();
                System.out.println("Incorrect input! (" + selection + ").");
                mainMenuAdmin();
        }
       
            
    }
    
    // Display customer management page
    // for ADMIN
    public void customerManagement()
    {
        
        
        System.out.println("------------------------------");
        System.out.println("Manage Customer");
        System.out.println("------------------------------");
        System.out.println("1 - Add New Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Edit Customer Details");
        System.out.println("4 - View Customer");
        System.out.println("5 - Search Customer");
        System.out.println("6 - Quit");
        
        String selection = input.nextLine().toUpperCase();;
        switch (selection) {
            case "1":
                L.clearConsole();
                uiAddCustomer();
                break;
            case "2":
                L.clearConsole();
                L.uiDeleteCustomer();
                break;
            case "3":
                L.clearConsole();
                uiEditCustomer();
                break;
            case "4":
                L.clearConsole();
                uiViewAllCustomers();
                break;
            case "5":
                L.clearConsole();
                uiSearchCustomer();
                break;    
            case "6":
                L.clearConsole();
                mainMenuAdmin();
                break;
            default:
                L.clearConsole();
                System.out.println("Incorrect input! (" + selection + ").");
                mainMenuAdmin();
        }
    }
    
    
    
    // Display Product Management Page
    // For Admin
    public void productManagement()
    {
        System.out.println("------------------------------");
        System.out.println("Manage Product");
        System.out.println("------------------------------");
        System.out.println("1 - Add New Product");
        System.out.println("2 - Delete Product");
        System.out.println("3 - Edit Product Details");
        System.out.println("4 - View Product");
        System.out.println("5 - Search Product");
        System.out.println("6 - Quit");
        
        String selection = input.nextLine().toUpperCase();;
        switch (selection) {
            case "1":
                L.clearConsole();
                uiAddProduct(); //done
                break;
            case "2":
                L.clearConsole();
                uiDeleteProduct(); // done
                break;
            case "3":
                L.clearConsole();
                uiEditProduct(); // done
                break;
            case "4":
                L.clearConsole();
                uiViewAllProduct(); //done
                break;
            case "5":
                L.clearConsole(); // done
                uiSearchProduct();
                break;    
            case "6":
                L.clearConsole();
                mainMenuAdmin();
                break;
            default:
                L.clearConsole();
                System.out.println("Incorrect input! (" + selection + ").");
                mainMenuAdmin();
        }
    }

    // Display product management page
    // for ADMIN and CUSTOMER

    public void OrderManagement(String customerID, String userType)
    {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("Manage Order");
        System.out.println("------------------------------");
        System.out.println("1 - Make new Order");
        System.out.println("2 - Search Order");
        System.out.println("3 - Edit Order");
        System.out.println("4 - Delete Order");
        System.out.println("5 - View Order");
        if(userType.equals("CUSTOMER"))     System.out.println("6 - LOG OUT");
        else if(userType.equals("ADMIN"))   System.out.println("6 - Quit");
        System.out.println("\n----------------------------------------------------");
        System.out.println(">>> Please select your choice: ");
        String getInput = scanner.nextLine().toUpperCase();
            switch (getInput) {
                case "1":
                    L.clearConsole();
                    uiMakeOrder(customerID, userType); // done
                    break;
                case "2":
                    L.clearConsole();
                    uiSearchOrder(customerID, userType);         // done
                    break;
                case "3":
                    L.clearConsole();
                    uiEditOrder(customerID, userType);      // done
                case "4":
                    L.clearConsole();
                    uiDeleteOrder(customerID, userType);            // done
                    break;
                case "5":
                    L.clearConsole();
                    uiViewAllOrders(customerID, userType); // done
                    break;
                case "6":
                    L.clearConsole();
                    if(userType.equals("CUSTOMER"))     L.verifyLogin();
                    else if(userType.equals("ADMIN"))   mainMenuAdmin();
                    break;                        
                default:
                    L.clearConsole();
                    System.out.println("Incorrect input! (" + getInput + ").");
                    OrderManagement(customerID, userType);
            }
        
    }
    
    
 
    public void uiAddCustomer() {
        String name="", password="", address="", contact=""; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----------------- ADD NEW CUSTOMER -----------------\n");
        System.out.println("(Minimum 4 and maximum 12 characters!)");
        System.out.println(">>> Please enter the customer name: ");
        name=scanner.nextLine().toUpperCase();
        if((name.length() >= 4) && (name.length() <= 12)) {
            System.out.println("\n(Minimum 4 and maximum 12 characters!)");
            System.out.println(">>> Please enter the password: ");
            password=scanner.nextLine().toUpperCase();
            if((password.length() >= 4) && (password.length() <= 12)) {
                System.out.println("\n(Maximum 25 characters!)");
                System.out.println(">>> Please enter the address: ");
                address=scanner.nextLine().toUpperCase();
                if(address.equals("")) {address="-";}
                if((address.length() <= 25)) {
                    System.out.println("\n(Maximum 16 characters!)");
                    System.out.println(">>> Please enter the contact number: ");
                    contact=scanner.nextLine().toUpperCase();
                    if(contact.equals("")) { contact="-"; }
                    if(contact.length() <= 16) {
                        new Login().clearConsole();
                        Control c = new Control();
                        c.addCustomer(password, name, address, contact);
                    } else { System.out.println("\nIncorrect input!"); }
                } else { System.out.println("\nIncorrect input!"); }
            } else { System.out.println("\nIncorrect input!"); }
        } else { System.out.println("\nIncorrect input!"); }
        scanner.nextLine();
        new Login().clearConsole();
        customerManagement();
    }
    
    private void uiSearchCustomer() {
        String id=""; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----------- SEARCH FOR SPECIFIC CUSTOMER -----------\n");
        System.out.println("(Example: UID123456)");
        System.out.println(">>> Please enter the customer ID number: ");
        id=scanner.nextLine().toUpperCase();
        L.clearConsole();
        new Control().searchCustomer(id);
        scanner.nextLine();
        L.clearConsole();
        customerManagement();
    }
        
    private void uiEditCustomer() {
        String id="", name="", password="", address="", contact=""; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- EDIT CUSTOMER ------------------\n");
        System.out.println("(Example: UID1234)");
        System.out.println(">>> Please enter the customer ID number: ");
        id=scanner.nextLine().toUpperCase();
        if(id.length() == 7) {
            System.out.println("\n(Minimum 4 and maximum 12 characters!)");
            System.out.println(">>> Please enter the new name: ");
            name=scanner.nextLine().toUpperCase();
            if((name.length() >= 4) && (name.length() <= 12)) {
                System.out.println("\n(Minimum 4 and maximum 12 characters!)");
                System.out.println(">>> Please enter the new password: ");
                password=scanner.nextLine().toUpperCase();
                if((password.length() >= 4) && (password.length() <= 12)) {
                    System.out.println("\n(Maximum 25 characters!)");
                    System.out.println(">>> Please enter the new address: ");
                    address=scanner.nextLine().toUpperCase();
                    if(address.equals("")) {address="-";}
                    if((address.length() <= 25)) {
                        System.out.println("\n(Maximum 16 characters!)");
                        System.out.println(">>> Please enter the new contact number: ");
                        contact=scanner.nextLine().toUpperCase();
                        if(contact.equals("")) {contact="-";}
                        if((contact.length() <= 16)) {
                            L.clearConsole();
                            new Control().editCustomer(id, password, name, address, contact);
                        } else { System.out.println("\nIncorrect input!"); }
                    } else { System.out.println("\nIncorrect input!"); }
                } else { System.out.println("\nIncorrect input!"); }
            } else { System.out.println("\nIncorrect input!"); }
        } else { System.out.println("\nIncorrect input!"); }
        scanner.nextLine();
        L.clearConsole();
        customerManagement();
    }
 
    
    
    private void uiViewAllCustomers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---------------- VIEW ALL CUSTOMERS ----------------");
        new Control().viewCustomer();
        scanner.nextLine();
        L.clearConsole();
        customerManagement();
    }
    
    
    private void uiAddProduct()
    {
        String name, type;
        double price;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n---------------- ADD PRODUCT ----------------");
        System.out.println(">>> Please enter product name: ");
        name = scanner.nextLine();
        System.out.println(">>> Please enter product price: ");
        price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println(">>> Please enter product type (Fragile/Non-Fragile): ");
        type = scanner.nextLine();
        
        ProductControl c = new ProductControl();
        c.addProduct(name, price, type);
        
        scanner.nextLine();
        L.clearConsole();
        productManagement();
    }
    
    private void uiViewAllProduct()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---------------- VIEW ALL PRODUCTS ----------------");
        new ProductControl().viewProduct();
        
        scanner.nextLine();
        L.clearConsole();
        productManagement();
    }
    
    private void uiEditProduct()
    {
        String id, name, type;
        double price; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- EDIT PRODUCT ------------------\n");
        System.out.println(">>> Please enter the product ID: ");
        id = scanner.nextLine().toUpperCase();
        
        System.out.println(">>> Please enter new product name: ");
        name = scanner.nextLine();
        System.out.println(">>> Please enter new product price: ");
        price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println(">>> Please enter new product type (Fragile/Non-Fragile): ");
        type = scanner.nextLine();
        
        ProductControl c = new ProductControl();
        c.editProduct(id, name, price, type);
        
        scanner.nextLine();
        L.clearConsole();
        productManagement();
    }
    
    
    private void uiDeleteProduct()
    {
        String id; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- DELETE PRODUCT ------------------\n");
        System.out.println(">>> Please enter the product ID: ");
        id = scanner.nextLine().toUpperCase();
        
        ProductControl c = new ProductControl();
        c.deleteProduct(id);
        
        scanner.nextLine();
        L.clearConsole();
        productManagement();
    }
    
    
    private void uiSearchProduct()
    {
        String id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- SEARCH PRODUCT ------------------\n");
        System.out.println(">>> Please enter the product ID: ");
        id = scanner.nextLine().toUpperCase();
        
        ProductControl c = new ProductControl();
        c.searchProduct(id);
        
        scanner.nextLine();
        L.clearConsole();
        productManagement();
    }
        
    
    private void uiMakeOrder(String customerID, String userType)
    {
        String pID = "0";
        int quantity = 0, count = 0;
        ArrayList<String> productIDs = new ArrayList<>();
        ArrayList<Integer> productQuantity = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- MAKE NEW ORDER ------------------\n");
        
        ProductControl pc = new ProductControl();
        pc.viewProduct();
        System.out.println();
            
        try
        {
            System.out.println(">>> Please enter product ID (Enter 0 to complete order): ");
            pID = scanner.nextLine().toUpperCase();
            if(!pID.equals("0".toUpperCase()))
            {
                System.out.println(">>> Please enter quantity of the product: ");
                quantity = scanner.nextInt();
                scanner.nextLine();
                count++;
            }

            while(!pID.equals("0"))
            {
                productIDs.add(pID);
                productQuantity.add(quantity);

                System.out.println(">>> Please enter product ID (Enter 0 to complete order): ");
                pID = scanner.nextLine().toUpperCase();
                if(!pID.equals("0".toUpperCase()))
                {
                    System.out.println(">>> Please enter quantity of the product: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    count++;
                }
            }
            if(count>0)
            {
                OrderControl c = new OrderControl();
                c.makeOrder(customerID, productIDs, productQuantity);            
            }
            else    System.out.println("Order Unsuccesful.");           
        }
        catch(Exception e)
        {
            System.out.println("\nInformation incorrect.");
            System.out.println("Order Unsuccesful."); 
            scanner.nextLine();
        }
        
        


        
        scanner.nextLine();
        L.clearConsole();
        OrderManagement(customerID, userType);
    }
    
    private void uiViewAllOrders(String customerID, String userType)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---------------- VIEW ALL ORDERS ----------------");
        new OrderControl().viewOrder(customerID, userType);
        
        scanner.nextLine();
        L.clearConsole();
        OrderManagement(customerID, userType);        
    }
    
    private void uiDeleteOrder(String customerID, String userType)
    {
        String orderID; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- DELETE ORDER ------------------\n");
        System.out.println(">>> Please enter the order ID: ");
        orderID = scanner.nextLine().toUpperCase();
        
        OrderControl c = new OrderControl();
        c.deleteOrder(customerID, userType, orderID);
        
        scanner.nextLine();
        L.clearConsole();
        OrderManagement(customerID, userType);
    }
    
    private void uiEditOrder(String customerID, String userType)
    {
        String orderID, name, type;
        double price; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- EDIT ORDER ------------------\n");
        System.out.println(">>> Please enter the order ID: ");
        orderID = scanner.nextLine().toUpperCase();
        
        OrderControl c = new OrderControl();
        c.editOrder(customerID, userType, orderID);
        
        scanner.nextLine();
        L.clearConsole();
        OrderManagement(customerID, userType);        
    }
    
    private void uiSearchOrder(String customerID, String userType)
    {
        String orderID;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- SEARCH ORDER ------------------\n");
        System.out.println(">>> Please enter the order ID: ");
        orderID = scanner.nextLine().toUpperCase();
        
        OrderControl c = new OrderControl();
        c.searchOrder(customerID, userType, orderID);
        
        scanner.nextLine();
        L.clearConsole();
        OrderManagement(customerID, userType);
    }
}
