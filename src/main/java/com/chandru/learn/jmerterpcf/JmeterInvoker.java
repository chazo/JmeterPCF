package com.chandru.learn.jmerterpcf;


import java.io.File;
import java.io.IOException;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JmeterInvoker {

    public static void invoke(String pathToFile) throws IOException, ConfigurationException, GenerationException {
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        File jmeterHome = new File("/app/BOOT-INF/classes/jmeter_home");
        if(!jmeterHome.isDirectory()) {
        	jmeterHome = new File("/Users/Documents/applications/apache-jmeter-5.4/");
        }

//         Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties(jmeterHome.getAbsolutePath()+"/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(jmeterHome.getAbsolutePath());
//        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        File in = new File(pathToFile);
        HashTree testPlanTree = SaveService.loadTree(in);

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();

		ReportGenerator rg = new ReportGenerator("./jmeter/HTTP Request.jtl", null);

		rg.generate();
    }
}


