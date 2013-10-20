/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SHASapplication;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Uday
 */
public class DBconnection {
    
	String url = "jdbc:mysql://127.0.0.1/SHAS?" +"user=root&password=pass";
	Connection conn;
        Statement statement ;
        ResultSet rs;
        Date date = new Date();
        Timestamp time=new Timestamp(date.getTime());
        //Timestamp newTime;
         int minutes;
       // static long m;
        static int thermostatID,heaterID,sprinklerID,showerID,fridgeID;
        
        
        public void readThermostat(ThermostatBean bean){
	
            String id=null;String desired=null;
            int actualDesired=bean.getDesiredTemperature();
			
            try {
                Class.forName("com.mysql.jdbc.Driver");
        		// connect to db using DriverManager
			 System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
			 rs=statement.executeQuery("select * from configsmartthermostat ORDER by TimeStamp DESC LIMIT 1");
			
                        ///* 12:44:53 PM 127.0.0.1 */ INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `TimeStamp`) VALUES ('2', '60', '54', '3', '2', CURRENT_TIMESTAMP);
			while(rs.next()){
                                //System.out.println("inside the while");
                                id=rs.getString("ID");
                                System.out.println("id "+id);
                                String current=rs.getString("CurrentTemperature");
                                System.out.println("current "+current);
                                desired=rs.getString("DesiredTemperature");
                                System.out.println("desired "+desired);
                        }
			if(id.equals(Integer.toString(thermostatID))){
                            System.out.println("duplicate ID");
                            
                        }
                        else{
                            thermostatID=Integer.parseInt(id);
                          //  System.out.println("inside wlse ");
                            if(desired.equals(Integer.toString(actualDesired))){
                                                            System.out.println("same temperature");

                            }
                            else
                            {
                                   System.out.println("different temperature and desired is "+desired);

                                bean.setDesiredTemperature(Integer.parseInt(desired));
                                FormAccessor.shasObject.setDesiredTemperature(bean);
                                FormAccessor.setValues();

                            }
                        }
                        
        }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }
         
        public void readHeater(SmartWaterHeaterBean bean){
	
            String id=null;String startTime=null;String endTime=null;
            int actualStartTime=bean.getStartTime();int actualEndTime=bean.getEndTime();
			
            try {
                Class.forName("com.mysql.jdbc.Driver");
        		// connect to db using DriverManager
			 System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
			 rs=statement.executeQuery("select * from configsmartwaterheater ORDER by TimeStamp DESC LIMIT 1");
			
                        ///* 12:44:53 PM 127.0.0.1 */ INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `TimeStamp`) VALUES ('2', '60', '54', '3', '2', CURRENT_TIMESTAMP);
			while(rs.next()){
                               // System.out.println("inside the while");
                                id=rs.getString("ID");
                                System.out.println("id "+id);
                                startTime=rs.getString("StartTime");
                                System.out.println("startTime "+startTime);
                                endTime=rs.getString("EndTime");
                                System.out.println("endtime "+endTime);
                        }
			if(id.equals(Integer.toString(heaterID))){
                            System.out.println("duplicate ID");
                            
                        }
                        else{
                            heaterID=Integer.parseInt(id);
                            //System.out.println("inside wlse ");
                            if(startTime.equals(Integer.toString(actualStartTime)) && endTime.equals(Integer.toString(actualEndTime)) ){
                                                            System.out.println("same time");

                            }
                            else
                            {
                                   System.out.println("different time and start is "+startTime+"and end is "+endTime);

                            bean.setStartTime(Integer.parseInt(startTime));
                            bean.setEndTime(Integer.parseInt(endTime));
                            FormAccessor.shasObject.decideWaterTemperature(bean);
                            FormAccessor.setValues();

                            }
                        }
                        
        }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }
         public void readSprinkler(SprinklerBean bean){
	
            String id=null;String startTime=null;String moisture=null;
            int actualStartTime=bean.getStartTime();int actualDesiredMoisture=bean.getDesired();
			
            try {
                Class.forName("com.mysql.jdbc.Driver");
        		// connect to db using DriverManager
			 System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
			 rs=statement.executeQuery("select * from configsmartsprinkler ORDER by TimeStamp DESC LIMIT 1");
			
                        ///* 12:44:53 PM 127.0.0.1 */ INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `TimeStamp`) VALUES ('2', '60', '54', '3', '2', CURRENT_TIMESTAMP);
			while(rs.next()){
                                //System.out.println("inside the while");
                                id=rs.getString("ID");
                                System.out.println("id "+id);
                                startTime=rs.getString("StartTime");
                                System.out.println("startTime "+startTime);
                                moisture=rs.getString("DesiredMoisture");
                                System.out.println("Desiredmoisture "+moisture);
                        }
			if(id.equals(Integer.toString(sprinklerID))){
                            System.out.println("duplicate ID");
                            
                        }
                        else{
                            sprinklerID=Integer.parseInt(id);
                            System.out.println("inside wlse ");
                            if(startTime.equals(Integer.toString(actualStartTime)) && moisture.equals(Integer.toString(actualDesiredMoisture)) ){
                                                            System.out.println("same time and moisture");

                            }
                            else
                            {
                                   System.out.println("different time and start is "+startTime+"and moisture is"+moisture);

                            bean.setStartTime(Integer.parseInt(startTime));
                            bean.setDesired(Integer.parseInt(moisture));
                            FormAccessor.shasObject.decideSprinkleTime(bean);
                            FormAccessor.setValues();

                            }
                        }
                        
        }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }
         public void readShower(ShowerBean bean){
	
            String id=null;String temp1=null,temp2=null,temp3=null,temp4=null;
            int actualTemp1=shasController.temp1;int actualTemp2=shasController.temp2;int actualTemp3=shasController.temp3;int actualTemp4=shasController.temp4;
            try {
                Class.forName("com.mysql.jdbc.Driver");
        		// connect to db using DriverManager
			 System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
			 rs=statement.executeQuery("select * from configsmartshower ORDER by TimeStamp DESC LIMIT 1");
			
                        ///* 12:44:53 PM 127.0.0.1 */ INSERT INTO `logsthermostat` (`ID`, `User1`, `User2`, `User3`, `User4`, `TimeStamp`) VALUES ('2', '60', '54', '3', '2', CURRENT_TIMESTAMP);
			while(rs.next()){
                               // System.out.println("inside the while");
                                id=rs.getString("ID");
                                System.out.println("id "+id);
                                temp1=rs.getString("User1");
                                //System.out.println("startTime "+startTime);
                                temp2=rs.getString("User2");
                                //System.out.println("moisture "+moisture);
                                temp3=rs.getString("User3");
                                temp4=rs.getString("User4");

                        }
			if(id.equals(Integer.toString(showerID))){
                            System.out.println("duplicate ID");
                            
                        }
                        else{
                            showerID=Integer.parseInt(id);
                           // System.out.println("inside wlse ");
                            if(temp1.equals(Integer.toString(actualTemp1)) && temp2.equals(Integer.toString(actualTemp2)) && temp3.equals(Integer.toString(actualTemp3)) && temp4.equals(Integer.toString(actualTemp4)) ){
                                                            System.out.println("same temperature for user");

                            }
                            else
                            {
                                   System.out.println("different temperature"+temp1+""+temp2+""+temp3+""+temp4);

                                   shasController.temp1=Integer.parseInt(temp1);
                                   shasController.temp2=Integer.parseInt(temp2);
                                   shasController.temp3=Integer.parseInt(temp3);
                                   shasController.temp4=Integer.parseInt(temp4);
                                   System.out.println("temperatures Updated!!");

                           FormAccessor.setValues();

                            }
                        }
                        
        }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
         
         public void readFridge(SmartFridgeBean bean){
	
            String id=null;String startTime=null;String endTime=null;
            int actualStartTime=bean.getStartTime();int actualEndTime=bean.getEndTime();
			
            try {
                Class.forName("com.mysql.jdbc.Driver");
        		// connect to db using DriverManager
			 System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
			 rs=statement.executeQuery("select * from configsmartrefrigerator ORDER by TimeStamp DESC LIMIT 1");
			
                        ///* 12:44:53 PM 127.0.0.1 */ INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `TimeStamp`) VALUES ('2', '60', '54', '3', '2', CURRENT_TIMESTAMP);
			while(rs.next()){
                                //System.out.println("inside the while");
                                id=rs.getString("ID");
                                System.out.println("id "+id);
                                startTime=rs.getString("StartTime");
                                System.out.println("startTime "+startTime);
                                endTime=rs.getString("EndTime");
                                System.out.println("startTime "+endTime);
                        }
			if(id.equals(Integer.toString(fridgeID))){
                            System.out.println("duplicate ID");
                            
                        }
                        else{
                            fridgeID=Integer.parseInt(id);
                          //  System.out.println("inside wlse ");
                            if(startTime.equals(Integer.toString(actualStartTime)) && endTime.equals(Integer.toString(actualEndTime))  ){
                                                            System.out.println("same time and moisture");

                            }
                            else
                            {
                                   System.out.println("different time and start is "+startTime+"and moisture is"+endTime);

                            bean.setStartTime(Integer.parseInt(startTime));
                            bean.setEndTime(Integer.parseInt(endTime));
                            //bean.setDesired(Integer.parseInt(moisture));
                            FormAccessor.shasObject.decideFridgeOperation(bean);
                            FormAccessor.setValues();

                            }
                        }
                        
        }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }
    
        
        @SuppressWarnings("empty-statement")
        public void writeThermostat(ThermostatBean bean){
                  
            int energyMode;
            Double usage=0.0;
            String status,mode = null; //for DB
            Boolean onOff;
            int energy;
            String operatingMode;
            onOff=bean.getOnOff();
            operatingMode=bean.getOperatingMode();
            if(onOff)
                status="3";
            else
                status="0";
            if(operatingMode.equalsIgnoreCase("Hot"))
                mode="3";
            else if(operatingMode.equalsIgnoreCase("Idle"))
                mode="2";
            else if(operatingMode.equalsIgnoreCase("Cold"))
                mode="1";

            energyMode=bean.getEnergyMode();
            if(energyMode==3)
                usage=(15*(1.46/4));
            else if(energyMode==2)
                usage=(15*(0.73/4));
            else if(energyMode==1)
                usage=(15*(0.365/4));
          //  System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                        //System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
               
                         int executeUpdate = statement.executeUpdate("INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `EnergyMode`,`TimeStamp`) VALUES (NULL, '"+bean.getCurrentTemperature()+"', '"+bean.getDesiredTemperature()+"', '"+status+"', '"+mode+"','"+bean.getEnergyMode()+"','"+time+"')");
                         int executeAgain=statement.executeUpdate("INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`,`TimeStamp`) VALUES (NULL, '1', '"+usage.toString()+"','NULL','"+time+"')");
                         
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                }
        }
            public void writeHeater(SmartWaterHeaterBean bean){
                  
            String status,mode = null; //for DB
            Double energyUsed=0.0;
            Boolean onOff;
            int energy;
            String operatingMode;
            onOff=bean.getOnOff();
            if(onOff){
                status="3";
                energyUsed=(15 * (5.5/4));
            }
            else
                status="0";
           //minutes+=15;
           //System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                       // System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
                int executeUpdate = statement.executeUpdate("INSERT INTO `logsmartwaterheater` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `EnergyMode`,`TimeStamp`) VALUES (NULL, '"+bean.getCurrentTemperature()+"', '"+bean.getDesiredTemperature()+"', '"+status+"','"+bean.getEnergyMode()+"','"+time+"')");
                int executeAgain=statement.executeUpdate("INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`,`TimeStamp`) VALUES (NULL, '3', '"+energyUsed.toString()+"','NULL','"+time+"')");
         
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                 }
        
        }
        public void writeSprinkler(SprinklerBean bean){
                  
            String status,mode = null;
            String water;//for DB
            int waterUsage=0;
            Boolean onOff;
            onOff=bean.getOnOff();
            if(onOff){
                status="3";water="3";waterUsage=15*3;
                
            }
            else{
                status="0";water="0";
            }
            
           //minutes+=15;
           //System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                       // System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
                
                         int executeAgain=statement.executeUpdate("INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`,`TimeStamp`) VALUES (NULL, '5','NULL', '"+Integer.toString(waterUsage)+"','"+time+"')");
                         int executeUpdate = statement.executeUpdate("INSERT INTO `logsmartsprinkler` (`ID`, `CurrentMoisture`, `DesiredMoisture`, `WaterStatus`,`DeviceStatus`,`TimeStamp`) VALUES (NULL, '"+bean.getMoisture()+"', '"+bean.getDesired()+"', '"+water+"','"+status+"','"+time+"')");
                         
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                 }
        
        }
        public void writeShower(ShowerBean bean){
                  
            String status,mode = null;
            String water;//for DB
            int waterUsage=0;

            Boolean onOff,waterOnOff;
            waterOnOff=bean.getWaterOnOff();
            onOff=bean.getOnOff();
            if(onOff)
                status="3";
            else
                status="0";
            if(waterOnOff){
                water="3";
                waterUsage=15*3;
            }
            else
                water="0";
            
           //minutes+=15;
          // System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                       // System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
                int executeUpdate = statement.executeUpdate("INSERT INTO `logsmartshower` (`ID`, `UserSelection`, `WaterStatus`,`DeviceStatus`,`TimeStamp`) VALUES (NULL, '"+bean.getUserProfile()+"','"+water+"','"+status+"','"+time+"')");
                int executeAgain=statement.executeUpdate("INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`,`TimeStamp`) VALUES (NULL, '6','NULL', '"+Integer.toString(waterUsage)+"','"+time+"')");
         
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                 }
        
        }
        
        public void writeFridge(SmartFridgeBean bean){
                  
            String status,mode = null;
            String door;//for DB
            Double energyUsed=0.0;
            Boolean onOff,doorOpen;
            doorOpen=bean.getDoor();
            onOff=bean.getOnOff();
            if(onOff)
                status="3";
            else
                status="0";
            if(doorOpen)
                door="3";
            else
                door="0";
            mode=bean.getOperatingMode();
            if(mode.equalsIgnoreCase("Low")){
                mode="1";
                energyUsed=(15 * (0.5/4));
            }
            else if (mode.equalsIgnoreCase("normal")){
                mode="2";
                energyUsed= 15* 0.25;
            }
             else if (mode.equalsIgnoreCase("off"))
                mode="0";

            
           //minutes+=15;
           //System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                       // System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
                int executeUpdate = statement.executeUpdate("INSERT INTO `logsmartrefrigerator` (`ID`, `DoorStatus`,`DeviceStatus`,`EnergyMode`,`TimeStamp`) VALUES (NULL, '"+door+"','"+status+"','"+mode+"','"+time+"')");
                int executeAgain=statement.executeUpdate("INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`,`TimeStamp`) VALUES (NULL, '4', '"+energyUsed.toString()+"','NULL','"+time+"')");
                  
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                 }
        
        }
        
        public void writePlug(SmartPlugBean bean){
                  
            String status,name = null,deviceID=null;
            String door;//for DB
            Double energyUsed=0.0;
            Boolean onOff,doorOpen;
           // doorOpen=bean.getDoor();
            name=bean.getDeviceName();
            onOff=bean.getOnOff();
            if(onOff)
                status="3";
            else
                status="0";
            if(name.equalsIgnoreCase("oven")){
                deviceID="9";
            }
            else if(name.equalsIgnoreCase("Coffee maker")){
                deviceID="8";
            }
            
            
           //minutes+=15;
           //System.out.println("times mode "+mode+"time smp incr"+minutes);
           minutes=time.getMinutes()+15;
           time.setMinutes(minutes);
           int executeUpdate;
           
            
            try{        
                        Class.forName("com.mysql.jdbc.Driver");
                       // System.out.println("The Jdbc URL is "+url);
			 conn =DriverManager.getConnection( url);
                         statement = conn.createStatement();
                         if (deviceID.equals("8")&& status.equalsIgnoreCase("ON"))
                                executeUpdate = statement.executeUpdate("UPDATE INTO `configsmartplug` SET `StatusID`='1' WHERE ID='8'");                                                        //UPDATE table_name SET column1=value, column2=value2,...WHERE some_column=some_value
                         else if(deviceID.equals("9")&& status.equalsIgnoreCase("ON"))
                                 
                                executeUpdate = statement.executeUpdate("UPDATE INTO `configsmartplug` SET `StatusID`='1' WHERE ID='9'");
                          else if(deviceID.equals("8")&& status.equalsIgnoreCase("OFF"))
                                 
                                executeUpdate = statement.executeUpdate("UPDATE INTO `configsmartplug` SET `StatusID`='2' WHERE ID='8'");
                          else if(deviceID.equals("9")&& status.equalsIgnoreCase("OFF"))
                                 
                                executeUpdate = statement.executeUpdate("UPDATE INTO `configsmartplug` SET `StatusID`='2' WHERE ID='9'");
                         
            }
            	catch ( ClassNotFoundException cnfex ) 
		{
			System.out.println("Class not found !! " +cnfex);			
		}
		catch ( SQLException sqlex ) 
		{
			System.out.println("SQL Exception !! " +sqlex);			
			sqlex.printStackTrace();
		}
		catch (Exception ex)
		{
			System.out.println("Exception has occured "+ex);
		}
            finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
                 }
        
        }




}
