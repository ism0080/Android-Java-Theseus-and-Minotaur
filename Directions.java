package nz.ac.ara.macklei.TAMdemo;

public enum Directions {
	UP(0, -1),
	RIGHT(1, 0),
	DOWN(0, 1),
	LEFT(-1, 0),
	SPACE(0, 0)
	;	
	public int acrossAdjust;
	public int downAdjust;
	
	private Directions(int across, int down) {
		acrossAdjust = across;
		downAdjust = down;
	}
}
