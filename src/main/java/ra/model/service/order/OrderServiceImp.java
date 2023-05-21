package ra.model.service.order;

import ra.model.entity.Order;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class OrderServiceImp implements  IOrderService{
    @Override
    public int createNewOrder(Order o) {
        int newOrderId =-1;
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATENEWORDER(?,?,?,?)}");
            callSt.setInt(1,o.getOrderId());
            callSt.setString(2, o.getPhone());
            callSt.setString(3, o.getAddress());
            callSt.registerOutParameter(4, Types.INTEGER);
            callSt.executeUpdate();
            newOrderId = callSt.getInt(4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newOrderId;

    }
}
