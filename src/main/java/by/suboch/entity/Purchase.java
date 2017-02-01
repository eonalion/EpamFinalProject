package by.suboch.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
public class Purchase  implements IDatabaseEntity {
    private int purchaseId;
    private int accountId;
    private List<Integer> tracksId;
    private LocalDateTime date;
    private double totalPrice;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<Integer> getTracksId() {
        return tracksId;
    }

    public void setTracksId(List<Integer> tracksId) {
        this.tracksId = tracksId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
