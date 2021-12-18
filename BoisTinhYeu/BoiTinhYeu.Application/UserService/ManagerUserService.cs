using BoiTinhYeu.Data.EF;
using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Application.UserService
{
    public class ManagerUserService : IManagerUserService
    {
        private readonly BoiTinhYeuDbContext _context;
        public ManagerUserService(BoiTinhYeuDbContext context)
        {
            _context = context;
        }
        public User Create(User user)
        {
            var newUser = new User()
            {
                Username = user.Username,
                Password = user.Password,
                DoB = user.DoB,
                Fullname = user.Fullname,
                Gender = user.Gender
            };
            _context.Users.Add(newUser);
            _context.SaveChanges();
            return newUser;
        }

        public User GetByUsername(string Username)
        {
            var user = _context.Users.Find(Username);
            return user;
        }
    }
}
