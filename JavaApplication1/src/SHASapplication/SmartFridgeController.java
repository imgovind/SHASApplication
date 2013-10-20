/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SmartFridgeController {
    
    SmartFridge instance1 = new SmartFridge();
    
            public SmartFridgeBean decideFridgeOperation(SmartFridgeBean bean){
            
                instance1.decideFridgeOperation(bean);
            return bean;
        }

}
