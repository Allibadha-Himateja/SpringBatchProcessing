package com.example.batch_processing.Processors;

import com.example.batch_processing.Models.Student;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration  {

    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }

    @Bean
    public FlatFileItemReader<Student> reader()
    {
        return new FlatFileItemReaderBuilder<Student>()
                .name("StudentProcessor")
                .resource(new ClassPathResource("studentdata.csv"))
                .linesToSkip(1) // 🔥 IMPORTANT for csv to data conversion header will not be cconverted into int,string,float values...
                .delimited()
                .names("sno","sname","fee")
                .targetType(Student.class)
                .build();

    }
    @Bean
    public JdbcBatchItemWriter<Student> writer(DataSource dataSource)
    {
        return new JdbcBatchItemWriterBuilder<Student>()
                .sql("Insert into student (sno,sname,fee) values (:sno, :sname, :fee)")
                .dataSource(dataSource)
                .beanMapped().build();
    }
}


class StudentProcessor implements ItemProcessor<Student,Student>
{
    @Override
    public Student process(Student student) {
        student.setSname(student.getSname().toUpperCase());
        return student;
    }
}
