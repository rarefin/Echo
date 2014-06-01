package edu.univdhaka.iit.echo.test.dao;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.univdhaka.iit.echo.dao.IssueCategoryDao;
import edu.univdhaka.iit.echo.dao.IssueCategoryDaoImpl;
import edu.univdhaka.iit.echo.domain.IssueCategory;

public class IssueCategoryDaoImplTest {

	private static final int ID = 5;
	private static final int VERSION = 0;
	private static final String CREATEDDATE = null;
	private static final Date LASTMODIFIEDDATE = null;
	private static final boolean IS_NEW = true;
	private static final String TITLE = "something";

	private IssueCategoryDao issueCategoryDao;

	@Before
	public void setUp() {
		issueCategoryDao = new IssueCategoryDaoImpl();
	}

	@Test
	public void testInsert() {
		IssueCategory issueCategory = new IssueCategory();

		issueCategory.setVersion(VERSION);
		issueCategory.setCreatedDate(CREATEDDATE);
		issueCategory.setLastModifiedDate(LASTMODIFIEDDATE);
		issueCategory.setNew(IS_NEW);
		issueCategory.setTitle(TITLE);

		issueCategoryDao.insert(issueCategory, 10);

		// Check by asserting
		IssueCategory newCategory = issueCategoryDao.select(ID);

		Assert.assertNotNull(newCategory);
		Assert.assertEquals(ID, newCategory.getId());
		Assert.assertEquals(VERSION, newCategory.getVersion());
		Assert.assertEquals(CREATEDDATE, newCategory.getCreatedDate());
		Assert.assertEquals(LASTMODIFIEDDATE, newCategory.getLastModifiedDate());
		Assert.assertEquals(IS_NEW, newCategory.isNew());
		Assert.assertEquals(TITLE, newCategory.getTitle());
	}

	/*@Test
	public void testDeleteEcho() {
		issueCategoryDao.delete(ID);

		// Check by asserting
		IssueCategory newCategory = issueCategoryDao.select(ID);
		Assert.assertNotNull(newCategory);
	}*/

	/*@Test
	public void testUpdateEcho() {
		IssueCategory issueCategory = new IssueCategory();

		issueCategory.setId(ID);
		issueCategory.setVersion(VERSION);
		issueCategory.setCreatedDate(CREATEDDATE);
		issueCategory.setLastModifiedDate(LASTMODIFIEDDATE);
		issueCategory.setNew(IS_NEW);
		issueCategory.setTitle(TITLE);

		issueCategoryDao.update(issueCategory, ID);

		// Check by asserting
		IssueCategory newCategory = issueCategoryDao.select(ID);

		Assert.assertNotNull(newCategory);
		Assert.assertEquals(ID, newCategory.getId());
		Assert.assertEquals(VERSION, newCategory.getVersion());
		Assert.assertEquals(CREATEDDATE, newCategory.getCreatedDate());
		Assert.assertEquals(LASTMODIFIEDDATE, newCategory.getLastModifiedDate());
		Assert.assertEquals(IS_NEW, newCategory.isNew());
		Assert.assertEquals(TITLE, newCategory.getTitle());
	}

	@Test
	public void testSelect() {
		IssueCategory newCategory = issueCategoryDao.select(ID);

		Assert.assertNotNull(newCategory);
		Assert.assertEquals(ID, newCategory.getId());
		Assert.assertEquals(VERSION, newCategory.getVersion());
		Assert.assertEquals(CREATEDDATE, newCategory.getCreatedDate());
		Assert.assertEquals(LASTMODIFIEDDATE, newCategory.getLastModifiedDate());
		Assert.assertEquals(IS_NEW, newCategory.isNew());
		Assert.assertEquals(TITLE, newCategory.getTitle());
	}

	@Test
	public void testGetAllIssueCategoryInfo() {
		issueCategoryDao.getAllIssueCategoryInfo();
	}
*/
}
