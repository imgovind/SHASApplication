using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class LogSmartWaterHeaterController : Controller
    {
        //
        // GET: /LogSmartWaterHeater/
        static double FULLMULTIPLIER = 1.375;
        static double HALFMULTIPLIER = 0.6875;
        static double ECOMULTIPLIER = 0.34375;
        static double IDLEMULTIPLIER = 0;

        private shasEntities db = new shasEntities();
        public ActionResult Index()
        {
            //EARLY
            var array = db.logsmartwaterheaters.ToArray();


            //MID - 1

            int FULLMODE = 0;
            int HALFMODE = 0;
            int QUARTMODE = 0;
            int ZEROMODE = 0;

            Object[] newArraySer = new Object[array.Length];
            Object[] newArraySAer = new Object[array.Length];
            int xj = 0;
            foreach (var item in array)
            {
                if (item.EnergyMode.Equals("3"))
                {
                    FULLMODE = FULLMODE + 1;
                    newArraySer[xj] = (15 * (FULLMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FULLMULTIPLIER)) - (15 * (FULLMULTIPLIER))).ToString();
                }
                else if (item.EnergyMode.Equals("2"))
                {
                    HALFMODE = HALFMODE + 1;
                    newArraySer[xj] = (15 * (HALFMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FULLMULTIPLIER) - (15 * (HALFMULTIPLIER))).ToString());
                }
                else if (item.EnergyMode.Equals("1"))
                {
                    QUARTMODE = QUARTMODE + 1;
                    newArraySer[xj] = (15 * (ECOMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FULLMULTIPLIER)) - (15 * (ECOMULTIPLIER))).ToString();
                }
                else if (item.EnergyMode.Equals("0"))
                {
                    ZEROMODE = ZEROMODE + 1;
                    newArraySer[xj] = (15 * (IDLEMULTIPLIER)).ToString();
                    newArraySAer[xj] = ((15 * (FULLMULTIPLIER)) - (IDLEMULTIPLIER)).ToString();
                }
                xj++;
            }

            //MId - 2

            int fullModeMinutes = FULLMODE * 15;
            int halfModeMinutes = HALFMODE * 15;
            int quartModeMinutes = QUARTMODE * 15;
            int zeroModeMinutes = ZEROMODE * 15;


            int fullModeEnergyUsage = Convert.ToInt32(fullModeMinutes * (FULLMULTIPLIER));
            int halfModeEnergyUsage = Convert.ToInt32(halfModeMinutes * (HALFMULTIPLIER));
            int quartModeEnergyUsage = Convert.ToInt32(quartModeMinutes * (ECOMULTIPLIER));
            int zeroModeEnergyUsage = Convert.ToInt32(zeroModeMinutes * (IDLEMULTIPLIER));


            //Mid - 3

            ViewBag.fullMM = fullModeMinutes;
            ViewBag.halfMM = halfModeMinutes;
            ViewBag.quartMM = quartModeMinutes;
            ViewBag.zeroMM = zeroModeMinutes;
            ViewBag.fullMU = fullModeEnergyUsage;
            ViewBag.halfMU = halfModeEnergyUsage;
            ViewBag.quartMU = quartModeEnergyUsage;
            ViewBag.zeroMu = zeroModeEnergyUsage;


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

            //FINAL
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
