﻿using antisocial.backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace antisocial.backend.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            return View();
        }

        public ActionResult About()
        {
            return View();
        }

        public ActionResult Contact()
        {
            //Database
            var model = new ContactModel
            {
                Name = "John Doe",
                Phone = "6666"
            };

            return View(model);
        }
    }
}