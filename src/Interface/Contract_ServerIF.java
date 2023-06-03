package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Contract.ContractList;
import Contract.ContractListImpl;
import Contract.PaymentList;
import Contract.PaymentListImpl;

public interface Contract_ServerIF extends Remote{
	public PaymentList getPaymentList() throws RemoteException;
	public ContractList getContractList() throws RemoteException;
}
