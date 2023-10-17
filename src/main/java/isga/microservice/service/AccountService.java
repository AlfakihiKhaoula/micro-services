package isga.microservice.service;

import isga.microservice.dto.BankAccountRequestDTO;
import isga.microservice.dto.BankAccountResponseDTO;
import isga.microservice.entities.BankAccount;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
