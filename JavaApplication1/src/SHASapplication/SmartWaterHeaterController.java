/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SmartWaterHeaterController {
    	
   private int waterTemperature;
    WaterSensor instance1 = new WaterSensor();
    SmartWaterHeater instance2 = new SmartWaterHeater();

        public int getWaterTemperature(){
		
	//System.out.println("inside smartwater heater controller ");
         waterTemperature = instance1.getCurrentTemperature();
        // System.out.println("inside smartwater heater controller  temp from sensor"+waterTemperature);

		return waterTemperature;
	}
        
        public SmartWaterHeaterBean modifyTemperature(SmartWaterHeaterBean bean){
            
           bean= instance2.modifyTemperature(bean);
           return bean;
        }
        

    
}
