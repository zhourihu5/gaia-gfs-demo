package com.longfor.gaia.gfs.demo.dorado;

import com.bstek.dorado.web.loader.DoradoLoader;
import com.longfor.gaia.gfs.web.dorado.config.DoradoApplicationContextInilializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication
public class Application  {


	public static void main(String[] args) throws Exception {
		System.setProperty("doradoHome", "classpath:dorado-home/");

		SpringApplication app = new SpringApplication(Application.class);
		app.addInitializers(new DoradoApplicationContextInilializer());

		DoradoLoader doradoLoader = DoradoLoader.getInstance();
		doradoLoader.preload(true);

		Set<Object> sources = new LinkedHashSet<Object>();
		sources.addAll(doradoLoader.getContextLocations(false));
		app.setSources(sources);

		app.run(args);
	}
}
