/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uday
 */
public class SmartWaterHeater {
    
    int desiredWaterTemperature,currentWaterTemperature;
    
    
    public SmartWaterHeaterBean modifyTemperature(SmartWaterHeaterBean bean){
        int difference;
                desiredWaterTemperature=bean.getDesiredTemperature();
		currentWaterTemperature=bean.getCurrentTemperature();
                System.out.println("inside heater current temp"+currentWaterTemperature);

		//ThermostatController instance1 = new ThermostatController();
		bean.setOnOff(true);
                bean.setEnergyMode(0);
                FormAccessor.dbConn.writeHeater(bean);
		if( desiredWaterTemperature > currentWaterTemperature)
		{
			while(desiredWaterTemperature != currentWaterTemperature)
			{	
                            
                             difference=desiredWaterTemperature-currentWaterTemperature;
                                if(difference>10){
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException ex) {
                                     Logger.getLogger(SmartWaterHeater.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                    bean.setEnergyMode(3);
                                    currentWaterTemperature+=4;
                                    bean.setCurrentTemperature(currentWaterTemperature);
                                    FormAccessor.dbConn.writeHeater(bean);
                                    System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature());
                                }
                                else if (difference>5){
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException ex) {
                                     Logger.getLogger(SmartWaterHeater.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                    bean.setEnergyMode(2);
                                    currentWaterTemperature+=2;;
                                    bean.setCurrentTemperature(currentWaterTemperature);
                                    FormAccessor.dbConn.writeHeater(bean);
                                    System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature());}
                                else{
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException ex) {
                                     Logger.getLogger(SmartWaterHeater.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                 bean.setEnergyMode(1);   
                                 currentWaterTemperature++;
                                    bean.setCurrentTemperature(currentWaterTemperature);
                                    FormAccessor.dbConn.writeHeater(bean);
                                    System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature());
                                }
                                   

                            	//instance1.returnCurrentTemperature(bean.getCurrentTemperature());

			}
                        bean.setEnergyMode(0);  
                        FormAccessor.dbConn.writeHeater(bean);
                        System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature());
                                
                }
        return bean;
        
    }
    
}
