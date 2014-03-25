/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab7.ejb;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.enterprise.event.Observes;
import javax.interceptor.Interceptors;

/**
 *
 * @author USER
 */
@Singleton
@LocalBean
public class NewSessionBean {

    private int deleted=0;
    @Interceptors(Interceptor.class)
    public void deleteEvent(@Observes TestEvent testEvent)
    {
        System.out.println("added");
        deleted++;
    }
    public int getDeleted()
    {
        System.out.println("getted");
        return deleted;
    }

}
