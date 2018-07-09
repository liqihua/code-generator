package com.generator.service;

import com.generator.common.basic.Table;
import com.generator.common.basic.TableColumn;
import com.core.common.utils.Tool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorService {
	protected static final int TYPE_MAPPER = 1;
	protected static final int TYPE_ENTITY = 2;
	protected static final int TYPE_DAO = 3;
	protected static final int TYPE_SERVICE = 4;
	protected static final int TYPE_SERVICE_IMPL = 5;
	protected static final int TYPE_WEB_CONTROLLER = 6;
	protected static final int TYPE_JSP_LIST = 7;
	protected static final int TYPE_JSP_FORM = 8;
	protected static final int TYPE_DTO = 9;
	
	protected static final String TPL_MAPPER = "/mapper.ftl";
	protected static final String TPL_ENTITY = "/entity.ftl";
	protected static final String TPL_DAO = "/dao.ftl";
	protected static final String TPL_SERVICE = "/service_interface.ftl";
	protected static final String TPL_SERVICE_IMPL = "/service_impl.ftl";
	protected static final String TPL_WEB_CONTROLLER = "/webController.ftl";
	protected static final String TPL_JSP_LIST = "/jspList.ftl";
	protected static final String TPL_JSP_FORM = "/jspForm.ftl";
	protected static final String TPL_DTO = "/dto.ftl";
	
	protected static final String PATH_MAPPER ="\\dao\\mappers";
	protected static final String PATH_ENTITY ="\\entity";
	protected static final String PATH_DAO ="\\dao";
	protected static final String PATH_SERVICE ="\\service";
	protected static final String PATH_SERVICE_IMPL ="\\service\\impl";
	protected static final String PATH_WEB_CONTROLLER ="\\controller\\web";
	protected static final String PATH_JSP = "\\src\\main\\webapp\\WEB-INF\\views\\project";
	protected static final String PATH_DTO = "\\entity\\dto";
	
	protected static final String SUFFIX_MAPPER = "Dao.xml";
	protected static final String SUFFIX_ENTITY = ".java";
	protected static final String SUFFIX_DAO = "Dao.java";
	protected static final String SUFFIX_SERVICE = "Service.java";
	protected static final String SUFFIX_SERVICE_IMPL = "ServiceImpl.java";
	protected static final String SUFFIX_WEB_CONTROLLER = "WebController.java";
	protected static final String SUFFIX_JSP_LIST = "List.jsp";
	protected static final String SUFFIX_JSP_FORM = "Form.jsp";
	protected static final String SUFFIX_RETURN = ".java";
	
	public static final String PERM_TYPE_VIEW = "view";
	public static final String PERM_TYPE_EDIT = "edit";
	public static final String PERM_TYPE_DELETE = "delete";
	
	
	@Autowired
	private TableService tableService;
	@Autowired
	private Environment environment;
	
	/*
	 * 全部生成
	 */
	public void makeAll(String tableName,String packageName){
		makeMapper(tableName,packageName);
		makeEntity(tableName,packageName);
		makeDao(tableName,packageName);
		makeVO(tableName,packageName);
		makeService(tableName,packageName);
		makeServiceImpl(tableName,packageName);
		/*makeWebController(tableName,packageName);*/
		//makeJspList(tableName);
		//makeJspForm(tableName);
	}
	
	
	/*
	 * 生成mapper
	 */
	public void makeMapper(String tableName,String packageName){
		generate(tableName, TYPE_MAPPER,packageName);
	}
	
	/*
	 * 生成实体类
	 */
	public void makeEntity(String tableName,String packageName){
		generate(tableName, TYPE_ENTITY,packageName);
	}
	
	/*
	 * 生成dao
	 */
	public void makeDao(String tableName,String packageName){
		generate(tableName, TYPE_DAO,packageName);
	}
	
	/*
	 * 生成return
	 */
	public void makeVO(String tableName, String packageName){
		generate(tableName, TYPE_DTO,packageName);
	}
	
	/*
	 * 生成service
	 */
	public void makeService(String tableName,String packageName){
		generate(tableName, TYPE_SERVICE,packageName);
	}

	/*
	 * 生成serviceImpl
	 */
	public void makeServiceImpl(String tableName,String packageName){
		generate(tableName, TYPE_SERVICE_IMPL,packageName);
	}

	
	/*
	 * 生成controller
	 */
	public void makeWebController(String tableName,String packageName){
		generate(tableName, TYPE_WEB_CONTROLLER,packageName);
	}
	
	/*
	 * 生成list.jsp
	 */
	public void makeJspList(String tableName,String packageName){
		generate(tableName, TYPE_JSP_LIST,packageName);
	}
	
	/*
	 * 生成form.jsp
	 */
	public void makeJspForm(String tableName,String packageName){
		generate(tableName, TYPE_JSP_FORM,packageName);
	}
	
	public void generate(String tableName,int type,String packageName){
		String tplFile = null;
		String suffix = null;
		String genPath = null;
		
		switch(type){
			case TYPE_MAPPER:
				tplFile = TPL_MAPPER;
				genPath = PATH_MAPPER;
				suffix = SUFFIX_MAPPER;
				break;
			case TYPE_ENTITY:
				tplFile = TPL_ENTITY;
				genPath = PATH_ENTITY;
				suffix = SUFFIX_ENTITY;
				break;
			case TYPE_DAO:
				tplFile = TPL_DAO;
				genPath = PATH_DAO;
				suffix = SUFFIX_DAO;
				break;
			case TYPE_SERVICE:
				tplFile = TPL_SERVICE;
				genPath = PATH_SERVICE;
				suffix = SUFFIX_SERVICE;
				break;
			case TYPE_SERVICE_IMPL:
				tplFile = TPL_SERVICE_IMPL;
				genPath = PATH_SERVICE_IMPL;
				suffix = SUFFIX_SERVICE_IMPL;
				break;
			case TYPE_WEB_CONTROLLER:
				tplFile = TPL_WEB_CONTROLLER;
				genPath = PATH_WEB_CONTROLLER;
				suffix = SUFFIX_WEB_CONTROLLER;
				break;
			case TYPE_JSP_LIST:
				tplFile = TPL_JSP_LIST;
				genPath = PATH_JSP;
				suffix = SUFFIX_JSP_LIST;
				break;
			case TYPE_JSP_FORM:
				tplFile = TPL_JSP_FORM;
				genPath = PATH_JSP;
				suffix = SUFFIX_JSP_FORM;
				break;
			case TYPE_DTO:
				tplFile = TPL_DTO;
				genPath = PATH_DTO;
				suffix = SUFFIX_RETURN;
				break;
		}

        genPath = Tool.getCodeRoot() + packageName.replace(".","\\") + genPath;
		
		List<TableColumn> columnList = tableService.findColumnList(tableName);
		if(columnList != null){
			String tableNameCH = "";
			for(TableColumn column : columnList){
				column = tableService.convert(column);
				if("id".equals(column.getColumnName())){
					tableNameCH = column.getColumnComment();
				}
			}
			String className = tableService.toClassName(tableName);
			Table sysTable = new Table(tableName,tableNameCH,className,tableService.firstLower(className), columnList);
			sysTable.setPermView(tableService.toPermStr(tableName, PERM_TYPE_VIEW));
			sysTable.setPermEdit(tableService.toPermStr(tableName, PERM_TYPE_EDIT));
			sysTable.setPermDelete(tableService.toPermStr(tableName, PERM_TYPE_DELETE));
			sysTable.setShortName(tableService.toShortName(tableName));
			
			Map<String,Object> table = new HashMap<String, Object>();
			table.put("table", sysTable);
			table.put("packageName", packageName);
			Configuration cfg = new Configuration();
	        String path = this.getClass().getResource("/").getPath()+"templates";
	        String fileName = sysTable.getClassName()+suffix;
	        if(type == TYPE_JSP_LIST || type == TYPE_JSP_FORM){
	        	fileName = tableService.firstLower(fileName);
	        }
	        if(type == TYPE_DTO){
	        	fileName = "DTO"+sysTable.getShortName()+suffix;
	        }
			try{
				cfg.setDirectoryForTemplateLoading(new File(path));
				Template template = cfg.getTemplate(tplFile,"UTF-8");
				StringWriter out = new StringWriter();
				template.process(table, out);
				System.out.println("--------- freemarker making ---------");
				System.out.println(out.toString());

				File targetDir = new File(genPath);
				targetDir.mkdirs();
				File targetFile = new File(targetDir,fileName);
				targetFile.createNewFile();
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8");
				osw.write(out.toString());
				osw.close();
			System.out.println("--- path:"+targetFile.getPath());
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	
}
