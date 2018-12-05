package com.mao.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.mao.domain.CategoryBean;
import com.mao.domain.Product;
import com.mao.service.ProductService;

/**
 * 添加商品
 */
public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				Product product = new Product();
				
				//因为下面获取得到的是name和value，没办法存到product中
				Map<String, Object> map = new HashMap<String, Object>();
				List<String> productImgList = new LinkedList<String>();
				
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
				servletFileUpload.setHeaderEncoding("utf-8");
				boolean multipartContent = servletFileUpload.isMultipartContent(request);
				//判断是否是带文件上传的表单
				if(multipartContent) {
					List<FileItem> list = servletFileUpload.parseRequest(request);
					//遍历表单元素集合
					for(FileItem item:list) {
						//判断是否是普通表单数据
						if(item.isFormField()){
							//获取表单元素的name和value
							String name = item.getFieldName();
							String value = item.getString("utf-8");
							map.put(name, value);
						}else {
							//文件上传表单数据
							String fileName = item.getName();
							//String path = this.getServletContext().getRealPath("upload");
							File path = new File("G:/apache-tomcat-7.0.52/apache-tomcat-7.0.52/webapps/Mao/WebContent/upload");
							InputStream is = item.getInputStream();
							OutputStream os = new FileOutputStream(path+"/"+fileName);
							
							IOUtils.copy(is, os);
							productImgList.add("upload/"+fileName);
							
						}
						item.delete();
					}
				}
				
				BeanUtils.populate(product, map);
				product.setImage(productImgList.get(0));
				String[] array = productImgList.toArray(new String[productImgList.size()]);
				StringBuilder productImg = new StringBuilder("");
				for(String str: array) {
					productImg.append(str+";");
				}
				product.setProductImg(productImg.toString());
				
				CategoryBean category = new CategoryBean();
				category.setCid((String) map.get("cid"));
				product.setCategory(category);

				ProductService productService = new ProductService();
				productService.savePeoduct(product);
				
				response.sendRedirect(request.getContextPath()+"/admin/adminFillAllProduct");
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
