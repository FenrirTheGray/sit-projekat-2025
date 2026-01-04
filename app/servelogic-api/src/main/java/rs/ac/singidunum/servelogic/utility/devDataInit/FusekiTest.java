package rs.ac.singidunum.servelogic.utility.devDataInit;

import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

public class FusekiTest {
	public static void init() {

        FusekiDBUtility db = new FusekiDBUtility();

        db.update("INSERT DATA { <http://test/s> <http://test/p> 'Hello World' }");
        
        db.select("SELECT ?o WHERE { ?s ?p ?o }", row -> {
            System.out.println("Result: " + row.get("o"));
        });
    }
}
