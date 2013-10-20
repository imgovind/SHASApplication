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
    public class ConfigureAlertController : Controller
    {
        private shasEntities db = new shasEntities();

        //
        // GET: /ConfigureAlert/

        public ActionResult Index()
        {
            return View(db.users.ToList());
        }

        //
        // GET: /ConfigureAlert/Details/5

        public ActionResult Details(int id = 0)
        {
            user user = db.users.Find(id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);
        }

        //
        // GET: /ConfigureAlert/Create

        public ActionResult Create()
        {
            return View();
        }

        //
        // POST: /ConfigureAlert/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(user user)
        {
            if (ModelState.IsValid)
            {
                db.users.Add(user);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(user);
        }

        //
        // GET: /ConfigureAlert/Edit/5

        public ActionResult Edit(int id = 0)
        {
            user user = db.users.Find(id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);
        }

        //
        // POST: /ConfigureAlert/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(user user)
        {
            var userRecord = db.users.Find(user.ID);
            string ps = userRecord.Password;
            string rm = userRecord.RememberMe;
            string sq = userRecord.SecurityQuestion;
            string sa = userRecord.SecurityAnswer;
            if (ModelState.IsValid)
            {
                shasEntities db1 = new shasEntities();
                bool updateComplete = default(bool);
                user.Password = ps;
                user.RememberMe = rm;
                user.SecurityAnswer = sa;
                user.SecurityQuestion = sq;
                updateComplete = true;
                if (updateComplete == true) {
                    db1.Entry(user).State = EntityState.Modified;
                    db1.SaveChanges();
                }
                return RedirectToAction("Index");
            }
            return View(user);
        }

        //
        // GET: /ConfigureAlert/Delete/5

        public ActionResult Delete(int id = 0)
        {
            user user = db.users.Find(id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);
        }

        //
        // POST: /ConfigureAlert/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            user user = db.users.Find(id);
            db.users.Remove(user);
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