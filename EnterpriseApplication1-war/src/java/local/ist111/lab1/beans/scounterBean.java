/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface scounterBean extends Serializable{
    
    public int getSaveCount();

    public void setSaveCount(int saveCount);

    public int getUpdateCount();

    public void setUpdateCount(int updateCount);

    public int getDeleteCount();

    public void setDeleteCount(int deleteCount);
    
    public void cinc();
    public void uinc();
    public void dinc();
}
