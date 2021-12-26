using BoiTinhYeu.Data.EF;
using BoiTinhYeu.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using BoiTinhYeu.Application.Common;
using Microsoft.AspNetCore.Http;
using System.Net.Http.Headers;
using System.IO;

namespace BoiTinhYeu.Application.UserService
{
    public class ManagerUserService : IManagerUserService
    {
        private readonly BoiTinhYeuDbContext _context;
        private readonly IStorageService _storageService;
        public ManagerUserService(BoiTinhYeuDbContext context, IStorageService storageService)
        {
            _context = context;
            _storageService = storageService;
        }

        public User addImage(string username, Image img)
        {
            var user = _context.Users.Where(x => x.Username == username).FirstOrDefault();
            if(img.image != null)
            {
                user.ava = this.SaveFile(img.image);
            }
            _context.Users.Update(user);
            _context.SaveChanges();
            return user;
        }
        private string SaveFile(IFormFile file)
        {
            var originalFileName = ContentDispositionHeaderValue.Parse(file.ContentDisposition).FileName.Trim('"');
            var fileName = $"{Guid.NewGuid()}{Path.GetExtension(originalFileName)}";
            _storageService.SaveFileAsync(file.OpenReadStream(), fileName);
            return fileName;
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
