package com.aksh.titan;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aksh.titan.db.TitanDBConfigurator;

@SpringBootApplication
public class TitanGraphCrud extends ResourceConfig {
	private static final Logger logger = Logger.getLogger(TitanGraphCrud.class);
	private final String[] restResourcePackage = new String[] { "com.aksh.titan.rest" };

	public TitanGraphCrud() {
		logger.info("Constructor");
		registerFinder(new PackageNamesScanner(restResourcePackage, false));
	}

	public static void main(String[] args) {

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * try { GremlinServer.main(args); } catch (Exception e) { logger.error(
		 * "Error starting Gremlin server -",e); }
		 * 
		 * } }).start();
		 * 
		 * ConfigurableApplicationContext applicationContext=new
		 * GenericApplicationContext(); TitanDBConfigurator
		 * titanDBConfigurator=TitanDBConfigurator.getInstance();
		 * titanDBConfigurator.setDbConfigFile(new
		 * ClassPathResource("/dev/titan-berkeleydb-es.properties"));
		 * applicationContext.getBeanFactory().registerSingleton(
		 * "tianDBConfigurator", titanDBConfigurator ); new
		 * SpringApplicationBuilder().parent(applicationContext).build().run(
		 * EmailSurvLoadApplication.class, args);
		 */
		try {
			logger.info("Starting germlin server");
			GremlinServerCustom custom = GremlinServerCustom.start(args);
			Map<String, Graph> graphs = custom.getServerGremlinExecutor().getGraphManager().getGraphs();
			Graph graph = graphs.values().iterator().next();
			TitanDBConfigurator.setGraph(graph);
			logger.info("Done -Starting germlin server");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(TitanGraphCrud.class, args);

	}

}
