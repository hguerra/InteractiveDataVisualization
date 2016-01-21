package test.app;

import java.util.List;

import br.inpe.util.GenericFactory;
import br.inpe.worldwind.dao.GeometryRecord;
import br.inpe.worldwind.dao.JDBCDao;
import br.inpe.worldwind.dao.model.vegtype_2000;

public class JavaTest {
	public static void main(String[] args) {
//		System.out.println(GenericFactory.getSimpleName(GeometryRecord.class));
//		
//		GeometryRecord g = GenericFactory.getInstance(vegtype_2000.class);
//		
//		g.setDisplayName("Test getInstance");
//		
//		System.out.println(g);
//		
//		System.out.println(g.getDisplayName());
		
		JDBCDao<vegtype_2000> dao = new JDBCDao<>();
		
		List<vegtype_2000> all = dao.getAll(vegtype_2000.class);
		
		for(vegtype_2000 v: all){
			System.out.println(v.getDisplayName());
		}
	}
	
}
