package com.example.bankapp.service;

import com.example.bankapp.model.Account;
import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Cacheable(value = "accounts", key = "#id")
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account accountDetails) {
        return accountRepository.findById(id)
                .map(account -> {
                    account.setName(accountDetails.getName());
                    account.setBalance(accountDetails.getBalance());
                    return accountRepository.save(account);
                }).orElse(null);
    }

    public boolean deleteAccount(Long id) {
        return accountRepository.findById(id)
                .map(account -> {
                    accountRepository.delete(account);
                    return true;
                }).orElse(false);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Async("taskExecutor")
    public CompletableFuture<Account> findAccountByIdAsync(Long id) {
        return CompletableFuture.completedFuture(accountRepository.findById(id).orElse(null));
    }
}
