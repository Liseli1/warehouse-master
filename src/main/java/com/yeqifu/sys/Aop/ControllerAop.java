package com.yeqifu.sys.Aop;

import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.SysLog;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class ControllerAop {

    @Autowired
    private AopService aopService;
    //所有新增操作的切入点
    @Pointcut("execution(* com.yeqifu.bus.controller.*.add*(..))")
    public void addpiontcut(){

    }
    //所有删除操作的切入点
    @Pointcut("execution(* com.yeqifu.bus.controller.*.delete*(..))")
    public void delpiontcut(){

    }
    //所有修改操作的切入点
    @Pointcut("execution(* com.yeqifu.bus.controller.*.update*(..))")
    public void editpiontcut(){

    }

    @Around("addpiontcut()||delpiontcut()||editpiontcut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp){
        //获取系统时间
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=formatter.format(date);
        //获取操作人
        HttpSession session=WebUtils.getSession();
        User user= (User) session.getAttribute("user");
        String person=user.getName()+user.getLoginname();
        //获取方法名
        String type=pjp.getSignature().getName();
        //获取ip
        String clientIp = getRequestClientIp();
        if (clientIp.equals("0:0:0:0:0:0:0:1")){
            clientIp="127.0.0.1";
        }
        //获取入参
        Object[] indata=null;
        Object returnObj = null;
        indata=pjp.getArgs();
        try {
            returnObj = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            returnObj="操作不成功";
        }
        String in="";
        if (indata!=null&&indata.length>1){
           in="id="+indata[0].toString()+","+"number="+indata[1].toString()+","+indata[2];
        }else if (indata.length==1){
            in=indata[0].toString();
        }
        SysLog sysLog=new SysLog(null,person,in,type,time,returnObj.toString(),clientIp);
        aopService.insertLog(sysLog);
        return returnObj;
    }

    /**
     * 获取请求客户端IP
     *
     * @return 客户端IP信息
     */
    private String getRequestClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return WebUtils.getCliectIp(request);
    }
}
