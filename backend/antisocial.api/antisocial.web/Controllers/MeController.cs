using antisocial.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Microsoft.AspNet.Identity;

namespace antisocial.web.Controllers
{
    [Authorize]
    public class MeController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Me
        public ActionResult Index()
        {
            var userId = User.Identity.GetUserId();
            var userData = db.UserData.FirstOrDefault(e => e.User.UserName == User.Identity.Name);
            if (userData == null)
                return HttpNotFound();
            ViewBag.Rank = db.UserData.Count(e => e.Score > userData.Score) + 1;

            return View(userData);
        }
    }
}