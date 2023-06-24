package com.example.transfer.service.impl;

import com.example.transfer.dao.AccountDao;
import com.example.transfer.pojo.Account;
import com.example.transfer.service.TransferService;


public class TransferServiceImpl implements TransferService {

    // 最佳状态
    private AccountDao accountDao;

    // set方法传值
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
        accountDao.updateAccountByCardNo(from);
    }
}
