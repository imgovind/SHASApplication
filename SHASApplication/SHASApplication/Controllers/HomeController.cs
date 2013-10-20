using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SHASApplication.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Message = "Simple Home Automation System";

            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "| SHAS Description Page";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "| SHAS Contact Team";

            return View();
        }

        public ActionResult Privacy() 
        {
            ViewBag.Message = "This is the privacy page of the SHAS Application Page.";
        
            return View();
        }

        public ActionResult Weather()
        {
            return View();
        }
    }
}
