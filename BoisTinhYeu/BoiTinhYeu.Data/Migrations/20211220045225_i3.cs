using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace BoiTinhYeu.Data.Migrations
{
    public partial class i3 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: new DateTime(2001, 2, 6, 0, 0, 0, 0, DateTimeKind.Unspecified));
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));
        }
    }
}
