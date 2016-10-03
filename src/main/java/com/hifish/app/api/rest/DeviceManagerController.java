package com.hifish.app.api.rest;

import com.hifish.app.service.DeviceManager;
import com.hifish.app.vo.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Device manager controller.
 */
@RestController
@RequestMapping(value = "/device/manager")
@Api(value = "设备管理API", description = "设备管理API,提供设备管理接口,web页面调用")
public class DeviceManagerController extends AbstractRestHandler {

    private final Logger log = LoggerFactory.getLogger(DeviceManagerController.class);

    @Autowired
    private DeviceManager deviceManager;

    @RequestMapping(value = "/test",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "通用设备注册")
    @Transactional
    public BaseResponse registerCommonDevice(String string) {
        return new BaseResponse(RESPONSE_SUCCESS);
    }


}
