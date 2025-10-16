package com.src.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.src.daoImpl.UserDAOImpl;
import com.src.model.Asset;
import com.src.model.TableCreater;
import com.src.model.User;
import com.src.model.UserHistory;
import com.src.model.Wall;

/**
 * Application Lifecycle Listener implementation class AppStartupListener
 *
 */
@WebListener
public class AppStartupListener implements ServletContextListener {
	private static final boolean auto_create = true;

	/**
	 * Default constructor.
	 */
	public AppStartupListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		if (auto_create) {
			System.out.println("Auto Table Creation ENABLED");
			TableCreater.createTables(Asset.class, UserHistory.class, User.class,Wall.class );

		} else {
			System.out.println("Auto Table Creation DISABLED");
		}
		try {
			UserDAOImpl userDAO = new UserDAOImpl();
			userDAO.createDefaultAdminIfNotExists();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
