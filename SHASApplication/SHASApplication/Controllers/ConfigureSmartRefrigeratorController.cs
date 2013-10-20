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
    public class ConfigureSmartRefrigeratorController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureSmartRefrigerator/

        public ActionResult Index()
        {
            return View(db.configsmartrefrigerators.ToList());
        }

        //
        // GET: /ConfigureSmartRefrigerator/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartrefrigerator configsmartrefrigerator = db.configsmartrefrigerators.Find(id);
            if (configsmartrefrigerator == null)
            {
                return HttpNotFound();
            }
            return View(configsmartrefrigerator);
        }

        //
        // GET: /ConfigureSmartRefrigerator/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureSmartRefrigerator/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartrefrigerator configsmartrefrigerator)
        {
            if (ModelState.IsValid)
            {
                db.configsmartrefrigerators.Add(configsmartrefrigerator);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configsmartrefrigerator);
        }

        //
        // GET: /ConfigureSmartRefrigerator/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartrefrigerator configsmartrefrigerator = db.configsmartrefrigerators.Find(id);
            if (configsmartrefrigerator == null)
            {
                return HttpNotFound();
            }
            return View(configsmartrefrigerator);
        }

        //
        // POST: /ConfigureSmartRefrigerator/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartrefrigerator configsmartrefrigerator)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configsmartrefrigerator).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configsmartrefrigerator);
        }

        //
        // GET: /ConfigureSmartRefrigerator/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartrefrigerator configsmartrefrigerator = db.configsmartrefrigerators.Find(id);
            if (configsmartrefrigerator == null)
            {
                return HttpNotFound();
            }
            return View(configsmartrefrigerator);
        }

        //
        // POST: /ConfigureSmartRefrigerator/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartrefrigerator configsmartrefrigerator = db.configsmartrefrigerators.Find(id);
            db.configsmartrefrigerators.Remove(configsmartrefrigerator);
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