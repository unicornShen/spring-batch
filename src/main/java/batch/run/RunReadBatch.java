package batch.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import batch.config.ReadBatchConfigure;

/**
 *
 * @author Unicorn
 */
@SpringBootApplication
public class RunReadBatch {
    public static void main(String[] args) {
        System.out.println("[t] RunReadBatch.main()");

        SpringApplication.exit( //
                SpringApplication.run(ReadBatchConfigure.class, args) //
        );
    }
}
