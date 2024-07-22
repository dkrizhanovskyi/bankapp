package com.example.bankapp.service;

import com.example.bankapp.model.Account;
import com.example.bankapp.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testGetAccountById() {
        Account account = new Account("John Doe", 1000.0);
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(1L);

        assertNotNull(foundAccount);
        assertEquals(1L, foundAccount.getId());
        assertEquals("John Doe", foundAccount.getName());
        assertEquals(1000.0, foundAccount.getBalance());
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account("John Doe", 1000.0);

        when(accountRepository.save(account)).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        assertEquals("John Doe", createdAccount.getName());
        assertEquals(1000.0, createdAccount.getBalance());
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account("John Doe", 1000.0);
        account.setId(1L);

        Account updatedDetails = new Account("John Doe Updated", 2000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = accountService.updateAccount(1L, updatedDetails);

        assertNotNull(updatedAccount);
        assertEquals("John Doe Updated", updatedAccount.getName());
        assertEquals(2000.0, updatedAccount.getBalance());
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account("John Doe", 1000.0);
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).delete(account);

        boolean isDeleted = accountService.deleteAccount(1L);

        assertTrue(isDeleted);
        verify(accountRepository, times(1)).delete(account);
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account("John Doe", 1000.0);
        Account account2 = new Account("Jane Doe", 2000.0);

        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

        List<Account> accounts = accountService.getAllAccounts();

        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }
}
