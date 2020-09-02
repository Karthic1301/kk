/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ast.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class Config {

	@Value("${jdbc.driverClassName}")
	private String driverName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String userName;

	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String dialect;

	@Value("${hibernate.show_sql}")
	private String showSQL;

	@Value("${hibernate.format_sql}")
	private String formatSQL;

	/*
	 * @Value("${const.AWSAccessKey}") private String awsAccessKey;
	 * 
	 * @Value("${const.AWSSecretKey") private String awsSecretKey;
	 * 
	 * @Value("${const.AWSAccountFlag}") private boolean awsAccountFlag;
	 */

	@Value("${const.dataBaseName}")
	private String dataBaseName;
	@Value("${const.assetDirectory}")
	private String assetDirectory;

	// @Value("${const.printFontDirectory}")
	private String printFontDirectory;

	// @Value("${const.itextLicenseDirectory}")
	private String itextLicenseDirectory;

	
	public String getCleaningAfterCheckout() {
		return cleaningAfterCheckout;
	}

	public void setCleaningAfterCheckout(String cleaningAfterCheckout) {
		this.cleaningAfterCheckout = cleaningAfterCheckout;
	}

	@Value("${const.cleaningAfterCheckout}")
	private String cleaningAfterCheckout;
	
	
	@Value("${const.host}")
	private String host;

	@Value("${const.port}")
	private int port;

	@Value("${const.mailUserName}")
	private String mailUserName;

	@Value("${const.mailPassword}")
	private String mailPassword;
	
	@Value("${const.onlineApiService}")
	private String onlineApiService;
	
	 @Value("${const.mySqlHomePath}")
	    private String mySqlHomePath;

	public String getOnlineApiService() {
		return onlineApiService;
	}

	public void setOnlineApiService(String onlineApiService) {
		this.onlineApiService = onlineApiService;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getShowSQL() {
		return showSQL;
	}

	public void setShowSQL(String showSQL) {
		this.showSQL = showSQL;
	}

	public String getFormatSQL() {
		return formatSQL;
	}

	public void setFormatSQL(String formatSQL) {
		this.formatSQL = formatSQL;
	}

	public String getAssetDirectory() {
		return assetDirectory;
	}

	public void setAssetDirectory(String assetDirectory) {
		this.assetDirectory = assetDirectory;
	}

	public String getPrintFontDirectory() {
		return printFontDirectory;
	}

	public void setPrintFontDirectory(String printFontDirectory) {
		this.printFontDirectory = printFontDirectory;
	}

	public String getItextLicenseDirectory() {
		return itextLicenseDirectory;
	}

	public void setItextLicenseDirectory(String itextLicenseDirectory) {
		this.itextLicenseDirectory = itextLicenseDirectory;
	}

	/*
	 * public String getAwsAccessKey() { return awsAccessKey; }
	 * 
	 * public void setAwsAccessKey(String awsAccessKey) { this.awsAccessKey =
	 * awsAccessKey; }
	 * 
	 * public String getAwsSecretKey() { return awsSecretKey; }
	 * 
	 * public void setAwsSecretKey(String awsSecretKey) { this.awsSecretKey =
	 * awsSecretKey; }
	 * 
	 * public boolean isAwsAccountFlag() { return awsAccountFlag; }
	 * 
	 * public void setAwsAccountFlag(boolean awsAccountFlag) { this.awsAccountFlag =
	 * awsAccountFlag; }
	 */
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	
	public String getMySqlHomePath() {
		return mySqlHomePath;
	}

	public void setMySqlHomePath(String mySqlHomePath) {
		this.mySqlHomePath = mySqlHomePath;
	}

}
