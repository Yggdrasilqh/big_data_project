package com.yggdrasil;

import com.yggdrasil.tools.HadoopConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BigDataProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigDataProjectApplication.class, args);
	}

	@Bean
	public HadoopConnector getBeanOfHadoopConnector(){
		return new HadoopConnector("hdfs://10.8.173.131:9000");
	}
}
