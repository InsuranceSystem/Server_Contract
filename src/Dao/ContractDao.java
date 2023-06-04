package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Contract.Contract;
import Exception.DaoException;

public class ContractDao extends Dao {

    public ContractDao() throws DaoException {
        try {
            super.connect();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
            System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
        }
    }

    public void create(Contract contract) throws DaoException {
        String query = "INSERT INTO contracts (customerID, insuranceID, insurancePeriod, premium, paymentCycle, "
                + "maxCompensation, dateOfSubscription, dateOfMaturity, maturity, resurrection, cancellation) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, contract.getCustomerID());
            statement.setString(2, contract.getInsuranceID());
            statement.setString(3, contract.getInsurancePeriod());
            statement.setDouble(4, contract.getPremium());
            statement.setString(5, contract.getPaymentCycle());
            statement.setDouble(6, contract.getMaxCompensation());
            statement.setDate(7, java.sql.Date.valueOf(contract.getDateOfSubscription()));
            statement.setDate(8, java.sql.Date.valueOf(contract.getDateOfMaturity()));
            statement.setBoolean(9, contract.isMaturity());
            statement.setBoolean(10, contract.isResurrection());
            statement.setBoolean(11, contract.isCancellation());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Contract 생성 중에 오류가 발생했습니다.", "create");
        }
    }

    public List<Contract> retrieveByCustomerId(String customerId) throws DaoException {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM contract WHERE customerID = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setCustomerID(resultSet.getString("customerID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsurancePeriod(resultSet.getString("insurancePeriod"));
                contract.setPremium(resultSet.getInt("premium"));
                contract.setPaymentCycle(resultSet.getString("paymentCycle"));
                contract.setMaxCompensation(resultSet.getInt("maxCompensation"));
                String dateString = resultSet.getString("dateOfSubscription");
                LocalDate dateOfSubscription = LocalDate.parse(dateString);
                contract.setDateOfSubscription(dateOfSubscription);
                String dateStringMaturity = resultSet.getString("dateOfMaturity");
                LocalDate dateOfMaturity = LocalDate.parse(dateStringMaturity);
                contract.setDateOfMaturity(dateOfMaturity);
                contract.setMaturity(resultSet.getBoolean("maturity"));
                contract.setResurrection(resultSet.getBoolean("resurrection"));
                contract.setCancellation(resultSet.getBoolean("cancellation"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            throw new DaoException("Customer ID로 Contract 조회 중에 오류가 발생했습니다.", "retrieveByCustomerId");
        }

        return contracts;
    }

    public List<Contract> retrieveByInsuranceId(String insuranceId) throws DaoException {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM contract WHERE insuranceID = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, insuranceId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setCustomerID(resultSet.getString("customerID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsurancePeriod(resultSet.getString("insurancePeriod"));
                contract.setPremium(resultSet.getInt("premium"));
                contract.setPaymentCycle(resultSet.getString("paymentCycle"));
                contract.setMaxCompensation(resultSet.getInt("maxCompensation"));
                String dateString = resultSet.getString("dateOfSubscription");
                LocalDate dateOfSubscription = LocalDate.parse(dateString);
                contract.setDateOfSubscription(dateOfSubscription);
                String dateStringMaturity = resultSet.getString("dateOfMaturity");
                LocalDate dateOfMaturity = LocalDate.parse(dateStringMaturity);
                contract.setDateOfMaturity(dateOfMaturity);
                contract.setMaturity(resultSet.getBoolean("maturity"));
                contract.setResurrection(resultSet.getBoolean("resurrection"));
                contract.setCancellation(resultSet.getBoolean("cancellation"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            throw new DaoException("Insurance ID로 Contract 조회 중에 오류가 발생했습니다.", "retrieveByInsuranceId");
        }

        return contracts;
    }

    public void update(Contract contract) throws DaoException {
        String query = "UPDATE contract SET insurancePeriod = ?, premium = ?, paymentCycle = ?, "
                + "maxCompensation = ?, dateOfSubscription = ?, dateOfMaturity = ?, maturity = ?, "
                + "resurrection = ?, cancellation = ? WHERE customerID = ? AND insuranceID = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, contract.getInsurancePeriod());
            statement.setDouble(2, contract.getPremium());
            statement.setString(3, contract.getPaymentCycle());
            statement.setDouble(4, contract.getMaxCompensation());
            statement.setDate(5, java.sql.Date.valueOf(contract.getDateOfSubscription()));
            statement.setDate(6, java.sql.Date.valueOf(contract.getDateOfMaturity()));
            statement.setBoolean(7, contract.isMaturity());
            statement.setBoolean(8, contract.isResurrection());
            statement.setBoolean(9, contract.isCancellation());
            statement.setString(10, contract.getCustomerID());
            statement.setString(11, contract.getInsuranceID());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Contract 업데이트 중에 오류가 발생했습니다.", "update");
        }
    }

    public void updateCancellation(String customerId, String insuranceId, boolean cancellation) throws DaoException {
        String query = "UPDATE contract SET cancellation = ? WHERE customerID = ? AND insuranceID = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setBoolean(1, cancellation);
            statement.setString(2, customerId);
            statement.setString(3, insuranceId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Contract 취소 상태 업데이트 중에 오류가 발생했습니다.", "updateCancellation");
        }
    }

    public List<Contract> retrieveByCustomerInsuranceId(String customerId, String insuranceId) throws DaoException {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM contract WHERE customerID = ? AND insuranceID = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, customerId);
            statement.setString(2, insuranceId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setCustomerID(resultSet.getString("customerID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsurancePeriod(resultSet.getString("insurancePeriod"));
                contract.setPremium(resultSet.getInt("premium"));
                contract.setPaymentCycle(resultSet.getString("paymentCycle"));
                contract.setMaxCompensation(resultSet.getInt("maxCompensation"));
                String dateString = resultSet.getString("dateOfSubscription");
                LocalDate dateOfSubscription = LocalDate.parse(dateString);
                contract.setDateOfSubscription(dateOfSubscription);
                String dateStringMaturity = resultSet.getString("dateOfMaturity");
                LocalDate dateOfMaturity = LocalDate.parse(dateStringMaturity);
                contract.setDateOfMaturity(dateOfMaturity);
                contract.setMaturity(resultSet.getBoolean("maturity"));
                contract.setResurrection(resultSet.getBoolean("resurrection"));
                contract.setCancellation(resultSet.getBoolean("cancellation"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            throw new DaoException("Customer ID와 Insurance ID로 Contract 조회 중에 오류가 발생했습니다.", "retrieveByCustomerInsuranceId");
        }

        return contracts;
    }

    public ArrayList<Contract> retrieveAll() throws DaoException {
        String query = "select * from contract;";
        ResultSet results = super.retrieve(query);
        ArrayList<Contract> contractList = new ArrayList<>();

        try {
            while (results.next()) {
                Contract contract = new Contract();
                contract.setCustomerID(results.getString("customerID"));
                contract.setInsuranceID(results.getString("insuranceID"));
                contract.setInsurancePeriod(results.getString("insurancePeriod"));
                contract.setPremium(results.getInt("premium"));
                contract.setPaymentCycle(results.getString("paymentCycle"));
                contract.setMaxCompensation(results.getInt("maxCompensation"));
                String dateString = results.getString("dateOfSubscription");
                LocalDate dateOfSubscription = LocalDate.parse(dateString);
                contract.setDateOfSubscription(dateOfSubscription);
                String dateStringMaturity = results.getString("dateOfMaturity");
                LocalDate dateOfMaturity = LocalDate.parse(dateStringMaturity);
                contract.setDateOfMaturity(dateOfMaturity);
                contract.setMaturity(results.getBoolean("maturity"));
                contract.setResurrection(results.getBoolean("resurrection"));
                contract.setCancellation(results.getBoolean("cancellation"));

                contractList.add(contract);
            }
        } catch (SQLException e) {
            throw new DaoException("모든 Contract 조회 중에 오류가 발생했습니다.", "retrieveAll");
        }

        return contractList;
    }
}
