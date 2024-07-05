package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the currentbids database table.
 * 
 */
@Entity
@Table(name="currentbids")
@NamedQuery(name="CurrentBid.findAll", query="SELECT c FROM CurrentBid c")
public class CurrentBid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurrentBid;

	private float currMaxValue;

	//bi-directional many-to-one association to Bid
	@ManyToOne
	@JoinColumn(name="Bids_idBid")
	private Bid bid;

	public CurrentBid() {
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