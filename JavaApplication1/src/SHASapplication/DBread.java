/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Uday
 */
public class DBread extends TimerTask {

   // Timer timer;
        @Override
        public void run() {
            //System.out.println("ReminderTask is completed by Java timer");
            MainController.readDB();
            MainController.timer.cancel(); //Not necessary because we call System.exit
            //System.exit(0); //Stops the AWT thread (and everything else)
        }


    
}
