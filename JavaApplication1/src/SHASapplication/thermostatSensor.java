package SHASapplication;
import java.util.Random;

public class thermostatSensor {

	private int  insideTemperature;

	//returns the current room temperature	
	int getInsideTemperature(){

		Random randomInt = new Random();
		insideTemperature=randomInt.nextInt(95-55+1)+55;
		return insideTemperature;
	}

	//returns the current temperature to the display unit after cool/heat started to reach desire temperature
	int returnCurrentTemperature(){
		return 0;
	}
}
