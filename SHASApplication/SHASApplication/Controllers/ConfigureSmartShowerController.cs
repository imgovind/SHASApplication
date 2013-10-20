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
    public class ConfigureSmartShowerController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureSmartShower/

        public ActionResult Index()
        {
            return View(db.configsmartshowers.ToList());
        }

        //
        // GET: /ConfigureSmartShower/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartshower configsmartshower = db.configsmartshowers.Find(id);
            if (configsmartshower == null)
            {
                return HttpNotFound();
            }
            return View(configsmartshower);
        }

        //
        // GET: /ConfigureSmartShower/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureSmartShower/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartshower configsmartshower)
        {
            if (ModelState.IsValid)
            {
                db.configsmartshowers.Add(configsmartshower);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configsmartshower);
        }

        //
        // GET: /ConfigureSmartShower/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartshower configsmartshower = db.configsmartshowers.Find(id);
            if (configsmartshower == null)
            {
                return HttpNotFound();
            }
            return View(configsmartshower);
        }

        //
        // POST: /ConfigureSmartShower/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartshower configsmartshower)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configsmartshower).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configsmartshower);
        }

        //
        // GET: /ConfigureSmartShower/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartshower configsmartshower = db.configsmartshowers.Find(id);
            if (configsmartshower == null)
            {
                return HttpNotFound();
            }
            return View(configsmartshower);
        }

        //
        // POST: /ConfigureSmartShower/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartshower configsmartshower = db.configsmartshowers.Find(id);
            db.configsmartshowers.Remove(configsmartshower);
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