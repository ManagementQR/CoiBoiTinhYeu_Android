using Microsoft.EntityFrameworkCore.Migrations;

namespace BoiTinhYeu.Data.Migrations
{
    public partial class i1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: "2/6/2001");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "Username",
                keyValue: "nhuy",
                column: "DoB",
                value: "2001-02-06");
        }
    }
}
