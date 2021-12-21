using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace BoiTinhYeu.Data.EF
{
    public class BoiTinhYeuDbContextFactory : IDesignTimeDbContextFactory<BoiTinhYeuDbContext>
    {
        public BoiTinhYeuDbContext CreateDbContext(string[] args)
        {
            IConfigurationRoot configuration = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsetting.json")
                .Build();

            var connectionString = configuration.GetConnectionString("BoiTinhYeuDatabase");

            var optionsBuilder = new DbContextOptionsBuilder<BoiTinhYeuDbContext>();
            optionsBuilder.UseSqlServer(connectionString);

            return new BoiTinhYeuDbContext(optionsBuilder.Options);
        }
    }
}
