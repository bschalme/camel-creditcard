package ca.airspeed.camel.creditcard.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CreditCardItem {

	private Date txnDate;

	private BigDecimal txnAmount;
	
	private String description;

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
