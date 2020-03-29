package com.example.tangboyang1.controller;

import com.example.tangboyang1.dao.OrderDao;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.utils.QRCodeGenerator;
import com.example.tangboyang1.utils.ZXingCode;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

@RestController
public class GetORCode {
    @Autowired
    private OrderDao orderDao;
    @GetMapping(value="/payQRimageByOrderid")
    public ResponseEntity<byte[]> getQRImage(HttpServletRequest request,Integer orderid) throws UnknownHostException {
        if(orderid==null||orderid.equals("")){
            throw new ErrorCodeException(CommonErrorCode.INVALID_PARAMS);
        }try {
            Orders orderByOrderId = orderDao.findOrderByOrderId(orderid);
            if(orderByOrderId.getPaystate()==1){
                throw new  ErrorCodeException(CommonErrorCode.Order_Pay_Error);
            }

        }catch (ErrorCodeException e){
            throw new  ErrorCodeException(CommonErrorCode.Order_Pay_Error);
        }catch (Exception e){
            throw new ErrorCodeException(CommonErrorCode.UNKOWN_ERROR);
        }
        String serverName = request.getServerName();//localhost
        int serverPort = request.getServerPort();//8080
        String contextPath = request.getContextPath();//项目名

        //二维码内的信息
        String info = "http://"+serverName+":"+serverPort+contextPath+"/OrderController/update/orders/payState/by/Orderid?id="+orderid;
        byte[] qrcode = null;
        try {
            qrcode = QRCodeGenerator.getQRCodeImage(info, 360, 360);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]> (qrcode, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "qw")
    public ResponseEntity dsf(){

        File logoFile = new File("C:\\Users\\Administrator\\Desktop\\111.jpg");
        File QrCodeFile = new File("C:\\Users\\Administrator\\Desktop/05.png");
        String url = "https://www.baidu.com/";
        String note = "访问百度连接";
        BufferedImage bufferedImage = ZXingCode.drawLogoQRCode(logoFile, QrCodeFile, url, note);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<> ("sadfsdafsadfsdsddsd", headers, HttpStatus.CREATED);
    }
}
