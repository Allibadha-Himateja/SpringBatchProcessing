package com.example.batch_processing.Listeners;
import com.example.batch_processing.Models.Student;
import com.example.batch_processing.Processors.FileTriggeredJobStarter;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Set;

@Component
public class CsvFileChangeListener implements FileChangeListener {

    @Autowired
    private FlatFileItemReader<Student> reader;

    @Autowired
    private FileTriggeredJobStarter jobStarter;

    @Autowired
    private Step Step1;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {

        for (ChangedFiles changedFiles : changeSet) {

            for (ChangedFile changedFile : changedFiles.getFiles()) {

                Path path = changedFile.getFile().toPath();
                ChangedFile.Type changeType = changedFile.getType();

                if (changeType == ChangedFile.Type.ADD
                        || changeType == ChangedFile.Type.MODIFY) {
                    try {
//                        jobStarter.startJob(path);
                        System.out.println(path.toString()+" "+changeType.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
