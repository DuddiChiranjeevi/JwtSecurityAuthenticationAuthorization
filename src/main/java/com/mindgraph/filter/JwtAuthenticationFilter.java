package com.mindgraph.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mindgraph.service.JwtService;
import com.mindgraph.service.UserInfoUserDetailsService;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserInfoUserDetailsService userInfoUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=null;
		String userName=null;
		String authHeader=request.getHeader("Authorization");
		if(authHeader !=null && authHeader.startsWith("Bearer")) {
			
			token=authHeader.substring(7);
			userName=jwtService.extractUsername(token);
		}
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication() ==null) {
		UserDetails userDetails=	userInfoUserDetailsService.loadUserByUsername(userName);
		
		if(jwtService.validateToken(token, userDetails)) {
			UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(authHeader, null,userDetails.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
		}
			
		}
		filterChain.doFilter(request, response);
		
	}

}
