package com.abg.stationMapping.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import com.abg.stationMapping.util.Consts;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "app")
@Validated
@Getter
@Setter
public class AppProperties {
			
	private String projectName;
	private String experienceLayer;
	private String processLayer;
	private String systemLayer;
	private String apiName;
	private String apiSuccessResponseMessage;
	private String language;
	private final ECOM ecom = new ECOM();
	
	
	public static class ECOM {
		@NotNull
		private String avisURL;
		@NotNull
		private String budgetURL;
		private String connectTimeOut = "1000";
		private int readTimeOut = 5000;
		@NotNull
		private String locationProximity;
		@NotNull
		private String Authorization;
		@NotNull
		private String channel;
		
		public String getAvisURL() {
			return avisURL;
		}
		public void setAvisURL(String avisURL) {
			this.avisURL = avisURL;
		}
		public String getBudgetURL() {
			return budgetURL;
		}
		public void setBudgetURL(String budgetURL) {
			this.budgetURL = budgetURL;
		}
		public String getConnectTimeOut() {
			return System.getenv().getOrDefault("ECOM_CONNECTTIMEOUT", connectTimeOut);
		}
		public void setConnectTimeOut(String connectTimeOut) {
			this.connectTimeOut = connectTimeOut;
		}
		public int getReadTimeOut() {
			return readTimeOut;
		}
		public void setReadTimeOut(int readTimeOut) {
			this.readTimeOut = readTimeOut;
		}
		public String getLocationProximity() {
			return locationProximity;
		}
		public void setLocationProximity(String locationProximity) {
			this.locationProximity = locationProximity;
		}
		public String getAuthorization() {
			return Authorization;
		}
		public void setAuthorization(String authorization) {
			Authorization = authorization;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}	
		
		
	}		
	
}
