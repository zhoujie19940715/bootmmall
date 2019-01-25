package com.zhoujie.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.zhoujie.mall.common.ResponseCode;
import com.zhoujie.mall.common.ServerResponse;
import com.zhoujie.mall.pojo.Shipping;
import com.zhoujie.mall.pojo.User;
import com.zhoujie.mall.service.IShippingService;
import com.zhoujie.mall.util.CookieUtil;
import com.zhoujie.mall.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/shipping/")
public class ShippingController {


    @Autowired
    private IShippingService iShippingService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpServletRequest request, HttpSession session, Shipping shipping){
       // user user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.add(user.getId(),shipping);
    }


    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpServletRequest request, HttpSession session, Integer shippingId){
       // User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.del(user.getId(),shippingId);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpServletRequest request, HttpSession session, Shipping shipping){
       // User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.update(user.getId(),shipping);
    }


    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<Shipping> select(HttpServletRequest request, HttpSession session, Integer shippingId){
       // User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.select(user.getId(),shippingId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
       // User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = JsonUtil.string2Obj(CookieUtil.readLoginToken(request), User.class);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.list(user.getId(),pageNum,pageSize);
    }














}
