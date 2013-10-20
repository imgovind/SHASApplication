/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class ShowerBean {
     
    private  int currentTemperature,desiredTemperature,userProfile=0;
     Boolean onOff=Boolean.TRUE,waterOnOff,userStatus;

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public  int getUserProfile() {
        return userProfile;
    }

    public  void setUserProfile(int userProfile) {
        this.userProfile = userProfile;
    }
    

    public Boolean getWaterOnOff() {
        return waterOnOff;
    }

    public void setWaterOnOff(Boolean waterOnOff) {
        this.waterOnOff = waterOnOff;
    }

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
