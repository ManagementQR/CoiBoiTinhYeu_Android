using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.Entities
{
    public class Category
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<History> Histories { get; set; }
    }
}
