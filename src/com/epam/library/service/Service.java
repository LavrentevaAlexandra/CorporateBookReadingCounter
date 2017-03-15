package com.epam.library.service;

import com.epam.library.DAO.AbstractDAO;
import com.epam.library.DAO.BookDAO;
import com.epam.library.DAO.EmployeeDAO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.exception.DAOException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 15.03.2017.
 */
public class Service {
    public Service() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void findBook(String request) {
        BookDAO bookDAO = new BookDAO();
        try {
            showBooks(bookDAO.findBook(request));
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            bookDAO.closeConnection();
        }
    }

    public void findEmployeesMore(int bookNumber) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            Map<Employee, Integer> map = employeeDAO.findEmployeesWithNumberOfBooksMoreThan(bookNumber);
            showEmployeesMore(map);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            employeeDAO.closeConnection();
        }
    }

    public void findEmployeesLess(int bookNumber) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            Map<Employee, Integer> map = employeeDAO.findEmployeesWithNumberOfBooksLessEqThan(bookNumber);
            showEmployeesLess(map);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            employeeDAO.closeConnection();
        }
    }

    public boolean renameBook(String oldTitle, String newTitle) {
        boolean isRenamed = false;
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findBookByTitle(oldTitle);
            book.setTitle(newTitle);
            isRenamed = bookDAO.update(book);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            bookDAO.closeConnection();
        }
        return isRenamed;
    }


    public void createBook(String title, String author, String brief, int dateOfPublishing) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = new Book(title, author, brief, dateOfPublishing);
            bookDAO.create(book);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void showBook(int id) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findEntityById(id);
            System.out.println(book);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void updateBook(int id, String newTitle, String newAuthor, String newBrief, String newDateOfPublishing) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findEntityById(id);
            int dateOfPublishing = 0;
            if (newTitle.isEmpty()) {
                newTitle = book.getTitle();
            }
            if (newAuthor.isEmpty()) {
                newAuthor = book.getAuthor();
            }
            if (newBrief.isEmpty()) {
                newBrief = book.getBrief();
            }
            if (newDateOfPublishing.isEmpty()) {
                dateOfPublishing = book.getDateOfPublishing();
            } else {
                try {
                    dateOfPublishing = Integer.parseInt(newDateOfPublishing);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            book = new Book(newTitle, newAuthor, newBrief, dateOfPublishing);
            book.setBookId(id);
            bookDAO.update(book);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void deleteBook(int id) {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            bookDAO.closeConnection();
        }
    }

    private void showEmployeesMore(Map<Employee, Integer> map) {
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
    }

    private void showEmployeesLess(Map<Employee, Integer> map) {
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            Employee employee = entry.getKey();
            System.out.println(employee.getName() + "," + employee.getDateOfBirth() + " : " + entry.getValue());
        }
    }

    private void showBooks(List<Book> bookList) {
        for (Book book: bookList) {
            System.out.println(book);
        }
    }
}
