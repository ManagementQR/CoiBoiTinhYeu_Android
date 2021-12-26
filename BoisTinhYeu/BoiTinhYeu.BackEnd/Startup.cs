using BoiTinhYeu.Application.CatrgoryService;
using BoiTinhYeu.Application.Common;
using BoiTinhYeu.Application.HistoryService;
using BoiTinhYeu.Application.UserService;
using BoiTinhYeu.Data.EF;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BoiTinhYeu.BackEnd
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            //cấu hình kết nối với db
            services.AddDbContext<BoiTinhYeuDbContext>(options =>
                options.UseSqlServer(Configuration.GetConnectionString("BoiTinhYeuDatabase")));

            //declare DI
            services.AddTransient<IManagerUserService, ManagerUserService>(); //mỗi lần request sẽ tạo mới
            services.AddTransient<IManagerCatogeryService, ManagerCatogeryService>(); //mỗi lần request sẽ tạo mới
            services.AddTransient<IManagerHistoryService, ManagerHistoryService>();
            services.AddTransient<IStorageService, StorageService>(); //mỗi lần request sẽ tạo mới

            services.AddControllers();

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "swagger eShopSolution", Version = "v1" });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }
            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            //use swagger
            app.UseSwagger();
            //cấu hình swagger ui
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "Swagger EShopSolution version1");
            });

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
