package com.zyro;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import com.zyro.utils.DriverFactory;

@SpringBootConfiguration
@ComponentScan("com.zyro")
@EnableAutoConfiguration
public class SpringConfig {

    @Scope("singleton")
    @Bean(destroyMethod = "quit")
    WebDriver webDriver(List<DriverFactory> driverFactories, @Value("${webDriver.mode}") String mode) {
        return driverFactories.stream().filter(df -> df.name()
                .equalsIgnoreCase(mode))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unknown WebDriver mode: " + mode))
                .newInstance();
    }

    @Bean
    WebDriverWait webDriverWait(
            WebDriver webDriver,
            @Value("${wait.explicit.seconds}") Long waitExplicitSec,
            @Value("${wait.implicit.milliseconds}") Long waitImplicitMilliSec
    ) {
        return new WebDriverWait(webDriver, waitExplicitSec, waitImplicitMilliSec);
    }
}
