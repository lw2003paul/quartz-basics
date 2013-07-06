package br.com.alexandre.quartz_basics;

import java.util.Calendar;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.alexandre.quartz_basics.jobs.MyJob;

public class CriandoJobProgramaticamente {
	public static void main(final String[] args) throws SchedulerException {
		// Obtendo contexto do Spring
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Obtem scheduler
		final Scheduler scheduler = (Scheduler) applicationContext.getBean("schedulerFactoryBean");
		
		// Cria JobDetail para MyJob
		final JobDetail jobDetail = new JobDetail();
		jobDetail.setJobClass(MyJob.class);
		jobDetail.setName("MyJobDetail1");
		jobDetail.getJobDataMap().put("ID", 1); // Passa ID
		
		// Data de Inicio
		final Calendar startTimeInstance = Calendar.getInstance();
		startTimeInstance.add(Calendar.MINUTE, 1);
		
		// Data de fim
		final Calendar endTimeInstance = Calendar.getInstance();
		endTimeInstance.add(Calendar.MINUTE, 10);
		
		// Cria SimpleTrigger
		final Trigger trigger = new SimpleTrigger();
		trigger.setName("MyTrigger1");
		trigger.setGroup("MyTriggerGroup");
		trigger.setStartTime(startTimeInstance.getTime());
		trigger.setEndTime(endTimeInstance.getTime());
		
		// Agenda Job
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
