

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import Contract.ContractListImpl;
import Contract.PaymentListImpl;
import Interface.Contract_ServerIF;
import Contract.ContractList;
import Contract.PaymentList;




public class ContractServer extends UnicastRemoteObject implements Contract_ServerIF{
	private static final long serialVersionUID = 1L;
	private static PaymentList PaymentList;
	private static ContractList ContractList;
	
	protected ContractServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			Registry registry = LocateRegistry.createRegistry(1600);	
			ContractServer server = new ContractServer();		
			registry.rebind("ContractServer", server);	
			PaymentList = new PaymentListImpl();
			ContractList = new ContractListImpl();
					
			System.out.println("Contract Server is ready !!!");		
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}
	@Override
	public PaymentList getPaymentList() throws RemoteException{
		return PaymentList;
	}

	@Override
	public ContractList getContractList() throws RemoteException{
		return ContractList;
	}
	
}
