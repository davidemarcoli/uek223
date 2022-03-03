package com.noseryoung.uek223;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Log4j2
//ApplicationListener used to run commands after startup
class AppStartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("App started successfully");
    }
}

