using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Application.CatrgoryService
{
    public interface IManagerCatogeryService
    {
        List<Category> GetCategories();
    }
}
