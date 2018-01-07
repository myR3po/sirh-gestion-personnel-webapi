package dev.sgp.api.entite;

import javax.persistence.Embeddable;

@Embeddable
public class Banque {

	private String denomination;
	private String bic;
	private String iban;
	
	public Banque() {
		// utilisé par jpa
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
}
