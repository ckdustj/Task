package com.task;

import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("select location, sum(count) as 'count' from search group by location")
    List<TaskDTO> read_data();

    @Select("SELECT * FROM `search`")
    List<TaskDTO> read_all_data();
}
