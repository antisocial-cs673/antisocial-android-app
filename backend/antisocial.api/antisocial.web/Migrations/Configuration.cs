namespace antisocial.web.Migrations
{
    using antisocial.entities;
    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<antisocial.web.Models.AntisocialDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(antisocial.web.Models.AntisocialDbContext context)
        {
            SeedMembership(context);
        }

        private void SeedMembership(antisocial.web.Models.AntisocialDbContext context)
        {
            #region Creating roles

            if (!context.Roles.Any(r => r.Name == "Administrator"))
            {
                var store = new RoleStore<IdentityRole>(context);
                var manager = new RoleManager<IdentityRole>(store);
                var role = new IdentityRole { Name = "Administrator" };

                manager.Create(role);
            }

            #endregion

            if (!context.Users.Any(u => u.UserName == "admin@bu.edu"))
            {
                var store = new UserStore<ApplicationUser>(context);
                var manager = new UserManager<ApplicationUser>(store);
                var user = new ApplicationUser { UserName = "admin@bu.edu" };

                manager.Create(user, "%$#P@ssw0rd)@(");
                manager.AddToRole(user.Id, "Administrator");

                context.UserData.AddOrUpdate(e => e.UserId, new UserData { UserId = user.Id, TotalBlockedTime = 0, Score = 0 });
            }

        }
    }
}
