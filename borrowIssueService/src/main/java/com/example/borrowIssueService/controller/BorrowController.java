package com.example.borrowIssueService.controller;

import com.example.borrowIssueService.entity.Borrow;
import com.example.borrowIssueService.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public Borrow issueBook(@RequestBody Borrow borrow) {
        return borrowService.issueBook(borrow);
    }

    @GetMapping
    public List<Borrow> getAllIssuedBooks() {
        return borrowService.getAllIssuedBooks();
    }

    @GetMapping("/{issueId}")
    public Optional<Borrow> getIssueDetails(@PathVariable Long issueId) {
        return borrowService.getIssueDetails(issueId);
    }

    @PutMapping("/return/{issueId}")
    public Borrow returnBook(@PathVariable Long issueId) {
        return borrowService.returnBook(issueId);
    }
}
