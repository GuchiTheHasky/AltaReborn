package com.alta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAltaRebornApplication {

	public static void main(String[] args) {
		SpringApplication.from(AltaRebornApplication::main).with(TestAltaRebornApplication.class).run(args);
	}

}
