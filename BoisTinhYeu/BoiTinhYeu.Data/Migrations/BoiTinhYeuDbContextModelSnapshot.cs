﻿// <auto-generated />
using BoiTinhYeu.Data.EF;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace BoiTinhYeu.Data.Migrations
{
    [DbContext(typeof(BoiTinhYeuDbContext))]
    partial class BoiTinhYeuDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.1.22")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("BoiTinhYeu.Data.Entities.Category", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.ToTable("Categories");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "Bói tên"
                        },
                        new
                        {
                            Id = 2,
                            Name = "Bói ngày sinh"
                        },
                        new
                        {
                            Id = 3,
                            Name = "Bói cung hoàng đạo"
                        });
                });

            modelBuilder.Entity("BoiTinhYeu.Data.Entities.History", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<int>("CategoryId")
                        .HasColumnType("int");

                    b.Property<string>("Username")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Username1")
                        .HasColumnType("nvarchar(450)");

                    b.Property<string>("infor")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("result")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.HasIndex("CategoryId");

                    b.HasIndex("Username1");

                    b.ToTable("Histories");
                });

            modelBuilder.Entity("BoiTinhYeu.Data.Entities.User", b =>
                {
                    b.Property<string>("Username")
                        .HasColumnType("nvarchar(450)");

                    b.Property<string>("DoB")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Fullname")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("Gender")
                        .HasColumnType("int");

                    b.Property<string>("Password")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Username");

                    b.ToTable("Users");

                    b.HasData(
                        new
                        {
                            Username = "nhuy",
                            DoB = "2001-02-06",
                            Fullname = "Nguyen Thi Nhu Y",
                            Gender = 0,
                            Password = "nhuy"
                        });
                });

            modelBuilder.Entity("BoiTinhYeu.Data.Entities.History", b =>
                {
                    b.HasOne("BoiTinhYeu.Data.Entities.Category", "Category")
                        .WithMany()
                        .HasForeignKey("CategoryId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("BoiTinhYeu.Data.Entities.User", "User")
                        .WithMany()
                        .HasForeignKey("Username1");
                });
#pragma warning restore 612, 618
        }
    }
}
