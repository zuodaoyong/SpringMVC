package com.springmvc.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.LastModified;

import com.springmvc.async.AppAsyncListener;
import com.springmvc.async.AsyncRequestProcessor;

@RequestMapping("/test")
@Controller
public class TestController implements LastModified{

	private long lastModified;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "123";
	}
	
	
	@RequestMapping("/sync")
	@ResponseBody
	public void sync(HttpServletRequest request,HttpServletResponse response){
		long startTime = System.currentTimeMillis();  
        System.out.println("LongRunningServlet Start::Name="  
                + Thread.currentThread().getName() + "::ID="  
                + Thread.currentThread().getId());  
   
        String time = request.getParameter("time");  
        int secs = Integer.valueOf(time);  
        // max 10 seconds  
        if (secs > 10000)  
            secs = 10000;  
   
        longProcessing(secs);  
   
        PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        long endTime = System.currentTimeMillis();  
        out.write("Processing done for " + secs + " milliseconds!!");  
        System.out.println("LongRunningServlet Start::Name="  
                + Thread.currentThread().getName() + "::ID="  
                + Thread.currentThread().getId() + "::Time Taken="  
                + (endTime - startTime) + " ms.");  
		//return "test";
	}
	
	@RequestMapping("/async")
	@ResponseBody
	public void async(HttpServletRequest request,HttpServletResponse response){
		long startTime = System.currentTimeMillis();  
        System.out.println("AsyncLongRunningServlet Start::Name="  
                + Thread.currentThread().getName() + "::ID="  
                + Thread.currentThread().getId());  
   
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);  
   
        String time = request.getParameter("time");  
        int secs = Integer.valueOf(time);  
        // max 10 seconds  
        if (secs > 10000)  
            secs = 10000;  
   
        AsyncContext asyncCtx = request.startAsync();  
        asyncCtx.addListener(new AppAsyncListener());  
        asyncCtx.setTimeout(9000);  
   
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request  
                .getServletContext().getAttribute("executor");  
   
        executor.execute(new AsyncRequestProcessor(asyncCtx, secs));  
        long endTime = System.currentTimeMillis();  
        System.out.println("AsyncLongRunningServlet End::Name="  
                + Thread.currentThread().getName() + "::ID="  
                + Thread.currentThread().getId() + "::Time Taken="  
                + (endTime - startTime) + " ms."); 
	}
	
	private void longProcessing(int secs) {  
        // wait for given time before finishing  
        try {  
            Thread.sleep(secs);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
    }

	
	@Override
	public long getLastModified(HttpServletRequest request) {
		 //时间戳逻辑，返回最后修改时间，例如
        if (lastModified == 0L) {
            lastModified = System.currentTimeMillis();
        }
        return lastModified;
	}  
	
	
}
