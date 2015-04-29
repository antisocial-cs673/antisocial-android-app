using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace antisocial.api.Models
{
    public class Group
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public string OwnerId { get; set; }

        public string AppLists { get; set; }

        //minutes
        [Required]
        [Range(0, 24*60)]
        public int BlockTime { get; set; }

        public virtual ICollection<GroupMember> Members { get; set; } 

        [ForeignKey("OwnerId")]
        public virtual ApplicationUser Owner { get; set; }
    }
}