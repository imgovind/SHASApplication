using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SHASApplication.Models;
using System.Text;
using System.Globalization;

namespace SHASApplication.Controllers
{
    public class ConfigureSmartPlugController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureSmartPlug/

        public ActionResult Index()
        {
            var configsmartplugs = db.configsmartplugs.Include(c => c.configdevicestatu).Include(c => c.device);
            return View(configsmartplugs.ToList());
        }

        //
        // GET: /ConfigureSmartPlug/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartplug configsmartplug = db.configsmartplugs.Find(id);
            if (configsmartplug == null)
            {
                return HttpNotFound();
            }
            return View(configsmartplug);
        }

        //
        // GET: /ConfigureSmartPlug/Create

        public ActionResult Create()
        {
            ViewBag.StatusID = new SelectList(db.configdevicestatus, "ID", "Status");
            ViewBag.DeviceID = new SelectList(db.devices, "ID", "Name");
            return View();
        }

        //
        // POST: /ConfigureSmartPlug/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartplug configsmartplug)
        {
            if (ModelState.IsValid)
            {
                db.configsmartplugs.Add(configsmartplug);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.StatusID = new SelectList(db.configdevicestatus, "ID", "Status", configsmartplug.StatusID);
            ViewBag.DeviceID = new SelectList(db.devices, "ID", "Name", configsmartplug.DeviceID);
            return View(configsmartplug);
        }

        //
        // GET: /ConfigureSmartPlug/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartplug configsmartplug = db.configsmartplugs.Find(id);
            if (configsmartplug == null)
            {
                return HttpNotFound();
            }
            ViewBag.StatusID = new SelectList(db.configdevicestatus, "ID", "Status", configsmartplug.StatusID);
            ViewBag.DeviceID = new SelectList(db.devices, "ID", "Name", configsmartplug.DeviceID);
            return View(configsmartplug);
        }

        //
        // POST: /ConfigureSmartPlug/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartplug configsmartplug)
        {
            device currentDev = new device();
            shasEntities db1 = new shasEntities();

            if (ModelState.IsValid)
            {
                bool fetchedDeviceRecord = false;
                var CurrentDeviceID = configsmartplug.DeviceID;
                currentDev = db1.devices.Find(CurrentDeviceID);
                fetchedDeviceRecord = true;
                db.Entry(configsmartplug).State = EntityState.Modified;
                db.SaveChanges();
                if (fetchedDeviceRecord == true) {
                    if (configsmartplug.StatusID == 2) 
                    {
                        currentDev.DeviceStatus = "OFF";
                    }
                    else if (configsmartplug.StatusID == 1) 
                    {
                        currentDev.DeviceStatus = "ON";
                    }
                    db1.Entry(currentDev).State = EntityState.Modified;
                    db1.SaveChanges();
                }
                return RedirectToAction("Index");
            }
            ViewBag.StatusID = new SelectList(db.configdevicestatus, "ID", "Status", configsmartplug.StatusID);
            ViewBag.DeviceID = new SelectList(db.devices, "ID", "Name", configsmartplug.DeviceID);
            return View(configsmartplug);
        }

        //
        // GET: /ConfigureSmartPlug/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartplug configsmartplug = db.configsmartplugs.Find(id);
            if (configsmartplug == null)
            {
                return HttpNotFound();
            }
            return View(configsmartplug);
        }

        //
        // POST: /ConfigureSmartPlug/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartplug configsmartplug = db.configsmartplugs.Find(id);
            db.configsmartplugs.Remove(configsmartplug);
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