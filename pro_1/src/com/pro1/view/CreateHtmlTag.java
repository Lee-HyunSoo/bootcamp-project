package com.pro1.view;
import com.pro1.domain.Cafe;
import java.util.ArrayList;
import java.util.Map;

public class CreateHtmlTag {
	
    public static StringBuilder tags = new StringBuilder();
    
    public static void createHtml(Map<String, ArrayList<Cafe>> cafes) {
        tags.append("<html>") ;
        tags.append("<head><title>Cafe</title></head>");
        tags.append("<header>");
        tags.append("<h1 style='text-align: center; margin-top: 20px;'>");
        tags.append("카페 별 메뉴판");
        tags.append("</h1>");
        tags.append("</header>");
        tags.append("<body>");
        for(String s : cafes.keySet()) {
            tags.append("<div style='float: left; text-align: center; margin: 20px; border: 1px solid gray; background-color:black;'>");
            tags.append("<div style='margin-top: 5px; margin: 20px; font-weight: bold; font-size:20px;background-color:red;'>");
            tags.append(s); // 카페 이름 출력
            tags.append("</div>");
            tags.append("<table>");
            int index = 0;
            for (int i = 0; i < cafes.get(s).size(); i++) {
                tags.append("<tr>");
                for (int j = 0 ; j < 4; j++) {
                    if (index >= cafes.get(s).size())
                        break;
                    tags.append("<td>");
                    tags.append("<button style='width:100px; height:80px; margin: 5px; font-family:돋움 font-weight:700;'>");
                    tags.append(cafes.get(s).get(index).getMenu());
                    tags.append("<br><br>");
                    tags.append(cafes.get(s).get(index).getPrice()).append("원");
                    tags.append("</button>");
                    tags.append("</td>");
                    index++;
                }
                tags.append("</tr>");
            }
            tags.append("</table>");
            tags.append("</div>");
        }
    }
    
    public static void createHtml(String cheapStore, String menu) {
        tags.append("<table style='border: 3px solid black; width = 600;'>");
        tags.append("<tr>");
        tags.append("<td>");
        tags.append("<span style='margin: 20px; font-size: 20px;'>");
        tags.append("<span style='font-size: 30px; color: red;'>");
        tags.append(menu);
        tags.append("</span>");
        tags.append("가 가장 싼 가게는");
        tags.append("<span style='font-size: 30px; color: red;'>");
        tags.append(cheapStore);
        tags.append("</span>");
        tags.append(" 입니다.");
        tags.append("</span>");
        tags.append("<td>");
        tags.append("</tr>");
        tags.append("</table>");
        tags.append("<hr style='margin-top:20px; margin-bottom: 20px;'>");
    }
    
    public static void createHtml(boolean coupon) {
        if (coupon) {
            tags.append("<div style='margin: 20px'>");
            tags.append("<span style='font-size: 20px; margin-right: 10px;'>");
            tags.append("해당 메뉴의 쿠폰을 사용하시겠습니까?");
            tags.append("</span>");
            tags.append("<input type='radio' name='coupon'>");
            tags.append("<span style='margin-right: 10px;'>");
            tags.append("예");
            tags.append("</span>");
            tags.append("<input type='radio' name='coupon'>");
            tags.append("<span>");
            tags.append("아니오");
            tags.append("</span>");
            tags.append("</div>");
            tags.append("<hr style='margin-top:20px; margin-bottom: 20px;'>");
        }
        else {
            tags.append("<div style='margin: 20px'>");
            tags.append("<span style='font-size: 20px;'>");
            tags.append("해당 메뉴의 쿠폰이 없습니다.");
            tags.append("</span>");
            tags.append("</div>");
            tags.append("<hr style='margin-top:20px; margin-bottom: 20px;'>");
        }
    }
    
    public static void createHtml(Cafe cafe, boolean coupon) {
        tags.append("<div style='float: left; text-align: center; margin: 20px; padding: 10px; border: 1px solid gray;'>");
        tags.append("<span style='font-size: 30px;'>");
        tags.append("주문 내역");
        tags.append("<hr />");
        tags.append("</span>");
        tags.append("<table>");
        tags.append("<tr>");
        tags.append("<td>");
        tags.append("<div style='text-align: left; width:500px; height:500px; margin: 5px;' disabled>");
        tags.append("<span style='font-size: 20px; color: black;'>");
        tags.append("매장명 : ").append(cafe.getStore()).append("<br><br>");
        tags.append("결제한 메뉴 : ").append(cafe.getMenu()).append("<br><br>");
        if (coupon)
            tags.append("결제 금액 : ").append(cafe.getPrice() - (cafe.getPrice()) * 10 / 100).append("원").append("<br><br>");
        else
            tags.append("결제 금액 : ").append(cafe.getPrice()).append("원").append("<br><br>");
        tags.append("결제한 시간 : ").append(cafe.getTime()).append("<br><br>");
        tags.append("</span>");
        tags.append("</div>");
        tags.append("</td>");
        tags.append("</tr>");
        tags.append("</table>");
        tags.append("</div>");
        tags.append("</body>");
        tags.append("</html>");
    }
    
}