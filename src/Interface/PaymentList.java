package Interface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import Contract.Payment;

public interface PaymentList extends Remote{

	public boolean add(String paymentInfo) throws ParseException, IOException, RemoteException;

	public boolean delete() throws RemoteException;

	public ArrayList<Payment> retrieve() throws Exception , RemoteException;

	public boolean update(Payment updatedPayment) throws IOException , RemoteException;

	public ArrayList<Payment> retreiveCustomerInsurancePayment(String customerId, String selectedInsuranceId) throws RemoteException;
	public ArrayList<Payment> retreiveCustomerPayment(String selectedCustomerId) throws RemoteException;
	public ArrayList<String> retreiveUnpaidCustomerId() throws RemoteException;

	public ArrayList<String> retreiveDateStatusById(String selectedCustomerId, String selectedInsuranceId) throws RemoteException;

	public boolean updateWhetherPayment(String selectedCustomerId, String selectedInsuranceId) throws RemoteException, IOException;
}