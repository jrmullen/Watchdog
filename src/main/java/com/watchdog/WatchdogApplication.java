package com.watchdog;

/**
 * Created by jmullen on 9/7/16.
 */

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class WatchdogApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(WatchdogApplication.class, args);
//	}
//}

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class WatchdogApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WatchdogApplication.class, args);
	}

}
