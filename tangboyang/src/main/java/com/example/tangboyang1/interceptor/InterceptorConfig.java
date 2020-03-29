package com.example.tangboyang1.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Autowired
//    private InitInterceptor initInterceptor;

    //
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())//添加拦截器
                .addPathPatterns("/ShoppingCartController/**","/UserController/**","/OrderController/**","/AddressController/**","/CommentController/**",
                        "/ProductController/delete/Book/by/id","/ProductController/update/Book/by/Bookid","/ProductController/add/product") //拦截所有请求
                .excludePathPatterns("/UserController/fing/user/by/openid", "/UserController/fing/user/by/usernameAndpassward","/UserController/Regiest/user/by/json","/OrderController/update/orders/payState/by/Orderid");//对应的不拦截的请求
    }
}