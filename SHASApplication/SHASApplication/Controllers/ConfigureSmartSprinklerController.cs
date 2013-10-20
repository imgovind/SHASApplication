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
    public class ConfigureSmartSprinklerController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureSmartSprinkler/

        public ActionResult Index()
        {
            return View(db.configsmartsprinklers.ToList());
        }

        //
        // GET: /ConfigureSmartSprinkler/Details/5

        public ActionResult Details(int id = 0)
        {
            configsmartsprinkler configsmartsprinkler = db.configsmartsprinklers.Find(id);
            if (configsmartsprinkler == null)
            {
                return HttpNotFound();
            }
            return View(configsmartsprinkler);
        }

        //
        // GET: /ConfigureSmartSprinkler/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureSmartSprinkler/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configsmartsprinkler configsmartsprinkler)
        {
            if (ModelState.IsValid)
            {
                db.configsmartsprinklers.Add(configsmartsprinkler);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configsmartsprinkler);
        }

        //
        // GET: /ConfigureSmartSprinkler/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configsmartsprinkler configsmartsprinkler = db.configsmartsprinklers.Find(id);
            if (configsmartsprinkler == null)
            {
                return HttpNotFound();
            }
            return View(configsmartsprinkler);
        }

        //
        // POST: /ConfigureSmartSprinkler/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configsmartsprinkler configsmartsprinkler)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configsmartsprinkler).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configsmartsprinkler);
        }

        //
        // GET: /ConfigureSmartSprinkler/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configsmartsprinkler configsmartsprinkler = db.configsmartsprinklers.Find(id);
            if (configsmartsprinkler == null)
            {
                return HttpNotFound();
            }
            return View(configsmartsprinkler);
        }

        //
        // POST: /ConfigureSmartSprinkler/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configsmartsprinkler configsmartsprinkler = db.configsmartsprinklers.Find(id);
            db.configsmartsprinklers.Remove(configsmartsprinkler);
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