using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.Entities
{
    public class User
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public string Fullname { get; set; }
        public DateTime DoB { get; set; }
        public int Gender { get; set; }
        public List<History> Histories { get; set; }
    }
}
