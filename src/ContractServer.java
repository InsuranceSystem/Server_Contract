
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Exception.CustomClassNotFoundException;
import Exception.CustomConnectException;
import Exception.CustomNotBoundException;
import ListImpl.ContractListImpl;
import ListImpl.PaymentListImpl;
import Interface.ContractList;
import Interface.PaymentList;

public class ContractServer extends UnicastRemoteObject {
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
			registry1.rebind("PaymentList", (Remote) stub);

			// SurveyList 객체 등록
			ContractList contractList = new ContractListImpl();
			ContractList stub2 = (ContractList) UnicastRemoteObject.exportObject(contractList, 0);
			Registry registry2 = LocateRegistry.createRegistry(1304);
			registry2.rebind("ContractList", stub2);

			System.out.println("Contract Server is ready !!!");

		} catch (RemoteException e) {
		    System.out.println("서버와의 원격 통신 중 오류가 발생했습니다: " + e.getMessage());
		    System.out.println("잠시 후에 다시 시도해 주세요.");
		} catch (CustomNotBoundException e) {
		    System.out.println("요청한 서비스를 찾을 수 없습니다: " + e.getMessage());
		    System.out.println("관리자에게 문의하여 문제를 해결해 주세요.");
		} catch (MalformedURLException e) {
		    System.out.println("잘못된 URL 형식으로 연결을 시도했습니다: " + e.getMessage());
		    System.out.println("URL 주소를 다시 확인하고 재시도해 주세요.");
		} catch (CustomConnectException e) {
		    System.out.println("서버에 연결할 수 없습니다: " + e.getMessage());
		    System.out.println("인터넷 연결을 확인하고, 서버가 정상적으로 실행 중인지 확인해 주세요.");
		} catch (IllegalAccessException e) {
		    System.out.println("잘못된 접근으로 인해 오류가 발생했습니다: " + e.getMessage());
		    System.out.println("권한이 필요한 작업에 접근하지 않도록 주의해 주세요.");
		} catch (CustomClassNotFoundException | NoClassDefFoundError e) {
		    System.out.println("필요한 클래스를 찾을 수 없습니다: " + e.getMessage());
		    System.out.println("프로그램이 올바르게 설치되었는지 확인하고, 필요한 파일이 제대로 위치해 있는지 확인해 주세요.");
		} catch (IOException e) {
		    // IOException 예외 처리
		    throw new CustomConnectException("입출력 오류가 발생했습니다", e);
		} catch (Exception e) {
		    System.out.println("오류가 발생했습니다: " + e.getMessage());
		    System.out.println("문제가 지속되면 관리자에게 문의하여 도움을 받으세요.");
		}
	}

}
