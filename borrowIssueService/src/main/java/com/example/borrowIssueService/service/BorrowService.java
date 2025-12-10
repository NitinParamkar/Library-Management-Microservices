package com.example.borrowIssueService.service;

import com.example.borrowIssueService.entity.Borrow;
import com.example.borrowIssueService.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    public Borrow issueBook(Borrow borrow) {
        borrow.setIssueDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllIssuedBooks() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getIssueDetails(Long issueId) {
        return borrowRepository.findById(issueId);
    }

    public Borrow returnBook(Long issueId) {
        Borrow borrow = borrowRepository.findById(issueId).orElseThrow(() -> new RuntimeException("Issue not found"));
        borrow.setReturnDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }
}
