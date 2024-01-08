package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Scheduled(cron = " * * * * * ?")
    public void create_csv(){
        List<TaskDTO> taskDTOS = taskMapper.read_data();

        try(
                FileOutputStream fileOutputStream = new FileOutputStream("my_csv.csv");
                OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream, "MS949");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
        ){
            for(TaskDTO taskDTO: taskDTOS){
                bufferedWriter.write(taskDTO.getLocation());
                bufferedWriter.write(",");
                bufferedWriter.write(taskDTO.getCount());
                bufferedWriter.write("\n");
//                System.out.println(taskDTO);

            }
            System.out.println("파일 생성 완 !");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("오류나써요");
        }
    }
}
