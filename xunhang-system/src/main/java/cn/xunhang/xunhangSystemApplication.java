package cn.xunhang;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@SpringBootApplication
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class xunhangSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(xunhangSystemApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ xunhang启动成功 ヾ(◍°∇°◍)ﾉﾞ\n"
			 	+"  ┏┓　　　┏┓\n"
				+"┏┛┻━━━┛┻┓\n"
				+"┃　　　　　　　┃\n"
				+"┃　　　━　　　┃\n"
				+"┃　┳┛　┗┳　┃\n"
				+"┃　　　　　　　┃\n"
				+"┃　　　┻　　　┃\n"
				+"┃　　　　　　　┃\n"
				+"┗━┓　　　┏━┛\n"
				+"　　┃　　　┃神兽保佑\n"
				+"　　┃　　　┃代码无BUG！\n"
				+"　　┃　　　┗━━━┓\n"
				+"　　┃　　　　　　　┣┓\n"
				+"　　┃　　　　　　　┏┛\n"
				+"　　┗┓┓┏━┳┓┏┛\n"
				+"　　　┃┫┫　┃┫┫\n"
				+"　　　┗┻┛　┗┻┛\n");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(xunhangSystemApplication.class);
	}
}
