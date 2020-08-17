package indi.zhangweisep;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用程序核心启动类
 *
 * @author ZhangWei
 * @date 2020/08/17
 * @since 2020/8/14 13:25
 */
@Slf4j
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = "indi.zhangweisep.modules.**.mapper")
public class ManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemApplication.class, args);
    }

}
