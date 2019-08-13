package com.yahoo.zyfx.yuanweiming.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spring_mvc
 * @description:
 * @author: chinasie-29@crc.com.hk
 * @create: 2019-08-13 13:56
 */
//@WebServlet是什么？
//
//其实，以前我们定义一个Servlet，需要在web.xml中去配置，不过在Servlet3.0后出现了基于注解的Servlet。
//
//仔细观察，你会发现，这个DispatcherServlet是自启动，而且传入了一个参数。
//
//要知道，在Spring MVC中，要想基于注解，需要在配置中指明扫描的包路径，就像这个样子：
//
//
//
//为了方便，我这里就通过初始化参数直接将需要扫描的基包路径传入。
@WebServlet(name = "dispatcherServlet", urlPatterns = "/", loadOnStartup = 1,
 initParams = {@WebInitParam(name = "base-package", value = "com.yahoo.zyfx.yuanweiming")})
public class DispatcherServlet extends HttpServlet {

    //扫描的基包
    private String basePackage = "";
    //基包下面所有的带包路径的权限定类名
    private List<String> packageNames = new ArrayList<String>();
    //注解实例化 注解上的名称：实例化对象
    private Map<String, Object> instanceMap = new HashMap<String, Object>();
    //带保路经的权限定名称：注解上的名称
    private Map<String, String> nameMap = new HashMap<String, String>();
    //URL地址和方法的映射关系 SpringMVC就是方法调用链
    private Map<String, Method> urlMethodMap = new HashMap<String, Method>();
    //Method和权限定类名的映射关系 主要是为了通过Method找到该方法的对象利用反射执行
    private Map<Method, String> methodPackageMap = new HashMap<Method, String>();

    /**
    *@Description: 其实，在init中，我们主要是完成了什么呢？
     *
     * 第一，我们应该去扫描基包下的类，得到信息A
     *
     * 第二，对于@Controller/@Service/@Repository注解而言，我们需要拿到对应的名称，并初始化它们修饰的类，形成映射关系B
     *
     * 第三，我们还得扫描类中的字段，如果发现有@Qualifier的话，我们需要完成注入
     *
     * 第四，我们还需要扫描@RequestMapping，完成URL到某一个Controller的某一个方法上的映射关系C
     *
     * 其实，Spring MVC的处理流程，就是类似这样的！
    *@Param:
    *@return:
    *@Author: chinasie-29@crc.com.hk
    *@date: 2019/8/13
    */
    @Override
    public void init(ServletConfig config) throws ServletException {

        basePackage = config.getInitParameter("base-package");

    }


}