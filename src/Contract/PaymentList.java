package Contract;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface PaymentList {



	public boolean add(String paymentInfo) throws ParseException, IOException;

	public boolean delete();

	public ArrayList<Payment> retrieve() throws Exception;

	public boolean update(Payment updatedPayment) throws IOException;

}