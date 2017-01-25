package com.pyshankov.hairdresser.dto;

import com.pyshankov.hairdresser.domain.Account;

import java.util.List;

/**
 * Created by pyshankov on 1/22/17.
 */
public class ResponseAccountsDto {

    final  List<Account> result ;

    final long count;

    final int offset;

    final int limit;


    public ResponseAccountsDto(List<Account> result, long count, int offset, int limit) {
        this.result = result;
        this.count = count;
        this.offset = offset;
        this.limit = limit;
    }

    public List<Account> getResult() {
        return result;
    }

    public long getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
