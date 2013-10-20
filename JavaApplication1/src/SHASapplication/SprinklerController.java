/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SprinklerController {
    
    Sprinkler instance1 = new Sprinkler();
       
        public SprinklerBean decideSprinkleTime(SprinklerBean bean){
            bean=instance1.decideSprinkleTime(bean);
            return bean;
        }
     
}
