package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import fabrik.ConcreteCreator;
import fabrik.ConcreteCreatorB;
import fabrik.Creator;
import fabrik.Product;

public class SportvereinModel {
	private Sportverein sportverein;

	public Sportverein getSportverein() {
		return sportverein;
	}

	public void setSportverein(Sportverein sportverein) {
		this.sportverein = sportverein;
	}
	public void leseAusDatei(String typ) throws IOException{
    	
      			BufferedReader ein = new BufferedReader(new FileReader("Sportverein.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.sportverein = new Sportverein(zeile[0], 
      				zeile[1], 
      				Integer.parseInt(zeile[2]), 
      				Integer.parseInt(zeile[3]), 
      				zeile[4].split("_"));
      				ein.close();
      		}
	public void schreibeSportvereineInCsvDatei() throws IOException {
		Creator creator = new ConcreteCreator();
		Product writer = creator.factoryMethod();
		writer.fuegeInDateiHinzu(this.sportverein);
		writer.schliesseDatei();
	}
	public void schreibeSportvereineInTxtDatei() throws IOException {
		Creator creator = new ConcreteCreatorB();
		Product writer = creator.factoryMethod();
		writer.fuegeInDateiHinzu(this.sportverein);
		writer.schliesseDatei();
	}
}
