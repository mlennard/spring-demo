/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.sample.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:vzhukovskii@codenvy.com">Vladyslav Zhukovskii</a>
 * @version $Id: 12.09.13 vlad $
 */
@Controller
@RequestMapping("/")
public class UrlController {
    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "main";
    }

    @RequestMapping(value = "site", method = RequestMethod.GET)
    @ExceptionHandler(JSoupException.class)
    public String getInfo(@RequestParam(value = "url", required = true) String url, ModelMap model) {
        String title;
        Map<String, URL> links = new HashMap<String, URL>();

        try {
            Document doc = Jsoup.connect("http://" + url).get();
            title = doc.title();
            Elements elemLinks = doc.select("a[href]");

            for (Element link : elemLinks) {
                links.put(link.text(), new URL(link.attr("abs:href")));
            }
        } catch (Exception e) {
            throw new JSoupException(e.getMessage());
        }

        model.addAttribute("title", title);
        model.addAttribute("links", links);

        return "info";
    }
}