package com.asm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo (nếu cần)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1. Cho phép truy cập từ nguồn gốc (origin) của Frontend của bạn
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");

        // 2. Cho phép các phương thức HTTP (GET, POST, PUT, DELETE, OPTIONS,...)
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // 3. Cho phép các Header tùy chỉnh (nếu bạn gửi Authorization,
        // Content-Type,...)
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // 4. Cho phép gửi Cookies/Credentials (nếu cần)
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // 5. Thiết lập thời gian cache cho Preflight Request (tính bằng giây)
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp (nếu cần)
    }
}