package edu.univdhaka.iit.echo.dao;

import java.util.List;

import edu.univdhaka.iit.echo.domain.Echo;

// This is the interface that contains the method to manipulate the 'echo' table in database

public interface EchoDao {

	public int insertEcho(Echo echo, int issueCategoryId);

	public List<Echo> getAllEcho();

	public void deleteEcho(int echoId);

	public void updateEcho(Echo echo, int echoId);

	public Echo selectOneEcho(int echoId);

	public List<Echo> findEchoByUserName(String userName);

	public int getIssueCategoryId(int echoId);

	public List<Echo> findEchoByIssueCategory(String issueCategory);

}
