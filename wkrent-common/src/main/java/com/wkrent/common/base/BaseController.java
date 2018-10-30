package com.wkrent.common.base;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
public class BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected String getLoginAccount(HttpSession session) {
        if(session == null){
            return StringUtils.EMPTY;
        }
        return (String) session.getAttribute("userAccount");
    }
}
