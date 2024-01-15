package com.example.paymentgateway.service;

import com.example.paymentgateway.controller.AccountController;
import com.example.paymentgateway.dao.AccountDao;
import com.example.paymentgateway.entity.Account;
import com.example.paymentgateway.exception.InsufficientAmountException;
import com.example.paymentgateway.exception.InvalidCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;

    @Transactional
    public void makeTransaction(String cartNum,double amount) {
        Account account = accountDao.findAccountByCardNumber(cartNum)
                .orElseThrow(InvalidCardException::new);
        double currentAmount = account.getAmount();
        if(amount > currentAmount){
            throw new InsufficientAmountException();
        }
        double updateAmount = currentAmount - amount;
        account.setAmount(updateAmount);
    }
}
