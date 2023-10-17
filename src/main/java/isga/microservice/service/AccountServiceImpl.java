package isga.microservice.service;

import isga.microservice.dto.BankAccountRequestDTO;
import isga.microservice.dto.BankAccountResponseDTO;
import isga.microservice.entities.BankAccount;
import isga.microservice.mappers.AccountMapper;
import isga.microservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private BankAccountRepository bankAccountRepos;
    @Autowired
    private AccountMapper accountMapper ;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepos.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBnakAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
}
