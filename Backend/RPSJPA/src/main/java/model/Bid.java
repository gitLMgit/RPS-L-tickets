package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


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
	private Buyer buyer;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	private Ticket ticket;

	//bi-directional many-to-one association to CurrentBid
	@OneToMany(mappedBy="bid")
	private List<CurrentBid> currentbids;

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

	public List<CurrentBid> getCurrentbids() {
		return this.currentbids;
	}

	public void setCurrentbids(List<CurrentBid> currentbids) {
		this.currentbids = currentbids;
	}

	public CurrentBid addCurrentbid(CurrentBid currentbid) {
		getCurrentbids().add(currentbid);
		currentbid.setBid(this);

		return currentbid;
	}

	public CurrentBid removeCurrentbid(CurrentBid currentbid) {
		getCurrentbids().remove(currentbid);
		currentbid.setBid(null);

		return currentbid;
	}

}