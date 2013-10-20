using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SHASApplication.Models;

namespace SHASApplication.Controllers
{
    
    public class LogThermostatController : Controller
    {
        static double FULLMULTIPLIER = 0.365;
        static double HALFMULTIPLIER = 0.1825;
        static double ECOMULTIPLIER = 0.09125;
        static double IDLEMULTIPLIER = 0;
        private shasEntities db = new shasEntities();
        //
        // GET: /LogThermostat/
        
        public ActionResult Index()
        {
            //One Array
            var array = db.logsthermostats.ToArray();

            //Calculation : Energy
            int HOTFULLMODE = 0;
            int HOTHALFMODE = 0;
            int HOTQUARTMODE = 0;
            int HOTZEROMODE = 0;

            int COLDFULLMODE = 0;
            int COLDHALFMODE = 0;
            int COLDQUARTMODE = 0;
            int COLDZEROMODE = 0;

            int IDLEFULLMODE = 0;
            int IDLEHALFMODE = 0;
            int IDLEQUARTMODE = 0;
            int IDLEZEROMODE = 0;

            int FULLMODE = 0;
            int HALFMODE = 0;
            int QUARTMODE = 0;
            int ZEROMODE = 0;
            

            foreach (var item in array)
            {
                if (item.OperatingMode.Equals("3")) 
                {
                    if (item.EnergyMode.Equals("3"))
                    {
                        HOTFULLMODE = HOTFULLMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("2"))
                    {
                        HOTHALFMODE = HOTHALFMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("1"))
                    {
                        HOTQUARTMODE = HOTQUARTMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("0"))
                    {
                        HOTZEROMODE = HOTZEROMODE + 1;
                    }
                }
                else if (item.OperatingMode.Equals("2")) 
                {
                    if (item.EnergyMode.Equals("3"))
                    {
                        IDLEFULLMODE = IDLEFULLMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("2"))
                    {
                        IDLEHALFMODE = IDLEHALFMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("1"))
                    {
                        IDLEQUARTMODE = IDLEQUARTMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("0"))
                    {
                        IDLEZEROMODE = IDLEZEROMODE + 1;
                    }
                }
                else if (item.OperatingMode.Equals("1"))
                {
                    if (item.EnergyMode.Equals("3"))
                    {
                        COLDFULLMODE = COLDFULLMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("2"))
                    {
                        COLDHALFMODE = COLDHALFMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("1"))
                    {
                        COLDQUARTMODE = COLDQUARTMODE + 1;
                    }
                    else if (item.EnergyMode.Equals("0"))
                    {
                        COLDZEROMODE = COLDZEROMODE + 1;
                    }
                }
            }

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

            //


            int hotFullModeMinutes = HOTFULLMODE * 15;
            int hotHalfModeMinutes = HOTHALFMODE * 15;
            int hotQuartModeMinutes = HOTQUARTMODE * 15;
            int hotZeroModeMinutes = HOTZEROMODE * 15;

            int coldFullModeMinutes = COLDFULLMODE * 15;
            int coldHalfModeMinutes = COLDHALFMODE * 15;
            int coldQuartModeMinutes = COLDQUARTMODE * 15;
            int coldZeroModeMinutes = COLDZEROMODE * 15;

            int idleFullModeMinutes = IDLEFULLMODE * 15;
            int idleHalfModeMinutes = IDLEHALFMODE * 15;
            int idleQuartModeMinutes = IDLEQUARTMODE * 15;
            int idleZeroModeMinutes = IDLEZEROMODE * 15;

            int fullModeMinutes = FULLMODE * 15;
            int halfModeMinutes = HALFMODE * 15;
            int quartModeMinutes = QUARTMODE * 15;
            int zeroModeMinutes = ZEROMODE * 15;


            int fullModeEnergyUsage = Convert.ToInt32(fullModeMinutes * (FULLMULTIPLIER));
            int halfModeEnergyUsage = Convert.ToInt32(halfModeMinutes * (HALFMULTIPLIER));
            int quartModeEnergyUsage = Convert.ToInt32(quartModeMinutes * (ECOMULTIPLIER));
            int zeroModeEnergyUsage = Convert.ToInt32(zeroModeMinutes * (IDLEMULTIPLIER));

            int hotFullModeEnergyUsage = Convert.ToInt32(hotFullModeMinutes * (FULLMULTIPLIER));
            int hotHalfModeEnergyUsage = Convert.ToInt32(hotHalfModeMinutes * (HALFMULTIPLIER));
            int hotQuartModeEnergyUsage = Convert.ToInt32(hotQuartModeMinutes * (ECOMULTIPLIER));
            int hotZeroModeEnergyUsage = Convert.ToInt32(hotZeroModeMinutes * (IDLEMULTIPLIER));

            int coldFullModeEnergyUsage = Convert.ToInt32(coldFullModeMinutes * (FULLMULTIPLIER));
            int coldHalfModeEnergyUsage = Convert.ToInt32(coldHalfModeMinutes * (HALFMULTIPLIER));
            int coldQuartModeEnergyUsage = Convert.ToInt32(coldQuartModeMinutes * (ECOMULTIPLIER));
            int coldZeroModeEnergyUsage = Convert.ToInt32(coldZeroModeMinutes * (IDLEMULTIPLIER));

            int idleFullModeEnergyUsage = Convert.ToInt32(idleFullModeMinutes * (FULLMULTIPLIER));
            int idleHalfModeEnergyUsage = Convert.ToInt32(idleHalfModeMinutes * (HALFMULTIPLIER));
            int idleQuartModeEnergyUsage = Convert.ToInt32(idleQuartModeMinutes * (ECOMULTIPLIER));
            int idleZeroModeEnergyUsage = Convert.ToInt32(idleZeroModeMinutes * (IDLEMULTIPLIER));

            int hotModeMinutes = hotFullModeMinutes + hotHalfModeMinutes + hotQuartModeMinutes + hotZeroModeMinutes;
            int coldModeMinutes = coldFullModeMinutes + coldHalfModeMinutes + coldQuartModeMinutes + coldZeroModeMinutes;
            int idleModeMinutes = idleFullModeMinutes + idleHalfModeMinutes + idleQuartModeMinutes + idleZeroModeMinutes;

            int hotModeEnergyUsage = hotFullModeEnergyUsage + hotHalfModeEnergyUsage + hotQuartModeEnergyUsage + hotZeroModeEnergyUsage;
            int coldModeEnergyUsage = coldFullModeEnergyUsage + coldHalfModeEnergyUsage + coldQuartModeEnergyUsage + coldZeroModeEnergyUsage;
            int idleModeEnergyUsage = idleFullModeEnergyUsage + idleHalfModeEnergyUsage + idleQuartModeEnergyUsage + idleZeroModeEnergyUsage;

            //Separate HCI
            ViewBag.hotFMU = hotFullModeEnergyUsage;
            ViewBag.hotHMU = hotHalfModeEnergyUsage;
            ViewBag.hotQMU = hotQuartModeEnergyUsage;
            ViewBag.hotZMU = hotZeroModeEnergyUsage;
            ViewBag.hotFMM = hotFullModeMinutes;
            ViewBag.hotHMM = hotHalfModeMinutes;
            ViewBag.hotQMM = hotQuartModeMinutes;
            ViewBag.hotZMM = hotZeroModeMinutes;

            ViewBag.coldFMU = coldFullModeEnergyUsage;
            ViewBag.coldHMU = coldHalfModeEnergyUsage;
            ViewBag.coldQMU = coldQuartModeEnergyUsage;
            ViewBag.coldZMU = coldZeroModeEnergyUsage;
            ViewBag.coldFMM = coldFullModeMinutes;
            ViewBag.coldHMM = coldHalfModeMinutes;
            ViewBag.coldQMM = coldQuartModeMinutes;
            ViewBag.coldZMM = coldZeroModeMinutes;

            ViewBag.idleFMU = idleFullModeEnergyUsage;
            ViewBag.idleHMU = idleHalfModeEnergyUsage;
            ViewBag.idleQMU = idleQuartModeEnergyUsage;
            ViewBag.idleZMU = idleZeroModeEnergyUsage;
            ViewBag.idleFMM = idleFullModeMinutes;
            ViewBag.idleHMM = idleHalfModeMinutes;
            ViewBag.idleQMM = idleQuartModeMinutes;
            ViewBag.idleZMM = idleZeroModeMinutes;

            //CONSOLIDATED HCI
            ViewBag.hotMM = hotModeMinutes;
            ViewBag.coldMM = coldModeMinutes;
            ViewBag.idleMM = idleModeMinutes;
            ViewBag.hotMU = hotModeEnergyUsage;
            ViewBag.coldMU = coldModeEnergyUsage;
            ViewBag.idleMU = idleModeEnergyUsage;

            //MAIN
            ViewBag.fullMM = fullModeMinutes;
            ViewBag.halfMM = halfModeMinutes;
            ViewBag.quartMM = quartModeMinutes;
            ViewBag.zeroMM = zeroModeMinutes;
            ViewBag.fullMU = fullModeEnergyUsage;
            ViewBag.halfMU = halfModeEnergyUsage;
            ViewBag.quartMU = quartModeEnergyUsage;
            ViewBag.zeroMu = zeroModeEnergyUsage;


            


            //Master:
            ViewBag.GContainer = newArraySer;
            ViewBag.eSpent = Convert.ToInt32(energySpent);
            ViewBag.eSaved = Convert.ToInt32(energySaved);



            Object[] newArray = new Object[array.Length];
            int i = 0;
            foreach (var item in array)
            {
                DateTime dt = (DateTime)item.TimeStamp;
                newArray[i] = dt.ToString("HH:mm");
                i++;
            }
            ViewBag.TimeLineStampMoney = newArray;

            return View(db.logsthermostats.ToList());
        }

    }
}
