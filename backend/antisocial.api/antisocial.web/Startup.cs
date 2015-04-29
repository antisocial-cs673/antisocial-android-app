using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(antisocial.web.Startup))]
namespace antisocial.web
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
