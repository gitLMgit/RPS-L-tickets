package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the currentbids database table.
 * 
 */
@Entity
@Table(name="currentbids")
@NamedQuery(name="Currentbid.findAll", query="SELECT c FROM Currentbid c")
public class Currentbid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurrentBid;

	private float currMaxValue;

	//bi-directional many-to-one association to Bid
	@ManyToOne
	@JoinColumn(name="Bids_idBid")
	@JsonManagedReference
	private Bid bid;

	public Currentbid() {
	}

	public int getIdCurrentBid() {
		return this.idCurrentBid;
	}

	public void setIdCurrentBid(int idCurrentBid) {
		this.idCurrentBid = idCurrentBid;
	}

	public float getCurrMaxValue() {
		return this.currMaxValue;
	}

	public void setCurrMaxValue(float currMaxValue) {
		this.currMaxValue = currMaxValue;
	}

	public Bid getBid() {
		return this.bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

}