package com.example.transfer.service;


public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}
