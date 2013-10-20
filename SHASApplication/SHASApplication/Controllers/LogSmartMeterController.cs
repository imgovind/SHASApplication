using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class LogSmartMeterController : Controller
    {

        private shasEntities db = new shasEntities();

        //
        // GET: /LogSmartMeter/

        public ActionResult Index()
        {
            var array = db.logsmartmeters.ToArray();

            Dictionary<string, string> dicta = new Dictionary<string, string>();

            var devArray = db.devices.ToArray();

            foreach (var item in devArray) 
            {
                dicta.Add(item.ID.ToString(), item.Name);
            }

            ViewBag.DeviceDictionary = dicta;

            double ThermostatEnergyUsage = default(double), ThermostatWaterUsage = default(double), ThermostatOccurrence = default(double);
            double ShowerEnergyUsage = default(double), ShowerWaterUsage = default(double), ShowerOccurrence = default(double);
            double RefrigeratorEnergyUsage = default(double), RefrigeratorWaterUsage = default(double), RefrigeratorOccurrence = default(double);
            double SprinklerEnergyUsage = default(double), SprinklerWaterUsage = default(double), SprinklerOccurrence = default(double);

            foreach (var item in array) 
            {
                if (item.DeviceID.ToString().Equals("1")) 
                {
                    if (!item.EnergyUsed.Equals("NULL"))
                    {
                        ThermostatEnergyUsage = ThermostatEnergyUsage + double.Parse(item.EnergyUsed.ToString());
                    }
                    else
                    {
                        ThermostatEnergyUsage = ThermostatEnergyUsage + 0;
                    }
                    if (!item.WaterUsed.Equals("NULL"))
                    {
                        ThermostatWaterUsage = ThermostatWaterUsage + double.Parse(item.WaterUsed.ToString());
                    }
                    else
                    {
                        ThermostatWaterUsage = ThermostatWaterUsage + 0;
                    }

                    ThermostatOccurrence = ThermostatOccurrence + 1;
                }
                else if (item.DeviceID.ToString().Equals("4"))
                {
                    if (!item.EnergyUsed.Equals("NULL"))
                    {
                        RefrigeratorEnergyUsage = RefrigeratorEnergyUsage + double.Parse(item.EnergyUsed.ToString());
                    }
                    else
                    {
                        RefrigeratorEnergyUsage = RefrigeratorEnergyUsage + 0;
                    }
                    if (!item.WaterUsed.Equals("NULL"))
                    {
                        RefrigeratorWaterUsage = RefrigeratorWaterUsage + double.Parse(item.WaterUsed.ToString());
                    }
                    else
                    {
                        RefrigeratorWaterUsage = RefrigeratorWaterUsage + 0;
                    }

                    RefrigeratorOccurrence = RefrigeratorOccurrence + 1;
                }
                else if (item.DeviceID.ToString().Equals("5"))
                {
                    if (!item.EnergyUsed.Equals("NULL"))
                    {
                        SprinklerEnergyUsage = SprinklerEnergyUsage + double.Parse(item.EnergyUsed.ToString());
                    }
                    else
                    {
                        SprinklerEnergyUsage = SprinklerEnergyUsage + 0;
                    }
                    if (!item.WaterUsed.Equals("NULL"))
                    {
                        SprinklerWaterUsage = SprinklerWaterUsage + double.Parse(item.WaterUsed.ToString());
                    }
                    else
                    {
                        SprinklerWaterUsage = SprinklerWaterUsage + 0;
                    }

                    SprinklerOccurrence = SprinklerOccurrence + 1;
                }
                else if (item.DeviceID.ToString().Equals("6"))
                {
                    if (!item.EnergyUsed.Equals("NULL"))
                    {
                        ShowerEnergyUsage = ShowerEnergyUsage + double.Parse(item.EnergyUsed.ToString());
                    }
                    else
                    {
                        ShowerEnergyUsage = ShowerEnergyUsage + 0;
                    }
                    if (!item.WaterUsed.Equals("NULL"))
                    {
                        ShowerWaterUsage = ShowerWaterUsage + double.Parse(item.WaterUsed.ToString());
                    }
                    else
                    {
                        ShowerWaterUsage = ShowerWaterUsage + 0;
                    }
                    
                    ShowerOccurrence = ShowerOccurrence + 1;
                }
            }

            ViewBag.BubbleDumba = new dynamic[] 
            {
                new 
                {
                    x = ThermostatEnergyUsage,
                    y = ThermostatWaterUsage,
                    size = ThermostatOccurrence,
                    category = "ThermoStat"
                }, 
                new 
                {
                    x = RefrigeratorEnergyUsage,
                    y = RefrigeratorWaterUsage,
                    size = RefrigeratorOccurrence,
                    category = "Smart Refrigerator"
                }, 
                new 
                {
                    x = SprinklerEnergyUsage,
                    y = SprinklerWaterUsage,
                    size = SprinklerOccurrence,
                    category = "Smart Sprinkler"
                }, 
                new 
                {
                    x = ShowerEnergyUsage,
                    y = ShowerWaterUsage,
                    size = ShowerOccurrence,
                    category = "Smart Shower"
                }
            };

            ViewBag.tEU = ThermostatEnergyUsage;
            ViewBag.tWU = ThermostatWaterUsage;
            ViewBag.tOC = ThermostatOccurrence;
            ViewBag.shoEU = ShowerEnergyUsage;
            ViewBag.shoWU = ShowerWaterUsage;
            ViewBag.shoOC = ShowerOccurrence;
            ViewBag.reEU = RefrigeratorEnergyUsage;
            ViewBag.reWU = RefrigeratorWaterUsage;
            ViewBag.reOC = RefrigeratorOccurrence;
            ViewBag.sprEU = SprinklerEnergyUsage;
            ViewBag.sprWU = SprinklerWaterUsage;
            ViewBag.sprOC = SprinklerOccurrence;

            return View(array);
        }

    }
}
