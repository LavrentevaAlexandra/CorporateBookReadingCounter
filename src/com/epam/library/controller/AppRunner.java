package com.epam.library.controller;

import com.epam.library.connector.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Created by Alexandra_Lavrenteva on 3/15/2017.
 */
public class AppRunner {
    private static final Logger LOG = LogManager.getLogger();
    public static void main(String[] args) {
        String command = "";
        LOG.info("Application started!");
        do {
            System.out.println("Enter command. (find more|find less|rename |search)");
            Scanner scanner = new Scanner(System.in);
            DBConnector.registerDB();
            command = scanner.nextLine();
            Controller controller = new Controller();
            controller.defineService(command);
        } while (!command.equals("end"));
        LOG.info("Application shut down");
    }
}