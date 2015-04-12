package br.com.inpe.interactivedatavisualization.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
	/*
	// protected ConfigProvider config;
	// private RepositoryManager reposManager;
	// private ContextAwareConnection reposConnection;
	// private SesameDataset dataset;
	private ArrayList<Literal> defora2008 = new ArrayList<Literal>();
	private ArrayList<Literal> border = new ArrayList<Literal>();
	private ArrayList<Literal> label = new ArrayList<Literal>();
	private ArrayList<Literal> pop0 = new ArrayList<Literal>();
	private ArrayList<Literal> pop1 = new ArrayList<Literal>();
	private ArrayList<Literal> pop2 = new ArrayList<Literal>();
	private ArrayList<Literal> pop3 = new ArrayList<Literal>();
	private ArrayList<Literal> pop4 = new ArrayList<Literal>();
	private ArrayList<Literal> pop5 = new ArrayList<Literal>();
	private ArrayList<Literal> pop6 = new ArrayList<Literal>();
	private ArrayList<Literal> pop7 = new ArrayList<Literal>();
	private ArrayList<Literal> pop8 = new ArrayList<Literal>();
	private ArrayList<Literal> pop9 = new ArrayList<Literal>();
//	private ArrayList<Resource> cells = new ArrayList<Resource>();
//	private ArrayList<Resource> munis = new ArrayList<Resource>();
	private ArrayList<MunicipalityDataItem> muniData = new ArrayList<MunicipalityDataItem>();
	private ArrayList<MesoRegion> mesoRegions = new ArrayList<MesoRegion>();
	private ArrayList<MicroRegion> microRegions = new ArrayList<MicroRegion>();

	public DataRetriever() {
		// this.config=new ConfigProvider();
		// reposManager = new
		// RemoteRepositoryManager(this.config.getProperty("repositoryURL"));
		// reposManager.initialize();
		//
		// try {
		// this.reposConnection=new
		// ContextAwareConnection(reposManager.getRepository(config.getProperty("repositoryID")));
		// } catch (RepositoryException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// dataset = new SesameDataset(this.reposConnection);
		// reposConnection.close();
	}

	public ResultSet queryForStates() {

		Model resultModel = ModelFactory.createDefaultModel();

		Model model = ModelFactory.createDefaultModel();
		File dir = new File("amazonData/");

		File[] fileList = dir.listFiles();
		for (File f : fileList) {
			InputStream in = null;
			try {
				if(f.getAbsolutePath().contains(".svn")){
					
				}
				else{
						System.out.println(f);

				in = new FileInputStream(f);
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(in != null)
			model.read(in, null, "TURTLE");
		}

//		String sparqlQuery = " SELECT ?a ?b ?c ?d WHERE { <http://spatial.linkedscience.org/context/brazilian-town/BRAZIL_MUNICIPALITY_150160> ?a ?b  } ";
//		// model.write(System.out);
//		String sparqlQueryString1 = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
//				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
//				+ "prefix lsv: <http://linkedscience.org/lsv/ns#> "
//				+ "prefix amazon: <http://spatial.linkedscience.org/context/amazon/> "
//				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
//				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
//				+ "  SELECT ?muni ?muni2 ?name ?border ?pop09 ?pop08 ?pop07 ?pop06 ?pop05 ?pop04 ?pop03 ?pop02 ?pop01 ?pop00 WHERE {"
//				+ "       ?muni rdf:type <http://dbpedia.org/resource/Municipalities_of_Brazil> . "
//				+ "        ?muni lsv:border ?border  . "
//				+ "       ?muni foaf:name ?name . "
//				+ "       ?muni2 owl:sameAs ?muni . "
//				+ "       ?muni2 	amazon:population_2009 	?pop09 . "
//				+ "       ?muni2 	amazon:population_2008 	?pop08 . "
//				+ "       ?muni2 	amazon:population_2007 	?pop07 . "
//				+ "       ?muni2 	amazon:population_2006 	?pop06 . "
//				+ "       ?muni2 	amazon:population_2005 	?pop05 . "
//				+ "       ?muni2 	amazon:population_2004 	?pop04 . "
//				+ "       ?muni2 	amazon:population_2003 	?pop03 . "
//				+ "       ?muni2 	amazon:population_2002 	?pop02 . "
//				+ "       ?muni2 	amazon:population_2001 	?pop01 . "
//				+ "       ?muni2 	amazon:population_2000 	?pop00 . "
//				+ "       ?muni <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within>	<http://www.dbpedia.org/resource/Para_State>. "
//				+ "        }";

		String sparqlQueryString2 = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix lsv: <http://linkedscience.org/lsv/ns#> "
				+ "prefix amazon: <http://spatial.linkedscience.org/context/amazon/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "prefix br-town: <http://spatial.linkedscience.org/context/brazilian-town/> " 
				+ "prefix tisc: <http://observedchange.com/tisc/ns#>  " 

				+ "  SELECT DISTINCT ?muni ?cell ?border" +
				" ?pop00 ?pop01 ?pop02 ?pop03 ?pop04 ?pop05 ?pop06 ?pop07 ?pop08 ?pop09" +
				" ?defor2002 ?defor2003 ?defor2003 ?defor2004 ?defor2005 ?defor2006 ?defor2007 ?defor2008" +
				" ?overlapArea ?overlapPc " +
				"?acum2002 ?acum2007 ?acum2008 ?past06 " +
				"?soja2004 ?soja2005 ?soja2006 ?soja2007 ?soja2008 " +
				"?cattle2004 ?cattle2005 ?cattle2006 ?cattle2007 ?cattle2008 " +
				" ?permAgr06 ?tempAgr06 ?mesoName ?microName ?mesoBorder ?microBorder " +
				" ?gdp2004 ?gdp2005 ?gdp2006 ?gdp2007 ?gdp2008 ?gdpAgr2004 ?gdpAgr2005 ?gdpAgr2006 ?gdpAgr2007 ?gdpAgr2008 ?name ?centroid WHERE {"
				+ "       ?muni rdf:type <http://dbpedia.org/resource/Municipalities_of_Brazil> . "
				+ "        ?muni	tisc:geometry  ?border  . "
				+ "       ?muni foaf:name ?name . "
				+ "       ?muni amazon:hasCentroid ?centroid . "
				+ "       ?muni2 owl:sameAs ?muni . "
				+ "       ?muni2 	amazon:population_2009 	?pop09 . "
				+ "       ?muni2 	amazon:population_2008 	?pop08 . "
				+ "       ?muni2 	amazon:population_2007 	?pop07 . "
				+ "       ?muni2 	amazon:population_2006 	?pop06 . "
				+ "       ?muni2 	amazon:population_2005 	?pop05 . "
				+ "       ?muni2 	amazon:population_2004 	?pop04 . "
				+ "       ?muni2 	amazon:population_2003 	?pop03 . "
				+ "       ?muni2 	amazon:population_2002 	?pop02 . "
				+ "       ?muni2 	amazon:population_2001 	?pop01 . "
				+ "       ?muni2 	amazon:population_2000 	?pop00 . "
				+ "       ?muni <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within>	<http://www.dbpedia.org/resource/Para_State>. "
	//			+ "       ?cell 	amazon:hasItsCentroidInsideOf 	?muni . "
				+ "		?cell amazon:DEFOR_2002 ?defor2002 ."
				+ "		?cell amazon:DEFOR_2003 ?defor2003 ."
				+ "		?cell amazon:DEFOR_2004 ?defor2004 ."
				+ "		?cell amazon:DEFOR_2005 ?defor2005 ."
				+ "		?cell amazon:DEFOR_2006 ?defor2006 ."
				+ "		?cell amazon:DEFOR_2007 ?defor2007 ."
				+ "		?cell amazon:DEFOR_2008 ?defor2008 ." 
				+ "		?a amazon:partiallyOverlapsFrom ?cell ." 
				+ "		?a amazon:partiallyOverlapsTo ?muni ." 
				+ "		?a amazon:overlapArea ?overlapArea ." 
				+ "		?a amazon:cellOverlapRatio ?overlapPc ." 
				+ "     ?cell amazon:ACUM_2002 ?acum2002 . "
				+ "     ?cell amazon:ACUM_2007 ?acum2007 . "
				+ "     ?cell amazon:ACUM_2008 ?acum2008 . "
				+ "     ?cell amazon:CENSO06_PASTAGEM ?past06 . "
				+ "     ?cell amazon:CENSO06_PERMANENTE ?permAgr06 . "
				+ "     ?cell amazon:CENSO06_TEMPORARIA ?tempAgr06 . "
				
				+ "     ?muni2 br-town:soja_produced_2004 ?soja2004  . "		
				+ "     ?muni2 br-town:soja_produced_2005 ?soja2005  . "		
				+ "     ?muni2 br-town:soja_produced_2006 ?soja2006  . "		
				+ "     ?muni2 br-town:soja_produced_2007 ?soja2007  . "		
				+ "     ?muni2 br-town:soja_produced_2008 ?soja2008  . "		

				+ "     ?muni2 br-town:cattle_inventory_2004 ?cattle2004  . "		
				+ "     ?muni2 br-town:cattle_inventory_2005 ?cattle2005  . "		
				+ "     ?muni2 br-town:cattle_inventory_2006 ?cattle2006  . "		
				+ "     ?muni2 br-town:cattle_inventory_2007 ?cattle2007  . "		
				+ "     ?muni2 br-town:cattle_inventory_2008 ?cattle2008  . "
				
				+ "     ?muni2 amazon:GDP_current_prices_2004 ?gdp2004  . "		
				+ "     ?muni2 amazon:GDP_current_prices_2005 ?gdp2005  . "								
				+ "     ?muni2 amazon:GDP_current_prices_2006 ?gdp2006  . "								
				+ "     ?muni2 amazon:GDP_current_prices_2007 ?gdp2007  . "		
				+ "     ?muni2 amazon:GDP_current_prices_2008 ?gdp2008  . "		
				
				+ "     ?muni2 amazon:GDP_agriculture_2004 ?gdpAgr2004  . "		
				+ "     ?muni2 amazon:GDP_agriculture_2005 ?gdpAgr2005  . "								
				+ "     ?muni2 amazon:GDP_agriculture_2006 ?gdpAgr2006  . "								
				+ "     ?muni2 amazon:GDP_agriculture_2007 ?gdpAgr2007  . "		
				+ "     ?muni2 amazon:GDP_agriculture_2008 ?gdpAgr2008  . "		
				
				+ "     ?muni <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within> ?micro  . "		
				+ "     ?micro foaf:name ?microName  . "		
				+ "     ?micro <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within> ?meso  . "		
				+ "     ?meso foaf:name ?mesoName  . "		
				+ "     ?meso lsv:border ?mesoBorder  . "	
				+ "     ?micro lsv:border ?microBorder  . "	

				+"        }";

		String deforValueQuery = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix lsv: <http://linkedscience.org/lsv/ns#> "
				+ "prefix amazon: <http://spatial.linkedscience.org/context/amazon/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "  SELECT ?muni ?micro ?meso ?microName ?mesoName  WHERE {"
				+ "       ?muni rdf:type <http://dbpedia.org/resource/Municipalities_of_Brazil> . "
				+ "       ?muni <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within>	?micro. "
				+ "     ?micro foaf:name ?microName  . "		
				+ "     ?micro <http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within> ?meso  . "		
				+ "     ?meso foaf:name ?mesoName  . "		
//				+ "     ?meso lsv:border ?mesoBorder  . "	
				+ "     ?micro lsv:border ?microBorder  . "	


				+"        }";

		// System.out.println(sparqlQueryString1);
		Query query = QueryFactory.create(sparqlQueryString2);

		ARQ.getContext().setTrue(ARQ.useSAX);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		ResultSet results = qexec.execSelect();
//		 ResultSetFormatter.out(System.out, results, query);
		int i = 0;
		boolean muniAvailable = false;
		while (results.hasNext()) {
			muniAvailable = false;
			QuerySolution soln = results.nextSolution();
			
			
			for(int j=0; j<muniData.size();j++){
				if(muniData.get(j).getUri().equals(soln.getResource("muni").getURI())){
					muniAvailable = true;
					i = j;
				}
			}
			
			if(muniAvailable == false){
				muniData.add(new MunicipalityDataItem(soln.getResource("muni").getURI(),
						soln.getResource("cell").getURI(), 
						soln.getLiteral("defor2002").getDouble(),
						soln.getLiteral("defor2003").getDouble(),
						soln.getLiteral("defor2004").getDouble(),
						soln.getLiteral("defor2005").getDouble(),
						soln.getLiteral("defor2006").getDouble(),
						soln.getLiteral("defor2007").getDouble(),
						soln.getLiteral("defor2008").getDouble(),
						soln.getLiteral("border").getString(),
						soln.getLiteral("overlapArea").getDouble(),
						soln.getLiteral("overlapPc").getDouble(), 
						soln.getLiteral("pop00").getDouble(),
						soln.getLiteral("pop01").getDouble(),
						soln.getLiteral("pop02").getDouble(),
						soln.getLiteral("pop03").getDouble(),
						soln.getLiteral("pop04").getDouble(),
						soln.getLiteral("pop05").getDouble(),
						soln.getLiteral("pop06").getDouble(),
						soln.getLiteral("pop07").getDouble(),
						soln.getLiteral("pop08").getDouble(),
						soln.getLiteral("pop09").getDouble(),
						soln.getLiteral("acum2002").getDouble(),
						soln.getLiteral("acum2007").getDouble(),
						soln.getLiteral("acum2008").getDouble(),
						soln.getLiteral("past06").getDouble(),
						soln.getLiteral("soja2004").getDouble(),
						soln.getLiteral("soja2005").getDouble(),
						soln.getLiteral("soja2006").getDouble(),
						soln.getLiteral("soja2007").getDouble(),
						soln.getLiteral("soja2008").getDouble(),

						soln.getLiteral("cattle2004").getDouble(),
						soln.getLiteral("cattle2005").getDouble(),
						soln.getLiteral("cattle2006").getDouble(),
						soln.getLiteral("cattle2007").getDouble(),
						soln.getLiteral("cattle2008").getDouble(),
						
						soln.getLiteral("gdp2004").getDouble(),
						soln.getLiteral("gdp2005").getDouble(),
						soln.getLiteral("gdp2006").getDouble(),
						soln.getLiteral("gdp2007").getDouble(),
						soln.getLiteral("gdp2008").getDouble(),

						soln.getLiteral("tempAgr06").getDouble(),
						soln.getLiteral("permAgr06").getDouble(),
						soln.getLiteral("mesoName").getString(),
						soln.getLiteral("microName").getString(),
						soln.getLiteral("mesoBorder").getString(),
						soln.getLiteral("microBorder").getString(),
						soln.getLiteral("name").getString(),
						soln.getLiteral("centroid").getString()
						));
			}
			else{
				muniData.get(i).addEntry(soln.getResource("cell").getURI(),
						soln.getLiteral("defor2002").getDouble(), 
						soln.getLiteral("defor2003").getDouble(),
						soln.getLiteral("defor2004").getDouble(),
						soln.getLiteral("defor2005").getDouble(),
						soln.getLiteral("defor2006").getDouble(),
						soln.getLiteral("defor2007").getDouble(),
						soln.getLiteral("defor2008").getDouble(), 
						soln.getLiteral("overlapArea").getDouble(),
						soln.getLiteral("overlapPc").getDouble(),
						soln.getLiteral("acum2002").getDouble(),
						soln.getLiteral("acum2007").getDouble(),
						soln.getLiteral("acum2008").getDouble(),
						soln.getLiteral("past06").getDouble(),
						soln.getLiteral("soja2004").getDouble(),
						soln.getLiteral("tempAgr06").getDouble(),
						soln.getLiteral("permAgr06").getDouble()
						);
			}
			
//			i++;
//			// System.out.println("HIER");
//			QuerySolution soln = results.nextSolution();
//			pop0.add(soln.getLiteral("defor2008"));
//			pop1.add(soln.getLiteral("defor2007"));
//			pop2.add(soln.getLiteral("defor2006"));
//			pop3.add(soln.getLiteral("defor2005"));
//			pop4.add(soln.getLiteral("defor2004"));
//			pop5.add(soln.getLiteral("defor2003"));
//			pop6.add(soln.getLiteral("defor2002"));
//			munis.add(soln.getResource("muni"));
//			// pop8.add(soln.getLiteral("pop08"));
//			// pop9.add(soln.getLiteral("pop09"));
//
//			// border.add(soln.getLiteral("border"));
//			// label.add(soln.getLiteral("name"));
//			//
			// System.out.println(soln.get("?defor2008"));

			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/aggregatedData"),
			// "true");
			//
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2002"),
			// soln.getLiteral("defor2002"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2003"),
			// soln.getLiteral("defor2003"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2004"),
			// soln.getLiteral("defor2004"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2005"),
			// soln.getLiteral("defor2005"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2006"),
			// soln.getLiteral("defor2006"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2007"),
			// soln.getLiteral("defor2007"));
			// resultModel.add(soln.getResource("muni"),
			// resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/defor2008"),
			// soln.getLiteral("defor2008"));

		}

		// ResIterator munisRes =
		// resultModel.listResourcesWithProperty(resultModel.createProperty("http://spatial.linkedscience.org/context/amazon/aggregatedData"));
		// List<Resource> muniList = munisRes.toList();
		// for(Resource muniResource : muniList){
		//
		// }

		qexec.close();
		orderMuniInMicroMeso();
System.out.println("MUNI SIZE: "+muniData.size() + " " + mesoRegions.size() +" "+ microRegions.size());
		return results;
	}

	public void orderMuniInMicroMeso(){
		int mesoNr = 0;
		int microNr = 0;
		boolean mesoAvailable = false;
		boolean microAvailable = false;
		for(MunicipalityDataItem muniDataItem: muniData){
			
			
			mesoAvailable = false;
			microAvailable = false;
			
			for(int j=0; j<mesoRegions.size();j++){
				if(mesoRegions.get(j).getName().equals(muniDataItem.getMesoRegion())){
					mesoAvailable = true;
					mesoNr = j;
				}
			}
			
			for(int j=0; j<microRegions.size();j++){
				if(microRegions.get(j).getName().equals(muniDataItem.getMicroRegion())){
					microAvailable = true;
					microNr = j;
				}
			}
			if(mesoAvailable == false){
				mesoRegions.add(new MesoRegion(muniDataItem.getMesoBorder(), muniDataItem.getMesoRegion(), new MicroRegion(muniDataItem.getMicroBorder(), muniDataItem.getMicroRegion(), muniDataItem)));
				microRegions.add(new MicroRegion(muniDataItem.getMicroBorder(), muniDataItem.getMicroRegion(), muniDataItem));

			}
			
			else if (microAvailable == false){
				mesoRegions.get(mesoNr).getMicoRegions().add(new MicroRegion(muniDataItem.getMicroBorder(), muniDataItem.getMicroRegion(), muniDataItem));
				microRegions.add(new MicroRegion(muniDataItem.getMicroBorder(), muniDataItem.getMicroRegion(), muniDataItem));
			}
			else {
				microRegions.get(microNr).getMuniItems().add(muniDataItem);
			}
			
		}
	}
	
	public ResultSet queryEndpoint() {

		String sparqlQueryString1 = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix lsv: <http://linkedscience.org/lsv/ns#> "
				+ "prefix amazon: <http://spatial.linkedscience.org/context/amazon/> "
				+ "  SELECT ?a ?label ?border ?defor2008 WHERE {"
				+ "       ?a rdf:type lsv:Item . "
				+ "        ?a lsv:border ?border  . "
				+ "       ?a rdfs:label ?label . "
				+ "       ?a amazon:DEFOR_2008 ?defor2008 . " + "        }";

		System.out.println(sparqlQueryString1);
		Query query = QueryFactory.create(sparqlQueryString1);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://spatial.linkedscience.org/sparql", query);

		ResultSet results = qexec.execSelect();
		// ResultSetFormatter.out(System.out, results, query);
		int i = 0;
		while (results.hasNext() && i < 1000) {
			i++;
			// System.out.println("HIER");
			QuerySolution soln = results.nextSolution();
			defora2008.add(soln.getLiteral("defor2008"));
			border.add(soln.getLiteral("border"));
			label.add(soln.getLiteral("label"));
			// System.out.println(soln.get("?defor2008"));

		}

		qexec.close();

		return results;
	}

	public ArrayList<Literal> getDefora2008() {
		return defora2008;
	}

	public void setDefora2008(ArrayList<Literal> defora2008) {
		this.defora2008 = defora2008;
	}

	public ArrayList<Literal> getBorder() {
		return border;
	}

	public void setBorder(ArrayList<Literal> border) {
		this.border = border;
	}

	public ArrayList<Literal> getLabel() {
		return label;
	}

	public void setLabel(ArrayList<Literal> label) {
		this.label = label;
	}

	public ArrayList<Literal> getPop0() {
		return pop0;
	}

	public void setPop0(ArrayList<Literal> pop0) {
		this.pop0 = pop0;
	}

	public ArrayList<Literal> getPop1() {
		return pop1;
	}

	public void setPop1(ArrayList<Literal> pop1) {
		this.pop1 = pop1;
	}

	public ArrayList<Literal> getPop2() {
		return pop2;
	}

	public void setPop2(ArrayList<Literal> pop2) {
		this.pop2 = pop2;
	}

	public ArrayList<Literal> getPop3() {
		return pop3;
	}

	public void setPop3(ArrayList<Literal> pop3) {
		this.pop3 = pop3;
	}

	public ArrayList<Literal> getPop4() {
		return pop4;
	}

	public void setPop4(ArrayList<Literal> pop4) {
		this.pop4 = pop4;
	}

	public ArrayList<Literal> getPop5() {
		return pop5;
	}

	public void setPop5(ArrayList<Literal> pop5) {
		this.pop5 = pop5;
	}

	public ArrayList<Literal> getPop6() {
		return pop6;
	}

	public void setPop6(ArrayList<Literal> pop6) {
		this.pop6 = pop6;
	}

	public ArrayList<Literal> getPop7() {
		return pop7;
	}

	public void setPop7(ArrayList<Literal> pop7) {
		this.pop7 = pop7;
	}

	public ArrayList<Literal> getPop8() {
		return pop8;
	}

	public void setPop8(ArrayList<Literal> pop8) {
		this.pop8 = pop8;
	}

	public ArrayList<Literal> getPop9() {
		return pop9;
	}

	public void setPop9(ArrayList<Literal> pop9) {
		this.pop9 = pop9;
	}
	
	

	public ArrayList<MunicipalityDataItem> getMuniData() {
		return muniData;
	}

	public void setMuniData(ArrayList<MunicipalityDataItem> muniData) {
		this.muniData = muniData;
	}

	
	public ArrayList<MesoRegion> getMesoRegions() {
		return mesoRegions;
	}

	public void setMesoRegions(ArrayList<MesoRegion> mesoRegions) {
		this.mesoRegions = mesoRegions;
	}

	public ArrayList<MicroRegion> getMicroRegions() {
		return microRegions;
	}

	public void setMicroRegions(ArrayList<MicroRegion> microRegions) {
		this.microRegions = microRegions;
	}

	public static void main(String[] args) {
		DataRetriever dataR = new DataRetriever();
		dataR.queryForStates();
		// dataR.queryEndpoint();
		// AmazonTest ama = new AmazonTest(dataR.getBorder(), dataR.getLabel(),
		// dataR.getPop0(), dataR.getPop1(), dataR.getPop2(), dataR.getPop3(),
		// dataR.getPop4(), dataR.getPop5(), dataR.getPop6(), dataR.getPop7(),
		// dataR.getPop8(), dataR.getPop9());
		// System.out.println(dataR.getDefora2008().size());
		// ama.start("World Wind Extruded Polygons", AppFrame.class);
		// ResultSet results = dataR.queryEndpoint();

	}
	*/
}
