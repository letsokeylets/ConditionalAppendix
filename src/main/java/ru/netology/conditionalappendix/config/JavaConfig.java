package ru.netology.conditionalappendix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.conditionalappendix.profile.DevProfile;
import ru.netology.conditionalappendix.profile.ProductionProfile;
import ru.netology.conditionalappendix.profile.SystemProfile;

@Configuration
@ConfigurationProperties("netology.profile")
@Data
public class JavaConfig {

    private boolean dev;

    /**
     * Получаем нужную реализацию в зависимости от netology.profile.dev
     * true - DevProfile
     * false - ProductionProfile
     * @return
     */
    @Bean
    public SystemProfile getProfile() {
        return dev ? new DevProfile() : new ProductionProfile();
    }


}
