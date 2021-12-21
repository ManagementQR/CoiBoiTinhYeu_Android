using BoiTinhYeu.Data.Configuration;
using BoiTinhYeu.Data.Entities;
using BoiTinhYeu.Data.Extensions;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.EF
{
    public class BoiTinhYeuDbContext : DbContext
    {
        public BoiTinhYeuDbContext(DbContextOptions options) : base(options)
        {

        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new UserConfiguration());
            modelBuilder.ApplyConfiguration(new CategoryConfiguration());
            modelBuilder.ApplyConfiguration(new HistoryConfiguration());
            modelBuilder.Seed();
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DbSet<History> Histories { get; set; }
    }
}
