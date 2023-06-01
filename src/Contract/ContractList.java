package Contract;

import java.io.IOException;
import java.util.ArrayList;


public interface ContractList {

	boolean add(Contract contract) throws IOException ;


	ArrayList<Contract> retrieve() throws Exception ;

	boolean updateCancellation(String customerId, String insuranceId) throws IOException;


	void setResurrectFromCustomer(Customer customer);


	void setMaturityFromCustomer(Customer customer);
	
	void setWheaterPaymentFromCustomer(Customer customer);

	ArrayList<Contract> getContractsByCID(String inputCustomerId);


	ArrayList<Contract> retreiveCustomerContract(String customerId);


	ArrayList<String> getInsuranceIdFromCustomerId(String customerId);


	Contract getContractByInsuranceID(String insuranceID);


	ArrayList<String> retreivePremiumById(String selectedCustomerId, String selectedInsuranceId);



}