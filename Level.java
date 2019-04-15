import java.util.*;

public class Level {
	Collection<Sprite> elem = new ArrayList<Sprite>();
	int length;
	int curWid;
	int edgeWid;
	Player p;
	
	Level(){
		length = 1000;
		curWid = 20;//start at width 1-20, run through 500 lenght of level like an array
		edgeWid = 0;
		p = new Player();
		elem.add(p);
		for(int i=0; i<100;i++) {
			elem.add(new Obstacle());
		}
		for(int i=0; i<100;i++) {
			elem.add(new Food());
		}
		for(int i=0; i<25;i++) {
			elem.add(new NestPiece());
		}
	}
	
	public String toString() {
		String r="";
		for(int i = 0; i<20;i++)
			r+= " *";

		r+="\n";
		if(this.p.alive) {
			Lane currLane = Lane.Top;			
			for(int j = 0;j<3;j++) {
				for(int i =edgeWid;i<curWid;i++) {
					r+=".";
					boolean fill = false;
					for(Sprite e:elem) {
						if (i==e.getXloc() && e.getLane().equals(currLane)) { 
							r+=e.toString();
							fill = true;
						}
						
					}
					if(!fill) r+=" ";
				}
				r+= "\n";
				if(j == 0) currLane = Lane.Mid;
				else currLane = Lane.Bottom;
			}
			
		}
		else {
			r+= p.currQ.toString();
		}
		p.update(elem);
		r+="\n";
		for(int i = 0; i<20;i++)
			r+= " *";
		r+= p.Stats();
		return r;
	}
	public void update() {
		if(p.alive) {
			p.xloc +=1;
			edgeWid +=1;
			curWid +=1;
		}
	}
	public void reborn() {
		this.p.setInf();
		this.p.setAlive(true);
		this.p.setHealth(50);
		this.p.self = 'O';
		
	}
}
