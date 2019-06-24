package com.abg.stationMapping.util;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.abg.stationMapping.Exception.StationMappingException;
import com.abg.stationMapping.config.AppProperties;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.vo.StationMappingVO;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;

@Component
public class ExternalWebClient {
	
	@Autowired
    private AppProperties appProperties;
	
	public WebClient getWebClient(StationMappingVO stationMappingVO) throws StationMappingException {
		try {
			SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();
						
			ClientHttpConnector httpConnector = new ReactorClientHttpConnector(options -> options.sslContext(sslContext)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.parseInt(appProperties.getEcom().getConnectTimeOut())).afterNettyContextInit(ctx -> {
						ctx.addHandlerLast(new ReadTimeoutHandler(appProperties.getEcom().getReadTimeOut(), TimeUnit.MILLISECONDS));
					}));

			return WebClient.builder().clientConnector(httpConnector).build();
		} catch (NullPointerException ex) {
			LoggingUtil.log("NullPointerException : getWebClient : " + ex, stationMappingVO, ExternalWebClient.class,
					Level.ERROR);
			throw new StationMappingException(ex);
		} catch (Exception ex) {
			LoggingUtil.log("Exception : getWebClient : " + ex, stationMappingVO, ExternalWebClient.class, Level.ERROR);
			throw new StationMappingException(ex);
		}

	}	
}
