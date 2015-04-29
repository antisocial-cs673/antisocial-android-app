using antisocial.api.Areas.Api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using antisocial.api.Models;
using antisocial.entities;
namespace antisocial.api.Areas.Api.Controllers
{
    /// <summary>
    /// Description of Group Api Controller
    /// </summary>
    [RoutePrefix("api/Groups")]
    
    public class GroupController : ApiController
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        /// <summary>
        /// Returns user's group, that he/she is member of
        /// </summary>
        /// <returns></returns>
        [Route("")]
        public IQueryable<GroupListModel> Get()
        {
            //TODO: filter by user id
            return db.Groups.Select(g=>new GroupListModel{Id = g.Id, Name = g.Name});
        }

        /// <summary>
        /// Returns specified group's details
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [Route("{id}")]
        public GroupViewModel Get(int id)
        {
            
            return new GroupViewModel { Id = id, Name = "Group Name 1", BlockTime = 2, AppLists = "com.facebook.katana" };
        }
    }
}
