package da.factory;

public abstract class DAOFactory {

	public static int  LocalServer =1;
	public static int  InternetServer = 2;
	
	public static DAOFactory obtenerFactory(int origen){
		
		switch (origen) {
		case 1: return new LocalDaoFactory();
			
		case 2: 

		default:
			return null;
		}
		
		
	}
	
	
}
