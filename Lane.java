package birdyRun;

public enum Lane {  // lane enum to dictate position on screen
	Top("top"),
	Mid("mid"),
	Bottom("bottom");
	
	private String name = null;
	
	private Lane(String l){
		name = l;
	}
	public String getName() {
		return name;
	}

}
