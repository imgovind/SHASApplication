using Mvc.Mailer;
using SHASApplication.Models;
using System;

namespace SHASApplication.Mailers
{ 
    public interface IUserMailer
    {
			MvcMailMessage Welcome();
			MvcMailMessage DeviceAlertTOFLT(device deviceModel,user userModel,TimeSpan t);
            MvcMailMessage DeviceAlertTOFLTEmergency(device deviceModel, user userModel, configemergencyauthority emergenModel, TimeSpan tS);
	}
}