package sitprojekat.model;

public class Proizvod {

	private long id;
	private String naziv;
	private String opis;
	private double cena;
	public Proizvod(long id, String naziv, String opis, double cena) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + "]";
	}
	
	
}
