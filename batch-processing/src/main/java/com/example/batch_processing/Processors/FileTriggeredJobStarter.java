package com.example.batch_processing.Processors;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class FileTriggeredJobStarter {

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private  Job importUserJob;

    public void startJob(Path filePath) throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addString("file", filePath.toString())
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobOperator.start(importUserJob, params);
    }
}

