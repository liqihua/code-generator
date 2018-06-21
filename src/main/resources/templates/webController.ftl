package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import springfox.documentation.annotations.ApiIgnore;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.page.SysPage;
import com.java.sys.common.utils.Tool;
import com.java.entity.${table.className};
import com.java.service.${table.className}Service;

@ApiIgnore
@Controller
@RequestMapping("/sys/${table.lowerName}WebController")
public class ${table.className}WebController extends BaseController{
	@Resource
	private ${table.className}Service ${table.lowerName}Service;
	
	@ModelAttribute
	public ${table.className} get(@RequestParam(required=false) String id) {
		${table.className} entity = null;
		if (Tool.isNotBlank(id)){
			entity = ${table.lowerName}Service.get(id);
		}
		if (entity == null){
			entity = new ${table.className}();
		}
		return entity;
	}
	
	@RequiresPermissions("${table.permView}")
	@RequestMapping("/list")
	public String list(${table.className} ${table.lowerName},Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<${table.className}> page = ${table.lowerName}Service.findPage(${table.lowerName},request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/${table.lowerName}List.jsp";
	}
	
	@RequiresPermissions("${table.permEdit}")
	@RequestMapping("/form")
	public String form(${table.className} ${table.lowerName}, Model model) {
		model.addAttribute("entity", ${table.lowerName});
		return "/WEB-INF/views/project/${table.lowerName}Form.jsp";
	}
	
	@RequiresPermissions("${table.permEdit}")
	@RequestMapping("/save")
	public String save(${table.className} ${table.lowerName}, Model model, RedirectAttributes redirectAttributes) {
		${table.lowerName}Service.save(${table.lowerName});
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/${table.lowerName}WebController/list";
	}
	
	@RequiresPermissions("${table.permDelete}")
	@RequestMapping("/delete")
	public String delete(${table.className} ${table.lowerName}, RedirectAttributes redirectAttributes) {
		${table.lowerName}Service.delete(${table.lowerName});
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/${table.lowerName}WebController/list";
	}
}
