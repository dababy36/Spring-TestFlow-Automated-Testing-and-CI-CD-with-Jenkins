package com.BankingTest.Bank.DTO;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
@Data

public class Request {
    @NotNull
    private Long receiverId;
    @NotNull
    private BigDecimal amountToTransfer;

}
