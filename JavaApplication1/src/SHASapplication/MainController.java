/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;
//import Design.SHAS_GUI;

import java.util.Timer;


/**
 *
 * @author Uday
 */
public class MainController {

    /**
     * @param args the command line arguments
     * 
     */
         
static FormAccessor object1 = new  FormAccessor();
static int tempDesired;
static Timer timer;
    public static void main(String args[]) {
        
       
          usual();
          timer = new Timer();  //At this line a new Thread will be created
          timer.schedule(new DBread(), 10*1000);


          //readDB();
    }
    public static void usual(){
        object1.callDevices();
        object1.setValues();
        //FormAccessor.dbConn.firstTry();
        
    }
    public static void readDB(){
        
        FormAccessor.dbConn.readThermostat(FormAccessor.beanObject);
        FormAccessor.dbConn.readHeater(FormAccessor.waterObject);
        FormAccessor.dbConn.readSprinkler(FormAccessor.sprinklerBean);
        FormAccessor.dbConn.readShower(FormAccessor.showerObject);
        FormAccessor.dbConn.readFridge(FormAccessor.fridgeBean);
        
    }
 
   
    public  static void setButton(int current,int desired){
      
       tempDesired=FormAccessor.beanObject.getDesiredTemperature();
        //System.out.println("tempDesired is "+tempDesired);
        ThermostatBean bean=new ThermostatBean();
        bean.setCurrentTemperature(current);
        bean.setDesiredTemperature(desired);
        tempDesired=FormAccessor.beanObject.getDesiredTemperature();
       // System.out.println("tempDesired is again"+tempDesired);
        
        bean= FormAccessor.shasObject.setDesiredTemperature(bean);
      //  System.out.println("after the call "+bean.getDesiredTemperature());
        int temp=bean.getDesiredTemperature();
        if (temp==0){
           
            object1.setValues();
        }
        else{
                        //System.out.println("inside else temp =0");

            FormAccessor.beanObject=bean;
            object1.setValues();
        }
        
    }
    
    public static void setProfile(int id){
        
        ShowerBean bean = new ShowerBean();
        object1.showerObject.setOnOff(Boolean.TRUE);
        object1.showerObject.setUserProfile(id);
        bean = object1.showerObject;
        bean=object1.shasObject.switchShowerOn(bean);
        object1.showerObject=bean;
        object1.setValues();
        
    }
    
    public static void turnOff(String text){
        
            if(text.equalsIgnoreCase("Coffee maker")){
                     
                     SmartPlugBean bean = new SmartPlugBean();
                     bean.setDeviceName("Coffee Maker");
                     //bean.setOnOff(Boolean.FALSE);
                     FormAccessor.shasObject.turnOffPlug(bean);
                 }
                 
                 else if(text.equalsIgnoreCase("oven")){
                     SmartPlugBean bean = new SmartPlugBean();
                     bean.setDeviceName("Oven");
                     //bean.setOnOff(Boolean.FALSE);
                     FormAccessor.shasObject.turnOffPlug(bean);
                     
                 }
         
        
        
        
    }
    
     public static void turnOn(String text){
       
                 if(text.equalsIgnoreCase("Coffee maker")){
                     
                     SmartPlugBean bean = new SmartPlugBean();
                     bean.setDeviceName("Coffee Maker");
                     //bean.setOnOff(Boolean.TRUE);
                     FormAccessor.shasObject.turnOnPlug(bean);
                 }
                 
                 else if(text.equalsIgnoreCase("oven")){
                     SmartPlugBean bean = new SmartPlugBean();
                     bean.setDeviceName("Oven");
                     //bean.setOnOff(Boolean.TRUE);
                     FormAccessor.shasObject.turnOnPlug(bean);
                     
                 }
     }
     
     public static void simulateThermostat(int currentTemperature,int desiredTemperature){
          
        
        ThermostatBean bean =new ThermostatBean();
        
        object1.beanObject.setCurrentTemperature(currentTemperature);
        object1.beanObject.setDesiredTemperature(desiredTemperature);
        
        bean=object1.beanObject;
       

        //code to call the thermostat
        bean=object1.shasObject.setDesiredTemperature(bean);
        object1.beanObject=bean;
        object1.setValues();
    
     }
     
     public static void simulateShower(String temp){
         ShowerBean bean = new ShowerBean();
         
         if(temp.equalsIgnoreCase("yes"))
                 FormAccessor.showerObject.setUserStatus(Boolean.TRUE);
         else if(temp.equalsIgnoreCase("no"))
                 FormAccessor.showerObject.setUserStatus(Boolean.FALSE); 
         bean=FormAccessor.showerObject;
         bean=FormAccessor.shasObject.switchShowerOn(bean);
         FormAccessor.showerObject=bean;
         FormAccessor.setValues();
     }
      public static void simulateFridge(String temp){
         SmartFridgeBean bean = new SmartFridgeBean();
         
         if(temp.equalsIgnoreCase("yes"))
                 FormAccessor.fridgeBean.setDoor(Boolean.TRUE);
         else if(temp.equalsIgnoreCase("no"))
                 FormAccessor.fridgeBean.setDoor(Boolean.FALSE); 
         bean=FormAccessor.fridgeBean;
         bean=FormAccessor.shasObject.decideFridgeOperation(bean);
         FormAccessor.fridgeBean=bean;
         FormAccessor.setValues();
     }
      
      public static void simulateSprinkler(int temp){
         SprinklerBean bean = new SprinklerBean();
         FormAccessor.sprinklerBean.setDesired(temp);
         bean=FormAccessor.sprinklerBean;
         bean=FormAccessor.shasObject.decideSprinkleTime(bean);
         FormAccessor.sprinklerBean=bean;
         FormAccessor.setValues();
     }
}
