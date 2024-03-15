package br.com.acme.cervejariaacme.interceptor;

import br.com.acme.cervejariaacme.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if(usuarioLogado != null){
            System.out.println("Estou logado");
        }else{
            System.out.println("Nao estou logado");
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {}
}
