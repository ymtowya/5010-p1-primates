package housing;

public class MonkeySanctuary {
	
	private static int uniqueIdSeries = 1;

	private static int getUniqueId() {
		return MonkeySanctuary.uniqueIdSeries++;
	}
	private final int id;
	
	public MonkeySanctuary() {
		// TODO Auto-generated constructor stub
		this.id = getUniqueId();
	}
	
	

}
