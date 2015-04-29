using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace antisocial.entities
{
    public class UserData
    {
        [Key]
        public string UserId { get; set; }

        [DefaultValue(0)]
        public int Score { get; set; }

        [DefaultValue(0)]
        public int TotalBlockedTime { get; set; }

        [ForeignKey("UserId")]
        public virtual ApplicationUser User { get; set; }
    }
}
