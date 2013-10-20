/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

import Design.SHAS_GUI;

/**
 *
 * @author Uday
 */
public class FormAccessor {
    
     public  static SHAS_GUI object1 = new SHAS_GUI();
     //public  static SHAS_GUI object2 = new SHAS_GUI();
     public static shasController shasObject= new shasController();
     public static ThermostatBean beanObject = new ThermostatBean();
     public static SmartWaterHeaterBean waterObject = new SmartWaterHeaterBean();
     public static ShowerBean showerObject = new ShowerBean();
     public static SmartFridgeBean fridgeBean = new SmartFridgeBean();
     public static SprinklerBean sprinklerBean = new SprinklerBean();
     public static SmartPlugBean plugBean = new SmartPlugBean();
     public static DBconnection dbConn = new DBconnection();
     
   public static  void callDevices(){
        waterObject=shasObject.decideWaterTemperature(waterObject);
        showerObject = shasObject.switchShowerOn(showerObject);
        sprinklerBean = shasObject.decideSprinkleTime(sprinklerBean);
        fridgeBean = shasObject.decideFridgeOperation(fridgeBean);
        beanObject=shasObject.decideTemperature(beanObject);
    }
    
    public static void setValues(){
        
        object1.setThermostatValues(beanObject);
        object1.setWaterHeaterValues(waterObject);
        object1.setShowerValues(showerObject);
        object1.setSprinklerValues(sprinklerBean);
        object1.setSmartFridgeValues(fridgeBean);
        object1.setVisible(true);

    }
  
    
}
