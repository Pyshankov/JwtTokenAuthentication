package com.pyshankov.hairdresser.dto;

import com.pyshankov.hairdresser.domain.Account;
import com.pyshankov.hairdresser.domain.CustomerAccount;
import com.pyshankov.hairdresser.domain.FreelanceAccount;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Created by pyshankov on 1/30/17.
 */
public class FromDto {

    public static Account fromCreateAccountDto(AccountDto accountDto){
        Account account = null;
        if (accountDto.getAccountType().equals(AccountType.CUSTOMER)){
            account = new CustomerAccount(accountDto.getFullName(), accountDto.getPhone());
        }
        else if(accountDto.getAccountType().equals(AccountType.FREELANCE)){
            account = new FreelanceAccount(accountDto.getFullName(), accountDto.getPhone());
            ((FreelanceAccount) account).setServicesList(Arrays.asList(accountDto.getServiceList().split(",")));
        }
        return account;
    }

    public static AccountDto toCreateAccountDto(Account account){

        AccountDto accountDto = new AccountDto(
                account.getAccountType(),
                account.getFullName(),
                account.getPhone(),
                account.getLocation()
        );
        if(accountDto.getAccountType().equals(AccountType.FREELANCE)) {
            accountDto.setServiceList( String.join(",", ((FreelanceAccount) account).getServicesList()));
        }
        return accountDto;
    }
}
