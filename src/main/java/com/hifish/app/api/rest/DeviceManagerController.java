package com.hifish.app.api.rest;

import com.hifish.app.util.SignUtil;
import com.hifish.app.vo.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * The type Device manager controller.
 */
@RestController
@RequestMapping(value = "/device/manager")
@Api(value = "设备管理API", description = "设备管理API,提供设备管理接口,web页面调用")
public class DeviceManagerController extends AbstractRestHandler {

    private final Logger logger = LoggerFactory.getLogger(DeviceManagerController.class);


    /**
     * Register common device base response.
     *
     * @param string the string
     * @return the base response
     */
    @RequestMapping(value = "/test",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "通用设备注册")
    @Transactional
    public BaseResponse registerCommonDevice(String string) {
        return new BaseResponse(RESPONSE_SUCCESS);
    }


    /**
     * Do get string.
     *
     * @param signature the signature
     * @param timestamp the timestamp
     * @param nonce     the nonce
     * @param echostr   the echostr
     * @return the string
     */
    @RequestMapping(value = "/security",
            method = RequestMethod.GET)
    public String doGet(@RequestParam(value = "signature", required = true) String signature,
                        @RequestParam(value = "timestamp", required = true) String timestamp,
                        @RequestParam(value = "nonce", required = true) String nonce,
                        @RequestParam(value = "echostr", required = true) String echostr) {
        logger.info(signature);
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        } else {
            logger.info("这里存在非法请求！");
            return "false";
        }
    }

//    @RequestMapping(value = "/security", method = RequestMethod.POST)
//    // post 方法用于接收微信服务端消息
//    public void doPost() {
//        System.out.println("这是 post 方法！");
//    }

}
