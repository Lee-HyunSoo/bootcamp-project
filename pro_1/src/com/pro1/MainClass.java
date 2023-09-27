package com.pro1;
import com.pro1.domain.Cafe;
import com.pro1.service.CafeServiceImpl;
import com.pro1.view.CreateHtmlFile;
import com.pro1.view.CreateHtmlTag;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        CafeServiceImpl cafeService = new CafeServiceImpl();

        CreateHtmlTag.createHtml(cafeService.getCafes().cafes);
        System.out.println();
        System.out.println("\r\n"
        		+ " ██╗       ██╗███╗   ██╗██████╗ ██╗   ██╗████████╗    ███╗   ███╗███████╗███╗   ██╗██╗   ██╗\r\n"
        		+ "███║       ██║████╗  ██║██╔══██╗██║   ██║╚══██╔══╝    ████╗ ████║██╔════╝████╗  ██║██║   ██║\r\n"
        		+ "╚██║       ██║██╔██╗ ██║██████╔╝██║   ██║   ██║       ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║\r\n"
        		+ " ██║       ██║██║╚██╗██║██╔═══╝ ██║   ██║   ██║       ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║\r\n"
        		+ " ██║██╗    ██║██║ ╚████║██║     ╚██████╔╝   ██║       ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝\r\n"
        		+ " ╚═╝╚═╝    ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝    ╚═╝       ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \r\n"
        		+ "                                                                                            \r\n"
        		+ "");
        System.out.print("메뉴를 입력해 주세요 : ");
        String menu = scan.nextLine(); // pick menu

        String cheapStore = cafeService.findCheapStore(menu); // find cheap store
        System.out.println("해당 메뉴가 가장 싼 가게는 " + cheapStore + "입니다.");
        CreateHtmlTag.createHtml(cheapStore, menu);

        System.out.println();
        System.out.println("\r\n"
        		+ "██████╗         ██████╗██╗  ██╗███████╗ ██████╗██╗  ██╗     ██████╗ ██████╗ ██╗   ██╗██████╗  ██████╗ ███╗   ██╗\r\n"
        		+ "╚════██╗       ██╔════╝██║  ██║██╔════╝██╔════╝██║ ██╔╝    ██╔════╝██╔═══██╗██║   ██║██╔══██╗██╔═══██╗████╗  ██║\r\n"
        		+ " █████╔╝       ██║     ███████║█████╗  ██║     █████╔╝     ██║     ██║   ██║██║   ██║██████╔╝██║   ██║██╔██╗ ██║\r\n"
        		+ "██╔═══╝        ██║     ██╔══██║██╔══╝  ██║     ██╔═██╗     ██║     ██║   ██║██║   ██║██╔═══╝ ██║   ██║██║╚██╗██║\r\n"
        		+ "███████╗██╗    ╚██████╗██║  ██║███████╗╚██████╗██║  ██╗    ╚██████╗╚██████╔╝╚██████╔╝██║     ╚██████╔╝██║ ╚████║\r\n"
        		+ "╚══════╝╚═╝     ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝╚═╝  ╚═╝     ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝      ╚═════╝ ╚═╝  ╚═══╝\r\n"
        		+ "                                                                                                                \r\n"
        		+ "");
        boolean flag = cafeService.checkCoupon(cheapStore, menu);
        boolean couponUse = false;
        CreateHtmlTag.createHtml(flag);
        if (flag) {
            System.out.print("해당 메뉴의 쿠폰이 있습니다. 사용하시겠습니까? : ");
            String input = scan.nextLine();
            if (input.equals("예")) {
                System.out.println("쿠폰을 사용합니다.");
                couponUse = true;
                cafeService.useCoupon(couponUse, cheapStore, menu); // update coupon table
            }
            else
                System.out.println("쿠폰을 사용하지 않습니다.");
        }
        else
            System.out.println("해당 메뉴의 쿠폰이 없습니다.");

        System.out.println();
        System.out.println("\r\n"
        		+ "██████╗        ██████╗  █████╗ ██╗   ██╗███╗   ███╗███████╗███╗   ██╗████████╗    ██╗███╗   ██╗███████╗ ██████╗ \r\n"
        		+ "╚════██╗       ██╔══██╗██╔══██╗╚██╗ ██╔╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝    ██║████╗  ██║██╔════╝██╔═══██╗\r\n"
        		+ " █████╔╝       ██████╔╝███████║ ╚████╔╝ ██╔████╔██║█████╗  ██╔██╗ ██║   ██║       ██║██╔██╗ ██║█████╗  ██║   ██║\r\n"
        		+ " ╚═══██╗       ██╔═══╝ ██╔══██║  ╚██╔╝  ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║       ██║██║╚██╗██║██╔══╝  ██║   ██║\r\n"
        		+ "██████╔╝██╗    ██║     ██║  ██║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║       ██║██║ ╚████║██║     ╚██████╔╝\r\n"
        		+ "╚═════╝ ╚═╝    ╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ \r\n"
        		+ "                                                                                                                \r\n"
        		+ "");
        System.out.print("주문하신 결제 내역 : ");
        Cafe cafe = cafeService.paymentInfo(cheapStore, menu);
        System.out.println(cafe.toString()); // print payment
        CreateHtmlTag.createHtml(cafeService.paymentInfo(cheapStore, menu), flag);
        CreateHtmlFile.filewrite(CreateHtmlTag.tags.toString()); // cafe's menu list to html

        System.out.println();
        System.out.println("\r\n"
        		+ "██╗  ██╗        ██████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ███████╗\r\n"
        		+ "██║  ██║       ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔════╝ ██╔════╝\r\n"
        		+ "███████║       ██║     ███████║███████║██████╔╝██║  ███╗█████╗  \r\n"
        		+ "╚════██║       ██║     ██╔══██║██╔══██║██╔══██╗██║   ██║██╔══╝  \r\n"
        		+ "     ██║██╗    ╚██████╗██║  ██║██║  ██║██║  ██║╚██████╔╝███████╗\r\n"
        		+ "     ╚═╝╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝\r\n"
        		+ "                                                                \r\n"
        		+ "");
        System.out.print("결제하시겠습니까? : ");
        String input = scan.nextLine();
        cafeService.choosePayment(cafe, input); // thread.sleep
    }

}