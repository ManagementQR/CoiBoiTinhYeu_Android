using Microsoft.EntityFrameworkCore.Migrations;

namespace BoiTinhYeu.Data.Migrations
{
    public partial class abv : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
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
                nullable: true,
                oldClrType: typeof(string),
                oldType: "nvarchar(450)",
                oldNullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Username1",
                table: "Histories",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Histories_Username1",
                table: "Histories",
                column: "Username1");

            migrationBuilder.AddForeignKey(
                name: "FK_Histories_Users_Username1",
                table: "Histories",
                column: "Username1",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Histories_Users_Username1",
                table: "Histories");

            migrationBuilder.DropIndex(
                name: "IX_Histories_Username1",
                table: "Histories");

            migrationBuilder.DropColumn(
                name: "Username1",
                table: "Histories");

            migrationBuilder.AlterColumn<string>(
                name: "Username",
                table: "Histories",
                type: "nvarchar(450)",
                nullable: true,
                oldClrType: typeof(string),
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
    }
}
