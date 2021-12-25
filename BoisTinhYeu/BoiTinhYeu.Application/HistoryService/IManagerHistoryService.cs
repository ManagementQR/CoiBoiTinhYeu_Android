using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Application.HistoryService
{
    public interface IManagerHistoryService
    {
        History create(History history);
        List<History> getByUsername(string username);

        List<History> find(string username, string keyword);

        void delete(int id);
    }
}
