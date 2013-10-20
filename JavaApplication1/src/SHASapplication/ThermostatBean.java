package SHASapplication;

public class ThermostatBean {
	
	 int currentTemperature,desiredTemperature,energyMode;

    public int getEnergyMode() {
        return energyMode;
    }

    public void setEnergyMode(int energyMode) {
        this.energyMode = energyMode;
    }
	String operatingMode,usageData;
	 Boolean onOff=Boolean.TRUE;
	
	
	public int getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(int currentTemperature) {

		this.currentTemperature = currentTemperature;
	}
	public int getDesiredTemperature() {
		return desiredTemperature;
	}
	public void setDesiredTemperature(int desiredTemperature) {
		this.desiredTemperature = desiredTemperature;
	}
	public String getOperatingMode() {
		return operatingMode;
	}
	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
	}
	public String getUsageData() {
		return usageData;
	}
	public void setUsageData(String usageData) {
		this.usageData = usageData;
	}
	public Boolean getOnOff() {
		return onOff;
	}
	public void setOnOff(Boolean onOff) {
		this.onOff = onOff;
	}

}
