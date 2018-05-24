package batch.config;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import batch.bo.PersonBo;
import batch.listener.JobCompletionNotificationListener;
import batch.processor.PersonItemProcessor;

/**
 *
 * @author Unicorn
 */
@Configuration
@EnableBatchProcessing
@ComponentScan("batch.listener")
public class ReadBatchConfigure {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        System.out.println("[t] importUserJob()");

        return jobBuilderFactory //
                .get("importUserJob") //
                .incrementer(new RunIdIncrementer()) //
                .listener(listener) //
                .flow(step1) //
                .end() //
                .build();
    }

    @Bean
    public Step step1() {
        System.out.println("[t] step1()");

        return stepBuilderFactory //
                .get("step1") //
                .<PersonBo, PersonBo>chunk(10) //
                .reader(reader()) //
                .processor(processor()) //
                .writer(writer()) // 
                .build();
    }

    @Bean
    public FlatFileItemReader<PersonBo> reader() {
        System.out.println("[t] reader()");

        final FlatFileItemReader<PersonBo> itemReader = new FlatFileItemReaderBuilder<PersonBo>() //
                .name("PersonBoItemReader") //
                .resource(new ClassPathResource("sample-data.csv")) //
                .delimited() //
                .names(new String[] { "name", "age" }) //
                //                .fieldSetMapper(new BeanWrapperFieldSetMapper<PersonBo>() {
                //                    {
                //                        setTargetType(PersonBo.class);
                //                    }
                //                }) //
                .targetType(PersonBo.class) //
                .build();

        System.out.println(ToStringBuilder.reflectionToString(itemReader, ToStringStyle.MULTI_LINE_STYLE));
        return itemReader;
    }

    @Bean
    public PersonItemProcessor processor() {
        System.out.println("[t] processor()");

        return new PersonItemProcessor();
    }

    @Bean
    public ItemWriter<PersonBo> writer() {
        System.out.println("[t] writer()");

        final ItemWriter<PersonBo> itemWriter = new ItemWriter<PersonBo>() {
            @Override
            public void write(List<? extends PersonBo> items) throws Exception {
                System.out.println("[t] ItemWriter.write()");
            }
        };

        return itemWriter;
    }

}
