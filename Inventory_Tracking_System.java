

import java.io.*;
import java.util.Scanner;

public class Inventory_Tracking_System {
//-----------------------------------------------------
// Title: Captain Class
// Author: Azerhan BAĞCI
// Description: This class writes an inventory tracking system using binary search trees.
//-----------------------------------------------------
    public static void main(String[] args) throws IOException {
        BST<Integer, Product> bst = new BST<Integer, Product>();
        Scanner sc = new Scanner(new File("input_Q1.txt"));
        String product = null;
        int id = 0, piece= 0, c = 0;

        greet();

        while(sc.hasNext()) {
            String s = sc.next();
            switch (s) {
                case "Add_product" -> {
                    id = Integer.parseInt(sc.next());
                    c++;
                }
                case "Is_Available" -> {
                    id = Integer.parseInt(sc.next());
                    if(bst.get(id) == null)
                        System.out.println("The product is out of stock!!!");
                    else {
                        System.out.println("There are " + bst.get(id).getPiece() + " products.");
                    }
                }
                case "Quit" -> quit();
                default -> {
                    if(!isInteger(s)) {
                        product = s;
                        piece = Integer.parseInt(sc.next());
                        c++;
                    }
                }
            }
            if(c==2) {
                Product p = new Product(product, piece);
                bst.put(id, p);
                createProduct(id, p);
                c = 0;
            }
        }
    }

    public static boolean isInteger(String str) {
        //--------------------------------------------------------
        // Summary: Returns boolean of whether given String value can be parsed into Integer or not.
        // Precondition: str is String.
        // Postcondition: Returned boolean.
        // Note: This is a method which is not a must in homework however I typed to make my work easier.
        //--------------------------------------------------------
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static void greet() {
        //--------------------------------------------------------
        // Summary: Prints "--------------- WELCOME TO ITS ---------------".
        // Precondition: this is not null
        // Postcondition: Printed "--------------- WELCOME TO ITS ---------------".
        //--------------------------------------------------------
        System.out.println("--------------- WELCOME TO ITS ---------------");
    }

    private static void quit() {
        //--------------------------------------------------------
        // Summary: Prints "Thank you for using ITS, Good Bye!" and exits the system.
        // Precondition: this is not null
        // Postcondition: Printed "Thank you for using ITS, Good Bye!" and exited.
        //--------------------------------------------------------
        System.out.println("Thank you for using ITS, Good Bye!");
        System.exit(0);
    }

    private static void createProduct(int id, Product p) {
        //--------------------------------------------------------
        // Summary: Prints various Strings when given two values.
        // Precondition: id is int and p is Product.
        // Postcondition: Printed various Strings.
        System.out.println("Create Product:\n");
        System.out.println("\t\t\t\t\t\tID: " + id);
        System.out.println("\t\t\t\t\t\tName: " + p.getName());
        System.out.println("\t\t\t\t\t\tPiece: " + p.getPiece());
    }
}
