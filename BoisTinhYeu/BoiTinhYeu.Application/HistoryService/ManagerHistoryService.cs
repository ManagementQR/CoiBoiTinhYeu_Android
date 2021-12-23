using BoiTinhYeu.Data.EF;
using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace BoiTinhYeu.Application.HistoryService
{
    public class ManagerHistoryService : IManagerHistoryService
    {
        private readonly BoiTinhYeuDbContext _context;
        public ManagerHistoryService(BoiTinhYeuDbContext context)
        {
            _context = context;
        }
        public History create(History history)
        {
            var newHistory = new History() {
                Username = history.Username,
                Fullname = history.Fullname,
                infor = history.infor,
                result = history.result
            };
            _context.Histories.Add(newHistory);
            _context.SaveChanges();
            return newHistory;
        }

        public List<History> getByUsername(string username)
        {
            return _context.Histories.Where(x=>x.Username==username).ToList();
        }
    }
}
