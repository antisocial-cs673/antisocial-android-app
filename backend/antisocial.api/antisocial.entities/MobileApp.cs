using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace antisocial.entities
{
    public enum MobilePlatform
    {
        Android,
        iOS,
        Windows
    }
    public class MobileApp
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string PackageName { get; set; }
        public string LogoUrl { get; set; }
    }
}
