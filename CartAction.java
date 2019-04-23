package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.CartInfoDAO;
import com.internousdev.venus.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware {

	CartInfoDAO cartInfoDAO = new CartInfoDAO();
	private List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
	private Map<String, Object> session;
	private int allTotalPrice;

	public String execute() throws SQLException {

		String userId = null;

		if (session.isEmpty()) {
			return "sessionTimeout";
		}

		if (session.containsKey("userId")) {
			userId = String.valueOf(session.get("userId"));
		} else {
			userId = String.valueOf(session.get("tempUserId"));
		}

		cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(userId);
		allTotalPrice = cartInfoDAO.getAllTotalPrice(userId);

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getAllTotalPrice() {
		return allTotalPrice;
	}

	public void setAllTotalPrice(int allTotalPrice) {
		this.allTotalPrice = allTotalPrice;
	}

	public List<CartInfoDTO> getCartInfoDTOList() {
		return cartInfoDTOList;
	}

	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList) {
		this.cartInfoDTOList = cartInfoDTOList;
	}

}
