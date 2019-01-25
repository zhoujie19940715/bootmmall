package com.zhoujie.mall.controller.portal;

import com.zhoujie.mall.common.Const;
import com.zhoujie.mall.common.ResponseCode;
import com.zhoujie.mall.common.ServerResponse;
import com.zhoujie.mall.pojo.User;
import com.zhoujie.mall.service.IUserService;
import com.zhoujie.mall.util.CookieUtil;
import com.zhoujie.mall.util.JsonUtil;
import com.zhoujie.mall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
          //  session.setAttribute(Const.CURRENT_USER, response.getData());
            //说明：面试必考！！！
            //①将在服务端生成的cookie对象响应给浏览器保存
            CookieUtil.writeLoginToken(httpServletResponse,session.getId());
            //②用seesion的id作为key,user对象序列化的json字符串作为值，过期时间为30分钟，保存到redis数据库
            RedisShardedPoolUtil.setEx(session.getId(),Const.RedisCacheExtime.REDIS_SESSION_EXTIME,JsonUtil.obj2String(response.getData()));
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
       // session.removeAttribute(Const.CURRENT_USER);
        //删除的原理是设置MaxAge为0
        //要不要动redis呢？一定要把user对象从redis中删除
        String loginToken = CookieUtil.readLoginToken(request);
        RedisShardedPoolUtil.del(loginToken);//从redis中删除
        CookieUtil.delLoginToken(request,response);//从cookie中删除
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }


    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }


    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session, HttpServletRequest request) {
       /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }*/
       //读cookie
        // 动不动redis? 一定是从redis中获取序列化的user对象
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        //从redis中获取user的序列
        String userJson = RedisShardedPoolUtil.get(loginToken);
        //反序列化,生成user对象
        User user = JsonUtil.string2Obj(userJson, User.class);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }


    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }


    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }


    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpServletRequest request, String passwordOld, String passwordNew) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        //从redis中获取user的序列
        String userJson = RedisShardedPoolUtil.get(loginToken);
        //反序列化,生成user对象
        User user = JsonUtil.string2Obj(userJson, User.class);
        if (user != null) {
            return iUserService.resetPassword(passwordOld, passwordNew, user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpServletResponse httpServletResponse, HttpServletRequest request, User user) {

        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        //从redis中获取user的序列
        String userJson = RedisShardedPoolUtil.get(loginToken);
        //反序列化,生成user对象
        User currentUser = JsonUtil.string2Obj(userJson, User.class);
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()) {
            response.getData().setUsername(currentUser.getUsername());
           // session.setAttribute(Const.CURRENT_USER, response.getData());
            CookieUtil.writeLoginToken(httpServletResponse,loginToken);
            RedisShardedPoolUtil.setEx(loginToken,Const.RedisCacheExtime.REDIS_SESSION_EXTIME,JsonUtil.obj2String(response.getData()));
        }
        return response;
    }

    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        //从redis中获取user的序列
        String userJson = RedisShardedPoolUtil.get(loginToken);
        //反序列化,生成user对象
        User currentUser = JsonUtil.string2Obj(userJson, User.class);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }


}
