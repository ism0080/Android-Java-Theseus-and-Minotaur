package nz.ac.ara.macklei.TAMdemo;


public interface Loadable {
	int setWidthAcross(int widthAcross);
	int setDepthDown(int depthDown );
	void addWallAbove(Point where);
	void addWallLeft(Point where);
	void addTheseus(Point where);
	void addMinotaur(Point where);
	void addExit(Point where);
}
