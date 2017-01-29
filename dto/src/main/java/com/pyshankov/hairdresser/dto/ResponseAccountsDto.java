package com.pyshankov.hairdresser.dto;



import java.util.List;

/**
 * Created by pyshankov on 1/22/17.
 */
public class ResponseAccountsDto {

    final  List<AccountDto> result ;

    final long count;

    final int offset;

    final int limit;


    public ResponseAccountsDto(List<AccountDto> result, long count, int offset, int limit) {
        this.result = result;
        this.count = count;
        this.offset = offset;
        this.limit = limit;
    }

    public List<AccountDto> getResult() {
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
