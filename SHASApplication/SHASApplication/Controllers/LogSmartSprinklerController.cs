using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class LogSmartSprinklerController : Controller
    {
        //
        // GET: /LogSmartSprinkler/
        private shasEntities db = new shasEntities();
        static double FLOWMULTIPLIER = 10;
        static double IDLEMULTIPLIER = 0;
        public ActionResult Index()
        {
            var array = db.logsmartsprinklers.ToArray();

            //Water Usage:

            int FLOWMODE = 0;
            int NOFLMODE = 0;

            Object[] newArraySer = new Object[array.Length];
            Object[] newArraySAer = new Object[array.Length];
            int xj = 0;
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
                DateTime dt = (DateTime)item.TimeStamp;
                newArray[i] = dt.ToString("HH:mm");
                i++;
            }
            ViewBag.TimeLineStampMoney = newArray;
            return View(array);
        }

    }
}
