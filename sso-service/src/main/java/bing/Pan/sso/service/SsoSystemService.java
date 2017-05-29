package bing.Pan.sso.service;

import bing.Pan.sso.mapper.mapperInterface.SsoSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 11:41
 * @desc :
 */

@Service
public class SsoSystemService extends BaseService{


    @Autowired private SsoSystemMapper ssoSystemMapper;

}
