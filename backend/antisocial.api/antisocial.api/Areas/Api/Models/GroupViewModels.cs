using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace antisocial.api.Areas.Api.Models
{
    public class GroupViewModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string AppLists { get; set; }
        public int BlockTime { get; set; }
    }

    public class GroupListModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }
}