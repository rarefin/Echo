package edu.univdhaka.iit.echo.dao;

import java.util.List;

import edu.univdhaka.iit.echo.domain.Echo;
import edu.univdhaka.iit.echo.domain.UserAccount;

public interface EchoDao {
	
	public void insertEcho(Echo echo, UserAccount user);
	
	public List<Echo> getAllEcho();
	
	public void deleteEcho(String text);

	public void updateEcho(Echo echo, String text);

	public Echo selectEcho(String text);

}
