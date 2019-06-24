package com.abg.stationMapping.logging;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abg.stationMapping.vo.StationMappingVO;

public final class LoggingUtil {

	private LoggingUtil() {
	}

	public static void log(String message, StationMappingVO stationMappingVO, Class<?> clazz, Level level) {

		Logger logger = LogManager.getLogger(clazz);

		if (null != stationMappingVO) {
			logger.log(level, "UUID : " + stationMappingVO.getUuid() + " Txn ID : " + stationMappingVO.getTransactionId()
					+ " Message : " + message);
			//String logMessage = parameters.stream().reduce("", (x,y) -> x+stationMappingVO.getValue(y));
		} else {
			logger.log(level, " Message : " + message);
		}

	}

	
}
