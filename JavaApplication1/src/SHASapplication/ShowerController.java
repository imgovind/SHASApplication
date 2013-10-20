/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class ShowerController {

    Shower instance2 = new Shower();

    
    public ShowerBean switchShowerOn(ShowerBean bean){
       
        bean=instance2.setShowerOn(bean);
        
        return bean;
        
    }
    
}
