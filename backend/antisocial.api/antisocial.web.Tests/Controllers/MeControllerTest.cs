using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using antisocial.web.Controllers;
using System.Web.Mvc;
using Moq;
namespace antisocial.web.Tests.Controllers
{
    [TestClass]
    public class MeControllerTest
    {
        [TestMethod]
        public void Me_Index_ViewResult()
        {
            MeController controller = new MeController();

            var moq = new Mock<ControllerContext>();
            moq.SetupGet(e => e.HttpContext.User.Identity.Name).Returns("test@bu.edu");
            moq.SetupGet(x => x.HttpContext.Request.IsAuthenticated).Returns(true);

            controller.ControllerContext = moq.Object;

            ViewResult result = controller.Index() as ViewResult;

            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void Me_Index_Rank_Bigger_Than_Zero()
        {
            MeController controller = new MeController();

            var moq = new Mock<ControllerContext>();
            moq.SetupGet(e => e.HttpContext.User.Identity.Name).Returns("test@bu.edu");
            moq.SetupGet(x => x.HttpContext.Request.IsAuthenticated).Returns(true);

            controller.ControllerContext = moq.Object;

            ViewResult result = controller.Index() as ViewResult;
            int? rank = result.ViewBag.Rank as int?;

            Assert.IsTrue(rank > 0);
        }


    }
}
