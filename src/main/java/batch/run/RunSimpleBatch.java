package batch.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import batch.config.SimpleBatchConfigure;

/**
 *
 * @author Unicorn
 */
@SpringBootApplication
public class RunSimpleBatch {
    public static void main(String[] args) {
        System.out.println("[t] RunSimpleBatch.main()");

        //        System.exit( //
        //                SpringApplication.exit( //
        //                        SpringApplication.run(SimpleConfigure.class, args) //
        //                ) //
        //        );
        
        SpringApplication.exit( //
                SpringApplication.run(SimpleBatchConfigure.class, args) //
        );
    }
}
