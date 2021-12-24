using Microsoft.EntityFrameworkCore.Migrations;

namespace BoiTinhYeu.Data.Migrations
{
    public partial class i2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Histories_Categories_CategoryId",
                table: "Histories");

            migrationBuilder.DropForeignKey(
                name: "FK_Histories_Users_Username1",
                table: "Histories");

            migrationBuilder.DropIndex(
                name: "IX_Histories_CategoryId",
                table: "Histories");

            migrationBuilder.DropIndex(
                name: "IX_Histories_Username1",
                table: "Histories");

            migrationBuilder.DeleteData(
                table: "Categories",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Categories",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Categories",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DropColumn(
                name: "CategoryId",
                table: "Histories");

            migrationBuilder.DropColumn(
                name: "Username1",
                table: "Histories");

            migrationBuilder.AlterColumn<string>(
                name: "Username",
                table: "Histories",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "nvarchar(max)",
                oldNullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Histories_Username",
                table: "Histories",
                column: "Username");

            migrationBuilder.AddForeignKey(
                name: "FK_Histories_Users_Username",
                table: "Histories",
                column: "Username",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Histories_Users_Username",
                table: "Histories");

            migrationBuilder.DropIndex(
                name: "IX_Histories_Username",
                table: "Histories");

            migrationBuilder.AlterColumn<string>(
                name: "Username",
                table: "Histories",
                type: "nvarchar(max)",
                nullable: true,
                oldClrType: typeof(string),
                oldNullable: true);

            migrationBuilder.AddColumn<int>(
                name: "CategoryId",
                table: "Histories",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "Username1",
                table: "Histories",
                type: "nvarchar(450)",
                nullable: true);

            migrationBuilder.InsertData(
                table: "Categories",
                columns: new[] { "Id", "Name" },
                values: new object[] { 1, "Bói tên" });

            migrationBuilder.InsertData(
                table: "Categories",
                columns: new[] { "Id", "Name" },
                values: new object[] { 2, "Bói ngày sinh" });

            migrationBuilder.InsertData(
                table: "Categories",
                columns: new[] { "Id", "Name" },
                values: new object[] { 3, "Bói cung hoàng đạo" });

            migrationBuilder.CreateIndex(
                name: "IX_Histories_CategoryId",
                table: "Histories",
                column: "CategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Histories_Username1",
                table: "Histories",
                column: "Username1");

            migrationBuilder.AddForeignKey(
                name: "FK_Histories_Categories_CategoryId",
                table: "Histories",
                column: "CategoryId",
                principalTable: "Categories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Histories_Users_Username1",
                table: "Histories",
                column: "Username1",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
