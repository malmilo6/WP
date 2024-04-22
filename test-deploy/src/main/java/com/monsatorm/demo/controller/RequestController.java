package com.monsatorm.demo.controller;

import com.monsatorm.demo.model.projections.NoIdOrderDetailDtoPImpl;
import com.monsatorm.demo.model.projections.RequestHistoryDtoPImpl;
import com.monsatorm.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RequestController {
    private final OrderDetailService orderDetailService;
    private final SessionService sessionService;
    private final RequestService requestService;
    private final CookieService cookieService;
    private final OrderService orderService;

    @GetMapping("/init-session/{tableId}")
    public String initSession(@PathVariable("tableId") Integer tableId,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        String sessionId = cookieService.checkUserCookies(request, response);
//        boolean sessionStatus = sessionService.checkSessionExpiration(sessionId);
//        String sessionId = UUID.randomUUID().toString();

        sessionService.updateSessionTimeOrAddSession(sessionId, tableId, sessionId, null);

        //todo cookie permission
        //todo present valid || present expired || absent
        return sessionId;
    }

    @GetMapping("/home")
    String  homePage() {
        //todo return main page
        return "homeeeee";
    }
    @PostMapping("/create-request/{orderId}/{requestTypeId}")
    void createRequest(@PathVariable("orderId") String orderId, @PathVariable("requestTypeId") Integer requestTypeId) {

        requestService.createRequest(orderId, requestTypeId);
        requestService.broadcastRecentRequests();
    }
    @PutMapping("/close-request/{requestId}")
    void closeRequest(@PathVariable("requestId") Integer requestId) {
        requestService.closeRequest(requestId);
    }

    @PutMapping("/close-order/{orderId}")
    void closeOrder(@PathVariable("orderId") String orderId){
        orderService.closeOrder(orderId);
    }
//    @GetMapping("/get-by-table-and-id/{tableId}/{orderId}")
//    public List<OrderDetailDtoPImpl> getOrderDetailByTableId(@PathVariable("tableId") Integer tableId,
//                                                             @PathVariable("orderId") Optional<String> orderId) {
//        return orderDetailService.getOrderDetailByTableId(tableId, orderId);
//    }
    @GetMapping("/get-by-table/{tableId}")
    public List<NoIdOrderDetailDtoPImpl> getOrderDetailByTableIdNoId(@PathVariable("tableId") Integer tableId) {

        return orderDetailService.getOrderDetailByOnlyTableId(tableId);
    }

    @GetMapping("/recent-requests")
    public List<RequestHistoryDtoPImpl> getRecentRequests() {

        return requestService.getRecentRequests();
    }

    //test
    @GetMapping("/{tableId}")
    public String getOrderId(@PathVariable("tableId") Integer tableId) {
        List<NoIdOrderDetailDtoPImpl> orderDetails = orderDetailService.getOrderDetailByOnlyTableId(tableId);
        return !orderDetails.isEmpty() ? orderDetails.get(0).getKey() : null;
    }
}