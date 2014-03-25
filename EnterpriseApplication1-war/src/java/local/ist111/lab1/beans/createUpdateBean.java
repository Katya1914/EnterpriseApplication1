/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import local.ist111.lab7.dao.Dao;
import local.ist111.lab2.model.Book;

/**
 *
 * @author USER
 */
@Named(value = "createUpdateBean")
@RequestScoped
public class createUpdateBean {
    private Book editable;
    @EJB
    Dao dao;
    /**
     * Creates a new instance of createUpdateBean
     */
    public createUpdateBean() {
    
    }
    
    @Inject
    ConversationCounterBean counter;
    
    @PostConstruct
    public void construct()
    {
          HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

    String id = req.getParameter("id");
    if(id==null)
    {
        editable=new Book();
        editable.setId(0);
    }
    else
    {
        Integer IntId=Integer.parseInt(id);
        if((editable=dao.getBookById(IntId))==null){
            editable=new Book();
            editable.setId(0);
        }
    }  
    }
    public String save()
    {
        if(editable.getId()<=0)
        {
            counter.cinc();
            dao.addBook(editable);
        }
        else
        {
            counter.uinc();
            dao.updateBook(editable);
        }
        return "index.xhtml";
    }

    public Book getEditable() {
        return editable;
    }

    public void setEditable(Book editable) {
        this.editable = editable;
    }
    
    public String back()
    {
        return "index.xhtml";
    }
}
