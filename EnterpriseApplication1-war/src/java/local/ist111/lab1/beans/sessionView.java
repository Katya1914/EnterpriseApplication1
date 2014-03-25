/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import local.ist111.lab7.ejb.NewSessionBean;

/**
 *
 * @author USER
 */
@Named(value = "sessionView")
@SessionScoped
public class sessionView implements Serializable {
    @EJB
    scounterBean counter;
    @EJB
    NewSessionBean nsb;
    /**
     * Creates a new instance of sessionView
     */
    public sessionView() {
    }
    public int getSaveCount()
    {
        return counter.getSaveCount();
    }


    public int getUpdateCount(){
        return counter.getUpdateCount();
    }

    public int getDeleteCount(){
        return counter.getDeleteCount();
        //return 0;
    }
    
    public scounterBean getCounter()
    {
        return counter;
    }
    
    public int getDeleted()
    {
        return nsb.getDeleted();
    }
}
