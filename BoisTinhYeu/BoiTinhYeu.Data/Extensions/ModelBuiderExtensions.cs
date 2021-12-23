using BoiTinhYeu.Data.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.Extensions
{
    public static class ModelBuiderExtensions
    {
        public static void Seed(this ModelBuilder modelBuider)
        {
            var d = new DateTime(2001, 02, 06);
            modelBuider.Entity<User>().HasData(
                new User()
                {
                    Username = "nhuy",
                    Password = "nhuy",
                    Fullname = "Nguyen Thi Nhu Y",
                    Gender = 0,
                    DoB = d.Date.ToString("d")

                }
            );

            //modelBuider.Entity<Category>().HasData(
            //    new Category()
            //    {
            //        Id = 1,
            //        Name = "Bói tên",
            //    },
            //    new Category()
            //    {
            //        Id = 2,
            //        Name = "Bói ngày sinh",
            //    },
            //    new Category()
            //    {
            //        Id = 3,
            //        Name = "Bói cung hoàng đạo",
            //    }
            //);
        }
    }
}
