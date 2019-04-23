<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/venus.css">
<link href="https://fonts.googleapis.com/css?family=Great+Vibes&amp;subset=latin-ext" rel="stylesheet">
<title>カート画面</title>
</head>
<body>
	<script type="text/javascript" src="./js/cart.js"></script>
	<jsp:include page="header.jsp" />
	<div id="contents">
	<div class="top">
		<h1>カート画面</h1>
	</div>
		<s:if test="cartInfoDTOList != null && cartInfoDTOList.size() > 0">
			<s:form id="cartForm">
				<table class="horizontal-list-table">
					<thead>
						<tr>
							<th><s:label value="#" /></th>
							<th><s:label value="商品名" /></th>
							<th><s:label value="商品名ふりがな" /></th>
							<th><s:label value="商品画像" /></th>
							<th><s:label value="値段" /></th>
							<th><s:label value="発売会社名" /></th>
							<th><s:label value="発売年月日" /></th>
							<th><s:label value="購入個数" /></th>
							<th><s:label value="合計金額" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="cartInfoDTOList">
							<tr>
								<td><s:checkbox name="cartCheckList" class="cartCheckList"
										value="checked" fieldValue="%{productId}"
										onchange="checkValue(this)" /></td>
								<s:hidden name="productId" value="%{productId}" />
								<td><s:property value="productName" /></td>
								<td><s:property value="productNameKana" /></td>
								<td><img
									src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
									width="50px" height="50px" /></td>
								<td><s:property value="price" />円</td>
								<td><s:property value="releaseCompany" /></td>
								<td><s:property value="releaseDate" /></td>
								<td><s:property value="productCount" /></td>
								<td><s:property value="totalPrice" />円</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<h2 class="cartTotalPrice">
					<s:label value="カート合計金額 :" />
					<s:property value="allTotalPrice" />
					円
				</h2>
				<br>
				<div class="submit_btn_box">
					<s:submit value="決済" class="submit_btn"
						onclick="goSettlementConfirmAction()" />
				</div>

				<div class="submit_btn_box">
					<s:submit value="削除" id="deleteButton" class="submit_btn"
						onclick="goDeleteCartAction()" disabled="true" />
				</div>

			</s:form>
		</s:if>
		<s:else>
			<div class="info">カート情報がありません。</div>
		</s:else>
	</div>
</body>
</html>