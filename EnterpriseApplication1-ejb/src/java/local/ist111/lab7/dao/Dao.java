/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab7.dao;

import java.util.List;
import javax.ejb.Remote;
import local.ist111.lab2.model.Book;

/**
 *
 * @author USER
 */
@Remote
public interface Dao {
    List<Book> listAllBook();
    Book getBookById(int id);
    Book addBook(Book b);
    Book updateBook(Book b);
    boolean deleteBook(int id);
}
