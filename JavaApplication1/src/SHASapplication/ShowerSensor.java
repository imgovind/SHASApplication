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
public class ShowerSensor {
    private Boolean motionSensor;

    public Boolean getMotionSensor() {
        Random randomBoolean = new Random();
        motionSensor = randomBoolean.nextBoolean();
        return motionSensor;
    }
    
    
    
}
