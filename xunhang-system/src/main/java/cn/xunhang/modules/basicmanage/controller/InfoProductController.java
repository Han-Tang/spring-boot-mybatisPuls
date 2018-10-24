package cn.xunhang.modules.basicmanage.controller;


import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.base.Tree;
import cn.xunhang.common.excel.ExcelLogs;
import cn.xunhang.common.excel.ExcelUtil;
import cn.xunhang.common.utils.XmlUtil;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.entity.InfoProductBom;
import cn.xunhang.modules.basicmanage.entity.InfoProductExcel;
import cn.xunhang.modules.basicmanage.entity.ProductSale;
import cn.xunhang.modules.basicmanage.service.*;
import cn.xunhang.modules.basicmanage.vo.InfoProductVo;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzc
 * @since 2018-07-30
 */

@RestController
@RequestMapping("/infoProduct")
public class InfoProductController extends InfoBaseController {

    @Autowired
    private InfoProductService infoProductService;
    @Autowired
    private ProductProduceService productProduceService;
    @Autowired
    private ProductPurchService productPurchService;
    @Autowired
    private ProductSaleService productSaleService;
    @Autowired
    private InfoProductBomService infoProductBomService;


    /**
     * 保存
     * @param vo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody InfoProductVo vo) {

        infoProductService.insertInfo(vo.getInfoProduct());

        String id = vo.getInfoProduct().getId();
        vo.getProductProduce().setInfoProductId(id);
        vo.getProductPurch().setInfoProductId(id);
        vo.getProductSale().setInfoProductId(id);

        productProduceService.insertInfo(vo.getProductProduce());
        productPurchService.insertInfo(vo.getProductPurch());
        productSaleService.insertInfo(vo.getProductSale());
        return new Response("提交成功");
    }

    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody InfoProductVo vo) {

        infoProductService.updateInfo(vo.getInfoProduct());
        productProduceService.updateInfo(vo.getProductProduce());
        productPurchService.updateInfo(vo.getProductPurch());
        productSaleService.updateInfo(vo.getProductSale());

        return new Response("修改成功");
    }

    /**
     * 查询 带分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {

        Page<InfoProductVo> pageInfo = infoProductService.queryList(params);

        return new ObjectResponse<Page>(pageInfo);
    }

    /**
     * 查询明细
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public Response queryDetail(@RequestBody Map<String, Object> params) {

        Map<String, Object> map = infoProductService.queryDetails(params);
        return new ObjectResponse<Map>(map);
    }

    /**
     * 删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        List<String> ids = (List<String>)params.get("id");
        infoProductService.deleteInfo(ids);
        return new Response("删除成功");
    }


    /**
     * 导出产品资料
     * @author tyj
     * @date 2018-10-15 11:39
     */
    @PostMapping("/exportProduct")
    public void exportProduct(HttpServletResponse response, @RequestBody Map<String, Object> params){
        Boolean template = (Boolean) params.get("template");
        List<InfoProductExcel> ll = new ArrayList<InfoProductExcel>();
        if(template == null){
            List<InfoProductVo> list = infoProductService.queryList(params).getRecords();

            for (InfoProductVo vo : list){
                ll.add(InfoProductExcel.infoProductExcel(vo));
            }
        }else {
            InfoProductVo vo = new InfoProductVo();
            vo.setInfoProduct(new InfoProduct());
            vo.setProductSale(new ProductSale());
            ll.add(InfoProductExcel.infoProductExcel(vo));
        }

        String filename = "产品资料.Excel";
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Access-Control-Expose-Headers","Content-Disposition");
            response.setHeader("Content-Disposition", URLEncoder.encode(filename, "UTF-8") + ".xls");
            OutputStream out = response.getOutputStream();

            ExcelUtil.getInstance().exportExcel(out,ll, InfoProductExcel.class);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入产品资料
     * @author tyj
     * @date 2018-10-15 16:15
     */
    @PostMapping("/importProduct")
    public Response importProduct(@RequestParam(value="file" ,required=true) MultipartFile multipartFile) throws Exception{

        InputStream inputStream= multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        List<InfoProductExcel> importExcel = ExcelUtil.getInstance().importExcel(inputStream,InfoProductExcel.class,fileName);
        for (InfoProductExcel info : importExcel){
            InfoProductVo vo = InfoProductExcel.infoProductVo(info);

            infoProductService.insertInfo(vo.getInfoProduct());

            String id = vo.getInfoProduct().getId();
            vo.getProductProduce().setInfoProductId(id);
            vo.getProductPurch().setInfoProductId(id);
            vo.getProductSale().setInfoProductId(id);

            productProduceService.insertInfo(vo.getProductProduce());
            productPurchService.insertInfo(vo.getProductPurch());
            productSaleService.insertInfo(vo.getProductSale());
        }

        return new Response("导入产品资料成功");
    }

    /**
     * 产品BOM导入
     * @return
     */
    @PostMapping("/importBom")
    public Response importBom(@RequestParam(value="file" ,required=true) MultipartFile multipartFile,
                              @RequestParam(value="orderNum",required=true) String orderNum,
                              HttpServletRequest request) throws Exception{

        if(!multipartFile.isEmpty()){
            String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
            File dir = new File(filePath);
            if (!dir.exists()) dir.mkdir();

            String path = filePath + multipartFile.getOriginalFilename();
            File file = new File(path);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            try {
                infoProductBomService.importBom(file,orderNum);
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }finally {
                //如果不需要File文件可删除
                if (file.exists()) file.delete();
            }
        }
        return new Response("产品BOM导入成功");
    }

    /**
     * bom列表查询
     * @return
     */
    @PostMapping("/bomList")
    public Response bomList(@RequestBody Map params){
        List<Tree<InfoProductBom>> menuList = infoProductBomService.queryList(params);
        return new ObjectResponse<List>(menuList);
    }


}
