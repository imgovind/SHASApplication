using SHASApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class ConfigureController : Controller
    {
        //
        // GET: /Configure/

        private shasEntities db = new shasEntities();

        public ActionResult Index()
        {
            var devicesArray = db.devices.ToArray();

            device[] revDevicesArray = new device[7];

            int i = 0;
            foreach (var item in devicesArray) {
                if (i < 7) {
                    revDevicesArray[i] = devicesArray[i];
                }
                i++;
            }

            return View(revDevicesArray);
        }

    }
}
