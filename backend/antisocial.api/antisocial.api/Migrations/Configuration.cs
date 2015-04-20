namespace antisocial.api.Migrations
{
    using antisocial.api.Models;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<antisocial.api.Models.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(antisocial.api.Models.ApplicationDbContext context)
        {
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //
            context.MobileApps.AddOrUpdate(
                p => p.Name,
                new MobileApp { Name = "Facebook", PackageName = "com.facebook.katana" },
                new MobileApp { Name = "Twitter", PackageName = "com.twitter.android" },
                new MobileApp { Name = "Viber", PackageName = "com.viber.voip" },
                new MobileApp { Name = "Whatsapp", PackageName = "com.whatsapp" },
                new MobileApp { Name = "Instagram", PackageName = "com.instagram.android" }
                );
        }
    }
}
