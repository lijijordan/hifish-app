/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信XML消息体处理工具
 * @author: yinhong
 * @date: 2016年11月29日 下午1:15:16
 * @version: V1.0
 */
package com.hifish.app.util;

import com.hifish.app.domain.wx.resp.WxRespTextMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Wx msg util.
 *
 * @Description: 微信XML消息体处理工具
 */
public class WxMsgUtil {

    /**
     * 对象到 xml 的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有 xml 节点的转换都增加 CDATA 标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * Parse xml map.
     *
     * @param request the request
     * @return the map
     * @throws IOException       the io exception
     * @throws DocumentException the document exception
     * @Description: 解析微信发来的请求 （XML）,目前解析两层XML
     * @return: Map<String String>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request)
            throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;

        try {
            inputStream = request.getInputStream();

            if (inputStream != null) {
                SAXReader reader = new SAXReader();
                Document document = reader.read(inputStream);
                if (document != null) {
                    Element root = document.getRootElement();
                    if (root != null) {
                        List<Element> elementList = root.elements();
                        if (elementList != null) {
                            for (Element e : elementList) {
                                map.put(e.getName(), e.getText());

                                List<Element> innerElementList = e.elements();
                                if (innerElementList != null) {
                                    for (Element innerElement : innerElementList) {
                                        map.put(innerElement.getName(), innerElement.getText());
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return map;
    }

    /**
     * Text message to xml string.
     *
     * @param textMessage the text message
     * @return the string
     * @Description: 文本消息对象转换成 xml
     * @return: String
     */
    public static String textMessageToXml(WxRespTextMsg textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }


    /**
     * 判断是否是QQ表情
     *
     * @param content the content
     * @return boolean
     */
    public static boolean isQqFace(String content) {
        boolean result = false;
        // 判断QQ表情的正则表达式
        String qqfaceRegex =
                "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * emoji表情转换(hex -> utf-16)
     *
     * @param hexEmoji the hex emoji
     * @return string
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

}
