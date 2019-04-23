package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.CartInfoDAO;
import com.internousdev.venus.dao.ProductInfoDAO;
import com.internousdev.venus.dao.UserInfoDAO;
import com.internousdev.venus.dto.CartInfoDTO;
import com.internousdev.venus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware {

	CartInfoDAO cartInfoDAO = new CartInfoDAO();
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	private List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
	private int productId;
	private int productCount;
	private Map<String, Object> session;
	private int allTotalPrice;

	public String execute() throws SQLException {

		if (session.isEmpty()) {
			return "sessionTimeout";
		}

		String result = ERROR;
		String userId = null;

		if (session.containsKey("userId")) {
			userId = String.valueOf(session.get("userId"));
		} else {
			userId = String.valueOf(session.get("tempUserId"));
		}

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = productInfoDAO.getProductInfo(productId);

		int count = 0;

		if (cartInfoDAO.isExistsCartInfo(userId, productId)) {

			count = cartInfoDAO.updateProductCount(userId, productId, productCount);

		} else {
			count = cartInfoDAO.addCart(userId, productId, productCount, productInfoDTO.getPrice());

		}

		if (count > 0) {
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

}
