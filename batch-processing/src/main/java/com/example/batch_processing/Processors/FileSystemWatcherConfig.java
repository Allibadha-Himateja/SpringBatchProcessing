package com.example.batch_processing.Processors;

import com.example.batch_processing.Listeners.CsvFileChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.Duration;

@Configuration
public class FileSystemWatcherConfig {

    @Autowired
    private CsvFileChangeListener fileChangeListener;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher watcher =
                new FileSystemWatcher(
                        true,                       // daemon thread
                        Duration.ofSeconds(5),       // poll interval
                        Duration.ofSeconds(3)        // quiet period
                );

        watcher.addSourceDirectory(
                new File("D:\\SpringFiles")          // DIRECTORY, not file
        );

        watcher.addListener(fileChangeListener);

        return watcher;
    }
}
