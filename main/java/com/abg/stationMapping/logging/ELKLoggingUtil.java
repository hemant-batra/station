package com.abg.stationMapping.logging;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abg.stationMapping.config.AppProperties;
import com.abg.stationMapping.controller.StationMappingController;
import com.abg.stationMapping.util.Consts;
import com.abg.stationMapping.vo.StationMappingVO;

@Component
public class ELKLoggingUtil {
		
	@Autowired
	private AppProperties appProperties;
	
	private SimpleDateFormat dtf = new SimpleDateFormat(Consts.DATE_PATTERN);
	
	private Logger logger = LogManager.getLogger(StationMappingController.class);
	
	public void sourceInitailize(HashMap<String, Object> hashMap, StationMappingVO stationMappingVO) {
		hashMap.put("source.starttime", stationMappingVO.getRequestTime());
		hashMap.put("source.nanostarttime", System.nanoTime());
		hashMap.put("uuid", stationMappingVO.getUuid());
		hashMap.put("source.path", "/partner/stationMapping/{partnerCode}/{stationCode}");
		hashMap.put("clientId", stationMappingVO.getClientId());
	}
	public void sourceEnd(HashMap<String, Object> hashMap, StationMappingVO stationMappingVO) {
		try {
			hashMap.put("project.name", appProperties.getProjectName());
			
			hashMap.put("server.hostname", InetAddress.getLocalHost().getHostName());
			hashMap.put("brand", "NA");
			hashMap.put("key_value", "");
			hashMap.put("app.name", appProperties.getApiName());
			hashMap.put("layer.name", appProperties.getSystemLayer());

			hashMap.put("host", InetAddress.getLocalHost().getHostAddress());

			hashMap.put("source.name", "StationMapping");
			hashMap.put("source.response.type", "success");
			hashMap.put("source.response.http.status", "200");

			hashMap.put("target.response.http.status", "200");
			hashMap.put("target.name", "Database");
			hashMap.put("target.response.type", "success");

			hashMap.put("source.endtime", dtf.format((System.currentTimeMillis())));
			hashMap.put("source.nanoendtime", System.nanoTime());
			
			logger.info("Logger hashMap : " + hashMap.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void targetInitailize(HashMap<String, Object> hashMap, StationMappingVO stationMappingVO) {
		hashMap.put("target.starttime", dtf.format((System.currentTimeMillis())));
		hashMap.put("target.nanostarttime", System.nanoTime());
	}
	public void targetEnd(HashMap<String, Object> hashMap, StationMappingVO stationMappingVO) {
		hashMap.put("target.endtime", dtf.format((System.currentTimeMillis())));
		hashMap.put("target.nanoendtime", System.nanoTime());
	}
}
