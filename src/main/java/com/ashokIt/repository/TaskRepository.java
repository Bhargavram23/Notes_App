package com.ashokIt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokIt.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
		public List<Task> findByOwner_id(Integer id);
}
