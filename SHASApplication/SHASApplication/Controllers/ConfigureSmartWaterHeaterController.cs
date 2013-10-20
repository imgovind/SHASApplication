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
    public class ConfigureSmartWaterHeaterController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureSmartWaterHeater/

        public ActionResult Index()
        {
            return View(db.configsmartwaterheaters.ToList());
        }

        //
        // GET: /ConfigureSmartWaterHeater/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartwaterheater configsmartwaterheater = db.configsmartwaterheaters.Find(id);
            if (configsmartwaterheater == null)
            {
                return HttpNotFound();
            }
            return View(configsmartwaterheater);
        }

        //
        // GET: /ConfigureSmartWaterHeater/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureSmartWaterHeater/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartwaterheater configsmartwaterheater)
        {
            if (ModelState.IsValid)
            {
                db.configsmartwaterheaters.Add(configsmartwaterheater);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configsmartwaterheater);
        }

        //
        // GET: /ConfigureSmartWaterHeater/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartwaterheater configsmartwaterheater = db.configsmartwaterheaters.Find(id);
            if (configsmartwaterheater == null)
            {
                return HttpNotFound();
            }
            return View(configsmartwaterheater);
        }

        //
        // POST: /ConfigureSmartWaterHeater/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartwaterheater configsmartwaterheater)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configsmartwaterheater).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configsmartwaterheater);
        }

        //
        // GET: /ConfigureSmartWaterHeater/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartwaterheater configsmartwaterheater = db.configsmartwaterheaters.Find(id);
            if (configsmartwaterheater == null)
            {
                return HttpNotFound();
            }
            return View(configsmartwaterheater);
        }

        //
        // POST: /ConfigureSmartWaterHeater/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartwaterheater configsmartwaterheater = db.configsmartwaterheaters.Find(id);
            db.configsmartwaterheaters.Remove(configsmartwaterheater);
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