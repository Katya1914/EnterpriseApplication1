/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import local.ist111.lab7.dao.Dao;
import local.ist111.lab2.model.Book;

/**
 *
 * @author USER
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean {
    @EJB
    private Dao dao;
    
    
    
    @Inject
    ConversationCounterBean counter;
    
    private List<Book> list;
    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }
    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
        
    }
    @PostConstruct
    public void construct()
    {
        list=dao.listAllBook();
    }
    public String deleteBean(int id)
    {
        dao.deleteBook(id);
        list=dao.listAllBook();
        counter.dinc();
        return "index.xhtml";
    }
    
    public String toCreate()
    {
        return "saveupdate.xhtml";
    }
    
    public String toUpdate(int id)
    {
        return "saveupdate.xhtml";
    }
    
    public String endDialog()
            {
                counter.refreshConv();
                return "index.xhtml";
            }
}
