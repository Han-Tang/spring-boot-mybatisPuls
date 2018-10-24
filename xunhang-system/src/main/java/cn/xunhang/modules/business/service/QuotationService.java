package cn.xunhang.modules.business.service;

import cn.xunhang.modules.business.dao.QuotationDtlDao;
import cn.xunhang.modules.business.dao.QuotationHdrDao;
import cn.xunhang.modules.business.entity.QuotationDtl;
import cn.xunhang.modules.business.entity.QuotationHdr;
import cn.xunhang.modules.business.service.infoBase.InfoBaseBillService;
import cn.xunhang.modules.business.service.infoBase.InfoBaseService;
import cn.xunhang.vo.Param;

import java.util.Map;

/**
 * <p>
 *  销售报价单服务类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public interface QuotationService extends InfoBaseBillService<QuotationHdr,QuotationHdrDao,QuotationDtl,QuotationDtlDao> {

    void deleteInfo(Map<String, Object> params);

    /**
     * 报价单审核
     * @param document
     * @return
     */
    QuotationHdr examine(QuotationHdr document);

    /**
     * 流程退回
     */
    QuotationHdr processReturn(Map<String, Object> params);

    /**
     * 打印
     */
    Param<QuotationHdr,QuotationDtl> printing(QuotationHdr document);

    /**
     * 校验数据
     * @param param
     */
    void verifyForm(Param<QuotationHdr, QuotationDtl> param);


}
