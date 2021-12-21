using BoiTinhYeu.Application.CatrgoryService;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BoiTinhYeu.BackEnd.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoryController : ControllerBase
    {
        private readonly IManagerCatogeryService _managerCatogeryService;
        public CategoryController(IManagerCatogeryService managerCatogeryService)
        {
            _managerCatogeryService = managerCatogeryService;
        }
        [HttpGet]
        public IActionResult getAll()
        {
            var categories = _managerCatogeryService.GetCategories();
            return Ok(categories);
        }
    }
}
