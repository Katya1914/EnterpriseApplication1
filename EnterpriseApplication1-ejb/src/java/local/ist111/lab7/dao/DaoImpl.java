/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import local.ist111.lab2.model.Book;

/**
 *
 * @author USER
 */
@Stateless
public class DaoImpl implements Dao {
    @PersistenceContext(unitName = "punitt")
    private EntityManager em;
    Connection connection;

    private DataSource datasource;
    
    @Override
    public List<Book> listAllBook() {
        /*try { 
            ArrayList<Book> bookList = new ArrayList<Book>(); 
            String query = "SELECT * FROM book"; 
            PreparedStatement stmt = getConnection().prepareStatement(query); 
            ResultSet resultSet = stmt.executeQuery(); 
            while (resultSet.next()) { 
                bookList.add(fromResultSet(resultSet)); 
            } return bookList; } 
        catch (Exception e) 
        { throw new RuntimeException( "An error has occurred in listAllBooks method", e); } 
        finally { closeConnection(); }*/
        return em.createQuery("Select b from Book b").getResultList();
    }

    @Override
    public Book getBookById(int id) {
        /*try { 
            Book b = new Book(); 
            String query = "SELECT * FROM book where id=?"; 
            PreparedStatement stmt = getConnection().prepareStatement(query); 
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery(); 
            if (resultSet.next()) { 
                return fromResultSet(resultSet);
            }
            return null;
        } 
        catch (Exception e) 
        { throw new RuntimeException( "An error has occurred in listAllBooks method", e); } 
        finally { closeConnection(); }*/
        return (Book) em.createQuery("select b from Book b where b.id = :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public Book addBook(Book b) {
        /*try { String query = "INSERT INTO book (`name`,`crdate`) VALUES (?, ?)"; 
        PreparedStatement stmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); 
        stmt.setString(1, b.getName()); 
        stmt.setTimestamp(2, new Timestamp(b.getCreateDate().getTime())); 
        stmt.executeUpdate(); 
        ResultSet resultSet = stmt.getGeneratedKeys(); 
        if (resultSet.next()) 
        { b.setId(resultSet.getInt(1)); return b; } 
        throw new Exception("Book not added to db"); } 
        catch (Exception e) { throw new RuntimeException("An error has occurred in addBook method", e); } 
        finally { closeConnection(); }*/
        em.persist(b);
        em.joinTransaction();
        return b;
    }

    @Override
    public Book updateBook(Book b) {
        /*try { String query = "update book set name=?,crdate=? where id=?"; 
        PreparedStatement stmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); 
        stmt.setString(1, b.getName()); 
        stmt.setTimestamp(2, new Timestamp(b.getCreateDate().getTime())); 
        stmt.setInt(3, b.getId());
        stmt.executeUpdate(); 
        return getBookById(b.getId());
        } 
        catch (Exception e) { throw new RuntimeException("An error has occurred in addBook method", e); } 
        finally { closeConnection(); }*/
        em.merge(b);
        em.joinTransaction();
        return b;
    }

    @Override
    public boolean deleteBook(int id) {
        /*try { String query = "delete from book where id=?"; 
        PreparedStatement stmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); 
        stmt.setInt(1, id); 
        stmt.executeUpdate(); 
        return true; } 
        catch (Exception e) { throw new RuntimeException("An error has occurred in deleteBook method", e); } 
        finally { closeConnection(); }*/
        em.remove(em.find(Book.class, id));
        em.joinTransaction();
        return true;
    }

    private Connection getConnection() {
            try {
                connection=datasource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        return connection;
    }

    private Book fromResultSet(ResultSet resultSet) {
        Book b=null;
        try {
            b=new Book();
            b.setId(resultSet.getInt(1));
            b.setName(resultSet.getString(2));
            b.setCreateDate(resultSet.getDate(3));
        } catch (SQLException ex) {
            Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    private void closeConnection() {
        if(connection!=null)
        {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @PostConstruct
    public void construct()
    {
        try {
            javax.naming.InitialContext jndiCntx = new InitialContext();
            datasource = (DataSource)
                    jndiCntx.lookup("jdbc/myDb");
        } catch (NamingException ex) {
            Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
