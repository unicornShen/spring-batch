package batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Unicorn
 */
@Configuration
@EnableBatchProcessing
//@ImportResource({ "classpath:application-context.xml" })
public class SimpleBatchConfigure {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(Step step1) throws Exception {
        System.out.println("[t] job()");

        return jobBuilderFactory //
                .get("job1") //
                .incrementer(new RunIdIncrementer()) //
                .start(step1) //
                .build();
    }

    @Bean
    public Step step1() {
        System.out.println("[t] step1()");

        return stepBuilderFactory //
                .get("step1") //
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                        System.out.println("[t] step1.execute()");
                        return null;
                    }
                }).build();
    }

}
