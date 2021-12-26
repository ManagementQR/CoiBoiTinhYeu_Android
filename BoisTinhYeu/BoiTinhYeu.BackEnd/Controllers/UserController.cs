using BoiTinhYeu.Application;
using BoiTinhYeu.Application.UserService;
using BoiTinhYeu.Data.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BoiTinhYeu.BackEnd.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IManagerUserService _managerUserService;
        public UserController(IManagerUserService managerUserService)
        {
            _managerUserService = managerUserService;
        }

        [HttpGet]
        public ActionResult getByUsername([FromQuery]string Username)
        {
            var user = _managerUserService.GetByUsername(Username);
            //if(user == null)
            //{
            //    return BadRequest("Cannot find user");
            //}
            return Ok(user);
        }

        [HttpPost]
        public IActionResult create(User user)
        {
            var newUser = _managerUserService.Create(user);
            return Ok(newUser);
        }

        [HttpPatch]
        public IActionResult update([FromBody]User user)
        {
            var newUser = _managerUserService.Update(user);
            return Ok(newUser);
        }

        [HttpPost("image")]
        public IActionResult iamge([FromForm]string username, [FromForm]Image image)
        {
            var user = _managerUserService.addImage(username,image);
            return Ok(user);
        }

    }
}
