package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.CartInfoDAO;
import com.internousdev.venus.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware {

	private List<CartInfoDTO> cartInfoDTOList;
	private Map<String, Object> session;
	private int allTotalPrice;
	private Collection<String> cartCheckList;

	public String execute() throws SQLException {

		if (session.isEmpty()) {
			return "sessionTimeout";
		}

		String result = ERROR;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();

		String userId = null;
		int count = 0;

		if (session.containsKey("userId")) {
			userId = String.valueOf(session.get("userId"));
		} else {
			userId = String.valueOf(session.get("tempUserId"));
		}

		for (String productId : cartCheckList) {

			count += cartInfoDAO.delete(productId, userId);
		}
		if (count == cartCheckList.size()) {

			cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(userId);
			allTotalPrice = cartInfoDAO.getAllTotalPrice(userId);
			result = SUCCESS;
		}

		return result;
	}

	public List<CartInfoDTO> getCartInfoDTOList() {
		return cartInfoDTOList;
	}

	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList) {
		this.cartInfoDTOList = cartInfoDTOList;
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

	public Collection<String> getCartCheckList() {
		return cartCheckList;
	}

	public void setCartCheckList(Collection<String> cartCheckList) {
		this.cartCheckList = cartCheckList;
	}

}
