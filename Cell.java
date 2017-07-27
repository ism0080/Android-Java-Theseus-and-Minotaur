package nz.ac.ara.macklei.TAMdemo;

import java.util.HashMap;

public class Cell {
	private HashMap<String, Object> things = new HashMap<String, Object>();

	public Cell() {
		this.things.put("top", Wall.NOTHING);
		this.things.put("left", Wall.NOTHING);
		this.things.put("character", Part.NOTHING);
		this.things.put("objective", Part.NOTHING);
	}
	
	public void set(String key, Object object) {
		this.things.put(key, object);
	}
	
	public Object get(String key) {
		return this.things.get(key);
	}
	
}
