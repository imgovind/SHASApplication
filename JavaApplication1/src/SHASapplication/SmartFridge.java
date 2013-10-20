/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;


/**
 *
 * @author Uday
 */
public class SmartFridge {
    
    int current,desired;
    
            public SmartFridgeBean decideFridgeOperation(SmartFridgeBean bean){
            
                desired=bean.getTemperature();
                while(current<=desired){
                    current--;
                }
                
            return bean;
        }

}
