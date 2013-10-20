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
    public class DeviceController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /Device/

        public ActionResult Index()
        {
            return View(db.devices.ToList());
        }

        //
        // GET: /Device/Details/5

        public ActionResult Details(int id = 0)
        {
            device device = db.devices.Find(id);
            if (device == null)
            {
                return HttpNotFound();
            }
            return View(device);
        }

        //
        // GET: /Device/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /Device/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(device device)
        {
            if (ModelState.IsValid)
            {
                db.devices.Add(device);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(device);
        }

        //
        // GET: /Device/Edit/5

        public ActionResult Edit(int id = 0)
        {
            device device = db.devices.Find(id);
            if (device == null)
            {
                return HttpNotFound();
            }
            return View(device);
        }

        //
        // POST: /Device/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(device device)
        {
            shasEntities db1 = new shasEntities();
            device dv1 = new device();
            dv1 = db1.devices.Find(device.ID);
            if (ModelState.IsValid)
            {
                device.TimeStamp = dv1.TimeStamp;
                db.Entry(device).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(device);
        }

        //
        // GET: /Device/Delete/5

        public ActionResult Delete(int id = 0)
        {
            device device = db.devices.Find(id);
            if (device == null)
            {
                return HttpNotFound();
            }
            return View(device);
        }

        //
        // POST: /Device/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            device device = db.devices.Find(id);
            db.devices.Remove(device);
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