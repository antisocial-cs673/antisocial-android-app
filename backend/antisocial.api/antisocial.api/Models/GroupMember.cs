using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace antisocial.api.Models
{
    public class GroupMember
    {
        [Key]
        [Column(Order=0)]
        public string UserId { get; set; }

        [Key]
        [Column(Order = 1)]
        public int GroupId { get; set; }

        public bool IsActive { get; set; }

        public DateTime LastLaunchedTime { get; set; }

        [ForeignKey("UserId")]
        public virtual ApplicationUser User { get; set; }

        [ForeignKey("GroupId")]
        public virtual Group Group { get; set; }

    }
}