package com.BankingTest.Bank.Controller;


import com.BankingTest.Bank.DTO.ApiResponse;
import com.BankingTest.Bank.DTO.Request;
import com.BankingTest.Bank.Exception.AccountNotFoundException;
import com.BankingTest.Bank.Service.IaccountService;
import com.BankingTest.Bank.entity.account;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RequiredArgsConstructor
@RestController
@RequestMapping("operations")
public class AccountController

{
    private final IaccountService iaccountService;

    @GetMapping("user/{Id}/details")
    public ResponseEntity<ApiResponse> GetAccountInfos(@PathVariable Long Id){

        try{
            account account=iaccountService.GetAccountById(Id);
            return ResponseEntity.ok(new ApiResponse("account found",account));
        }catch (AccountNotFoundException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null)
            );

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
        }


    }

    @PostMapping("user/{IdSender}/transfer")
    public ResponseEntity<ApiResponse> TransferMoney(@PathVariable  Long IdSender, @RequestBody Request request)
    {
        try{
            String Operationresults=iaccountService.TransferMoney(IdSender,request.getReceiverId(),request.getAmountToTransfer());
            return ResponseEntity.ok(new ApiResponse(Operationresults,null));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
        }
    }


}
