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
public class SprinklerSensor {
    
       private int moisture;

    public int getMoisture() {
        Random randomBoolean = new Random();
        moisture = randomBoolean.nextInt(80-20+1)+20;
        return moisture;
    }
    
}
