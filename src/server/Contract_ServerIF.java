package server;
import Contract.ContractList;
import Contract.ContractListImpl;
import Contract.PaymentList;
import Contract.PaymentListImpl;

public interface Contract_ServerIF {
	public PaymentList getPaymentList();
	public ContractList getContractList();
}
