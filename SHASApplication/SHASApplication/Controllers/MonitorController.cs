using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SHASApplication.Mailers;
using System.Data;
using System.Data.Entity;
using System.Web.Security;
using System.Data.Entity.Validation;
using System.Diagnostics;
using System.Security.Principal;

namespace SHASApplication.Controllers
{
    public class MonitorController : Controller
    {
        private shasEntities db = new shasEntities();
        //
        // GET: /Monitor/
        private IUserMailer _userMailer = new UserMailer();
        public IUserMailer UserMailer
        {
            get { return _userMailer; }
            set { _userMailer = value; }
        }
        public ActionResult Index()
        {
            var db = new shasEntities();
            var usersArray = db.users.ToArray();
            var devicesArray = db.devices.ToArray();
            var configSPArray = db.configsmartplugs.ToArray();
            configemergencyauthority emergencyAuthority = db.configemergencyauthorities.Find(1);
            foreach (var item in devicesArray)
            {
                if(item.DeviceStatus.Equals("ON")){
                    DateTime deviceDT = (DateTime)item.TimeStamp;
                    int devGovindID = item.ID;
                    var csmp = from x in db.configsmartplugs
                               where x.DeviceID == devGovindID
                               select x;
                    foreach (var summa in csmp) {
                        item.DeviceThreshold = summa.DeviceThreshold;
                    }

                    DateTime revDeviceDT = deviceDT.AddMinutes(Convert.ToDouble(item.DeviceThreshold));
                    DateTime nowDT = System.DateTime.Now;
                    int result = DateTime.Compare(nowDT,revDeviceDT);
                    TimeSpan t = default(TimeSpan);
                    t = nowDT - revDeviceDT;
                    if (result >= 0)
                    {
                        foreach (var userRecordItem in usersArray) {
                            //int userIDRec = getUserID(User.Identity.Name);
                            //var userRecord = db.users.Find(userIDRec, User.Identity.Name);
                            try
                            {
                                //Intimate User
                                UserMailer.DeviceAlertTOFLT(item, userRecordItem, t).Send();
                                //Intimate Emergency Authority
                                UserMailer.DeviceAlertTOFLTEmergency(item, userRecordItem, emergencyAuthority, t).Send();
                            }
                            catch (Exception ex)
                            {
                                Console.WriteLine(ex);   //Should print stacktrace + details of inner exception

                                if (ex.InnerException != null)
                                {
                                    Console.WriteLine("InnerException is: {0}", ex.InnerException);
                                }
                            }
                            //SEND EMAIL
                        }
                    }
                }
            }

            return View(devicesArray);
        }
        //Helper Methods
        public int getUserID(string userName)
        {
            int resultID = default(int);

            var userID = from x in db.users
                         where x.UserName == userName
                         select x;

            foreach (var item in userID)
            {
                resultID = item.ID;
            }

            return resultID;
        }
    }
}
