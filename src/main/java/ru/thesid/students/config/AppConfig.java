package ru.thesid.students.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.thesid.students.AppRunner;

@Configuration
public class AppConfig {

    @Bean
    public AppRunner appRunner() {
        return new AppRunner();
    }
}
