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
    public class ConfigEmergencyAuthoritiesController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigEmergencyAuthorities/

        public ActionResult Index()
        {
            return View(db.configemergencyauthorities.ToList());
        }

        //
        // GET: /ConfigEmergencyAuthorities/Details/5

        public ActionResult Details(int id = 0)
        {
            configemergencyauthority configemergencyauthority = db.configemergencyauthorities.Find(id);
            if (configemergencyauthority == null)
            {
                return HttpNotFound();
            }
            return View(configemergencyauthority);
        }

        //
        // GET: /ConfigEmergencyAuthorities/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigEmergencyAuthorities/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(configemergencyauthority configemergencyauthority)
        {
            if (ModelState.IsValid)
            {
                db.configemergencyauthorities.Add(configemergencyauthority);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(configemergencyauthority);
        }

        //
        // GET: /ConfigEmergencyAuthorities/Edit/5

        public ActionResult Edit(int id = 0)
        {
            configemergencyauthority configemergencyauthority = db.configemergencyauthorities.Find(id);
            if (configemergencyauthority == null)
            {
                return HttpNotFound();
            }
            return View(configemergencyauthority);
        }

        //
        // POST: /ConfigEmergencyAuthorities/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(configemergencyauthority configemergencyauthority)
        {
            if (ModelState.IsValid)
            {
                db.Entry(configemergencyauthority).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(configemergencyauthority);
        }

        //
        // GET: /ConfigEmergencyAuthorities/Delete/5

        public ActionResult Delete(int id = 0)
        {
            configemergencyauthority configemergencyauthority = db.configemergencyauthorities.Find(id);
            if (configemergencyauthority == null)
            {
                return HttpNotFound();
            }
            return View(configemergencyauthority);
        }

        //
        // POST: /ConfigEmergencyAuthorities/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            configemergencyauthority configemergencyauthority = db.configemergencyauthorities.Find(id);
            db.configemergencyauthorities.Remove(configemergencyauthority);
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