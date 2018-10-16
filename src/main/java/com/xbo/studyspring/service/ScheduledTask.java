package com.xbo.studyspring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {
    /**
     * 自动扫描，启动时间点之后5秒执行一次
     */
//    @Async("asyncPoolTaskExecutor")
//    @Scheduled(fixedRate=5000)
    public void getCurrentDate() {
        log.info("Scheduled定时任务执行：" + new Date());
    }
}