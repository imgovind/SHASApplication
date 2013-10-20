/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SmartPlugController {
    
    SmartPlug plugInstance = new SmartPlug();
    
    public void turnOffPlug(SmartPlugBean bean){
        plugInstance.turnOffPlug(bean);
    }
    
      public void turnOnPlug(SmartPlugBean bean){
          plugInstance.turnOnPlug(bean);
        
    }
}
