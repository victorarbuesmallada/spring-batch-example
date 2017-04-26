package org.cascomio.springbatchexample;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Import({ SchedulerConfiguration.class, BatchConfiguration.class })
public class BatchRunner implements ApplicationRunner {
    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Override
    public void run(ApplicationArguments args) {
        schedulerFactory.start();
    }

    @Override
    public void finalize() {
        try {
            schedulerFactory.destroy();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
