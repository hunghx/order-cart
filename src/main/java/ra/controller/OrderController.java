package ra.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.Order;
import ra.model.entity.UserLogin;
import ra.model.service.cart.CartServiceImp;
import ra.model.service.order.OrderServiceImp;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("orderController")
public class OrderController {
    OrderServiceImp orderService = new OrderServiceImp();
    @PostMapping("/create")
    public String create(@RequestParam("phone") String phone, @RequestParam("address") String address, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        int newCartId =  orderService.createNewOrder(new Order(userLogin.getCartId(),phone,address));
        userLogin.setCartId(newCartId);
        session.setAttribute("userLogin",userLogin);
        return "redirect:/cartController";
    }
}
