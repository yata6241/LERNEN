package aaas;

import java.io.IOException;

import fabrik.ConcreteCreator;
import fabrik.Creator;
import fabrik.Product;

public class s {
	
	public abstract class Product {
		public abstract void fuegeInDateiHinzu(Object object) throws IOException;
		public abstract void schliesseDatei() throws IOException;
	}
	public abstract class Creator {
		public abstract Product factoryMethod() throws IOException;
	}
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
	public class ConcreteCreator extends Creator {
		@Override
		public Product factoryMethod() throws IOException {
			return new ConcreteProduct();
		}
	}
	
	
	//in model
	public void schreibeSportvereineInCsvDatei() throws IOException {
		Creator creator = new ConcreteCreator();
		Product writer = creator.factoryMethod();
		writer.fuegeInDateiHinzu(this.sportverein);
		writer.schliesseDatei();
	}
	
	public class ConcreteProductB extends Product {
		private BufferedWriter aus;

		public ConcreteProductB() throws IOException {
			this.aus = new BufferedWriter(new FileWriter("SportvereineAusgabe.txt", true));
		}

		@Override
		public void fuegeInDateiHinzu(Object object) throws IOException {
			Sportverein s = (Sportverein) object;
			this.aus.write("Daten des Sportvereins\n");
			this.aus.write(String.format("%-40s%s%n", "Name des Sportvereins:", s.getName()));
			this.aus.write(String.format("%-40s%s%n", "Ort des Sportvereins:", s.getOrt()));
			this.aus.write(String.format("%-40s%d%n", "Anzahl Mitglieder:", s.getAnzahlMitglieder()));
			this.aus.write(String.format("%-40s%d%n", "Anzahl ehemaliger Mitglieder:", s.getAnzahlEhemaligerMitglieder()));
			this.aus.write(String.format("%-40s%s%n", "Sportarten:", s.getSportartenAlsString(',')));
			this.aus.write("\n");
		}

		@Override
		public void schliesseDatei() throws IOException {
			this.aus.close();
		}
		
		ublic class ConcreteCreatorB extends Creator {
			@Override
			public Product factoryMethod() throws IOException {
				return new ConcreteProductB();
			}
		}
		
		//inmodel 
		public void schreibeSportvereineInTxtDatei() throws IOException {
		Creator creator = new ConcreteCreatorB();
		Product writer = creator.factoryMethod();
		writer.fuegeInDateiHinzu(this.sportverein);
		writer.schliesseDatei();
	}
		
	// inj Control
		
		public void schreibeSportvereineInTxtDatei() {
			try {
				atModel.schreibeSportvereineInTxtDatei();
			} catch (IOException exc) {
				atView.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
			} catch (Exception exc) {
				atView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
			}
		}
		
		/7 in view // Attribute:
		private MenuItem mnItmTxtExport = new MenuItem("txt-Export");

		// in initKomponenten(), direkt nach mnItmCsvExport:
		this.mnDatei.getItems().add(mnItmTxtExport);

		// in initListener():
		mnItmTxtExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				atControl.schreibeSportvereineInTxtDatei();
			}
		});
	
		
		
		
		variante B
		
		
		
	
		public class ConcreteCreatorAB extends Creator {
			@Override
			public Product factoryMethod(String typ) throws IOException {
				if ("csv".equals(typ)) {
					return new ConcreteProductA();
				} else if ("txt".equals(typ)) {
					return new ConcreteProductB();
				} else {
					throw new IllegalArgumentException("Unbekannter Dateityp: " + typ);
				}
			}
		}
		
		IN MODEL 
		public void schreibeSportvereineInDatei(String typ) throws IOException {
			Creator creator = new ConcreteCreatorAB();
			Product writer = creator.factoryMethod(typ);
			writer.fuegeInDateiHinzu(this.sportverein);
			writer.schliesseDatei();
		}
		
		
		CONTROL 
		public void schreibeSportvereineInDatei(String typ) {
			try {
				atModel.schreibeSportvereineInDatei(typ);
			} catch (IOException exc) {
				atView.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
			} catch (Exception exc) {
				atView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
			}
		}
}
