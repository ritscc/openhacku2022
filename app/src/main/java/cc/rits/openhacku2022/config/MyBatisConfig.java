package cc.rits.openhacku2022.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisの設定
 */
@MapperScan("cc.rits.openhacku2022.db.mapper")
@Configuration
public class MyBatisConfig {
}
