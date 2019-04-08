package birdyRun;

import java.util.Comparator;

public class entityCollision implements Comparator<Entity>{ // comparator to tell if there is a collision
	@Override
	public int compare(Entity a, Entity b) {
		if (a.getXLoc() == b.getXLoc() && a.getYLoc() == b.getYLoc()) {
			return 0;
		}else {
			return -1;
		}
	}

}
