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
 * The type Wx resp news msg.
 *
 * @Description: 微信图文消息体
 */
public class WxRespNewsMsg extends WxRespBaseMsg {

    // 图文消息个数，限制为 10 条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
    private List<WxArticle> Articles;

    /**
     * Gets article count.
     *
     * @return the article count
     */
    public int getArticleCount() {
        return ArticleCount;
    }

    /**
     * Sets article count.
     *
     * @param articleCount the article count
     */
    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
    public List<WxArticle> getArticles() {
        return Articles;
    }

    /**
     * Sets articles.
     *
     * @param articles the articles
     */
    public void setArticles(List<WxArticle> articles) {
        Articles = articles;
    }
}
