using BoiTinhYeu.Data.EF;
using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

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

        public User GetByUsername(string username)
        {
            var user = _context.Users.Where(x => x.Username == username).FirstOrDefault();
            return user;
        }

        public User Update(User user)
        {
            var olduser = _context.Users.Find(user.Username);
            olduser.Fullname = user.Fullname;
            olduser.Gender = user.Gender;
            olduser.DoB = user.DoB;
            olduser.Password = user.Password;
            _context.Users.Update(olduser);
            _context.SaveChanges();
            return olduser;
        }
    }
}
