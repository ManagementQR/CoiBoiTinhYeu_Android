using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace BoiTinhYeu.Data.Migrations
{
    public partial class i6 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<string>(
                name: "DoB",
                table: "Users",
                nullable: true,
                oldClrType: typeof(DateTime),
                oldType: "datetime2");

            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: "2001-02-06");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<DateTime>(
                name: "DoB",
                table: "Users",
                type: "datetime2",
                nullable: false,
                oldClrType: typeof(string),
                oldNullable: true);

            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: new DateTime(2001, 2, 6, 0, 0, 0, 0, DateTimeKind.Unspecified));
        }
    }
}
