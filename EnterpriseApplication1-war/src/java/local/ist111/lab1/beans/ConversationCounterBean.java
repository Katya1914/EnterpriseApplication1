/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import local.ist111.lab7.ejb.TestEvent;

/**
 *
 * @author USER
 */
@Named(value = "sessionCounterBean")
@ConversationScoped
public class ConversationCounterBean implements Serializable{

    /**
     * Creates a new instance of SessionCounterBean
     */
    public ConversationCounterBean() {
    }
    
    @Inject
    private Event<TestEvent> event;
    
    @Inject
    Conversation conv;
    @Inject
    sessionView counter;

    
    private int saveCount;
    private int updateCount;
    private int deleteCount;

    
    
    @PostConstruct
    public void postConstruct()
    {
        
        conv.begin();
    }
    
    @PreDestroy
    public void refreshConv()
    {
        conv.end();
    }
   
    @PostActivate
    public void activate()
    {
        System.out.println("activate");
    }
    @PrePassivate
    public void passivate()
    {
        System.out.println("Passivate");
    }
    public int getSaveCount() {
        return saveCount;
    }
    
 
    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }
    
  
    public int getUpdateCount() {
        return updateCount;
    }


    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }


    public int getDeleteCount() {
        return deleteCount;
    }


    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }
 

    public void cinc()
    {
        counter.getCounter().cinc();
        saveCount++;
    }

    public void uinc()
    {
        counter.getCounter().uinc();
        updateCount++;
    }
    
    public void dinc()
    {
        event.fire(new TestEvent());
        counter.getCounter().dinc();
        deleteCount++;
    }
}
