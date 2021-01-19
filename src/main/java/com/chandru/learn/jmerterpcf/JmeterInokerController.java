package com.chandru.learn.jmerterpcf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmeterInokerController {

	@GetMapping( path = "/invoke")
	public String invoke() throws IOException, ConfigurationException, GenerationException, URISyntaxException {
		URL res = getClass().getClassLoader().getResource("HTTP Request.jmx");
		File file = Paths.get(res.toURI()).toFile();
		String absolutePath = file.getAbsolutePath();
		JmeterInvoker.invoke(absolutePath);

		return "Success!";

	}

}
