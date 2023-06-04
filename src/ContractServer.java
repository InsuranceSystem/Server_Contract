

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
import Interface.ContractList;
import Interface.PaymentList;



public class ContractServer extends UnicastRemoteObject{
	private static final long serialVersionUID = 1L;
	
	
	protected ContractServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			System.setProperty("java.security.policy", "policy.txt");
			System.setSecurityManager(null);		
	
			PaymentList paymentList = new PaymentListImpl();
			PaymentList stub = (PaymentList) UnicastRemoteObject.exportObject(paymentList, 0);
		    Registry registry1 = LocateRegistry.createRegistry(1303);
		    registry1.rebind("PaymentList", (Remote)stub);
		        
		        
		        // SurveyList 객체 등록
		        ContractList contractList = new ContractListImpl();
		        ContractList stub2 = (ContractList) UnicastRemoteObject.exportObject(contractList, 0);
		        Registry registry2 = LocateRegistry.createRegistry(1304);
		        registry2.rebind("ContractList", stub2);
		
			System.out.println("Contract Server is ready !!!");		
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}

}
