package org.ds.msgboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.ds.msgboard.dao")
public class MsgboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgboardApplication.class, args);
	}
}
