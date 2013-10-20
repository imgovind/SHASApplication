/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class Shower {
 
    private int currentTemperature,desiredTemperature;
    SmartWaterHeaterBean waterBean = new SmartWaterHeaterBean();
    SmartWaterHeaterController instance1 = new SmartWaterHeaterController();
    
    public ShowerBean setShowerOn(ShowerBean bean){
        
        bean.setOnOff(Boolean.TRUE);
        bean.setWaterOnOff(Boolean.TRUE);
        currentTemperature=bean.getCurrentTemperature();
        waterBean.setCurrentTemperature(currentTemperature);
        desiredTemperature=bean.getDesiredTemperature();
        waterBean.setDesiredTemperature(desiredTemperature);
            //FormAccessor.dbConn.writeShower(bean);
            
        if(currentTemperature < desiredTemperature){
            instance1.modifyTemperature(waterBean);
            bean.setCurrentTemperature(waterBean.getCurrentTemperature());
            bean.setWaterOnOff(Boolean.TRUE);
            //FormAccessor.dbConn.writeShower(bean);
            
        }
            FormAccessor.dbConn.writeShower(bean);
            
        return bean;
    }
            
    
}
