/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

import java.util.Random;

/**
 *
 * @author Uday
 */
public class WaterSensor {
  private static int currentTemperature;

   
    public int getCurrentTemperature() {
        
        Random r = new Random();
        currentTemperature=r.nextInt(60-30+1)+30;
        //System.out.println("random value"+currentTemperature);
        return (currentTemperature);
                    
    }
}
