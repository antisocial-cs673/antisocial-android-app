using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace antisocial.entities
{
    public class Group
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public string OwnerId { get; set; }

        public string AppLists { get; set; }
        public string PhoneNumber { get; set; }
        public string MobileCarier { get; set; }
        //minutes
        [Required]
        [Range(0, 24 * 60)]
        public int BlockTime { get; set; }

        public virtual ICollection<GroupMember> Members { get; set; }

        [ForeignKey("OwnerId")]
        public virtual ApplicationUser Owner { get; set; }
    }
}
