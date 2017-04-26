package org.cascomio.springbatchexample;

import org.cascomio.springbatchexample.jobs.PropertiesBatchJob;
import org.quartz.JobDataMap;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableBatchProcessing
public class SchedulerConfiguration {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importProperties;

    @Bean
    public JobDetailFactoryBean jobDetailFactory() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("importPropertiesJob", importProperties);
        JobDetailFactoryBean jobFactory = new JobDetailFactoryBean();
        jobFactory.setJobClass(PropertiesBatchJob.class);
        jobFactory.setJobDataMap(jobDataMap);
        return jobFactory;
    }

    @Bean
    public CronTriggerFactoryBean batchJobFactory() {
        CronTriggerFactoryBean jobFactory = new CronTriggerFactoryBean();
        jobFactory.setCronExpression("0/30 * * * * ?");
        jobFactory.setJobDetail(jobDetailFactory().getObject());
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(batchJobFactory().getObject());
        return scheduler;
    }
}
