package fr.exalt.businessmicroserviceoperation.domain.ports.input;

import fr.exalt.businessmicroserviceoperation.domain.entities.BankAccount;
import fr.exalt.businessmicroserviceoperation.domain.entities.Operation;

import fr.exalt.businessmicroserviceoperation.domain.entities.TransferOperation;
import fr.exalt.businessmicroserviceoperation.domain.exceptions.*;
import fr.exalt.businessmicroserviceoperation.infrastructure.adapters.output.models.OperationDto;
import fr.exalt.businessmicroserviceoperation.infrastructure.adapters.output.models.TransferDto;

import java.util.Collection;
import java.util.Map;

public interface InputOperationService {
    Operation createOperation(OperationDto dto);

    Collection<Operation> getAllOperations();

    Collection<Operation> getAccountOperations(String accountId);

    Map<String, BankAccount> transferBetweenAccounts(TransferDto dto);

    Collection<TransferOperation> getAllTransfer();

    Collection<Operation> getAllDepositOperation();

    Collection<Operation> getAllWithdrawalsOperation();
}
