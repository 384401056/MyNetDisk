package com.blueice.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class uploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//上传文件夹和缓存文件夹路径。
		String uploadPath = this.getServletContext().getRealPath("WEB-INF/Upload");
		String savePath = "WEB-INF/Upload"; //用来存入数据库的，这个路么不能用服务硬盘路径。
		String temp = this.getServletContext().getRealPath("WEB-INF/temp");
		
		Map<String,String> beanMap = new HashMap<String, String>(); 
		beanMap.put("ip", request.getRemoteAddr()); //获取IP.
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);    //设置缓冲区大小。
		factory.setRepository(new File(temp)); //设置缓存文件夹。
		
		
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");     //文件名中文乱码处理。
		fileUpload.setFileSizeMax(1024*1024*100);  //单个上传文件的大小限制
		fileUpload.setSizeMax(1024*1024*200);		 //总的上传大小限制
		
		try {
		
			//判断文件上传的表单类型。
			if (!fileUpload.isMultipartContent(request)){
				throw new RuntimeException("不是文件上传的表单。");
			}
		
			/*===开始解析上传项*/
			List<FileItem> list = fileUpload.parseRequest(request);
			
			for(FileItem item :list){
				
				if(item.isFormField()){
					
					//获取非文件上传项的值，并做乱码处理。
					String name = item.getFieldName();
					String value = item.getString("utf-8");  
					beanMap.put(name, value);
					
				}else{
					
					//对文件名进行UUID的处理。
					String realName = item.getName();
					
					/*item.getName()会获取路径全名，所以要将真正的文件名提取出来。视频教程中并没有说明此处。*/
					realName = realName.substring(realName.lastIndexOf("\\")+1);

					String uuidName = UUID.randomUUID()+"_"+realName;
					
					beanMap.put("realName", realName);
					beanMap.put("uuidName", uuidName);
					
					
					/*--对上传文件的位置进行分目录存储。*/
					String hash = Integer.toHexString(uuidName.hashCode());//将这个uuidName的哈希值转为16进制的字符串。
							
					for(char c :hash.toCharArray()){
						
						uploadPath+="/"+c;
						savePath+="/"+c;
					}
					new File(uploadPath).mkdirs(); //创建文件夹。
					beanMap.put("savePath", savePath);
					
					InputStream in = item.getInputStream();  //获取文件输入流。
					OutputStream out = new FileOutputStream(new File(uploadPath,uuidName)); //创建文件输出流，指向建立好的文件夹路径uploadPath.
					
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					
					item.delete();  //删除缓存文件。
				}
				
			}
			
			/*
			 * 2.将文件信息写入数据库
			 * 将beanMap中的数据存入数据中。
			 */
			
			Resource res = new Resource();
			BeanUtils.populate(res, beanMap);
			
			
			
			String sqlStr = "INSERT INTO resource VALUES(null,?,?,?,?,null,?)";
			QueryRunner runner = new QueryRunner(DaoUtils.getDataSource());
				
			runner.update(sqlStr, 
					res.getUuidName(),
					res.getRealName(),
					res.getSavePath(),
					res.getIp(),
					res.getDescription()
					);
			
			
			//重定向回主页。
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.toString());
		}
		
		
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
