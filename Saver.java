package nz.ac.ara.macklei.TAMdemo;

public interface Saver {
	void save( Game game );
	void save( Game game, String fileName );
	void save( Game game, String fileName, String levelName );
}
