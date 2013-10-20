/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class SprinklerBean {
    
    private  int moisture=-1,desired=80;
    private  Boolean onOff=Boolean.FALSE;
    private  int startTime=21;

    public int getDesired() {
        return desired;
    }

    public void setDesired(int desired) {
        this.desired = desired;
    }
    

    public  int getStartTime() {
        return startTime;
    }

    public  void setStartTime(int startTime) {
        this.startTime = startTime;
    }



    public  int getMoisture() {
        return moisture;
    }

    public  void setMoisture(int moisture) {
        this.moisture = moisture;
    }

    public  Boolean getOnOff() {
        return onOff;
    }

    public  void setOnOff(Boolean onOff) {
       this.onOff = onOff;
    }
    
}
