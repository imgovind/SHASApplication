using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SHASApplication.Models;

namespace SHASApplication.Controllers
{
    public class ConfigureThermostatController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureThermostat/

        public ActionResult Index()
        {
            return View(db.configsmartthermostats.ToList());
        }

        //
        // GET: /ConfigureThermostat/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartthermostat configsmartthermostat = db.configsmartthermostats.Find(id);
            if (configsmartthermostat == null)
            {
                return HttpNotFound();
            }
            return View(configsmartthermostat);
        }

        //
        // GET: /ConfigureThermostat/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureThermostat/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartthermostat configsmartthermostat)
        {
            if (ModelState.IsValid)
            {
                db.configsmartthermostats.Add(configsmartthermostat);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configsmartthermostat);
        }

        //
        // GET: /ConfigureThermostat/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartthermostat configsmartthermostat = db.configsmartthermostats.Find(id);
            if (configsmartthermostat == null)
            {
                return HttpNotFound();
            }
            return View(configsmartthermostat);
        }

        //
        // POST: /ConfigureThermostat/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartthermostat configsmartthermostat)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configsmartthermostat).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configsmartthermostat);
        }

        //
        // GET: /ConfigureThermostat/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartthermostat configsmartthermostat = db.configsmartthermostats.Find(id);
            if (configsmartthermostat == null)
            {
                return HttpNotFound();
            }
            return View(configsmartthermostat);
        }

        //
        // POST: /ConfigureThermostat/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartthermostat configsmartthermostat = db.configsmartthermostats.Find(id);
            db.configsmartthermostats.Remove(configsmartthermostat);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}