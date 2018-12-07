package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long>{
    public List<Task> findAllByOrderByStartDateAsc();
    public List<Task> findAllByOrderByEndDateAsc();
    public List<Task> findAllByOrderByPriorityAsc();
    public List<Task> findAllByOrderByStatusAsc();
    public List<Task> findByTaskContainingIgnoreCase(String task);
    @Query(value ="select * from fsd.task where parent_id=?1",nativeQuery=true )
    public List<Task> findByParentTaskId(long parentId);
    @Query(value ="select * from fsd.task where project_id=?1",nativeQuery=true )
    public List<Task> findByProject(long projectId);
}
