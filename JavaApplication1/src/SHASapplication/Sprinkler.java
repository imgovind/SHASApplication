/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;
import java.util.Date;

/**
 *
 * @author Uday
 */
public class Sprinkler {
    
    private static int moisture,startTime,currentTime;
    
            public SprinklerBean decideSprinkleTime(SprinklerBean bean){
                
                int desired =bean.getDesired();
                moisture = bean.getMoisture();
               // System.out.println("current skeptical  moisture "+moisture);

                
                
                    
                    bean.setOnOff(Boolean.TRUE);
                    bean.setMoisture(moisture);
                    FormAccessor.dbConn.writeSprinkler(bean);

                    while(moisture <= desired){
                        moisture++;
                        bean.setMoisture(moisture);
                        FormAccessor.dbConn.writeSprinkler(bean);

                    }
                
                FormAccessor.dbConn.writeSprinkler(bean);
                return bean;
            }

    
}
