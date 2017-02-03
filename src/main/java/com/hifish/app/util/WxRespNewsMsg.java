package com.hifish.app.util; /**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信图文消息体
 * @author: yinhong
 * @date: 2016年11月29日 下午2:15:43
 * @version: V1.0
 */

import com.hifish.app.domain.wx.resp.WxRespBaseMsg;

import java.util.List;

/**
 * @Description: 微信图文消息体
 */
public class WxRespNewsMsg extends WxRespBaseMsg {

    // 图文消息个数，限制为 10 条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
    private List<WxArticle> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<WxArticle> getArticles() {
        return Articles;
    }

    public void setArticles(List<WxArticle> articles) {
        Articles = articles;
    }
}
