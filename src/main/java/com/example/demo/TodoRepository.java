package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	// 1. 期限が短い順（昇順）で全てのタスクを取得
	List<Todo> findAllByOrderByDeadlineAsc();

	// 2. 本日が期限のタスクのみを取得
	List<Todo> findByDeadline(LocalDate date);
}
