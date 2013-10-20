/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SmartWaterHeaterBean {
    
    private  int currentTemperature,desiredTemperature,energyMode,startTime=-1,EndTime=-1;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return EndTime;
    }

    public void setEndTime(int EndTime) {
        this.EndTime = EndTime;
    }

    public int getEnergyMode() {
        return energyMode;
    }

    public void setEnergyMode(int energyMode) {
        this.energyMode = energyMode;
    }
     Boolean onOff=Boolean.TRUE;

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public int getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(int desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int temperature) {
        this.currentTemperature = temperature;
    }
    
}
