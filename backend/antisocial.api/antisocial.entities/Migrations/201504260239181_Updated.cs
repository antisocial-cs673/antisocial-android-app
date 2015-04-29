namespace antisocial.entities.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Updated : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.GroupMembers", "IsAccceptedInvitation", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.GroupMembers", "IsAccceptedInvitation");
        }
    }
}
