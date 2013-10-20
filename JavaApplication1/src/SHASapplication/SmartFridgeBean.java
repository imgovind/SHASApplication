/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SmartFridgeBean {
    private  Boolean OnOff=Boolean.TRUE,door=Boolean.TRUE;
    private  String operatingMode;
    private  int temperature,startTime=17,endTime=22;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Boolean getDoor() {
        return door;
    }

    public void setDoor(Boolean door) {
        this.door = door;
    }
    

    public  int getTemperature() {
        return temperature;
    }

    public  void setTemperature(int temperature) {
       this. temperature = temperature;
    }


    public  Boolean getOnOff() {
        return OnOff;
    }

    public  void setOnOff(Boolean OnOff) {
        this.OnOff = OnOff;
    }

    public  String getOperatingMode() {
        return operatingMode;
    }

    public  void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }
    
}
