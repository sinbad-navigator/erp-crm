package com.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 主程序
 *
 * @author ec
 */
@SpringBootApplication(scanBasePackages = "com", exclude = {DataSourceAutoConfiguration.class})
public class ErpCrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpCrmApplication.class, args);
        System.out.println("ErpCrm启动成功=======");
    }
}
