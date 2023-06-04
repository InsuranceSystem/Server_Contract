package Interface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;



public interface ContractList extends Remote{

	boolean add(Contract contract) throws IOException, RemoteException;


	ArrayList<Contract> retrieve() throws Exception ,RemoteException;

	boolean updateCancellation(String customerId, String insuranceId) throws IOException ,RemoteException;

//
//	void setResurrectFromCustomer(Customer customer);
//
//
//	void setMaturityFromCustomer(Customer customer);
//	
//	void setWheaterPaymentFromCustomer(Customer customer);

	ArrayList<Contract> getContractsByCID(String inputCustomerId) throws IOException, ParseException ,RemoteException;


	ArrayList<Contract> retreiveCustomerContract(String customerId) throws RemoteException;


	ArrayList<String> getInsuranceIdFromCustomerId(String customerId) throws RemoteException;


	Contract getContractByInsuranceID(String insuranceID) throws RemoteException;


	ArrayList<String> retreivePremiumById(String selectedCustomerId, String selectedInsuranceId) throws RemoteException;



}