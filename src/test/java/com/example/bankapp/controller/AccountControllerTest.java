package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.TestSecurityConfig;  // Убедитесь, что этот импорт корректный
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, AccountController.class})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAccountById() throws Exception {
        Account account = new Account("John Doe", 1000.0);
        account.setId(1L);

        when(accountService.getAccountById(any(Long.class))).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.balance").value(1000.0));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testCreateAccount() throws Exception {
        Account account = new Account("John Doe", 1000.0);

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .contentType("application/json")
                .content("{\"name\": \"John Doe\", \"balance\": 1000.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.balance").value(1000.0));
    }

}
