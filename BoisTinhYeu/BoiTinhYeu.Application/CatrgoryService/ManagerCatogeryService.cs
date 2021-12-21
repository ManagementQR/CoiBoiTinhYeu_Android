using BoiTinhYeu.Data.EF;
using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace BoiTinhYeu.Application.CatrgoryService
{
    public class ManagerCatogeryService : IManagerCatogeryService
    {
        private readonly BoiTinhYeuDbContext _context;
        public ManagerCatogeryService(BoiTinhYeuDbContext context)
        {
            _context = context;
        }
        public List<Category> GetCategories()
        {
            return _context.Categories.ToList();
        }
    }
}
