package fr.atatorus.tutoselenium;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author denis
 */
@ManagedBean(name = "prefs")
@SessionScoped
public class UserPreferences implements Serializable {

	private static final long serialVersionUID = 1L;

	private String locale = Locale.FRENCH.toString();

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setFrenchLocale() {
		locale = Locale.FRENCH.toString();
	}

	public void setEnglishLocale() {
		locale = Locale.ENGLISH.toString();
	}

}
