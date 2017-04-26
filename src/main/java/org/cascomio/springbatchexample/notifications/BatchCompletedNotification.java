package org.cascomio.springbatchexample.notifications;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class BatchCompletedNotification  extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(String.format("%s job terminated at %tc", jobExecution.getJobConfigurationName(), jobExecution.getEndTime()));
    }
}
