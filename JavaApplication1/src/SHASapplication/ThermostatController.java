package SHASapplication;

public class ThermostatController {

	thermostat instance1 = new thermostat();
	thermostatSensor instance2 = new thermostatSensor();

        int insideTemperature;

	//calls the sensor for getting inside temperature
	public int getInsideTemperature(){
		
		insideTemperature = instance2.getInsideTemperature();
		return insideTemperature;
	}

	//call to the thermostat to set the desired temperature
	public ThermostatBean setDesiredTemperature(ThermostatBean bean) throws InterruptedException{

                System.out.println("Thermostat ");

		bean = instance1.setDesiredTemperature(bean);
		return bean;
	}

	
}
