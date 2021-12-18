using BoiTinhYeu.Application.HistoryService;
using BoiTinhYeu.Data.Entities;
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
    public class HistoryController : ControllerBase
    {
        private readonly IManagerHistoryService _managerHistoryService;
        public HistoryController(IManagerHistoryService managerHistoryService)
        {
            _managerHistoryService = managerHistoryService;
        }
        [HttpGet("{username}")]
        public IActionResult getByUserName(string username)
        {
            var histories = _managerHistoryService.getByUsername(username);
            return Ok(histories);
        }

        [HttpPost]
        public IActionResult create(History history)
        {
            var newHistory = _managerHistoryService.create(history);
            return Ok(newHistory);
        }
    }
}
