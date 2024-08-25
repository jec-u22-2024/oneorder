package com.preflame.oneorder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String[] existPathList = new String[] {
		"(?i)^\\/order/main\\/?$",
		"(?i)^\\/order/cart\\/?$",
		"(?i)^\\/order/hist\\/?$",
		"(?i)^\\/order/callStaff\\/?$",
		"(?i)^\\/order/accounting\\/?$",
		"(?i)^\\/master/regi\\/?$",
		"(?i)^\\/master/history\\/?$",
		"(?i)^\\/master/merchandice\\/?$",
		"(?i)^\\/master/category\\/?$",
		"(?i)^\\/master/table\\/?$",
		"(?i)^\\/master\\/?$",
		"(?i)^\\/kitchen\\/?$",
		"(?i)^\\/kitchen/home\\/?$",
		"(?i)^\\/kitchen/order\\/?$",
		"(?i)^\\/kitchen/recommend\\/?$",
	};

	@GetMapping(value = "/error")
	public String handleError(HttpServletRequest request, HttpServletResponse response) {
		// ステータスコード取得
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// URI取得
		Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			// ステータスコードが404のとき
			if (statusCode == HttpServletResponse.SC_NOT_FOUND) {
				if (uri != null ) {
					for (String path : existPathList) {
						if (uri.toString().matches(path)) {
							// ステータスコードを200に変更
							response.setStatus(HttpServletResponse.SC_OK);
						}
					}
				}
				return "index.html";
			}
		}

		return "error";
	}
}
