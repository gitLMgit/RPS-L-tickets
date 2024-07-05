package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the seat database table.
 * 
 */
@Entity
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSeat;

	private int number;

	private int row;

	private String section;

	//bi-directional many-to-one association to SeatInHall
	@OneToMany(mappedBy="seat")
	private List<SeatInHall> seatInHalls;

	public Seat() {
	}

	public int getIdSeat() {
		return this.idSeat;
	}

	public void setIdSeat(int idSeat) {
		this.idSeat = idSeat;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<SeatInHall> getSeatInHalls() {
		return this.seatInHalls;
	}

	public void setSeatInHalls(List<SeatInHall> seatInHalls) {
		this.seatInHalls = seatInHalls;
	}

	public SeatInHall addSeatInHall(SeatInHall seatInHall) {
		getSeatInHalls().add(seatInHall);
		seatInHall.setSeat(this);

		return seatInHall;
	}

	public SeatInHall removeSeatInHall(SeatInHall seatInHall) {
		getSeatInHalls().remove(seatInHall);
		seatInHall.setSeat(null);

		return seatInHall;
	}

}