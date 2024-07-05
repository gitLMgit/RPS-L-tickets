package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the seat_in_hall database table.
 * 
 */
@Entity
@Table(name="seat_in_hall")
@NamedQuery(name="SeatInHall.findAll", query="SELECT s FROM SeatInHall s")
public class SeatInHall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSeatHall;

	//bi-directional many-to-one association to Hall
	@ManyToOne
	private Hall hall;

	//bi-directional many-to-one association to Seat
	@ManyToOne
	private Seat seat;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="seatInHall")
	private List<Ticket> tickets;

	public SeatInHall() {
	}

	public int getIdSeatHall() {
		return this.idSeatHall;
	}

	public void setIdSeatHall(int idSeatHall) {
		this.idSeatHall = idSeatHall;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setSeatInHall(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setSeatInHall(null);

		return ticket;
	}

}