package org.cascomio.springbatchexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.cascomio.springbatchexample.models.Property;
import org.cascomio.springbatchexample.parsers.DefaultHtmlParser;
import org.cascomio.springbatchexample.parsers.HtmlParser;
import org.cascomio.springbatchexample.readers.FoxtonsReader;
import org.jsoup.nodes.Document;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.cascomio.springbatchexample.notifications.BatchCompletedNotification;
import org.cascomio.springbatchexample.processors.FoxtonsProcessor;
import org.cascomio.springbatchexample.writters.SparkPropertyWriter;

import java.util.Collection;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public static final String IMPORT_PROPERTIES = "import Properties";

    @Bean
    public HtmlParser htmlParser(){
        return new DefaultHtmlParser();
    }

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public JobExecutionListenerSupport listener() {
        return new BatchCompletedNotification();
    }

    @Bean
    @Qualifier("importProperties")
    public Job importProperties() {
        return jobBuilderFactory.get(IMPORT_PROPERTIES)
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(importFoxtonsProperties())
                .end()
                .build();
    }

    @Bean
    public Step importFoxtonsProperties() {
        return stepBuilderFactory.get("import Foxtons Properties")
                .<Collection<Document>, Collection<Property>>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public JavaSparkContext sparkContext() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Property Batch Example");
        sparkConf.setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        return sparkContext;
    }

    @Bean
    public ItemReader<Collection<Document>> reader() {
        return new FoxtonsReader();
    }

    @Bean
    public ItemProcessor<Collection<Document>, Collection<Property>> processor() {
        return new FoxtonsProcessor();
    }

    @Bean
    public ItemWriter<Collection<Property>> writer() {
        return new SparkPropertyWriter();
    }
}
