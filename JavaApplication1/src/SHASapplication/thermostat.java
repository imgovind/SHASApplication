 package SHASapplication;
 import java.util.*;


public class thermostat {

	private String operatingMode ;
	private int desiredTemperature,insideTemperature,difference;
	//private Boolean override=false;

	//this will return the current temperature to the display unit while operating 
	public ThermostatBean setDesiredTemperature(ThermostatBean bean) throws InterruptedException
	{
		

		ThermostatController instance1 = new ThermostatController();
                desiredTemperature=bean.getDesiredTemperature();
		insideTemperature=bean.getCurrentTemperature();
		bean.setOnOff(true);
		if(desiredTemperature > insideTemperature)
		{
			operatingMode = "HOT";
			bean.setOperatingMode(operatingMode);
                        bean.setEnergyMode(0);
                         System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                         FormAccessor.dbConn.writeThermostat(bean);//write DB
			while(desiredTemperature != insideTemperature)
			{	
                                difference=desiredTemperature-insideTemperature;

                                if(difference>10){
                                    Thread.sleep(1000);
                                    bean.setEnergyMode(3);
                                    insideTemperature+=4;
				bean.setCurrentTemperature(insideTemperature);
				FormAccessor.dbConn.writeThermostat(bean);//write DB
                                System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                                }
                                else if (difference>5){
                                Thread.sleep(1000);
                                bean.setEnergyMode(2);
                                insideTemperature+=2;;
				bean.setCurrentTemperature(insideTemperature);
				FormAccessor.dbConn.writeThermostat(bean);//write DB
                                System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());}
                                else{
				Thread.sleep(1000);
				insideTemperature++;  
                                bean.setEnergyMode(1);
				bean.setCurrentTemperature(insideTemperature);
				FormAccessor.dbConn.writeThermostat(bean);//write DB
                                System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                                }

			}
                       bean.setOperatingMode("IDLE");
                       bean.setEnergyMode(0);
                      FormAccessor.dbConn.writeThermostat(bean);//write DB
                       System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());

                        return bean;



		}
		else if(desiredTemperature < insideTemperature)
		{
			operatingMode = "COLD";
			bean.setOperatingMode(operatingMode);
                        bean.setEnergyMode(0);
                        System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                        FormAccessor.dbConn.writeThermostat(bean);//write DB
			while(desiredTemperature!=insideTemperature)
			{
			
                            difference=desiredTemperature-insideTemperature;
                                
                            if(difference<-10){
                                    Thread.sleep(1000);
				insideTemperature-=4;
                                bean.setEnergyMode(3);
				bean.setCurrentTemperature(insideTemperature);
                                FormAccessor.dbConn.writeThermostat(bean);//write DB
				System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                                }
                                else if (difference<-5){
                                Thread.sleep(1000);
                                bean.setEnergyMode(2);
				insideTemperature-=2;;
				bean.setCurrentTemperature(insideTemperature);
                                FormAccessor.dbConn.writeThermostat(bean);//write DB
				System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());}
                                else{
				Thread.sleep(1000);
                                bean.setEnergyMode(1);
				insideTemperature--;
				bean.setCurrentTemperature(insideTemperature);
                                FormAccessor.dbConn.writeThermostat(bean);//write DB
				System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());
                                }	
                            
			}
                      bean.setOperatingMode("IDLE");
                      bean.setEnergyMode(0);
                      FormAccessor.dbConn.writeThermostat(bean);//write DB
                      System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());

                        return bean;


		}
		else if(bean.getDesiredTemperature() == bean.getCurrentTemperature())
		{

			operatingMode = "IDLE";
			bean.setOperatingMode(operatingMode);
			bean.setCurrentTemperature(insideTemperature);
                        bean.setEnergyMode(0);
                        FormAccessor.dbConn.writeThermostat(bean);//write DB
                        System.out.println("current "+bean.getCurrentTemperature()+"desired "+bean.getDesiredTemperature()+"Operating mode "+bean.getOperatingMode());

                        return bean;
		}

               

		return bean ;
        }

}
