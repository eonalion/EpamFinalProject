package by.suboch.logic;

import by.suboch.ajax.BiTuple;
import by.suboch.dao.PurchaseDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Purchase;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class PurchaseLogic {

    public LogicActionResult addPurchase(int accountId, List<Integer> tracksId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            LogicActionResult result = new LogicActionResult();
            if(tracksId.isEmpty()){
                result.setState(LogicActionResult.State.FAILURE);
                result.setResult(ActionResult.FAILURE_EMPTY_CART);
            } else {
                PurchaseDAO purchaseDAO = new PurchaseDAO(connection);
//                int purchaseId = purchaseDAO.addPurchase(accountId);
                int purchaseId = 1;
                purchaseDAO.fillPurchaseM2mTracks(purchaseId, tracksId);
                result.setState(LogicActionResult.State.SUCCESS);
                result.setResult(ActionResult.SUCCESS_PURCHASE);
            }
            return result;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while adding new purchase.", e);
        }
    }

    public List<Purchase> loadAllPurchases(int accountId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
           PurchaseDAO purchaseDAO = new PurchaseDAO(connection);
           return purchaseDAO.loadAccountPurchases(accountId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while adding new purchase.", e);
        }
    }

    public Purchase loadPurchaseById(int purchaseId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PurchaseDAO purchaseDAO = new PurchaseDAO(connection);
            return purchaseDAO.findPurchaseById(purchaseId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while adding new purchase.", e);
        }
    }
}
