package roms;

import java.util.Scanner;





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
    

    // Display product management page
    // for ADMIN and CUSTOMER
    }
    public void OrderManagement()
    {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("Manage Order");
        System.out.println("------------------------------");
        System.out.println("1 - Make new Order");
        System.out.println("2 - Search Order");
        System.out.println("3 - Delete Order");
        System.out.println("4 - View Order");
        System.out.println("0 - LOG OUT");
        System.out.println("\n----------------------------------------------------");
        System.out.println(">>> Please select your choice: ");
        String getInput = scanner.nextLine().toUpperCase();
            switch (getInput) {
                case "1":
                    L.clearConsole();
                    //uiMakeOrder();
                    break;
                case "2":
                    L.clearConsole();
                    //uiSearchOrder();
                    break;
                case "3":
                    //L.clearConsole();
                    //uiDeleteOrder();
                    break;
                case "4":
                    //L.clearConsole();
                    //uiViewAllOrders();
                    break;
                case "0":
                    L.clearConsole();
                    L.verifyLogin();
                    break;
                default:
                    L.clearConsole();
                    System.out.println("Incorrect input! (" + getInput + ").");
                    OrderManagement();
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
                        Manage m = new Manage();
                        m.addCustomer(password, name, address, contact);
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
        new Manage().searchCustomer(id);
        scanner.nextLine();
        L.clearConsole();
        customerManagement();
    }
        
    private void uiEditCustomer() {
        String id="", name="", password="", address="", contact=""; 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------- EDIT CUSTOMER ------------------\n");
        System.out.println("(Example: UID123456)");
        System.out.println(">>> Please enter the customer ID number: ");
        id=scanner.nextLine().toUpperCase();
        if(id.length() == 9) {
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
                            new Manage().editCustomer(id, password, name, address, contact);
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
        new Manage().viewCustomer();
        scanner.nextLine();
        L.clearConsole();
        customerManagement();
    }
        
}
