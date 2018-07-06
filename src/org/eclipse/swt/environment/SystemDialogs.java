package org.eclipse.swt.environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;
import org.eclipse.rap.rwt.service.ServiceHandler;
import org.eclipse.rap.rwt.service.ServiceManager;
import org.eclipse.swt.widgets.Shell;

public class SystemDialogs {
	
	@SuppressWarnings("resource")
	public static boolean SaveFile(Shell parent, String title, String extensions, String default_file_name, File file) {
		try {
			
			class DownloadServiceHandler implements ServiceHandler {

				byte[] download_data;
				String file_name;

				public DownloadServiceHandler(byte[] download_data, String file_name) {
					this.download_data = download_data;
					this.file_name = file_name;
				}

				public void service(HttpServletRequest request, HttpServletResponse response)
						throws IOException, ServletException {
					response.setContentType("application/octet-stream");
					response.setContentLength(download_data.length);
					String contentDisposition = "attachment; filename=\"" + file_name + "\"";
					response.setHeader("Content-Disposition", contentDisposition);
					response.getOutputStream().write(download_data);
				}
			
			}

			byte[] file_bytes = new byte[(int) file.length()];
			new FileInputStream(file).read(file_bytes);
			
			ServiceManager manager = RWT.getServiceManager();
			ServiceHandler handler = new DownloadServiceHandler(file_bytes, default_file_name);
			manager.unregisterServiceHandler("downloadServiceHandler");
			manager.registerServiceHandler( "downloadServiceHandler", handler );
			
			StringBuilder url = new StringBuilder();
			url.append( RWT.getServiceManager().getServiceHandlerUrl( "downloadServiceHandler" ) );
			url.append( '&' ).append( "filename" ).append( '=' ).append( default_file_name );
			
			UrlLauncher launcher = RWT.getClient().getService( UrlLauncher.class );
			launcher.openURL(url.toString());
			
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}		
	}

}
