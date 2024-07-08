package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the bids database table.
 * 
 */
@Entity
@Table(name="bids")
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBid;

	@Temporal(TemporalType.DATE)
	@Column(name="bid_time")
	private Date bidTime;

	@Column(name="bid_value")
	private float bidValue;

	//bi-directional many-to-one association to Buyer
	@ManyToOne
	@JsonManagedReference
	private Buyer buyer;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	private Ticket ticket;

	//bi-directional many-to-one association to Currentbid
	@OneToMany(mappedBy="bid")
	@JsonBackReference
	private List<Currentbid> currentbids;

	public Bid() {
	}

	public int getIdBid() {
		return this.idBid;
	}

	public void setIdBid(int idBid) {
		this.idBid = idBid;
	}

	public Date getBidTime() {
		return this.bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public float getBidValue() {
		return this.bidValue;
	}

	public void setBidValue(float bidValue) {
		this.bidValue = bidValue;
	}

	public Buyer getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Currentbid> getCurrentbids() {
		return this.currentbids;
	}

	public void setCurrentbids(List<Currentbid> currentbids) {
		this.currentbids = currentbids;
	}

	public Currentbid addCurrentbid(Currentbid currentbid) {
		getCurrentbids().add(currentbid);
		currentbid.setBid(this);

		return currentbid;
	}

	public Currentbid removeCurrentbid(Currentbid currentbid) {
		getCurrentbids().remove(currentbid);
		currentbid.setBid(null);

		return currentbid;
	}

}