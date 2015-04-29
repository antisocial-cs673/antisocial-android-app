using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace antisocial.entities
{
    public class GroupMember
    {
        [Key]
        [Column(Order = 0)]
        public string UserId { get; set; }

        [Key]
        [Column(Order = 1)]
        public int GroupId { get; set; }

        public bool IsActive { get; set; }

        public DateTime LastLaunchedTime { get; set; }

        public bool IsAccceptedInvitation { get; set; }

        [ForeignKey("UserId")]
        public virtual ApplicationUser User { get; set; }

        [ForeignKey("GroupId")]
        public virtual Group Group { get; set; }

    }
}
