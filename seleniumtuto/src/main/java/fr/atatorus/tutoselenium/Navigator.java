package fr.atatorus.tutoselenium;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * 
 * @author denis
 */
@ManagedBean
@SessionScoped
public class Navigator implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String P1 = "page1";
	private static final String P2 = "page2";
	private static final String P3 = "page3";
	private static final Map<Integer, String> map;

	static {
		map = new HashMap<>();
		map.put(1, P1);
		map.put(2, P2);
		map.put(3, P3);
	}
	private Integer nextPage;
	private Integer previousPage;
	private Integer currentPage = 1;

	public Navigator( ) {
	}

	public SelectItem[] getPages() {
		return new SelectItem[] { new SelectItem(1, P1), new SelectItem(2, P2), new SelectItem(3, P3) };
	}

	public String go() {
		gotoPage(nextPage);
		return map.get(nextPage);
	}

	public String page1() {
		return gotoPage(1);
	}

	public String page2() {
		return gotoPage(1);
	}

	public String page3() {
		return gotoPage(3);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPreviousPage() {
		return previousPage;
	}

	private String gotoPage(int page) {
		previousPage = currentPage;
		currentPage = page;
		return map.get(currentPage);
	}

	public String getPreviousPageMessage() {
		String message = MessageFormat.format(	getMessages("footer.previous_page_text"),
												getMessages(map.get(previousPage) + ".name"));
		return message;
	}

	private String getMessages(String key) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		return bundle.getString(key);
	}
}
