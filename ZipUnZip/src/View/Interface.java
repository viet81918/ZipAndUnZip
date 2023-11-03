/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ZipUnZip;
import java.util.Scanner;

/**
 *
 * @author conch
 */
public class Interface {
    ZipUnZip program = new ZipUnZip();
    Scanner sc = new Scanner(System.in);
    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    program.zipFile();
                    break;
                case 2:
                    program.unzipFile();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1) Zip file.");
        System.out.println("2) Unzip file.");
        System.out.println("3) Exit.");
        System.out.print("Enter your choice: ");
    }

    public int getUserChoice() {
        try {
            String choice = sc.nextLine();
            return Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
