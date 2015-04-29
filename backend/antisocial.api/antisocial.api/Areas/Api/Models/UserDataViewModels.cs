using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;


namespace antisocial.api.Areas.Api.Models
{
    public class UserDataViewModel
    {
        public int Score { get; set; }
        public int TotalBlockedTime { get; set; }
        public int Rank { get; set; }

    }

    public class UserDataPostModel
    {
        [Required]
        public int Minutes { get; set; }
        [Required]
        public int AppCount { get; set; }
    }
}