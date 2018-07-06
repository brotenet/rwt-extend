package org.eclipse.rwt;

import java.util.HashMap;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.client.service.StartupParameters;

public abstract class RWTApplication implements ApplicationConfiguration{

	String title = "RiskMatrix CRM"; 
	String icon = "favicon.png"; 
	String stylesheet = "application/styles.css"; 
	String urlpattern = "";
	AbstractEntryPoint entrypoint;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void configure(Application application) {
		HashMap<String, String> properties = configureProperties(application, stylesheet, title, icon);
		application.addEntryPoint("/" + urlpattern, entrypoint.getClass(), properties);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static HashMap<String, String> configureProperties(Application application, String styles_sheet,
			String application_title, String fav_icon) {
		application.setOperationMode(OperationMode.SWT_COMPATIBILITY);
		application.addStyleSheet("application.styles", styles_sheet);
		HashMap<String, String> properties = null;
		try {
			properties = new HashMap<String, String>();
		} catch (Exception e) {
		}

		properties.put(WebClient.THEME_ID, "application.styles");
		properties.put(WebClient.PAGE_TITLE, application_title);
		properties.put( WebClient.PAGE_OVERFLOW, "scrollY" );
		properties.put( WebClient.HEAD_HTML, "<link rel=\"icon\" type=\"image/png\" href=\"" + fav_icon + "\" />");
		return properties;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStylesheet() {
		return stylesheet;
	}

	public void setStylesheet(String stylesheet) {
		this.stylesheet = stylesheet;
	}

	public AbstractEntryPoint getEntrypoint() {
		return entrypoint;
	}

	public void setEntrypoint(AbstractEntryPoint entrypoint) {
		this.entrypoint = entrypoint;
	}

	public String getUrlPattern() {
		return urlpattern;
	}

	public void setUrlPattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}
	
	public static HashMap<String, String> collectArguments(){
		HashMap<String, String> output = new HashMap<>();
		StartupParameters service = RWT.getClient().getService( StartupParameters.class );
		for(String parameter_name : service.getParameterNames().toArray(new String[service.getParameterNames().size()])) {
			output.put(parameter_name, service.getParameter(parameter_name));
		}		
		return output;
	}
	
}
