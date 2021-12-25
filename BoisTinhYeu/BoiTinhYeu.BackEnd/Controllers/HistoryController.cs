﻿using BoiTinhYeu.Application.HistoryService;
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
        [HttpGet()]
        public IActionResult getByUserName([FromQuery]string username)
        {
            var histories = _managerHistoryService.getByUsername(username);
            return Ok(histories);
        }
        
        [HttpGet("find")]
        public IActionResult find([FromQuery]string username, string keyword)
        {
            var histories = _managerHistoryService.find(username,keyword);
            return Ok(histories);
        }

        [HttpPost]
        public IActionResult create([FromBody]History history)
        {
            var newHistory = _managerHistoryService.create(history);
            return Ok(newHistory);
        }

        [HttpDelete]
        public IActionResult delete([FromQuery]int id)
        {
            _managerHistoryService.delete(id);
            return Ok();
        }
    }
}
