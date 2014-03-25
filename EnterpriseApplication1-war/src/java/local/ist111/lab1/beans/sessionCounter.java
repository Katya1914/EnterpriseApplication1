/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab1.beans;

import javax.ejb.Stateful;

/**
 *
 * @author USER
 */
@Stateful
public class sessionCounter implements scounterBean{
    private int saveCount;
    private int updateCount;
    private int deleteCount;
    
    @Override
    public void cinc(){
        saveCount++;
    }
    @Override
    public void uinc(){
        updateCount++;
    }
    @Override
    public void dinc(){
        deleteCount++;
    }

    @Override
    public int getSaveCount() {
        return saveCount;
    }

    @Override
    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    @Override
    public int getDeleteCount() {
        return deleteCount;
    }

    @Override
    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }
}

