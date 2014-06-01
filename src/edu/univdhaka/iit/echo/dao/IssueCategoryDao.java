package edu.univdhaka.iit.echo.dao;

import java.util.List;

import edu.univdhaka.iit.echo.domain.IssueCategory;

//This is the interface that contains the method to manipulate the 'issue category' table in database
public interface IssueCategoryDao {
	
	public int insert(IssueCategory category, int userId);

	public List<IssueCategory> getAllIssueCategoryInfo();

	public void delete(int id);

	public void update(IssueCategory category, int id);

	public IssueCategory select(int id);
	
}
