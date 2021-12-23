using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Application.UserService
{
    public interface IManagerUserService
    {
        User GetByUsername(string Username);
        User Create(User user);

        User Update(User user);
    }
}
