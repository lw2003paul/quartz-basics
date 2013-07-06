package br.com.alexandre.quartz_basics.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;

public class MyJob implements Job {

	public void execute(final JobExecutionContext context) throws JobExecutionException {
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		// Obtem ID
		final Integer id = (Integer) context.getJobDetail().getJobDataMap().get("ID");
		
		// Obtem startTime do SimpleTrigger
		final Date startTime = ((SimpleTrigger) context.getTrigger()).getStartTime();
		
		// Obtem endTime do SimpleTrigger
		final Date endTime = ((SimpleTrigger) context.getTrigger()).getEndTime();
		
		final Date now = new Date();
		
		final StringBuilder sb = new StringBuilder();
		sb.append("Executando job")
			.append(" ")
			.append("com o ID")
			.append(" ")
			.append(id)
			.append(" ")
			.append("agendado para iniciar as")
			.append(" ")
			.append(dateFormat.format(startTime))
			.append(" ")
			.append("e para terminar as")
			.append(" ")
			.append(dateFormat.format(endTime))
			.append(" ")
			.append("no momento")
			.append(" ")
			.append(dateFormat.format(now))
			.append(".");
		
		System.out.println(sb.toString());
	}
	
}