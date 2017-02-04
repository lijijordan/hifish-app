package com.hifish.app.util;

import com.hifish.app.task.WechatTask;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/2/4
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class QRCodeUtil {


    private static String genPostJson(String sn) {
        JSONObject qrCodeJson = new JSONObject();
        qrCodeJson.put("action_name", "QR_LIMIT_STR_SCENE");
        JSONObject scene = new JSONObject();
        JSONObject sceneId = new JSONObject();
        sceneId.put("scene_str", sn);
        scene.put("scene", sceneId);
        qrCodeJson.put("action_info", scene);
//        qrCodeJson.put()
        System.out.println("post json:" + qrCodeJson.toString());
        return qrCodeJson.toString();
    }

    /**
     * Generate qr code.
     *
     * @param sn the sn
     * @throws Exception the exception
     */
    public static void generateQRCode(String sn) throws Exception {
        String postJson = genPostJson(sn);
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + WechatTask.getToken();
        System.out.println("post url:" + url);
        try {
            String rs = HttpUtils.sendPostBuffer(url, postJson);
            System.out.println(rs);
            JSONObject jsonObject = new JSONObject(rs);
            String ticket = (String) jsonObject.get("ticket");
            System.out.println("二维码下载地址:" + "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket);
        } catch (Exception e) {
            System.out.println("请求错误！");
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        generateQRCode("A001");
    }

}
