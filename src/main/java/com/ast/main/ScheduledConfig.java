package com.ast.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ast.HealthCare.Dao.SMSSettingDao;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.ast.HealthCare")
public class ScheduledConfig {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SMSSettingDao ssd;
	ScheduledConfig(){
	/*	 this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("jdbc "+this.jdbcTemplate);
	}
	
    // initialDelay = 3 second
	// fixedDelay = 2 second.
	   @Scheduled(cron = "0 0/30 * * * ?", zone="Asia/Kolkata")
	   public void callBeginingOfDay() {
		   // calling sms setting dao
		   ssd.Smssetting();
		   // calling daily sms setting
	       ssd.dailysms();
	     
	   }
}