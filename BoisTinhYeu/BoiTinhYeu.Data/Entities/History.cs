using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.Entities
{
    public class History
    {
        public int Id { get; set; }
        public int CategoryId { get; set; }
        public string Username { get; set; }
        public string infor { get; set; }
        public string result { get; set; }

        public User User { get; set; }
        public Category Category { get; set; }
    }
}
