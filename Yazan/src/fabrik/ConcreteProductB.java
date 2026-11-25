package fabrik;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Sportverein;

public class ConcreteProductB extends Product {
	private BufferedWriter aus;

	public ConcreteProductB() throws IOException {
		this.aus = new BufferedWriter(new FileWriter("SportvereineAusgabe.txt", true));
	}

	@Override
	public void fuegeInDateiHinzu(Object object) throws IOException {
		Sportverein s = (Sportverein) object;
		this.aus.write("Daten des Sportvereins\n");
		this.aus.write("Name des Sportverins: "+ s.getName()+ "\n"); 
		this.aus.write("Ort des Sportverins: " + s.getOrt()+ "\n");
		this.aus.write("Anzahl Mitgleider " + s.getAnzahlMitglieder()+ "\n");
		this.aus.write("Anzahl ehemitglieder: " + s.getAnzahlEhemaligerMitglieder()+ "\n");
		this.aus.write("Sportarten: " + s.getSportartenAlsString(',') + "\n");
		this.aus.write("\n");
		
	}

	@Override
	public void schliesseDatei() throws IOException {
		this.aus.close();
	}
}