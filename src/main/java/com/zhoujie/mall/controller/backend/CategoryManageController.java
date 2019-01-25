package com.zhoujie.mall.controller.backend;

import com.zhoujie.mall.common.ResponseCode;
import com.zhoujie.mall.common.ServerResponse;
import com.zhoujie.mall.pojo.User;
import com.zhoujie.mall.service.ICategoryService;
import com.zhoujie.mall.service.IUserService;
import com.zhoujie.mall.util.CookieUtil;
import com.zhoujie.mall.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest request, HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request),User.class);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //springMVC拦截器(在调用方法前，拦截所有的请求，并进行一些逻辑处理)拦截已经做过权限处理了
        //校验一下是否是管理员
       /* if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加我们处理分类的逻辑
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }*/
        return iCategoryService.addCategory(categoryName,parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest request, HttpSession session, Integer categoryId, String categoryName){
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        /*if(iUserService.checkAdminRole(user).isSuccess()){
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }*/
        //更新categoryName
        return iCategoryService.updateCategoryName(categoryId,categoryName);
    }

    //####1.获取品类子节点(平级)get_category.do
    @RequestMapping(value = "get_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest request, HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
       // User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request),User.class);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
         return    iCategoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest request, HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
      //  User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request),User.class);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
      /*  if(iUserService.checkAdminRole(user).isSuccess()){

        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }*/
        //查询当前节点的id和递归子节点的id
//            0->10000->100000
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }

}
