package fabrik;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Sportverein;

public class ConcreteProduct extends Product {
	private BufferedWriter aus;

	public ConcreteProduct() throws IOException {
		this.aus = new BufferedWriter(new FileWriter("SportvereineAusgabe.csv", true));
	}

	@Override
	public void fuegeInDateiHinzu(Object object) throws IOException {
		Sportverein sportverein = (Sportverein) object;
		this.aus.write(sportverein.gibSportvereinZurueck(';'));
	}

	@Override
	public void schliesseDatei() throws IOException {
		this.aus.close();
	}
}