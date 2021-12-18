using BoiTinhYeu.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Text;

namespace BoiTinhYeu.Data.Configuration
{
    public class HistoryConfiguration : IEntityTypeConfiguration<History>
    {
        public void Configure(EntityTypeBuilder<History> builder)
        {
            builder.ToTable("Histories");
            builder.HasKey(x => x.Id);

            builder.HasOne(x => x.User).WithMany(x => x.Histories).HasForeignKey(x => x.Username);
            builder.HasOne(x => x.Category).WithMany(x => x.Histories).HasForeignKey(x => x.CategoryId);
        }
    }
}
