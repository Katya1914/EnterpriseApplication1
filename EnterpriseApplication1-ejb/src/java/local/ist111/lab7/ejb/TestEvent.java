/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab7.ejb;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class TestEvent implements Serializable{
    int deleteCount;
    public void deleteEvent()
    {
        deleteCount++;
    }
}
