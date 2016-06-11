package listeners;

import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Models.Constants;
import Models.DBObject;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         DBObject obj = new DBObject();
         HashMap<String, String> map = new HashMap<String, String>();
         addMainPages(map);
         arg0.getServletContext().setAttribute(Constants.INDEX_HASHMAP, map);
         arg0.getServletContext().setAttribute(DBObject.ATTR_DB, obj);
    }

	private void addMainPages(HashMap<String, String> map) {
        map.put(Constants.INDEX_DO_HOMEPAGE, Constants.INDEX_DO_HOMEPAGE_TITLE);
        map.put(Constants.INDEX_DO_PROFILE_INFO, Constants.INDEX_DO_PROFILE_INFO_TITLE);
        map.put(Constants.INDEX_DO_REGISTER, Constants.INDEX_DO_REGISTER_TITLE);
        map.put(Constants.INDEX_DO_ADD_QUIZ, Constants.INDEX_DO_ADD_QUIZ_TITLE);
        map.put(Constants.INDEX_DO_ADD_QUESTION, Constants.INDEX_DO_ADD_QUESTION_TITLE);
        map.put(Constants.INDEX_DO_MY_QUIZES, Constants.INDEX_DO_MY_QUIZES_TITLE);
        map.put(Constants.INDEX_DO_QUIZ_PAGE, Constants.INDEX_DO_QUIZ_PAGE_TITLE);
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
