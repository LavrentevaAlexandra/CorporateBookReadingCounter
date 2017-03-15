package com.epam.library.controller;

import com.epam.library.service.Service;

import java.util.Scanner;

/**
 * Created by 123 on 14.03.2017.
 */
public class Controller {

    public void defineService(String command) {
        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        int bookId;
        String yearOfPublishing;
        String title="";
        String author="";
        String brief="";

        switch (command) {
            case "find more":         //find (then accept book number parameter)
                System.out.println("Enter book number: ");
                int bookNumberMore = scanner.nextInt();
                service.findEmployeesMore(bookNumberMore);
                break;
            case "find less":         //find (then accept book number parameter)
                System.out.println("Enter book number: ");
                int bookNumberLess = scanner.nextInt();
                service.findEmployeesLess(bookNumberLess);
                break;
            case "search":         //search book by name
                System.out.println("Enter key words: ");
                String request = scanner.nextLine();
                service.findBook(request);
                break;
            case "rename":          //renameBook
                System.out.println("Enter old title: ");
                String oldTitle = scanner.nextLine();
                System.out.println("Enter new title: ");
                String newTitle = scanner.nextLine();
                service.renameBook(oldTitle, newTitle);
                break;

            //CRUD functions

/*            case "create":          //create book
                System.out.println("Fill the information about book: ");
                System.out.println("Title: ");
                title = scanner.nextLine();
                System.out.println("Author: ");
                author = scanner.nextLine();
                System.out.println("Brief: ");
                brief = scanner.nextLine();
                System.out.println("Year of publishing: ");
                int year= scanner.nextInt();
                service.createBook(title,author,brief,year);
                break;
            case "show":          //show info about book ==read by id
                System.out.println("Enter book id: ");
                bookId = scanner.nextInt();
                service.showBook(bookId);
                break;
            case "update":        //update book info by id
                System.out.println("Enter book id: ");
                bookId = scanner.nextInt();
                System.out.println("Fill the information you want to change. Push Enter if you don't.");
                System.out.println("Change title: ");
                title = scanner.nextLine();
                System.out.println("Author: ");
                author= scanner.nextLine();
                System.out.println("Brief: ");
                brief = scanner.nextLine();
                System.out.println("Year of publishing: ");
                yearOfPublishing = scanner.nextLine();
                service.updateBook(bookId, title,author,brief,yearOfPublishing);
                break;
            case "delete":
                System.out.println("Enter book id: ");
                bookId = scanner.nextInt();
                service.deleteBook(bookId);
                break;*/
            case "end":
                System.out.println("End application");
                break;
            default:
                System.out.printf("There is no such command");
                break;
        }
    }
}
