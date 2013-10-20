/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;

/**
 *
 * @author Uday
 */
public class ControlPanel {
 
    shasController instance1 = new shasController();
    static int profile;
    
    public ShowerBean setProfile(ShowerBean bean) {
       
bean.setUserProfile(profile);

return bean;
         }

  //  public void setProfile(int profile) {
    //    this.profile = profile;
    //}
    
    
    
}
