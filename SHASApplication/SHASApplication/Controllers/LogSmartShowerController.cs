using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class LogSmartShowerController : Controller
    {
        private shasEntities db = new shasEntities();
        //
        // GET: /LogSmartShower/
        static double FLOWMULTIPLIER = 4.75;
        static double IDLEMULTIPLIER = 0;
        public ActionResult Index()
        {
            var array = db.logsmartshowers.ToArray();
            var array2 = db.configsmartshowers.ToArray();
            Dictionary<string,string> dt = new Dictionary<string,string>();
            Dictionary<string,string> du = new Dictionary<string, string>();
            
            var userArray = db.users.ToArray();
            var tempArray = db.configsmartshowers.ToArray();
            Object[] oArray = new Object[4];
            foreach (var item in array2)
            {
                oArray[0] = item.User1;
                oArray[1] = item.User2;
                oArray[2] = item.User3;
                oArray[3] = item.User4;
            }

            int xj = 1;
            foreach (var user in userArray)
            {
                dt.Add(user.ID.ToString(),user.UserName);
                du.Add(user.ID.ToString(),oArray[xj - 1].ToString());
                xj++;
            }
            ViewBag.UserDictionary = dt;
            ViewBag.TempDictionary = du;

            //Water Usage:

            int FLOWMODE = 0;
            int NOFLMODE = 0;

            Object[] newArraySer = new Object[array.Length];
            Object[] newArraySAer = new Object[array.Length];
            xj = 0;
            foreach (var item in array)
            {
                if (item.WaterStatus.Equals("3"))
                {
                    FLOWMODE = FLOWMODE + 1;
                    newArraySer[xj] = (15 * (FLOWMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FLOWMULTIPLIER)) - (15 * (FLOWMULTIPLIER))).ToString();
                }
                else if (item.WaterStatus.Equals("0"))
                {
                    NOFLMODE = NOFLMODE + 1;
                    newArraySer[xj] = (15 * (IDLEMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FLOWMULTIPLIER)) - (15 * (IDLEMULTIPLIER))).ToString();
                }
                xj++;
            }

            //MId - 2

            int flowModeMinutes = FLOWMODE * 15;
            int noflModeMinutes = NOFLMODE * 15;


            int flowModeEnergyUsage = Convert.ToInt32(flowModeMinutes * (FLOWMULTIPLIER));
            int noflModeEnergyUsage = Convert.ToInt32(noflModeMinutes * (IDLEMULTIPLIER));

            //Mid - 3

            ViewBag.flowMM = flowModeMinutes;
            ViewBag.noflMM = noflModeMinutes;
            ViewBag.flowMU = flowModeEnergyUsage;
            ViewBag.noflMu = noflModeEnergyUsage;

            //Mid - 4

            //MY

            Double energySaved = default(Double);
            Double energySpent = default(Double);

            foreach (String item in newArraySAer)
            {
                Double sunter = Double.Parse(item);
                energySaved = energySaved + sunter;
            }

            foreach (String item in newArraySer)
            {
                Double sunter = Double.Parse(item);
                energySpent = energySpent + sunter;
            }

            //Master
            //Master:
            ViewBag.GContainer = newArraySer;
            ViewBag.eSpent = Convert.ToInt32(energySpent);
            ViewBag.eSaved = Convert.ToInt32(energySaved);

            //OPPUKKU CHAPPA
            Object[] newArray = new Object[array.Length];
            int i = 0;
            foreach (var item in array)
            {
                DateTime dtona = (DateTime)item.TimeStamp;
                newArray[i] = dtona.ToString("HH:mm");
                i++;
            }
            ViewBag.TimeLineStampMoney = newArray;
            return View(array);
        }

    }
}
