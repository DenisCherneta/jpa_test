package ru.dcherneta.vcpe.lib.db.service;

import javax.ws.rs.ApplicationPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Class for configuration rest api
 */
@ApplicationPath("/*")
public class RestApplication extends ResourceConfig {

	public RestApplication() {	
		property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
		packages("ru.dcherneta.vcpe.lib.db");
/*
		this.property("jersey.config.disableMoxyJson", Boolean.valueOf(true));
		this.packages(new String[]{"ru.dcherneta.vcpe.lib.db"});*/
		//this.register(new JacksonJaxbJsonProvider((new JacksonObjectMapperResolver()).getContext(ObjectMapper.class), (Annotations[])null));
}

}
