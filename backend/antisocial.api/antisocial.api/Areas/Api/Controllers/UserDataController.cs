using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using antisocial.api.Areas.Api.Models;
using antisocial.api.Models;
using Microsoft.AspNet.Identity;
using System.Web.Http.Description;
using antisocial.entities;

namespace antisocial.api.Areas.Api.Controllers
{
#if DEBUG
#else
    [Authorize]
#endif
    [RoutePrefix("v1/userdata")]
    public class UserDataController : ApiController
    {
        private ApplicationDbContext db = new ApplicationDbContext();


        [ResponseType(typeof(UserDataViewModel))]
        [HttpPost]
        [Route("")]
        public IHttpActionResult Post(UserDataPostModel data)
        {

            //get user id
            var userData = db.UserData.Find(RequestContext.Principal.Identity.GetUserId());

            if(userData == null)
            {
                return NotFound();
            }

            userData.Score += data.Minutes * data.AppCount;
            userData.TotalBlockedTime += data.Minutes;
            db.SaveChanges();

            int rank = db.UserData.Count(e => e.Score > userData.Score) + 1;

            return Ok(new UserDataViewModel { Score = userData.Score, TotalBlockedTime = userData.TotalBlockedTime, Rank = rank });
        }
    }
}
