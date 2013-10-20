/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uday
 */
public class SmartPlug {
    
    public int time,deviceTime;
    
     public void turnOffPlug(SmartPlugBean bean){
        bean.setOnOff(Boolean.FALSE);
        FormAccessor.dbConn.writePlug(bean);
        System.out.println("device "+ bean.getDeviceName()+" Turned OFF");
        
    }
    
      public void turnOnPlug(SmartPlugBean bean){
        bean.setOnOff(Boolean.TRUE);
        
        FormAccessor.dbConn.writePlug(bean);
        System.out.println("device "+ bean.getDeviceName()+" Turned ON");
        
    }
      
     
}
