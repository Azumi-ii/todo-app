package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {
	@Autowired
    private TodoRepository todoRepository;

    // 一覧画面表示
    @GetMapping("/todos")
    public String listTodos(Model model) {
        List<Todo> todoList = todoRepository.findAll();
        model.addAttribute("todos", todoList);
        return "index";
    }

    // 追加画面表示
    @GetMapping("/todos/add")
    public String showAddForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    // タスク保存処理
    @PostMapping("/todos/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todos"; // 保存後に一覧へリダイレクト
    }
 // 今日のタスクのみ表示
    @GetMapping("/todos/today")
    public String getTodayTodos(Model model) {
        List<Todo> todoList = todoRepository.findByDeadline(LocalDate.now());
        model.addAttribute("todos", todoList);
        return "index";
    }

    // 期限が短い順でソート
    @GetMapping("/todos/sort")
    public String getSortedTodos(Model model) {
        List<Todo> todoList = todoRepository.findAllByOrderByDeadlineAsc();
        model.addAttribute("todos", todoList);
        return "index";
    }
}