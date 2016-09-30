package com.tops001.dubbott.test.provider.service.webcontainer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.webcontainer.WebContainerContextInjectionService;

@Service
public class WebContainerContextInjectionServiceImpl implements WebContainerContextInjectionService {

	@Override
	public String fetchHTTPRequestPathInfo(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getPathInfo();
	}

	@Override
	public String fetchHTTPResponse(HttpServletResponse httpServletResponse) {
		httpServletResponse.addHeader("responseheadername", "responseheadervalue");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        try {
            PrintWriter pw =
                            new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()));
            /*
             * PrintWriter does not automatically flush so going to flush pw
             * manually. Reminder, cannot just flush HttpServletResponse
             * OutputStream either since decorated class has no idea about
             * PrintWriter.
             */
            pw.write("Hello World");
            pw.flush();
            /*
             * this should always be committed now
             */
            //if (httpServletResponse.isCommitted()) {
            pw.write(" -- I was committted");
            //}
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not output the servlet response.");
        }

        return "Shouldn't see me";
	}

	@Override
	public void fetchServletContext(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ServletContext servletContext) throws IOException, ServletException {
		httpServletRequest.setAttribute("wink", "testing 1-2-3");
        servletContext.getRequestDispatcher("/servlets-test.jsp").include(httpServletRequest,
                                                                          httpServletResponse);
        httpServletRequest.removeAttribute("wink");

        // need to flush buffer so the response is committed
        httpServletResponse.flushBuffer();
	}

	@Override
	public String fetchServletConfig(ServletConfig servletConfig) {
		return servletConfig.getServletName();
	}
	
}
