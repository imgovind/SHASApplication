package SHASapplication;

import Design.SHAS_GUI;
import java.util.Random;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class shasController {


	private int insideTemperature,outsideTemperature,setTemperature,time,showerProfile;
        private static int currentWaterTemperature,desiredWaterTemperature=80;
	private int[] standardDesiredTemperature={65,80,70};
        private int[] standardDesiredWaterTemperature={80,50};
        public static int temp1=80,temp2=85,temp3=75,temp4=70;
        private Boolean motionSensor;

	ThermostatController instance1 = new ThermostatController();
        SmartWaterHeaterController instance2 = new SmartWaterHeaterController();
        ShowerController instance3 = new ShowerController();
        SprinklerController instance4 = new SprinklerController();
        SmartFridgeController instance5 = new SmartFridgeController();
        SmartPlugController instance6 = new SmartPlugController();
        
	Date dateObject = new Date();
	
	public void getInsideTemperature(){

		
		insideTemperature = instance1.getInsideTemperature();
		

	}

	
        
        // for smart water heater
        public void getInsideWaterTemperature(){

		
		currentWaterTemperature = instance2.getWaterTemperature();
		

	}
        
         public void setWaterTemperature(){

		
		currentWaterTemperature = instance2.getWaterTemperature();
		

	}
        public SmartWaterHeaterBean decideWaterTemperature(SmartWaterHeaterBean bean){
            
        Boolean onOff = bean.getOnOff();
        int startTime=bean.getStartTime();
        int endTime=bean.getEndTime();
        System.out.println("Smart WaterHeater");

            
            if(!onOff){
                bean.setCurrentTemperature(currentWaterTemperature);
                bean.setDesiredTemperature(0);
                return bean;
            }
            else{
            time = dateObject.getHours();
            getInsideWaterTemperature();
            bean.setOnOff(true);
            bean.setCurrentTemperature(currentWaterTemperature);
           // System.out.println("before big "+bean.getCurrentTemperature());
            if(startTime == -1 && endTime==-1){
                startTime=8;endTime=10;
            }
            desiredWaterTemperature=standardDesiredWaterTemperature[0];
            bean.setDesiredTemperature(desiredWaterTemperature);
               
            if(time >= startTime && time <= endTime){
                        
                        
                        if(desiredWaterTemperature > currentWaterTemperature){
                    
                          bean= instance2.modifyTemperature(bean);
                    
                     }
                }
                else
                    bean.setOnOff(Boolean.FALSE);
                
            return bean;
            }
        }
// for thermostat 
	public ThermostatBean decideTemperature(ThermostatBean beanInstance){
                
            Boolean onOff = beanInstance.getOnOff();
            getInsideTemperature();
	    beanInstance.setCurrentTemperature(insideTemperature);
                          System.out.println("Smart Thermostat");

            
            if(!onOff){
                //beanInstance.setCurrentTemperature(0);
                beanInstance.setDesiredTemperature(0);
                beanInstance.setOperatingMode("IDLE");
                return beanInstance;
                
            }
            else{
		time = dateObject.getHours();
		System.out.println("time is "+time);
		
		
		if(time >= 8 && time <= 17  ){
			
			setTemperature = standardDesiredTemperature[1];
			beanInstance.setDesiredTemperature(setTemperature);
		}
		else if(time >= 17 && time <= 21  ){
			
			setTemperature = standardDesiredTemperature[2];
			beanInstance.setDesiredTemperature(setTemperature);
		}
		else{
			
			setTemperature = standardDesiredTemperature[0];
			beanInstance.setDesiredTemperature(setTemperature);
		}
                
		if(setTemperature!=insideTemperature){
			
			beanInstance=setDesiredTemperature(beanInstance);
			
			//beanInstance.setDesiredTemperature(setTemperature);
			return beanInstance;
		}
		return beanInstance;
            }
	}

	public ThermostatBean setDesiredTemperature(ThermostatBean bean){
                
            int temperature = bean.getDesiredTemperature();
            int currentTemperature = bean.getCurrentTemperature();
            if(currentTemperature>95 || currentTemperature<55){
                
                System.out.println("Emergency Alert !!");
               // bean.setOnOff(Boolean.FALSE);
                bean.setCurrentTemperature(currentTemperature);
                bean.setDesiredTemperature(temperature);
                try {
                    /// bean.setOperatingMode("OFF");
                    bean= instance1.setDesiredTemperature(bean);
                } catch (InterruptedException ex) {
                    Logger.getLogger(shasController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return bean;
            }
            else{
            
            if(temperature>95 || temperature <55)
            {
                    System.out.println("Sorry , Cannot set "+ temperature +" as Desired ");
                    bean.setDesiredTemperature(80);
                    return bean;
            }
            else{
            
                try {
			
			bean=instance1.setDesiredTemperature(bean);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
            }
          }


	}
      

        public ShowerBean switchShowerOn(ShowerBean bean){
            
            //ControlPanel userInput = new ControlPanel();
            //bean=userInput.setProfile(bean);
              Boolean onOff = bean.getOnOff();
              ShowerSensor motion = new ShowerSensor();
              Boolean status=bean.getUserStatus();
              System.out.println("Smart Shower");
            
           
            if(status==null){
            
            bean.setCurrentTemperature(desiredWaterTemperature);
            bean.setDesiredTemperature(80);
            showerProfile=bean.getUserProfile();

            //need to use map with user temp as key value pair
            if(showerProfile==1){
                bean.setDesiredTemperature(temp1);
                bean.setUserProfile(1);
            }
            else if(showerProfile==2){
                bean.setDesiredTemperature(temp2);
                  bean.setUserProfile(2);
            }
            else if(showerProfile==3){
                bean.setDesiredTemperature(temp3);
                  bean.setUserProfile(3);
            }
            else if(showerProfile==4){
                bean.setDesiredTemperature(temp4);
                  bean.setUserProfile(4);
            }
            
            
            System.out.println(""+bean.getDesiredTemperature());

            bean=instance3.switchShowerOn(bean);
             //status=bean.getUserStatus();
            
            //code for motion sensor
            motionSensor=motion.getMotionSensor();
            if(motionSensor){
            System.out.println("Shower turned on");
            bean.setWaterOnOff(Boolean.TRUE);
            FormAccessor.dbConn.writeShower(bean);
            return bean;
            }
            else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                 motionSensor=motion.getMotionSensor();
                if(motionSensor){
                    System.out.println("Shower turned on");
                    bean.setWaterOnOff(Boolean.TRUE);
                    FormAccessor.dbConn.writeShower(bean);
                    return bean;
                }
                else{
                bean.setWaterOnOff(Boolean.FALSE);
                FormAccessor.dbConn.writeShower(bean);
                return bean;
                }
            }
           }
           
            else if(status){
                
            bean.setCurrentTemperature(desiredWaterTemperature);
            bean.setDesiredTemperature(80);
            showerProfile=bean.getUserProfile();

            //need to use map with user temp as key value pair
             if(showerProfile==1){
                bean.setDesiredTemperature(temp1);
                bean.setUserProfile(1);
            }
            else if(showerProfile==2){
                bean.setDesiredTemperature(temp2);
                  bean.setUserProfile(2);
            }
            else if(showerProfile==3){
                bean.setDesiredTemperature(temp3);
                  bean.setUserProfile(3);
            }
            else if(showerProfile==4){
                bean.setDesiredTemperature(temp4);
                  bean.setUserProfile(4);
            }
           
            
            System.out.println(""+bean.getDesiredTemperature());

            bean=instance3.switchShowerOn(bean);
             
             System.out.println("Shower turned on");
             bean.setWaterOnOff(Boolean.TRUE);
             FormAccessor.dbConn.writeShower(bean);
            
             return bean;
             
    
                }
            else if(!status){
                 bean.setCurrentTemperature(desiredWaterTemperature);
            bean.setDesiredTemperature(80);
            showerProfile=bean.getUserProfile();

            //need to use map with user temp as key value pair
            if(showerProfile==1)
                bean.setDesiredTemperature(temp1);
            else if(showerProfile==2)
                bean.setDesiredTemperature(temp2);
            else if(showerProfile==3)
                bean.setDesiredTemperature(temp3);
            else if(showerProfile==4)
                bean.setDesiredTemperature(temp4);
            
            
            System.out.println(""+bean.getDesiredTemperature());

            bean=instance3.switchShowerOn(bean);
            System.out.println("Shower turned OFF");
            bean.setWaterOnOff(Boolean.FALSE);
            FormAccessor.dbConn.writeShower(bean);
            

            try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                 motionSensor=motion.getMotionSensor();
                if(motionSensor){
                    System.out.println("Shower turned on");
                    bean.setWaterOnOff(Boolean.TRUE);
                    FormAccessor.dbConn.writeShower(bean);
            
                    return bean;
                }
                else{
                bean.setWaterOnOff(Boolean.FALSE);
                FormAccessor.dbConn.writeShower(bean);
                return bean;
                }
             
            
            }
            return bean;
        } 
        
        //for sprinkler
        
        public SprinklerBean decideSprinkleTime(SprinklerBean bean){
            
              //Boolean onOff = bean.getOnOff();
               int startTime,currentTime,moisture;
              SprinklerSensor instance1 = new SprinklerSensor();
              int currentMoisture=instance1.getMoisture();
              int desiredMoisture=bean.getDesired();
              bean.setMoisture(currentMoisture);
                            System.out.println("Smart Sprinkler");

               System.out.println("current moisture "+currentMoisture+"desired "+desiredMoisture);
             
           //   moisture = instance1.getMoisture();
             // bean.setMoisture(moisture);
              startTime=bean.getStartTime();
              currentTime = dateObject.getHours();
              System.out.println("time"+currentTime+" and start time"+startTime);
              
            if( currentMoisture >= desiredMoisture){
               // System.out.println("inside the moisture if");
                bean.setOnOff(Boolean.FALSE);
                bean.setMoisture(currentMoisture);
               // bean.setStartTime(startTime);
                FormAccessor.dbConn.writeSprinkler(bean);
                return bean;
            }
            else{
                //System.out.println("inside the else part ");
             if(startTime==currentTime)
                     bean=instance4.decideSprinkleTime(bean);          
                FormAccessor.dbConn.writeSprinkler(bean);
                return bean;
             
            }
          }
        
        //for fridge
        public SmartFridgeBean decideFridgeOperation(SmartFridgeBean bean){
           
            Boolean doorOpenClose=bean.getDoor();
            int startTime=bean.getStartTime();
            int endTime=bean.getEndTime();
            Boolean onOff;
                          System.out.println("Smart Fridge");

            
            if(doorOpenClose){
                bean.setOnOff(Boolean.TRUE);
            }
            else if(!doorOpenClose){
                System.out.println("Alert!!!");
                bean.setOnOff(Boolean.FALSE);
            }
              onOff = bean.getOnOff();
              
            
            if(!onOff){
                bean.setOperatingMode("OFF");
                //bean.setTemperature(0);
                FormAccessor.dbConn.writeFridge(bean);
                return bean;
            }
            else{
            bean.setTemperature(10);
            time = dateObject.getHours();
		System.out.println("time is "+time);
                
                bean.setOnOff(Boolean.TRUE);
		
                if(time >= startTime && time <= endTime ){
                        bean.setOperatingMode("LOW");
                        instance5.decideFridgeOperation(bean);

                }
                else{
			bean.setOperatingMode("Normal");
                        instance5.decideFridgeOperation(bean);

                }
                               
                
            FormAccessor.dbConn.writeFridge(bean);
            return bean;
            }
        }
        
     public void turnOffPlug(SmartPlugBean bean){
        instance6.turnOffPlug(bean);
         
        }
    
      public void turnOnPlug(SmartPlugBean bean){
          
          String name=bean.getDeviceName();
          if (name.equalsIgnoreCase("Coffee pot")){
          bean.setRunningTime(20);
            }
          else if (name.equalsIgnoreCase("oven")){
          bean.setRunningTime(10);
           }
          instance6.turnOnPlug(bean);
        
     }
            
            
            
        
        
        
        
}
