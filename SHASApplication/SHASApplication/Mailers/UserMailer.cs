using Mvc.Mailer;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using SHASApplication.Models;
using System.Data.Entity.Validation;
using System.Diagnostics;
using System.Security.Principal;
using System.Text;

namespace SHASApplication.Mailers
{ 
    public class UserMailer : MailerBase, IUserMailer 	
	{
        shasEntities db = new shasEntities();

		public UserMailer()
		{
			MasterName="_Layout";
		}
		
		public virtual MvcMailMessage Welcome()
		{
			//ViewBag.Data = someObject;
			return Populate(x =>
			{
				x.Subject = "Welcome";
				x.ViewName = "Welcome";
				x.To.Add("some-email@example.com");
			});
		}
 
		public virtual MvcMailMessage DeviceAlertTOFLT(device deviceModel,user userModel,TimeSpan tS)
		{
            ViewBag.ID = userModel.ID;
            ViewBag.UserName = userModel.UserName;
            ViewBag.DeviceName = deviceModel.Name;
            ViewBag.DeviceStatus = deviceModel.DeviceStatus;
            ViewBag.DeviceThreshold = deviceModel.DeviceThreshold;
            ViewBag.TimeStamp = deviceModel.TimeStamp;

            string tONDay = default(string);
            string tONHour = default(string);
            string tONMin = default(string);
            string tONSec = default(string);

            tONDay = tS.Days.ToString();
            tONHour = tS.Hours.ToString();
            tONMin = tS.Minutes.ToString();
            tONSec = tS.Seconds.ToString();

            ViewBag.tSDays = tONDay;
            ViewBag.tSHours = tONHour;
            ViewBag.tSMinutes = tONMin;
            ViewBag.tSSeconds = tONSec;

            StringBuilder sb = new StringBuilder();
            sb.Append(userModel.Mobile);
            sb.Append("@txt.att.net");
            string userMobileNumber = sb.ToString();
            sb.Clear();
            sb.Append("Turn Off");
            sb.Append(" ");
            sb.Append(deviceModel.Name);
            string deviceNameSubject = sb.ToString();
            sb.Clear();

            //ViewBag.Data = someObject;
			return Populate(x =>
			{
				x.Subject = deviceNameSubject;
				x.ViewName = "DeviceAlertTOFLT";
				x.To.Add(userModel.UserName);
                x.CC.Add(userMobileNumber);
                x.Bcc.Add("shasapplication@gmail.com");
			});
		}
        public virtual MvcMailMessage DeviceAlertTOFLTEmergency(device deviceModel, user userModel, configemergencyauthority emergenModel, TimeSpan tS)
        {
            ViewBag.ID = userModel.ID;
            ViewBag.UserName = userModel.UserName;
            ViewBag.DeviceName = deviceModel.Name;
            ViewBag.DeviceStatus = deviceModel.DeviceStatus;
            ViewBag.DeviceThreshold = deviceModel.DeviceThreshold;
            ViewBag.TimeStamp = deviceModel.TimeStamp;

            string tONDay = default(string);
            string tONHour = default(string);
            string tONMin = default(string);
            string tONSec = default(string);

            tONDay = tS.Days.ToString();
            tONHour = tS.Hours.ToString();
            tONMin = tS.Minutes.ToString();
            tONSec = tS.Seconds.ToString();

            ViewBag.tSDays = tONDay;
            ViewBag.tSHours = tONHour;
            ViewBag.tSMinutes = tONMin;
            ViewBag.tSSeconds = tONSec;

            ViewBag.emergenName = emergenModel.Name;

            StringBuilder sb = new StringBuilder();
            
            sb.Append(userModel.Mobile);
            sb.Append("@att.txt.net");
            string userMobileNumber = sb.ToString();
            sb.Clear();
            
            sb.Append(emergenModel.Number);
            sb.Append("@att.txt.net");
            string emergenMobileNumber = sb.ToString();
            sb.Clear();

            //ViewBag.Data = someObject;
            return Populate(x =>
            {
                x.Subject = "Alert | Device Turned On For Long Time";
                x.ViewName = "DeviceAlertTOFLTEmergen";
                x.To.Add(emergenModel.Email);
                x.To.Add(emergenMobileNumber);
                x.CC.Add(userModel.UserName);
                x.CC.Add(userMobileNumber);
                x.Bcc.Add("shasapplication@gmail.com");
            });        
        }
 	}
}